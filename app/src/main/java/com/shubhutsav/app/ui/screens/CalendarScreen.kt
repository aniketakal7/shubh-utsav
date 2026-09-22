package com.shubhutsav.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shubhutsav.app.data.FestivalRepository
import com.shubhutsav.app.ui.components.AdBannerMockup
import com.shubhutsav.app.ui.components.FestivalRowCard
import com.shubhutsav.app.ui.components.LanguageSegmentItem
import com.shubhutsav.app.ui.components.PremiumUpgradeDialog
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.RoyalMaroon
import com.shubhutsav.app.ui.theme.VedicGold
import kotlinx.coroutines.launch

@Composable
fun CalendarScreen(
    navController: NavHostController,
    isHindi: Boolean = false,
    language: String = if (isHindi) "hi" else "en",
    isPremium: Boolean,
    onToggleLanguage: () -> Unit = {},
    onLanguageChange: (String) -> Unit = {},
    onUpgradePremium: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("2026") }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedMonth by remember { mutableStateOf("All") }
    var showPremiumDialog by remember { mutableStateOf(false) }

    val categories = listOf("All", "Major", "Diwali Season", "Navratri", "Shiva", "Krishna", "Regional", "National")
    val months = listOf("All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    val filteredFestivals = remember(searchQuery, selectedCategory, selectedMonth) {
        FestivalRepository.festivals.filter { festival ->
            val matchesSearch = searchQuery.isBlank() ||
                    festival.nameEn.contains(searchQuery, ignoreCase = true) ||
                    festival.nameHi.contains(searchQuery) ||
                    festival.summaryEn.contains(searchQuery, ignoreCase = true) ||
                    festival.summaryHi.contains(searchQuery)

            val matchesCategory = selectedCategory == "All" || festival.categoryEn == selectedCategory
            val matchesMonth = selectedMonth == "All" || festival.monthEn == selectedMonth

            matchesSearch && matchesCategory && matchesMonth
        }
    }

    // Nested scroll dynamic collapse dimensions
    val density = LocalDensity.current
    val filterSectionHeight = 225.dp
    val topBarHeight = 60.dp
    val filterSectionPx = with(density) { filterSectionHeight.toPx() }
    val topBarPx = with(density) { topBarHeight.toPx() }

    var filterOffsetPx by remember { mutableFloatStateOf(0f) }
    var topBarOffsetPx by remember { mutableFloatStateOf(0f) }

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Smooth animation helpers
    fun expandHeader() {
        coroutineScope.launch {
            val animTopBar = Animatable(topBarOffsetPx)
            val animFilter = Animatable(filterOffsetPx)
            launch {
                animTopBar.animateTo(0f, tween(260)) {
                    topBarOffsetPx = value
                }
            }
            launch {
                animFilter.animateTo(0f, tween(300)) {
                    filterOffsetPx = value
                }
            }
        }
    }

    fun collapseHeader() {
        coroutineScope.launch {
            val animFilter = Animatable(filterOffsetPx)
            animFilter.animateTo(-filterSectionPx, tween(250)) {
                filterOffsetPx = value
            }
        }
    }

    // Advanced NestedScrollConnection for 2-stage dynamic minimizing:
    // Stage 1: Collapses the heavy filter panel (Search + Year + Categories + Months) from 225dp to 0dp.
    // Stage 2: On continued downward scroll, smoothly slides the compact top bar offscreen,
    //          giving 100% full screen space to festival cards!
    // On scroll up: Instantly slides the compact top bar back in so filters and search are 1 tap away.
    // At list top: Re-expands the full filter controls.
    val nestedScrollConnection = remember(filterSectionPx, topBarPx) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y

                // Scrolling down (delta < 0): Content moving up
                if (delta < 0) {
                    // Stage 1: Collapse the filter panel first
                    if (filterOffsetPx > -filterSectionPx) {
                        val oldOffset = filterOffsetPx
                        filterOffsetPx = (filterOffsetPx + delta).coerceIn(-filterSectionPx, 0f)
                        val consumed = filterOffsetPx - oldOffset
                        return Offset(0f, consumed)
                    }
                    // Stage 2: Once filters are collapsed, slide the top bar away for 100% full screen space
                    if (topBarOffsetPx > -topBarPx) {
                        val oldOffset = topBarOffsetPx
                        topBarOffsetPx = (topBarOffsetPx + delta).coerceIn(-topBarPx, 0f)
                        val consumed = topBarOffsetPx - oldOffset
                        return Offset(0f, consumed)
                    }
                }

                // Scrolling up (delta > 0): Content moving down
                if (delta > 0) {
                    // Instantly restore the compact top bar so navigation & filters are accessible
                    if (topBarOffsetPx < 0f) {
                        val oldOffset = topBarOffsetPx
                        topBarOffsetPx = (topBarOffsetPx + delta).coerceIn(-topBarPx, 0f)
                        val consumed = topBarOffsetPx - oldOffset
                        return Offset(0f, consumed)
                    }
                    // If user is at top of list, expand the filter section
                    if (lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset == 0) {
                        if (filterOffsetPx < 0f) {
                            val oldOffset = filterOffsetPx
                            filterOffsetPx = (filterOffsetPx + delta).coerceIn(-filterSectionPx, 0f)
                            val consumed = filterOffsetPx - oldOffset
                            return Offset(0f, consumed)
                        }
                    }
                }

                return Offset.Zero
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                // When pulling down at the very top of the list, expand filter panel
                if (available.y > 0 && filterOffsetPx < 0f) {
                    val oldOffset = filterOffsetPx
                    filterOffsetPx = (filterOffsetPx + available.y).coerceIn(-filterSectionPx, 0f)
                    val consumed = filterOffsetPx - oldOffset
                    return Offset(0f, consumed)
                }
                return Offset.Zero
            }
        }
    }

    val filterFraction = (-filterOffsetPx / filterSectionPx).coerceIn(0f, 1f)
    val currentFilterHeight = (filterSectionHeight * (1f - filterFraction)).coerceAtLeast(0.dp)
    val currentTopBarHeight = (topBarHeight + with(density) { topBarOffsetPx.toDp() }).coerceAtLeast(0.dp)

    if (showPremiumDialog) {
        PremiumUpgradeDialog(
            isHindi = language != "en",
            language = language,
            onDismiss = { showPremiumDialog = false },
            onUpgrade = {
                onUpgradePremium()
                showPremiumDialog = false
            }
        )
    }

    Scaffold(
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 0.dp
                ) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("home") },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = {
                            Text(
                                when (language) {
                                    "mr" -> "मुख्य"
                                    "hi" -> "मुख्य"
                                    else -> "Home"
                                },
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    NavigationBarItem(
                        selected = true,
                        onClick = { /* Already on Calendar */ },
                        icon = { Icon(Icons.Default.CalendarMonth, contentDescription = "Calendar") },
                        label = {
                            Text(
                                when (language) {
                                    "mr" -> "कॅलेंडर"
                                    "hi" -> "कैलेंडर"
                                    else -> "Calendar"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("settings") },
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                        label = {
                            Text(
                                when (language) {
                                    "mr" -> "सेटिंग्ज"
                                    "hi" -> "सेटिंग्स"
                                    else -> "Settings"
                                },
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
                .padding(bottom = padding.calculateBottomPadding())
                .nestedScroll(nestedScrollConnection)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Dynamic Minimizing Header Container
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = if (filterFraction > 0.05f) 4.dp else 0.dp,
                    shadowElevation = if (filterFraction > 0.05f) 3.dp else 0.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        // Top Bar Row (Smoothly switches between Expanded title and Minimized quick-filter bar)
                        if (currentTopBarHeight > 0.dp) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(currentTopBarHeight)
                                    .clipToBounds()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    // Left Section
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.weight(1f, fill = false)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(34.dp)
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = "🪔", fontSize = 18.sp)
                                        }

                                        Spacer(modifier = Modifier.width(10.dp))

                                        if (filterFraction < 0.5f) {
                                            // Expanded Title
                                            Text(
                                                text = when (language) {
                                                    "mr" -> "उत्सव दिनदर्शिका"
                                                    "hi" -> "उत्सव कैलेंडर"
                                                    else -> "Festival Calendar"
                                                },
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.primary,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        } else {
                                            // Minimized Compact Title & Quick Filter Pill
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .clickable { expandHeader() }
                                                    .padding(vertical = 2.dp)
                                            ) {
                                                Text(
                                                    text = when (language) {
                                                        "mr" -> "उत्सव (${filteredFestivals.size})"
                                                        "hi" -> "त्योहार (${filteredFestivals.size})"
                                                        else -> "Festivals (${filteredFestivals.size})"
                                                    },
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Surface(
                                                    shape = RoundedCornerShape(10.dp),
                                                    color = KesariyaSaffron.copy(alpha = 0.14f),
                                                    border = BorderStroke(0.8.dp, KesariyaSaffron.copy(alpha = 0.5f))
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = "$selectedYear • ${if (selectedCategory == "All") (when (language) { "mr" -> "सर्व"; "hi" -> "सभी"; else -> "All" }) else selectedCategory}",
                                                            fontSize = 11.sp,
                                                            fontWeight = FontWeight.SemiBold,
                                                            color = KesariyaSaffron
                                                        )
                                                        Icon(
                                                            imageVector = Icons.Default.KeyboardArrowDown,
                                                            contentDescription = "Expand Filters",
                                                            tint = KesariyaSaffron,
                                                            modifier = Modifier.size(14.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // Right Actions Section
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        // Quick Search Icon Button when minimized
                                        if (filterFraction >= 0.5f) {
                                            IconButton(
                                                onClick = { expandHeader() },
                                                modifier = Modifier.size(32.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Search,
                                                    contentDescription = "Search",
                                                    tint = KesariyaSaffron,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                            }
                                            IconButton(
                                                onClick = { expandHeader() },
                                                modifier = Modifier.size(32.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Tune,
                                                    contentDescription = "Filter",
                                                    tint = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                            }
                                        } else {
                                            // Quick collapse button when expanded
                                            IconButton(
                                                onClick = { collapseHeader() },
                                                modifier = Modifier.size(32.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowUp,
                                                    contentDescription = "Minimize Header",
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.width(4.dp))

                                        // Segmented Tri-Pill Language Selector (EN | हिं | मरा)
                                        Surface(
                                            shape = RoundedCornerShape(16.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                                            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.padding(2.dp)
                                            ) {
                                                val currentLang = if (language in listOf("en", "hi", "mr")) language else if (isHindi) "hi" else "en"

                                                LanguageSegmentItem(
                                                    label = "EN",
                                                    isSelected = currentLang == "en",
                                                    activeColor = MaterialTheme.colorScheme.primary,
                                                    onClick = { onLanguageChange("en") }
                                                )

                                                LanguageSegmentItem(
                                                    label = "हिं",
                                                    isSelected = currentLang == "hi",
                                                    activeColor = KesariyaSaffron,
                                                    onClick = { onLanguageChange("hi") }
                                                )

                                                LanguageSegmentItem(
                                                    label = "मरा",
                                                    isSelected = currentLang == "mr",
                                                    activeColor = Color(0xFFC2410C),
                                                    onClick = { onLanguageChange("mr") }
                                                )
                                            }
                                        }

                                        IconButton(
                                            onClick = { navController.navigate("settings") },
                                            modifier = Modifier.size(34.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Settings,
                                                contentDescription = "Settings",
                                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Collapsible Filter Panel (Dynamically shrinks from 225dp to 0dp)
                        if (currentFilterHeight > 0.dp) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(currentFilterHeight)
                                    .clipToBounds()
                                    .graphicsLayer {
                                        alpha = (1f - filterFraction * 1.35f).coerceIn(0f, 1f)
                                    }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 2.dp)
                                ) {
                                    // Capsule Search Bar
                                    OutlinedTextField(
                                        value = searchQuery,
                                        onValueChange = { searchQuery = it },
                                        placeholder = {
                                            Text(
                                                text = when (language) {
                                                    "mr" -> "उत्सव किंवा मंत्र शोधा (दिवाळी, शिवरात्री)..."
                                                    "hi" -> "त्योहार या मंत्र खोजें (दीवाली, शिवरात्रि)..."
                                                    else -> "Search celebrations (Diwali, Shivratri)..."
                                                },
                                                fontSize = 12.5.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        },
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Search,
                                                contentDescription = "Search",
                                                tint = KesariyaSaffron,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        },
                                        trailingIcon = {
                                            if (searchQuery.isNotBlank()) {
                                                IconButton(onClick = { searchQuery = "" }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Clear,
                                                        contentDescription = "Clear",
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                }
                                            }
                                        },
                                        singleLine = true,
                                        shape = RoundedCornerShape(20.dp),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                            focusedBorderColor = KesariyaSaffron,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(48.dp)
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    // Year Selector Bar
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = when (language) {
                                                "mr" -> "पंचांग वर्ष"
                                                "hi" -> "पंचांग वर्ष"
                                                else -> "Calendar Year"
                                            },
                                            style = MaterialTheme.typography.titleSmall,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )

                                        Surface(
                                            shape = RoundedCornerShape(14.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                                        ) {
                                            Row(modifier = Modifier.padding(2.dp)) {
                                                listOf("2025", "2026", "2027").forEach { yr ->
                                                    val isSelected = selectedYear == yr
                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(10.dp))
                                                            .background(
                                                                if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                                            )
                                                            .clickable { selectedYear = yr }
                                                            .padding(horizontal = 12.dp, vertical = 4.dp),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Text(
                                                            text = yr,
                                                            fontSize = 11.5.sp,
                                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))

                                    // Category Filter Chips Row
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    ) {
                                        items(categories) { cat ->
                                            val (label, icon) = when (cat) {
                                                "All" -> Pair(when (language) { "mr" -> "सर्व"; "hi" -> "सभी"; else -> "All" }, "🪔")
                                                "Major" -> Pair(when (language) { "mr" -> "प्रमुख"; "hi" -> "प्रमुख"; else -> "Major" }, "✨")
                                                "Diwali Season" -> Pair(when (language) { "mr" -> "दिवाळी"; "hi" -> "दीपावली"; else -> "Diwali" }, "🪔")
                                                "Navratri" -> Pair(when (language) { "mr" -> "नवरात्र"; "hi" -> "नवरात्रि"; else -> "Navratri" }, "🔱")
                                                "Shiva" -> Pair(when (language) { "mr" -> "शिव"; "hi" -> "शिव"; else -> "Shiva" }, "🕉️")
                                                "Krishna" -> Pair(when (language) { "mr" -> "कृष्ण"; "hi" -> "कृष्ण"; else -> "Krishna" }, "🦚")
                                                "Regional" -> Pair(when (language) { "mr" -> "प्रांतीय"; "hi" -> "प्रांतीय"; else -> "Regional" }, "🌸")
                                                else -> Pair(when (language) { "mr" -> "राष्ट्रीय"; "hi" -> "राष्ट्रीय"; else -> "National" }, "🇮🇳")
                                            }
                                            FilterChip(
                                                selected = selectedCategory == cat,
                                                onClick = { selectedCategory = cat },
                                                leadingIcon = { Text(text = icon, fontSize = 11.sp) },
                                                label = {
                                                    Text(
                                                        label,
                                                        fontSize = 11.5.sp,
                                                        fontWeight = if (selectedCategory == cat) FontWeight.Bold else FontWeight.Medium
                                                    )
                                                },
                                                colors = FilterChipDefaults.filterChipColors(
                                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
                                                    selectedLabelColor = MaterialTheme.colorScheme.primary
                                                )
                                            )
                                        }
                                    }

                                    // Month Filter Chips Row
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        modifier = Modifier.padding(bottom = 2.dp)
                                    ) {
                                        items(months) { m ->
                                            val label = when (m) {
                                                "All" -> when (language) { "mr" -> "सर्व महिने"; "hi" -> "सभी माह"; else -> "All Months" }
                                                "January" -> when (language) { "mr" -> "जानेवारी"; "hi" -> "जनवरी"; else -> "Jan" }
                                                "February" -> when (language) { "mr" -> "फेब्रुवारी"; "hi" -> "फ़रवरी"; else -> "Feb" }
                                                "March" -> when (language) { "mr" -> "मार्च"; "hi" -> "मार्च"; else -> "Mar" }
                                                "April" -> when (language) { "mr" -> "एप्रिल"; "hi" -> "अप्रैल"; else -> "Apr" }
                                                "May" -> when (language) { "mr" -> "मे"; "hi" -> "मई"; else -> "May" }
                                                "June" -> when (language) { "mr" -> "जून"; "hi" -> "जून"; else -> "Jun" }
                                                "July" -> when (language) { "mr" -> "जुलै"; "hi" -> "जुलाई"; else -> "Jul" }
                                                "August" -> when (language) { "mr" -> "ऑगस्ट"; "hi" -> "अगस्त"; else -> "Aug" }
                                                "September" -> when (language) { "mr" -> "सप्टेंबर"; "hi" -> "सितंबर"; else -> "Sep" }
                                                "October" -> when (language) { "mr" -> "ऑक्टोबर"; "hi" -> "अक्टूबर"; else -> "Oct" }
                                                "November" -> when (language) { "mr" -> "नोव्हेंबर"; "hi" -> "नवंबर"; else -> "Nov" }
                                                else -> when (language) { "mr" -> "डिसेंबर"; "hi" -> "दिसंबर"; else -> "Dec" }
                                            }
                                            FilterChip(
                                                selected = selectedMonth == m,
                                                onClick = { selectedMonth = m },
                                                label = { Text(label, fontSize = 10.5.sp) }
                                            )
                                        }
                                    }

                                    // Results count header
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 2.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = when (language) {
                                                "mr" -> "${filteredFestivals.size} पावन उत्सव सापडले"
                                                "hi" -> "${filteredFestivals.size} पावन उत्सव मिले"
                                                else -> "${filteredFestivals.size} sacred celebrations found"
                                            },
                                            fontSize = 11.5.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )

                                        if (searchQuery.isNotBlank() || selectedCategory != "All" || selectedMonth != "All") {
                                            TextButton(
                                                onClick = {
                                                    searchQuery = ""
                                                    selectedCategory = "All"
                                                    selectedMonth = "All"
                                                },
                                                contentPadding = PaddingValues(0.dp)
                                            ) {
                                                Text(
                                                    text = when (language) {
                                                        "mr" -> "फिल्टर काढा"
                                                        "hi" -> "फ़िल्टर हटाएं"
                                                        else -> "Reset Filters"
                                                    },
                                                    fontSize = 11.5.sp,
                                                    color = KesariyaSaffron,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Festival List - Receives FULL SCREEN SPACE as the header minimizes!
                LazyColumn(
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    items(filteredFestivals, key = { it.id }) { festival ->
                        FestivalRowCard(
                            festival = festival,
                            isHindi = language != "en",
                            language = language,
                            selectedYear = selectedYear,
                            onClick = { navController.navigate("festival/${festival.id}") }
                        )
                    }

                    // Ad banner at bottom if not premium
                    if (!isPremium) {
                        item {
                            AdBannerMockup(
                                isHindi = language != "en",
                                onRemoveAdsClick = { showPremiumDialog = true }
                            )
                        }
                    }
                }
            }

            // Floating "Scroll to Top & Expand Filters" pill when scrolled down deep
            val isScrolledDown by remember {
                derivedStateOf {
                    lazyListState.firstVisibleItemIndex > 2 || filterFraction > 0.8f
                }
            }

            AnimatedVisibility(
                visible = isScrolledDown,
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut() + slideOutVertically { it / 2 },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 14.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    tonalElevation = 6.dp,
                    shadowElevation = 6.dp,
                    border = BorderStroke(1.dp, VedicGold.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .clickable {
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(0)
                                expandHeader()
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowUpward,
                            contentDescription = "Scroll to top",
                            tint = Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = when (language) {
                                "mr" -> "वर जा & फिल्टर"
                                "hi" -> "शीर्ष & फ़िल्टर"
                                else -> "Top & Filters"
                            },
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
