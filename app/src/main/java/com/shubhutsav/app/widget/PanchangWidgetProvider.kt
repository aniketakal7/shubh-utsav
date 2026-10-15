package com.shubhutsav.app.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.shubhutsav.app.MainActivity
import com.shubhutsav.app.R
import com.shubhutsav.app.data.PanchangCalculator
import com.shubhutsav.app.data.PreferencesManager
import java.util.Calendar

class PanchangWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (widgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, widgetId)
        }
    }

    companion object {
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, widgetId: Int) {
            try {
                val prefs = PreferencesManager(context)
                val city = prefs.getSelectedLocation()
                val lang = prefs.languageCode
                val calendar = Calendar.getInstance()

                val panchang = PanchangCalculator.calculateForDate(city = city, calendar = calendar)
                val choghadiyaSlots = PanchangCalculator.calculateChoghadiya(city = city, calendar = calendar)
                val activeSlot = choghadiyaSlots.find { it.isCurrent }

                val views = RemoteViews(context.packageName, R.layout.widget_panchang)

                // Launch MainActivity when tapped
                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                val pendingIntent = PendingIntent.getActivity(
                    context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

                // Fill Values
                val cityName = if (lang == "en") city.name else city.hindiName
                views.setTextViewText(R.id.widget_city, "📍 $cityName")

                val month = if (lang == "en") panchang.hinduMonthEn else panchang.hinduMonthHi
                val paksha = if (lang == "en") panchang.pakshaEn else panchang.pakshaHi
                val tithi = if (lang == "en") panchang.tithiEn else panchang.tithiHi
                views.setTextViewText(R.id.widget_tithi, "$month $paksha • $tithi")

                val nakshatra = if (lang == "en") panchang.nakshatraEn else panchang.nakshatraHi
                val date = if (lang == "en") panchang.gregorianDateEn else panchang.gregorianDateHi
                views.setTextViewText(R.id.widget_date_nakshatra, "$nakshatra • $date")

                views.setTextViewText(R.id.widget_sun_times, "☀️ ${panchang.sunrise}  •  🌙 ${panchang.sunset}")
                views.setTextViewText(R.id.widget_muhurat, "✨ अभिजित: ${panchang.abhijitMuhurat}")
                views.setTextViewText(R.id.widget_rahu_kaal, "राहुकाल: ${panchang.rahuKaal}")

                if (activeSlot != null) {
                    val slotName = if (lang == "en") activeSlot.type.nameEn else activeSlot.type.nameHi
                    views.setTextViewText(R.id.widget_choghadiya, "चौघड़िया: $slotName")
                }

                appWidgetManager.updateAppWidget(widgetId, views)
            } catch (_: Exception) {}
        }
    }
}
