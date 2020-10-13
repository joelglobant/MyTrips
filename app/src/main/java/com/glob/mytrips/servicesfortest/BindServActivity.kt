package com.glob.mytrips.servicesfortest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.glob.mytrips.R
import kotlinx.android.synthetic.main.activity_bind_serv.*

class BindServActivity : AppCompatActivity() {

    private final val sr = "yolo"



    private val TAG = BindServActivity::class.java.simpleName
    lateinit var mBoundService: BoundService
    var mServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bind_serv)

        printTimestamp.setOnClickListener {
            if (mServiceBound)
                timestampText.text = mBoundService.timeStamp
            else
                timestampText.text = "Without Connection"
        }
        bindService.setOnClickListener {
            Log.i(TAG, "onCreate: hello")
            //val intent = Intent(applicationContext, BoundService::class.java)
            val intent = Intent(this, BoundService::class.java)
            //startService(intent)
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        }
        stopService.setOnClickListener {
            if (mServiceBound) {
                unbindService(mServiceConnection)
                mServiceBound = false
            }
            //val intent = Intent(this@BindServActivity, BoundService::class.java)
            //stopService(intent)
        }

        launchService.setOnClickListener {
            val intent = Intent(applicationContext, BoundService::class.java)
            startService(intent)
        }
        removeService.setOnClickListener {
            val intent = Intent(this@BindServActivity, BoundService::class.java)
            stopService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        if (mServiceBound) {
            unbindService(mServiceConnection)
            mServiceBound = false
        }
    }

    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mServiceBound = false
            Log.i(TAG, "onServiceDisconnected: $name")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder = service as BoundService.MyBinder
            mBoundService = myBinder.service
            mServiceBound = true
        }

    }
}