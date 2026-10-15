package com.shubhutsav.app.data

data class SacredMantra(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val nameMr: String,
    val mantraSanskrit: String,
    val transliteration: String,
    val meaningEn: String,
    val meaningHi: String,
    val meaningMr: String,
    val deity: String,
    val benefits: String
)

object JapaMalaRepository {

    val mantras: List<SacredMantra> = listOf(
        SacredMantra(
            id = "om_namah_shivaya",
            nameEn = "Om Namah Shivaya",
            nameHi = "ॐ नमः शिवाय (पंचाक्षरी मंत्र)",
            nameMr = "ॐ नमः शिवाय (शिवाचा पंचाक्षरी मंत्र)",
            mantraSanskrit = "ॐ नमः शिवाय",
            transliteration = "Om Namah Shivaya",
            meaningEn = "I bow to Lord Shiva, the supreme auspicious consciousness within all beings.",
            meaningHi = "मैं भगवान शिव को नमन करता हूँ, जो कल्याणकारी और सर्वोच्च चेतना हैं।",
            meaningMr = "मी भगवान शिवाला वंदन करतो, जो सर्वांचे कल्याण करणारा परमेश्वर आहे.",
            deity = "Lord Shiva",
            benefits = "Brings inner stillness, dissolves negative karma, enhances concentration and peace of mind."
        ),
        SacredMantra(
            id = "gayatri_mantra",
            nameEn = "Gayatri Mantra",
            nameHi = "गायत्री महामंत्र (ऋग्वेद)",
            nameMr = "गायत्री महामंत्र",
            mantraSanskrit = "ॐ भूर्भुवः स्वः तत्सवितुर्वरेण्यं भर्गो देवस्य धीमहि धियो यो नः प्रचोदयात् ॥",
            transliteration = "Om Bhur Bhuvaḥ Svaḥ Tat Savitur Vareṇyaṁ Bhargo Devasya Dhīmahi Dhiyo Yo Naḥ Pracodayāt",
            meaningEn = "We meditate upon the radiant divine light of the Sun Creator. May that divine light awaken and illuminate our intellect.",
            meaningHi = "उस प्राणस्वरूप, दुःखों के नाशक, सुख के स्वरूप, श्रेष्ठ, तेजस्वी परमपिता परमात्मा के तेज को हम धारण करें, जो हमारी बुद्धि को सन्मार्ग की ओर प्रेरित करे।",
            meaningMr = "त्या सूर्यस्वरूपी परमेश्वराच्या दैवी तेजाचे आम्ही ध्यान करतो, जे तेज आमच्या बुद्धीला सत्कर्माची प्रेरणा देवो.",
            deity = "Maa Gayatri / Surya Dev",
            benefits = "Sharpens intellect, grants spiritual illumination, bestows clarity, memory, and divine protection."
        ),
        SacredMantra(
            id = "maha_mrityunjaya",
            nameEn = "Maha Mrityunjaya Mantra",
            nameHi = "महामृत्युंजय मंत्र (संजीवनी मंत्र)",
            nameMr = "महामृत्युंजय संजीवनी मंत्र",
            mantraSanskrit = "ॐ त्र्यम्बकं यजामहे सुगन्धिं पुष्टिवर्धनम् । उर्वारुकमिव बन्धनान्मृत्योर्मुक्षीय मामृतात् ॥",
            transliteration = "Om Tryambakaṁ Yajāmahe Sugandhiṁ Puṣṭi-Vardhanam | Urvārukam-Iva Bandhanān Mṛtyor-Mukṣīya Māmṛtāt ||",
            meaningEn = "We worship the three-eyed Lord Shiva, fragrant and nourishing all beings. Like a ripe cucumber liberated from its stalk, may He liberate us from death and bondages for immortality.",
            meaningHi = "हम त्रिनेत्रधारी भगवान शिव की आराधना करते हैं जो सुगंधित एवं जीवन शक्ति का पोषण करने वाले हैं। जिस प्रकार पका फल बेल से मुक्त होता है, उसी प्रकार हम मृत्यु के भय से मुक्त होकर अमरता प्राप्त करें।",
            meaningMr = "आम्ही त्रिनेत्रधारी शिवाचे ध्यान करतो जो सर्वांना पुष्टी देणारा आहे. पिकलेले फळ ज्याप्रमाणे देठापासून सहज मुक्त होते, तसे आम्ही मृत्युच्या भयापासून मुक्त व्हावे.",
            deity = "Lord Mahadev",
            benefits = "Removes fear of accidents, grants healing, vitality, physical longevity, and emotional resilience."
        ),
        SacredMantra(
            id = "hare_krishna",
            nameEn = "Hare Krishna Mahamantra",
            nameHi = "हरे कृष्ण महामंत्र",
            nameMr = "हरे कृष्ण महामंत्र",
            mantraSanskrit = "हरे कृष्ण हरे कृष्ण कृष्ण कृष्ण हरे हरे । हरे राम हरे राम राम राम हरे हरे ॥",
            transliteration = "Hare Krishna Hare Krishna Krishna Krishna Hare Hare | Hare Rama Hare Rama Rama Rama Hare Hare ||",
            meaningEn = "O divine energy of the Lord (Hare), O all-attractive Supreme Lord Krishna, O source of all pleasure Rama, please engage me in Your devotional service.",
            meaningHi = "हे भगवान की पराशक्ति (हरे), हे सर्व-आकर्षक श्रीकृष्ण, हे आनंददाता श्रीराम! मुझे अपनी दिव्य भक्ति सेवा में लीन करें।",
            meaningMr = "हे परमेश्वराच्या दिव्य शक्ती, हे सर्वांना आकर्षित करणार्‍या श्रीकृष्णा, हे श्रीराम, मला तुमच्या भक्तीत मग्न ठेवा.",
            deity = "Radha Krishna / Sita Ram",
            benefits = "Cleanses the heart of anxiety, brings boundless joy, devotional sweetness, and supreme bliss."
        ),
        SacredMantra(
            id = "ganesh_mantra",
            nameEn = "Ganesh Beej Mantra",
            nameHi = "श्री गणेश बीज मंत्र",
            nameMr = "श्री गणेश बीज मंत्र",
            mantraSanskrit = "ॐ गं गणपतये नमः",
            transliteration = "Om Gaṁ Gaṇapataye Namaḥ",
            meaningEn = "I bow to Lord Ganesha, the lord of beginnings, wisdom, and remover of all obstacles.",
            meaningHi = "विघ्नहर्ता और बुद्धि के दाता भगवान श्री गणेश को मेरा प्रणाम।",
            meaningMr = "सर्व विघ्नांचा नाश करणार्‍या व बुद्धी देणार्‍या श्री गणेशाला माझे नमन असो.",
            deity = "Lord Ganesha",
            benefits = "Removes roadblocks in career, education, and relationships; grants wisdom and smooth beginnings."
        ),
        SacredMantra(
            id = "lakshmi_mantra",
            nameEn = "Mahalakshmi Mantra",
            nameHi = "श्री महालक्ष्मी मंत्र",
            nameMr = "महालक्ष्मी मंत्र",
            mantraSanskrit = "ॐ श्रीं ह्रीं क्लीं श्रीं सिद्ध लक्ष्म्यै नमः",
            transliteration = "Om Śrīṁ Hrīṁ Klīṁ Śrīṁ Siddha Lakṣmyai Namaḥ",
            meaningEn = "Salutations to Goddess Mahalakshmi, the granter of all prosperity, spiritual wealth, and auspiciousness.",
            meaningHi = "समस्त सिद्धियों, ऐश्वर्य, वैभव और शुभता की दात्री भगवती महालक्ष्मी को प्रणाम।",
            meaningMr = "सर्व संपत्ती, सुख व समृद्धी देणाऱ्या आदिशक्ती महालक्ष्मीला माझा नमस्कार असो.",
            deity = "Maa Lakshmi",
            benefits = "Attracts financial stability, abundance, harmony in the household, and righteous prosperity."
        ),
        SacredMantra(
            id = "ram_raksha_taraka",
            nameEn = "Shri Ram Taraka Mantra",
            nameHi = "श्रीराम तारक मंत्र",
            nameMr = "श्रीराम तारक मंत्र",
            mantraSanskrit = "श्री राम जय राम जय जय राम",
            transliteration = "Śrī Rāma Jaya Rāma Jaya Jaya Rāma",
            meaningEn = "Victory to Lord Rama, the ideal divine embodiment of righteousness and supreme compassion.",
            meaningHi = "परम करुणामय मर्यादा पुरुषोत्तम भगवान श्री राम की जय हो, सर्वत्र विजय हो।",
            meaningMr = "मर्यादा पुरुषोत्तम भगवान श्रीरामांचा सर्वत्र जयजयकार असो.",
            deity = "Lord Ram",
            benefits = "Bestows courage, removes sorrow and loneliness, grants fearlessness and moral strength."
        ),
        SacredMantra(
            id = "om_namo_narayanaya",
            nameEn = "Ashtakshari Vishnu Mantra",
            nameHi = "अष्टाक्षरी नारायण मंत्र",
            nameMr = "अष्टाक्षरी नारायण मंत्र",
            mantraSanskrit = "ॐ नमो नारायणाय",
            transliteration = "Om Namo Nārāyaṇāya",
            meaningEn = "I bow to Lord Narayana, the sustainer and preserver of the entire cosmos.",
            meaningHi = "समस्त चराचर जगत के पालनकर्ता भगवान श्री नारायण को मेरा शत-शत नमन।",
            meaningMr = "संपूर्ण विश्वाचे पालनपोषण करणार्‍या भगवान श्री विष्णूंना माझा नमस्कार असो.",
            deity = "Lord Vishnu",
            benefits = "Preserves health, shields family from adversity, brings balance, calm, and spiritual evolution."
        )
    )

    fun getMantraById(id: String): SacredMantra {
        return mantras.find { it.id == id } ?: mantras.first()
    }
}
