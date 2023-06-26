package org.bedu.v2_tiendabedu.utilitis

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import org.bedu.v2_tiendabedu.MenuActivity
import org.bedu.v2_tiendabedu.R

const val NOTIFICATION_COMPRA_ID = 10

/*@SuppressLint("MissingPermission")
fun carritoPendienteNotification(context: Context){
    with(context){
        val builder = NotificationCompat.Builder(this,NotificationApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_24)
            .setLargeIcon(ContextCompat.getDrawable(context, R.drawable.logo_256)?.toBitmap())
            .setColor(getColor(android.R.color.holo_red_dark))
            .setContentTitle(getString(R.string.compraNotificationTitle))
            .setContentText(getString(R.string.compraNotificationtext))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(this).run {
            notify(NOTIFICATION_COMPRA_ID, builder.build())
        }
    }
}*/

@SuppressLint("MissingPermission")
fun carritoPendienteNotification(activity: Activity, context: Context){
    with(activity){
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("fragmentName", "CarritoFragment")
        val pendingIntent = PendingIntent.getActivity(
            activity,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE)


        val builder = NotificationCompat.Builder(context,NotificationApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_24)
            .setLargeIcon(ContextCompat.getDrawable(activity, R.drawable.logo_256)?.toBitmap())
            .setColor(getColor(android.R.color.holo_red_dark))
            .setContentTitle(getString(R.string.compraNotificationTitle))
            .setContentText(getString(R.string.compraNotificationtext))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).run {
            notify(NOTIFICATION_COMPRA_ID, builder.build())
        }
    }
}

/*@SuppressLint("MissingPermission")
fun otherLayoutNotification(activity: Activity, context: Context){
    val contentView = RemoteViews("org.bedu.v2_tiendabedu")
    with(context){
        val builder = NotificationCompat.Builder(context,NotificationApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_24)
            .setLargeIcon(ContextCompat.getDrawable(activity, R.drawable.logo_256)?.toBitmap())
            .setColor(getColor(android.R.color.holo_red_dark))
            .setContentTitle(getString(R.string.compraNotificationTitle))
            .setContentText(getString(R.string.compraNotificationtext))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
    }
}*/


