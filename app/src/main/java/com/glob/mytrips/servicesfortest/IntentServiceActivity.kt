package com.glob.mytrips.servicesfortest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.glob.mytrips.R
import kotlinx.android.synthetic.main.activity_intent_service.*

class IntentServiceActivity : AppCompatActivity() {

    private lateinit var myReceiver: MyReceiver

    companion object {
        const val FILTER_ACTION_KEY = "939"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)
        etInputIntService.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                btnIntentService.performClick()
                etInputIntService.setText("")
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        btnIntentService.setOnClickListener {
            val message = etInputIntService.text.toString()
            val intent = Intent(this@IntentServiceActivity, MyIntentService::class.java)
            intent.putExtra("message", message)
            startService(intent)
        }
    }

    private fun setReceiver() {
        myReceiver = MyReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(FILTER_ACTION_KEY)
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter)
    }

    override fun onStart() {
        setReceiver()
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(myReceiver)
        super.onStop()
    }

    inner class MyReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var message: String? = null
            intent?.let {
                message = it.getStringExtra("broadcastMessage")
            }
            tvIntentService.text = "${tvIntentService.text} \n $message"
        }
    }
}