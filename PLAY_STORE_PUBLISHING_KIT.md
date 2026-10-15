# Shubh Utsav (शुभ उत्सव) — Google Play Store Publishing Kit 🚀

This kit contains all the required assets, metadata, descriptions, privacy policy, and commands to publish **Shubh Utsav** to the **Google Play Console**.

---

## 1. Google Play Console Listing Metadata

### App Title
`Shubh Utsav: Panchang & Puja` *(30 characters)*

### Short Description
`Daily Panchang, 30+ Festival Pujas, Japa Mala, Aarti, Choghadiya & Wishes.` *(74 characters)*

### Category
**Lifestyle** (Secondary: **Books & Reference** / **Culture**)

### Tags / Keywords
`panchang`, `hindu calendar 2026`, `puja vidhi`, `japa mala`, `aarti sangrah`, `hanuman chalisa`, `choghadiya`, `vrat katha`, `diwali puja`, `shubh muhurat`, `festive greetings`

---

## 2. Full Store Description (English)

```text
Welcome to Shubh Utsav (शुभ उत्सव) — your modern, clean, and authentic Vedic Hindu Calendar, Puja Vidhi, and Daily Sadhana companion.

Designed with immense love and cultural reverence by Aniket Akal, Shubh Utsav empowers Indian families, young couples, and first-time performers to celebrate all festivals with complete clarity, confidence, and peace of mind.

🌟 KEY HIGHLIGHTS & SACRED FEATURES:

1. 📅 ACCURATE DAILY PANCHANG & MUHURAT
• Real-time Tithi, Paksha, Nakshatra, Yoga, Karana, and Vikram Samvat.
• Precise location-aware Sunrise, Sunset, Abhijit Muhurat, Rahu Kaal, and Brahma Muhurat.
• Built-in database of 25+ major Indian cities (Delhi, Mumbai, Bengaluru, Pune, Lucknow, Varanasi, etc.) plus automatic GPS detection for towns and villages.

2. 📿 DIGITAL JAPA MALA & MANTRA COUNTER (जाप माला)
• Authentic 108 Rudraksha bead counter with gentle haptic vibration feedback.
• 8 sacred Vedic mantras: Om Namah Shivaya, Gayatri Mantra, Maha Mrityunjaya, Hare Krishna, Ganesh Mantra, Lakshmi Mantra, and Ram Taraka Mantra.
• Track your daily chanting sadhana streaks and completed malas.

3. 🎵 AARTI & CHALISA PLAYER WITH SYNC LYRICS (आरती व चालीसा)
• Sing along with auto-advancing, synchronized karaoke-style lyrics in Hindi, Marathi, and English.
• Rich collection including Jai Ganesh Deva, Sukhkarta Dukhharta, Hanuman Chalisa, Om Jai Jagdish Hare, Lakshmi Aarti, and Shiv Omkara.
• Interactive Temple Room: Tap to ring the sacred bronze Bell (घंटी) and blow the deep resonant Conch (शंखनाद)!

4. ⏱️ DAILY CHOGHADIYA & DISHA SHOOL (चौघड़िया मुहूर्त)
• Complete 16-period Day & Night Choghadiya (Amrit, Shubh, Labh, Char, Rog, Kaal, Udveg).
• Live "Active Now" indicator telling you the exact quality of the current moment.
• Today's Disha Shool travel rules and traditional Vedic remedies.

5. 📖 VRAT KATHA & PARANA TIMERS (व्रत कथा एवं पारण)
• Detailed guidance for Ekadashi, Pradosh Vrat, Sankashti Chaturthi, Karva Chauth, Solah Somwar, and Brihaspativar.
• Complete fasting rules: What to eat (Phalahar) and what is strictly prohibited.
• Exact Parana Muhurat timing to break your fast with divine blessings.
• High-contrast reading mode with A- / A+ adjustable font sizes for elders.

6. 🎨 FESTIVE WISHES & WHATSAPP STATUS MAKER (शुभकामना कार्ड मेकर)
• Create personalized festive greeting cards with your family name.
• Traditional festive themes (Royal Maroon, Kesariya Gold, Midnight Divine).
• 1-Tap share high-resolution cards directly to WhatsApp Status and Instagram!

7. 📱 GLANCEABLE HOME SCREEN WIDGET
• Check today's Tithi, Nakshatra, Sunrise, and Rahu Kaal directly on your phone's home screen without opening the app!

8. 🛒 SMART SAMAGRI SHOPPING LISTS
• Complete checklist of puja items for every festival.
• Check off items as you buy and share formatted shopping lists on WhatsApp with one tap.

100% Offline-First • Zero Distractions • Made with ❤️ by Aniket Akal
```

---

## 3. Required Graphic Assets Specifications

| Asset | Size | Format | Note |
|---|---|---|---|
| **App Icon** | 512 x 512 px | 32-bit PNG (no alpha) | Festive Diya & Kalash with royal maroon background |
| **Feature Graphic** | 1024 x 500 px | JPG or 24-bit PNG | Gold Vedic border with "Shubh Utsav: Panchang & Puja Companion" |
| **Phone Screenshots** | Min 1080 x 1920 px (At least 4, max 8) | PNG/JPG | Capture Home, Japa Mala, Aarti Player, Choghadiya, Vrat Katha, Greetings |

---

## 4. Google Play Compliant Privacy Policy

You can host this free on GitHub Pages (e.g. `https://aniketakal7.github.io/shubh-utsav/privacy.html`):

```markdown
# Privacy Policy for Shubh Utsav

Last updated: October 15, 2026

Shubh Utsav ("we", "our", or "the app"), developed by Aniket Akal, respects your privacy.

1. Information We Collect:
- Location Data: We access coarse or fine location solely when requested by the user to calculate accurate local sunrise, sunset, and panchang timings. Location is processed locally on your device and is never stored on or shared with external servers.
- Offline Storage: Your puja checklists, japa mala counts, and saved cities are stored entirely locally on your device using Android SharedPreferences.

2. Third-Party Services:
The app may utilize Google Firebase (Analytics and Crashlytics) to diagnose crashes and ensure app stability. No personally identifiable financial or sensitive data is collected.

3. Changes:
We may update this policy periodically. Any changes will be posted on this page.

Contact: aniketakal@gmail.com
```

---

## 5. Building the Production Release Bundle (.aab)

Google Play Store requires an **Android App Bundle (`.aab`)** for new app submissions.

### Step 1: Generate Release Keystore (if you don't have one)
In terminal / PowerShell:
```powershell
keytool -genkey -v -keystore shubh-utsav-release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias shubhutsav
```

### Step 2: Build the Signed Production Bundle
```powershell
.\gradlew.bat bundleRelease
```
The generated bundle will be located at:
`app/build/outputs/bundle/release/app-release.aab`

Upload `app-release.aab` directly to Google Play Console under **Production ➔ Releases ➔ Create new release**.
