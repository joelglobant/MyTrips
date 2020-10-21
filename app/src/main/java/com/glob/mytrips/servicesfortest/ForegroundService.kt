package com.glob.mytrips.servicesfortest

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.glob.mytrips.App.Companion.CHANNEL_ID
import com.glob.mytrips.R

class ForegroundService : Service() {

    lateinit var myPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        myPlayer = MediaPlayer.create(this,
            R.raw.the_wisp_sings
        )
        myPlayer.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        myPlayer.start()

        val notificationIntent = Intent(this, ForegroundActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            notificationIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        myPlayer.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}