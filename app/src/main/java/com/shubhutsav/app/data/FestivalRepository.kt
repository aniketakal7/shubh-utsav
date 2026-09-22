package com.shubhutsav.app.data

object FestivalRepository {

    val festivals: List<Festival> by lazy {
        listOf(
            // 1. DIWALI
            Festival(
                id = "diwali",
                nameEn = "Diwali (Deepavali)",
                nameHi = "दीपावली (लक्ष्मी पूजन)",
                date2026En = "12 Nov 2026",
                date2026Hi = "12 नवंबर 2026",
                date2025En = "20 Oct 2025",
                date2027En = "31 Oct 2027",
                monthEn = "November",
                monthHi = "नवंबर",
                categoryEn = "Diwali Season",
                categoryHi = "दीपावली पर्व",
                emoji = "🪔",
                summaryEn = "The festival of lights celebrating the return of Lord Rama and divine blessings of Maa Lakshmi and Lord Ganesha.",
                summaryHi = "रोशनी का महापर्व, माता लक्ष्मी व भगवान गणेश का स्वागत एवं अंधकार पर प्रकाश की विजय।",
                significanceEn = "Diwali marks Lord Rama's triumphant return to Ayodhya after 14 years of exile. On this night, Goddess Lakshmi visits clean and welcoming homes to bestow prosperity, wisdom, and inner light. Lighting diyas symbolizes removing ignorance and inviting positive energy.",
                significanceHi = "दीपावली 14 वर्ष के वनवास के बाद भगवान श्री राम के अयोध्या लौटने का पावन उत्सव है। इस रात्रि देवी महालक्ष्मी और विघ्नहर्ता गणेश जी हर उस घर में पधारते हैं जो स्वच्छता और श्रद्धा से सजा हो। दीप जलाने से अज्ञान और नकारात्मकता दूर होती है।",
                explainSimplyEn = "Think of Diwali as an annual spiritual and home reset. You clean away old clutter, light lamps to bring warmth and hope, pray for prosperity with ethics (represented by Lakshmi and Ganesha together), and share sweets with loved ones to sweeten relationships.",
                explainSimplyHi = "दीपावली को अपने घर और मन की वार्षिक शुद्धि समझें। पुरानी नकारात्मकता को साफ करें, दीप जलाकर आशा और सकारात्मक ऊर्जा लाएं, और लक्ष्मी जी के साथ गणेश जी की पूजा इसलिए करें ताकि धन के साथ सद्बुद्धि भी मिले।",
                muhurat = MuhuratTiming(
                    nameEn = "Lakshmi Puja Muhurat (Pradosh Kaal)",
                    nameHi = "लक्ष्मी पूजन मुहूर्त (प्रदोष काल)",
                    timeEn = "05:40 PM – 07:35 PM",
                    timeHi = "शाम 05:40 से 07:35 तक",
                    noteEn = "Pradosh Kaal and Sthir Lagna (Vrishabha) are considered best for stable household wealth.",
                    noteHi = "प्रदोष काल और स्थिर लग्न (वृषभ) में पूजा करना घर में स्थायी सुख-समृद्धि लाता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("7 Days Before", "7 दिन पहले", "Deep clean house and declutter closets. Check diyas, rangoli stencils and lights.", "घर की गहरी सफाई करें, पुराने अनावश्यक सामान हटाएं। दीये और सजावटी लाइटें जांचें।"),
                    PrepTask("3 Days Before", "3 दिन पहले", "Buy silver/brass utensils or coin. Procure dry fruits, sweets and gifts for relatives.", "धनतेरस पर बर्तन या चांदी का सिक्का खरीदें। रिश्तेदारों के लिए मिठाई और सूखे मेवे तैयार करें।"),
                    PrepTask("1 Day Before", "1 दिन पहले", "Decorate main entrance with mango leaves (Toran), draw rangoli base, prepare puja chowki with red cloth.", "मुख्य द्वार पर तोरण लगाएं, रंगोली बनाएं और पूजा की चौकी पर लाल कपड़ा बिछाकर तैयार रखें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Take a bath early, wear traditional clean clothes, arrange 21 diyas with cotton wicks and pure ghee/oil.", "स्नान कर स्वच्छ वस्त्र पहनें, शाम के लिए 21 दीये, घी/तेल और बत्तियां पहले से तैयार रखें।")
                ),
                steps = listOf(
                    PujaStep(
                        1,
                        "Sanctification & Aachaman",
                        "पवित्रीकरण एवं आचमन",
                        "Sprinkle Ganga water over yourself and the puja space. Take water in your right palm with a spoon, sip thrice chanting Vishnu's name, then wash hands.",
                        "अपने ऊपर व पूजा स्थल पर गंगाजल छिड़कें। दाहिने हाथ में आचमनी से तीन बार जल लेकर विष्णु नाम का स्मरण कर पिएं, फिर हाथ धो लें।",
                        "ॐ अपवित्रः पवित्रो वा सर्वावस्थां गतोऽपि वा। यः स्मरेत्पुण्डरीकाक्षं स बाह्याभ्यन्तरः शुचिः॥",
                        "Keep a separate clean towel handy.",
                        "हाथ पोंछने के लिए अलग सूती रुमाल रखें।"
                    ),
                    PujaStep(
                        2,
                        "Sankalp (Vow of Intention)",
                        "संकल्प",
                        "Take unbroken rice (akshat), flowers and a coin in your right palm. State today's date, your family name, and pray for health, prosperity and peace.",
                        "दाहिने हाथ में अक्षत, पुष्प और सिक्का लें। आज की तिथि, गोत्र और नाम का उच्चारण कर सपरिवार सुख-शांति व समृद्धि की कामना करें।",
                        "ॐ विष्णुर्विष्णुर्विष्णुः श्रीमद्भगवतो महापुरुषस्य विष्णोराज्ञया प्रवर्तमानस्य...",
                        "If you don't know your Gotra, say 'Kashyap Gotra' or simply pray in your mother tongue with a sincere heart.",
                        "यदि गोत्र याद न हो तो 'कश्यप गोत्र' कह सकते हैं या शुद्ध भाव से मातृभाषा में प्रार्थना करें।"
                    ),
                    PujaStep(
                        3,
                        "Kalash Sthapana & Ganesh Pujan",
                        "कलश स्थापना एवं गणेश पूजन",
                        "Fill brass/copper kalash with clean water, add supari, coin and mango leaves. Place coconut wrapped in red cloth on top. Worship Lord Ganesha first with roli and durva grass.",
                        "तांबे/पीतल के कलश में जल, सिक्का, सुपारी और आम के पत्ते रखें। ऊपर लाल कपड़े में लिपटा नारियल रखें। सर्वप्रथम श्री गणेश जी को रोली व दूर्वा अर्पित करें।",
                        "ॐ गं गणपतये नमः। वक्रतुण्ड महाकाय सूर्यकोटि समप्रभ। निर्विघ्नं कुरु मे देव सर्वकार्येषु सर्वदा॥",
                        "Always place Ganesha to the left of Lakshmi from your viewing perspective.",
                        "देखने पर गणेश जी हमेशा लक्ष्मी जी के दाईं ओर (लक्ष्मी जी की बायीं ओर) होने चाहिए।"
                    ),
                    PujaStep(
                        4,
                        "Maha Lakshmi Aavahan & Shodashopachara",
                        "महालक्ष्मी आवाहन एवं षोडशोपचार",
                        "Invite Goddess Lakshmi. Offer flowers, dhoop, deep, roli, unbroken rice, fruits, lotus flower and sweets (Kheel-Batashe).",
                        "मां लक्ष्मी का आवाहन करें। उन्हें लाल कमल, रोली, अक्षत, धूप, दीप, फल, खील-बताशे और मिठाई श्रद्धापूर्वक अर्पित करें।",
                        "ॐ श्रीं ह्रीं श्रीं कमले कमलालये प्रसीद प्रसीद श्रीं ह्रीं श्रीं ॐ महालक्ष्म्यै नमः॥",
                        "Offer 5 or 7 varieties of dry fruits and sweets if possible.",
                        "संभव हो तो पंचमेवा और कमल गट्टे भी अर्पित करें।"
                    ),
                    PujaStep(
                        5,
                        "Kuber & Bahi-Khata / Ledger Pujan",
                        "कुबेर पूजन एवं बही-खाता पूजन",
                        "Worship Lord Kuber (treasurer of wealth) and write 'Shubh-Labh' and 'Swastika' with roli on your books, diary or laptop.",
                        "धन के अधिपति कुबेर जी की पूजा करें और अपनी डायरी, लैपटॉप या बही-खाते पर रोली से 'शुभ-लाभ' और 'स्वास्तिक' बनाएं।",
                        "ॐ यक्षाय कुबेराय वैश्रवणाय धनधान्याधिपतये धनधान्यसमृद्धिं मे देहि दापय स्वाहा॥",
                        "Modern professionals can place their work laptops or diaries here.",
                        "कामकाजी लोग अपने लैपटॉप या कार्य-पुस्तिका पर भी स्वास्तिक बना सकते हैं।"
                    ),
                    PujaStep(
                        6,
                        "Lighting of Primary Diyas & Deep Daan",
                        "मुख्य दीप प्रज्ज्वलन एवं दीप दान",
                        "Light the primary 4-wick ghee diya (Akhand Diya) in front of deities. Then light 21 mustard oil/ghee earthen diyas and place them around your home.",
                        "ठाकुर जी के सम्मुख चार मुखी घी का मुख्य दीया जलाएं। इसके बाद 21 मिट्टी के दीयों को प्रज्ज्वलित कर घर के हर कोने में रखें।",
                        "शुभं करोति कल्याणमारोग्यं धनसंपदा। शत्रुबुद्धिविनाशाय दीपज्योतिर्नमोऽस्तुते॥",
                        "Place the first diyas at the puja chowki, then main entrance, kitchen, and tulsi plant.",
                        "पहला दीया पूजा स्थान, दूसरा मुख्य द्वार, तीसरा रसोई और चौथा तुलसी जी के पास रखें।"
                    ),
                    PujaStep(
                        7,
                        "Aarti & Distribution of Prasad",
                        "आरती एवं प्रसाद वितरण",
                        "Perform Lakshmi Aarti and Ganesh Aarti with family singing together. Bow down, seek forgiveness for any shortcomings, and share prasad.",
                        "सपरिवार मिलकर श्री गणेश जी और लक्ष्मी जी की आरती गाएं। झुककर प्रणाम करें, अनजाने में हुई भूल की क्षमा मांगें और प्रसाद बांटें।",
                        "ॐ जय लक्ष्मी माता, मैया जय लक्ष्मी माता। तुमको निसदिन सेवत, हर विष्णु विधाता॥",
                        "Aarti should be rotated clockwise in circles of 7 or 11 times.",
                        "आरती को 7 या 11 बार दक्षिणावर्त (क्लॉकवाइज) घुमाना चाहिए।"
                    )
                ),
                supplies = listOf(
                    Supply("Lakshmi & Ganesh Idol (Clay / Metal)", "लक्ष्मी-गणेश जी की प्रतिमा", "1 Set", "1 जोड़ा", "Idols & Statues", "प्रतिमा"),
                    Supply("Earthen Diyas (Mitti ke diye)", "मिट्टी के दीये", "21 to 51 pcs", "21 से 51 नग", "Lamps & Lighting", "दीपक"),
                    Supply("Pure Cow Ghee or Mustard Oil", "शुद्ध देसी घी या सरसों तेल", "500 ml", "500 मिली", "Lamps & Lighting", "दीपक"),
                    Supply("Cotton Wicks (Batti - round and long)", "फूल और लंबी बत्तियां", "2 packets", "2 पैकेट", "Lamps & Lighting", "दीपक"),
                    Supply("Kheel & Batashe", "खील और बताशे", "250 g each", "250-250 ग्राम", "Prasad & Sweets", "प्रसाद"),
                    Supply("Red & Yellow Cloth for Chowki", "चौकी के लिए लाल व पीला वस्त्र", "1 meter each", "1-1 मीटर", "Puja Setup", "पूजा स्थल"),
                    Supply("Roli (Kumkum) & Sandalwood (Chandan)", "रोली (कुमकुम) और चंदन", "1 small pack", "1 छोटा पैकेट", "Puja Powders", "रोली-चंदन"),
                    Supply("Unbroken Rice (Akshat)", "अक्षत (बिना टूटे चावल)", "100 g", "100 ग्राम", "Puja Essentials", "आवश्यक सामग्री"),
                    Supply("Ganga Jal (Holy Water)", "गंगाजल", "1 bottle", "1 शीशी", "Puja Essentials", "आवश्यक सामग्री"),
                    Supply("Supari (Betel nuts) & Clove/Cardamom", "पूजा सुपारी, लौंग और इलायची", "5 pcs each", "5-5 नग", "Puja Essentials", "आवश्यक सामग्री"),
                    Supply("Fresh Marigold Flowers & Garland", "गेंदे के फूल और माला", "2 garlands + loose", "2 माला + खुले फूल", "Flowers & Leaves", "फूल और पत्ते"),
                    Supply("Fresh Lotus Flower (Kamal)", "कमल का ताजा फूल", "1 or 2 pcs", "1 या 2 नग", "Flowers & Leaves", "फूल और पत्ते", "Pink rose petals if lotus is unavailable", "कमल न मिलने पर गुलाबी गुलाब के फूल"),
                    Supply("Mango Leaves (Aam ke patte)", "आम के पत्ते (तोरण व कलश हेतु)", "11 leaves", "11 पत्ते", "Flowers & Leaves", "फूल और पत्ते"),
                    Supply("Panchamrit (Milk, Curd, Honey, Sugar, Ghee)", "पंचामृत (दूध, दही, शहद, शक्कर, घी)", "1 small bowl", "1 कटोरी", "Prasad & Sweets", "प्रसाद"),
                    Supply("Mithai (Laddus / Kaju Katli)", "मिठाई (लड्डू / काजू कतली)", "500 g", "500 ग्राम", "Prasad & Sweets", "प्रसाद"),
                    Supply("Incense Sticks (Agarbatti) & Dhoop", "अगरबत्ती और धूपबत्ती", "1 pack each", "1-1 पैकेट", "Puja Essentials", "आवश्यक सामग्री"),
                    Supply("Camphor (Karpuram) for Aarti", "भीमसेनी कपूर", "1 small box", "1 डिब्बी", "Puja Essentials", "आवश्यक सामग्री"),
                    Supply("Silver Coin / Currency Coin", "चांदी का सिक्का या सिक्का", "1 pc", "1 नग", "Puja Essentials", "आवश्यक सामग्री")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ श्रीं ह्रीं श्रीं कमले कमलालये प्रसीद प्रसीद श्रीं ह्रीं श्रीं ॐ महालक्ष्म्यै नमः॥",
                        "Om Shreem Hreem Shreem Kamale Kamalalaye Praseed Praseed Shreem Hreem Shreem Om Mahalakshmyai Namah",
                        "Salutations to the Supreme Goddess Lakshmi, who dwells in the sacred lotus, shower your divine grace, peace, and abundance upon us.",
                        "कमल के आसन पर विराजमान परमेश्वरी महालक्ष्मी, मुझ पर प्रसन्न हों और अपनी दिव्य कृपा व समृद्धि बरसाएं।"
                    ),
                    MantraItem(
                        "वक्रतुण्ड महाकाय सूर्यकोटि समप्रभ। निर्विघ्नं कुरु मे देव सर्वकार्येषु सर्वदा॥",
                        "Vakratunda Mahakaya Suryakoti Samaprabha | Nirvighnam Kuru Me Deva Sarvakaryeshu Sarvada",
                        "O Lord Ganesha of curved trunk and immense aura radiant like million suns, please remove all hurdles from all my endeavours forever.",
                        "हे विशालकाय, टेढ़ी सूंड वाले करोड़ों सूर्यों के समान तेजस्वी गणेश जी, मेरे सभी कार्यों को सदा बाधा-मुक्त करें।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Placing Lakshmi on the right of Ganesha", "लक्ष्मी जी को गणेश जी के दाईं ओर बैठाना", "Place Ganesha to Lakshmi's right (which is your left when facing them). Lakshmi sits to Ganesha's left.", "सामने से देखने पर गणेश जी को बाईं तरफ और लक्ष्मी जी को दाईं तरफ रखें।"),
                    CommonMistake("Leaving diyas unmonitored near curtains", "पर्दों या कपड़ों के पास दीये रखना", "Keep all burning diyas away from synthetic curtains, wiring, or open windows. Use brass/heat-proof coasters.", "दीयों को हमेशा पर्दे, तारों और लकड़ी की सतहों से दूर और सुरक्षित प्लेट पर रखें।"),
                    CommonMistake("Using cracked or chipped earthen diyas", "टूटे या चटके हुए मिट्टी के दीये जलाना", "Soak diyas in water hours before and discard any cracked or leaking ones.", "दीयों को पहले पानी में भिगोकर सुखा लें और टूटे हुए दीयों का उपयोग न करें।")
                )
            ),

            // 2. DHANTERAS
            Festival(
                id = "dhanteras",
                nameEn = "Dhanteras (Dhanatrayodashi)",
                nameHi = "धनतेरस (धनत्रयोदशी)",
                date2026En = "9 Nov 2026",
                date2026Hi = "9 नवंबर 2026",
                date2025En = "18 Oct 2025",
                date2027En = "28 Oct 2027",
                monthEn = "November",
                monthHi = "नवंबर",
                categoryEn = "Diwali Season",
                categoryHi = "दीपावली पर्व",
                emoji = "✨",
                summaryEn = "Auspicious day for health, buying utensils or gold, and worshipping Lord Dhanvantari and Yama Deepam.",
                summaryHi = "आरोग्य के देवता भगवान धन्वंतरि की पूजा, नए बर्तन व धातु की खरीदारी और यम दीप दान।",
                significanceEn = "Lord Dhanvantari emerged from the Samudra Manthan with the pot of Amrita (elixir of immortality) on this day. It is celebrated to pray for robust health before celebrating wealth. In the evening, a special four-wick diya is lit facing south (Yama Deepam) to avert untimely accidents and illnesses.",
                significanceHi = "समुद्र मंथन के समय भगवान धन्वंतरि अमृत कलश लेकर इसी दिन प्रकट हुए थे। यह दिन धन से पहले स्वास्थ्य के महत्व को रेखांकित करता है। शाम को अकाल मृत्यु के भय से मुक्ति हेतु दक्षिण दिशा में यम का चौमुखा दीपक जलाया जाता है।",
                explainSimplyEn = "Dhanteras reminds us that health is the real first wealth. Buy something durable for the home (even a simple steel spoon or copper glass), pray to Lord Dhanvantari for family health, and light a lamp facing south outside your doorstep.",
                explainSimplyHi = "धनतेरस हमें सिखाता है कि पहला सुख निरोगी काया है। अपनी सामर्थ्य अनुसार घर के लिए कोई उपयोगी बर्तन खरीदें, परिवार के अच्छे स्वास्थ्य की प्रार्थना करें और मुख्य द्वार पर यम दीप जलाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Dhanteras Puja & Buying Muhurat",
                    nameHi = "धनतेरस पूजा एवं खरीदारी मुहूर्त",
                    timeEn = "06:15 PM – 08:10 PM",
                    timeHi = "शाम 06:15 से 08:10 तक",
                    noteEn = "Auspicious Choghadiya window during Pradosh Kaal.",
                    noteHi = "प्रदोष काल में खरीदारी और पूजा श्रेष्ठ मानी जाती है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("3 Days Before", "3 दिन पहले", "Plan purchases (brass, copper, gold, or silver) according to household budget.", "बजट के अनुसार पीतल, तांबा, चांदी या बर्तन की योजना बनाएं।"),
                    PrepTask("1 Day Before", "1 दिन पहले", "Clean and wash entrance, prepare wheat dough or mud for Yama diya.", "मुख्य द्वार साफ करें और यम के दीपक के लिए आटे या मिट्टी का दीया तैयार रखें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Make purchases in auspicious muhurat, wash new utensils before placing in kitchen, light Yama Deepam at dusk.", "शुभ मुहूर्त में खरीदारी करें, नए बर्तनों को धोकर हल्दी-कुमकुम लगाएं और गोधूलि वेला में यम दीप जलाएं।")
                ),
                steps = listOf(
                    PujaStep(1, "Cleanse and Purify", "शुद्धिकरण", "Sprinkle Ganga water, light agarbatti.", "गंगाजल छिड़कें और धूपबत्ती लगाएं।"),
                    PujaStep(2, "Dhanvantari Puja", "धन्वंतरि पूजन", "Offer yellow flowers, haldi, and kheer to Lord Dhanvantari praying for family wellness.", "भगवान धन्वंतरि को पीले पुष्प, हल्दी और खीर का भोग लगाकर निरोगी जीवन की प्रार्थना करें।", "ॐ नमो भगवते वासुदेवाय धन्वन्तरये अमृतकलशहस्ताय नमः"),
                    PujaStep(3, "Kuber Puja & Buying Sanctification", "कुबेर पूजन व बर्तन शुद्धि", "Place newly bought utensil or item on chowki, apply roli-akshat and offer sweets.", "खरीदे गए बर्तन या आभूषण पर रोली-अक्षत लगाकर भोग लगाएं।"),
                    PujaStep(4, "Yama Deepam (Evening)", "यम दीप दान (सायंकाल)", "Light a 4-wick flour/earthen diya filled with mustard oil facing south outside the main door.", "सरसों के तेल से चौमुखा दीपक जलाकर मुख्य द्वार के बाहर दक्षिण दिशा की ओर मुख करके रखें।", "मृत्युना पाशदण्डाभ्यां कालेन श्यामया सह। त्रयोदश्यां दीपदानात् सूर्यजः प्रीयतां मम॥")
                ),
                supplies = listOf(
                    Supply("New Metal Utensil (Brass / Copper / Steel)", "धातु का नया बर्तन (पीतल/तांबा/स्टील)", "1 pc", "1 नग", "Shopping", "खरीदारी"),
                    Supply("Clay or Wheat Dough Diya for Yama", "यम दीप हेतु चौमुखा दीया", "1 pc", "1 नग", "Puja Items", "पूजा सामग्री"),
                    Supply("Mustard Oil for Yama Diya", "सरसों का तेल", "100 ml", "100 मिली", "Puja Items", "पूजा सामग्री"),
                    Supply("Yellow Flowers & Haldi", "पीले फूल और पिसी हल्दी", "1 pack", "1 पैकेट", "Flowers & Leaves", "फूल और पत्ते"),
                    Supply("Batasha / Kheer for Bhog", "बताशे या खीर का भोग", "1 bowl", "1 कटोरी", "Prasad & Sweets", "प्रसाद")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ धन्वन्तरये नमः॥",
                        "Om Dhanvantaraye Namah",
                        "I bow to Lord Dhanvantari, divine physician of the cosmos.",
                        "समस्त रोगों का शमन करने वाले भगवान धन्वंतरि को नमन।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Buying knives, scissors or black iron items", "कैंची, चाकू या नुकीली चीजें खरीदना", "Avoid purchasing sharp iron blades or glass on Dhanteras; choose copper, brass, gold, silver or simple steel.", "धारदार लोहे के औजार या कांच का सामान खरीदने से बचें; पीतल, तांबा या बर्तन लें।"),
                    CommonMistake("Placing Yama diya inside the living room", "यम का दीया घर के भीतर रखना", "Yama diya is strictly placed outside the threshold facing southwards.", "यम दीप को हमेशा मुख्य द्वार के बाहर दक्षिण की ओर मुंह करके रखा जाता है।")
                )
            ),

            // 3. CHOTI DIWALI / NARAKA CHATURDASHI
            Festival(
                id = "choti_diwali",
                nameEn = "Choti Diwali (Naraka Chaturdashi)",
                nameHi = "छोटी दिवाली (नरक चतुर्दशी)",
                date2026En = "11 Nov 2026",
                date2026Hi = "11 नवंबर 2026",
                date2025En = "19 Oct 2025",
                date2027En = "30 Oct 2027",
                monthEn = "November",
                monthHi = "नवंबर",
                categoryEn = "Diwali Season",
                categoryHi = "दीपावली पर्व",
                emoji = "🌙",
                summaryEn = "A day of internal and external cleansing, celebrating Lord Krishna's triumph over demon Narakasura.",
                summaryHi = "भगवान श्रीकृष्ण द्वारा नरकासुर के संहार का दिन, उबटन स्नान और 14 दीपों की रोशनी।",
                significanceEn = "Lord Krishna and Satyabhama vanquished the tyrant Narakasura, liberating 16,000 captive women. Before sunrise, people perform Abhyanga Snan (holy bath with sesame oil and herbal ubtan) to wash away impurities, fatigue, and sins.",
                significanceHi = "भगवान श्रीकृष्ण और सत्यभामा ने नरकासुर का वध कर धर्म की रक्षा की थी। इस दिन सूर्योदय से पूर्व उबटन व तिल के तेल से अभ्यंग स्नान करने से शरीर तेजस्वी और मन निर्मल होता है।",
                explainSimplyEn = "Choti Diwali is like a revitalizing self-care and detox day before the grand Diwali puja. Wake up early, apply traditional ubtan, take a warm fragrant bath, and light 14 small diyas around the house in the evening.",
                explainSimplyHi = "बड़ी दिवाली से पहले यह शरीर और मन की स्वच्छता का दिन है। सुबह जल्दी उठकर उबटन लगाएं, शुद्ध स्नान करें और शाम को घर में 14 दीये जलाकर स्वच्छता और प्रकाश फैलाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Abhyanga Snan Muhurat (Pre-dawn)",
                    nameHi = "अभ्यंग स्नान मुहूर्त (ब्रह्म मुहूर्त)",
                    timeEn = "05:08 AM – 06:38 AM",
                    timeHi = "सुबह 05:08 से 06:38 तक",
                    noteEn = "Bath taken before sunrise with sesame oil is considered as purifying as Ganga bath.",
                    noteHi = "सूर्योदय से पूर्व तिल का तेल और उबटन लगाकर स्नान करना अत्यंत शुभ होता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare natural ubtan (gram flour, turmeric, sandalwood, rose water) and sesame oil.", "बेसन, हल्दी, चंदन और गुलाब जल मिलाकर उबटन तैयार करें और तिल का तेल रखें।"),
                    PrepTask("On the Day Morning", "दिन की सुबह", "Wake before sunrise, massage with oil and ubtan, take warm bath.", "सूर्योदय से पूर्व उठकर तेल-उबटन लगाएं और गुनगुने पानी से स्नान करें।"),
                    PrepTask("On the Day Dusk", "शाम के समय", "Light 14 diyas and place them in bathroom, courtyard, terrace, and corners.", "शाम को 14 दीये जलाकर घर की छत, सीढ़ियों और कोनों में रखें।")
                ),
                steps = listOf(
                    PujaStep(1, "Abhyanga Snan", "उबटन व तेल स्नान", "Massage body with sesame oil and herbal paste, take bath with warm water.", "तिल के तेल व उबटन की मालिश के बाद गर्म जल से स्नान करें।"),
                    PujaStep(2, "Krishna Worship", "श्रीकृष्ण पूजन", "Offer fragrant flowers and makhan-mishri to Lord Krishna.", "भगवान श्रीकृष्ण को माखन-मिश्री का भोग लगाएं और दीप दिखाएं।", "ॐ नमो भगवते वासुदेवाय"),
                    PujaStep(3, "14 Deep Daan", "14 दीप प्रज्ज्वलन", "Light 14 small earthen lamps at dusk in overlooked corners of the house.", "संध्या के समय घर के अंधेरे कोनों और छतों पर 14 दीपक रखें।")
                ),
                supplies = listOf(
                    Supply("Herbal Ubtan powder", "पारंपरिक उबटन", "100 g", "100 ग्राम", "Bath Essentials", "स्नान सामग्री"),
                    Supply("Sesame Oil (Til ka tel)", "तिल का तेल", "100 ml", "100 मिली", "Bath Essentials", "स्नान सामग्री"),
                    Supply("Earthen Lamps", "मिट्टी के दीये", "14 pcs", "14 नग", "Lamps", "दीपक"),
                    Supply("Makhan Mishri", "माखन मिश्री", "1 small bowl", "1 कटोरी", "Prasad", "प्रसाद")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ कृष्णाय वासुदेवाय हरये परमात्मने। प्रणतक्लेशनाशाय गोविंदाय नमो नमः॥",
                        "Om Krishnaya Vasudevaya Haraye Paramatmane | Pranata Klesha Nashaya Govindaya Namo Namah",
                        "Salutations to Lord Krishna, who removes all sorrows of those who surrender to Him.",
                        "समस्त संकटों को हरने वाले भगवान श्रीकृष्ण को कोटि-कोटि प्रणाम।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Sleeping in late past sunrise", "सूर्योदय के बाद देर तक सोते रहना", "The spiritual benefit of Chaturdashi lies in waking during pre-dawn hours for purification.", "इस दिन का मुख्य महत्व सूर्योदय से पहले उठकर स्नान करने में है।")
                )
            ),

            // 4. GOVARDHAN PUJA
            Festival(
                id = "govardhan",
                nameEn = "Govardhan Puja (Annakut)",
                nameHi = "गोवर्धन पूजा (अन्नकूट)",
                date2026En = "13 Nov 2026",
                date2026Hi = "13 नवंबर 2026",
                date2025En = "21 Oct 2025",
                date2027En = "1 Nov 2027",
                monthEn = "November",
                monthHi = "नवंबर",
                categoryEn = "Diwali Season",
                categoryHi = "दीपावली पर्व",
                emoji = "🏔️",
                summaryEn = "Celebration of nature, cow worship, and community gratitude remembering Lord Krishna lifting Mount Govardhan.",
                summaryHi = "प्रकृति और गौ माता के प्रति कृतज्ञता, भगवान कृष्ण द्वारा गोवर्धन पर्वत उठाने का पावन स्मृति पर्व।",
                significanceEn = "Lord Krishna taught the people of Braj to worship Mother Nature (Govardhan hill, cows, forests and rains) rather than celestial fear. Devotees prepare 'Annakut' (a mountain of 56 diverse vegetarian dishes) representing abundance and ecology.",
                significanceHi = "श्रीकृष्ण ने ब्रजवासियों को भय की जगह प्रकृति (पहाड़, पेड़, गाय और वर्षा) की पूजा करना सिखाया। इस दिन 56 भोग या अन्नकूट बनाकर भगवान को अर्पित किया जाता है जो समाज में अन्न और प्रकृति के सम्मान का प्रतीक है।",
                explainSimplyEn = "Govardhan Puja is India's traditional Thanksgiving to nature and farmers. Families make a symbolic hill of cow dung or flowers in the courtyard, prepare mixed vegetable curry (Annakut kadhi/subzi), and walk around it with gratitude.",
                explainSimplyHi = "गोवर्धन पूजा प्रकृति और पर्यावरण के प्रति आभार व्यक्त करने का दिन है। आंगन में गोबर या फूलों से गोवर्धन पर्वत की सुंदर आकृति बनाएं, अन्नकूट की मिश्रित सब्जी का भोग लगाएं और परिक्रमा करें।",
                muhurat = MuhuratTiming(
                    nameEn = "Govardhan Puja Pratahkaal Muhurat",
                    nameHi = "गोवर्धन पूजा प्रातःकाल मुहूर्त",
                    timeEn = "06:42 AM – 08:51 AM",
                    timeHi = "सुबह 06:42 से 08:51 तक",
                    noteEn = "Morning time is ideal for shaping the hill and parikrama.",
                    noteHi = "सुबह का समय गोवर्धन आकृति बनाने और परिक्रमा हेतु सर्वोत्तम है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Procure seasonal vegetables (radish, methi, peas, carrots, brinjal) for the 56-ingredient Annakut subzi.", "अन्नकूट की मिश्रित सब्जी के लिए सभी मौसमी हरी सब्जियां खरीदकर साफ कर लें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Clean courtyard, shape Govardhan with fresh cow dung/flowers, decorate with cotton/grass, cook Annakut.", "आंगन में गोवर्धन जी की आकृति बनाएं, कढ़ी-चावल और अन्नकूट की सब्जी बनाकर भोग लगाएं।")
                ),
                steps = listOf(
                    PujaStep(1, "Create Mount Govardhan", "गोवर्धन आकृति निर्माण", "Shape the hill with cow dung or yellow/marigold petals in your courtyard or balcony tray.", "आंगन या पूजा थाली में गोबर या ताजे गेंदे के फूलों से गोवर्धन पर्वत बनाएं।"),
                    PujaStep(2, "Offering of Flute & Curd", "वेणु व दही अर्पण", "Place a small clay pot of curd in the center navel, offer flowers, roli, and sugar cane.", "नाभि स्थान पर मिट्टी की कुल्हड़ में दही रखें, गन्ने के टुकड़े और पुष्प अर्पित करें।"),
                    PujaStep(3, "Annakut Bhog", "अन्नकूट भोग", "Offer mixed seasonal vegetable subzi, puri, kheer, and sweets to Krishna.", "कढ़ी, चावल, पूरी और 56 मौसमी सब्जियों से बनी अन्नकूट सब्जी का प्रेमपूर्वक भोग लगाएं।", "ॐ नमो भगवते वासुदेवाय"),
                    PujaStep(4, "Parikrama (Circumambulation)", "गोवर्धन परिक्रमा", "Walk 7 or 4 times clockwise around the Govardhan hill with folded hands and ringing bell.", "हाथ जोड़कर घंटी बजाते हुए गोवर्धन जी की 4 या 7 बार दक्षिणावर्त परिक्रमा करें।")
                ),
                supplies = listOf(
                    Supply("Fresh Cow Dung or Yellow Marigolds", "ताजा गोबर या गेंदे के फूल", "1 basket", "1 टोकरी", "Craft", "सजावट"),
                    Supply("Mixed Seasonal Vegetables (7+ varieties)", "मिश्रित मौसमी सब्जियां", "1 kg", "1 किलो", "Food", "अन्नकूट सामग्री"),
                    Supply("Clay pot with Curd (Dahi)", "मिट्टी के कुल्हड़ में ताजा दही", "1 bowl", "1 कुल्हड़", "Food", "भोग"),
                    Supply("Sugar Cane (Ganna) & Batasha", "गन्ना और खील-बताशे", "Small piece", "छोटे टुकड़े", "Puja Items", "पूजा सामग्री")
                ),
                mantras = listOf(
                    MantraItem(
                        "गोवर्धन धराधार गोकुल त्राणकारक। विष्णुबाहु कृतोच्छ्राय गवां कोटिप्रदो भव॥",
                        "Govardhana Dharadhara Gokula Tranakarakah | Vishnubahu Kritochhraya Gavam Kotiprado Bhava",
                        "O Mount Govardhan, lifter of burdens and protector of Gokul, blessed by the hands of Lord Vishnu, bestow prosperity upon our cattle and homes.",
                        "हे गोवर्धन पर्वत! आप गोकुल के रक्षक और भगवान विष्णु की भुजाओं से धारण किए गए हैं, हमारे घर में अन्न और धन की वृद्धि करें।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Stepping on the symbolic hill after parikrama", "परिक्रमा के बाद गोवर्धन जी पर पैर लगना", "Treat the sacred symbol with deep respect until respectful visarjan the next day.", "गोवर्धन आकृति का पूरा सम्मान रखें और अगले दिन ही विसर्जन करें।")
                )
            ),

            // 5. BHAI DOOJ
            Festival(
                id = "bhai_dooj",
                nameEn = "Bhai Dooj (Yama Dwitiya)",
                nameHi = "भाई दूज (यम द्वितीया)",
                date2026En = "14 Nov 2026",
                date2026Hi = "14 नवंबर 2026",
                date2025En = "22 Oct 2025",
                date2027En = "2 Nov 2027",
                monthEn = "November",
                monthHi = "नवंबर",
                categoryEn = "Diwali Season",
                categoryHi = "दीपावली पर्व",
                emoji = "👫",
                summaryEn = "A tender celebration of sibling love, long life, and divine bonds between brothers and sisters.",
                summaryHi = "भाई-बहन के अटूट स्नेह, दीर्घायु की प्रार्थना और परस्पर सम्मान का पवित्र त्योहार।",
                significanceEn = "Yamraj visited his sister Yamuna on this day after a long time. Overjoyed, Yamuna fed him sumptuously and applied a protective tilak. Yamraj declared that any brother receiving his sister's affectionate tilak on this day will be blessed with long life and safety.",
                significanceHi = "यमराज इस दिन अपनी बहन यमुना के घर गए थे। यमुना ने प्रेमपूर्वक भोजन कराकर भाई के माथे पर मंगल तिलक लगाया। यमराज ने वरदान दिया कि जो भाई आज बहन के हाथ का भोजन ग्रहण कर तिलक करवाएगा, उसे अकाल भय नहीं रहेगा।",
                explainSimplyEn = "Similar to Raksha Bandhan, but here sisters invite brothers home, apply an auspicious sandalwood-roli tilak, place unbroken rice on the forehead, offer sweets, and pray for each other's lifelong welfare.",
                explainSimplyHi = "भाई दूज रक्षाबंधन की तरह ही भाई-बहन के प्रेम का उत्सव है। बहनें भाई को अपने घर आमंत्रित करती हैं, स्नेह से रोली-अक्षत का तिलक करती हैं और उनकी लंबी आयु व सुखद जीवन की कामना करती हैं।",
                muhurat = MuhuratTiming(
                    nameEn = "Bhai Dooj Aparahna Muhurat (Afternoon)",
                    nameHi = "भाई दूज अपराह्न मुहूर्त",
                    timeEn = "01:10 PM – 03:18 PM",
                    timeHi = "दोपहर 01:10 से 03:18 तक",
                    noteEn = "Afternoon is the traditional auspicious window for the tilak ceremony.",
                    noteHi = "दोपहर का अपराह्न काल तिलक और भोजन के लिए सबसे शुभ माना गया है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare brother's favorite sweets, clean silver thali, dry coconut (Gola/Giri).", "भाई की पसंदीदा मिठाई बनाएं, तिलक थाली और सूखा नारियल (गोला) तैयार रखें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Sister fasts until tilak, arranges wooden chowki with rice paste, invites brother respectfully.", "बहनें तिलक तक सात्विक रहें, भाई को आसन पर बैठाकर आरती और तिलक करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Rice Paste Seat", "आसन व्यवस्था", "Place a wooden chowki facing east and decorate with rice paste (Chowk).", "पूर्व दिशा की ओर मुख करके भाई के बैठने के लिए आसन लगाएं।"),
                    PujaStep(2, "Tilak & Akshat", "तिलक व अक्षत", "Apply sandalwood and roli paste vertically on brother's forehead, press unbroken rice gently.", "भाई के माथे पर रोली और चंदन का सुंदर टीका लगाएं और ऊपर अक्षत लगाएं।"),
                    PujaStep(3, "Kalava & Aarti", "कलावा व आरती", "Tie sacred red thread on his right wrist and rotate aarti thali clockwise.", "भाई की दाहिनी कलाई पर रक्षासूत्र (मौली) बांधें और घी के दीये से आरती उतारें।"),
                    PujaStep(4, "Sweet & Dry Coconut Offering", "मिठाई व नारियल भेंट", "Feed him homemade sweet, hand over the dry coconut (symbol of safety), and exchange blessings/gifts.", "भाई को मिठाई खिलाएं, सूखा नारियल भेंट करें और एक-दूसरे को मंगल आशीष दें।")
                ),
                supplies = listOf(
                    Supply("Dry Coconut (Nariyal Gola)", "सूखा नारियल (गोला)", "1 pc", "1 नग", "Essentials", "तिलक सामग्री"),
                    Supply("Roli, Chandan & Akshat", "रोली, चंदन और अक्षत", "1 thali set", "1 थाली", "Essentials", "तिलक सामग्री"),
                    Supply("Kalava / Mauli Thread", "कलावा (मौली धागा)", "1 roll", "1 गट्टी", "Essentials", "तिलक सामग्री"),
                    Supply("Homemade Sweet or Mithai", "मिठाई", "250 g", "250 ग्राम", "Sweets", "मिठाई")
                ),
                mantras = listOf(
                    MantraItem(
                        "भ्रातस्तवानुजाताहं भुङ्क्ष्व भक्तमिमं शुभम्। प्रीतये यमराजस्य यमुनाया विशेषतः॥",
                        "Bhratastavanujataham Bhunkshva Bhaktamimam Shubham | Preetaye Yamarajasya Yamunaya Visheshatah",
                        "O dear brother, accept this auspicious meal offered by your sister with love, for the grace of Yamraj and Yamuna.",
                        "हे भाई! अपनी बहन के स्नेह से अर्पित इस मिष्ठान्न को स्वीकार करो, यमराज और यमुना की कृपा से तुम सदा दीर्घायु रहो।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Brother facing south during tilak", "तिलक के समय दक्षिण दिशा की ओर मुख करना", "Brother should face North or East for positive spiritual energy.", "तिलक करवाते समय भाई का मुख उत्तर या पूर्व दिशा में होना चाहिए।")
                )
            ),

            // 6. SHARAD NAVRATRI
            Festival(
                id = "navratri",
                nameEn = "Sharad Navratri",
                nameHi = "शारदीय नवरात्रि (घटस्थापना व कन्या पूजन)",
                date2026En = "11–19 Oct 2026",
                date2026Hi = "11–19 अक्टूबर 2026",
                date2025En = "22–30 Sep 2025",
                date2027En = "1–9 Oct 2027",
                monthEn = "October",
                monthHi = "अक्टूबर",
                categoryEn = "Navratri",
                categoryHi = "नवरात्रि",
                emoji = "🌺",
                summaryEn = "Nine glorious nights dedicated to Goddess Durga's nine divine forms, concluding with Kanya Pujan.",
                summaryHi = "मां दुर्गा के नौ दिव्य स्वरूपों की आराधना, अखंड ज्योति, उपवास और कन्या पूजन का महापर्व।",
                significanceEn = "Navratri honors Goddess Durga defeating Mahishasura, symbolizing the triumph of divine inner consciousness over demonic base instincts. The nine days worship Shailaputri, Brahmacharini, Chandraghanta, Kushmanda, Skandamata, Katyayani, Kalaratri, Mahagauri, and Siddhidatri.",
                significanceHi = "नवरात्रि महिषासुर-मर्दिनी मां भगवती की शक्ति का उत्सव है, जो हमारे भीतर के अज्ञान और विकारों पर विजय का संदेश देता है। नौ दिन मां के नौ स्वरूपों की आराधना कर अष्टमी-नवमी पर कन्या पूजन किया जाता है।",
                explainSimplyEn = "Navratri is a 9-day journey of physical detox (satvik fasting) and mental rejuvenation. Families establish a sacred water pot (Kalash), sow barley seeds (Jowar), chant the Durga Chalisa, and honor little girls as living forms of the Goddess on Ashtami or Navami.",
                explainSimplyHi = "नवरात्रि 9 दिनों का आत्म-शुद्धि का अवसर है। सात्विक आहार लें, घटस्थापना करें, जौ बोएं, अखंड दीप जलाएं और अष्टमी या नवमी को छोटी कन्याओं का आदरपूर्वक पूजन कर हलवा-पूरी और चने का प्रसाद खिलाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Ghatasthapana Muhurat (Day 1 Morning)",
                    nameHi = "घटस्थापना मुहूर्त (प्रतिपदा प्रातःकाल)",
                    timeEn = "06:21 AM – 10:14 AM",
                    timeHi = "सुबह 06:21 से 10:14 तक",
                    noteEn = "Abhijit Muhurat on Day 1 is also exceptionally auspicious: 11:46 AM – 12:32 PM.",
                    noteHi = "प्रतिपदा को अभिजीत मुहूर्त (11:46 से 12:32) में भी घटस्थापना की जा सकती है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("3 Days Before", "3 दिन पहले", "Procure earthen bowl for barley (jowar), clean copper kalash, red chunri, Durga saptashati book.", "जौ बोने के लिए मिट्टी का सकोरा, कलश, लाल चुनरी और दुर्गा सप्तशती की पुस्तक व्यवस्थित करें।"),
                    PrepTask("Day 1 (Ghatasthapana)", "पहला दिन (घटस्थापना)", "Sow clean barley in soil, establish Kalash with coconut, light Akhand Jyot safely.", "मिट्टी में शुद्ध जौ बोएं, कलश स्थापित कर अखंड ज्योति प्रज्ज्वलित करें।"),
                    PrepTask("Day 8 / 9 (Ashtami/Navami)", "अष्टमी / नवमी (कन्या पूजन)", "Cook satvik Halwa, Puri, and Kale Chane. Invite 9 young girls and 1 young boy (Batuk), wash their feet and serve.", "काले चने, हलवा और पूरी का भोग बनाएं। 9 कन्याओं और एक लंगूर (बटुक) के चरण धोकर आदरपूर्वक भोजन कराएं।")
                ),
                steps = listOf(
                    PujaStep(1, "Ghatasthapana & Jowar Sowing", "घटस्थापना एवं जौ बोना", "Spread clean soil in an earthen dish, sow barley seeds, place water-filled kalash on top.", "मिट्टी के पात्र में साफ मिट्टी बिछाकर जौ के बीज बोएं, मध्य में गंगाजल मिश्रित जल से भरा कलश स्थापित करें।", "ॐ ऐं ह्रीं क्लीं चामुण्डायै विच्चे"),
                    PujaStep(2, "Maa Durga Aavahan", "मां दुर्गा आवाहन", "Offer red hibiscus/rose flowers, red chunri, shringar items (bangles, bindi, mehendi).", "मां भगवती को लाल पुष्प, लाल चुनरी और सोलह श्रृंगार की सामग्री अर्पित करें।", "या देवी सर्वभूतेषु शक्तिरूपेण संस्थिता। नमस्तस्यै नमस्तस्यै नमस्तस्यै नमो नमः॥"),
                    PujaStep(3, "Daily Path & Aarti", "दैनिक पाठ व आरती", "Recite Durga Chalisa, Siddha Kunjika Stotram or Durga Saptashati daily with family.", "प्रतिदिन सुबह-शाम दुर्गा चालीसा, कुंजिका स्तोत्र का पाठ करें और कपूर से आरती करें।"),
                    PujaStep(4, "Kanya Pujan (Ashtami/Navami)", "कन्या पूजन", "Wash feet of little girls, apply tilak, tie sacred thread, serve Halwa-Chana-Puri, gift fruit and coins.", "कन्याओं के पैर स्वच्छ जल से धोएं, रोली का टीका लगाएं, हलवा-चना खिलाएं और दक्षिणा देकर चरण स्पर्श करें।")
                ),
                supplies = listOf(
                    Supply("Barley Seeds (Jowar) & Clean Soil", "जौ के बीज और शुद्ध मिट्टी", "100 g", "100 ग्राम", "Setup", "घटस्थापना"),
                    Supply("Earthen Dish for Jowar", "मिट्टी का सकोरा / पात्र", "1 pc", "1 नग", "Setup", "घटस्थापना"),
                    Supply("Red Chunri & Shringar items", "लाल चुनरी व श्रृंगार सामग्री", "1 set", "1 सेट", "Deity", "मां का श्रृंगार"),
                    Supply("Black Chickpeas (Kale Chane)", "काले चने (भोग हेतु)", "250 g", "250 ग्राम", "Food", "भोग सामग्री"),
                    Supply("Semolina (Sooji) & Ghee for Halwa", "सूजी और देसी घी (हलवे हेतु)", "250 g each", "250-250 ग्राम", "Food", "भोग सामग्री"),
                    Supply("Durga Saptashati / Chalisa Book", "दुर्गा सप्तशती / चालीसा पुस्तक", "1 pc", "1 प्रति", "Books", "ग्रंथ")
                ),
                mantras = listOf(
                    MantraItem(
                        "सर्वमङ्गलमाङ्गल्ये शिवे सर्वार्थसाधिके। शरण्ये त्र्यम्बके गौरि नारायणि नमोऽस्तु ते॥",
                        "Sarvamangala Mangalye Shive Sarvartha Sadhike | Sharanye Tryambake Gauri Narayani Namo'stu Te",
                        "Salutations to the Supreme Goddess Narayani, who is the auspiciousness of all that is auspicious, the fulfiller of all pure desires.",
                        "हे सर्वमंगलदायिनी, कल्याणकारिणी, सब पुरुषार्थों को सिद्ध करने वाली नारायणी! आपको बारंबार नमस्कार है।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Leaving Akhand Diya drafty or unmonitored", "अखंड ज्योति को हवा या असुरक्षित स्थान पर छोड़ना", "Use a glass chimni (lamp cover) so breezes don't extinguish it, and replenish oil regularly.", "अखंड दीये पर कांच की चिमनी लगाएं और समय-समय पर घी/तेल डालते रहें।"),
                    CommonMistake("Treating Kanya Pujan as mere formality", "कन्या पूजन में औपचारिकता निभाना", "Treat the children with genuine warmth and love, as living embodiments of Shakti.", "बालिकाओं को साक्षात देवी का रूप मानकर प्रेम और आदर से भोजन कराएं।")
                )
            ),

            // 7. DUSSEHRA / VIJAYADASHAMI
            Festival(
                id = "dussehra",
                nameEn = "Dussehra (Vijayadashami)",
                nameHi = "दशहरा (विजयादशमी)",
                date2026En = "20 Oct 2026",
                date2026Hi = "20 अक्टूबर 2026",
                date2025En = "2 Oct 2025",
                date2027En = "10 Oct 2027",
                monthEn = "October",
                monthHi = "अक्टूबर",
                categoryEn = "Major",
                categoryHi = "प्रमुख उत्सव",
                emoji = "🏹",
                summaryEn = "Victory of good over evil, Lord Rama's vanquishing of Ravana, and Aparajita & Shami tree worship.",
                summaryHi = "अधर्म पर धर्म की विजय, रावण दहन, अपराजिता व शमी पूजन एवं नए शुभ संकल्पों का दिन।",
                significanceEn = "Vijayadashami celebrates Lord Rama slaying the 10-headed demon king Ravana, representing the destruction of lust, anger, ego, greed, and delusion. It is also the day Goddess Durga immersed her trident to vanquish Mahishasura after 9 fierce battles.",
                significanceHi = "दशहरा भगवान श्री राम द्वारा दशानन रावण के वध का पावन दिन है, जो काम, क्रोध, मद, लोभ जैसे दस विकारों पर विजय का प्रतीक है। इसी दिन मां दुर्गा ने भी महिषासुर का संहार किया था।",
                explainSimplyEn = "Dussehra is a celebration of courage and moral victory. Write down one bad habit or fear you wish to overcome, worship your tools of work (books, instruments, vehicles) in gratitude (Shastra Puja), and seek elders' blessings with Shami leaves.",
                explainSimplyHi = "दशहरा अपनी कमियों और बुराइयों को जीतने का दिन है। अपनी एक बुरी आदत छोड़ने का संकल्प लें, अपने कार्य के औजारों, किताबों या वाहन की पूजा (शस्त्र पूजन) करें और बड़ों से शमी पत्ते भेंट कर आशीर्वाद लें।",
                muhurat = MuhuratTiming(
                    nameEn = "Vijay Muhurat (Auspicious Victory Window)",
                    nameHi = "विजय मुहूर्त (सर्वकार्य सिद्धि)",
                    timeEn = "01:58 PM – 02:44 PM",
                    timeHi = "दोपहर 01:58 से 02:44 तक",
                    noteEn = "Any new venture, learning, or enterprise begun in Vijay Muhurat yields success.",
                    noteHi = "इस मुहूर्त में शुरू किया गया नया कार्य सदा सफल और कल्याणकारी होता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Clean car, bike, laptop, work tools, and instruments for Ayudha / Shastra puja.", "अपने वाहन, कार्य-उपकरण, किताबें आदि साफ कर सजाएं।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Perform Aparajita puja in afternoon, attend local Ramlila / Ravan Dahan at sunset, distribute Jalebi.", "दोपहर में विजय मुहूर्त में पूजा करें, शाम को रावण दहन देखें और जलेबी-फाफड़ा बांटें।")
                ),
                steps = listOf(
                    PujaStep(1, "Aparajita Puja", "अपराजिता देवी पूजन", "Draw eight-petaled lotus with sandalwood in northeastern direction and pray for invincibility of character.", "ईशान कोण में अपराजिता देवी का ध्यान कर रोली और अक्षत अर्पित करें।", "ॐ अपराजितायै नमः"),
                    PujaStep(2, "Shami Tree Worship", "शमी वृक्ष पूजन", "Offer water and deep to the sacred Shami tree; exchange Shami leaves as 'gold' with friends and elders.", "शमी के वृक्ष को जल व दीप अर्पित करें और पत्तों को 'सोने' के रूप में बड़ों को देकर आशीर्वाद लें।", "शमी शमयते पापं शमी लोहितकण्टका। धारिण्यर्जुनबाणानां रामस्य प्रियवादिनी॥"),
                    PujaStep(3, "Ayudha / Vehicle Pujan", "वाहन व उपकरण पूजन", "Apply swastika with vermilion on vehicles, laptops, or tools of your trade, offering garland and sweet.", "अपने वाहन और कार्य के उपकरणों पर रोली से स्वास्तिक बनाएं, फूल माला पहनाएं और मिठाई का भोग लगाएं।")
                ),
                supplies = listOf(
                    Supply("Shami Leaves or Plant branch", "शमी के पत्ते", "Few sprigs", "कुछ पत्तियां", "Sacred", "पवित्र पत्ते"),
                    Supply("Fresh Marigold Garlands for Vehicles", "वाहन हेतु गेंदे की माला", "2 pcs", "2 नग", "Decor", "माला"),
                    Supply("Jalebi or Besan Ladoo", "जलेबी या बेसन के लड्डू", "500 g", "500 ग्राम", "Sweets", "प्रसाद")
                ),
                mantras = listOf(
                    MantraItem(
                        "राम रामेति रामेति रमे रामे मनोरमे। सहस्रनाम तत्तुल्यं रामनाम वरानने॥",
                        "Rama Rameti Rameti Rame Rame Manorame | Sahasranama Tattulyam Ramanama Varanane",
                        "Chanting the holy name of Sri Rama just once is equal to reciting the thousand divine names of God.",
                        "श्री राम नाम का स्मरण समस्त मंगलों को देने वाला और हजारों नामों के समान कल्याणकारी है।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Treating Ravan Dahan merely as entertainment", "रावण दहन को केवल तमाशा समझना", "Reflect on eradicating personal arrogance and ego, which were Ravana's ultimate downfall.", "रावण के अहंकार को याद कर अपने भीतर के अभिमान को जलाने का संकल्प लें।")
                )
            ),

            // 8. MAHA SHIVRATRI
            Festival(
                id = "shivratri",
                nameEn = "Maha Shivratri",
                nameHi = "महाशिवरात्रि",
                date2026En = "15 Feb 2026",
                date2026Hi = "15 फ़रवरी 2026",
                date2025En = "26 Feb 2025",
                date2027En = "6 Mar 2027",
                monthEn = "February",
                monthHi = "फ़रवरी",
                categoryEn = "Shiva",
                categoryHi = "शिव आराधना",
                emoji = "🔱",
                summaryEn = "The grand cosmic night of Lord Shiva and Maa Parvati, celebrated with Abhishekam, fasting, and night vigil.",
                summaryHi = "भगवान शिव और माता पार्वती के दिव्य मिलन की पावन रात्रि, पंचामृत अभिषेक एवं ॐ नमः शिवाय का अखण्ड जप।",
                significanceEn = "Maha Shivratri marks the divine marriage of Shiva (pure consciousness) and Shakti (dynamic nature). It is also the night when Shiva performed the Tandava dance of cosmic creation and preservation. Fasting and staying alert on this night awakens inner spiritual power.",
                significanceHi = "महाशिवरात्रि शिव और शक्ति के पावन परिणय का महापर्व है। समुद्र मंथन के विष को पीकर नीलकंठ बने शिव के प्रति कृतज्ञता और रात्रि जागरण से चित्त शांत और एकाग्र होता है।",
                explainSimplyEn = "Shivratri is a tranquil digital and mental detox night. Shiva loves simplicity — no expensive rituals are required, just clean cold water, fresh milk, and green Belpatra offered with a calm, focused mind.",
                explainSimplyHi = "शिवरात्रि मन की शांति और एकाग्रता का पर्व है। भगवान भोलेनाथ को किसी आडंबर की जरूरत नहीं होती; केवल एक लोटा शुद्ध जल, थोड़ा कच्चा दूध और श्रद्धा से चढ़ाए गए बेलपत्र से वे प्रसन्न हो जाते हैं।",
                muhurat = MuhuratTiming(
                    nameEn = "Nishita Kaal Puja Muhurat (Midnight)",
                    nameHi = "निशीथ काल पूजा मुहूर्त (मध्यरात्रि)",
                    timeEn = "12:09 AM – 01:01 AM",
                    timeHi = "रात 12:09 से 01:01 तक",
                    noteEn = "The midnight Nishita Kaal is the most powerful time for Shiva Linga Abhishekam.",
                    noteHi = "निशीथ काल में शिवलिंग का जलाभिषेक और महामृत्युंजय जप अत्यंत फलदायी होता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Wash 21 or 108 unbroken Bel leaves (Bilva patra), buy raw unboiled milk, honey, and white flowers.", "21 या 108 बिना कटे-फटे बेलपत्र धोकर रखें, कच्चा दूध, शहद और सफेद पुष्प की व्यवस्था करें।"),
                    PrepTask("On the Day Morning", "दिन की सुबह", "Take bath, take vow of fasting (Phalahar/Nirjala), visit local Shiva temple for morning Jalabhishek.", "स्नान कर व्रत का संकल्प लें और शिवलिंग पर जल-दूध अर्पित करें।"),
                    PrepTask("Night Vigil", "रात्रि जागरण", "Perform the four prahar pujas or chant 'Om Namah Shivaya' with family at midnight.", "मध्यरात्रि में निशीथ काल में शिवलिंग का पंचामृत अभिषेक कर महामृत्युंजय मंत्र जपें।")
                ),
                steps = listOf(
                    PujaStep(1, "Sankalp & Dhyana", "संकल्प एवं शिव ध्यान", "Sit facing East or North, close eyes, take water in hand and dedicate your fast to Lord Shiva.", "उत्तर या पूर्व की ओर मुख कर बैठें, जल लेकर व्रत व पूजा का शांत मन से संकल्प लें।", "ॐ नमः शिवाय"),
                    PujaStep(2, "Panchamrit Abhishekam", "पंचामृत अभिषेक", "Pour raw milk, curd, honey, sugar, and ghee in gentle stream over the Shiva Linga, followed by clean Ganga water.", "शिवलिंग पर क्रमशः कच्चा दूध, दही, शहद, शक्कर और घी अर्पित करें, अंत में शीतल गंगाजल से स्नान कराएं।", "ॐ त्र्यम्बकं यजामहे सुगन्धिं पुष्टिवर्धनम्। उर्वारुकमिव बन्धनान्मृत्योर्मुक्षीय माऽमृतात्॥"),
                    PujaStep(3, "Bhasma & Chandan Tilak", "भस्म व चंदन लेप", "Apply sacred Vibhuti (bhasma) and white sandalwood paste with three fingers (Tripundra) on the Lingam.", "शिवलिंग पर तीन उंगलियों से त्रिपुण्ड्र चंदन और भस्म लगाएं।"),
                    PujaStep(4, "Offering Belpatra & Dhatura", "बेलपत्र, धतूरा व भांग अर्पण", "Offer smooth side of Belpatra facing downward onto the Lingam. Offer Dhatura fruit and white flowers.", "बेलपत्र के चिकने भाग को शिवलिंग की ओर रखकर अर्पित करें। धतूरा और सफेद मदार के पुष्प चढ़ाएं।", "त्रिदलं त्रिगुणाकारं त्रिनेत्रं च त्रियायुधम्। त्रिजन्मपापसंहारं एकबिल्वं शिवार्पणम्॥"),
                    PujaStep(5, "Aarti & Shiva Chalisa", "शिव आरती व चालीसा", "Light camphor diya, recite Shiva Chalisa and ring bells with reverence.", "कपूर का दीपक जलाकर 'जय शिव ओंकारा' आरती करें और शिव चालीसा का पाठ करें।")
                ),
                supplies = listOf(
                    Supply("Unbroken Belpatra (Bilva leaves)", "अखंडित बेलपत्र", "21 or 108 pcs", "21 या 108 नग", "Leaves", "पवित्र पत्ते"),
                    Supply("Raw Cow Milk (Unpasteurized)", "गाय का कच्चा दूध", "500 ml", "500 मिली", "Abhishek", "अभिषेक सामग्री"),
                    Supply("Ganga Jal (Holy Water)", "गंगाजल", "1 small bottle", "1 शीशी", "Abhishek", "अभिषेक सामग्री"),
                    Supply("Honey (Madhu) & Pure Ghee", "शुद्ध शहद और देसी घी", "50 g each", "50-50 ग्राम", "Abhishek", "अभिषेक सामग्री"),
                    Supply("Dhatura & Bhaang leaves", "धतूरा और भांग", "1 or 2 pcs", "1-2 नग", "Sacred", "पूजा सामग्री"),
                    Supply("White Sandalwood Paste & Bhasma", "सफेद चंदन और भस्म", "1 small pack", "1 डिब्बी", "Tilak", "चंदन"),
                    Supply("Bhimseni Camphor for Aarti", "भीमसेनी कपूर", "1 box", "1 डिब्बी", "Aarti", "आरती")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ नमः शिवाय॥",
                        "Om Namah Shivaya",
                        "I bow to Lord Shiva, the auspicious inner consciousness of all beings.",
                        "समस्त प्राणियों के कल्याणकर्ता परमपिता शिव को मेरा सादर नमन।"
                    ),
                    MantraItem(
                        "ॐ त्र्यम्बकं यजामहे सुगन्धिं पुष्टिवर्धनम्। उर्वारुकमिव बन्धनान्मृत्योर्मुक्षीय माऽमृतात्॥",
                        "Om Tryambakam Yajamahe Sugandhim Pushtivardhanam | Urvarukamiva Bandhanan Mrityor Mukshiya Ma'mritat",
                        "We meditate on the Three-Eyed Lord who nourishes all life. May He liberate us from death and fear, guiding us to immortality.",
                        "हम त्रिनेत्रधारी भगवान शिव की आराधना करते हैं जो जीवन को पुष्ट करते हैं। वे हमें मृत्यु और भय के बंधनों से मुक्त कर अमृतत्व प्रदान करें।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Offering Tulsi or Ketaki flowers to Shiva", "शिवजी पर तुलसी या केतकी के फूल चढ़ाना", "Tulsi, Ketaki flowers, and turmeric (haldi) are traditionally never offered on Shiva Linga; use white flowers, bilva, and sandalwood.", "शिवलिंग पर तुलसी, केतकी के फूल और हल्दी नहीं चढ़ाई जाती; केवल श्वेत पुष्प, बेलपत्र और भस्म अर्पित करें।"),
                    CommonMistake("Doing full circular parikrama around Shiva Lingam", "शिवलिंग की पूरी परिक्रमा करना", "Never cross the water outlet channel (Nirmali/Somasutra). Walk up to the outlet, turn back, and repeat.", "शिवलिंग की आधी परिक्रमा (सोमसूत्र को लांघे बिना) की जाती है।")
                )
            ),

            // 9. KRISHNA JANMASHTAMI
            Festival(
                id = "janmashtami",
                nameEn = "Krishna Janmashtami",
                nameHi = "श्रीकृष्ण जन्माष्टमी",
                date2026En = "4 Sep 2026",
                date2026Hi = "4 सितंबर 2026",
                date2025En = "16 Aug 2025",
                date2027En = "25 Aug 2027",
                monthEn = "September",
                monthHi = "सितंबर",
                categoryEn = "Krishna",
                categoryHi = "कृष्ण जन्मोत्सव",
                emoji = "🪈",
                summaryEn = "Midnight celebration of the divine birth of Lord Krishna with decorated cradles, panchamrit snan, and Makhan-Mishri.",
                summaryHi = "रोहिणी नक्षत्र की मध्यरात्रि में भगवान श्रीकृष्ण के जन्मोत्सव की गूंज, पालना झुलाना और माखन-मिश्री भोग।",
                significanceEn = "Lord Krishna incarnated at midnight in the prison cell of Mathura on Ashtami tithi of Bhadrapada to vanquish oppression and establish Dharma. Devotees fast through the day, decorate a baby cradle (Palna), and celebrate birth with joyous conch shells at midnight.",
                significanceHi = "द्वापर युग में धर्म की स्थापना और कंस के अत्याचारों के अंत हेतु भाद्रपद कृष्ण अष्टमी की आधी रात को भगवान श्रीकृष्ण का प्राकट्य हुआ। भक्त दिनभर उपवास रखते हैं और रात 12 बजे शंख व घंटी बजाकर जन्मोत्सव मनाते हैं।",
                explainSimplyEn = "Janmashtami is like celebrating the birth of the most playful, lovable divine baby at home. Dress up a small idol of Bal Gopal, decorate a small cradle with flowers, make delicious butter and rock sugar (makhan-mishri), and rock the cradle tenderly at midnight.",
                explainSimplyHi = "जन्माष्टमी बाल गोपाल के जन्मोत्सव का आनंदमय पर्व है। लड्डू गोपाल जी का सुंदर श्रृंगार करें, झूले को फूलों से सजाएं, रात 12 बजे पंचामृत से स्नान कराकर झूले में झुलाएं और माखन-मिश्री व पंजीरी का भोग लगाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Nishita Kaal Janmotsav Muhurat",
                    nameHi = "निशीथ काल जन्मोत्सव मुहूर्त",
                    timeEn = "11:58 PM – 12:44 AM",
                    timeHi = "रात 11:58 से 12:44 तक",
                    noteEn = "Midnight 46-minute window when Krishna was born under Rohini Nakshatra.",
                    noteHi = "इसी पावन समय पर मध्यरात्रि में कान्हा का जन्मोत्सव मनाया जाता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("2 Days Before", "2 दिन पहले", "Prepare coriander seed powder prasad (Dhaniya Panjiri), buy yellow clothes and peacock feather for Bal Gopal.", "धनिया पंजीरी तैयार करें, लड्डू गोपाल जी की पीली पोशाक, मुकुट और मोरपंख की व्यवस्था करें।"),
                    PrepTask("On the Day Morning", "दिन की सुबह", "Clean puja room, decorate cradle with jasmine and marigolds, observe fast with fruits.", "झूले को फूलों से सजाएं, फलाहारी व्रत रखें और घर में भजन-कीर्तन का वातावरण बनाएं।"),
                    PrepTask("Midnight", "मध्यरात्रि 12 बजे", "Bathe Bal Gopal in panchamrit, dress in new clothes, blow conch shell, offer makhan-mishri, rock the cradle.", "रात 12 बजे शंख बजाकर जन्मोत्सव मनाएं, पंचामृत स्नान कराकर झूले में झुलाएं।")
                ),
                steps = listOf(
                    PujaStep(1, "Midnight Aavahan & Conch Blowing", "मध्यरात्रि प्राकट्य व शंखनाद", "At exactly 12:00 midnight, ring bells and blow the Shankh to announce the arrival of Bal Krishna.", "रात 12 बजते ही शंख, घंटी और ताली बजाकर बाल कृष्ण के प्राकट्य का जयकारा लगाएं।", "नन्द के आनंद भयो, जय कन्हैया लाल की! हाथी घोड़ा पालकी, जय कन्हैया लाल की!"),
                    PujaStep(2, "Panchamrit Snan", "पंचामृत अभिषेक", "Bathe the idol of Bal Gopal gently in a decorative plate with milk, curd, honey, ghee, sugar, and Ganga water.", "लड्डू गोपाल को तांबे या चांदी की थाली में दूध, दही, शहद, घी और गंगाजल से प्रेमपूर्वक स्नान कराएं।"),
                    PujaStep(3, "Shringar & Peacock Crown", "श्रृंगार व मोर मुकुट", "Dry the idol with a soft cloth, apply chandan tilak, dress in yellow silk clothes, place peacock feather in crown.", "मुलायम वस्त्र से पोंछकर पीताम्बरी वस्त्र पहनाएं, चंदन लगाएं और मोरपंख युक्त मुकुट व बांसुरी सजाएं।"),
                    PujaStep(4, "Palna Jhula & Bhog Offering", "झूला झुलाना व भोग अर्पण", "Place Bal Gopal in the decorated swing, rock the swing with a string, offer Makhan-Mishri and Panjiri.", "बाल गोपाल को झूले में विराजमान कर प्रेम से झूला झुलाएं, माखन-मिश्री, पंजीरी और खीरे का भोग लगाएं।"),
                    PujaStep(5, "Aarti & Fast Breaking", "आरती व पारण", "Perform 'Aarti Kunj Bihari Ki' and break fast with prasad and charnamrit.", "कुंजबिहारी जी की आरती गाएं, चरणामृत ग्रहण करें और फलाहार से व्रत खोलें।")
                ),
                supplies = listOf(
                    Supply("Bal Gopal Idol & Miniature Swing (Jhula)", "लड्डू गोपाल जी की प्रतिमा व झूला", "1 set", "1 सेट", "Idol", "प्रतिमा व झूला"),
                    Supply("Yellow Silk Dress, Crown & Flute", "पीली पोशाक, मुकुट व बांसुरी", "1 set", "1 सेट", "Dress", "पोशाक"),
                    Supply("Fresh Cow White Butter (Makhan) & Mishri", "ताजा सफेद माखन और मिश्री", "1 bowl", "1 कटोरी", "Bhog", "भोग"),
                    Supply("Coriander Panjiri (Dhaniya Panjiri)", "धनिया पंजीरी", "250 g", "250 ग्राम", "Bhog", "भोग"),
                    Supply("Peacock Feather (Mor Pankh)", "सुंदर मोरपंख", "1 pc", "1 नग", "Decor", "श्रृंगार"),
                    Supply("Panchamrit Ingredients", "पंचामृत सामग्री", "1 bowl", "1 कटोरी", "Abhishek", "स्नान")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ नमो भगवते वासुदेवाय॥",
                        "Om Namo Bhagavate Vasudevaya",
                        "I bow to the Supreme Lord Vasudeva Krishna, the light of wisdom and love.",
                        "परमब्रह्म भगवान श्रीकृष्ण को मेरा कोटि-कोटि वंदन।"
                    ),
                    MantraItem(
                        "हरे कृष्ण हरे कृष्ण कृष्ण कृष्ण हरे हरे। हरे राम हरे राम राम राम हरे हरे॥",
                        "Hare Krishna Hare Krishna Krishna Krishna Hare Hare | Hare Rama Hare Rama Rama Rama Hare Hare",
                        "The Maha-Mantra uniting the soul in divine ecstasy and unconditional love.",
                        "दिव्य महामंत्र जो मन को समस्त चिंताओं से मुक्त कर आनंद की अनुभूति कराता है।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Rocking the cradle roughly", "झूले को जोर से झटका देकर झुलाना", "Rock the swing very gently and with soft affection, just as you would rock a sleeping newborn infant.", "झूले को हमेशा अत्यंत कोमल भाव से हल्के हाथों से झुलाना चाहिए।")
                )
            ),

            // 10. GANESH CHATURTHI
            Festival(
                id = "ganesh",
                nameEn = "Ganesh Chaturthi (Vinayaka Chavithi)",
                nameHi = "गणेश चतुर्थी (विनायक चतुर्थी)",
                date2026En = "14 Sep 2026",
                date2026Hi = "14 सितंबर 2026",
                date2025En = "27 Aug 2025",
                date2027En = "5 Sep 2027",
                monthEn = "September",
                monthHi = "सितंबर",
                categoryEn = "Ganesha",
                categoryHi = "गणेशोत्सव",
                emoji = "🐘",
                summaryEn = "Welcoming Vighnaharta Lord Ganesha into our homes with eco-friendly clay idols, 21 Durva blades, and steamed Modaks.",
                summaryHi = "विघ्नहर्ता गणपति बप्पा का घर में सप्रेम स्वागत, 21 दूर्वा दल अर्पण और मोदक का प्रिय भोग।",
                significanceEn = "Ganesh Chaturthi celebrates the arrival of Lord Ganesha to earth from Kailash mountain. As the deity of beginnings, intellect, and remover of obstacles, his presence brings clarity, unity, and joyful celebration to every home.",
                significanceHi = "भाद्रपद शुक्ल चतुर्थी को विघ्नहर्ता भगवान गणेश जी का प्राकट्य हुआ। किसी भी शुभ कार्य की शुरुआत गणेश वंदना से होती है। बप्पा की स्थापना से घर में रिद्धि-सिद्धि, सुख-शांति और सद्बुद्धि का वास होता है।",
                explainSimplyEn = "Welcome Ganesha as an esteemed, loved guest in your home. Choose an eco-friendly clay idol that can dissolve naturally in water. Offer 21 blades of fresh green Durva grass, light a lamp, recite the Atharvashirsha, and enjoy sweet modaks.",
                explainSimplyHi = "गणपति बप्पा को अपने घर के सबसे प्रिय अतिथि के रूप में लाएं। मिट्टी की इको-फ्रेंडली प्रतिमा चुनें जो विसर्जन पर प्रकृति को नुकसान न पहुंचाए। उन्हें 21 दूर्वा घास, लाल सिंदूर और 11 या 21 मोदक प्रेम से खिलाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Madhyahna Ganesha Sthapana Muhurat",
                    nameHi = "मध्याह्न गणेश स्थापना मुहूर्त",
                    timeEn = "11:08 AM – 01:34 PM",
                    timeHi = "दोपहर 11:08 से 01:34 तक",
                    noteEn = "Lord Ganesha was born during Madhyahna (midday) hours.",
                    noteHi = "गणेश जी का जन्म मध्याह्न काल में हुआ था, अतः इसी समय स्थापना श्रेष्ठ है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("3 Days Before", "3 दिन पहले", "Book clay (Shaadu maati) idol, arrange modak mould and red flowers (hibiscus).", "मिट्टी की प्रतिमा लाएं, मोदक बनाने का सांचा और लाल गुड़हल के फूलों की व्यवस्था करें।"),
                    PrepTask("1 Day Before", "1 दिन पहले", "Clean puja chowki, hang yellow marigold toran, prepare modak filling (coconut and jaggery).", "चौकी सजाएं, फूलों का तोरण लगाएं और मोदक हेतु नारियल-गुड़ का मिश्रण तैयार रखें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Bring idol covering face with cloth, perform welcome aarti at threshold, establish in Madhyahna muhurat.", "वस्त्र से मुख ढककर बप्पा को लाएं, द्वार पर आरती कर अंदर लाएं और मध्याह्न में स्थापना करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Aavahan & Prana Pratishtha", "आवाहन एवं प्राण प्रतिष्ठा", "Uncover the idol with joy, invite Lord Ganesha into the murti with pure devotion.", "बप्पा की प्रतिमा से कपड़ा हटाएं और अक्षत-पुष्प हाथ में लेकर मूर्ति में देवत्व का आवाहन करें।", "ॐ गं गणपतये नमः। अस्य प्राण प्रतिष्ठापनात् सुप्रतिष्ठितो भव।"),
                    PujaStep(2, "Shodashopachara Snan & Vastram", "स्नान व वस्त्र अर्पण", "Offer water droplets with durva, apply red sindoor and chandan, drape red vastram or kalava.", "दूर्वा से जल छिड़ककर सांकेतिक स्नान कराएं, लाल सिंदूर का तिलक लगाएं और लाल जनेऊ या वस्त्र अर्पित करें।"),
                    PujaStep(3, "Offering 21 Durva Blades", "21 दूर्वा दल अर्पण", "Wash 21 green three-pronged Durva blades, offer them at Ganesha's holy feet in pairs.", "21 हरी दूर्वा को साफ पानी से धोकर दो-दो के जोड़े में गणेश जी के चरणों में समर्पित करें।", "ॐ गणाधिपाय नमः दूर्वादलं समर्पयामि"),
                    PujaStep(4, "Modak & Laddu Naivedya", "मोदक व लड्डू नैवेद्य", "Offer 11 or 21 warm steamed Ukadiche Modak or Motichoor Laddus with betel leaves.", "11 या 21 उकडीचे मोदक या मोतीचूर के लड्डू, पान का बीड़ा और फल श्रद्धापूर्वक अर्पित करें।"),
                    PujaStep(5, "Aarti & Atharvashirsha", "आरती व अथर्वशीर्ष", "Sing 'Sukh Karta Dukh Harta' aarti and recite Ganapati Atharvashirsha with folded hands.", "सपरिवार 'सुखकर्ता दुखहर्ता' आरती गाएं और बप्पा से सभी विघ्न दूर करने की प्रार्थना करें।")
                ),
                supplies = listOf(
                    Supply("Eco-Friendly Clay Ganesha Idol", "मिट्टी की इको-फ्रेंडली गणेश प्रतिमा", "1 pc", "1 नग", "Idol", "प्रतिमा"),
                    Supply("Fresh Green Durva Grass", "ताजी हरी दूर्वा", "21 blades", "21 तिनके", "Sacred", "पवित्र दूर्वा"),
                    Supply("Red Hibiscus Flowers (Gudhal)", "लाल गुड़हल के फूल", "5 to 11 pcs", "5 से 11 नग", "Flowers", "फूल"),
                    Supply("Steamed Modak / Motichoor Laddus", "मोदक या मोतीचूर लड्डू", "11 or 21 pcs", "11 या 21 नग", "Bhog", "भोग"),
                    Supply("Red Sindoor (Vermilion) & Janeu", "लाल सिंदूर और जनेऊ", "1 small pack", "1 डिब्बी", "Puja Items", "पूजा सामग्री"),
                    Supply("Betel Leaves (Paan) & Supari", "पान के पत्ते और सुपारी", "5 sets", "5 जोड़े", "Puja Items", "पूजा सामग्री")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ गं गणपतये नमः॥",
                        "Om Gam Ganapataye Namah",
                        "My salutations to the primal lord Ganesha, bestower of intellect and remover of obstacles.",
                        "समस्त विघ्नों का नाश करने वाले बुद्धिप्रदाता गणपति को प्रणाम।"
                    ),
                    MantraItem(
                        "वक्रतुण्ड महाकाय सूर्यकोटि समप्रभ। निर्विघ्नं कुरु मे देव सर्वकार्येषु सर्वदा॥",
                        "Vakratunda Mahakaya Suryakoti Samaprabha | Nirvighnam Kuru Me Deva Sarvakaryeshu Sarvada",
                        "O immense Lord of radiant aura, please bless all our noble endeavours to be hurdle-free forever.",
                        "करोड़ों सूर्यों के समान तेजस्वी विघ्नहर्ता, मेरे सभी कार्यों को सदा निर्विघ्न संपन्न करें।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Looking at the Moon on Ganesh Chaturthi night", "चतुर्थी की रात चंद्रमा के दर्शन करना", "According to tradition, looking at the moon on Bhadrapada Chaturthi invites false blame (Mithya Kalanka). If viewed accidentally, recite the Syamantaka jewel mantra.", "इस रात चंद्रमा के दर्शन से बचने की परंपरा है ताकि मिथ्या कलंक से बचा जा सके।"),
                    CommonMistake("Using Plaster of Paris (PoP) idols", "पीओपी (प्लास्टर ऑफ पेरिस) की मूर्ति लाना", "Always choose soluble clay (mitti) that dissolves cleanly in water without polluting water bodies.", "पर्यावरण की रक्षा हेतु हमेशा मिट्टी से बनी मूर्ति ही स्थापित करें।")
                )
            ),

            // 11. HOLI & HOLIKA DAHAN
            Festival(
                id = "holi",
                nameEn = "Holi & Holika Dahan",
                nameHi = "होली एवं होलिका दहन",
                date2026En = "3–4 Mar 2026",
                date2026Hi = "3–4 मार्च 2026",
                date2025En = "13–14 Mar 2025",
                date2027En = "22–23 Mar 2027",
                monthEn = "March",
                monthHi = "मार्च",
                categoryEn = "Major",
                categoryHi = "प्रमुख उत्सव",
                emoji = "🎨",
                summaryEn = "Festival of colors, spring renewal, bonfires of faith (Holika Dahan), and sweet Gujiyas.",
                summaryHi = "रंगों और उल्लास का महापर्व, भक्त प्रह्लाद की रक्षा की स्मृति में होलिका दहन और गुजिया की मिठास।",
                significanceEn = "Holika Dahan commemorates young devotee Prahlad surviving the fire while demoness Holika perished, proving faith conquers tyranny. The next day (Dhulandi/Rangwali Holi) welcomes spring by breaking social divides with vibrant herbal gulal.",
                significanceHi = "होलिका दहन असत्य और अहंकार पर भक्ति की विजय का प्रतीक है। भगवान नृसिंह ने प्रह्लाद की रक्षा की और होलिका जल गई। अगले दिन धुलेंडी पर सभी भेदभाव भुलाकर अबीर-गुलाल से वसंत का स्वागत किया जाता है।",
                explainSimplyEn = "Day 1 is Holika Dahan: light an evening bonfire to burn away old grudges, negativity, and laziness. Day 2 is Rangwali Holi: play with natural herbal colors, hug friends and family, and relish crisp sweet gujiyas.",
                explainSimplyHi = "पहले दिन शाम को होलिका दहन में अपने मन के द्वेष, आलस्य और नकारात्मकता की आहुति दें। दूसरे दिन हर्बल गुलाल से होली खेलें, गिले-शिकवे दूर करें और पारंपरिक गुजिया का आनंद लें।",
                muhurat = MuhuratTiming(
                    nameEn = "Holika Dahan Muhurat (Avoid Bhadra)",
                    nameHi = "होलिका दहन मुहूर्त (भद्रा रहित)",
                    timeEn = "06:27 PM – 08:52 PM",
                    timeHi = "शाम 06:27 से 08:52 तक",
                    noteEn = "Holika Dahan is performed strictly after Bhadra Mukha ends during Pradosh.",
                    noteHi = "होलिका दहन हमेशा भद्रा पुच्छ या भद्रा समाप्त होने के बाद प्रदोष काल में किया जाता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("3 Days Before", "3 दिन पहले", "Buy organic herbal gulal (cornstarch/marigold based), coconut, raw grains (wheat/gram ear heads).", "हर्बल गुलाल, नारियल, गेहूं और चने की ताजी बालियां (होलिका में भूनने हेतु) लाएं।"),
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare homemade Gujiyas (mawa, dry fruits, cardamom) and Thandai syrup.", "मावा और मेवे से भरी खस्ता गुजिया बनाएं और ठंडाई का मिश्रण तैयार रखें।"),
                    PrepTask("Holika Evening", "दहन की शाम", "Make cow dung garlands (Gulari/Badkulle), offer raw cotton thread around Holika.", "बड़कूले की माला पहनाएं, कच्चे सूत से 3 या 7 बार परिक्रमा कर नारियल अर्पित करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Holika Pujan", "होलिका पूजन", "Sprinkle water on the bonfire structure, offer turmeric, roli, unbroken rice, and sweets.", "होलिका के पास जल छिड़कें, रोली, अक्षत, पुष्प और बताशे अर्पित करें।"),
                    PujaStep(2, "Circumambulation & Raw Thread", "सूत लपेटना व परिक्रमा", "Wrap raw cotton thread 3 or 7 times around Holika while walking clockwise praying for family peace.", "कच्चा सूत हाथ में लेकर होलिका की 3 या 7 बार परिक्रमा करें और परिवार की सुरक्षा की प्रार्थना करें।"),
                    PujaStep(3, "Roasting Wheat Ears", "अन्न की बालियां भूनना", "Roast ears of fresh wheat or chickpeas in the sacred flames and distribute as toasted prasad.", "गेहूं और चने की ताजी बालियों को पवित्र अग्नि में भूनकर प्रसाद रूप में घर के सभी सदस्यों को खिलाएं।"),
                    PujaStep(4, "Rangwali Holi Morning", "रंगोत्सव / धुलेंडी", "First apply a pinch of gulal to God's feet and elders' feet, then play safely with family.", "सबसे पहले भगवान और घर के बड़ों के चरणों में गुलाल लगाकर आशीर्वाद लें, फिर हर्बल रंगों से खेलें।")
                ),
                supplies = listOf(
                    Supply("Organic Herbal Gulal (Pink, Yellow, Green)", "हर्बल गुलाल (गुलाबी, पीला, हरा)", "3 packs", "3 पैकेट", "Colors", "रंग"),
                    Supply("Raw Cotton Thread (Kachha Soot)", "कच्चा सूत", "1 roll", "1 गट्टी", "Holika", "दहन सामग्री"),
                    Supply("Garland of Cow Dung Cakes (Badkulle)", "गोबर के बड़कूलों की माला", "1 pc", "1 माला", "Holika", "दहन सामग्री"),
                    Supply("Fresh Wheat / Gram earheads (Baaliyan)", "गेहूं/चने की ताजी बालियां", "1 bunch", "1 गुच्छा", "Harvest", "अन्न"),
                    Supply("Homemade Gujiya", "पारंपरिक मावा गुजिया", "500 g", "500 ग्राम", "Sweets", "मिठाई"),
                    Supply("Thandai with Dry Fruits", "शाही ठंडाई", "1 bottle", "1 बोतल", "Drinks", "पेय")
                ),
                mantras = listOf(
                    MantraItem(
                        "अहकूटा भयत्रस्तैः कृता त्वं होलि बालिशैः। अतस्त्वां पूजयिष्यामि भूते भूतिप्रदा भव॥",
                        "Ahakuta Bhayatrastaih Krita Tvam Holi Balishaih | Atastvam Poojayishyami Bhoote Bhootiprada Bhava",
                        "O sacred flame of Holi, you dispel all fear and negativity; we worship you to bestow vitality, health, and peace upon all.",
                        "हे होलिका! आप समस्त भयों का नाश करने वाली हैं, हम आपकी पूजा करते हैं ताकि घर में सुख और आरोग्यता की वृद्धि हो।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Using harsh chemical or silver industrial paints", "रसायनिक पक्के रंगों या पेंट का प्रयोग", "Chemical colors cause severe eye damage and skin allergies. Use only skin-friendly herbal cornstarch colors.", "रासायनिक रंगों से त्वचा और आंखों को नुकसान पहुंचता है; केवल हर्बल गुलाल का ही उपयोग करें।")
                )
            ),

            // 12. KARVA CHAUTH
            Festival(
                id = "karva",
                nameEn = "Karva Chauth",
                nameHi = "करवा चौथ",
                date2026En = "29 Oct 2026",
                date2026Hi = "29 अक्टूबर 2026",
                date2025En = "10 Oct 2025",
                date2027En = "19 Oct 2027",
                monthEn = "October",
                monthHi = "अक्टूबर",
                categoryEn = "Major",
                categoryHi = "प्रमुख उत्सव",
                emoji = "🌕",
                summaryEn = "A loving celebration of marriage, long life, and devotion marked by a day-long fast and moon sighting through a sieve.",
                summaryHi = "पति की दीर्घायु और अखंड सौभाग्य का निर्जला व्रत, शाम की पावन कथा और छलनी से चंद्र दर्शन।",
                significanceEn = "Celebrated on Kartik Krishna Chaturthi, married women observe a Nirjala fast (without food or water) from sunrise until moonrise, praying to Goddess Parvati, Lord Shiva, and Kartikeya for the longevity, health, and prosperity of their spouse.",
                significanceHi = "कार्तिक कृष्ण चतुर्थी को सुहागिन महिलाएं पति की लंबी उम्र, स्वास्थ्य और सुखद दांपत्य जीवन के लिए सूर्योदय से लेकर चंद्रोदय तक निर्जला व्रत रखती हैं। मां गौरी और शिव परिवार की पूजा कर रात को चंद्रमा को अर्घ्य देकर व्रत खोला जाता है।",
                explainSimplyEn = "Karva Chauth is an expression of marital affection and partnership. Wake up early for the pre-dawn meal (Sargi), dress in festive red, listen to the inspirational Karva Chauth story with other women in the afternoon, and view the moon through a decorative sieve before taking your first sip of water.",
                explainSimplyHi = "करवा चौथ दांपत्य प्रेम और त्याग का सुंदर उत्सव है। भोर में सास द्वारा दी गई सरगी खाएं, दिनभर सात्विक व शांत भाव से रहें, दोपहर में करवा चौथ की कथा सुनें और रात में चंद्रमा के दर्शन कर पति के हाथ से जल पीकर व्रत पूर्ण करें।",
                muhurat = MuhuratTiming(
                    nameEn = "Karva Chauth Puja Muhurat (Evening)",
                    nameHi = "करवा चौथ पूजा मुहूर्त (सायंकाल)",
                    timeEn = "05:46 PM – 07:02 PM",
                    timeHi = "शाम 05:46 से 07:02 तक",
                    noteEn = "Estimated Moonrise: 08:15 PM (varies by city coordinates).",
                    noteHi = "पूजा शाम को कथा के साथ होती है; चंद्रोदय अनुमानित रात 08:15 बजे होगा।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Apply mehendi, prepare traditional red attire, assemble sieve (channi), earthen pot (Karva), and Sargi items.", "हाथों में मेहंदी लगाएं, लाल वस्त्र, पूजा की छलनी, मिट्टी का करवा और सरगी तैयार रखें।"),
                    PrepTask("Pre-Dawn (04:30 AM)", "भोर 04:30 बजे", "Eat nourishing Sargi (fruits, dry fruits, feniyan, coconut water) before sunrise, drink plenty of water.", "सूर्योदय से पूर्व पौष्टिक सरगी ग्रहण करें और भरपूर पानी पिएं।"),
                    PrepTask("Afternoon (05:00 PM)", "दोपहर 05:00 बजे", "Listen to Karva Chauth Vrat Katha with elders, rotate the Karva thalis in circle (Ferna).", "सुहागिनों के साथ करवा चौथ की कथा सुनें और थाली व करवे को सात बार फेरें।"),
                    PrepTask("Moonrise", "चंद्रोदय के समय", "Offer Arghya to the moon with water and milk, view moon through sieve, then spouse, and drink water.", "चंद्रमा को जल का अर्घ्य दें, छलनी से चांद और पति का मुख देखकर पति के हाथ से जल ग्रहण करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Sargi Intake", "सरगी सेवन", "Wake up in Brahma Muhurat before sunrise, eat fruits, kheer/feniyan, and dry fruits gifted by mother-in-law.", "सूर्योदय से पहले सास द्वारा दी गई सरगी का सेवन करें।"),
                    PujaStep(2, "Evening Vrat Katha", "करवा चौथ कथा", "Wear red dress and traditional jewellery, sit with Karva filled with wheat/water, listen to Veeravati's story.", "लाल जोड़े में सजकर करवे में जल या गेहूं भरकर बैठें और चौथ माता की पावन कथा सुनें।"),
                    PujaStep(3, "Karva Phera (Thali exchange)", "थाली फेरना", "Pass the decorated Karva thali seven times chanting the traditional blessing verse.", "सात बार करवा फेरें: 'ले सुहागन ले करवा, दे सुहागन दे करवा'।"),
                    PujaStep(4, "Chandra Darshan & Arghya", "चंद्र दर्शन व अर्घ्य", "When the moon rises, light a small diya on the sieve. Offer water, raw milk, and rice grains to Chandra Dev.", "चंद्रमा निकलने पर छलनी पर दीपक रखकर चंद्र दर्शन करें, अर्घ्य में जल, कच्चा दूध और अक्षत अर्पित करें।", "ॐ सों सोमाय नमः। क्षीरोदार्णव सम्भूत अत्रिनेत्र समुद्भव। गृहाणार्घ्यं शशाङ्केश रोहिण्यासहितो मम॥"),
                    PujaStep(5, "Viewing Spouse & Fast Breaking", "पति दर्शन व पारण", "Look through the sieve at the moon and then at your spouse. Take your first sip of water and sweet from spouse's hands, then touch their feet.", "छलनी से पहले चांद को फिर पति को देखें, पति के हाथ से जल पीकर व्रत खोलें और बड़ों के चरण स्पर्श करें।")
                ),
                supplies = listOf(
                    Supply("Earthen or Brass Karva with spout", "मिट्टी या पीतल का करवा", "2 pcs", "2 नग", "Karva", "करवा"),
                    Supply("Decorative Puja Sieve (Channi)", "पूजा की छलनी", "1 pc", "1 नग", "Essentials", "छलनी"),
                    Supply("Small Diya for Sieve", "छलनी पर रखने हेतु दीपक", "1 pc", "1 नग", "Lamps", "दीपक"),
                    Supply("Raw Milk & Clean Water for Arghya", "अर्घ्य हेतु कच्चा दूध व जल", "1 small pot", "1 लोटा", "Arghya", "अर्घ्य"),
                    Supply("Sargi items (Dry fruits, Feniyan, Sweets)", "सरगी सामग्री (मेवे, फेनियां, फल)", "1 basket", "1 टोकरी", "Sargi", "सरगी"),
                    Supply("Baya (Gift for Mother-in-law)", "बायना (सास के लिए उपहार व वस्त्र)", "1 thali", "1 थाली", "Baya", "बायना")
                ),
                mantras = listOf(
                    MantraItem(
                        "नमः शिवायै शर्वाण्यै सौभाग्यं सन्ततिं शुभाम्। प्रयच्छ कान्तिं कल्याणि पतिं मे देहि जीवतम्॥",
                        "Namah Shivayai Sharvanyai Saubh భాగyam Santatim Shubham | Prayachha Kantim Kalyani Patim Me Dehi Jeevatam",
                        "Salutations to Mother Parvati, consort of Shiva, bless me with auspicious fortune, noble children, and grant long life to my husband.",
                        "हे मां पार्वती! मुझे अखंड सौभाग्य, संतान सुख और मेरे पति को दीर्घायु प्रदान करें।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Drinking cold refrigerated water immediately after moonrise", "व्रत के बाद तुरंत फ्रिज का ठंडा पानी पीना", "Drink lukewarm water with a pinch of sugar or lemon to soothe the empty stomach gently.", "दिनभर के निर्जला व्रत के बाद गुनगुना पानी या नींबू-शहद का पानी घूंट-घूंट कर पिएं।")
                )
            ),

            // 13. CHHATH PUJA
            Festival(
                id = "chhath",
                nameEn = "Chhath Puja (Surya Shashthi)",
                nameHi = "छठ पूजा (सूर्य षष्ठी महापर्व)",
                date2026En = "15–18 Nov 2026",
                date2026Hi = "15–18 नवंबर 2026",
                date2025En = "25–28 Oct 2025",
                date2027En = "3–6 Nov 2027",
                monthEn = "November",
                monthHi = "नवंबर",
                categoryEn = "Major",
                categoryHi = "महापर्व",
                emoji = "☀️",
                summaryEn = "The magnificent 4-day Mahaparv of purity, discipline, and devotion to the Sun God (Surya Dev) and Chhathi Maiya.",
                summaryHi = "चार दिवसीय लोक आस्था का महापर्व: नहाय-खाय, खरना, संध्या अर्घ्य और उषा अर्घ्य।",
                significanceEn = "Chhath is unique in Indian culture because it worships both the setting Sun (gratitude for the day past) and the rising Sun (welcoming new life). Originating from Vedic times, it honors the Sun as the source of all energy and Chhathi Maiya (Goddess Shashthi) as the protector of children.",
                significanceHi = "छठ पूजा संसार का एकमात्र ऐसा पर्व है जिसमें अस्ताचलगामी (डूबते) सूर्य को पहला अर्घ्य और उगते सूर्य को दूसरा अर्घ्य दिया जाता है। यह पर्व पूर्ण स्वच्छता, पर्यावरण, नदी और प्रकृति के प्रति अगाध निष्ठा का प्रतीक है।",
                explainSimplyEn = "A four-day spiritual discipline: Day 1 (Nahay Khay) clean house and eat bottle gourd-rice; Day 2 (Kharna) fast all day and eat jaggery kheer at dusk; Day 3 (Sandhya Arghya) stand in river/pool at sunset offering bamboo soop of Thekua; Day 4 (Usha Arghya) greet rising sun and complete the fast.",
                explainSimplyHi = "चार दिनों की साधना: पहला दिन 'नहाय-खाय' (साफ-सफाई व कद्दू-भात); दूसरा दिन 'खरना' (गुड़ की खीर का प्रसाद); तीसरा दिन 'संध्या अर्घ्य' (डूबते सूर्य को सूप से अर्घ्य); चौथा दिन 'उषा अर्घ्य' (उगते सूर्य को अर्घ्य देकर पारण)।",
                muhurat = MuhuratTiming(
                    nameEn = "Sunset & Sunrise Arghya Timings",
                    nameHi = "संध्या व उषा अर्घ्य समय",
                    timeEn = "Sunset: 05:28 PM | Sunrise: 06:45 AM",
                    timeHi = "संध्या अर्घ्य: 05:28 PM | उषा अर्घ्य: 06:45 AM",
                    noteEn = "Stand waist-deep in clean water during actual sunset and sunrise.",
                    noteHi = "नदी, तालाब या घर में बनाए गए स्वच्छ जल कुंड में खड़े होकर अर्घ्य दें।"
                ),
                preparationTimeline = listOf(
                    PrepTask("Day 1 (Nahay-Khay)", "पहला दिन (नहाय-खाय)", "Thorough cleaning of kitchen and utensils, eat pure Kaddu-Bhat (bottle gourd with rice) cooked in bronze/earthen pots.", "रसोई की पूर्ण पवित्रता, सेंधा नमक में बना कद्दू-भात और चने की दाल का सात्विक भोजन।"),
                    PrepTask("Day 2 (Kharna)", "दूसरा दिन (खरना)", "Nirjala fast through the day; prepare Rasiya (jaggery kheer) on clay stove with mango wood at dusk.", "दिनभर निर्जला उपवास, शाम को मिट्टी के चूल्हे पर आम की लकड़ी से गुड़ की रसिया-खीर बनाएं।"),
                    PrepTask("Day 3 (Sandhya Arghya)", "तीसरा दिन (संध्या अर्घ्य)", "Fry pure ghee Thekua, arrange bamboo soop and daura with sugarcane, ginger, fruits. Stand in water at sunset.", "घी में ठेकुआ बनाएं, बांस के सूप में फल, गन्ना और दीया सजाकर सूर्यास्त के समय जल में खड़े होकर अर्घ्य दें।"),
                    PrepTask("Day 4 (Usha Arghya)", "चौथा दिन (उषा अर्घ्य)", "Reach the ghat before dawn, offer arghya with milk and water to the rising sun, break the 36-hour fast.", "सूर्योदय से पूर्व घाट पहुंचें, लालिमा आते ही दूध-जल का अर्घ्य देकर 36 घंटे का निर्जला व्रत पूर्ण करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Nahay Khay Sanctity", "नहाय खाय", "Bathe in holy river or with Ganga water, cook meal with pure rock salt and cow ghee.", "गंगाजल से स्नान कर पूरी शुद्धि के साथ भोजन बनाएं और सूर्य देव को स्मरण कर ग्रहण करें।"),
                    PujaStep(2, "Kharna Prasad", "खरना प्रसाद", "Offer the sacred jaggery kheer, wheat roti, and banana to Sun God; entire family eats only in silence.", "शाम को एकांत में सूर्य देव को गुड़ की खीर का भोग लगाकर ग्रहण करें। इसके बाद 36 घंटे का निर्जला व्रत प्रारंभ होता है।"),
                    PujaStep(3, "Daura & Soop Arrangement", "दउरा व सूप सजाना", "Arrange bamboo soop with Thekua, bananas, coconut, sweet potato, sugarcane, and light an earthen lamp on it.", "बांस के सूप में शुद्ध ठेकुआ, केला, पानी वाला नारियल, सुथनी, मूली, अदरक का पौधा और दीपक सजाएं।"),
                    PujaStep(4, "Sandhya Arghya Offering", "संध्या अर्घ्य", "Enter waist-deep water facing west, hold the soop high, pour milk and water stream as the sun sets.", "पश्चिम दिशा की ओर मुंह कर जल में खड़े हों, सूप को दोनों हाथों से उठाकर डूबते सूर्य को अर्घ्य दें।", "ॐ सूर्याय नमः। एहि सूर्य सहस्रांशो तेजोराशे जगत्पते। अनुकम्पय मां भक्त्या गृहाणार्घ्यं दिवाकर॥"),
                    PujaStep(5, "Usha Arghya & Paran", "उषा अर्घ्य एवं पारण", "Face east as early morning light dawns, offer arghya with family, seek blessings of the Vrati.", "पूर्व दिशा में उगते सूर्य को अर्घ्य दें, व्रती के चरण छूकर आशीर्वाद लें और ठेकुआ का प्रसाद बांटें।")
                ),
                supplies = listOf(
                    Supply("Bamboo Soop & Daura Basket", "बांस का सूप और दउरा", "2 soop + 1 basket", "2 सूप + 1 दउरा", "Chhath", "दउरा सामग्री"),
                    Supply("Wheat Flour, Jaggery & Pure Ghee for Thekua", "गेहूं का आटा, गुड़ और देसी घी", "1 kg each", "1-1 किलो", "Prasad", "ठेकुआ सामग्री"),
                    Supply("Sugarcane with leaves (Ganna)", "पत्तेदार गन्ना", "5 pcs", "5 नग", "Offering", "गन्ना"),
                    Supply("Water Coconut (Pani wala Nariyal)", "पानी वाला जटा नारियल", "2 pcs", "2 नग", "Offering", "नारियल"),
                    Supply("Seasonal Produce (Radish, Ginger, Turmeric plant)", "मूली, अदरक और कच्ची हल्दी का पौधा", "1 bunch", "1 गुच्छा", "Harvest", "फल-सब्जी"),
                    Supply("Clay Lamp (Chhath Diya) & Camphor", "मिट्टी के दीये और कपूर", "11 pcs", "11 नग", "Lamps", "दीपक")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ ह्रीं ह्रीं सूर्याय सहस्रकिरणाय मनोवांछित फलम् देहि देहि स्वाहा॥",
                        "Om Hreem Hreem Suryaya Sahasrakiranaya Manovanchhita Phalam Dehi Dehi Svaha",
                        "O thousand-rayed radiant Sun God, the supreme source of energy and light, please grant noble desires and good health to all.",
                        "हे सहस्त्र किरणों वाले भगवान सूर्यदेव! आप जगत के प्राण हैं, हमारे जीवन में तेज, आरोग्य और सत्य की प्रतिष्ठा करें।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Touching puja items without washing hands", "बिना हाथ धोए पूजा सामग्री को छूना", "Chhath has the strictest purity rules in Indian culture. Wash hands with clean water before touching any soop item.", "छठ में शुद्धता का विशेष ध्यान रखा जाता है; सामग्री को सदैव धुले हाथों से ही स्पर्श करें।")
                )
            ),

            // 14. MAKAR SANKRANTI / PONGAL
            Festival(
                id = "sankranti",
                nameEn = "Makar Sankranti / Pongal",
                nameHi = "मकर संक्रांति / पोंगल",
                date2026En = "14 Jan 2026",
                date2026Hi = "14 जनवरी 2026",
                date2025En = "14 Jan 2025",
                date2027En = "14 Jan 2027",
                monthEn = "January",
                monthHi = "जनवरी",
                categoryEn = "Regional",
                categoryHi = "कृषि व सूर्य पर्व",
                emoji = "🌾",
                summaryEn = "Sun's transition into Capricorn (Uttarayan), nationwide harvest celebrations, sesame-jaggery (Til-Gul), and Pongal.",
                summaryHi = "सूर्य का मकर राशि में प्रवेश (उत्तरायण), तिल-गुड़ का स्नेह, खिचड़ी दान और नई फसल का उत्सव।",
                significanceEn = "Makar Sankranti marks the end of winter solstice and the beginning of longer, warmer days as the Sun enters the northern celestial hemisphere (Uttarayan). Across India, it is celebrated with holy dips in sacred rivers, kite flying, and sharing sweet sesame and jaggery.",
                significanceHi = "मकर संक्रांति के दिन सूर्य देव धनु राशि से मकर राशि में प्रवेश कर उत्तरायण होते हैं। यह दिन प्रकृति में प्रकाश, गर्मी और ऊर्जा की वापसी का संदेश देता है। गंगा स्नान, खिचड़ी दान और तिल-गुड़ बांटने की परंपरा है।",
                explainSimplyEn = "Celebrate the return of sunshine and harvest. Eat dishes made of sesame (til) and jaggery (gur) to warm the body, donate food and warm blankets to the needy, fly kites, and say: 'Til-gul ghya, god god bola' (take this sweet sesame, speak sweetly).",
                explainSimplyHi = "सर्दी की विदाई और धूप के स्वागत का पर्व है। तिल और गुड़ की तासीर गर्म होती है, इसलिए तिल के लड्डू खाएं, जरूरतमंदों को तिल-कंबल और खिचड़ी दान करें, और सबके साथ मीठी वाणी बोलें।",
                muhurat = MuhuratTiming(
                    nameEn = "Sankranti Punya Kaal Muhurat",
                    nameHi = "मकर संक्रांति पुण्य काल मुहूर्त",
                    timeEn = "07:15 AM – 12:30 PM",
                    timeHi = "सुबह 07:15 से दोपहर 12:30 तक",
                    noteEn = "Punya Kaal is the most auspicious window for snan, Surya arghya, and daan.",
                    noteHi = "पुण्य काल में स्नान, सूर्य पूजा और दान करना अनंत फलदायी माना गया है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare sesame-jaggery laddoos (Til ke laddoo), purchase new crop rice, black urad dal for khichdi.", "तिल-गुड़ के लड्डू बनाएं और खिचड़ी के लिए नए चावल, उड़द की काली दाल और घी तैयार रखें।"),
                    PrepTask("On the Day Morning", "संक्रांति सुबह", "Take bath early adding sesame seeds in water, offer water to Sun God, make donations.", "पानी में तिल डालकर स्नान करें, सूर्य देव को तांबे के लोटे से जल दें और खिचड़ी दान करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Til Snan", "तिल स्नान", "Mix black or white sesame seeds in your bath water to cleanse body and soul.", "स्नान के जल में तिल मिलाकर स्नान करें।"),
                    PujaStep(2, "Surya Arghya", "सूर्य अर्घ्य", "Offer water mixed with red flowers, roli, and sesame from a copper vessel facing East.", "तांबे के लोटे में जल, लाल फूल, रोली और तिल मिलाकर पूर्व दिशा में सूर्य देव को अर्घ्य दें।", "ॐ घृणिः सूर्याय नमः"),
                    PujaStep(3, "Khichdi & Woolen Daan", "खिचड़ी व कंबल दान", "Touch raw rice, black dal, salt, turmeric, and warm clothes, then donate to needy people.", "कच्ची खिचड़ी सामग्री (चावल, दाल, नमक, घी) और ऊनी वस्त्रों को स्पर्श कर दान करें।"),
                    PujaStep(4, "Sweet Sharing", "तिल-गुड़ वितरण", "Distribute Til-patti, Revdi, Gajak, and Pongal sweet with family and neighbors.", "तिल-गुड़ के लड्डू और खिचड़ी परिवार के साथ बैठकर प्रेमपूर्वक खाएं।")
                ),
                supplies = listOf(
                    Supply("White and Black Sesame Seeds (Til)", "सफेद व काले तिल", "250 g each", "250-250 ग्राम", "Essentials", "तिल सामग्री"),
                    Supply("Pure Jaggery (Gur)", "शुद्ध देशी गुड़", "500 g", "500 ग्राम", "Sweets", "गुड़"),
                    Supply("Khichdi Ingredients (Rice, Black Urad Dal)", "खिचड़ी सामग्री (चावल, उड़द दाल)", "1 kg set", "1 सेट", "Daan", "दान सामग्री"),
                    Supply("Kites and Spools (Patang & Manja)", "पतंग और मांझा", "1 set", "1 सेट", "Recreation", "पतंग")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ सूर्याय नमः। ॐ आदित्याय नमः॥",
                        "Om Suryaya Namah | Om Adityaya Namah",
                        "Salutations to the luminous Sun God, the life-giver of all realms.",
                        "समस्त संसार को ऊर्जा और प्रकाश देने वाले भगवान सूर्य को नमन।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Using plastic or glass-coated thread that harms birds while flying kites", "पतंग में चाइनीज मांझे का प्रयोग करना", "Use only soft cotton thread to protect birds and avoid road injuries.", "पक्षियों और इंसानों की सुरक्षा हेतु सूती धागे का ही प्रयोग करें।")
                )
            ),

            // 15. RAKSHA BANDHAN
            Festival(
                id = "rakhi",
                nameEn = "Raksha Bandhan",
                nameHi = "रक्षाबंधन",
                date2026En = "28 Aug 2026",
                date2026Hi = "28 अगस्त 2026",
                date2025En = "9 Aug 2025",
                date2027En = "17 Aug 2027",
                monthEn = "August",
                monthHi = "अगस्त",
                categoryEn = "Major",
                categoryHi = "प्रमुख उत्सव",
                emoji = "🧿",
                summaryEn = "Sacred thread of protection, mutual pledge of care, and unconditional affection between brothers and sisters.",
                summaryHi = "स्नेह और सुरक्षा का पावन पर्व, बहन द्वारा रक्षासूत्र बंधन और भाई द्वारा जीवनभर साथ निभाने का वचन।",
                significanceEn = "On Shravana Purnima, sisters tie a sacred talisman (Rakhi) around their brothers' wrists. The brother reciprocates with gifts and promises lifelong support. Historically, Draupadi tied a strip of her saree to Krishna's bleeding finger, and Krishna protected her honour forever.",
                significanceHi = "श्रावण पूर्णिमा को मनाया जाने वाला रक्षाबंधन केवल धागा नहीं, बल्कि परस्पर विश्वास और सुरक्षा का संकल्प है। महाभारत में द्रौपदी ने श्रीकृष्ण की उंगली पर अपनी साड़ी का पल्लू बांधा था और कान्हा ने चीरहरण के समय द्रौपदी के मान की रक्षा की थी।",
                explainSimplyEn = "A joyful celebration of family bonds. Sisters prepare an aarti thali with roli, akshat, a diya, sweets, and a beautiful Rakhi. Tie the thread during an auspicious hour (checking to ensure no Bhadra Kaal), feed a sweet, and share gifts.",
                explainSimplyHi = "परिवार के स्नेह का सुंदर पर्व। बहनें थाली में रोली, अक्षत, दीपक, मिठाई और राखी सजाएं। भद्रा काल से बचकर शुभ मुहूर्त में भाई की कलाई पर रक्षासूत्र बांधें, मिठाई खिलाएं और आरती करें।",
                muhurat = MuhuratTiming(
                    nameEn = "Auspicious Rakhi Tying Window (Post Bhadra)",
                    nameHi = "राखी बांधने का शुभ मुहूर्त (भद्रा रहित)",
                    timeEn = "01:30 PM – 08:35 PM",
                    timeHi = "दोपहर 01:30 से रात 08:35 तक",
                    noteEn = "Never tie Rakhi during Bhadra Kaal; afternoon Aparahna is best.",
                    noteHi = "भद्रा काल में राखी नहीं बांधी जाती; अपराह्न का समय सबसे शुभ होता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("3 Days Before", "3 दिन पहले", "Choose skin-friendly cotton/silk Rakhis, buy brother's favourite sweets.", "सुंदर सूती या रेशमी राखी चुनें, भाई की पसंद की मिठाई लाएं।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Sister prepares aarti thali, brother wears clean traditional clothes, sit facing East.", "थाली सजाएं, भाई को पूर्व दिशा में आसन पर बैठाएं।")
                ),
                steps = listOf(
                    PujaStep(1, "Thali Preparation", "पूजा थाली सजाना", "Arrange roli, chandan, akshat, a ghee diya, Rakhi, and sweets in a thali.", "थाली में रोली, अक्षत, चंदन, घी का दीपक, राखी और मिठाई रखें।"),
                    PujaStep(2, "Tilak & Akshat", "तिलक व अक्षत", "Apply vertical tilak of roli and chandan on brother's forehead, press unbroken rice gently.", "भाई के माथे पर रोली और चंदन का टीका लगाएं और अक्षत लगाएं।"),
                    PujaStep(3, "Tying the Rakhi", "राखी बांधना", "Tie the Rakhi on the right wrist with love, chanting the Raksha Sutra.", "भाई की दाहिनी कलाई पर प्रेमपूर्वक रक्षासूत्र बांधें।", "येन बद्धो बली राजा दानवेन्द्रो महाबलः। तेन त्वामपि बध्नामि रक्षे मा चल मा चल॥"),
                    PujaStep(4, "Aarti & Sweet Sharing", "आरती व मिठाई खिलाना", "Rotate the aarti thali clockwise around brother's face, feed him a bite of sweet.", "भाई की आरती उतारें और मिठाई खिलाकर मंगल कामना करें।"),
                    PujaStep(5, "Gift & Blessing Exchange", "उपहार व आशीर्वाद", "Brother presents gift to sister and touches her feet or offers blessings.", "भाई बहन को उपहार दे और एक-दूसरे के प्रति आजीवन स्नेह व सम्मान का संकल्प लें।")
                ),
                supplies = listOf(
                    Supply("Traditional Silk / Cotton Rakhi", "रेशमी या सूती राखी", "1 to 3 pcs", "1 से 3 नग", "Essentials", "राखी"),
                    Supply("Roli & Akshat (Unbroken Rice)", "रोली और अक्षत", "1 set", "1 डिब्बी", "Essentials", "रोली-अक्षत"),
                    Supply("Sweets (Kaju Katli / Gulab Jamun)", "मिठाई", "500 g", "500 ग्राम", "Sweets", "मिठाई"),
                    Supply("Brass or Steel Aarti Thali & Small Diya", "आरती थाली और दीपक", "1 set", "1 थाली", "Essentials", "थाली")
                ),
                mantras = listOf(
                    MantraItem(
                        "येन बद्धो बली राजा दानवेन्द्रो महाबलः। तेन त्वामपि बध्नामि रक्षे मा चल मा चल॥",
                        "Yena Baddho Bali Raja Danavendro Mahabalah | Tena Tvamapi Badhnami Rakshe Ma Chala Ma Chala",
                        "I tie upon your wrist the same protective thread that bound the mighty King Bali. O sacred talisman, stay steadfast and protect always.",
                        "जिस रक्षासूत्र से महाबली दानवेन्द्र राजा बलि को बांधा गया था, उसी से मैं तुम्हारी कलाई पर रक्षासूत्र बांधती हूं। हे रक्षासूत्र! तुम सदा इसकी रक्षा करना।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Tying Rakhi during Bhadra Kaal", "भद्रा काल में राखी बांधना", "Check local panchang to ensure the Bhadra period has concluded before beginning the ceremony.", "पंचांग देखकर सुनिश्चित करें कि भद्रा समाप्त हो चुकी हो, तभी रक्षासूत्र बांधें।")
                )
            ),

            // 16. UGADI / GUDI PADWA
            Festival(
                id = "ugadi",
                nameEn = "Ugadi / Gudi Padwa",
                nameHi = "उगादी / गुड़ी पड़वा",
                date2026En = "19 Mar 2026",
                date2026Hi = "19 मार्च 2026",
                date2025En = "30 Mar 2025",
                date2027En = "7 Apr 2027",
                monthEn = "March",
                monthHi = "मार्च",
                categoryEn = "Regional",
                categoryHi = "नववर्ष",
                emoji = "🚩",
                summaryEn = "Traditional Vedic New Year celebrated in Maharashtra, Karnataka, Andhra Pradesh, and Telangana.",
                summaryHi = "पारंपरिक हिंदू नववर्ष (नवसंवत्सर), गुड़ी फहराना और जीवन के छह स्वादों का पावन संदेश।",
                significanceEn = "Marks the first day of Chaitra Shukla Pratipada, the start of the Vedic lunisolar calendar. In Maharashtra, homes hoist the vibrant Gudi (flag topped with silver pot and neem leaves). In Deccan, people eat Pachadi/Bevu-Bella representing the 6 tastes of life.",
                significanceHi = "चैत्र शुक्ल प्रतिपदा को सृष्टि के आरंभ और नव संवत्सर के रूप में मनाया जाता है। महाराष्ट्र में घर-घर में गुड़ी (विजय पताका) फहराई जाती है। दक्षिण भारत में नीम-गुड़ (बेवु-बेल्ला) खाया जाता है जो जीवन के सुख-दुख के संतुलन का प्रतीक है।",
                explainSimplyEn = "The authentic Indian New Year. Hoist a bright flag (Gudi) outside your home to invite victory and prosperity, and eat a mixture of sweet jaggery and bitter neem leaves reminding us that life contains both joys and challenges.",
                explainSimplyHi = "हमारा पारंपरिक नववर्ष! घर के बाहर सुंदर गुड़ी फहराएं, आम के पत्तों का तोरण लगाएं, और नीम व गुड़ का मिश्रण खाकर यह संकल्प लें कि जीवन के सुख और दुख दोनों को समभाव से स्वीकार करेंगे।",
                muhurat = MuhuratTiming(
                    nameEn = "Gudi Hoisting & Pratipada Muhurat",
                    nameHi = "गुड़ी स्थापन व पूजा मुहूर्त",
                    timeEn = "06:25 AM – 08:30 AM",
                    timeHi = "सुबह 06:25 से 08:30 तक",
                    noteEn = "Morning hours at sunrise are best to hoist the Gudi.",
                    noteHi = "सूर्योदय के समय गुड़ी फहराना अत्यंत शुभ माना जाता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Arrange long bamboo stick, bright silk cloth, silver/copper kalash, neem leaves, garland of sugar crystals (Gaathi).", "बांस की छड़ी, रेशमी वस्त्र, तांबे/चांदी का कलश, नीम की पत्तियां और सांचे की मीठी गाठी तैयार रखें।"),
                    PrepTask("On the Day Morning", "नववर्ष की सुबह", "Draw Rangoli at entrance, hoist Gudi on right side of roof/window, prepare Puran Poli or Ugadi Pachadi.", "मुख्य द्वार पर रंगोली बनाएं, गुड़ी फहराएं और पूरन पोली का भोग लगाएं।")
                ),
                steps = listOf(
                    PujaStep(1, "Oil Bath & Rangoli", "अभ्यंग स्नान व रंगोली", "Take bath early, dress in new traditional clothes, create rangoli at threshold.", "स्नान कर नए वस्त्र पहनें और द्वार पर सुंदर रंगोली सजाएं।"),
                    PujaStep(2, "Gudi Assembly", "गुड़ी निर्माण व स्थापन", "Tie bright cloth to bamboo stick, add neem sprigs, sugar crystal garland, top with inverted copper/silver pot.", "बांस की छड़ी पर पीला/लाल वस्त्र, नीम के पत्ते, बताशों की माला बांधकर ऊपर उल्टा कलश लगाएं।"),
                    PujaStep(3, "Gudi Pujan", "गुड़ी पूजन", "Offer haldi-kumkum, flowers, and incense to the hoisted Gudi.", "गुड़ी को हल्दी-कुमकुम, अक्षत और पुष्प अर्पित कर धूप-दीप दिखाएं।", "ब्रह्मध्वज नमस्तेऽस्तु सर्वाभीष्ट फलप्रद। प्राप्तेऽस्मिन् वत्सरे नित्यं मद्भवे मंगलं कुरु॥"),
                    PujaStep(4, "Eating Bevu-Bella / Pachadi", "नीम-गुड़ (बेवु-बेल्ला) प्राशन", "Tee off the year tasting the mixture of neem flowers (bitter) and jaggery (sweet).", "नीम की पत्तियां और गुड़ एक साथ चबाएं।")
                ),
                supplies = listOf(
                    Supply("Long Bamboo Stick & Silk Cloth", "बांस की छड़ी व नया वस्त्र", "1 set", "1 सेट", "Gudi", "गुड़ी सामग्री"),
                    Supply("Copper or Silver Kalash (Pot)", "तांबे या चांदी का लोटा", "1 pc", "1 नग", "Gudi", "गुड़ी सामग्री"),
                    Supply("Neem Leaves & Sugar Garland (Gaathi)", "नीम की पत्तियां व गाठी की माला", "1 set", "1 सेट", "Gudi", "गुड़ी सामग्री"),
                    Supply("Puran Poli / Sweet dish", "पूरन पोली या मिष्ठान्न", "1 plate", "1 थाली", "Food", "भोग")
                ),
                mantras = listOf(
                    MantraItem(
                        "ब्रह्मध्वज नमस्तेऽस्तु सर्वाभीष्ट फलप्रद। प्राप्तेऽस्मिन् वत्सरे नित्यं मद्भवे मंगलं कुरु॥",
                        "Brahmadhavaja Namaste'stu Sarvabhishta Phalaprada | Prapte'smin Vatsare Nityam Madbhave Mangalam Kuru",
                        "Salutations to the sacred flag of Brahma, bestower of all blessings. May this new year bring sustained peace and prosperity to our family.",
                        "हे ब्रह्मध्वज! आप समस्त मनोकामनाओं को पूरा करने वाले हैं। इस नए वर्ष में हमारे घर में सदा मंगल और आनंद का वास हो।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Leaving the Gudi hoisted after sunset", "सूर्यास्त के बाद भी गुड़ी बंधी छोड़ देना", "Tradition requires respectfully lowering the Gudi before sunset on the same day.", "सूर्यास्त से पहले गुड़ी को प्रणाम कर ससम्मान उतार लिया जाता है।")
                )
            ),

            // 17. ONAM
            Festival(
                id = "onam",
                nameEn = "Onam (Thiruvonam)",
                nameHi = "ओणम (तिरुओणम)",
                date2026En = "26 Aug 2026",
                date2026Hi = "26 अगस्त 2026",
                date2025En = "5 Sep 2025",
                date2027En = "15 Sep 2027",
                monthEn = "August",
                monthHi = "अगस्त",
                categoryEn = "Regional",
                categoryHi = "दक्षिण भारतीय उत्सव",
                emoji = "🌸",
                summaryEn = "Kerala's harvest festival welcoming legendary King Mahabali with floral carpets (Pookkalam) and the grand Onasadya feast.",
                summaryHi = "केरल का भव्य फसल व सांस्कृतिक उत्सव, फूलों की सुंदर रंगोली (पूकलम) और स्वादिष्ट ओणसद्या भोज।",
                significanceEn = "Celebrates King Mahabali's annual return to his beloved people from the Netherworld, granted by Lord Vamana (Vishnu's avatar). It embodies an ideal golden era of honesty, prosperity, and equality where all citizens were happy.",
                significanceHi = "राजा महाबलि के अपनी प्रजा से मिलने आने की पावन स्मृति में ओणम मनाया जाता है। भगवान वामन ने महाबलि की दानशीलता से प्रसन्न होकर उन्हें वर्ष में एक बार अपनी प्रिय भूमि पर लौटने का वरदान दिया था।",
                explainSimplyEn = "Create a stunning floral Rangoli (Pookkalam) on the floor using real flower petals, wear elegant traditional Kasavu white clothes, and enjoy an elaborate 26-dish feast served on a fresh green banana leaf (Onasadya).",
                explainSimplyHi = "घर के आंगन में ताजे रंग-बिरंगे फूलों से पूकलम बनाएं, पारंपरिक सफेद-सुनहरे वस्त्र पहनें, और केले के पत्ते पर 26 तरह के व्यंजनों से युक्त सात्विक ओणसद्या भोज का सपरिवार आनंद लें।",
                muhurat = MuhuratTiming(
                    nameEn = "Thiruvonam Day Auspicious Timing",
                    nameHi = "तिरुओणम पावन समय",
                    timeEn = "06:00 AM – 09:30 AM",
                    timeHi = "सुबह 06:00 से 09:30 तक",
                    noteEn = "Morning is when Thrikkakara Appan (clay pyramid) is worshipped at the Pookkalam.",
                    noteHi = "सुबह के समय पूकलम के मध्य वामन देव (तृक्काकरा अप्पन) की पूजा की जाती है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("3 Days Before", "3 दिन पहले", "Collect colorful flower petals (marigold, jasmine, rose, ixora).", "विभिन्न रंगों के ताजे फूलों की पंखुड़ियां एकत्र करें।"),
                    PrepTask("On the Day Morning", "उत्सव की सुबह", "Assemble circular Pookkalam, prepare traditional banana leaf meal (Onasadya with Payasam).", "फूलों की गोलाकार रंगोली सजाएं और केले के पत्ते पर पायसम सहित स्वादिष्ट व्यंजन परोसें।")
                ),
                steps = listOf(
                    PujaStep(1, "Pookkalam Floral Art", "पूकलम निर्माण", "Lay concentric circles of colorful fresh flower petals at the entrance.", "प्रवेश द्वार पर ताजे फूलों से गोलाकार रंगोली (पूकलम) बनाएं।"),
                    PujaStep(2, "Vamana / Thrikkakara Pujan", "वामन देव पूजन", "Place small clay pyramidal idol (Thrikkakara Appan) in the center, offer flowers and rice paste.", "पूकलम के मध्य मिट्टी के पिरामिड रूपी वामन देव की स्थापना कर पूजन करें।"),
                    PujaStep(3, "Onasadya Feast", "ओणसद्या भोज", "Serve pure vegetarian feast (Avial, Sambar, Thoran, Ada Pradhaman Payasam) on a clean banana leaf.", "केले के पत्ते पर सात्विक भोजन और खीर (पायसम) परोसकर सपरिवार भोजन करें।")
                ),
                supplies = listOf(
                    Supply("Assorted Fresh Flower Petals", "रंग-बिरंगे ताजे फूलों की पंखुड़ियां", "2 kg", "2 किलो", "Flowers", "फूल"),
                    Supply("Fresh Banana Leaves", "ताजे केले के पत्ते", "5 to 10 pcs", "5 से 10 पत्ते", "Dining", "पत्तल"),
                    Supply("Jaggery Payasam (Ada Pradhaman)", "गुड़ और नारियल दूध की पायसम", "1 pot", "1 बर्तन", "Sweets", "मिठाई")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ नमो नारायणाय॥",
                        "Om Namo Narayanaya",
                        "Salutations to Lord Narayana, the all-pervading protector of the universe.",
                        "समस्त सृष्टि के पालनकर्ता भगवान श्री नारायण को नमन।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Serving Onasadya on artificial paper plates", "केले के पत्ते की जगह प्लास्टिक थाली में भोजन परोसना", "The cultural essence and aroma of Onasadya comes from warm food interacting with fresh banana leaf.", "ओणसद्या का असली स्वाद और शुचिता केले के पत्ते पर भोजन करने में ही है।")
                )
            ),

            // 18. RAM NAVAMI
            Festival(
                id = "ram",
                nameEn = "Ram Navami",
                nameHi = "श्री राम नवमी",
                date2026En = "27 Mar 2026",
                date2026Hi = "27 मार्च 2026",
                date2025En = "6 Apr 2025",
                date2027En = "15 Apr 2027",
                monthEn = "March",
                monthHi = "मार्च",
                categoryEn = "Major",
                categoryHi = "प्रमुख उत्सव",
                emoji = "🏹",
                summaryEn = "Celebrating the midday birth of Maryada Purushottam Lord Rama on Chaitra Navami.",
                summaryHi = "मर्यादा पुरुषोत्तम भगवान श्री राम का मध्याह्न 12 बजे जन्मोत्सव, रामायण पाठ और पंचामृत भोग।",
                significanceEn = "Lord Rama incarnated in Ayodhya at noon under Punarvasu Nakshatra to uphold righteous conduct (Maryada), compassion, and truth. Devotees celebrate with fasting, Rama Katha, and singing 'Bhae Pragat Kripala' at 12:00 noon.",
                significanceHi = "चैत्र शुक्ल नवमी को दोपहर 12 बजे अयोध्या में भगवान श्री राम का अवतरण हुआ। श्री राम का जीवन आदर्श पुत्र, भाई, पति और राजा के रूप में मानवता को मर्यादा और धर्म का मार्ग सिखाता है।",
                explainSimplyEn = "Celebrate the birth of Lord Rama at 12:00 noon. Swing the baby Rama idol in a flower-decked cradle, recite the divine hymn 'Bhae Pragat Kripala', and share sweet panchamrit and sunthora prasad.",
                explainSimplyHi = "दोपहर ठीक 12 बजे प्रभु श्री राम के जन्मोत्सव की खुशी मनाएं। बाल राम को झूले में झुलाएं, 'भए प्रगट कृपाला' स्तुति गाएं, और पंजीरी व पंचामृत का प्रसाद सभी को बांटें।",
                muhurat = MuhuratTiming(
                    nameEn = "Ram Navami Madhyahna Janmotsav Muhurat",
                    nameHi = "राम जन्मोत्सव मध्याह्न मुहूर्त",
                    timeEn = "11:12 AM – 01:38 PM",
                    timeHi = "दोपहर 11:12 से 01:38 तक",
                    noteEn = "Exactly 12:00 PM is the moment of divine birth.",
                    noteHi = "ठीक दोपहर 12:00 बजे भगवान राम का जन्म क्षण मनाया जाता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare dry ginger powder sweet (Sunthora), clean Ram Darbar idol, prepare yellow flowers.", "सौंठ और गुड़ से सुंठौरा बनाएं, राम दरबार की प्रतिमा साफ करें और पीले पुष्प लाएं।"),
                    PrepTask("On the Day (12:00 Noon)", "दोपहर 12:00 बजे", "Uncover baby Rama idol, ring bells, blow conch, sing Stuti, perform Aarti.", "ठीक 12 बजे शंख व घंटी बजाएं, 'भए प्रगट कृपाला' स्तुति गाएं और आरती करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Ram Darbar Sthapana", "राम दरबार स्थापना", "Place idols of Rama, Sita, Lakshmana, and Hanuman on a yellow-clothed chowki.", "पीले वस्त्र पर श्री राम, सीता माता, लक्ष्मण जी और हनुमान जी को स्थापित करें।"),
                    PujaStep(2, "Noon Stuti & Chanting", "मध्याह्न स्तुति", "At 12:00 PM, chant 'Bhae Pragat Kripala Deendayala' with joy.", "दोपहर ठीक 12 बजे हाथ जोड़कर प्रेम से स्तुति गाएं।", "भए प्रगट कृपाला दीनदयाला कौसल्या हितकारी। हरषित महतारी मुनि मन हारी अद्भुत रूप बिचारी॥"),
                    PujaStep(3, "Panchamrit & Sunthora Bhog", "पंचामृत व सुंठौरा भोग", "Offer fragrant tulsi leaves, panchamrit, and dry ginger sweet.", "तुलसी दल, पंचामृत और धनिया-सौंठ की पंजीरी का भोग लगाएं।")
                ),
                supplies = listOf(
                    Supply("Ram Darbar Idol / Photo", "श्री राम दरबार चित्र या प्रतिमा", "1 pc", "1 प्रति", "Idol", "प्रतिमा"),
                    Supply("Tulsi Leaves (Basil)", "ताजी तुलसी की पत्तियां", "21 leaves", "21 पत्ते", "Sacred", "तुलसी"),
                    Supply("Dry Ginger Sweet (Sunthora / Panjiri)", "सुंठौरा / पंजीरी", "250 g", "250 ग्राम", "Prasad", "प्रसाद"),
                    Supply("Yellow Flowers & Garlands", "पीले फूल व माला", "1 set", "1 माला", "Flowers", "फूल")
                ),
                mantras = listOf(
                    MantraItem(
                        "श्री राम जय राम जय जय राम॥",
                        "Shri Rama Jaya Rama Jaya Jaya Rama",
                        "Victory and glory to Lord Sri Rama, the light of our hearts.",
                        "प्रभु श्री राम की सदा जय हो, उनका नाम समस्त संतापों को हरने वाला है।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Offering meal without Tulsi leaves", "बिना तुलसी दल के भोग लगाना", "Lord Rama's offerings should always include pure sacred Tulsi leaves.", "श्री राम जी के भोग में तुलसी पत्र अवश्य होना चाहिए।")
                )
            ),

            // 19. HANUMAN JAYANTI
            Festival(
                id = "hanuman",
                nameEn = "Hanuman Jayanti",
                nameHi = "श्री हनुमान जयंती",
                date2026En = "2 Apr 2026",
                date2026Hi = "2 अप्रैल 2026",
                date2025En = "12 Apr 2025",
                date2027En = "20 Apr 2027",
                monthEn = "April",
                monthHi = "अप्रैल",
                categoryEn = "Major",
                categoryHi = "प्रमुख उत्सव",
                emoji = "🙏",
                summaryEn = "Birth anniversary of Mahabali Hanuman, embodiment of courage, devotion, and selfless service.",
                summaryHi = "पवनपुत्र महाबली हनुमान जी का प्राकट्योत्सव, चोला अर्पण, सुंदरकांड पाठ और बूंदी का प्रसाद।",
                significanceEn = "Born on Chaitra Purnima, Lord Hanuman is the supreme devotee of Lord Rama, representing unshakeable strength, humility, and protection against negative energies. Worshipping Him brings courage, physical vitality, and mental peace.",
                significanceHi = "चैत्र पूर्णिमा को अंजना नंदन श्री हनुमान जी का अवतरण हुआ। वे शक्ति, बुद्धि और भक्ति के साक्षात स्वरूप हैं। हनुमान चालीसा और सुंदरकांड का पाठ करने से भय, संकट और नकारात्मक ऊर्जा का नाश होता है।",
                explainSimplyEn = "Pray to Hanuman ji for mental courage, strength, and protection. Offer orange sindoor paste mixed with jasmine oil (Chola), recite the Hanuman Chalisa with full faith, and share sweet boondi or motichoor laddus.",
                explainSimplyHi = "साहस और आत्मविश्वास के लिए हनुमान जी की आराधना करें। उन्हें चमेली के तेल में नारंगी सिंदूर का लेप (चोला) चढ़ाएं, परिवार के साथ हनुमान चालीसा का पाठ करें और बूंदी के लड्डू का भोग लगाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Hanuman Jayanti Purnima Puja Muhurat",
                    nameHi = "हनुमान जयंती पूजा मुहूर्त",
                    timeEn = "06:18 AM – 10:45 AM",
                    timeHi = "सुबह 06:18 से 10:45 तक",
                    noteEn = "Morning Purnima tithi is ideal for offering Chola and reciting Sundarkand.",
                    noteHi = "सुबह के समय चोला चढ़ाना और सुंदरकांड का पाठ करना अत्यंत श्रेष्ठ है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Procure pure orange sindoor, pure jasmine oil (Chameli tel), fresh betel leaf, sweet boondi.", "शुद्ध नारंगी सिंदूर, चमेली का तेल, पान का बीड़ा और बूंदी के लड्डू की व्यवस्था करें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Clean altar, apply chandan and sindoor, recite Hanuman Chalisa 7 or 11 times.", "पूजा स्थल साफ करें, हनुमान चालीसा का 7 या 11 बार पाठ करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Chola Offering", "चोला अर्पण", "Mix orange sindoor with jasmine oil, apply gently to Hanuman ji's idol.", "चमेली के तेल में नारंगी सिंदूर मिलाकर हनुमान जी को चोला अर्पित करें।"),
                    PujaStep(2, "Paan & Tulsi Mala", "पान व तुलसी माला अर्पण", "Offer a sweet meetha paan (without supari) and garland made of Tulsi leaves or Akand flowers.", "तुलसी पत्तों की माला और मीठा पान हनुमान जी को समर्पित करें।"),
                    PujaStep(3, "Hanuman Chalisa Path", "हनुमान चालीसा पाठ", "Sit on red asan facing East, recite Hanuman Chalisa or Sundarkand with sincere devotion.", "लाल आसन पर बैठकर श्रद्धापूर्वक हनुमान चालीसा व बजरंग बाण का पाठ करें।", "संकट कटै मिटै सब पीरा। जो सुमिरै हनुमत बलबीरा॥")
                ),
                supplies = listOf(
                    Supply("Orange Sindoor & Jasmine Oil (Chameli tel)", "नारंगी सिंदूर व चमेली का तेल", "1 set", "1 सेट", "Chola", "चोला सामग्री"),
                    Supply("Fresh Sweet Paan (Meetha Paan)", "मीठा पान (बिना तंबाकू)", "1 pc", "1 नग", "Bhog", "भोग"),
                    Supply("Sweet Boondi or Laddus", "बूंदी या बेसन के लड्डू", "500 g", "500 ग्राम", "Sweets", "प्रसाद"),
                    Supply("Tulsi Leaf Garland", "तुलसी के पत्तों की माला", "1 pc", "1 माला", "Sacred", "माला")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ हं हनुमते रुद्रात्मकाय हुं फट्॥",
                        "Om Ham Hanumate Rudratmakaya Hum Phat",
                        "Salutations to Lord Hanuman, embodiment of Lord Rudra's invincible spiritual power.",
                        "रुद्र स्वरूप महाबली श्री हनुमान जी को मेरा प्रणाम, मेरे समस्त भयों को दूर करें।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Using synthetic perfumed chemical oils for Chola", "सुगंधित रासायनिक तेल का प्रयोग करना", "Use only natural, pure unadulterated Chameli (jasmine) oil mixed with orange vermilion.", "चोले के लिए केवल शुद्ध प्राकृतिक चमेली के तेल का ही उपयोग करें।")
                )
            ),

            // 20. GURU PURNIMA
            Festival(
                id = "guru",
                nameEn = "Guru Purnima (Vyasa Puja)",
                nameHi = "गुरु पूर्णिमा (व्यास पूजा)",
                date2026En = "29 Jul 2026",
                date2026Hi = "29 जुलाई 2026",
                date2025En = "10 Jul 2025",
                date2027En = "18 Jul 2027",
                monthEn = "July",
                monthHi = "जुलाई",
                categoryEn = "Major",
                categoryHi = "प्रमुख उत्सव",
                emoji = "📿",
                summaryEn = "Honoring teachers, spiritual guides, and Maharishi Veda Vyasa with heartfelt gratitude and paduka worship.",
                summaryHi = "मार्गदर्शक गुरुओं व महर्षि वेदव्यास जी के प्रति कृतज्ञता, चरण पादुका पूजन और गुरु वंदना।",
                significanceEn = "Marks the birth anniversary of sage Veda Vyasa who compiled the four Vedas, 18 Puranas, and Mahabharata. In Indian culture, the Guru dispels spiritual darkness (Gu = darkness, Ru = remover of darkness) and guides the disciple toward truth.",
                significanceHi = "आषाढ़ पूर्णिमा को महर्षि वेदव्यास जी का जन्म हुआ था। भारतीय संस्कृति में गुरु को ईश्वर से भी उच्च स्थान दिया गया है क्योंकि गुरु ही अज्ञान के अंधकार को मिटाकर परमात्मा का मार्ग प्रशस्त करते हैं।",
                explainSimplyEn = "A day of deep gratitude to all your mentors, spiritual guides, teachers, and parents who shaped your wisdom. Reach out to your teachers, seek their blessings, and meditate in silence.",
                explainSimplyHi = "अपने जीवन के सभी शिक्षकों, मार्गदर्शकों और माता-पिता के प्रति कृतज्ञता व्यक्त करने का दिन। गुरु के चरण स्पर्श करें, उन्हें आभार संदेश भेजें और उनके दिए ज्ञान का स्मरण कर ध्यान लगाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Guru Purnima Punya Kaal",
                    nameHi = "गुरु पूर्णिमा पुण्य काल",
                    timeEn = "06:30 AM – 11:30 AM",
                    timeHi = "सुबह 06:30 से 11:30 तक",
                    noteEn = "Morning hours are ideal for Guru Paduka puja and mantra chanting.",
                    noteHi = "प्रातःकाल गुरु पूजन और ध्यान हेतु सबसे उत्तम है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare yellow flowers, fresh fruits, sandalwood, and sweet gift for teachers/Guru.", "पीले फूल, फल, चंदन और गुरु दक्षिणा/उपहार की व्यवस्था करें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Touch parents' and elders' feet in morning, visit mentor or meditate on Guru mantra.", "सुबह माता-पिता के चरण स्पर्श करें, गुरु से मिलें या गुरु मंत्र का जाप करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Guru Paduka Pujan", "गुरु पादुका पूजन", "Wash Guru's holy sandals (Padukas) with clean water and milk, apply chandan.", "गुरु की चरण पादुकाओं को जल से धोकर चंदन और पुष्प अर्पित करें।"),
                    PujaStep(2, "Guru Vandana", "गुरु वंदना", "Recite the Guru Stotram with folded hands and calm mind.", "हाथ जोड़कर गुरु वंदना का पाठ करें।", "गुरुर्ब्रह्मा गुरुर्विष्णुः गुरुर्देवो महेश्वरः। गुरुः साक्षात् परं ब्रह्म तस्मै श्रीगुरवे नमः॥"),
                    PujaStep(3, "Offering of Dakshina & Fruit", "दक्षिणा व फल अर्पण", "Offer yellow cloth, fruits, and respectful dakshina, bowing to the ground.", "श्रद्धापूर्वक फल, मिठाई और गुरु दक्षिणा समर्पित कर साष्टांग प्रणाम करें।")
                ),
                supplies = listOf(
                    Supply("Yellow Flowers & Sandalwood Paste", "पीले फूल और चंदन", "1 bunch", "1 पैकेट", "Puja", "पूजा सामग्री"),
                    Supply("Fresh Seasonal Fruits", "ताजे मौसमी फल", "1 basket", "1 टोकरी", "Offering", "फल"),
                    Supply("Yellow Shawl / Cloth for Guru", "पीला शॉल / वस्त्र", "1 pc", "1 नग", "Gift", "वस्त्र")
                ),
                mantras = listOf(
                    MantraItem(
                        "गुरुर्ब्रह्मा गुरुर्विष्णुः गुरुर्देवो महेश्वरः। गुरुः साक्षात् परं ब्रह्म तस्मै श्रीगुरवे नमः॥",
                        "Gurur Brahma Gurur Vishnuh Gurur Devo Maheshwarah | Guruh Sakshat Param Brahma Tasmai Shri Gurave Namah",
                        "The Guru is Brahma the creator, Vishnu the preserver, and Maheshwara the transformer. The Guru is indeed the Supreme Reality; salutations to that noble Guru.",
                        "गुरु ही ब्रह्मा, विष्णु और महेश हैं। गुरु ही साक्षात् परब्रह्म हैं, ऐसे श्री सद्गुरु को मेरा कोटि-कोटि नमन।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Treating Guru Purnima merely as an academic Teacher's Day", "इसे केवल औपचारिक शिक्षक दिवस समझना", "Recognize the inner spiritual guide and the wisdom that liberates the mind.", "यह केवल किताबी ज्ञान का नहीं, बल्कि आत्म-ज्ञान देने वाले मार्गदर्शक के प्रति समर्पण का दिन है।")
                )
            ),

            // 21. MAHAVIR JAYANTI
            Festival(
                id = "mahavir",
                nameEn = "Mahavir Jayanti",
                nameHi = "महावीर जयंती",
                date2026En = "31 Mar 2026",
                date2026Hi = "31 मार्च 2026",
                date2025En = "10 Apr 2025",
                date2027En = "19 Apr 2027",
                monthEn = "March",
                monthHi = "मार्च",
                categoryEn = "National",
                categoryHi = "राष्ट्रीय व जैन पर्व",
                emoji = "🕊️",
                summaryEn = "Birth anniversary of Lord Mahavira, champion of Ahimsa (non-violence), truth, and unconditional compassion.",
                summaryHi = "भगवान महावीर का जन्म कल्याणक, अहिंसा, सत्य, अपरिग्रह और 'जियो और जीने दो' का पावन संदेश।",
                significanceEn = "Marks the birth of the 24th Tirthankara of Jainism, Bhagwan Mahavira. His eternal teachings of Ahimsa Paramo Dharma (non-violence is supreme), Satya (truth), Asteya (non-stealing), Brahmacharya, and Aparigraha (non-possession) are vital for world peace.",
                significanceHi = "जैन धर्म के 24वें तीर्थंकर भगवान महावीर का जन्म चैत्र शुक्ल त्रयोदशी को हुआ था। उनके द्वारा दिए गए 'अहिंसा परमो धर्मः' और 'जियो और जीने दो' के सिद्धांत समस्त मानव जाति को शांति और करुणा का मार्ग दिखाते हैं।",
                explainSimplyEn = "A day to practice kindness towards all living beings. Practice non-violence in thoughts, words, and actions, donate to animal shelters (Gaushalas), and eat simple pure vegetarian food.",
                explainSimplyHi = "सभी जीवों के प्रति करुणा और दया का दिन। अपनी वाणी और व्यवहार में मिठास लाएं, किसी भी जीव को कष्ट न पहुंचाएं, गौशाला में दान दें और 'जियो और जीने दो' की भावना अपनाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Prabhat Pheri & Abhishek Muhurat",
                    nameHi = "प्रभात फेरी व अभिषेक मुहूर्त",
                    timeEn = "06:15 AM – 09:30 AM",
                    timeHi = "सुबह 06:15 से 09:30 तक",
                    noteEn = "Morning temple procession and silver jug abhishek are traditional.",
                    noteHi = "प्रातःकाल भगवान महावीर का जलाभिषेक और शांति धारा की जाती है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Clean temple corner, plan donations for bird seed/feed and animal shelters.", "पक्षियों के दाने-पानी और गौशाला में दान की योजना बनाएं।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Chant Navkar Mantra, practice fasting or satvik meal without root vegetables, meditate.", "णमोकार मंत्र का 108 बार जाप करें, सात्विक आहार लें और ध्यान लगाएं।")
                ),
                steps = listOf(
                    PujaStep(1, "Navkar Mantra Chanting", "णमोकार मंत्र जप", "Chant the sacred universal Navkar mantra with deep humility.", "शुद्ध आसन पर बैठकर णमोकार महामंत्र का ध्यान करें।", "णमो अरिहंताणं, णमो सिद्धाणं, णमो आयरियाणं, णमो उवज्झायाणं, णमो लोए सव्वसाहूणं।"),
                    PujaStep(2, "Charity & Dana", "जीव दया व दान", "Feed cows, scatter grains for pigeons, and contribute to charities.", "गौ माता को हरा चारा खिलाएं, पक्षियों को दाना डालें और असहायों की सहायता करें।")
                ),
                supplies = listOf(
                    Supply("Bird Feed (Bajra/Grains)", "पक्षियों का दाना", "1 kg", "1 किलो", "Kindness", "जीव दया"),
                    Supply("Green Grass / Fodder for Cows", "गौशाला हेतु हरा चारा", "1 bundle", "1 गट्ठा", "Kindness", "गौ सेवा")
                ),
                mantras = listOf(
                    MantraItem(
                        "अहिंसा परमो धर्मः। जियो और जीने दो॥",
                        "Ahimsa Paramo Dharmah | Live and Let Live",
                        "Non-violence is the supreme spiritual law; live peacefully and let all creatures live peacefully.",
                        "अहिंसा ही परम धर्म है, सभी जीवों के प्रति दया रखें और शांति से जिएं।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Wasting food or harming small insects", "अन्न का अपव्यय या जीवों को सताना", "Practice mindfulness and avoid food wastage as a true tribute to Lord Mahavira.", "भोजन का आदर करें और किसी भी मूक प्राणी को कष्ट न दें।")
                )
            ),

            // 22. BUDDHA PURNIMA
            Festival(
                id = "buddha",
                nameEn = "Buddha Purnima (Vesak)",
                nameHi = "बुद्ध पूर्णिमा (वेसाक)",
                date2026En = "1 May 2026",
                date2026Hi = "1 मई 2026",
                date2025En = "12 May 2025",
                date2027En = "20 May 2027",
                monthEn = "May",
                monthHi = "मई",
                categoryEn = "National",
                categoryHi = "राष्ट्रीय व बौद्ध पर्व",
                emoji = "☸️",
                summaryEn = "Commemorating Gautama Buddha's birth, enlightenment under the Bodhi tree, and Mahaparinirvana.",
                summaryHi = "गौतम बुद्ध का जन्मोत्सव, बोधिवृक्ष तले ज्ञान प्राप्ति और महापरिनिर्वाण का पावन त्रिविध दिवस।",
                significanceEn = "Buddha Purnima celebrates three monumental events in Gautama Buddha's life on Vaishakha Purnima: his birth in Lumbini, his supreme enlightenment (Nirvana) in Bodh Gaya, and his Mahaparinirvana in Kushinagar.",
                significanceHi = "वैशाख पूर्णिमा के दिन तथागत बुद्ध का जन्म, ज्ञान प्राप्ति (संबोधि) और महापरिनिर्वाण तीनों घटित हुए थे। बुद्ध का मध्यम मार्ग और चार आर्य सत्य मनुष्य को दुखों से मुक्ति का व्यावहारिक मार्ग दिखाते हैं।",
                explainSimplyEn = "Wear clean white clothes, meditate for 15-20 minutes in silence, light a candle of peace, and practice mindful breathing to calm your thoughts.",
                explainSimplyHi = "सफेद वस्त्र पहनें, 15-20 मिनट शांति से बैठकर अपनी सांसों पर ध्यान (विपश्यना) लगाएं, शांति का दीपक जलाएं और दूसरों के प्रति मैत्री भाव रखें।",
                muhurat = MuhuratTiming(
                    nameEn = "Vesak Meditation Muhurat",
                    nameHi = "वेसाक ध्यान मुहूर्त",
                    timeEn = "06:00 AM – 08:30 AM",
                    timeHi = "सुबह 06:00 से 08:30 तक",
                    noteEn = "Morning silence is ideal for meditation and Bodhi tree water offering.",
                    noteHi = "प्रातःकाल ध्यान और पीपल के वृक्ष में जल देने का शुभ समय।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare white attire, clean white lotus/jasmine flowers, arrange meditation space.", "सफेद वस्त्र, सफेद फूल और शांत ध्यान स्थल तैयार रखें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Water the sacred Peepal (Bodhi) tree, practice 20 minutes mindful breathing, eat satvik kheer.", "पीपल के पेड़ को जल दें, 20 मिनट ध्यान लगाएं और सात्विक खीर का प्रसाद ग्रहण करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Bodhi Tree Worship", "बोधिवृक्ष पूजन", "Offer clean water to a Peepal tree with reverence for enlightenment.", "पीपल के वृक्ष पर जल व दूध चढ़ाकर तीन बार परिक्रमा करें।"),
                    PujaStep(2, "Mindfulness Meditation", "आनापानसति / विपश्यना ध्यान", "Sit straight, close eyes, observe natural breath without reacting.", "शांत बैठकर अपनी आती-जाती सांसों का निरीक्षण करें और चित्त को शांत करें।"),
                    PujaStep(3, "Triple Refuge (Trisharan)", "त्रिशरण पाठ", "Chant Buddham Sharanam Gacchami with pure devotion.", "त्रिशरण और पंचशील का पाठ करें।", "बुद्धं शरणं गच्छामि। धम्मं शरणं गच्छामि। संघं शरणं गच्छामि॥")
                ),
                supplies = listOf(
                    Supply("White Flowers (Jasmine / White Lotus)", "सफेद पुष्प", "1 bunch", "1 गुच्छा", "Flowers", "फूल"),
                    Supply("White Candle / Brass Diya", "सफेद मोमबत्ती या दीया", "2 pcs", "2 नग", "Light", "दीपक")
                ),
                mantras = listOf(
                    MantraItem(
                        "बुद्धं शरणं गच्छामि। धम्मं शरणं गच्छामि। संघं शरणं गच्छामि॥",
                        "Buddham Sharanam Gacchami | Dhammam Sharanam Gacchami | Sangham Sharanam Gacchami",
                        "I take refuge in the Enlightened One (Buddha); I take refuge in the Eternal Truth (Dhamma); I take refuge in the Spiritual Community (Sangha).",
                        "मैं बुद्ध की शरण में जाता हूं, मैं धर्म की शरण में जाता हूं, मैं संघ की शरण में जाता हूं।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Engaging in arguments or harsh speech", "क्रोध या कठोर वाणी का प्रयोग", "Buddha's teaching is built on Right Speech (Samma Vaca) and inner calmness.", "आज के दिन अपनी वाणी को शांत और सकारात्मक रखें।")
                )
            ),

            // 23. EID-UL-FITR
            Festival(
                id = "eid_fitr",
                nameEn = "Eid-ul-Fitr",
                nameHi = "ईद-उल-फ़ित्र",
                date2026En = "20 Mar 2026*",
                date2026Hi = "20 मार्च 2026*",
                date2025En = "31 Mar 2025*",
                date2027En = "10 Mar 2027*",
                monthEn = "March",
                monthHi = "मार्च",
                categoryEn = "National",
                categoryHi = "राष्ट्रीय व सर्वधर्म पर्व",
                emoji = "🌙",
                summaryEn = "Joyous conclusion of the holy month of Ramadan celebrated with morning prayers, charity (Zakat-al-Fitr), and sweet Sewaiyan.",
                summaryHi = "रमज़ान के पवित्र महीने की पूर्णता का उत्सव, अमन-चैन की नमाज़, दान (ज़कात) और मीठी सेवइयों की मिठास।",
                significanceEn = "Eid-ul-Fitr marks the end of Ramadan's month of dawn-to-dusk fasting, self-restraint, and devotion. Before the special community prayers, believers give Zakat-al-Fitr (charity to ensure the poor can also celebrate). Friends and neighbors embrace and share warm Eid greetings.",
                significanceHi = "यह रमज़ान के रोज़ों की समाप्ति पर अल्लाह का शुक्र अदा करने का दिन है। ईद की नमाज़ से पहले गरीबों को फितरा (दान) दिया जाता है ताकि हर कोई खुशी में शरीक हो सके। लोग गले मिलकर 'ईद मुबारक' कहते हैं और सेवइयां बांटते हैं।",
                explainSimplyEn = "Celebrate with your friends and neighbors! Greet with a warm 'Eid Mubarak', share sweet sheer khurma / sewaiyan, and contribute charity to those in need to spread happiness.",
                explainSimplyHi = "भाईचारे और सौहार्द का सुंदर त्योहार! अपने दोस्तों को 'ईद मुबारक' बोलें, मीठी सेवइयों का आनंद लें और जरूरतमंदों की मदद कर खुशियां बांटें।",
                muhurat = MuhuratTiming(
                    nameEn = "Eid Prayer Timing (Post-Sunrise)",
                    nameHi = "ईद की नमाज़ का समय",
                    timeEn = "07:30 AM – 09:00 AM",
                    timeHi = "सुबह 07:30 से 09:00 तक",
                    noteEn = "Dates depend upon crescent moon sighting (Chand Raat).",
                    noteHi = "ईद की सटीक तारीख चांद दिखने (चांद रात) पर निर्भर करती है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("Chand Raat", "चांद रात", "Sight crescent moon, prepare vermicelli (Sewaiyan), dry fruits, and milk for Sheer Khurma.", "चांद के दीदार के बाद शीर-खुरमा और सेवइयों की तैयारी करें।"),
                    PrepTask("Eid Morning", "ईद की सुबह", "Bathe, wear clean traditional kurta, give Zakat-al-fitr charity, offer prayers, exchange hugs.", "स्वच्छ वस्त्र पहनें, गरीबों को दान दें, नमाज़ अदा कर एक-दूसरे से गले मिलें।")
                ),
                steps = listOf(
                    PujaStep(1, "Charity (Zakat-al-Fitr)", "फितरा दान", "Distribute grains or funds to poor families so everyone enjoys the celebration.", "ईद की नमाज़ से पहले गरीबों को अनाज या आर्थिक मदद दें।"),
                    PujaStep(2, "Sharing Sheer Khurma", "सेवइयां व शीर-खुरमा", "Prepare rich milk, vermicelli, dates, and nuts, offering it to guests of all faiths.", "मेवे और दूध से बनी मीठी सेवइयां सपरिवार और पड़ोसियों के साथ बांटकर खाएं।")
                ),
                supplies = listOf(
                    Supply("Fine Roasted Vermicelli (Sewaiyan)", "भुनी हुई बारीक सेवइयां", "500 g", "500 ग्राम", "Food", "सेवइयां"),
                    Supply("Full Cream Milk & Dry Dates (Chhuhara)", "दूध व सूखे छुहारे", "1 litre + 100g", "1 लीटर + 100 ग्राम", "Food", "सामग्री")
                ),
                mantras = listOf(
                    MantraItem(
                        "ईद मुबारक! तक़ब्बल अल्लाहु मिन्ना व मिन्कुम॥",
                        "Eid Mubarak! Taqabbal Allahu Minna Wa Minkum",
                        "Blessed Eid! May the Almighty accept good deeds from us and from you.",
                        "ईद मुबारक! ईश्वर हम सबकी नेक दुआओं और इबादत को कुबूल फरमाएं।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Forgetting charity before the celebration", "बिना दान दिए केवल स्वयं खुशी मनाना", "The spiritual heart of Eid lies in ensuring no neighbor goes hungry on this day.", "ईद का असली संदेश यह है कि हमारे आसपास कोई भूखा या उदास न रहे।")
                )
            ),

            // 24. EID-UL-ADHA
            Festival(
                id = "eid_adha",
                nameEn = "Eid-ul-Adha (Bakrid)",
                nameHi = "ईद-उल-अज़हा (बकरीद)",
                date2026En = "27 May 2026*",
                date2026Hi = "27 मई 2026*",
                date2025En = "6 Jun 2025*",
                date2027En = "17 May 2027*",
                monthEn = "May",
                monthHi = "मई",
                categoryEn = "National",
                categoryHi = "राष्ट्रीय व सर्वधर्म पर्व",
                emoji = "🌙",
                summaryEn = "Festival of sacrifice, faith, and sharing food with the underprivileged and family.",
                summaryHi = "त्याग, समर्पण और जरूरतमंदों के साथ भोजन साझा करने का पावन पर्व।",
                significanceEn = "Commemorates Prophet Ibrahim's supreme readiness to surrender all personal attachment for the Almighty. The meat or food prepared is traditionally divided into three equal portions: one for the poor, one for friends/relatives, and one for the household.",
                significanceHi = "यह पर्व हजरत इब्राहीम के त्याग और समर्पण की याद दिलाता है। इस दिन तैयार भोजन और कुर्बानी के तीन हिस्से किए जाते हैं: एक हिस्सा गरीबों के लिए, एक रिश्तेदारों के लिए और एक घर के लिए।",
                explainSimplyEn = "A solemn celebration of selflessness and sharing. The primary message is to sacrifice selfishness, share abundance with the poor, and greet everyone with love.",
                explainSimplyHi = "निःस्वार्थता और त्याग का पर्व। अपने स्वार्थ और अहंकार का त्याग करें और जरूरतमंदों के साथ भोजन व खुशियां बांटें।",
                muhurat = MuhuratTiming(
                    nameEn = "Morning Eid Prayer",
                    nameHi = "ईद की सुबह की नमाज़",
                    timeEn = "07:00 AM – 08:30 AM",
                    timeHi = "सुबह 07:00 से 08:30 तक",
                    noteEn = "Dates depend on lunar moon sighting.",
                    noteHi = "तारीख चांद के दीदार के अनुसार तय होती है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare clean attire, arrange distribution packages for charity.", "दान और भोजन वितरण की व्यवस्था करें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Offer morning community prayers, share meals with the destitute.", "सुबह की नमाज़ के बाद गरीबों में भोजन और कपड़े बांटें।")
                ),
                steps = listOf(
                    PujaStep(1, "Community Prayer", "सामूहिक प्रार्थना", "Gather for morning prayers for peace and benevolence.", "शांति और सद्भाव के लिए सुबह की प्रार्थना करें।"),
                    PujaStep(2, "Three-part Sharing", "तीन भागों में भोजन वितरण", "Distribute one third to poor, one third to friends, retain one third for home.", "भोजन का एक तिहाई हिस्सा गरीबों को प्रेमपूर्वक दें।")
                ),
                supplies = listOf(
                    Supply("Food packages for charity", "दान हेतु भोजन पैकेट", "As per capacity", "यथाशक्ति", "Charity", "दान सामग्री")
                ),
                mantras = listOf(
                    MantraItem(
                        "ईद-उल-अज़हा मुबारक॥",
                        "Eid-ul-Adha Mubarak",
                        "Wishing you a peaceful and blessed Eid of sacrifice and charity.",
                        "त्याग और सेवा के इस पर्व पर आपको हार्दिक शुभकामनाएं।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Ignoring the underprivileged during festivities", "गरीबों को भूलकर केवल अपनों में सीमित रहना", "The festival's sole spiritual purpose is feeding and comforting the needy.", "इस पर्व की आत्मा जरूरतमंदों की सेवा और भोजन कराने में है।")
                )
            ),

            // 25. CHRISTMAS
            Festival(
                id = "christmas",
                nameEn = "Christmas",
                nameHi = "क्रिसमस (बड़ा दिन)",
                date2026En = "25 Dec 2026",
                date2026Hi = "25 दिसंबर 2026",
                date2025En = "25 Dec 2025",
                date2027En = "25 Dec 2027",
                monthEn = "December",
                monthHi = "दिसंबर",
                categoryEn = "National",
                categoryHi = "राष्ट्रीय व सर्वधर्म पर्व",
                emoji = "🎄",
                summaryEn = "Celebrating the birth of Jesus Christ with Christmas trees, carols, midnight mass, and spreading joy.",
                summaryHi = "प्रभु यीशु मसीह का जन्मोत्सव, क्रिसमस ट्री, कैरोल गान, आधी रात की प्रार्थना और प्रेम का संदेश।",
                significanceEn = "Christmas celebrates the birth of Jesus Christ in Bethlehem, bringing message of peace, unconditional love, humility, and forgiveness to all mankind.",
                significanceHi = "25 दिसंबर को प्रभु ईसा मसीह के जन्म की स्मृति में क्रिसमस मनाया जाता है। उनका जीवन क्षमा, प्रेम, शांति और मानवता की सेवा का अमर संदेश देता है।",
                explainSimplyEn = "Decorate an evergreen Christmas tree with warm lights and star on top, bake or buy plum cake, sing carols, and exchange thoughtful gifts with family and friends.",
                explainSimplyHi = "घर में क्रिसमस ट्री को रंग-बिरंगी लाइटों और सितारों से सजाएं, प्लम केक बनाएं या खाएं, और प्रियजनों को उपहार देकर प्रेम और खुशियां फैलाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Midnight Mass Timing",
                    nameHi = "मध्यरात्रि प्रार्थना (मिडनाइट मास)",
                    timeEn = "11:30 PM – 12:30 AM (Christmas Eve)",
                    timeHi = "क्रिसमस की पूर्व संध्या रात 11:30 से 12:30 तक",
                    noteEn = "Traditional church service welcoming Christmas day.",
                    noteHi = "गिरजाघरों में विशेष प्रार्थना सभा।"
                ),
                preparationTimeline = listOf(
                    PrepTask("3 Days Before", "3 दिन पहले", "Decorate tree with lights, baubles, and star; bake fruit cake.", "क्रिसमस ट्री सजाएं और केक की व्यवस्था करें।"),
                    PrepTask("Christmas Eve", "क्रिसमस की पूर्व संध्या", "Attend church mass or light candles at home, place presents under the tree.", "मोमबत्तियां जलाएं और ट्री के नीचे उपहार रखें।")
                ),
                steps = listOf(
                    PujaStep(1, "Candle Lighting & Prayer", "मोमबत्ती प्रज्ज्वलन व प्रार्थना", "Light candles of peace, joy, and hope praying for harmony across the world.", "विश्व शांति और सबके कल्याण के लिए मोमबत्ती जलाकर मौन प्रार्थना करें।"),
                    PujaStep(2, "Sharing Plum Cake", "केक काटना व बांटना", "Cut traditional fruit cake and share with neighbors and children.", "परिवार और मित्रों के साथ केक काटकर खुशियां साझा करें।")
                ),
                supplies = listOf(
                    Supply("Decorated Christmas Tree & Star", "सजावटी क्रिसमस ट्री व सितारा", "1 set", "1 सेट", "Decor", "सजावट"),
                    Supply("Traditional Plum Cake", "पारंपरिक प्लम केक", "500 g", "500 ग्राम", "Food", "केक"),
                    Supply("Decorative Candles", "सफेद व लाल मोमबत्तियां", "2 to 4 pcs", "2-4 नग", "Light", "मोमबत्ती")
                ),
                mantras = listOf(
                    MantraItem(
                        "धरती पर शांति और मनुष्यों में सद्भाव हो! मेरी क्रिसमस॥",
                        "Peace on Earth and Goodwill to all! Merry Christmas",
                        "May divine peace, love, and light fill every heart on earth.",
                        "समस्त संसार में शांति, प्रेम और भाईचारे का वास हो।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Overspending beyond budget", "अनावश्यक दिखावा करना", "Christmas is about simple love, kindness to the poor, and warmth of family.", "दिखावे की जगह प्रेम, सादगी और जरूरतमंदों की मदद पर ध्यान दें।")
                )
            ),

            // 26. NEW YEAR
            Festival(
                id = "newyear",
                nameEn = "New Year's Day",
                nameHi = "नव वर्ष (ग्रेगोरियन कैलेंडर)",
                date2026En = "1 Jan 2026",
                date2026Hi = "1 जनवरी 2026",
                date2025En = "1 Jan 2025",
                date2027En = "1 Jan 2027",
                monthEn = "January",
                monthHi = "जनवरी",
                categoryEn = "National",
                categoryHi = "राष्ट्रीय व सर्वधर्म पर्व",
                emoji = "🎉",
                summaryEn = "Welcoming the calendar year with gratitude for the past, positive resolutions, and temple visits.",
                summaryHi = "बीते वर्ष के प्रति कृतज्ञता, नए वर्ष के शुभ संकल्प, मंदिर दर्शन और सकारात्मक शुरुआत।",
                significanceEn = "A universal day of new beginnings across the globe. In Indian homes, people begin January 1 by visiting local temples or places of worship, praying for health, career success, and peaceful harmony.",
                significanceHi = "कैलेंडर वर्ष का पहला दिन नई ऊर्जा, आशा और लक्ष्यों को निर्धारित करने का अवसर है। भारतीय परिवारों में सुबह मंदिर जाकर भगवान के दर्शन कर मंगलमय वर्ष की प्रार्थना की जाती है।",
                explainSimplyEn = "Begin the new year with clarity. Wake up early, write down 3 realistic spiritual or healthy habits you want to cultivate, visit a holy place, and bless your family.",
                explainSimplyHi = "नए साल की शुरुआत सादगी से करें। सुबह उठकर भगवान का धन्यवाद करें, अपने स्वास्थ्य और स्वभाव को बेहतर बनाने के 3 अच्छे संकल्प लें और दिन सकारात्मकता से बिताएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Prabhat Puja Muhurat",
                    nameHi = "प्रभात पूजन मुहूर्त",
                    timeEn = "07:00 AM – 09:30 AM",
                    timeHi = "सुबह 07:00 से 09:30 तक",
                    noteEn = "Morning temple darshan starts the year auspiciously.",
                    noteHi = "सुबह के समय देव दर्शन वर्षभर सकारात्मक ऊर्जा देता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("31 Dec Evening", "31 दिसंबर शाम", "Reflect on lessons of the past year with gratitude, write new intentions.", "बीते वर्ष की सीखों पर विचार करें और नए संकल्प लिखें।"),
                    PrepTask("1 Jan Morning", "1 जनवरी सुबह", "Early bath, light diya at home altar, visit temple, call distant relatives.", "घर के मंदिर में दीपक जलाएं, बड़ों के पैर छुएं और रिश्तेदारों को शुभकामनाएं दें।")
                ),
                steps = listOf(
                    PujaStep(1, "Gratitude Prayer", "कृतज्ञता प्रार्थना", "Bow before God thanking Him for health and family protection through the past year.", "हाथ जोड़कर ईश्वर को पिछले साल की सभी सफलताओं और सुरक्षा के लिए धन्यवाद दें।"),
                    PujaStep(2, "Sankalp for New Year", "नववर्ष संकल्प", "Write down positive commitments to health, learning, and kindness.", "स्वास्थ्य, स्वाध्याय और सेवा का दृढ़ संकल्प लें।")
                ),
                supplies = listOf(
                    Supply("Diya & Ghee for Home Altar", "दीपक और देसी घी", "1 set", "1 सेट", "Puja", "पूजा सामग्री"),
                    Supply("Fresh Flowers & Sweets for Temple Darshan", "मंदिर दर्शन हेतु फूल व मिठाई", "1 thali", "1 थाली", "Offering", "प्रसाद")
                ),
                mantras = listOf(
                    MantraItem(
                        "सर्वे भवन्तु सुखिनः सर्वे सन्तु निरामयाः। सर्वे भद्राणि पश्यन्तु मा कश्चिद्दुःखभाग्भवेत्॥",
                        "Sarve Bhavantu Sukhinah Sarve Santu Niramayah | Sarve Bhadrani Pashyantu Ma Kashchid Duhkhabhag Bhavet",
                        "May all beings everywhere be happy; may all beings be free from disease and pain; may all perceive what is auspicious; may none suffer.",
                        "सभी सुखी हों, सभी निरोगी हों, सभी का कल्याण हो और किसी को भी दुःख न मिले।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Making unrealistic harsh resolutions that are abandoned in a week", "ऐसे असंभव संकल्प लेना जो कुछ दिनों में टूट जाएं", "Choose one or two small daily habits like 10 minutes of daily walk or reading.", "छोटे और व्यावहारिक संकल्प लें जिन्हें आप पूरे साल सरलता से निभा सकें।")
                )
            ),

            // 27. LOHRI
            Festival(
                id = "lohri",
                nameEn = "Lohri",
                nameHi = "लोहड़ी",
                date2026En = "13 Jan 2026",
                date2026Hi = "13 जनवरी 2026",
                date2025En = "13 Jan 2025",
                date2027En = "13 Jan 2027",
                monthEn = "January",
                monthHi = "जनवरी",
                categoryEn = "Regional",
                categoryHi = "फसल व पारंपरिक पर्व",
                emoji = "🔥",
                summaryEn = "Vibrant winter harvest bonfire celebration of Punjab, worshipping Agni with peanuts, rewari, popcorn, and folk songs.",
                summaryHi = "पंजाब का पारंपरिक शीतकालीन फसल उत्सव, अग्नि देव की पूजा, रेवड़ी-मूंगफली की आहुति और भांगड़ा-गिद्दा।",
                significanceEn = "Lohri marks the harvesting of rabi crops (winter wheat and sugarcane) in North India. Families gather around a community bonfire, tossing sesame, jaggery, peanuts, and popcorn into the flames while thanking Agni Dev for warmth and abundance.",
                significanceHi = "लोहड़ी उत्तर भारत में नई फसल के स्वागत और शीत ऋतु की विदाई का प्रतीक है। रात को अलाव जलाकर अग्नि देव को रेवड़ी, तिल, मूंगफली और मक्का (पॉपकॉर्न) अर्पित किया जाता है और दुल्ला भट्टी के लोकगीत गाए जाते हैं।",
                explainSimplyEn = "Gather with family around a warm bonfire in the evening. Toss sesame seeds, peanuts, and rewari into the flames, walk around the fire, enjoy Sarson ka Saag with Makki ki Roti, and dance with joy.",
                explainSimplyHi = "शाम को परिवार के साथ मिलकर अलाव जलाएं। अग्नि में रेवड़ी, गजक, मूंगफली और मक्के के दाने अर्पित कर परिक्रमा करें। सरसों का साग और मक्के की रोटी खाएं और खुशियों से त्योहार मनाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Lohri Bonfire Lighting Time",
                    nameHi = "लोहड़ी अलाव प्रज्ज्वलन समय",
                    timeEn = "06:30 PM – 08:30 PM",
                    timeHi = "शाम 06:30 से 08:30 तक",
                    noteEn = "After dusk when the family gathers around the bonfire.",
                    noteHi = "संध्या काल में तारों के निकलने पर अलाव जलाया जाता है।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Arrange dry wood, cow dung cakes, peanuts (moongphali), rewari, gajak, popcorn.", "सूखी लकड़ियां, रेवड़ी, गजक, भुनी मूंगफली और मक्का के दाने तैयार रखें।"),
                    PrepTask("On the Day Evening", "शाम के समय", "Light the bonfire, perform parikrama with family, sing folk songs.", "अलाव जलाकर अग्नि देव की परिक्रमा करें और प्रसाद बांटें।")
                ),
                steps = listOf(
                    PujaStep(1, "Lighting the Fire", "अग्नि प्रज्ज्वलन", "Light the bonfire with dry wood and cow dung cakes invoking Agni Dev.", "पवित्र अग्नि प्रज्ज्वलित कर अग्नि देव को नमन करें।"),
                    PujaStep(2, "Offering & Parikrama", "आहुति व परिक्रमा", "Walk clockwise around the fire tossing rewari, peanuts, and popcorn into flames.", "अग्नि में तिल, रेवड़ी, मूंगफली की आहुति देते हुए परिक्रमा करें।", "आदर आए, दलिद्दर जाए!"),
                    PujaStep(3, "Traditional Meal", "पारंपरिक भोजन", "Relish hot Sarson ka Saag with Makki ki Roti and fresh white butter.", "सरसों का साग, मक्के की रोटी और गुड़ का आनंद लें।")
                ),
                supplies = listOf(
                    Supply("Dry Wood & Cow Dung Cakes", "सूखी लकड़ी व उपले", "1 bundle", "1 गट्ठा", "Fire", "अलाव"),
                    Supply("Peanuts (Moongphali) & Popcorn (Phulle)", "मूंगफली और खील-पॉपकॉर्न", "500 g each", "500-500 ग्राम", "Offering", "प्रसाद"),
                    Supply("Rewari & Sesame Gajak", "रेवड़ी और तिल की गजक", "500 g", "500 ग्राम", "Sweets", "प्रसाद")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ अग्नये नमः। स्वाहा॥",
                        "Om Agnaye Namah | Svaha",
                        "Salutations to Agni, the divine purifier and sustainer of life.",
                        "समस्त जीवन को ऊर्जा और ऊष्मा देने वाले भगवान अग्नि को नमन।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Using kerosene or plastics to ignite the bonfire", "अलाव में प्लास्टिक या केरोसिन का प्रयोग करना", "Ignite purely with dry twigs, camphor, and wood for clean, smoke-free tradition.", "पर्यावरण की रक्षा हेतु केवल सूखी लकड़ियों और कपूर से ही अलाव जलाएं।")
                )
            ),

            // 28. BAISAKHI
            Festival(
                id = "baisakhi",
                nameEn = "Baisakhi (Vaisakhi)",
                nameHi = "बैसाखी",
                date2026En = "13 Apr 2026",
                date2026Hi = "13 अप्रैल 2026",
                date2025En = "13 Apr 2025",
                date2027En = "14 Apr 2027",
                monthEn = "April",
                monthHi = "अप्रैल",
                categoryEn = "Regional",
                categoryHi = "फसल व खालसा पर्व",
                emoji = "🌾",
                summaryEn = "Joyful spring harvest festival and commemoration of the creation of the Khalsa Panth by Guru Gobind Singh Ji in 1699.",
                summaryHi = "फसल की कटाई का पावन पर्व एवं 1699 में गुरु गोबिंद सिंह जी द्वारा खालसा पंथ की स्थापना का ऐतिहासिक दिवस।",
                significanceEn = "Baisakhi marks the ripening of the golden rabi wheat crop. Historically, on this day in 1699 at Anandpur Sahib, Guru Gobind Singh Ji founded the Khalsa Panth (Panj Pyare), infusing the virtues of courage, equality, and righteousness.",
                significanceHi = "बैसाखी पंजाब व उत्तर भारत में पकी हुई सुनहरी गेहूं की फसल की कटाई का आनंद है। 13 अप्रैल 1699 को श्री गुरु गोबिंद सिंह जी ने आनंदपुर साहिब में खालसा पंथ की स्थापना कर समाज को समानता और निर्भीकता का मार्ग दिखाया था।",
                explainSimplyEn = "Visit the Gurdwara or temple, participate in community service (Kada Prasad / Langar seva), wear bright festive clothes, and celebrate the fruits of hard work and honest farming.",
                explainSimplyHi = "गुरुद्वारे जाएं, कड़ाह प्रसाद ग्रहण करें, लंगर सेवा में हाथ बटाएं, और किसान भाइयों की मेहनत व देश के अन्न भंडार के लिए ईश्वर का धन्यवाद करें।",
                muhurat = MuhuratTiming(
                    nameEn = "Baisakhi Sankranti Muhurat",
                    nameHi = "मेष संक्रांति मुहूर्त",
                    timeEn = "06:10 AM – 11:30 AM",
                    timeHi = "सुबह 06:10 से 11:30 तक",
                    noteEn = "Sun enters Aries (Mesha Rashi) on this morning.",
                    noteHi = "सूर्य देव का मेष राशि में प्रवेश।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Prepare whole wheat flour, sugar, and desi ghee for Kada Prasad.", "कड़ाह प्रसाद के लिए शुद्ध घी, आटा और चीनी तैयार रखें।"),
                    PrepTask("On the Day", "उत्सव के दिन", "Take holy dip, visit Gurdwara for Kirtan, relish Langar with family.", "गुरुद्वारे में कीर्तन सुनें, कड़ाह प्रसाद लें और लंगर में सेवा करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Morning Prayers & Gurdwara Visit", "प्रभात फेरी व गुरुद्वारा दर्शन", "Listen to Gurbani Kirtan, take Karah Prasad with both hands.", "गुरुवाणी कीर्तन सुनें और दोनों हाथ जोड़कर कड़ाह प्रसाद ग्रहण करें।"),
                    PujaStep(2, "Langar Seva", "लंगर सेवा", "Serve meals to visitors irrespective of background in community kitchen.", "लंगर में पंक्ति में बैठकर भोजन परोसने और जल सेवा का पुण्य लाभ लें।")
                ),
                supplies = listOf(
                    Supply("Ingredients for Kada Prasad (Atta, Ghee, Sugar)", "कड़ाह प्रसाद सामग्री (आटा, घी, चीनी)", "1 kg set", "1 सेट", "Prasad", "प्रसाद सामग्री")
                ),
                mantras = listOf(
                    MantraItem(
                        "वाहेगुरु जी का खालसा, वाहेगुरु जी की फतेह॥",
                        "Waheguru Ji Ka Khalsa, Waheguru Ji Ki Fateh",
                        "The Khalsa belongs to the Divine Lord; victory belongs to the Divine Lord.",
                        "खालसा परमात्मा का है और विजय भी परमात्मा की ही है।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Entering holy prayer halls with uncovered head", "सिर बिना ढके प्रार्थना स्थल में जाना", "Always cover your head with a clean scarf or handkerchief as a mark of respect.", "गुरुद्वारे या पूजा स्थल में हमेशा सिर ढककर ही प्रवेश करें।")
                )
            ),

            // 29. VISHU
            Festival(
                id = "vishu",
                nameEn = "Vishu",
                nameHi = "विषु",
                date2026En = "14 Apr 2026",
                date2026Hi = "14 अप्रैल 2026",
                date2025En = "14 Apr 2025",
                date2027En = "14 Apr 2027",
                monthEn = "April",
                monthHi = "अप्रैल",
                categoryEn = "Regional",
                categoryHi = "दक्षिण भारतीय नववर्ष",
                emoji = "🌼",
                summaryEn = "Kerala's traditional New Year marked by the auspicious first sight of dawn (Vishukkani) and elder gifts (Vishu Kaineettam).",
                summaryHi = "केरल का पारंपरिक नववर्ष, भोर में मंगलकारी 'विषुक्कणी' के दर्शन और बड़ों द्वारा 'विषु कैनीट्टम' का आशीर्वाद।",
                significanceEn = "Vishu represents astronomical spring equinox where day and night are equal. Devotees arrange the 'Vishukkani' (a tray containing golden cucumber, rice, gold coin, mirror, and yellow Kani Konna flowers) before Lord Krishna to ensure the year begins with abundance.",
                significanceHi = "विषु का अर्थ है समान दिन और रात (विषुव)। नववर्ष की सुबह आंखें खोलते ही सबसे पहले भगवान श्रीकृष्ण के समक्ष सजे 'विषुक्कणी' (सोना, पीले फूल, खीरा, दर्पण, अक्षत) के दर्शन किए जाते हैं ताकि पूरा वर्ष समृद्ध रहे।",
                explainSimplyEn = "The elders arrange a beautiful golden sight at night. On waking up at dawn, keep your eyes closed until you are led to the altar to open your eyes to Lord Krishna, golden flowers, and fruits (Vishukkani). Elders then gift coins (Kaineettam) to children.",
                explainSimplyHi = "नववर्ष की सुबह आंखें बंद करके पूजा स्थान पर जाएं और सबसे पहले भगवान कृष्ण और सुनहरे फूलों के दर्शन करें। इसके बाद घर के बुजुर्ग छोटों को सिक्के और आशीर्वाद (कैनीट्टम) देते हैं।",
                muhurat = MuhuratTiming(
                    nameEn = "Vishukkani Viewing Muhurat (Brahma Muhurat)",
                    nameHi = "विषुक्कणी दर्शन मुहूर्त (ब्रह्म मुहूर्त)",
                    timeEn = "04:30 AM – 06:15 AM",
                    timeHi = "भोर 04:30 से 06:15 तक",
                    noteEn = "First sight viewed immediately upon waking at dawn.",
                    noteHi = "सुबह नींद से जागते ही सबसे पहले कणी के दर्शन किए जाते हैं।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before Night", "एक दिन पूर्व रात्रि", "Arrange Uruli (bell metal bowl) with rice, golden cucumber (Kani Vellarikka), betel leaves, mirror, Konna flowers.", "कांसे की थाली में ककड़ी, पीले कणी कोन्ना के फूल, सिक्का, दर्पण और चावल सजाएं।"),
                    PrepTask("On the Day Dawn", "विषु की भोर", "Guide family members with closed eyes to view Vishukkani, distribute Kaineettam coins.", "आंखें बंद कर पूजा स्थल तक आएं, कणी दर्शन करें और बड़ों से सिक्के प्राप्त करें।")
                ),
                steps = listOf(
                    PujaStep(1, "Vishukkani Darshan", "विषुक्कणी दर्शन", "Open eyes to the radiant reflection of Lord Krishna, lit oil lamps, and golden flowers.", "सुबह आंखें खोलकर श्रीकृष्ण की प्रतिमा, दीये की लौ और सुनहरे फूलों के दर्शन करें।"),
                    PujaStep(2, "Vishu Kaineettam", "विषु कैनीट्टम", "Elders present coins and currency notes to children wishing them lifelong fortune.", "घर के बड़े सदस्य बच्चों को सिक्के और आशीर्वाद देकर समृद्धि की कामना करें।"),
                    PujaStep(3, "Vishu Sadya", "विषु सद्या", "Enjoy a festive meal featuring sweet, sour, salty, and bitter dishes reflecting life's variety.", "सपरिवार बैठकर विषु सद्या का पारंपरिक भोजन करें।")
                ),
                supplies = listOf(
                    Supply("Yellow Kani Konna Flowers (Golden Shower)", "कणी कोन्ना के पीले फूल", "1 bunch", "1 गुच्छा", "Flowers", "फूल"),
                    Supply("Golden Cucumber (Kani Vellarikka)", "सुनहरी ककड़ी", "1 pc", "1 नग", "Produce", "फल"),
                    Supply("Valkannadi (Traditional Metal Hand Mirror)", "छोटा दर्पण", "1 pc", "1 नग", "Sacred", "दर्पण"),
                    Supply("Coins for Kaineettam", "कैनीट्टम हेतु नए सिक्के", "Few coins", "कुछ सिक्के", "Blessing", "सिक्के")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ नमो नारायणाय॥",
                        "Om Namo Narayanaya",
                        "Salutations to Lord Narayana, sovereign preserver of creation.",
                        "भगवान श्री नारायण को सादर प्रणाम, यह नववर्ष सबके लिए कल्याणकारी हो।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Opening eyes before reaching the altar", "पूजा स्थल पहुंचने से पहले आंखें खोल लेना", "The sacred beauty of Vishu is that your very first visual impression of the new year is divine.", "विषु की परंपरा यही है कि साल का पहला दृश्य भगवान और समृद्धि का ही हो।")
                )
            ),

            // 30. CHAITRA NAVRATRI
            Festival(
                id = "chaitra_navratri",
                nameEn = "Chaitra Navratri",
                nameHi = "चैत्र नवरात्रि",
                date2026En = "19–27 Mar 2026",
                date2026Hi = "19–27 मार्च 2026",
                date2025En = "30 Mar – 7 Apr 2025",
                date2027En = "7–15 Apr 2027",
                monthEn = "March",
                monthHi = "मार्च",
                categoryEn = "Navratri",
                categoryHi = "नवरात्रि",
                emoji = "🌺",
                summaryEn = "Nine days of spring devotion worshipping Maa Durga's nine forms, culminating on Ram Navami.",
                summaryHi = "वसंत ऋतु की पावन नवरात्रि, मां दुर्गा के नौ रूपों की उपासना एवं राम नवमी पर पूर्णाहुति।",
                significanceEn = "Beginning on the Hindu New Year (Chaitra Shukla Pratipada), Chaitra Navratri marks seasonal transition from spring to summer. It harmonizes body energy through satvik fasting and aligns spiritual focus before celebrating the birth of Lord Rama on the ninth day.",
                significanceHi = "चैत्र शुक्ल प्रतिपदा से शुरू होने वाली यह नवरात्रि वसंत ऋतु के संधिकाल में आती है। नौ दिनों तक मां भगवती के नौ स्वरूपों की साधना कर नवमी के दिन श्री राम जन्मोत्सव के साथ यह अनुष्ठान पूर्ण होता है।",
                explainSimplyEn = "The spring counterpart of Navratri. Cleanse your body with seasonal light fasting, sow barley seeds, recite the Devi Kavacham, and celebrate both Maa Durga and Lord Rama.",
                explainSimplyHi = "वसंत ऋतु की नवरात्रि आपके शरीर और मन को नई ऊर्जा देती है। सात्विक व्रत रखें, घटस्थापना करें, मां दुर्गा की पूजा करें और नवमी पर कन्या पूजन व राम जन्मोत्सव मनाएं।",
                muhurat = MuhuratTiming(
                    nameEn = "Ghatasthapana Muhurat (Spring)",
                    nameHi = "चैत्र घटस्थापना मुहूर्त",
                    timeEn = "06:24 AM – 10:18 AM",
                    timeHi = "सुबह 06:24 से 10:18 तक",
                    noteEn = "First day of Chaitra Shukla Pratipada.",
                    noteHi = "चैत्र शुक्ल प्रतिपदा की प्रातः घटस्थापना करें।"
                ),
                preparationTimeline = listOf(
                    PrepTask("1 Day Before", "1 दिन पहले", "Clean puja space, procure barley, red cloth, clay pot, Durga Saptashati.", "पूजा घर साफ करें, जौ, कलश और दुर्गा सप्तशती तैयार रखें।"),
                    PrepTask("Day 1 to 9", "प्रतिपदा से नवमी", "Daily aarti, chanting, satvik falahar fasting, Kanya Pujan on Ashtami/Navami.", "दैनिक पूजा, अखंड दीप, अष्टमी-नवमी पर कन्या पूजन और राम नवमी उत्सव।")
                ),
                steps = listOf(
                    PujaStep(1, "Kalash Sthapana", "कलश स्थापना", "Sow barley seeds in earthen bowl, install Kalash with holy water and coconut.", "मिट्टी के सकोरे में जौ बोएं, मध्य में कलश स्थापित करें।"),
                    PujaStep(2, "Devi Aavahan & Stuti", "देवी आवाहन व स्तुति", "Offer red flowers, red chunri, incense, and chant Durga Chalisa.", "मां दुर्गा को लाल चुनरी व पुष्प अर्पित कर चालीसा का पाठ करें।", "या देवी सर्वभूतेषु मातृरूपेण संस्थिता। नमस्तस्यै नमस्तस्यै नमस्तस्यै नमो नमः॥"),
                    PujaStep(3, "Kanya Pujan & Ram Janma", "कन्या पूजन व राम जन्मोत्सव", "Worship 9 young girls on Ashtami/Navami, celebrate Ram Janmotsav at noon.", "कन्याओं का आदरपूर्वक पूजन कर भोजन कराएं और दोपहर 12 बजे राम जन्मोत्सव मनाएं।")
                ),
                supplies = listOf(
                    Supply("Barley Seeds (Jowar) & Soil", "जौ और स्वच्छ मिट्टी", "100 g", "100 ग्राम", "Setup", "घटस्थापना"),
                    Supply("Red Chunri & Puja Flowers", "लाल चुनरी व पुष्प", "1 set", "1 सेट", "Puja", "मां का श्रृंगार"),
                    Supply("Kale Chane & Sooji for Prasad", "काले चने व सूजी", "500 g", "500 ग्राम", "Food", "भोग सामग्री")
                ),
                mantras = listOf(
                    MantraItem(
                        "ॐ ऐं ह्रीं क्लीं चामुण्डायै विच्चे॥",
                        "Om Aim Hreem Kleem Chamundayai Vichche",
                        "The sacred Navarna Mantra invoking the united power of Saraswati, Lakshmi, and Kali.",
                        "मां सरस्वती, महालक्ष्मी और महाकाली की सम्मिलित शक्ति का पावन नवार्ण मंत्र।"
                    )
                ),
                mistakes = listOf(
                    CommonMistake("Consuming non-satvik foods (onion/garlic) during fasting", "व्रत काल में तामसिक भोजन का प्रयोग", "Keep diet completely free of onion, garlic, alcohol, and refined salt; use rock salt (Sendha namak).", "नौ दिनों तक प्याज-लहसुन से दूर रहें और केवल सेंधा नमक व सात्विक फलाहार लें।")
                )
            )
        )
    }

    fun getFestivalById(id: String): Festival? {
        return festivals.find { it.id == id }
    }

    fun searchFestivals(query: String, selectedYear: String = "2026"): List<Festival> {
        if (query.isBlank()) return festivals
        val q = query.trim().lowercase()
        return festivals.filter {
            it.nameEn.lowercase().contains(q) ||
            it.nameHi.contains(q) ||
            it.summaryEn.lowercase().contains(q) ||
            it.summaryHi.contains(q) ||
            it.categoryEn.lowercase().contains(q) ||
            it.categoryHi.contains(q) ||
            it.monthEn.lowercase().contains(q) ||
            it.monthHi.contains(q)
        }
    }
}
