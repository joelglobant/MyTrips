package com.glob.mytrips.servicesfortest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glob.mytrips.R
import kotlinx.android.synthetic.main.activity_foreground.*

class ForegroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground)
        setupButtons()
    }

    private fun setupButtons(){
        btnStart.setOnClickListener {
            startService(Intent(it.context, ForegroundService::class.java))
        }

        btnStop.setOnClickListener {
            stopService(Intent(it.context, ForegroundService::class.java))
        }
    }

}