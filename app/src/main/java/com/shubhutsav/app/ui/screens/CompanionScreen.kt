package com.shubhutsav.app.ui.screens

import android.content.Intent
import android.provider.CalendarContract
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.Festival
import com.shubhutsav.app.data.FestivalRepository
import com.shubhutsav.app.data.PreferencesManager
import com.shubhutsav.app.data.AuthManager
import com.shubhutsav.app.data.CompanionSnapshot
import com.shubhutsav.app.data.CompanionSyncManager
import com.shubhutsav.app.data.CommunityEvent
import com.shubhutsav.app.data.getDate2026
import com.shubhutsav.app.data.getName
import com.shubhutsav.app.ui.components.AppTopBar
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CompanionScreen(
    navController: NavHostController,
    language: String,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }
    var plannedIds by remember { mutableStateOf(prefs.plannedFestivalIds()) }
    var tradition by remember { mutableStateOf(prefs.selectedTradition) }
    var templeNote by remember { mutableStateOf(prefs.templeNote) }
    var memberName by remember { mutableStateOf("") }
    var members by remember { mutableStateOf(prefs.householdMembers()) }
    var showTraditions by remember { mutableStateOf(false) }
    var showMemberDialog by remember { mutableStateOf(false) }
    var syncMessage by remember { mutableStateOf<String?>(null) }
    var communityEvents by remember { mutableStateOf<List<CommunityEvent>>(emptyList()) }
    val user by AuthManager.currentUser

    LaunchedEffect(Unit) { CompanionSyncManager.loadCommunityEvents { communityEvents = it } }

    fun snapshot() = CompanionSnapshot(plannedIds, members, tradition, templeNote)

    if (showTraditions) {
        val traditions = listOf("General Indian", "Marathi", "Gujarati", "Bengali", "Tamil", "Telugu", "Punjabi", "Kannada", "Malayali")
        AlertDialog(
            onDismissRequest = { showTraditions = false },
            title = { Text("Choose your tradition") },
            text = { Column { traditions.forEach { option ->
                Row(Modifier.fillMaxWidth().clickable { tradition = option; prefs.selectedTradition = option; showTraditions = false }.padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = tradition == option, onClick = null); Text(option)
                }
            } } },
            confirmButton = { TextButton(onClick = { showTraditions = false }) { Text("Close") } }
        )
    }
    if (showMemberDialog) {
        AlertDialog(
            onDismissRequest = { showMemberDialog = false }, title = { Text("Add family member") },
            text = { OutlinedTextField(value = memberName, onValueChange = { memberName = it }, label = { Text("Name") }, singleLine = true) },
            confirmButton = { TextButton(onClick = { prefs.addHouseholdMember(memberName); members = prefs.householdMembers(); memberName = ""; showMemberDialog = false }) { Text("Add") } },
            dismissButton = { TextButton(onClick = { showMemberDialog = false }) { Text("Cancel") } }
        )
    }

    Scaffold(topBar = { AppTopBar(title = "My Festival Companion", language = language, isHindi = language == "hi", onLanguageChange = onLanguageChange, onToggleLanguage = onToggleLanguage, onSettingsClick = { navController.navigate("settings") }) }) { padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(14.dp), contentPadding = PaddingValues(vertical = 14.dp)) {
            item { Text("Plan, prepare and celebrate together", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant) }
            item {
                CompanionCard(Icons.Default.CloudSync, "Cloud sync", if (user == null) "Sign in from Settings to sync this plan" else "Signed in as ${AuthManager.displayName}", onClick = {
                    CompanionSyncManager.push(snapshot()) { error -> syncMessage = error ?: "Your festival plan is synced" }
                })
            }
            item {
                OutlinedButton(onClick = {
                    CompanionSyncManager.pull { cloud, error ->
                        if (cloud != null) {
                            cloud.plannedFestivalIds.forEach { prefs.setFestivalPlanned(it, true) }
                            plannedIds = prefs.plannedFestivalIds()
                            cloud.householdMembers.forEach { prefs.addHouseholdMember(it) }
                            members = prefs.householdMembers()
                            tradition = cloud.tradition; prefs.selectedTradition = tradition
                            templeNote = cloud.templeNote; prefs.templeNote = templeNote
                            syncMessage = "Cloud plan restored"
                        } else syncMessage = error
                    }
                }, modifier = Modifier.fillMaxWidth()) { Icon(Icons.Default.CloudDownload, null); Spacer(Modifier.width(8.dp)); Text("Restore cloud plan") }
            }
            if (syncMessage != null) item { Text(syncMessage!!, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall) }
            item { CompanionCard(Icons.Default.AccountTree, "Regional tradition", tradition, onClick = { showTraditions = true }) }
            item { SectionTitle("My celebrations") }
            items(FestivalRepository.festivals.take(10)) { festival ->
                FestivalPlanRow(festival, language, plannedIds.contains(festival.id), onToggle = { selected ->
                    prefs.setFestivalPlanned(festival.id, selected); plannedIds = prefs.plannedFestivalIds()
                }, onCalendar = { addToCalendar(context, festival) }, onOpen = { navController.navigate("festival/${festival.id}") })
            }
            item { SectionTitle("Family and sharing") }
            item {
                CompanionCard(Icons.Default.Group, "Family circle", if (members.isEmpty()) "Add people who help with preparations" else members.joinToString(), onClick = { showMemberDialog = true })
            }
            if (members.isNotEmpty()) item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) { members.forEach { person -> InputChip(selected = false, onClick = { prefs.removeHouseholdMember(person); members = prefs.householdMembers() }, label = { Text(person) }, trailingIcon = { Icon(Icons.Default.Close, null, Modifier.size(16.dp)) }) } }
            }
            item { CompanionCard(Icons.Default.Share, "Share a family plan", "Send your planned festivals and household list", onClick = { sharePlan(context, plannedIds, members) }) }
            item { SectionTitle("Useful on the day") }
            item { CompanionCard(Icons.Default.Restaurant, "Vrat and prasad", "Use each festival's shopping list for fasting-friendly prasad ingredients", onClick = { navController.navigate("shopping/${plannedIds.firstOrNull() ?: "diwali"}") }) }
            item { CompanionCard(Icons.Default.RecordVoiceOver, "Guided puja mode", "Open a puja vidhi and follow every step at your pace", onClick = { navController.navigate("festival/${plannedIds.firstOrNull() ?: "diwali"}") }) }
            item { SectionTitle("Local places and backup") }
            if (communityEvents.isNotEmpty()) {
                item { Text("Community events", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold) }
                items(communityEvents) { event -> CompanionCard(Icons.Default.Event, event.title, "${event.place} - ${event.date}", onClick = {}) }
            }
            item {
                OutlinedTextField(value = templeNote, onValueChange = { templeNote = it; prefs.templeNote = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Nearby temple or local event note") }, placeholder = { Text("Temple name, aarti time, or community event") }, minLines = 2)
            }
            item { CompanionCard(Icons.Default.Backup, "Export my plan", "Share a portable summary as a backup", onClick = { sharePlan(context, plannedIds, members, templeNote) }) }
            item { Text("Community events are loaded from Firestore. Calendar export opens your installed calendar app.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant) }
        }
    }
}

@Composable private fun SectionTitle(text: String) = Text(text, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))

@Composable private fun CompanionCard(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(8.dp)) { Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) { Icon(icon, null, Modifier.size(26.dp), tint = MaterialTheme.colorScheme.primary); Spacer(Modifier.width(14.dp)); Column(Modifier.weight(1f)) { Text(title, fontWeight = FontWeight.SemiBold); Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant) }; Icon(Icons.Default.ChevronRight, null) } }
}

@Composable private fun FestivalPlanRow(festival: Festival, language: String, planned: Boolean, onToggle: (Boolean) -> Unit, onCalendar: () -> Unit, onOpen: () -> Unit) {
    ElevatedCard(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) { Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = planned, onCheckedChange = onToggle); Column(Modifier.weight(1f).clickable(onClick = onOpen)) { Text("${festival.emoji} ${festival.getName(language)}", fontWeight = FontWeight.SemiBold); Text(festival.getDate2026(language), style = MaterialTheme.typography.bodySmall) }; IconButton(onClick = onCalendar) { Icon(Icons.Default.Event, "Add to calendar") } } }
}

private fun addToCalendar(context: android.content.Context, festival: Festival) {
    val cleanDate = festival.date2026En.substringAfterLast("-").replace("–", "").trim()
    val date = runCatching { SimpleDateFormat("d MMM yyyy", Locale.ENGLISH).parse(cleanDate) }.getOrNull()
    val intent = Intent(Intent.ACTION_INSERT).setData(CalendarContract.Events.CONTENT_URI).putExtra(CalendarContract.Events.TITLE, festival.nameEn).putExtra(CalendarContract.Events.DESCRIPTION, "Shubh Utsav preparation and puja plan").putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
    if (date != null) { intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date.time); intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, date.time + 86_400_000L) }
    runCatching { context.startActivity(intent) }.onFailure { Toast.makeText(context, "No calendar app found", Toast.LENGTH_SHORT).show() }
}

private fun sharePlan(context: android.content.Context, plannedIds: Set<String>, members: List<String>, templeNote: String = "") {
    val names = FestivalRepository.festivals.filter { plannedIds.contains(it.id) }.joinToString("\n") { "- ${it.nameEn}: ${it.date2026En}" }.ifBlank { "- No celebrations saved yet" }
    val message = "My Shubh Utsav plan\n\nCelebrations\n$names\n\nFamily: ${members.ifEmpty { listOf("Not added") }.joinToString()}\n${templeNote.takeIf { it.isNotBlank() }?.let { "\nLocal note: $it" } ?: ""}"
    context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, message), "Share plan"))
}
