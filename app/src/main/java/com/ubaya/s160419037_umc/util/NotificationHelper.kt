package com.ubaya.s160419037_umc.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.view.MainActivity
import java.lang.Exception

class NotificationHelper (val context: Context) {
    private val CHANNEL_ID = "todo_channel_id"
    private val NOTIFICATION_ID = 1

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_ID,
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "Notification channel description"
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(title: String, message: String, long_message: String){
        createNotificationChannel()

        // untuk pending intent
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        // Membangun Notifikasi
        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.logoinlogin)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logoinlogin)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(icon)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(long_message)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
    }
}