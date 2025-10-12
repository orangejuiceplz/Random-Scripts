#!/usr/bin/env fish

# Color definitions for output
set -l GREEN '\033[0;32m'
set -l YELLOW '\033[1;33m'
set -l RED '\033[0;31m'
set -l NC '\033[0m' # No Color

function print_info
    echo -e "$GREEN[INFO]$NC $argv"
end

function print_warn
    echo -e "$YELLOW[WARN]$NC $argv"
end

function print_error
    echo -e "$RED[ERROR]$NC $argv"
end

# Get the directory where the script is located
set SCRIPT_DIR (dirname (status -f))
set CONFIG_FILE ~/.config/fish/config.fish

# Backup existing config
if test -f $CONFIG_FILE
    print_info "Backing up existing config.fish to config.fish.backup"
    cp $CONFIG_FILE $CONFIG_FILE.backup
end

# 1. Check and install uwufetch
print_info "Checking for uwufetch..."
if not command -v uwufetch &> /dev/null
    print_warn "uwufetch not found. Installing..."
    sudo pacman -S --noconfirm uwufetch
    if test $status -eq 0
        print_info "uwufetch installed successfully"
    else
        print_error "Failed to install uwufetch"
        exit 1
    end
else
    print_info "uwufetch is already installed"
end

# Add uwufetch to the top of config.fish if not already present
if test -f $CONFIG_FILE
    if not grep -q "uwufetch" $CONFIG_FILE
        print_info "Adding uwufetch to the top of config.fish"
        set TEMP_FILE (mktemp)
        echo "# Display system info" > $TEMP_FILE
        echo "uwufetch" >> $TEMP_FILE
        echo "" >> $TEMP_FILE
        cat $CONFIG_FILE >> $TEMP_FILE
        mv $TEMP_FILE $CONFIG_FILE
    else
        print_info "uwufetch already in config.fish"
    end
else
    print_info "Creating new config.fish with uwufetch"
    mkdir -p (dirname $CONFIG_FILE)
    echo "# Display system info" > $CONFIG_FILE
    echo "uwufetch" >> $CONFIG_FILE
    echo "" >> $CONFIG_FILE
end

# 2. Check and install zoxide
print_info "Checking for zoxide..."
if not command -v zoxide &> /dev/null
    print_warn "zoxide not found. Installing..."
    sudo pacman -S --noconfirm zoxide
    if test $status -eq 0
        print_info "zoxide installed successfully"
    else
        print_error "Failed to install zoxide"
        exit 1
    end
else
    print_info "zoxide is already installed"
end

# Add zoxide to the bottom of config.fish if not already present
if not grep -q "zoxide init fish" $CONFIG_FILE
    print_info "Adding zoxide initialization to config.fish"
    echo "" >> $CONFIG_FILE
    echo "# Initialize zoxide" >> $CONFIG_FILE
    echo "zoxide init fish | source" >> $CONFIG_FILE
else
    print_info "zoxide already initialized in config.fish"
end

# 3. Check and install tlp
print_info "Checking for tlp..."
if not command -v tlp &> /dev/null
    print_warn "tlp not found. Installing..."
    sudo pacman -S --noconfirm tlp
    if test $status -eq 0
        print_info "tlp installed successfully"
    else
        print_error "Failed to install tlp"
        exit 1
    end
else
    print_info "tlp is already installed"
end

# Apply tlp.conf if it exists in the script directory
set TLP_CONF $SCRIPT_DIR/tlp.conf
if test -f $TLP_CONF
    print_info "Applying tlp.conf to /etc/tlp.conf"
    sudo cp $TLP_CONF /etc/tlp.conf
    if test $status -eq 0
        print_info "tlp.conf applied successfully"
        print_info "Starting tlp service..."
        sudo systemctl enable tlp.service
        sudo systemctl start tlp.service
    else
        print_error "Failed to apply tlp.conf"
        exit 1
    end
else
    print_warn "tlp.conf not found in $SCRIPT_DIR"
    print_warn "Skipping tlp configuration"
end

# 4. Check and install Sublime Text
print_info "Checking for Sublime Text..."
if not command -v subl &> /dev/null
    print_warn "Sublime Text not found. Installing..."
    
    # Install sublime-text-4 from the official repository
    # First, we need to import the GPG key and add the repository
    print_info "Setting up Sublime Text repository..."
    
    # Install the GPG key
    curl -O https://download.sublimetext.com/sublimehq-pub.gpg
    sudo pacman-key --add sublimehq-pub.gpg
    sudo pacman-key --lsign-key 8A8F901A
    rm sublimehq-pub.gpg
    
    # Add the stable channel to pacman.conf if not already present
    if not grep -q "sublimetext" /etc/pacman.conf
        print_info "Adding Sublime Text repository to pacman.conf"
        echo "" | sudo tee -a /etc/pacman.conf > /dev/null
        echo "[sublime-text]" | sudo tee -a /etc/pacman.conf > /dev/null
        echo "Server = https://download.sublimetext.com/arch/stable/x86_64" | sudo tee -a /etc/pacman.conf > /dev/null
        sudo pacman -Sy
    end
    
    sudo pacman -S --noconfirm sublime-text
    if test $status -eq 0
        print_info "Sublime Text installed successfully"
    else
        print_error "Failed to install Sublime Text"
        print_warn "You can try installing it manually from AUR: yay -S sublime-text-4"
    end
else
    print_info "Sublime Text is already installed"
end

# 5. Add ILoveCandy easter egg to pacman.conf
print_info "Checking for ILoveCandy in pacman.conf..."
if not grep -q "^ILoveCandy" /etc/pacman.conf
    print_info "Adding ILoveCandy easter egg to pacman.conf"
    # Backup pacman.conf
    sudo cp /etc/pacman.conf /etc/pacman.conf.backup
    
    # Add ILoveCandy under the [options] section
    sudo sed -i '/^\[options\]/a ILoveCandy' /etc/pacman.conf
    
    if test $status -eq 0
        print_info "ILoveCandy added successfully! 🍬"
        print_info "Your pacman progress bar will now be a Pac-Man eating candy!"
    else
        print_error "Failed to add ILoveCandy"
    end
else
    print_info "ILoveCandy is already enabled! 🍬"
end
source $CONFIG_FILE
