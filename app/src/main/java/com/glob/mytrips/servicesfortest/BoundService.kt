package com.glob.mytrips.servicesfortest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer

class BoundService : Service() {

    companion object {
        private val TAG = BoundService::class.java.simpleName
    }

    private val mBinder: IBinder = MyBinder()
    private lateinit var mChronometer: Chronometer

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: -created")
        mChronometer = Chronometer(this)
        mChronometer.base = SystemClock.elapsedRealtime()
        mChronometer.start()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.v(TAG, "in onBind")
        return mBinder
    }

    override fun onRebind(intent: Intent?) {
        Log.v(TAG, "in onRebind ")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "in onUnbind")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "in onDestroy")
        mChronometer.stop()
    }

    val timeStamp: String
        get() {
            val elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.base
            val hours = (elapsedMillis / 3600000).toInt()
            val minutes = ((elapsedMillis - hours * 3600000) / 60000).toInt()
            val seconds = ((elapsedMillis - hours * 3600000 - minutes * 60000) / 1000).toInt()
            val millis = (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000).toInt()
            return "$hours:$minutes:$seconds:$millis"
        }

    inner class MyBinder : Binder() {
        val service: BoundService
            get() = this@BoundService
    }

}