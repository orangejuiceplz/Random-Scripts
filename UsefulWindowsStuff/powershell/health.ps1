Get-PhysicalDisk | Get-StorageReliabilityCounter | 
    Select-Object DeviceId, WearsOutCount, Temperature, ReadErrorsTotal, WriteErrorsTotal

Get-WmiObject Win32_OperatingSystem | 
    Select-Object @{Name="MemoryUsage";Expression={"{0:N2}" -f ((($_.TotalVisibleMemorySize - $_.FreePhysicalMemory)*100)/ $_.TotalVisibleMemorySize)}}

Get-WmiObject Win32_Processor | 
    Select-Object DeviceID, LoadPercentage

Get-Process | Sort-Object CPU -Descending | Select-Object -First 5 Name, CPU