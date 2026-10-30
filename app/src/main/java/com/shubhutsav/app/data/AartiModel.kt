package com.shubhutsav.app.data

data class AartiLyricLine(
    val hindi: String,
    val marathi: String,
    val english: String
)

data class SacredAarti(
    val id: String,
    val titleEn: String,
    val titleHi: String,
    val titleMr: String,
    val deity: String,
    val category: String, // "Aarti", "Chalisa", "Stuti"
    val durationApproxSeconds: Int,
    val lines: List<AartiLyricLine>
)

object AartiRepository {

    val aartis: List<SacredAarti> = listOf(
        SacredAarti(
            id = "ganesh_aarti",
            titleEn = "Jai Ganesh Deva",
            titleHi = "जय गणेश देवा (श्री गणेश आरती)",
            titleMr = "जय गणेश देवा",
            deity = "Lord Ganesha",
            category = "Aarti",
            durationApproxSeconds = 180,
            lines = listOf(
                AartiLyricLine("जय गणेश, जय गणेश, जय गणेश देवा ।", "जय गणेश, जय गणेश, जय गणेश देवा ।", "Jai Ganesh, Jai Ganesh, Jai Ganesh Deva |"),
                AartiLyricLine("माता जाकी पार्वती, पिता महादेवा ॥", "माता जाकी पार्वती, पिता महादेवा ॥", "Mata Jaki Parvati, Pita Mahadeva ||"),
                AartiLyricLine("एक दन्त दयावन्त, चार भुजाधारी ।", "एक दन्त दयावन्त, चार भुजाधारी ।", "Ek danta dayavanta, char bhujadhari |"),
                AartiLyricLine("माथे पर तिलक सोहे, मूसे की सवारी ॥", "माथे पर तिलक सोहे, मूसे की सवारी ॥", "Mathe par tilak sohe, moose ki sawari ||"),
                AartiLyricLine("पान चढ़े फूल चढ़े, और चढ़े मेवा ।", "पान चढ़े फूल चढ़े, और चढ़े मेवा ।", "Paan charhe phool charhe, aur charhe mewa |"),
                AartiLyricLine("लड्डुअन का भोग लगे, सन्त करें सेवा ॥", "लड्डुअन का भोग लगे, सन्त करें सेवा ॥", "Ladduan ka bhog lage, sant karein seva ||"),
                AartiLyricLine("अन्धन को आँख देत, कोढ़िन को काया ।", "अन्धन को आँख देत, कोढ़िन को काया ।", "Andhan ko aankh det, korhin ko kaya |"),
                AartiLyricLine("बाँझन को पुत्र देत, निर्धन को माया ॥", "बाँझन को पुत्र देत, निर्धन को माया ॥", "Banjhan ko putra det, nirdhan ko maya ||"),
                AartiLyricLine("सूर श्याम शरण आए, सफल कीजे सेवा ।", "सूर श्याम शरण आए, सफल कीजे सेवा ।", "Soor shyam sharan aaye, safal kije seva |"),
                AartiLyricLine("जय गणेश, जय गणेश, जय गणेश देवा ॥", "जय गणेश, जय गणेश, जय गणेश देवा ॥", "Jai Ganesh, Jai Ganesh, Jai Ganesh Deva ||")
            )
        ),
        SacredAarti(
            id = "sukhkarta_marathi",
            titleEn = "Sukhkarta Dukhharta",
            titleHi = "सुखकर्ता दुखहर्ता (मराठी गणेश आरती)",
            titleMr = "सुखकर्ता दुखहर्ता (श्री समर्थ रामदास कृत)",
            deity = "Lord Ganesha",
            category = "Aarti",
            durationApproxSeconds = 210,
            lines = listOf(
                AartiLyricLine("सुखकर्ता दुखहर्ता वार्ता विघ्नाची ।", "सुखकर्ता दुखहर्ता वार्ता विघ्नाची ।", "Sukhkarta Dukhharta varta vighnachi |"),
                AartiLyricLine("नुरवी पुरवी प्रेम कृपा जयाची ॥", "नुरवी पुरवी प्रेम कृपा जयाची ॥", "Nurvi purvi prem kripa jayachi ||"),
                AartiLyricLine("सर्वांगी सुंदर उटी शेंदुराची ।", "सर्वांगी सुंदर उटी शेंदुराची ।", "Sarvangi sundar uti shendurachi |"),
                AartiLyricLine("कंठी झळके माळ मुक्ताफळांची ॥", "कंठी झळके माळ मुक्ताफळांची ॥", "Kanthi jhalke maal muktaphalanchi ||"),
                AartiLyricLine("जय देव जय देव जय मंगलमूर्ती ।", "जय देव जय देव जय मंगलमूर्ती ।", "Jai Dev Jai Dev Jai Mangalamurti |"),
                AartiLyricLine("दर्शनमात्रे मनकामना पुरती ॥ जय देव जय देव ॥", "दर्शनमात्रे मनकामना पुरती ॥ जय देव जय देव ॥", "Darshan matre manakamana purti || Jai Dev Jai Dev ||"),
                AartiLyricLine("रत्नखचित फरा तुज गौरीकुमरा ।", "रत्नखचित फरा तुज गौरीकुमरा ।", "Ratnakhachit phara tuj Gaurikumara |"),
                AartiLyricLine("चंदनाची उटी कुंकुमकेशरा ॥", "चंदनाची उटी कुंकुमकेशरा ॥", "Chandanachi uti kumkum keshara ||"),
                AartiLyricLine("हिरेजडित मुकुट शोभतो बरा ।", "हिरेजडित मुकुट शोभतो बरा ।", "Hirejadit mukut shobhato bara |"),
                AartiLyricLine("रुणझुणती नूपुरे चरणी घागरिया ॥ जय देव जय देव ॥", "रुणझुणती नूपुरे चरणी घागरिया ॥ जय देव जय देव ॥", "Runjhunti nupure charani ghagariya || Jai Dev Jai Dev ||"),
                AartiLyricLine("लंबोदर पीतांबर फणिवरबंधना ।", "लंबोदर पीतांबर फणिवरबंधना ।", "Lambodar pitambar phanivarbandhana |"),
                AartiLyricLine("सरळ सोंड वक्रतुंड त्रिनयना ॥", "सरळ सोंड वक्रतुंड त्रिनयना ॥", "Saral sond vakratunda trinayana ||"),
                AartiLyricLine("दास रामाचा वाट पाहे सदना ।", "दास रामाचा वाट पाहे सदना ।", "Das Ramacha vaat pahe sadana |"),
                AartiLyricLine("संकटी पावावे निर्वाणी रक्षावे सुरवरवंदना ॥", "संकटी पावावे निर्वाणी रक्षावे सुरवरवंदना ॥", "Sankati pavave nirvani rakshave survarvandana ||")
            )
        ),
        SacredAarti(
            id = "om_jai_jagdish",
            titleEn = "Om Jai Jagdish Hare",
            titleHi = "ॐ जय जगदीश हरे (महा आरती)",
            titleMr = "ॐ जय जगदीश हरे",
            deity = "Lord Vishnu",
            category = "Aarti",
            durationApproxSeconds = 240,
            lines = listOf(
                AartiLyricLine("ॐ जय जगदीश हरे, स्वामी जय जगदीश हरे ।", "ॐ जय जगदीश हरे, स्वामी जय जगदीश हरे ।", "Om Jai Jagdish Hare, Swami Jai Jagdish Hare |"),
                AartiLyricLine("भक्त जनों के संकट, क्षण में दूर करे ॥", "भक्त जनों के संकट, क्षण में दूर करे ॥", "Bhakt jano ke sankat, kshan mein door kare ||"),
                AartiLyricLine("जो ध्यावे फल पावे, दुःख बिनसे मन का ।", "जो ध्यावे फल पावे, दुःख बिनसे मन का ।", "Jo dhyave phal pave, dukh binase man ka |"),
                AartiLyricLine("सुख सम्पति घर आवे, कष्ट मिटे तन का ॥", "सुख सम्पति घर आवे, कष्ट मिटे तन का ॥", "Sukh sampati ghar aave, kasht mite tan ka ||"),
                AartiLyricLine("मात-पिता तुम मेरे, शरण गहूँ किसकी ।", "मात-पिता तुम मेरे, शरण गहूँ किसकी ।", "Maat-pita tum mere, sharan gahoon kiski |"),
                AartiLyricLine("तुम बिन और न दूजा, आस करूँ जिसकी ॥", "तुम बिन और न दूजा, आस करूँ जिसकी ॥", "Tum bin aur na dooja, aas karoon jiski ||"),
                AartiLyricLine("तुम पूरण परमात्मा, तुम अन्तर्यामी ।", "तुम पूरण परमात्मा, तुम अन्तर्यामी ।", "Tum pooran paramatma, tum antaryami |"),
                AartiLyricLine("पारब्रह्म परमेश्वर, तुम सब के स्वामी ॥", "पारब्रह्म परमेश्वर, तुम सब के स्वामी ॥", "Paarbrahm parmeshwar, tum sab ke swami ||"),
                AartiLyricLine("तुम करुणा के सागर, तुम पालनकर्ता ।", "तुम करुणा के सागर, तुम पालनकर्ता ।", "Tum karuna ke sagar, tum palankarta |"),
                AartiLyricLine("मैं मूरख खल कामी, कृपा करो भर्ता ॥", "मैं मूरख खल कामी, कृपा करो भर्ता ॥", "Main moorakh khal kaami, kripa karo bharta ||"),
                AartiLyricLine("ॐ जय जगदीश हरे, स्वामी जय जगदीश हरे ॥", "ॐ जय जगदीश हरे, स्वामी जय जगदीश हरे ॥", "Om Jai Jagdish Hare, Swami Jai Jagdish Hare ||")
            )
        ),
        SacredAarti(
            id = "hanuman_chalisa",
            titleEn = "Shri Hanuman Chalisa",
            titleHi = "श्री हनुमान चालीसा (गोस्वामी तुलसीदास कृत)",
            titleMr = "श्री हनुमान चालीसा",
            deity = "Lord Hanuman",
            category = "Chalisa",
            durationApproxSeconds = 300,
            lines = listOf(
                AartiLyricLine("श्रीगुरु चरन सरोज रज निज मनु मुकुरु सुधारि ।", "श्रीगुरु चरन सरोज रज निज मनु मुकुरु सुधारि ।", "Shri Guru charan saroj raj nij manu mukuru sudhari |"),
                AartiLyricLine("बरनउँ रघुबर बिमल जसु जो दायकु फल चारि ॥", "बरनउँ रघुबर बिमल जसु जो दायकु फल चारि ॥", "Baranau Raghuvar bimal jasu jo dayaku phal chari ||"),
                AartiLyricLine("बुद्धिहीन तनु जानिके सुमिरौ पवन-कुमार ।", "बुद्धिहीन तनु जानिके सुमिरौ पवन-कुमार ।", "Buddhiheen tanu janike sumirau pawan-kumar |"),
                AartiLyricLine("बल बुद्धि बिद्या देहु मोहिं हरहु कलेस बिकार ॥", "बल बुद्धि बिद्या देहु मोहिं हरहु कलेस बिकार ॥", "Bal buddhi bidya dehu mohi harahu kalesh bikar ||"),
                AartiLyricLine("जय हनुमान ज्ञान गुन सागर । जय कपीस तिहुँ लोक उजागर ॥", "जय हनुमान ज्ञान गुन सागर । जय कपीस तिहुँ लोक उजागर ॥", "Jai Hanuman gyan gun sagar | Jai Kapis tihun lok ujagar ||"),
                AartiLyricLine("राम दूत अतुलित बल धामा । अंजनि-पुत्र पवनसुत नामा ॥", "राम दूत अतुलित बल धामा । अंजनि-पुत्र पवनसुत नामा ॥", "Ram doot atulit bal dhama | Anjani-putra pawansut nama ||"),
                AartiLyricLine("महाबीर बिक्रम बजरंगी । कुमति निवार सुमति के संगी ॥", "महाबीर बिक्रम बजरंगी । कुमति निवार सुमति के संगी ॥", "Mahabir bikram bajarangi | Kumati nivar sumati ke sangi ||"),
                AartiLyricLine("कंचन बरन बिराज सुबेसा । कानन कुंडल कुंचित केसा ॥", "कंचन बरन बिराज सुबेसा । कानन कुंडल कुंचित केसा ॥", "Kanchan baran biraj subesa | Kanan kundal kunchit kesa ||"),
                AartiLyricLine("हाथ बज्र औ ध्वजा बिराजै । काँधे मूँज जनेऊ साजै ॥", "हाथ बज्र औ ध्वजा बिराजै । काँधे मूँज जनेऊ साजै ॥", "Hath bajra au dhwaja biraje | Kandhe moonj janeu saje ||"),
                AartiLyricLine("भूत पिसाच निकट नहिं आवै । महाबीर जब नाम सुनावै ॥", "भूत पिसाच निकट नहिं आवै । महाबीर जब नाम सुनावै ॥", "Bhoot pisach nikat nahin aave | Mahabir jab naam sunave ||"),
                AartiLyricLine("नासै रोग हरै सब पीरा । जपत निरंतर हनुमत बीरा ॥", "नासै रोग हरै सब पीरा । जपत निरंतर हनुमत बीरा ॥", "Nase rog hare sab peera | Japat nirantar Hanumat beera ||"),
                AartiLyricLine("संकट तें हनुमान छुड़ावै । मन क्रम बचन ध्यान जो लावै ॥", "संकट तें हनुमान छुड़ावै । मन क्रम बचन ध्यान जो लावै ॥", "Sankat te Hanuman chhudave | Man kram bachan dhyan jo lave ||"),
                AartiLyricLine("पवन तनय संकट हरन मंगल मूरति रूप ।", "पवन तनय संकट हरन मंगल मूरति रूप ।", "Pawan tanay sankat haran mangal murati roop |"),
                AartiLyricLine("राम लखन सीता सहित हृदय बसहु सुर भूप ॥", "राम लखन सीता सहित हृदय बसहु सुर भूप ॥", "Ram Lakhan Sita sahit hriday basahu sur bhoop ||")
            )
        ),
        SacredAarti(
            id = "lakshmi_aarti",
            titleEn = "Om Jai Lakshmi Mata",
            titleHi = "ॐ जय लक्ष्मी माता (दीपावली आरती)",
            titleMr = "ॐ जय लक्ष्मी माता",
            deity = "Maa Lakshmi",
            category = "Aarti",
            durationApproxSeconds = 200,
            lines = listOf(
                AartiLyricLine("ॐ जय लक्ष्मी माता, मैया जय लक्ष्मी माता ।", "ॐ जय लक्ष्मी माता, मैया जय लक्ष्मी माता ।", "Om Jai Lakshmi Mata, Maiya Jai Lakshmi Mata |"),
                AartiLyricLine("तुमको निसदिन सेवत, हर विष्णु विधाता ॥", "तुमको निसदिन सेवत, हर विष्णु विधाता ॥", "Tumko nisdin sevat, Har Vishnu Vidhata ||"),
                AartiLyricLine("उमा रमा ब्रह्माणी, तुम ही जग-माता ।", "उमा रमा ब्रह्माणी, तुम ही जग-माता ।", "Uma Rama Brahmani, tum hi jag-mata |"),
                AartiLyricLine("सूर्य-चन्द्रमा ध्यावत, नारद ऋषि गाता ॥", "सूर्य-चन्द्रमा ध्यावत, नारद ऋषि गाता ॥", "Surya-chandrama dhyavat, Narad rishi gata ||"),
                AartiLyricLine("जिस घर में तुम रहतीं, सब सद्गुण आता ।", "जिस घर में तुम रहतीं, सब सद्गुण आता ।", "Jis ghar mein tum rahteen, sab sadguna aata |"),
                AartiLyricLine("सब संभव हो जाता, मन नहीं घबराता ॥", "सब संभव हो जाता, मन नहीं घबराता ॥", "Sab sambhav ho jaata, man nahin ghabrata ||"),
                AartiLyricLine("तुम बिन यज्ञ न होते, वस्त्र न कोई पाता ।", "तुम बिन यज्ञ न होते, वस्त्र न कोई पाता ।", "Tum bin yagya na hote, vastra na koi pata |"),
                AartiLyricLine("खान-पान का वैभव, सब तुमसे आता ॥", "खान-पान का वैभव, सब तुमसे आता ॥", "Khaan-paan ka vaibhav, sab tumse aata ||"),
                AartiLyricLine("शुभ-गुण मंदिर सुंदर, क्षीरोदधि-जाता ।", "शुभ-गुण मंदिर सुंदर, क्षीरोदधि-जाता ।", "Shubh-gun mandir sundar, kshirodadhi-jaata |"),
                AartiLyricLine("रत्न चतुर्दश तुम बिन, कोई नहीं पाता ॥", "रत्न चतुर्दश तुम बिन, कोई नहीं पाता ॥", "Ratna chaturdash tum bin, koi nahin pata ||")
            )
        ),
        SacredAarti(
            id = "shiv_aarti",
            titleEn = "Om Jai Shiv Omkara",
            titleHi = "ॐ जय शिव ओंकारा (महाशिवरात्रि आरती)",
            titleMr = "ॐ जय शिव ओंकारा",
            deity = "Lord Shiva",
            category = "Aarti",
            durationApproxSeconds = 220,
            lines = listOf(
                AartiLyricLine("ॐ जय शिव ओंकारा, स्वामी जय शिव ओंकारा ।", "ॐ जय शिव ओंकारा, स्वामी जय शिव ओंकारा ।", "Om Jai Shiv Omkara, Swami Jai Shiv Omkara |"),
                AartiLyricLine("ब्रह्मा विष्णु सदाशिव अर्द्धांगी धारा ॥", "ब्रह्मा विष्णु सदाशिव अर्द्धांगी धारा ॥", "Brahma Vishnu Sadashiv ardhangi dhara ||"),
                AartiLyricLine("एकानन चतुरानन पंचानन राजे ।", "एकानन चतुरानन पंचानन राजे ।", "Ekanan chaturanan panchanan raje |"),
                AartiLyricLine("हंसासन गरुड़ासन वृषवाहन साजे ॥", "हंसासन गरुड़ासन वृषवाहन साजे ॥", "Hansasana garudasana vrishavahana saje ||"),
                AartiLyricLine("दो भुज चार चतुर्भुज दस भुज अति सोहे ।", "दो भुज चार चतुर्भुज दस भुज अति सोहे ।", "Do bhuj char chaturbhuj das bhuj ati sohe |"),
                AartiLyricLine("तीनों रूप निरखता त्रिभुवन जन मोहे ॥", "तीनों रूप निरखता त्रिभुवन जन मोहे ॥", "Teeno roop nirakhata tribhuvan jan mohe ||"),
                AartiLyricLine("अक्षमाला बनमाला रुण्डमाला धारी ।", "अक्षमाला बनमाला रुण्डमाला धारी ।", "Akshamala banamala rundamala dhari |"),
                AartiLyricLine("त्रिपुरारी कंसारी कर माला धारी ॥", "त्रिपुरारी कंसारी कर माला धारी ॥", "Tripurari kansari kar mala dhari ||"),
                AartiLyricLine("कर्पूरगौरं करुणावतारं संसारसारम् ।", "कर्पूरगौरं करुणावतारं संसारसारम् ।", "Karpura-gauram karunavataram sansarasaram |"),
                AartiLyricLine("सदावसन्तं हृदयारविन्दे भवं भवानीसहितं नमामि ॥", "सदावसन्तं हृदयारविन्दे भवं भवानीसहितं नमामि ॥", "Sada-vasantam hridayaravinde bhavam bhavani-sahitam namami ||")
            )
        ),
        SacredAarti(
            id = "durga_aarti",
            titleEn = "Jai Ambe Gauri",
            titleHi = "जय अम्बे गौरी (श्री दुर्गा आरती)",
            titleMr = "जय अंबे गौरी (दुर्गा माता आरती)",
            deity = "Maa Durga",
            category = "Aarti",
            durationApproxSeconds = 240,
            lines = listOf(
                AartiLyricLine("जय अम्बे गौरी, मैया जय श्यामा गौरी ।", "जय अम्बे गौरी, मैया जय श्यामा गौरी ।", "Jai Ambe Gauri, Maiya Jai Shyama Gauri |"),
                AartiLyricLine("तुमको निसदिन ध्यावत, हरि ब्रह्मा शिवरी ॥", "तुमको निसदिन ध्यावत, हरि ब्रह्मा शिवरी ॥", "Tumko nisdin dhyavat, Hari Brahma Shivri ||"),
                AartiLyricLine("मांग सिंदूर विराजत, टीको मृगमद को ।", "मांग सिंदूर विराजत, टीको मृगमद को ।", "Maang sindoor virajat, teeko mrigmad ko |"),
                AartiLyricLine("उज्ज्वल से दोउ नैना, चंद्रवदन नीको ॥", "उज्ज्वल से दोउ नैना, चंद्रवदन नीको ॥", "Ujjwal se dou naina, chandravadan neeko ||"),
                AartiLyricLine("कनक समान कलेवर, रक्तांबर राजै ।", "कनक समान कलेवर, रक्तांबर राजै ।", "Kanak saman kalevar, raktambar raje |"),
                AartiLyricLine("रक्तपुष्प गल माला, कंठन पर साजै ॥", "रक्तपुष्प गल माला, कंठन पर साजै ॥", "Raktapushp gal mala, kanthan par saaje ||"),
                AartiLyricLine("केहरि वाहन राजत, खड्ग खप्पर धारी ।", "केहरि वाहन राजत, खड्ग खप्पर धारी ।", "Kehari vahan rajat, khadag khappar dhari |"),
                AartiLyricLine("सुर-नर-मुनिजन सेवत, तिनके दुःखहारी ॥", "सुर-नर-मुनिजन सेवत, तिनके दुःखहारी ॥", "Sur-nar-munijan sevat, tinke dukh-hari ||"),
                AartiLyricLine("चौंसठ योगिनि गावत, नृत्य करत भैरों ।", "चौंसठ योगिनि गावत, नृत्य करत भैरों ।", "Chaunsath yogini gavat, nritya karat bhairon |"),
                AartiLyricLine("बाजत ताल मृदंगा, अरु बाजत डमरू ॥", "बाजत ताल मृदंगा, अरु बाजत डमरू ॥", "Bajat taal mridanga, aru bajat damroo ||"),
                AartiLyricLine("तुम ही जग की माता, तुम ही हो भरता ।", "तुम ही जग की माता, तुम ही हो भरता ।", "Tum hi jag ki mata, tum hi ho bharta |"),
                AartiLyricLine("भक्तन की दुःख हरता, सुख सम्पति करता ॥", "भक्तन की दुःख हरता, सुख सम्पति करता ॥", "Bhaktan ki dukh harta, sukh sampati karta ||")
            )
        ),
        SacredAarti(
            id = "shri_ram_stuti",
            titleEn = "Shri Ramchandra Kripalu Bhajuman",
            titleHi = "श्री रामचंद्र कृपालु भजु मन (राम स्तुति)",
            titleMr = "श्री रामचंद्र कृपालु भजु मन",
            deity = "Lord Rama",
            category = "Stuti",
            durationApproxSeconds = 240,
            lines = listOf(
                AartiLyricLine("श्रीरामचन्द्र कृपालु भजु मन हरण भवभय दारुणम् ।", "श्रीरामचन्द्र कृपालु भजु मन हरण भवभय दारुणम् ।", "Shri Ramchandra kripalu bhaju man haran bhavabhay daarunam |"),
                AartiLyricLine("नवकंज लोचन, कंज मुख, कर कंज, पद कंजारुणम् ॥", "नवकंज लोचन, कंज मुख, कर कंज, पद कंजारुणम् ॥", "Navakanj lochan, kanj mukh, kar kanj, pad kanjaarunam ||"),
                AartiLyricLine("कंदर्प अगणित अमित छबि, नवनील नीरद सुन्दरम् ।", "कंदर्प अगणित अमित छबि, नवनील नीरद सुन्दरम् ।", "Kandarp aganit amit chhabi, navaneel neerad sundaram |"),
                AartiLyricLine("पट पीत मानहु तड़ित रुचि शुचि नौमि जनक सुतावरम् ॥", "पट पीत मानहु तड़ित रुचि शुचि नौमि जनक सुतावरम् ॥", "Pat peet manahu tadit ruchi shuchi naumi Janak sutavaram ||"),
                AartiLyricLine("भजु दीनबंधु दिनेश दानव दैत्य वंश निकंदनम् ।", "भजु दीनबंधु दिनेश दानव दैत्य वंश निकंदनम् ।", "Bhaju deenabandhu dinesh danav daitya vansh nikandanam |"),
                AartiLyricLine("रघुनंद आनंदकंद कोशलचंद दशरथ नंदनम् ॥", "रघुनंद आनंदकंद कोशलचंद दशरथ नंदनम् ॥", "Raghunand aanandakand Koshalachand Dasharatha nandanam ||"),
                AartiLyricLine("सिर मुकुट कुंडल तिलक चारु उदारु अंग विभूषणम् ।", "सिर मुकुट कुंडल तिलक चारु उदारु अंग विभूषणम् ।", "Sir mukut kundal tilak chaaru udaaru ang vibhooshanam |"),
                AartiLyricLine("आजानुभुज शर चाप धर, संग्राम जित खर-दूषणम् ॥", "आजानुभुज शर चाप धर, संग्राम जित खर-दूषणम् ॥", "Aajaanubhuj shar chaap dhar, sangram jit Khar-Dooshanam ||"),
                AartiLyricLine("इति वदति तुलसीदास शंकर शेष मुनि मन रंजनम् ।", "इति वदति तुलसीदास शंकर शेष मुनि मन रंजनम् ।", "Iti vadati Tulasidas Shankar Shesh muni man ranjanam |"),
                AartiLyricLine("मम हृदय कंज निवास कुरु, कामादि खल दल गंजनम् ॥", "मम हृदय कंज निवास कुरु, कामादि खल दल गंजनम् ॥", "Mam hriday kanj nivaas kuru, kaamadi khal dal ganjanam ||")
            )
        ),
        SacredAarti(
            id = "ghalin_lotangan",
            titleEn = "Ghalin Lotangan (Prarthana)",
            titleHi = "घालीन लोटांगण वंदन चरणी (प्रार्थना)",
            titleMr = "घालीन लोटांगण वंदन चरणी",
            deity = "Universal Divine",
            category = "Stuti",
            durationApproxSeconds = 210,
            lines = listOf(
                AartiLyricLine("घालीन लोटांगण वंदीन चरण ।", "घालीन लोटांगण वंदीन चरण ।", "Ghalin lotangan vandin charan |"),
                AartiLyricLine("डोळ्यांनी पाहीन रूप तुझें ॥", "डोळ्यांनी पाहीन रूप तुझें ॥", "Dolyanni pahin roop tujhe ||"),
                AartiLyricLine("प्रेमें आलिंगिन आनंदें पूजिन ।", "प्रेमें आलिंगिन आनंदें पूजिन ।", "Premen aalingin aananden poojin |"),
                AartiLyricLine("भावें ओवाळीन म्हणे नामा ॥", "भावें ओवाळीन म्हणे नामा ॥", "Bhaven ovalin mhane nama ||"),
                AartiLyricLine("त्वमेव माता च पिता त्वमेव ।", "त्वमेव माता च पिता त्वमेव ।", "Tvameva mata cha pita tvameva |"),
                AartiLyricLine("त्वमेव बन्धुश्च सखा त्वमेव ॥", "त्वमेव बन्धुश्च सखा त्वमेव ॥", "Tvameva bandhush-cha sakha tvameva ||"),
                AartiLyricLine("त्वमेव विद्या द्रविणं त्वमेव ।", "त्वमेव विद्या द्रविणं त्वमेव ।", "Tvameva vidya dravinam tvameva |"),
                AartiLyricLine("त्वमेव सर्वं मम देव देव ॥", "त्वमेव सर्वं मम देव देव ॥", "Tvameva sarvam mama deva deva ||"),
                AartiLyricLine("कायेन वाचा मनसेंद्रियैर्वा ।", "कायेन वाचा मनसेंद्रियैर्वा ।", "Kayena vacha manasendriyairva |"),
                AartiLyricLine("बुद्ध्यात्मना वा प्रकृतिस्वभावात् ॥", "बुद्ध्यात्मना वा प्रकृतिस्वभावात् ॥", "Buddhyatmana va prakritisvabhavat ||"),
                AartiLyricLine("करोमि यद्यत् सकलं परस्मै ।", "करोमि यद्यत् सकलं परस्मै ।", "Karomi yadyat sakalam parasmai |"),
                AartiLyricLine("नारायणायेति समर्पयामि ॥", "नारायणायेति समर्पयामि ॥", "Narayanayeti samarpayami ||"),
                AartiLyricLine("अच्युतं केशवं रामनारायणं कृष्णदामोदरं वासुदेवं हरिम् ।", "अच्युतं केशवं रामनारायणं कृष्णदामोदरं वासुदेवं हरिम् ।", "Achyutam Keshavam Ramanarayanam Krishna Damodaram Vasudevam Harim |"),
                AartiLyricLine("श्रीधरं माधवं गोपिकावल्लभं जानकीनायकं रामचंद्रं भजे ॥", "श्रीधरं माधवं गोपिकावल्लभं जानकीनायकं रामचंद्रं भजे ॥", "Shridharam Madhavam Gopikavallabham Janakinayakam Ramachandram bhaje ||"),
                AartiLyricLine("हरे राम हरे राम राम राम हरे हरे ।", "हरे राम हरे राम राम राम हरे हरे ।", "Hare Rama Hare Rama Rama Rama Hare Hare |"),
                AartiLyricLine("हरे कृष्ण हरे कृष्ण कृष्ण कृष्ण हरे हरे ॥", "हरे कृष्ण हरे कृष्ण कृष्ण कृष्ण हरे हरे ॥", "Hare Krishna Hare Krishna Krishna Krishna Hare Hare ||")
            )
        ),
        SacredAarti(
            id = "shiva_tandava",
            titleEn = "Shiva Tandava Stotram",
            titleHi = "शिव तांडव स्तोत्रम् (रावण कृत)",
            titleMr = "शिव तांडव स्तोत्र",
            deity = "Lord Shiva",
            category = "Stotra",
            durationApproxSeconds = 300,
            lines = listOf(
                AartiLyricLine("जटाटवीगलज्जलप्रवाहपावितस्थले गलेऽवलम्ब्य लम्बितां भुजङ्गतुङ्गमालिकाम् ।", "जटाटवीगलज्जलप्रवाहपावितस्थले गलेऽवलम्ब्य लम्बितां भुजङ्गतुङ्गमालिकाम् ।", "Jatatavee-galaj-jala-pravaha-paavita-sthale gale avalambya lambitam bhujanga-tunga-malikam |"),
                AartiLyricLine("डमड्डमड्डमड्डमन्निनादवड्डमर्वयं चकार चण्डताण्डवं तनोतु नः शिवः शिवम् ॥", "डमड्डमड्डमड्डमन्निनादवड्डमर्वयं चकार चण्डताण्डवं तनोतु नः शिवः शिवम् ॥", "Damad-damad-damad-daman-ninadavaddamar-vayam chakara chanda-tandavam tanotu nah shivah shivam ||"),
                AartiLyricLine("जटाकटाहसंभ्रमभ्रमन्निलिम्पनिर्झरी- विलोलवीचिवल्लरीविराजमानमूर्धनि ।", "जटाकटाहसंभ्रमभ्रमन्निलिम्पनिर्झरी- विलोलवीचिवल्लरीविराजमानमूर्धनि ।", "Jata-kataha-sambhrama-bhraman-nilimpa-nirjhari vilola-veechi-vallari-virajamana-moordhani |"),
                AartiLyricLine("धधद्धधद्धधज्ज्वलल्ललाटपट्टपावके किशोरचन्द्रशेखरे रतिः प्रतिक्षणं मम ॥", "धधद्धधद्धधज्ज्वलल्ललाटपट्टपावके किशोरचन्द्रशेखरे रतिः प्रतिक्षणं मम ॥", "Dhadhad-dhadhad-dhadhajj-jwalal-lalata-patta-paavake kishora-chandra-shekhare ratih pratikshanam mama ||"),
                AartiLyricLine("धराधरेन्द्रनंदिनीविलासबन्धुबन्धुर- स्फुरद्दिगन्तसन्ततिप्रमोदमानमानसे ।", "धराधरेन्द्रनंदिनीविलासबन्धुबन्धुर- स्फुरद्दिगन्तसन्ततिप्रमोदमानमानसे ।", "Dharadharendra-nandini-vilasa-bandhu-bandhura sphurad-diganta-santati-pramodamana-manase |"),
                AartiLyricLine("कृपाकटाक्षधोरणीनिरुद्धदुर्धरापदि क्वचिद्दिगम्बरे मनो विनोदमेतु वस्तुनि ॥", "कृपाकटाक्षधोरणीनिरुद्धदुर्धरापदि क्वचिद्दिगम्बरे मनो विनोदमेतु वस्तुनि ॥", "Kripa-kataksha-dhorani-niruddha-durdharapadi kvachid-digambare mano vinodametu vastuni ||")
            )
        )
    )

    fun getAartiById(id: String): SacredAarti {
        return aartis.find { it.id == id } ?: aartis.first()
    }
}
