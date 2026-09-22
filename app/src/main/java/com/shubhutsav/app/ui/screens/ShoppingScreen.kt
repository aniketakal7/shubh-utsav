package com.shubhutsav.app.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.*
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.SuccessGreen

@Composable
fun ShoppingScreen(
    navController: NavHostController,
    festival: Festival,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onLanguageChange: (String) -> Unit = {},
    onToggleLanguage: () -> Unit = {}
) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }

    var boughtCounter by remember {
        mutableStateOf(prefs.getBoughtSuppliesCount(festival.id, festival.supplies.size))
    }
    var toggleCounter by remember { mutableStateOf(0) }

    var showAddItemDialog by remember { mutableStateOf(false) }
    var customItems by remember { mutableStateOf(prefs.getCustomSupplies(festival.id)) }
    var selectedFilter by remember { mutableStateOf(0) } // 0 = All, 1 = Pending, 2 = Bought

    val totalItems = festival.supplies.size + customItems.size
    val progressFraction = if (totalItems == 0) 0f else (boughtCounter.toFloat() / totalItems).coerceIn(0f, 1f)
    val pendingCount = (totalItems - boughtCounter).coerceAtLeast(0)

    if (showAddItemDialog) {
        AddCustomItemDialog(
            isHindi = language == "hi",
            language = language,
            onDismiss = { showAddItemDialog = false },
            onAdd = { name, qty ->
                prefs.addCustomSupply(festival.id, name, qty)
                customItems = prefs.getCustomSupplies(festival.id)
                showAddItemDialog = false
                Toast.makeText(
                    context,
                    when (language) {
                        "mr" -> "वस्तू जोडली गेली"
                        "hi" -> "वस्तु जोड़ी गई"
                        else -> "Item added"
                    },
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    Scaffold(
        topBar = {
            val topBarTitle = when (language) {
                "mr" -> "${festival.getName(language)} साहित्य"
                "hi" -> "${festival.nameHi} सामग्री"
                else -> "${festival.nameEn} Samagri"
            }
            AppTopBar(
                title = topBarTitle,
                isHindi = language == "hi",
                language = language,
                onLanguageChange = onLanguageChange,
                onToggleLanguage = onToggleLanguage,
                onBackClick = { navController.popBackStack() },
                onSettingsClick = { navController.navigate("settings") }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddItemDialog = true },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = {
                    Text(
                        when (language) {
                            "mr" -> "वस्तू जोडा"
                            "hi" -> "वस्तु जोड़ें"
                            else -> "Add Custom Item"
                        },
                        fontWeight = FontWeight.Bold
                    )
                },
                containerColor = KesariyaSaffron,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(vertical = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Progress Header Card
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = when (language) {
                                    "mr" -> "खरेदी चेकलिस्ट प्रगती"
                                    "hi" -> "खरीदारी चेकलिस्ट प्रगति"
                                    else -> "Shopping Checklist Progress"
                                },
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "$boughtCounter / $totalItems वस्तू खरेदी झाल्या"
                                    "hi" -> "$boughtCounter / $totalItems वस्तुएं प्राप्त"
                                    else -> "$boughtCounter of $totalItems items bought"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // WhatsApp Share Button
                        Button(
                            onClick = {
                                val shareText = buildShareText(
                                    festival = festival,
                                    isHindi = language == "hi",
                                    language = language,
                                    prefs = prefs,
                                    customItems = customItems
                                )
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, shareText)
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(
                                    sendIntent,
                                    when (language) {
                                        "mr" -> "यादी शेअर करा"
                                        "hi" -> "सूची शेयर करें"
                                        else -> "Share Shopping List"
                                    }
                                )
                                context.startActivity(shareIntent)
                            },
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                        ) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = when (language) {
                                    "mr" -> "शेअर"
                                    "hi" -> "शेयर"
                                    else -> "Share"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    LinearProgressIndicator(
                        progress = { progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = KesariyaSaffron
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Market-Ready Filter Pills: All | Pending to Buy | Bought
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val filterOptions = listOf(
                        Pair(
                            when (language) {
                                "mr" -> "सर्व ($totalItems)"
                                "hi" -> "सभी ($totalItems)"
                                else -> "All ($totalItems)"
                            },
                            0
                        ),
                        Pair(
                            when (language) {
                                "mr" -> "शिल्लक ($pendingCount)"
                                "hi" -> "बाकी ($pendingCount)"
                                else -> "To Buy ($pendingCount)"
                            },
                            1
                        ),
                        Pair(
                            when (language) {
                                "mr" -> "खरेदी झाली ($boughtCounter)"
                                "hi" -> "प्राप्त ($boughtCounter)"
                                else -> "Bought ($boughtCounter)"
                            },
                            2
                        )
                    )

                    filterOptions.forEach { (label, index) ->
                        val isSelected = selectedFilter == index
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent
                                )
                                .clickable { selectedFilter = index }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Supplies List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 12.dp),
                modifier = Modifier.weight(1f)
            ) {
                // Filtered Standard Puja Supplies
                val filteredStandardSupplies = festival.supplies.mapIndexed { idx, s -> Pair(idx, s) }
                    .filter { (idx, _) ->
                        val isBought = prefs.isSupplyBought(festival.id, idx)
                        when (selectedFilter) {
                            1 -> !isBought
                            2 -> isBought
                            else -> true
                        }
                    }

                if (filteredStandardSupplies.isNotEmpty()) {
                    item {
                        Text(
                            text = when (language) {
                                "mr" -> "आवश्यक पूजा साहित्य (${filteredStandardSupplies.size})"
                                "hi" -> "आवश्यक पूजा सामग्री (${filteredStandardSupplies.size})"
                                else -> "Essential Puja Samagri (${filteredStandardSupplies.size})"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }

                    itemsIndexed(filteredStandardSupplies) { _, (origIndex, supply) ->
                        val isBought = remember(origIndex, toggleCounter) {
                            prefs.isSupplyBought(festival.id, origIndex)
                        }

                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isBought)
                                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                else
                                    MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(
                                1.dp,
                                if (isBought) SuccessGreen.copy(alpha = 0.35f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    prefs.toggleSupply(festival.id, origIndex)
                                    boughtCounter = prefs.getBoughtSuppliesCount(festival.id, festival.supplies.size)
                                    toggleCounter++
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isBought,
                                    onCheckedChange = {
                                        prefs.setSupplyBought(festival.id, origIndex, it)
                                        boughtCounter = prefs.getBoughtSuppliesCount(festival.id, festival.supplies.size)
                                        toggleCounter++
                                    }
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = supply.getName(language),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        textDecoration = if (isBought) TextDecoration.LineThrough else TextDecoration.None,
                                        color = if (isBought) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                                    )
                                    val alt = supply.getAlternative(language)
                                    if (alt != null) {
                                        Text(
                                            text = when (language) {
                                                "mr" -> "पर्याय: $alt"
                                                "hi" -> "विकल्प: $alt"
                                                else -> "Alt: $alt"
                                            },
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = KesariyaSaffron.copy(alpha = 0.12f)
                                ) {
                                    Text(
                                        text = supply.getQuantity(language),
                                        color = KesariyaSaffron,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                // Custom user-added items
                val filteredCustomItems = customItems.mapIndexed { idx, pair -> Pair(idx, pair) }
                    .filter { (_, _) ->
                        // Show in All and To Buy
                        selectedFilter != 2
                    }

                if (filteredCustomItems.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "माझे अतिरिक्त साहित्य (${filteredCustomItems.size})"
                                "hi" -> "मेरी अतिरिक्त सामग्री (${filteredCustomItems.size})"
                                else -> "My Custom Items (${filteredCustomItems.size})"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    itemsIndexed(filteredCustomItems) { _, (_, pair) ->
                        val (cName, cQty) = pair
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = cName, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                                    Text(text = cQty, fontSize = 12.sp, color = KesariyaSaffron, fontWeight = FontWeight.Bold)
                                }
                                IconButton(
                                    onClick = {
                                        prefs.removeCustomSupply(festival.id, cName, cQty)
                                        customItems = prefs.getCustomSupplies(festival.id)
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Gray)
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
private fun AddCustomItemDialog(
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    onDismiss: () -> Unit,
    onAdd: (name: String, quantity: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(if (language == "mr") "१ नग" else if (language == "hi") "1 सेट" else "1 set") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(22.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            Column(modifier = Modifier.padding(22.dp)) {
                Text(
                    text = when (language) {
                        "mr" -> "अतिरिक्त साहित्य जोडा"
                        "hi" -> "अतिरिक्त सामग्री जोड़ें"
                        else -> "Add Custom Shopping Item"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            when (language) {
                                "mr" -> "वस्तूचे नाव"
                                "hi" -> "वस्तु का नाम"
                                else -> "Item Name"
                            }
                        )
                    },
                    placeholder = {
                        Text(
                            when (language) {
                                "mr" -> "उदा. पिवळे वस्त्र किंवा गुलाब पाणी"
                                "hi" -> "उदा. पीला वस्त्र या गुलाब जल"
                                else -> "e.g. Yellow cloth or Rose water"
                            }
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = {
                        Text(
                            when (language) {
                                "mr" -> "प्रमाण"
                                "hi" -> "मात्रा"
                                else -> "Quantity"
                            }
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            when (language) {
                                "mr" -> "रद्द करा"
                                "hi" -> "रद्द करें"
                                else -> "Cancel"
                            }
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (name.isNotBlank()) onAdd(name.trim(), quantity.trim())
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            when (language) {
                                "mr" -> "जोडा"
                                "hi" -> "जोड़ें"
                                else -> "Add Item"
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

private fun buildShareText(
    festival: Festival,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    prefs: PreferencesManager,
    customItems: List<Pair<String, String>>
): String {
    val festivalName = festival.getName(language)
    val sb = java.lang.StringBuilder()
    val headerTitle = when (language) {
        "mr" -> "🪔 शुभ उत्सव — $festivalName पूजा साहित्य यादी\n"
        "hi" -> "🪔 Shubh Utsav — $festivalName Puja Samagri List\n"
        else -> "🪔 Shubh Utsav — $festivalName Puja Samagri List\n"
    }
    sb.append(headerTitle)
    sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n")

    val pending = mutableListOf<String>()
    val bought = mutableListOf<String>()

    festival.supplies.forEachIndexed { i, s ->
        val itemTitle = s.getName(language)
        val qty = s.getQuantity(language)
        if (prefs.isSupplyBought(festival.id, i)) {
            bought.add("✓ $itemTitle ($qty)")
        } else {
            pending.add("☐ $itemTitle ($qty)")
        }
    }

    customItems.forEach { (cName, cQty) ->
        pending.add("☐ $cName ($cQty)")
    }

    if (pending.isNotEmpty()) {
        sb.append(
            when (language) {
                "mr" -> "खरेदीसाठी शिल्लक साहित्य:\n"
                "hi" -> "खरीदने के लिए शेष:\n"
                else -> "ITEMS TO BUY:\n"
            }
        )
        pending.forEach { sb.append("$it\n") }
        sb.append("\n")
    }

    if (bought.isNotEmpty()) {
        sb.append(
            when (language) {
                "mr" -> "खरेदी झालेले साहित्य:\n"
                "hi" -> "खरीदी जा चुकी सामग्री:\n"
                else -> "ALREADY BOUGHT:\n"
            }
        )
        bought.forEach { sb.append("$it\n") }
        sb.append("\n")
    }

    sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n")
    sb.append(
        when (language) {
            "mr" -> "शुभ उत्सव ॲपद्वारे सामायिक केले"
            "hi" -> "शुभ उत्सव ऐप द्वारा साझा किया गया"
            else -> "Shared via Shubh Utsav — Your Festival & Puja Companion"
        }
    )
    return sb.toString()
}
