try {
    $testData = @{
        input1 = @{
            value = 1
            unit = "METER"
        }
        input2 = $null
        meta = @{
            measurementType = "LengthUnit"
            operationType = "CONVERT"
            resultUnit = "CENTIMETERS"
        }
    }
    
    $jsonBody = $testData | ConvertTo-Json -Depth 10
    Write-Host "Sending request: $jsonBody"
    
    $response = Invoke-RestMethod -Uri 'http://localhost:8080/api/v1/quantities/perform' -Method Post -ContentType 'application/json' -Body $jsonBody -ErrorAction Stop
    
    Write-Host "Success: $($response.value) $($response.unit)"
} catch {
    Write-Host "Error: $($_.Exception.Message)"
    Write-Host "Status Code: $($_.Exception.Response.StatusCode.value__)"
    Write-Host "Response: $($_.Exception.Response.GetResponseStream())"
}
