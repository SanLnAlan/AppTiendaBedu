package org.bedu.v2_tiendabedu.utilitis

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import org.bedu.v2_tiendabedu.R

class NotificationApp: Application() {

    companion object {
        const val CHANNEL_ID = "DEFAULT_CHANNEL"
        const val CHANNEL_OTHERS = "OTROS"


    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChanel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setNotificationChanel(){
        val notificationCompra = getString(R.string.notification_compra)
        val descriptionText = getString(R.string.description_text)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(CHANNEL_ID, notificationCompra, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}