# Shubh Utsav (शुभ उत्सव) — Festival & Puja Helper

A modern, clean, and practical Android app designed for Indian families, young couples, and first-time performers to celebrate festivals and perform pujas at home with confidence, clarity, and peace of mind.

Built with **Kotlin**, **Jetpack Compose (Material 3)**, and an offline-first architecture.

---

## 🌟 Key Highlights & Features

### 1. Daily Panchang & Accurate Timings
- **Bilingual Presentation**: Real-time Tithi, Paksha, Nakshatra, Yoga, Karana, and Hindu Month in simple Hindi and English.
- **Astronomical Precision**: Location-aware Sunrise, Sunset, Abhijit Muhurat, Rahu Kaal, and Brahma Muhurat.
- **City Selector**: Pre-configured database of 25+ major Indian cities (Delhi, Mumbai, Bengaluru, Kolkata, Chennai, Hyderabad, Pune, Ahmedabad, Jaipur, Lucknow, Varanasi, Ayodhya, Patna, Bhopal, Chandigarh, Surat, Indore, Haridwar, Ujjain, Kochi, Guwahati, etc.) with instant coordinates calculation.

### 2. Comprehensive 30+ Festival Repository
Detailed, authentic bilingual content for all major, regional, and national celebrations:
- **Diwali 5-Day Season**: Dhanteras (Dhanvantari & Yama Deepam), Choti Diwali (Naraka Chaturdashi & Abhyanga Snan), Diwali (Lakshmi-Ganesh Puja & Pradosh Muhurat), Govardhan Puja (Annakut), Bhai Dooj (Yama Dwitiya).
- **Navratri & Dussehra**: Sharad Navratri (9 days, Ghatasthapana, Kanya Pujan), Chaitra Navratri, Dussehra (Vijayadashami, Shami & Ayudha Puja).
- **Shiva Celebrations**: Maha Shivratri (Char Prahar Abhishek, Bilva leaves, Mahamrityunjaya).
- **Krishna & Vishnu Celebrations**: Krishna Janmashtami (Nishita Kaal, Bal Gopal Jhula, Makhan Mishri), Ram Navami (Midday 12:00 birth celebration).
- **Lord Ganesha**: Ganesh Chaturthi (Clay idol sthapana, 21 Durva blades, Modaks, eco-friendly visarjan).
- **Family & Sun Celebrations**: Raksha Bandhan (Bhadra-free muhurat, Raksha Sutra), Karva Chauth (Sargi, Katha, Moon sighting through sieve), Chhath Puja (Nahay Khay, Kharna, Sandhya & Usha Arghya), Makar Sankranti / Pongal.
- **Spring & Harvest Celebrations**: Holi & Holika Dahan (herbal colors, bonfire prashad, Gujiya), Ugadi / Gudi Padwa (Gudi hoisting, Bevu-Bella), Onam (Pookkalam flower art, Onasadya feast), Lohri, Baisakhi, Vishu.
- **Spiritual & Universal Celebrations**: Guru Purnima, Hanuman Jayanti, Mahavir Jayanti, Buddha Purnima, Eid-ul-Fitr, Eid-ul-Adha, Christmas, New Year.

### 3. Interactive Step-by-Step Puja Vidhi
- Detailed, practical instructions for every step (Sanctification, Sankalp, Kalash Sthapana, Aavahan, Offerings, Aarti, and Prasad).
- Checkboxes with **zero-loss persistence** (`PreferencesManager` saves your checked steps locally).
- Dynamic progress tracker (e.g. "5 of 7 steps complete") with celebratory completion badge.
- Practical modern household tips (e.g. fire-safe diya placement, cloth towels, alternative offerings).

### 4. Smart Shopping List (Samagri) & WhatsApp Share
- Detailed quantities for all essential items (diyas, pure ghee, roli, flowers, camphor, sweets).
- Mark items as "Bought" with persistent state.
- **"+ Add Custom Item"** dialog to add personal household needs.
- **One-Tap WhatsApp Share**: Formats an organized message clearly separating *Items to Buy* `[ ]` and *Already Bought* `[✓]`.

### 5. Beginner-Friendly "Explain Simply" (सरल शब्दों में समझें)
- Dedicated dialog on every festival explaining the deep spiritual symbolism in modern, engaging language for young couples, kids, and first-timers.

### 6. Smart Reminders & Notifications
- Android notification channel `shubh_utsav_reminders` with high priority.
- Individual toggles for Preparation alerts (1 day before), Shopping alerts, and Muhurat alerts (30 mins before).
- "Send Test Notification Now" button to verify notifications directly on your device.

### 7. Instant Language Switcher (EN | हिं)
- Global one-tap toggle on the top app bar across all screens for immediate bilingual switching.

### 8. Monetization & Premium
- Non-intrusive banner simulation on the Calendar screen.
- "Remove Ads" dialog offering a one-time lifetime upgrade to Shubh Utsav Premium.

---

## 📱 How to Run and Test Locally

### Prerequisites
- Android Studio Ladybug (2024.2+) or Hedgehog+
- JDK 17 (`JAVA_HOME` configured)
- Android SDK Platform 35 (Android 15)

### Running from Command Line
```powershell
# In the project root directory
.\gradlew.bat assembleDebug
```
The compiled APK will be generated at:
`app/build/outputs/apk/debug/app-debug.apk`

### Running on an Android Device or Emulator
1. Connect your Android phone via USB and enable **USB Debugging** (Settings > Developer Options > USB Debugging), or start an Android Studio Virtual Device (AVD).
2. Install the debug APK:
   ```powershell
   adb install -r app/build/outputs/apk/debug/app-debug.apk
   ```
3. Or open the project in Android Studio and click **Run (Shift + F10)**.

---

## 🚀 Google Play Store Publishing Guide

### Step 1: Suggested Store Listing Metadata

- **App Title**: `Shubh Utsav – Festival & Puja Helper` (35 characters)
- **Short Description**: `Simple festival dates, puja vidhi, shopping checklists, muhurat & reminders.` (78 characters)
- **Category**: Lifestyle / Culture
- **Content Rating**: Everyone (3+)
- **Tags/Keywords**:
  `panchang, festival calendar, puja vidhi, diwali puja, shivratri, ganesh chaturthi, hindu calendar, muhurat, vrat katha, shopping list, indian festivals, chandra darshan`

### Step 2: Full Description Copy for Google Play Store

```text
Welcome to Shubh Utsav (शुभ उत्सव) — your modern, clean, and practical Festival & Puja companion.

Whether you are performing Diwali Lakshmi Puja for the first time, preparing for Navratri, celebrating Ganesh Chaturthi, or observing Karva Chauth, Shubh Utsav guides you step-by-step with zero confusion and complete peace of mind.

WHY CHOOSE SHUBH UTSAV?
• No Clutter, Just Clarity: A peaceful, warm design with easy-to-read cards and large fonts.
• Real-time Daily Panchang: Accurate Tithi, Nakshatra, Yoga, Karana, Sunrise, Sunset, Rahu Kaal, and Abhijit Muhurat tailored to your city.
• Comprehensive Calendar: 30+ Indian festivals covering 2025, 2026, and 2027. Filter by month or category.
• Interactive Step-by-Step Puja Vidhi: Follow clear instructions and tick off completed steps. Your progress is saved automatically!
• Complete Shopping (Samagri) Lists: Exact quantities, checkable bought items, and 1-tap WhatsApp sharing with family.
• "Explain Simply" for Beginners: Understand the deep spiritual logic behind each ritual in simple modern words.
• Sacred Mantras with Easy Meaning: Sanskrit text, phonetics, and Hindi/English explanations.
• Common Mistakes to Avoid: Practical safety and ritual tips for modern homes.
• Smart Notifications: Gentle reminders 1 day before and 30 minutes before auspicious muhurats.
• Bilingual & 100% Offline: Instant one-tap switch between Simple Hindi and English. Works fully offline without an active internet connection.

Celebrate your heritage with confidence and joy. Download Shubh Utsav today!
```

### Step 3: Required Graphic Assets for Google Play Console

1. **App Icon**: 512 × 512 px, 32-bit PNG with alpha, max 1024 KB.
   *(Design: Traditional golden diya on royal maroon background with soft saffron aura)*.
2. **Feature Graphic**: 1024 × 500 px, JPG or 24-bit PNG (no alpha).
   *(Design: Elegant Indian motifs, "Shubh Utsav", festival elements: diya, kalash, modak, flowers)*.
3. **Screenshots**: Minimum 4 phone screenshots (1080 × 2400 px or 16:9 ratio):
   - Screenshot 1: *Daily Panchang & Location Timings*
   - Screenshot 2: *30+ Major Festivals Calendar with Search & Filters*
   - Screenshot 3: *Interactive Step-by-Step Puja Vidhi with Checklist*
   - Screenshot 4: *Categorized Shopping List & 1-Tap WhatsApp Share*
   - Screenshot 5: *Bilingual English & Simple Hindi One-Tap Toggle*

### Step 4: Generating a Release Android App Bundle (.aab)

1. Create a Keystore (if you don't already have one):
   ```powershell
   keytool -genkey -v -keystore shubh-utsav-release.jks -alias shubhutsav -keyalg RSA -keysize 2048 -validity 10000
   ```
2. Build the signed bundle using Gradle:
   ```powershell
   .\gradlew.bat bundleRelease
   ```
3. The release bundle will be located at:
   `app/build/outputs/bundle/release/app-release.aab`
4. Upload `app-release.aab` to Google Play Console under **Internal Testing** or **Production Track**.

### Step 5: Data Safety & Privacy Disclosures
- **Location**: Used strictly on-device to calculate astronomical Sunrise, Sunset, and Rahu Kaal. No location data is sent to any external server.
- **Storage / Checklists**: Stored locally on the device using private SharedPreferences.
- **Analytics & Ads**: Standard Google Mobile Ads SDK (AdMob) for non-personalized or personalized ads depending on user consent.

---

## 🏛️ Project Architecture & File Organization

```
com.shubhutsav.app/
├── MainActivity.kt                     # Application entry point, NavHost, and Theme wrapper
├── data/
│   ├── CityData.kt                     # 25+ major Indian cities coordinates & repository
│   ├── FestivalModel.kt                # Data models for Festivals, Steps, Supplies, Mantras, Muhurats
│   ├── FestivalRepository.kt           # Authentic, rich 30+ festival repository in Hindi & English
│   ├── PanchangCalculator.kt           # Astronomical formulas for Tithi, Nakshatra, Yoga, Sunrise, Rahu Kaal
│   └── PreferencesManager.kt           # Local persistent storage for settings, checklists, and shopping status
├── notifications/
│   └── NotificationHelper.kt           # Android notification channel & smart reminder alerts
└── ui/
    ├── components/
    │   ├── AdBannerMockup.kt           # AdMob banner simulator & Premium upgrade dialog
    │   ├── AppTopBar.kt                # Universal top bar with instant EN/हिं toggle and city badge
    │   ├── CityPickerDialog.kt         # Searchable dialog to pick or change city
    │   ├── ExplainSimplySheet.kt       # Beginner-friendly spiritual explanation dialog
    │   ├── FestivalCard.kt             # Responsive card components for home and calendar
    │   └── PanchangCard.kt             # Royal maroon card with real-time location-aware timings
    ├── screens/
    │   ├── CalendarScreen.kt           # Multi-year festival calendar with live search & filters
    │   ├── FestivalDetailScreen.kt     # 4-tab detailed festival page with interactive checklist
    │   ├── HomeScreen.kt               # Today's panchang, upcoming celebrations, and quick actions
    │   ├── OnboardingScreen.kt         # First-time setup (language, city, ritual style)
    │   ├── SettingsScreen.kt           # Preferences, dark mode, notifications, and premium controls
    │   └── ShoppingScreen.kt           # Categorized shopping list, custom items, and WhatsApp share
    └── theme/
        ├── Color.kt                    # Curated traditional palette (Royal Maroon, Kesariya, Vedic Gold)
        └── Theme.kt                    # Material 3 Light & Dark mode themes
```

---

## 👨‍💻 Developer & Repository

- **GitHub Repository**: [https://github.com/aniketakal7/shubh-utsav](https://github.com/aniketakal7/shubh-utsav)
- **Author / Maintainer**: [@aniketakal7](https://github.com/aniketakal7)
- **License**: Open Source

