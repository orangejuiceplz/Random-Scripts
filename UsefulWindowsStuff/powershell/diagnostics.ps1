Get-NetIPConfiguration | Format-Table InterfaceAlias, IPv4Address, DNSServer

Test-Connection -ComputerName www.google.com -Count 4 | Format-Table Address, IPV4Address, ResponseTime

Test-NetConnection www.google.com -TraceRoute | Select-Object -ExpandProperty TraceRoute

# THIS ONLY WORKS IF YOU HAVE SPEEDTEST INSTALLED!!!!!!
Invoke-Expression "speedtest-cli --simple"