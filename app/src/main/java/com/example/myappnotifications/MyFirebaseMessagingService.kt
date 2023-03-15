package com.example.myappnotifications

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myappnotifications.MyAppNotification.Companion.CHANNEL_ID
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService()  {
    companion object{
         val NAME: String = MyFirebaseMessagingService::class.java.name
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val strMap : MutableMap<String, String> = message.data
        val title = strMap["title"]
        val message2 = strMap["message"]
        sendNotification(title,message2)
    }
    private fun sendNotification(title: String?, strmessage: String?) {
        val i = Intent(this,MainActivity::class.java)
        val pending = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_CANCEL_CURRENT)
        val notifi : NotificationCompat.Builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle(title).setContentText(strmessage).setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pending)
        val notification = notifi.build()
        val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1,notification)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("dasdasd", "onNewToken: $token", )
    }
}