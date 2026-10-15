package com.shubhutsav.app.data

data class SacredVrat(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val nameMr: String,
    val deity: String,
    val frequency: String,
    val significanceEn: String,
    val significanceHi: String,
    val significanceMr: String,
    val allowedFoods: List<String>,
    val prohibitedFoods: List<String>,
    val paranaGuideEn: String,
    val paranaGuideHi: String,
    val paranaGuideMr: String,
    val kathaTitle: String,
    val kathaStoryEn: String,
    val kathaStoryHi: String,
    val kathaStoryMr: String,
    val pujaVidhiSteps: List<String>
)

object VratRepository {

    val vrats: List<SacredVrat> = listOf(
        SacredVrat(
            id = "ekadashi",
            nameEn = "Ekadashi Vrat (हर एकादशी व्रत)",
            nameHi = "एकादशी महाव्रत (विष्णु आराधना)",
            nameMr = "एकादशी महाव्रत (विष्णू भक्ती)",
            deity = "Lord Vishnu",
            frequency = "Twice every month (Shukla & Krishna Paksha)",
            significanceEn = "The supreme among all fasts. Purifies mind and body, dissolves sins, and grants spiritual liberation (Moksha).",
            significanceHi = "समस्त व्रतों में शिरोमणि। भगवान विष्णु की कृपा प्राप्ति, आत्मिक शुद्धि एवं मोक्ष प्रदायक पावन व्रत।",
            significanceMr = "सर्व व्रतांमध्ये श्रेष्ठ मानले गेलेले व्रत. विष्णू कृपेने आत्मशुद्धी व मोक्ष प्राप्ती होते.",
            allowedFoods = listOf(
                "Sabudana (साबूदाना खिचड़ी / खीर)",
                "Singhara / Kuttu Atta (सिंघाड़े व कुट्टू का आटा)",
                "Sendha Namak (सेंधा नमक / Rock Salt)",
                "Fresh Fruits & Pure Cow Milk (फल, कच्चा नारियल, गाय का दूध)",
                "Dry Fruits & Makhana (काजू, बादाम, मखाना)"
            ),
            prohibitedFoods = listOf(
                "Rice in any form (चावल / भात पूर्णतः वर्जित)",
                "Wheat, Grains, Pulses (गेहूं, दालें, अन्न)",
                "Onion, Garlic, Non-veg (प्याज, लहसुन, तामसिक भोजन)",
                "Common Table Salt (सादा समुद्री नमक)"
            ),
            paranaGuideEn = "Parana (breaking the fast) must be observed next morning during Dwadashi Tithi after Sunrise and before Hari Vasara ends. Drink water with Tulsi leaf followed by satvik Prasad.",
            paranaGuideHi = "द्वादशी तिथि के सूर्योदय के उपरांत एवं हरि वासर समाप्त होने से पूर्व पारण करें। तुलसी पत्र युक्त जल पीकर सात्विक भोजन से व्रत खोलें।",
            paranaGuideMr = "द्वादशीच्या सूर्योदयानंतर व हरि वासर संपण्यापूर्वी तुळशीचे पान व पाणी घेऊन पारण करावे.",
            kathaTitle = "एकादशी व्रत महात्म्य कथा",
            kathaStoryEn = "In ancient Satya Yuga, a terrible demon named Muru defeated the Gods. Lord Vishnu fought him for a thousand years and rested in a cave in Badrikashram. When Muru attempted to strike the sleeping Lord, a radiant maiden manifested from Lord Vishnu's divine energy and burned Muru to ashes with her glance. Pleased, Lord Vishnu named her Ekadashi and granted the boon that anyone fasting on her day will be liberated from all past sins and attain Vaikuntha.",
            kathaStoryHi = "सतयुग में मुर नामक महाबली दैत्य ने देवताओं को परास्त कर स्वर्ग पर अधिकार कर लिया था। भगवान विष्णु ने मुर से दीर्घ युद्ध किया और बदरिकाश्रम की गुफा में विश्राम करने लगे। मुर ने जब सोते हुए प्रभु पर प्रहार करना चाहा, तभी भगवान के तेज से एक दिव्य कन्या प्रकट हुई जिसने हुंकार से मुर को भस्म कर दिया। प्रसन्न होकर प्रभु ने उस कन्या को 'एकादशी' नाम दिया और वरदान दिया कि जो भी इस दिन उपवास करेगा, वह समस्त पापों से मुक्त होकर विष्णुलोक प्राप्त करेगा।",
            kathaStoryMr = "सतयुगात मुर नावाच्या राक्षसाने देवांना जिंकले. भगवान विष्णूंनी त्याच्याशी युद्ध करून बदरिकाश्रमात विश्राम केला. मुर जेव्हा विष्णूंवर वार करणार होता, तेव्हा विष्णूंच्या तेजातून एक देवी प्रकट झाली व तिने एका दृष्टीक्षेपात त्या राक्षसाचा वध केला. विष्णूंनी तिला 'एकादशी' नाव दिले आणि वरदान दिले की जो कोणी या दिवशी उपवास करेल, तो सर्व पापांतून मुक्त होऊन मोक्ष पावेल.",
            pujaVidhiSteps = listOf(
                "दशमी की रात्रि से सात्विक आचरण रखें।",
                "प्रातः काल स्नान कर भगवान विष्णु के समक्ष व्रत का संकल्प लें।",
                "प्रभु को पीले पुष्प, चंदन, अक्षत और तुलसी दल अर्पित करें।",
                "ॐ नमो भगवते वासुदेवाय मंत्र का 108 बार जाप करें।",
                "संध्याकाल में विष्णु सहस्त्रनाम या एकादशी महात्म्य का पाठ करें।",
                "द्वादशी के सूर्योदय के पश्चात ब्राह्मण या जरूरतमंद को दान देकर पारण करें।"
            )
        ),
        SacredVrat(
            id = "pradosh",
            nameEn = "Pradosh Vrat (प्रदोष व्रत)",
            nameHi = "प्रदोष व्रत (महादेव शिव आराधना)",
            nameMr = "प्रदोष व्रत (शिव आराधना)",
            deity = "Lord Shiva & Mata Parvati",
            frequency = "Trayodashi Tithi (Twice monthly in evening)",
            significanceEn = "Observed during sunset twilight (Pradosh Kaal) on the 13th lunar day. Removes health issues, debts, and bestows peace and longevity.",
            significanceHi = "त्रयोदशी के सायंकाल (प्रदोष काल) में भगवान शिव माता पार्वती सहित कैलाश पर आनंद नृत्य करते हैं। यह व्रत समस्त रोगों, ऋण एवं संकटों का निवारण करता है।",
            significanceMr = "त्रयोदशीच्या संध्याकाळी प्रदोष काळात भगवान शिव प्रसन्न मुद्रेत असतात. या व्रताने दुःख व रोगांचा नाश होतो.",
            allowedFoods = listOf(
                "Milk, Curd, Panchamrit (दूध, दही, पंचामृत)",
                "Seasonal Fruits (ऋतु फल - सेब, केला, अनार)",
                "Waternuts / Sabudana in small measure (सिंघाड़ा, फलाहार)",
                "Water, Coconut water (जल, नारियल पानी)"
            ),
            prohibitedFoods = listOf(
                "All grains and cereals (अन्न, आटा, दालें)",
                "Heavy oily food (भारी तला-भुना भोजन)",
                "Spices and regular salt (सादा नमक, मिर्च-मसाले)"
            ),
            paranaGuideEn = "Perform Shiva Abhishekam during Pradosh Kaal (45 mins before to 45 mins after sunset). You may break the fast with light fruit prasad after the evening aarti.",
            paranaGuideHi = "प्रदोष काल (सूर्यास्त से 45 मिनट पूर्व व पश्चात) में रुद्राभिषेक या शिव पूजन के उपरांत आरती कर फलाहारी प्रसाद से व्रत पूर्ण करें।",
            paranaGuideMr = "सूर्यास्ताच्या वेळी प्रदोष काळात शिवलिंगावर जलाभिषेक व बेलपत्र अर्पण करून आरतीनंतर फलाहार घ्यावा.",
            kathaTitle = "प्रदोष व्रत पौराणिक कथा",
            kathaStoryEn = "Once, a poor widowed Brahmani wandered with her child. She found an orphaned prince whose kingdom was seized by enemies. The Brahmani learned about the sacred Pradosh Vrat from Sage Shandilya and performed it with devotion. By Lord Shiva's grace, Gandharvas helped the prince regain his lost kingdom, and the Brahmani's son became the prime minister, freeing the family from poverty forever.",
            kathaStoryHi = "प्राचीन काल में एक दरिद्र ब्राह्मणी अपने पुत्र के साथ भिक्षा मांगकर जीवन यापन करती थी। मार्ग में उसे एक विदर्भ देश का राजकुमार मिला जिसका राज्य शत्रुओं ने छीन लिया था। ब्राह्मणी ने महर्षि शांडिल्य से प्रदोष व्रत की महिमा सुनी और दोनों बालकों सहित नियमपूर्वक व्रत किया। भगवान शिव की कृपा से गंधर्वों की सहायता से राजकुमार को पुनः अपना राज्य मिला और ब्राह्मणी के परिवार का समस्त कष्ट समाप्त हो गया।",
            kathaStoryMr = "एका गरीब ब्राह्मणीने महर्षी शांडिल्यांच्या उपदेशानुसार प्रदोष व्रत केले. भगवान शंकराच्या कृपेने तिच्या आश्रयाला आलेल्या अनाथ राजपुत्राला त्याचे हरवलेले राज्य परत मिळाले आणि त्या कुटुंबाचे सर्व दारिद्र्य नष्ट झाले.",
            pujaVidhiSteps = listOf(
                "दिनभर शांत चित्त से 'ॐ नमः शिवाय' का स्मरण करें।",
                "सायंकाल सूर्यास्त से पूर्व पुनः स्वच्छ वस्त्र धारण करें।",
                "शिवलिंग पर कच्चा दूध, गंगाजल, बेलपत्र, धतूरा और भस्म अर्पित करें।",
                "दीपक प्रज्वलित कर प्रदोष व्रत कथा सुनें।",
                "कपूर से आरती करें और प्रसाद ग्रहण करें।"
            )
        ),
        SacredVrat(
            id = "sankashti_chaturthi",
            nameEn = "Sankashti Chaturthi (संकष्टी चतुर्थी)",
            nameHi = "संकष्टी श्री गणेश चतुर्थी व्रत",
            nameMr = "संकष्टी चतुर्थी व्रत (चंद्र दर्शन)",
            deity = "Lord Ganesha",
            frequency = "Monthly (Krishna Paksha 4th Day)",
            significanceEn = "Dispels all life crises, financial difficulties, and delays in auspicious work through Ganesha's grace and Moon sighting (Chandra Darshan).",
            significanceHi = "जीवन के बड़े से बड़े संकट को हरने वाला व्रत। रात्रि में चंद्र दर्शन एवं अर्घ्य देने के पश्चात ही व्रत पूर्ण होता है।",
            significanceMr = "संकटांचे निवारण करणारे श्री गणेशाचे अत्यंत प्रभावी व्रत. रात्री चंद्रोदयानंतर चंद्राला अर्घ्य देऊन उपवास सोडला जातो.",
            allowedFoods = listOf(
                "Modak, Ladoo prasad (मोदक, तिल-गुड़ के लड्डू)",
                "Fruits, Milk, Makhana (फल, दूध, मखाना)",
                "Sabudana khichdi (साबूदाना खिचड़ी सेंधा नमक युक्त)"
            ),
            prohibitedFoods = listOf(
                "Wheat, Rice, Lentils (गेहूं, चावल, दालें)",
                "Non-satvik spices (प्याज, लहसुन, हींग)"
            ),
            paranaGuideEn = "Fast is observed throughout the day. In the night, when the Moon rises, offer raw milk and water Arghya to the Moon, recite Sankata Nashanam Ganesha Stotram, and then break the fast.",
            paranaGuideHi = "दिनभर निराहार या फलाहार रहकर रात्रि चंद्रोदय के समय जल में कच्चा दूध, चंदन व अक्षत मिलाकर चंद्रमा को अर्घ्य दें और मोदक भोग लगाकर पारण करें।",
            paranaGuideMr = "रात्री चंद्रोदयाची वेळ पाहून चंद्राला अर्घ्य द्यावे, गणपती बाप्पाला मोदकाचा नैवेद्य दाखवून उपवास सोडावा.",
            kathaTitle = "संकष्टी चतुर्थी व्रत कथा",
            kathaStoryEn = "King Harishchandra's potter once fired a kiln inadvertently containing a cat's kittens. Filled with remorse, the potter prayed to Mother Gauri and Lord Ganesha on Sankashti Chaturthi. Miraculously, when the intense kiln cooled, the kittens emerged safe and alive, establishing the profound power of Sankashti fast.",
            kathaStoryHi = "एक समय कुम्हार के आंवे (भट्टी) में बिल्ली के बच्चे भूलवश दब गए थे। कुम्हार ने प्रायश्चित्त हेतु संकष्टी चतुर्थी का व्रत रख विघ्नहर्ता गणेश जी से बालकों की रक्षा की प्रार्थना की। भीषण अग्नि में भी गणेश जी की कृपा से वे बच्चे पूर्णतः सुरक्षित बाहर आए। तभी से संकट निवारण हेतु यह व्रत प्रसिद्ध हुआ।",
            kathaStoryMr = "कुंभाराच्या भट्टीत मांजरीची पिल्ले चुकून सापडली होती. कुंभाराने संकष्टी चतुर्थीचे व्रत करून गणेशाची प्रार्थना केली. गणेशाच्या कृपेने ती पिल्ले आगीतून सुखरूप बाहेर पडली. यावरून या व्रताची महती सर्वदूर पसरली.",
            pujaVidhiSteps = listOf(
                "प्रातः काल गणेश जी का आह्वान कर दूर्वा व लाल पुष्प अर्पित करें।",
                "दिनभर मन में ॐ गं गणपतये नमः का जप करें।",
                "शाम को चंद्रोदय की सही समय सारिणी जांचें।",
                "चंद्रोदय होने पर तांबे या चांदी के लोटे से चंद्रमा को अर्घ्य दें।",
                "गणेश जी की आरती कर प्रसाद ग्रहण करें।"
            )
        ),
        SacredVrat(
            id = "karva_chauth",
            nameEn = "Karva Chauth (करवा चौथ)",
            nameHi = "करवा चौथ (अखंड सौभाग्य व्रत)",
            nameMr = "करवा चौथ सौभाग्य व्रत",
            deity = "Maa Karva, Mata Parvati & Lord Shiva",
            frequency = "Yearly (Kartik Krishna Chaturthi)",
            significanceEn = "A sacred fast observed by married women praying for the longevity, health, and prosperity of their husbands.",
            significanceHi = "अखंड सौभाग्य, दांपत्य प्रेम और पति की दीर्घायु की कामना हेतु रखा जाने वाला कठिन निर्जला व्रत।",
            significanceMr = "पतीच्या दीर्घायुष्यासाठी आणि अखंड सौभाग्यासाठी केले जाणारे निर्जला व्रत.",
            allowedFoods = listOf(
                "Sargi (सुबह 4 बजे सूर्योदय पूर्व: फेनियां, मेवे, फल, दूध)",
                "Daytime: Complete Nirjala (जल भी नहीं)",
                "Post Moonrise: Complete satvik dinner with family"
            ),
            prohibitedFoods = listOf(
                "Water and food during the entire day until Moon sighting",
                "Non-satvik foods"
            ),
            paranaGuideEn = "Take pre-dawn Sargi before Brahma Muhurat ends. Observe waterless fast until night. View the Moon through a sieve, then see your husband, receive water from his hands to break the fast.",
            paranaGuideHi = "सूर्योदय पूर्व सरगी ग्रहण करें। दिनभर निर्जला रहें। रात्रि में छलनी से चंद्र दर्शन कर पतिदेव का मुख देखें और उनके हाथों से जल पीकर पारण करें।",
            paranaGuideMr = "पहाटे सूर्योदयापूर्वी सरगी खावी. दिवसभर निर्जला उपवास ठेवावा. रात्री चाळणीतून चंद्राचे व नंतर पतीचे दर्शन घेऊन पाणी पिऊन उपवास सोडावा.",
            kathaTitle = "वीरावती की करवा चौथ कथा",
            kathaStoryEn = "Veeravati was the only beloved sister among seven brothers. Exhausted during her first Karva Chauth, her brothers deceived her by lighting a fire on a distant hill to look like the rising moon. Veeravati broke her fast early, bringing misfortune to her husband. Realizing her mistake, she observed the fast with complete penance and devotion next year, restoring her husband's life and health through Goddess Parvati's grace.",
            kathaStoryHi = "रानी वीरावती सात भाइयों की लाडली बहन थी। करवा चौथ के कठिन निर्जला व्रत में भूख से व्याकुल देख उसके भाइयों ने पहाड़ी पर दीपक जलाकर कृत्रिम चंद्रमा दिखा दिया। वीरावती ने अर्ध देकर भोजन कर लिया जिससे उसके पति पर संकट आ गया। अगले वर्ष वीरावती ने विधिपूर्वक पूर्ण निष्ठा से व्रत का पालन किया और माता पार्वती की अनुकंपा से उसके पति को नवजीवन प्राप्त हुआ।",
            kathaStoryMr = "वीरवतीने भावांच्या भूलथापांना बळी पडून चंद्राच्या आधी दिवा पाहून उपवास सोडला, ज्यामुळे तिच्या पतीवर संकट आले. नंतर तिने देवी पार्वतीची आराधना करून पुन्हा संपूर्ण श्रद्धेने व्रत पूर्ण केले व तिच्या पतीला दीर्घायुष्य लाभले.",
            pujaVidhiSteps = listOf(
                "ब्रह्म मुहूर्त में सास द्वारा दी गई सरगी खाएं।",
                "दिन में 16 श्रृंगार कर करवा माता की पूजा करें।",
                "संध्याकाल में करवा फेरने की रस्म व करवा चौथ कथा सुनें।",
                "रात्रि में चंद्रोदय पर अर्घ्य दें।",
                "छलनी में दीपक रखकर चंद्र व पति का दर्शन कर चरण स्पर्श करें।"
            )
        ),
        SacredVrat(
            id = "solah_somwar",
            nameEn = "Solah Somwar Vrat (सोलह सोमवार)",
            nameHi = "सोलह सोमवार व्रत (मनोकामना पूर्ति)",
            nameMr = "सोळा सोमवार व्रत",
            deity = "Lord Shiva & Mata Parvati",
            frequency = "16 Consecutive Mondays",
            significanceEn = "Fulfills heartfelt wishes, brings righteous life partner, removes obstacles in marriage, and grants domestic harmony.",
            significanceHi = "योग्य जीवनसाथी की प्राप्ति, वैवाहिक सुख, संतान सुख एवं मनोवांछित फल की सिद्धि हेतु 16 सोमवार का संकल्प।",
            significanceMr = "मनोवांच्छित वर प्राप्ती व वैवाहिक सौख्यासाठी सोळा सोमवारचे व्रत केले जाते.",
            allowedFoods = listOf(
                "Wheat flour Churma prasad (गेहूं के आटे का चूरमा, घी व गुड़ युक्त)",
                "Fruits and milk (फल व दूध)",
                "One satvik meal in the evening after puja"
            ),
            prohibitedFoods = listOf(
                "Salt during the day",
                "Tamasic ingredients (लहसुन, प्याज)"
            ),
            paranaGuideEn = "Make Churma into 3 equal portions: 1 for Shiva offering, 1 for guests/cows/children, and 1 for yourself to eat once in the evening.",
            paranaGuideHi = "चूरमे के तीन भाग करें: एक शिव जी को अर्पण, दूसरा भक्तों में वितरण, और तीसरा स्वयं ग्रहण कर एक समय भोजन करें।",
            paranaGuideMr = "गव्हाच्या पिठाचा चूरमा करून त्याचे ३ भाग करावेत - एक देवाला, एक वाटण्यासाठी आणि एक स्वतः खाण्यासाठी.",
            kathaTitle = "सोलह सोमवार व्रत कथा",
            kathaStoryEn = "Lord Shiva and Mata Parvati once visited Amravati city. While playing dice, a temple priest falsely predicted Lord Shiva's defeat. Mata Parvati cursed him with illness. Later, apsaras taught the priest the Solah Somwar fast, by which he was cured and enlightened. Seeing this miracle, the king's daughter also performed the fast and found the ideal prince.",
            kathaStoryHi = "भगवान शिव और माता पार्वती अमरावती नगरी के सुंदर शिव मंदिर में पधारे। द्यूत क्रीड़ा में ब्राह्मण पुजारी ने असत्य वचन कहा जिससे पार्वती जी ने उसे कुष्ठ रोगी होने का शाप दिया। बाद में देवलोक की अप्सराओं ने ब्राह्मण को सोलह सोमवार व्रत की विधि बताई। नियमपूर्वक व्रत करने से ब्राह्मण निरोगी हो गया। इस व्रत के प्रभाव से आगे चलकर राजा की कन्या का विवाह भी सुयोग्य वर से संपन्न हुआ।",
            kathaStoryMr = "अमरावती नगरीत एका पुजार्‍याला पार्वतीच्या शापामुळे कुष्ठरोग झाला. अप्सरांनी सांगितल्याप्रमाणे त्याने १६ सोमवारचे व्रत केले आणि तो पूर्ण बरा झाला. या व्रताने मनातील सर्व इच्छा पूर्ण होतात.",
            pujaVidhiSteps = listOf(
                "प्रातःकाल सफेद वस्त्र पहनकर शिव मंदिर जाएं।",
                "शिवलिंग का पंचामृत से अभिषेक करें और सफेद फूल व बेलपत्र चढ़ाएं।",
                "घी और गुड़ से गेहूं के आटे का चूरमा बनाकर भोग लगाएं।",
                "सोलह सोमवार कथा का पाठ करें।",
                "शाम को केवल एक समय चूरमा खाकर व्रत संपन्न करें।"
            )
        ),
        SacredVrat(
            id = "brihaspativar",
            nameEn = "Brihaspativar Vrat (गुरुवार व्रत)",
            nameHi = "बृहस्पतिवार / गुरुवार व्रत (ज्ञान व समृद्धि)",
            nameMr = "गुरुवार व्रत (दत्त व विष्णू उपासना)",
            deity = "Lord Vishnu & Brihaspati Dev",
            frequency = "Weekly (Thursday)",
            significanceEn = "Brings prosperity, removes marriage hurdles, enhances knowledge, wisdom, and auspicious energies in household.",
            significanceHi = "वैवाहिक बाधाओं की शांति, संतान सुख, ज्ञान की वृद्धि और धन-धान्य की समृद्धि हेतु गुरुवार का पावन व्रत।",
            significanceMr = "विवाह कार्यात येणारे अडथळे दूर होण्यासाठी व घरात सुख-समृद्धी राहण्यासाठी गुरुवारचे व्रत केले जाते.",
            allowedFoods = listOf(
                "Yellow items: Chana dal, Jaggery, Turmeric khichdi (चना दाल, गुड़, बेसन हलवा)",
                "Yellow fruits (केला, पपीता, आम - नोट: केला पेड़ की पूजा करें, फल न खाएं)",
                "Milk and satvik food once a day without salt"
            ),
            prohibitedFoods = listOf(
                "Salt is strictly avoided in evening meal (नमक रहित भोजन)",
                "Do not eat bananas on this day as Banana tree is worshipped",
                "Washing hair or clothes is avoided as per tradition"
            ),
            paranaGuideEn = "Worship Banana tree with water, turmeric, chana dal and jaggery. Break fast in the evening with sweet yellow food without salt.",
            paranaGuideHi = "केले के वृक्ष में जल, हल्दी, चने की दाल व गुड़ अर्पित करें। सायंकाल बिना नमक का पीला भोजन (बेसन या दाल की पूड़ी/हलवा) खाकर पारण करें।",
            paranaGuideMr = "केळीच्या झाडाला हळद, हरभरा डाळ व गूळ अर्पण करावा. संध्याकाळी मिठाशिवाय पिवळे गोड जेवण करून उपवास सोडावा.",
            kathaTitle = "बृहस्पतिवार व्रत कथा",
            kathaStoryEn = "A wealthy merchant's wife was stingy and turned away sadhus asking for alms. Brihaspati Dev visited disguised as a sage and gave her advice she abused, losing all her wealth. Realizing the glory of Lord Brihaspati, she began observing the Thursday fast with yellow offerings, regaining all prosperity and happiness.",
            kathaStoryHi = "एक नगर में एक धनी व्यापारी रहता था जिसकी पत्नी अत्यंत कृपण थी और संतों को भिक्षा नहीं देती थी। देवगुरु बृहस्पति ने साधु वेश में आकर उसकी परीक्षा ली और अभिमान के कारण उसका समस्त धन नष्ट हो गया। बाद में भूल का अहसास होने पर उसने विधिपूर्वक पीले वस्त्र पहनकर गुरुवार का व्रत रखा। प्रभु की कृपा से उसका भंडार पुनः धन-धान्य से भर गया।",
            kathaStoryMr = "एका व्यापाऱ्याच्या पत्नीने साधूंचा अपमान केला ज्यामुळे तिचे सर्व ऐश्वर्य नष्ट झाले. नंतर तिने पश्चात्ताप करून गुरुवारचे व्रत सुरू केले आणि देवगुरु बृहस्पतीच्या कृपेने तिचे घर पुन्हा धनधान्याने भरून गेले.",
            pujaVidhiSteps = listOf(
                "पीले वस्त्र धारण कर पूजा स्थल पर भगवान विष्णु का चित्र स्थापित करें।",
                "पीले चंदन, पीले पुष्प, चने की दाल और गुड़ का भोग लगाएं।",
                "केले के पौधे को जल अर्पित कर परिक्रमा करें।",
                "बृहस्पतिवार व्रत कथा पढ़ें या सुनें।",
                "शाम को बिना नमक का पीला भोजन ग्रहण करें।"
            )
        )
    )

    fun getVratById(id: String): SacredVrat {
        return vrats.find { it.id == id } ?: vrats.first()
    }
}
