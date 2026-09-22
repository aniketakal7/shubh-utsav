package com.shubhutsav.app.data

data class PujaStep(
    val stepNumber: Int,
    val titleEn: String,
    val titleHi: String,
    val instructionEn: String,
    val instructionHi: String,
    val mantra: String? = null,
    val practicalTipEn: String? = null,
    val practicalTipHi: String? = null
)

fun PujaStep.getTitle(lang: String): String = when (lang) {
    "mr" -> titleHi
    "hi" -> titleHi
    else -> titleEn
}

fun PujaStep.getInstruction(lang: String): String = when (lang) {
    "mr" -> instructionHi
    "hi" -> instructionHi
    else -> instructionEn
}

fun PujaStep.getPracticalTip(lang: String): String? = when (lang) {
    "mr" -> practicalTipHi
    "hi" -> practicalTipHi
    else -> practicalTipEn
}

data class Supply(
    val nameEn: String,
    val nameHi: String,
    val quantityEn: String,
    val quantityHi: String,
    val categoryEn: String = "Essential Puja Samagri",
    val categoryHi: String = "मुख्य पूजा सामग्री",
    val alternativeEn: String? = null,
    val alternativeHi: String? = null
)

fun Supply.getName(lang: String): String = when (lang) {
    "mr" -> nameHi
    "hi" -> nameHi
    else -> nameEn
}

fun Supply.getQuantity(lang: String): String = when (lang) {
    "mr" -> quantityHi
    "hi" -> quantityHi
    else -> quantityEn
}

fun Supply.getAlternative(lang: String): String? = when (lang) {
    "mr" -> alternativeHi
    "hi" -> alternativeHi
    else -> alternativeEn
}

data class PrepTask(
    val timeframeEn: String,
    val timeframeHi: String,
    val taskEn: String,
    val taskHi: String
)

fun PrepTask.getTimeframe(lang: String): String = when (lang) {
    "mr" -> timeframeHi.replace("दिन पहले", "दिवस आधी").replace("उत्सव के दिन", "उत्सवाच्या दिवशी")
    "hi" -> timeframeHi
    else -> timeframeEn
}

fun PrepTask.getTask(lang: String): String = when (lang) {
    "mr" -> taskHi
    "hi" -> taskHi
    else -> taskEn
}

data class MantraItem(
    val mantraSanskrit: String,
    val transliteration: String,
    val meaningEn: String,
    val meaningHi: String
)

fun MantraItem.getMeaning(lang: String): String = when (lang) {
    "mr" -> meaningHi
    "hi" -> meaningHi
    else -> meaningEn
}

data class CommonMistake(
    val mistakeEn: String,
    val mistakeHi: String,
    val solutionEn: String,
    val solutionHi: String
)

fun CommonMistake.getMistake(lang: String): String = when (lang) {
    "mr" -> mistakeHi
    "hi" -> mistakeHi
    else -> mistakeEn
}

fun CommonMistake.getSolution(lang: String): String = when (lang) {
    "mr" -> solutionHi
    "hi" -> solutionHi
    else -> solutionEn
}

data class MuhuratTiming(
    val nameEn: String,
    val nameHi: String,
    val timeEn: String,
    val timeHi: String,
    val noteEn: String,
    val noteHi: String
)

fun MuhuratTiming.getName(lang: String): String = when (lang) {
    "mr" -> nameHi
    "hi" -> nameHi
    else -> nameEn
}

fun MuhuratTiming.getTime(lang: String): String = when (lang) {
    "mr" -> timeHi
    "hi" -> timeHi
    else -> timeEn
}

fun MuhuratTiming.getNote(lang: String): String = when (lang) {
    "mr" -> noteHi
    "hi" -> noteHi
    else -> noteEn
}

data class Festival(
    val id: String,
    val nameEn: String,
    val nameHi: String,
    val date2026En: String,
    val date2026Hi: String,
    val date2025En: String,
    val date2027En: String,
    val monthEn: String,
    val monthHi: String,
    val categoryEn: String,
    val categoryHi: String,
    val emoji: String,
    val summaryEn: String,
    val summaryHi: String,
    val significanceEn: String,
    val significanceHi: String,
    val explainSimplyEn: String,
    val explainSimplyHi: String,
    val muhurat: MuhuratTiming,
    val preparationTimeline: List<PrepTask>,
    val steps: List<PujaStep>,
    val supplies: List<Supply>,
    val mantras: List<MantraItem>,
    val mistakes: List<CommonMistake>
)

fun Festival.getName(lang: String): String = when (lang) {
    "mr" -> MarathiTranslations.getFestivalName(id, nameHi)
    "hi" -> nameHi
    else -> nameEn
}

fun Festival.getDate2026(lang: String): String = when (lang) {
    "mr" -> date2026Hi.replace("नवंबर", "नोव्हेंबर").replace("अक्टूबर", "ऑक्टोबर").replace("अगस्त", "ऑगस्ट")
    "hi" -> date2026Hi
    else -> date2026En
}

fun Festival.getCategory(lang: String): String = when (lang) {
    "mr" -> MarathiTranslations.getCategoryName(categoryHi)
    "hi" -> categoryHi
    else -> categoryEn
}

fun Festival.getSummary(lang: String): String = when (lang) {
    "mr" -> summaryHi
    "hi" -> summaryHi
    else -> summaryEn
}

fun Festival.getSignificance(lang: String): String = when (lang) {
    "mr" -> significanceHi
    "hi" -> significanceHi
    else -> significanceEn
}

fun Festival.getExplainSimply(lang: String): String = when (lang) {
    "mr" -> explainSimplyHi
    "hi" -> explainSimplyHi
    else -> explainSimplyEn
}
