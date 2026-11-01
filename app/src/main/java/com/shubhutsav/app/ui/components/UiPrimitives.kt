package com.shubhutsav.app.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubhutsav.app.ui.theme.KesariyaSaffron

/**
 * High-performance, spring-based tactile press animation modifier.
 * Creates an ultra-premium feel like iOS / high-end Android apps by smoothly
 * depressing by a slight ratio on touch and springing back with ripple feedback.
 */
fun Modifier.bouncyClickable(
    pressedScale: Float = 0.96f,
    rippleColor: Color = Color.Unspecified,
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) pressedScale else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "bouncy_scale"
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = ripple(
                bounded = true,
                color = if (rippleColor == Color.Unspecified) KesariyaSaffron.copy(alpha = 0.2f) else rippleColor
            ),
            enabled = enabled,
            onClick = onClick
        )
}

/**
 * Sacred Diya / auspicious glow pulse animation (oscillates alpha gently).
 */
fun Modifier.pulsingAlpha(
    minAlpha: Float = 0.45f,
    maxAlpha: Float = 1.0f,
    durationMs: Int = 1200
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_alpha_transition")
    val alpha by infiniteTransition.animateFloat(
        initialValue = minAlpha,
        targetValue = maxAlpha,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )
    this.graphicsLayer { this.alpha = alpha }
}

/**
 * Sacred Diya / auspicious scale pulse animation (gentle flame breathing).
 */
fun Modifier.pulsingScale(
    minScale: Float = 0.94f,
    maxScale: Float = 1.06f,
    durationMs: Int = 1400
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_scale_transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = minScale,
        targetValue = maxScale,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}

/**
 * Shimmer effect brush for auspicious cards, loading banners, and golden ribbons.
 */
@Composable
fun rememberShimmerBrush(
    shimmerColors: List<Color> = listOf(
        KesariyaSaffron.copy(alpha = 0.08f),
        KesariyaSaffron.copy(alpha = 0.25f),
        KesariyaSaffron.copy(alpha = 0.08f)
    )
): Brush {
    val transition = rememberInfiniteTransition(label = "shimmer_transition")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )
}

@Composable
fun SoftCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentPadding: Dp = 18.dp,
    elevation: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = containerColor,
        shadowElevation = elevation,
        tonalElevation = 1.dp,
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .then(
                if (onClick != null) Modifier.bouncyClickable(onClick = onClick)
                else Modifier
            )
    ) {
        Column(modifier = Modifier.padding(contentPadding), content = content)
    }
}

@Composable
fun SectionHeader(
    emoji: String,
    title: String,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Text(
                text = emoji,
                fontSize = 18.sp,
                modifier = Modifier.pulsingScale(minScale = 0.96f, maxScale = 1.04f, durationMs = 1800)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        if (actionLabel != null && onAction != null) {
            TextButton(
                onClick = onAction,
                modifier = Modifier.bouncyClickable(pressedScale = 0.92f, onClick = onAction)
            ) {
                Text(
                    text = actionLabel,
                    color = KesariyaSaffron,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun EmojiWell(
    emoji: String,
    tint: Color,
    size: Dp = 48.dp,
    emojiSize: Int = 22,
    enablePulse: Boolean = false
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(16.dp))
            .background(tint.copy(alpha = 0.14f))
            .then(if (enablePulse) Modifier.pulsingScale(minScale = 0.95f, maxScale = 1.05f) else Modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(text = emoji, fontSize = emojiSize.sp)
    }
}

@Composable
fun PillBadge(
    text: String,
    color: Color = KesariyaSaffron,
    isPulsing: Boolean = false,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = color.copy(alpha = 0.12f),
        modifier = modifier.then(if (isPulsing) Modifier.pulsingAlpha(minAlpha = 0.6f, maxAlpha = 1f) else Modifier)
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Composable
fun IconCircle(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun SegmentedPills(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            content = content
        )
    }
}
