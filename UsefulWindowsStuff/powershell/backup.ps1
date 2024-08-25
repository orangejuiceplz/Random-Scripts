$source = "C:\PathToYourSourceFolder"
$destination = "D:\PathToYourBackupFolder"
$date = Get-Date -Format "yyyy-MM-dd"
$backupPath = Join-Path $destination "Backup_$date"

New-Item -ItemType Directory -Force -Path $backupPath
Copy-Item $source -Destination $backupPath -Recurse -Force
Compress-Archive -Path $backupPath -DestinationPath "$backupPath.zip"
Remove-Item $backupPath -Recurse -Force