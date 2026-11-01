package com.shubhutsav.app.ui.components

import android.app.Activity
import android.speech.tts.TextToSpeech
import android.view.WindowManager
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shubhutsav.app.data.Festival
import com.shubhutsav.app.data.PreferencesManager
import com.shubhutsav.app.data.SacredSoundPlayer
import com.shubhutsav.app.data.getName
import com.shubhutsav.app.data.getTitle
import com.shubhutsav.app.data.getInstruction
import com.shubhutsav.app.data.getPracticalTip
import com.shubhutsav.app.ui.theme.*

/**
 * Dedicated hands-free Puja Mode:
 * - Keeps screen awake during worship (FLAG_KEEP_SCREEN_ON)
 * - Large high-contrast typography readable from distance at altar
 * - Step progression with audio temple bell chimes
 * - Text-To-Speech recitation for hands-free guidance
 */
@Composable
fun PujaModeDialog(
    festival: Festival,
    language: String,
    initialStepIndex: Int = 0,
    textToSpeech: TextToSpeech?,
    ttsReady: Boolean,
    onDismiss: () -> Unit,
    onStepCompletedChanged: () -> Unit = {}
) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }
    val activity = context as? Activity

    // Keep screen awake while in Puja Mode
    DisposableEffect(Unit) {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    val totalSteps = festival.steps.size
    var currentStepIndex by remember { mutableIntStateOf(initialStepIndex.coerceIn(0, (totalSteps - 1).coerceAtLeast(0))) }
    val currentStep = festival.steps.getOrNull(currentStepIndex)
    val isStepDone = currentStep != null && prefs.isStepCompleted(festival.id, currentStepIndex)

    fun speakCurrentStep() {
        if (ttsReady && textToSpeech != null && currentStep != null) {
            val textToRead = "${currentStep.getTitle(language)}. ${currentStep.getInstruction(language)}"
            textToSpeech.stop()
            textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, "puja_step_$currentStepIndex")
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF1B0505), Color(0xFF0F0000), Color(0xFF000000))
                    )
                )
                .systemBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                // Top Action Bar: Puja Mode Header & Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = KesariyaSaffron.copy(alpha = 0.2f),
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "🪔",
                                    fontSize = 18.sp,
                                    modifier = Modifier.pulsingScale(minScale = 0.94f, maxScale = 1.10f, durationMs = 1200)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = when (language) {
                                    "mr" -> "पूजा मोड (स्क्रीन नेहमी चालू)"
                                    "hi" -> "पूजा मोड (स्क्रीन हमेशा ऑन)"
                                    else -> "Puja Mode (Screen Stays Awake)"
                                },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = VedicGold
                            )
                            Text(
                                text = "${festival.getName(language)} • ${currentStepIndex + 1} / $totalSteps",
                                fontSize = 11.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Progress Bar across steps with animated spring
                val progressFraction = if (totalSteps > 0) (currentStepIndex + 1).toFloat() / totalSteps else 0f
                val animProgress by animateFloatAsState(
                    targetValue = progressFraction,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "puja_step_progress"
                )
                LinearProgressIndicator(
                    progress = { animProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = KesariyaSaffron,
                    trackColor = Color.White.copy(alpha = 0.15f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Main Scrollable Step Content
                if (currentStep != null) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Step Number and Title (Giant High Contrast)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = KesariyaSaffron,
                                modifier = Modifier.size(40.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${currentStep.stepNumber}",
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Text(
                                text = currentStep.getTitle(language),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                lineHeight = 30.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        // Procedure Instructions
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White.copy(alpha = 0.08f),
                            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.15f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = when (language) {
                                        "mr" -> "विधी कृती :"
                                        "hi" -> "विधि प्रक्रिया :"
                                        else -> "Procedure :"
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KesariyaSaffron,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = currentStep.getInstruction(language),
                                    fontSize = 17.sp,
                                    lineHeight = 26.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            }
                        }

                        // Mantra Card (if present)
                        if (!currentStep.mantra.isNullOrBlank()) {
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = VedicGold.copy(alpha = 0.12f),
                                border = BorderStroke(1.5.dp, VedicGold.copy(alpha = 0.5f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(18.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "🕉️ " + when (language) {
                                            "mr" -> "पवित्र मंत्र"
                                            "hi" -> "पवित्र मंत्र"
                                            else -> "Sacred Mantra"
                                        },
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = VedicGoldLight
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = currentStep.mantra ?: "",
                                        fontSize = 20.sp,
                                        lineHeight = 32.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = VedicGold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        // Practical Pro-Tip (if present)
                        val practicalTip = currentStep.getPracticalTip(language)
                        if (!practicalTip.isNullOrBlank()) {
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = Color.White.copy(alpha = 0.05f),
                                border = BorderStroke(0.6.dp, Color.White.copy(alpha = 0.1f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(text = "💡", fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = practicalTip,
                                        fontSize = 13.sp,
                                        lineHeight = 19.sp,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                }
                            }
                        }

                        // Audio Utility Row: Ring Bell & Speak Out Aloud
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedButton(
                                onClick = { SacredSoundPlayer.playTempleBell(context) },
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = VedicGold),
                                border = BorderStroke(1.dp, VedicGold.copy(alpha = 0.5f)),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = VedicGold,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "🔔 घंटी नाद"
                                        "hi" -> "🔔 घंटी नाद"
                                        else -> "🔔 Temple Bell"
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            OutlinedButton(
                                onClick = { speakCurrentStep() },
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f)),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = when (language) {
                                        "mr" -> "🔊 ऐका"
                                        "hi" -> "🔊 सुनें"
                                        else -> "🔊 Recite"
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom Navigation & Completion Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Previous Step Button
                    IconButton(
                        onClick = {
                            if (currentStepIndex > 0) {
                                currentStepIndex--
                                SacredSoundPlayer.playBeadClick(context)
                            }
                        },
                        enabled = currentStepIndex > 0,
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(if (currentStepIndex > 0) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.05f))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Step",
                            tint = if (currentStepIndex > 0) Color.White else Color.Gray
                        )
                    }

                    // Next / Mark Completed Button
                    Button(
                        onClick = {
                            prefs.setStepCompleted(festival.id, currentStepIndex, true)
                            onStepCompletedChanged()

                            if (currentStepIndex < totalSteps - 1) {
                                currentStepIndex++
                                SacredSoundPlayer.playBeadClick(context)
                            } else {
                                // Final step celebration chime!
                                SacredSoundPlayer.playMalaComplete(context)
                            }
                        },
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isStepDone) SuccessGreen else KesariyaSaffron
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                    ) {
                        Icon(
                            imageVector = if (isStepDone) Icons.Default.Check else Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (currentStepIndex == totalSteps - 1) {
                                when (language) {
                                    "mr" -> "पूजा संपन्न (पूर्ण करा)"
                                    "hi" -> "पूजा संपन्न (पूर्ण करें)"
                                    else -> "Complete Puja"
                                }
                            } else {
                                when (language) {
                                    "mr" -> "पूर्ण & पुढील पायरी"
                                    "hi" -> "पूर्ण एवं अगला चरण"
                                    else -> "Mark Done & Next"
                                }
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
