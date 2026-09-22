# Shubh Utsav - In-App Update Guide (Without Play Store) 🚀

If you are **not uploading the app to Google Play Store**, this guide explains how users can receive and install updates directly on their screens.

---

## How It Works Without Play Store

1. **You Build a New APK**:
   - Generate an APK (`app-debug.apk` or `app-release.apk`).
2. **You Host the APK Online (Free)**:
   - Upload it to **GitHub Releases**, **Google Drive**, **Firebase**, or your own server.
3. **App Detects the Update**:
   - Whenever users open Shubh Utsav, it checks `version_update.json`.
   - If a new version exists, the festive **Update Dialog** appears automatically on their screen.
4. **Direct In-App Download & Auto-Install**:
   - When the user taps **"Download & Install Update"**:
     - The APK downloads directly inside the app with a real-time progress bar (0% ➔ 100%).
     - As soon as it finishes, Android's system package installer pops up:
       *"Do you want to install an update to this application? Your existing data will not be lost."*
     - The user taps **"Install"**, and the app is updated!
   - There is also a **"Or download directly via browser"** fallback button if the user prefers downloading through Chrome.

---

## Step-by-Step Publishing Guide (Without Play Store)

### Step 1: Bump Version Numbers
In `app/build.gradle.kts`:
```kotlin
android {
    defaultConfig {
        versionCode = 2        // Increment build number (e.g. 1 -> 2)
        versionName = "1.1.0"  // Increment version name (e.g. 1.0.0 -> 1.1.0)
    }
}
```

### Step 2: Build the APK
In terminal:
```bash
./gradlew assembleRelease
# Or for testing:
./gradlew assembleDebug
```
Your APK will be located at:
`app/build/outputs/apk/release/app-release.apk` (or `debug/app-debug.apk`).

---

### Step 3: Host the APK (Option A: GitHub Releases - Recommended & Free)
1. Go to your GitHub repository in your browser.
2. Click **Releases** ➔ **Draft a new release**.
3. Set tag to `v1.1.0`.
4. Drag and drop your `.apk` file into the release assets.
5. Click **Publish Release**.
6. Right-click the `.apk` link under Assets and select **"Copy link address"**.
   Example link:
   `https://github.com/aniketakal7/shubh-utsav/releases/download/v1.1.0/app-release.apk`

---

### Step 4: Host the APK (Option B: Google Drive / Cloud / Website)
If using Google Drive:
1. Upload the APK file to Google Drive.
2. Set access to **"Anyone with the link can view"**.
3. Convert the shareable link to a direct download link, or paste the link in `directApkUrl`.

---

### Step 5: Update `version_update.json`
Update `version_update.json` with the new version code and your APK download link:
```json
{
  "latestVersionCode": 2,
  "latestVersionName": "1.1.0",
  "minSupportedVersionCode": 1,
  "isForceUpdate": false,
  "titles": {
    "en": "Shubh Utsav 1.1.0 Available! 🎉",
    "hi": "शुभ उत्सव 1.1.0 उपलब्ध है! 🎉",
    "mr": "शुभ उत्सव १.१.० उपलब्ध आहे! 🎉"
  },
  "releaseNotes": {
    "en": "• Added Ganesh Chaturthi 2025 Muhurats\n• Offline Vedic Panchang calculations\n• Interactive Puja steps tracker",
    "hi": "• नए त्योहार और मुहूर्त जोड़े गए\n• बेहतर पूजा विधि और आरती",
    "mr": "• नवीन सण आणि २०२५ मुहूर्त समाविष्ट\n• सुधारित पूजा विधी"
  },
  "updateUrl": "https://github.com/aniketakal7/shubh-utsav/releases/latest",
  "directApkUrl": "https://github.com/aniketakal7/shubh-utsav/releases/download/v1.1.0/app-release.apk",
  "publishedDate": "2025-01-01"
}
```

---

### Step 6: Host `version_update.json`
Commit `version_update.json` to GitHub (or GitHub Gist / pastebin).
Set `DEFAULT_UPDATE_URL` in `UpdateManager.kt` to the raw URL of this file:
```kotlin
const val DEFAULT_UPDATE_URL =
    "https://raw.githubusercontent.com/aniketakal7/shubh-utsav/main/version_update.json"
```

---

## What the User Sees on Their Phone

1. **Update Announcement**:
   - The app immediately notifies the user on launch with a festive golden card.
2. **Download Progress Bar**:
   - Tap **"Download & Install Update"**. The app downloads the APK right inside the dialog.
3. **1-Tap Android Install**:
   - Android prompts: *"Do you want to install an update to this application?"*
   - Tap **"Install"**. The app seamlessly updates with all their saved data intact!
