package com.shubhutsav.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shubhutsav.app.data.DevotionalChannel
import com.shubhutsav.app.data.DevotionalVideo
import com.shubhutsav.app.data.DevotionalVideoRepository
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.components.InAppVideoPlayer
import com.shubhutsav.app.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun DevotionalVideosScreen(
    navController: NavHostController,
    language: String,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var videos by remember { mutableStateOf(DevotionalVideoRepository.defaultVideos) }
    var channelInfo by remember { mutableStateOf(DevotionalVideoRepository.defaultChannel) }
    var selectedCategory by remember { mutableStateOf("All") }
    var activeVideo by remember { mutableStateOf<DevotionalVideo?>(DevotionalVideoRepository.defaultVideos.firstOrNull()) }
    var isRefreshing by remember { mutableStateOf(false) }

    // Load remote feed
    LaunchedEffect(Unit) {
        val loaded = DevotionalVideoRepository.getVideos(context)
        if (loaded.isNotEmpty()) {
            videos = loaded
            if (activeVideo == null) {
                activeVideo = loaded.firstOrNull()
            }
        }
        channelInfo = DevotionalVideoRepository.getChannelInfo(context)
    }

    fun refreshVideos() {
        coroutineScope.launch {
            isRefreshing = true
            val loaded = DevotionalVideoRepository.getVideos(context)
            if (loaded.isNotEmpty()) {
                videos = loaded
            }
            channelInfo = DevotionalVideoRepository.getChannelInfo(context)
            isRefreshing = false
        }
    }

    val categories = remember(videos) {
        val list = mutableListOf("All")
        val unique = videos.map { it.category }.distinct()
        list.addAll(unique)
        list
    }

    val filteredVideos = remember(videos, selectedCategory) {
        if (selectedCategory == "All") videos else videos.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = when (language) {
                    "mr" -> "श्री एकदंत सत्संग"
                    "hi" -> "श्री एकदंत सत्संग"
                    else -> "Shree Ekdanta Videos"
                },
                language = language,
                onLanguageChange = onLanguageChange,
                onToggleLanguage = onToggleLanguage,
                onBackClick = { navController.popBackStack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Channel Header Card
            item {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.2.dp, VedicGold.copy(alpha = 0.5f)),
                    shadowElevation = 4.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        KesariyaSaffron.copy(alpha = 0.12f),
                                        Color.Transparent
                                    )
                                )
                            )
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = KesariyaSaffron,
                                modifier = Modifier.size(52.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = "🪔", fontSize = 26.sp)
                                }
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = channelInfo.getName(language),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = channelInfo.handle,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = KesariyaSaffron
                                )
                                Text(
                                    text = channelInfo.subscriberText,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            IconButton(
                                onClick = { refreshVideos() },
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(VedicGold.copy(alpha = 0.15f))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Refresh",
                                    tint = VedicGoldDark,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = channelInfo.getDescription(language),
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = SuccessGreen.copy(alpha = 0.15f),
                                border = BorderStroke(0.6.dp, SuccessGreen.copy(alpha = 0.4f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "🛡️", fontSize = 11.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = when (language) {
                                            "mr" -> "थेट अ‍ॅपमध्ये प्ले — शून्य भटकाव"
                                            "hi" -> "सीधे ऐप में प्ले — बिना किसी भटकाव"
                                            else -> "Plays in-app • Zero distractions"
                                        },
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SuccessGreen
                                    )
                                }
                            }

                            Button(
                                onClick = {
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(channelInfo.channelUrl))
                                        context.startActivity(intent)
                                    } catch (_: Exception) {}
                                },
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC0000)),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                modifier = Modifier.height(34.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.OpenInNew,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "सबस्क्राइब करा"
                                        "hi" -> "सब्सक्राइब करें"
                                        else -> "Subscribe"
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }

            // In-App Embedded Video Player (when a video is selected)
            item {
                AnimatedVisibility(
                    visible = activeVideo != null,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    activeVideo?.let { currentVideo ->
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = AuspiciousRed,
                                        modifier = Modifier.size(8.dp)
                                    ) {}
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = when (language) {
                                            "mr" -> "सध्या चालू आहे (Now Playing)"
                                            "hi" -> "वर्तमान प्रसारण (Now Playing)"
                                            else -> "Now Playing in App"
                                        },
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = KesariyaSaffron
                                    )
                                }

                                IconButton(
                                    onClick = { activeVideo = null },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close Player",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }

                            // Embedded YouTube Player
                            InAppVideoPlayer(
                                videoId = currentVideo.id,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = currentVideo.getTitle(language),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            if (currentVideo.getDescription(language).isNotBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentVideo.getDescription(language),
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // Category Filter Row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { cat ->
                        val isSelected = selectedCategory == cat
                        val catLabel = when (cat) {
                            "All" -> when (language) {
                                "mr", "hi" -> "सर्व"
                                else -> "All"
                            }
                            "Aarti" -> when (language) {
                                "mr", "hi" -> "आरती"
                                else -> "Aarti"
                            }
                            "Chalisa" -> when (language) {
                                "mr", "hi" -> "चालीसा"
                                else -> "Chalisa"
                            }
                            "Stotra" -> when (language) {
                                "mr", "hi" -> "स्तोत्र"
                                else -> "Stotra"
                            }
                            "Mantra" -> when (language) {
                                "mr", "hi" -> "मंत्र"
                                else -> "Mantra"
                            }
                            "Puja Vidhi" -> when (language) {
                                "mr", "hi" -> "पूजा विधी"
                                else -> "Puja Vidhi"
                            }
                            else -> cat
                        }

                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = cat },
                            label = {
                                Text(
                                    text = catLabel,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = KesariyaSaffron,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            // Video Collection Header
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.VideoLibrary,
                        contentDescription = null,
                        tint = VedicGoldDark,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (language) {
                            "mr" -> "सर्व पावन व्हिडिओ (${filteredVideos.size})"
                            "hi" -> "सभी पावन वीडियो (${filteredVideos.size})"
                            else -> "All Devotional Videos (${filteredVideos.size})"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Video Cards
            items(filteredVideos, key = { it.id }) { video ->
                val isCurrentPlaying = activeVideo?.id == video.id

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(
                        if (isCurrentPlaying) 1.5.dp else 0.8.dp,
                        if (isCurrentPlaying) KesariyaSaffron else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    ),
                    shadowElevation = if (isCurrentPlaying) 6.dp else 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            activeVideo = video
                        }
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Thumbnail Box with Play Badge and Duration
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .background(Color(0xFF140D10))
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(video.thumbnailUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = video.getTitle(language),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            // Subtle dark gradient overlay
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color.Black.copy(alpha = 0.3f),
                                                Color.Transparent,
                                                Color.Black.copy(alpha = 0.65f)
                                            )
                                        )
                                    )
                            )

                            // Category Pill on Top-Left
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = KesariyaSaffron,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = video.category,
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            // Center Play Button
                            Surface(
                                shape = CircleShape,
                                color = if (isCurrentPlaying) KesariyaSaffron else Color.Black.copy(alpha = 0.65f),
                                border = BorderStroke(1.5.dp, VedicGoldLight),
                                modifier = Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Play",
                                        tint = Color.White,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }

                            // Duration badge on Bottom-Right
                            if (video.duration.isNotBlank()) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color.Black.copy(alpha = 0.8f),
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = "⏱️ ${video.duration}",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }

                        // Video Title and Action Info
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
                        ) {
                            Text(
                                text = video.getTitle(language),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isCurrentPlaying) KesariyaSaffron else MaterialTheme.colorScheme.onSurface,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )

                            if (video.getDescription(language).isNotBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = video.getDescription(language),
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isCurrentPlaying) {
                                        when (language) {
                                            "mr" -> "▶️ सध्या चालू आहे"
                                            "hi" -> "▶️ अभी चल रहा है"
                                            else -> "▶️ Now Playing"
                                        }
                                    } else {
                                        when (language) {
                                            "mr" -> "अ‍ॅपमध्ये पहा"
                                            "hi" -> "ऐप में देखें"
                                            else -> "Play in App"
                                        }
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCurrentPlaying) KesariyaSaffron else RoyalMaroon
                                )

                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                                ) {
                                    Text(
                                        text = "YouTube HD",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
