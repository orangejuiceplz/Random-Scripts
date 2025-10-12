#!/bin/bash

# arch linux custom installation script
# based on user's specific installation guide

set -eo pipefail

# color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
BOLD='\033[1m'
NC='\033[0m' # no color

# ==========================================
# helper functions
# ==========================================

# prints a stylized section header
print_section_header() {
    local title="✨ $1 ✨"
    local len=${#title}
    local border=$(printf '═%.0s' $(seq 1 $((len + 2))))
    echo -e "\n${PURPLE}╔$border╗${NC}"
    echo -e "${PURPLE}║ ${BOLD}${CYAN}$title${NC} ${PURPLE}║${NC}"
    echo -e "${PURPLE}╚$border╝${NC}"
}

print_step() {
    echo -e "${GREEN}🚀 [STEP]:${NC} ${BOLD}$1${NC}"
}

print_warning() {
    echo -e "${YELLOW}🔔 [WARNING]:${NC} $1"
}

print_error() {
    echo -e "${RED}❌ [ERROR]:${NC} $1"
}

print_info() {
    echo -e "${BLUE}ℹ️  [INFO]:${NC} $1"
}

wait_for_user() {
    echo -e "\n${YELLOW}Press ENTER when ready to continue...${NC}"
    read
}

# ==========================================
# initialization
# ==========================================
print_section_header "INITIALIZATION"
print_step "Setting keyboard layout to US"
loadkeys us

# ==========================================
# wifi setup (optional)
# ==========================================
print_section_header "WIFI SETUP"
read -p "Do you need to setup WiFi? (y/n): " SETUP_WIFI

if [[ "$SETUP_WIFI" =~ ^[Yy]$ ]]; then
    print_step "Setting up WiFi connection"
    
    # get device name
    echo "Available devices:"
    iwctl device list
    
    read -p "Enter your wireless device name (usually wlan0): " WIFI_DEVICE
    
    # scan and connect
    iwctl station "$WIFI_DEVICE" scan
    sleep 2
    iwctl station "$WIFI_DEVICE" get-networks
    
    read -p "Enter the network SSID to connect to: " NETWORK_SSID
    
    # check if network requires password
    read -p "Does this network require a password? (y/n): " NEEDS_PASSWORD
    
    if [[ "$NEEDS_PASSWORD" =~ ^[Yy]$ ]]; then
        read -sp "Enter WiFi password: " WIFI_PASSWORD
        echo
        iwctl --passphrase "$WIFI_PASSWORD" station "$WIFI_DEVICE" connect "$NETWORK_SSID"
    else
        iwctl station "$WIFI_DEVICE" connect "$NETWORK_SSID"
    fi
    
    sleep 3
else
    print_info "Skipping WiFi setup - assuming Ethernet connection."
fi

# ==========================================
# verify connection
# ==========================================
print_section_header "CONNECTION TEST"
print_step "Testing internet connection"
echo "Press Ctrl+C when you see successful ping responses..."
ping -c 4 google.com || true
sleep 5

# ==========================================
# disk partitioning
# ==========================================
print_section_header "DISK PARTITIONING"
lsblk
echo ""
print_info "Available disks are shown above."

read -p "Enter the disk to install on (e.g., /dev/nvme0n1 or /dev/sda): " DISK
if [[ -z "$DISK" ]]; then
    print_error "Disk cannot be empty. Exiting."
    exit 1
fi

echo ""
echo "Partitioning options:"
echo "1. Automatic partitioning (200M boot, 16G swap, rest for root)"
echo "2. Manual partitioning with cfdisk"
read -p "Choose option (1 or 2): " PARTITION_OPTION

if [ "$PARTITION_OPTION" = "1" ]; then
    print_warning "This will ERASE ALL DATA on $DISK!"
    read -p "Are you sure? Type 'YES' to continue: " CONFIRM
    
    if [ "$CONFIRM" != "YES" ]; then
        print_error "Installation cancelled."
        exit 1
    fi
    
    print_step "Creating partitions automatically..."
    
    # detect disk type for partition naming
    if [[ "$DISK" == *"nvme"* ]] || [[ "$DISK" == *"mmcblk"* ]]; then
        BOOTDRIVE="${DISK}p1"
        SWAPDRIVE="${DISK}p2"
        ROOTDRIVE="${DISK}p3"
    else
        BOOTDRIVE="${DISK}1"
        SWAPDRIVE="${DISK}2"
        ROOTDRIVE="${DISK}3"
    fi
    
    # wipe disk and create new partition table
    sgdisk --zap-all "$DISK"
    sgdisk --clear "$DISK"
    
    # create partitions
    sgdisk --new=1:0:+200M --typecode=1:ef00 "$DISK"  # efi boot
    sgdisk --new=2:0:+16G --typecode=2:8200 "$DISK"   # swap
    sgdisk --new=3:0:0 --typecode=3:8300 "$DISK"      # root
    
    # inform kernel of partition changes
    partprobe "$DISK"
    sleep 2
    
    echo "Partitions created:"
    lsblk "$DISK"
    
else
    print_step "Opening cfdisk for manual partitioning..."
    cfdisk "$DISK"
    
    echo ""
    lsblk "$DISK"
    echo ""
    echo "Enter the partition paths:"
    
    read -p "Boot partition (e.g., ${DISK}1): " BOOTDRIVE
    if [[ -z "$BOOTDRIVE" ]]; then
        print_error "Boot partition cannot be empty. Exiting."
        exit 1
    fi
    
    read -p "Swap partition (e.g., ${DISK}2): " SWAPDRIVE
    if [[ -z "$SWAPDRIVE" ]]; then
        print_error "Swap partition cannot be empty. Exiting."
        exit 1
    fi
    
    read -p "Root partition (e.g., ${DISK}3): " ROOTDRIVE
    if [[ -z "$ROOTDRIVE" ]]; then
        print_error "Root partition cannot be empty. Exiting."
        exit 1
    fi
fi

# ==========================================
# format partitions
# ==========================================
print_section_header "FORMATTING PARTITIONS"
print_step "Formatting partitions..."
echo "Formatting root partition as ext4..."
mkfs.ext4 -F "$ROOTDRIVE"

echo "Formatting boot partition as FAT32..."
mkfs.fat -F 32 "$BOOTDRIVE"

echo "Setting up swap..."
mkswap "$SWAPDRIVE"

print_step "Partitions formatted successfully!"
lsblk

# ==========================================
# mounting
# ==========================================
print_section_header "MOUNTING FILE SYSTEMS"
wait_for_user

print_step "Mounting partitions"
mount "$ROOTDRIVE" /mnt
mkdir -p /mnt/boot/efi
mount "$BOOTDRIVE" /mnt/boot/efi
swapon "$SWAPDRIVE"

print_step "Verifying mounts..."
lsblk
wait_for_user

# ==========================================
# packages
# ==========================================
print_section_header "INSTALLING PACKAGES"
print_step "Installing base system and packages..."
pacstrap /mnt base linux linux-firmware sof-firmware grub base-devel vim efibootmgr nano networkmanager git

print_step "Generating fstab..."
genfstab -U /mnt > /mnt/etc/fstab

print_step "Verifying fstab..."
cat /mnt/etc/fstab
echo ""
print_warning "Please verify the fstab above is correct."
wait_for_user

# ==========================================
# entering installed system
# ==========================================
print_section_header "SYSTEM CONFIGURATION"
print_step "Configuring the installed system..."

# capture user information before chroot
read -p "Enter desired hostname: " HOSTNAME
if [[ -z "$HOSTNAME" ]]; then
    print_error "Hostname cannot be empty. Exiting."
    exit 1
fi

read -p "Enter desired username: " USERNAME
if [[ -z "$USERNAME" ]]; then
    print_error "Username cannot be empty. Exiting."
    exit 1
fi

echo ""
read -sp "Enter root password: " ROOT_PASSWORD
echo
read -sp "Retype root password: " ROOT_PASSWORD_VERIFY
echo

while [ "$ROOT_PASSWORD" != "$ROOT_PASSWORD_VERIFY" ]; do
    print_warning "Passwords don't match. Try again."
    read -sp "Enter root password: " ROOT_PASSWORD
    echo
    read -sp "Retype root password: " ROOT_PASSWORD_VERIFY
    echo
done

echo ""
read -sp "Enter password for user $USERNAME: " USER_PASSWORD
echo
read -sp "Retype password for user $USERNAME: " USER_PASSWORD_VERIFY
echo

while [ "$USER_PASSWORD" != "$USER_PASSWORD_VERIFY" ]; do
    print_warning "Passwords don't match. Try again."
    read -sp "Enter password for user $USERNAME: " USER_PASSWORD
    echo
    read -sp "Retype password for user $USERNAME: " USER_PASSWORD_VERIFY
    echo
done

# create chroot script
cat > /mnt/root/setup.sh <<'CHROOT_SCRIPT'
#!/bin/bash

# step 23-25: timezone and clock
ln -sf /usr/share/zoneinfo/America/New_York /etc/localtime
echo "Current date and time:"
date
hwclock --systohc

# step 29: configure locale.gen
sed -i 's/^#en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen
locale-gen

# step 30: set locale.conf
echo "LANG=en_US.UTF-8" > /etc/locale.conf

# step 31: set keymap
echo "KEYMAP=us" > /etc/vconsole.conf

# step 32: set hostname
echo "HOSTNAME_PLACEHOLDER" > /etc/hostname

# configure hosts file
cat > /etc/hosts <<EOF
127.0.0.1   localhost
::1         localhost
127.0.1.1   HOSTNAME_PLACEHOLDER.localdomain HOSTNAME_PLACEHOLDER
EOF

# step 33: set root password
echo "root:ROOT_PASSWORD_PLACEHOLDER" | chpasswd

# step 34: create user
useradd -m -G wheel -s /bin/bash USERNAME_PLACEHOLDER

# step 35: set user password
echo "USERNAME_PLACEHOLDER:USER_PASSWORD_PLACEHOLDER" | chpasswd

# step 36: configure sudo
sed -i 's/^# %wheel ALL=(ALL:ALL) ALL/%wheel ALL=(ALL:ALL) ALL/' /etc/sudoers

# step 38: update system
pacman -Syu --noconfirm

# step 41: enable networkmanager
systemctl enable NetworkManager

# step 42-43: install and configure grub
grub-install --target=x86_64-efi --efi-directory=/boot/efi --bootloader-id=GRUB DISK_PLACEHOLDER
grub-mkconfig -o /boot/grub/grub.cfg

echo ""
echo "Configuration complete!"
CHROOT_SCRIPT

# replace placeholders in the chroot script
sed -i "s|HOSTNAME_PLACEHOLDER|$HOSTNAME|g" /mnt/root/setup.sh
sed -i "s|ROOT_PASSWORD_PLACEHOLDER|$ROOT_PASSWORD|g" /mnt/root/setup.sh
sed -i "s|USERNAME_PLACEHOLDER|$USERNAME|g" /mnt/root/setup.sh
sed -i "s|USER_PASSWORD_PLACEHOLDER|$USER_PASSWORD|g" /mnt/root/setup.sh
sed -i "s|DISK_PLACEHOLDER|$DISK|g" /mnt/root/setup.sh

# make script executable and run it
chmod +x /mnt/root/setup.sh
arch-chroot /mnt /root/setup.sh

# clean up
rm /mnt/root/setup.sh

# ==========================================
# finish
# ==========================================
print_section_header "FINISHING UP"
print_step "Unmounting and preparing to reboot..."
umount -R /mnt || umount -l /mnt
swapoff "$SWAPDRIVE"

print_section_header "INSTALLATION COMPLETE! 🐧"

echo -e "${GREEN}Your new Arch Linux system is ready!${NC}\n"
echo "System information:"
echo -e "  Hostname: ${BOLD}$HOSTNAME${NC}"
echo -e "  Username: ${BOLD}$USERNAME${NC}"
echo -e "  Timezone: ${BOLD}America/New_York${NC}"
echo -e "  Locale:   ${BOLD}en_US.UTF-8${NC}"
echo ""
echo -e "${YELLOW}The system will reboot in 10 seconds...${NC}"
echo "Press Ctrl+C to cancel reboot."
sleep 10

reboot
