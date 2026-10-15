package com.shubhutsav.app.data

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import kotlinx.coroutines.*
import kotlin.math.*

/**
 * SacredSoundPlayer provides authentic, lightweight audio generation and haptics
 * for Temple Bell (घंटी), Sacred Conch (शंख), Japa Mala bead clicks,
 * and ambient meditative Tanpura drone.
 * 100% offline, zero external audio asset dependencies!
 */
object SacredSoundPlayer {

    private var toneGen: ToneGenerator? = null
    private var tanpuraTrack: AudioTrack? = null
    private var isTanpuraPlaying = false
    private var tanpuraJob: Job? = null

    init {
        try {
            toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 85)
        } catch (_: Exception) {}
    }

    /**
     * Subtle tactile haptic vibration feedback
     */
    fun performHaptic(context: Context, isStrong: Boolean = false) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                val vibrator = vibratorManager?.defaultVibrator
                val effect = if (isStrong) {
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
                } else {
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
                }
                vibrator?.vibrate(effect)
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                if (isStrong) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator?.vibrate(VibrationEffect.createOneShot(25, 120))
                }
            }
        } catch (_: Exception) {}
    }

    /**
     * Plays gentle Rudraksha bead click chime for Japa Mala
     */
    fun playBeadClick(context: Context) {
        performHaptic(context, isStrong = false)
        CoroutineScope(Dispatchers.Default).launch {
            try {
                // High frequency gentle chime
                playTonePcm(frequency = 1860.0, durationMs = 60, amplitude = 0.35, decayFactor = 18.0)
            } catch (_: Exception) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_BEEP, 30)
            }
        }
    }

    /**
     * Plays celebration chime on completing 108 beads (1 Mala)
     */
    fun playMalaComplete(context: Context) {
        performHaptic(context, isStrong = true)
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val chords = listOf(1046.5, 1318.5, 1567.98, 2093.0) // C6, E6, G6, C7
                chords.forEach { freq ->
                    playTonePcm(frequency = freq, durationMs = 180, amplitude = 0.5, decayFactor = 8.0)
                    delay(70)
                }
            } catch (_: Exception) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_ACK, 200)
            }
        }
    }

    /**
     * 🔔 Resonant Temple Bell (मंदिर की घंटी)
     * Rich harmonic synthesis of bronze bell acoustic resonance
     */
    fun playTempleBell(context: Context) {
        performHaptic(context, isStrong = true)
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val sampleRate = 44100
                val durationMs = 1600
                val numSamples = (durationMs * sampleRate) / 1000
                val buffer = ShortArray(numSamples)

                // Bronze bell frequencies: Fundamental + Third + Fifth harmonics
                val f0 = 1200.0
                val f1 = 2400.0
                val f2 = 3600.0
                val f3 = 4800.0

                for (i in 0 until numSamples) {
                    val t = i.toDouble() / sampleRate
                    // Exponential acoustic ring-down decay
                    val decay = exp(-3.2 * t)
                    val sample = decay * (
                        0.55 * sin(2 * Math.PI * f0 * t) +
                        0.30 * sin(2 * Math.PI * f1 * t) +
                        0.15 * sin(2 * Math.PI * f2 * t) +
                        0.08 * sin(2 * Math.PI * f3 * t)
                    )
                    buffer[i] = (sample * Short.MAX_VALUE * 0.75).toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
                }

                playBuffer(buffer, sampleRate)
            } catch (_: Exception) {
                toneGen?.startTone(ToneGenerator.TONE_CDMA_HIGH_L, 400)
            }
        }
    }

    /**
     * 🐚 Sacred Conch (शंख नाद)
     * Resonant deep meditative vibration simulating blowing a Shankh
     */
    fun playShankhNaad(context: Context) {
        performHaptic(context, isStrong = true)
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val sampleRate = 44100
                val durationMs = 2800
                val numSamples = (durationMs * sampleRate) / 1000
                val buffer = ShortArray(numSamples)

                val baseFreq = 432.0 // Sacred 432 Hz healing pitch

                for (i in 0 until numSamples) {
                    val t = i.toDouble() / sampleRate
                    // Attack (0 to 0.4s) -> Sustain -> Slow breath release
                    val envelope = when {
                        t < 0.4 -> (t / 0.4)
                        t < 2.0 -> 1.0
                        else -> max(0.0, 1.0 - (t - 2.0) / 0.8)
                    }
                    // Gentle breath flutter vibrato
                    val vibrato = 1.0 + 0.015 * sin(2 * Math.PI * 5.5 * t)
                    val freq = baseFreq * vibrato

                    val sample = envelope * (
                        0.60 * sin(2 * Math.PI * freq * t) +
                        0.25 * sin(2 * Math.PI * (freq * 2.0) * t) +
                        0.15 * sin(2 * Math.PI * (freq * 3.0) * t)
                    )
                    buffer[i] = (sample * Short.MAX_VALUE * 0.7).toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
                }

                playBuffer(buffer, sampleRate)
            } catch (_: Exception) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_PROMPT, 1000)
            }
        }
    }

    /**
     * Starts / Stops Meditative Tanpura drone background
     */
    fun toggleTanpura(onStateChange: (Boolean) -> Unit) {
        if (isTanpuraPlaying) {
            stopTanpura()
            onStateChange(false)
        } else {
            startTanpura()
            onStateChange(true)
        }
    }

    fun isTanpuraActive(): Boolean = isTanpuraPlaying

    fun startTanpura() {
        if (isTanpuraPlaying) return
        isTanpuraPlaying = true

        tanpuraJob = CoroutineScope(Dispatchers.Default).launch {
            val sampleRate = 44100
            val loopDurationMs = 4000
            val numSamples = (loopDurationMs * sampleRate) / 1000
            val buffer = ShortArray(numSamples)

            // Sa (C# / 277.18 Hz) and Pa (G# / 415.30 Hz) harmonic drone
            val fSa = 277.18
            val fPa = 415.30
            val fSaLow = 138.59

            for (i in 0 until numSamples) {
                val t = i.toDouble() / sampleRate
                val drone = (
                    0.45 * sin(2 * Math.PI * fSa * t) +
                    0.30 * sin(2 * Math.PI * fPa * t) +
                    0.25 * sin(2 * Math.PI * fSaLow * t) +
                    0.05 * sin(2 * Math.PI * (fSa * 2) * t)
                )
                buffer[i] = (drone * Short.MAX_VALUE * 0.35).toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            try {
                val minSize = AudioTrack.getMinBufferSize(
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT
                )
                tanpuraTrack = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(max(minSize, buffer.size * 2))
                    .setTransferMode(AudioTrack.MODE_STREAM)
                    .build()

                tanpuraTrack?.play()

                while (isActive && isTanpuraPlaying) {
                    tanpuraTrack?.write(buffer, 0, buffer.size)
                }
            } catch (_: Exception) {
                isTanpuraPlaying = false
            }
        }
    }

    fun stopTanpura() {
        isTanpuraPlaying = false
        tanpuraJob?.cancel()
        tanpuraJob = null
        try {
            tanpuraTrack?.stop()
            tanpuraTrack?.release()
        } catch (_: Exception) {}
        tanpuraTrack = null
    }

    private fun playTonePcm(frequency: Double, durationMs: Int, amplitude: Double, decayFactor: Double) {
        val sampleRate = 44100
        val numSamples = (durationMs * sampleRate) / 1000
        val buffer = ShortArray(numSamples)

        for (i in 0 until numSamples) {
            val t = i.toDouble() / sampleRate
            val decay = exp(-decayFactor * t)
            val sample = decay * amplitude * sin(2 * Math.PI * frequency * t)
            buffer[i] = (sample * Short.MAX_VALUE).toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
        }
        playBuffer(buffer, sampleRate)
    }

    private fun playBuffer(buffer: ShortArray, sampleRate: Int) {
        try {
            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(buffer.size * 2)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            track.write(buffer, 0, buffer.size)
            track.play()
            CoroutineScope(Dispatchers.Default).launch {
                delay(3000)
                try {
                    track.stop()
                    track.release()
                } catch (_: Exception) {}
            }
        } catch (_: Exception) {}
    }
}
