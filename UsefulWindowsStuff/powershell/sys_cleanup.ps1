Remove-Item -Path $env:TEMP\* -Recurse -Force
Remove-Item -Path C:\Windows\Temp\* -Recurse -Force

Clear-RecycleBin -Force

Get-WmiObject Win32_LogicalDisk | 
    Where-Object { $_.DriveType -eq 3 } | 
    Select-Object DeviceID, VolumeName, 
    @{Name="Size(GB)";Expression={"{0:N2}" -f ($_.Size / 1GB)}}, 
    @{Name="FreeSpace(GB)";Expression={"{0:N2}" -f ($_.FreeSpace / 1GB)}}