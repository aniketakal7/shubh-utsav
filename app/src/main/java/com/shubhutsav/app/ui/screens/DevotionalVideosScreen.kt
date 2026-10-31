package com.shubhutsav.app.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldDark
import java.io.ByteArrayInputStream

/**
 * Checks whether an incoming URL request belongs to known advertising networks,
 * video ad streams, or tracking beacons so they can be blocked at the network layer.
 */
private fun isAdUrl(url: String): Boolean {
    val lower = url.lowercase()
    return lower.contains("doubleclick.net") ||
            lower.contains("googleads") ||
            lower.contains("pagead") ||
            lower.contains("googlesyndication") ||
            lower.contains("adservice.google") ||
            lower.contains("youtube.com/api/stats/ads") ||
            lower.contains("youtube.com/ptracking") ||
            lower.contains("youtube.com/youtubei/v1/att/") ||
            lower.contains("youtube.com/pagead/") ||
            lower.contains("/pagead/lvp") ||
            lower.contains("/pagead/adview") ||
            lower.contains("adformat=") ||
            lower.contains("ad_type=") ||
            lower.contains("ad_tag=") ||
            lower.contains("ad_unit=") ||
            lower.contains("&ad_") ||
            lower.contains("/ads?")
}

/**
 * JavaScript & CSS injection script:
 * 1. Permanently hides all YouTube banner ads, sponsored cards, promotional boxes, and app upsell popups via CSS.
 * 2. Continuously clicks "Skip Ad" buttons immediately as soon as they appear.
 * 3. Speeds up, mutes, and fast-forwards in-stream video ads to 0.05s before the end so they finish instantly.
 * 4. Removes sponsored recommendations from the channel feed.
 */
private val AD_BLOCK_SCRIPT = """
(function() {
    function injectAdBlockCss() {
        if (!document.getElementById('shubh-adblock-styles')) {
            const style = document.createElement('style');
            style.id = 'shubh-adblock-styles';
            style.textContent = `
                .ytp-ad-module,
                .ytp-ad-overlay-container,
                .ytp-ad-message-container,
                .video-ads,
                .ytp-ad-player-overlay,
                .ytp-ad-player-overlay-layout,
                .ytp-ad-skip-button-container,
                .ytp-ad-text,
                .ytp-ad-preview-container,
                .ytp-ad-image-overlay,
                ytd-promoted-video-renderer,
                ytd-banner-promo-renderer,
                ytd-ad-slot-renderer,
                ytd-in-feed-ad-layout-renderer,
                ytm-promoted-sparkles-web-renderer,
                ytm-promoted-sparkles-text-search-web-renderer,
                ytm-companion-ad-renderer,
                ytm-ad-slot-renderer,
                ytm-paid-content-overlay-renderer,
                ytm-open-app-banner-renderer,
                .ytm-open-app-banner-renderer,
                #app-banner,
                ytm-upsell-dialog-renderer,
                .ad-container,
                .ad-div,
                #player-ads,
                .sparkles-light-cta,
                .ytm-sparkles-cta,
                ytm-item-section-renderer[data-ad-id] {
                    display: none !important;
                    visibility: hidden !important;
                    height: 0px !important;
                    min-height: 0px !important;
                    opacity: 0 !important;
                    pointer-events: none !important;
                }
            `;
            (document.head || document.documentElement).appendChild(style);
        }
    }

    function skipAndRemoveAds() {
        injectAdBlockCss();

        // 1. Click skip ad buttons immediately
        const skipButtons = document.querySelectorAll(
            '.ytp-ad-skip-button, .ytp-ad-skip-button-modern, .ytp-skip-ad-button, .videoAdUiSkipButton, button.ytp-ad-skip-button-text, .ytp-ad-skip-button-container button, [class*="skip-button"], button.ytp-skip-ad-button'
        );
        for (let i = 0; i < skipButtons.length; i++) {
            try { skipButtons[i].click(); } catch (e) {}
        }

        // 2. Fast forward video if in-stream ad is interrupting / showing
        const adShowing = document.querySelector('.ad-showing, .ad-interrupting, .ytp-ad-player-overlay, [class*="ad-showing"], [class*="ad-interrupting"]');
        const video = document.querySelector('video');
        if (adShowing && video) {
            try {
                video.muted = true;
                video.playbackRate = 16.0;
                if (!isNaN(video.duration) && isFinite(video.duration) && video.duration > 0) {
                    video.currentTime = video.duration - 0.05;
                }
            } catch (e) {}
        }

        // 3. Close ad overlays
        const closeButtons = document.querySelectorAll(
            '.ytp-ad-overlay-close-button, .ytp-ad-overlay-close-container, button[aria-label="Close ad"]'
        );
        for (let i = 0; i < closeButtons.length; i++) {
            try { closeButtons[i].click(); } catch (e) {}
        }

        // 4. Remove sponsored/feed promo elements
        const promos = document.querySelectorAll(
            'ytm-promoted-sparkles-web-renderer, ytm-companion-ad-renderer, ytm-ad-slot-renderer, ytm-open-app-banner-renderer, ytm-upsell-dialog-renderer'
        );
        for (let i = 0; i < promos.length; i++) {
            try { promos[i].remove(); } catch (e) {}
        }
    }

    // Run now
    skipAndRemoveAds();

    // Run periodically
    if (!window.__shubh_adblock_timer) {
        window.__shubh_adblock_timer = setInterval(skipAndRemoveAds, 250);
    }

    // Run on DOM changes
    if (!window.__shubh_adblock_observer && window.MutationObserver) {
        window.__shubh_adblock_observer = new MutationObserver(skipAndRemoveAds);
        window.__shubh_adblock_observer.observe(document.body || document.documentElement, {
            childList: true,
            subtree: true
        });
    }
})();
""".trimIndent()

/**
 * Dedicated In-App Channel Viewer for @ShreeEkdanta with built-in Ad Blocker.
 * Displays the authentic interface of the YouTube channel, playing videos directly inside
 * the app with zero external redirects and blocking advertisements for a peaceful satsang experience.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DevotionalVideosScreen(
    navController: NavHostController,
    language: String,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val context = LocalContext.current
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var canGoBack by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var loadingProgress by remember { mutableIntStateOf(0) }
    var customView by remember { mutableStateOf<View?>(null) }
    var customViewCallback by remember { mutableStateOf<WebChromeClient.CustomViewCallback?>(null) }

    val channelUrl = "https://m.youtube.com/@ShreeEkdanta"

    // Intercept back navigation: if watching a video or navigating inside channel, step back.
    BackHandler(enabled = canGoBack || customView != null) {
        if (customView != null) {
            customViewCallback?.onCustomViewHidden()
            customView = null
        } else if (webViewRef?.canGoBack() == true) {
            webViewRef?.goBack()
        } else {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            if (customView == null) {
                AppTopBar(
                    title = when (language) {
                        "mr" -> "श्री एकदंत (@ShreeEkdanta)"
                        "hi" -> "श्री एकदंत (@ShreeEkdanta)"
                        else -> "Shree Ekdanta Channel"
                    },
                    language = language,
                    onLanguageChange = onLanguageChange,
                    onToggleLanguage = onToggleLanguage,
                    onBackClick = {
                        if (webViewRef?.canGoBack() == true) {
                            webViewRef?.goBack()
                        } else {
                            navController.popBackStack()
                        }
                    }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Fullscreen video container when video enters full screen
            if (customView != null) {
                AndroidView(
                    factory = { ctx ->
                        FrameLayout(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            setBackgroundColor(0xFF000000.toInt())
                            addView(customView)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            setBackgroundColor(0xFF0F0B0C.toInt())

                            settings.apply {
                                javaScriptEnabled = true
                                domStorageEnabled = true
                                mediaPlaybackRequiresUserGesture = false
                                loadsImagesAutomatically = true
                                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                                cacheMode = WebSettings.LOAD_DEFAULT
                                setSupportZoom(true)
                                builtInZoomControls = false
                                userAgentString = "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Mobile Safari/537.36"
                            }

                            webChromeClient = object : WebChromeClient() {
                                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                    loadingProgress = newProgress
                                    isLoading = newProgress < 100
                                    // Inject ad block script as DOM becomes available
                                    if (newProgress >= 25) {
                                        view?.evaluateJavascript(AD_BLOCK_SCRIPT, null)
                                    }
                                }

                                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                                    customView = view
                                    customViewCallback = callback
                                }

                                override fun onHideCustomView() {
                                    customView = null
                                    customViewCallback = null
                                }
                            }

                            webViewClient = object : WebViewClient() {
                                override fun shouldInterceptRequest(
                                    view: WebView?,
                                    request: WebResourceRequest?
                                ): WebResourceResponse? {
                                    val url = request?.url?.toString() ?: ""
                                    // Block ad networks, trackers, and ad endpoints
                                    if (isAdUrl(url)) {
                                        return WebResourceResponse(
                                            "text/plain",
                                            "UTF-8",
                                            ByteArrayInputStream(ByteArray(0))
                                        )
                                    }
                                    return super.shouldInterceptRequest(view, request)
                                }

                                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                    isLoading = true
                                    canGoBack = view?.canGoBack() == true
                                    view?.evaluateJavascript(AD_BLOCK_SCRIPT, null)
                                }

                                override fun onPageFinished(view: WebView?, url: String?) {
                                    isLoading = false
                                    canGoBack = view?.canGoBack() == true
                                    view?.evaluateJavascript(AD_BLOCK_SCRIPT, null)
                                }

                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    request: WebResourceRequest?
                                ): Boolean {
                                    val target = request?.url?.toString() ?: ""
                                    // Play all channel videos, shorts, and playlists inside this in-app viewer
                                    return if (target.contains("youtube.com") || target.contains("youtu.be")) {
                                        false
                                    } else {
                                        true // Block external redirects
                                    }
                                }
                            }

                            loadUrl(channelUrl)
                            webViewRef = this
                        }
                    },
                    update = { view ->
                        canGoBack = view.canGoBack()
                    },
                    modifier = Modifier.fillMaxSize()
                )

                // Top Loading Progress Bar
                if (isLoading) {
                    LinearProgressIndicator(
                        progress = { loadingProgress / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .align(Alignment.TopCenter),
                        color = KesariyaSaffron,
                        trackColor = Color.Transparent
                    )
                }

                // Quick Navigation & Ad-Shield Floating Toolbar at bottom
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                    shadowElevation = 8.dp,
                    border = BorderStroke(1.dp, VedicGold.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Ad-Free Shield Indicator
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF1B5E20).copy(alpha = 0.85f),
                            border = BorderStroke(0.5.dp, Color(0xFF81C784).copy(alpha = 0.6f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Shield,
                                    contentDescription = "Ad Blocker Active",
                                    tint = Color(0xFF81C784),
                                    modifier = Modifier.size(13.dp)
                                )
                                Text(
                                    text = when (language) {
                                        "mr" -> "जाहिरातमुक्त"
                                        "hi" -> "विज्ञापन-मुक्त"
                                        else -> "Ad-Free"
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        // Channel Home Reset button
                        IconButton(
                            onClick = { webViewRef?.loadUrl(channelUrl) },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Channel Home",
                                tint = KesariyaSaffron,
                                modifier = Modifier.size(19.dp)
                            )
                        }

                        // Refresh button
                        IconButton(
                            onClick = { webViewRef?.reload() },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reload",
                                tint = VedicGoldDark,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
