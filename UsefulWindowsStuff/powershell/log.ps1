$logPath = "C:\Logs\application.log"
$errorKeywords = @("error", "exception", "failed")

Get-Content $logPath | 
    Select-String -Pattern ($errorKeywords -join "|") | 
    ForEach-Object { 
        [PSCustomObject]@{
            Date = $_.Line.Substring(0, 19)
            Error = $_.Line.Substring(20)
        }
    } | Format-Table -AutoSize