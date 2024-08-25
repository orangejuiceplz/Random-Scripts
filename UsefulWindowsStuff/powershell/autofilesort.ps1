$sourceDir = "C:\PathToYourUnsortedFiles"
$destinations = @{ # Feel free to add any extentions here if you'd like!
    "Documents" = "*.docx", "*.pdf", "*.txt"
    "Images" = "*.jpg", "*.png", "*.gif"
    "Videos" = "*.mp4", "*.avi", "*.mov"
    "Music" = "*.mp3", "*.wav", "*.flac"
}

foreach ($folder in $destinations.Keys) {
    New-Item -Path "$sourceDir\$folder" -ItemType Directory -Force
    foreach ($extension in $destinations[$folder]) {
        Move-Item -Path "$sourceDir\$extension" -Destination "$sourceDir\$folder" -ErrorAction SilentlyContinue
    }
}