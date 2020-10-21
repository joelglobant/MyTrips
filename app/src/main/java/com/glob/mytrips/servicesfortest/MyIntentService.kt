package com.glob.mytrips.servicesfortest

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        private val TAG = MyIntentService::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {
        var message: String? = null
        intent?.let {
            message = it.getStringExtra("message")
        }
        intent?.action =
            IntentServiceActivity.FILTER_ACTION_KEY

        SystemClock.sleep(5000)
        val resultMessage = "La cadena resultante despues de 6 segundos de proceso.. $message"
        intent?.let {
            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(it.putExtra("broadcastMessage", resultMessage))
        }
        Log.i(TAG, "in onHandleIntent Service")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Toast.makeText(applicationContext, "Intent Service Created!", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "in onStart Service")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Intent Service Destroyed!", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "in onDestroy Service")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
        Log.i(TAG, "in onBind Service")
    }

}