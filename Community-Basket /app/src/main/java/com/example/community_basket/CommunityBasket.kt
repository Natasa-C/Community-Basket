package com.example.community_basket

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class CommunityBasket : Application() {
    val CHANNEL_ID : String = "MARKET-CHANNEL"

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var market_channel : NotificationChannel = NotificationChannel(CHANNEL_ID, "market channel", NotificationManager.IMPORTANCE_HIGH)
            market_channel.setDescription("Market channel")
            market_channel.enableLights(true)

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(market_channel)
        }
    }
}