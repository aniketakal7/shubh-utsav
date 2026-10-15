$ErrorActionPreference = "Stop"

Write-Host "Fetching GitHub credentials from Git Credential Manager..."
$cred = (echo "protocol=https`nhost=github.com" | git credential fill | Out-String)
$token = (($cred -split "`n" | Where-Object { $_ -match "^password=" }) -replace "^password=","").Trim()

if (-not $token) {
    Write-Error "Could not retrieve GitHub token."
    exit 1
}

$headers = @{
    "Authorization" = "token $token"
    "User-Agent"    = "ShubhUtsav-Deploy"
    "Accept"        = "application/vnd.github.v3+json"
}

$bodyContent = @(
    "# Shubh Utsav 1.5.0 - Sacred Sadhana & Puja Suite! ✨",
    "",
    "### What's New in v1.5.0:",
    "- Digital Japa Mala: 108 Rudraksha bead counter, tactile vibration & 8 sacred Vedic mantras",
    "- Sacred Aarti & Chalisa Player: Synchronized karaoke lyrics, swinging Temple Bell & Conch",
    "- Daily Choghadiya Timings: 16 Day & Night Muhurats with live active indicator & Disha Shool",
    "- Vrat Katha & Parana Guide: Ekadashi, Pradosh, Fasting foods & resizable sacred stories",
    "- Home Screen Widget: Real-time Panchang, Tithi, Nakshatra & Muhurat right on your phone",
    "- Festive Greeting Card Maker: Personalize with family name & share to WhatsApp status",
    "- Google Play Publishing Kit: Complete Play Store metadata, privacy policy & bundle support",
    "",
    "Made with love by Aniket Akal"
) -join "`n"

$releaseObj = @{
    tag_name         = "v1.5.0"
    target_commitish = "main"
    name             = "Shubh Utsav v1.5.0 - Sacred Sadhana & Puja Suite"
    body             = $bodyContent
    draft            = $false
    prerelease       = $false
}
$releaseJson = $releaseObj | ConvertTo-Json -Depth 5

Write-Host "Creating GitHub Release v1.5.0..."
$createReleaseUrl = "https://api.github.com/repos/aniketakal7/shubh-utsav/releases"
$release = Invoke-RestMethod -Uri $createReleaseUrl -Headers $headers -Method Post -Body ([System.Text.Encoding]::UTF8.GetBytes($releaseJson)) -ContentType "application/json; charset=utf-8"

Write-Host "Release created successfully! ID: $($release.id)"
Write-Host "Release URL: $($release.html_url)"

$uploadUrlPattern = $release.upload_url -replace '\{\?name,label\}', ''
$apkPath = "app-release.apk"

if (-not (Test-Path $apkPath)) {
    $apkPath = "app\build\outputs\apk\release\app-release.apk"
}

Write-Host "Uploading APK asset from $apkPath..."
$uploadAssetUrl = "$uploadUrlPattern?name=app-release.apk"

$apkBytes = [System.IO.File]::ReadAllBytes((Resolve-Path $apkPath).Path)

$uploadHeaders = @{
    "Authorization"  = "token $token"
    "User-Agent"     = "ShubhUtsav-Deploy"
    "Content-Type"   = "application/vnd.android.package-archive"
}

$assetResponse = Invoke-RestMethod -Uri $uploadAssetUrl -Headers $uploadHeaders -Method Post -Body $apkBytes

Write-Host "APK uploaded successfully!"
Write-Host "Direct Download URL: $($assetResponse.browser_download_url)"
Write-Host "Asset Size: $([math]::Round($assetResponse.size / 1MB, 2)) MB"
