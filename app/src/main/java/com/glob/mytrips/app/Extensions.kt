package com.glob.mytrips.app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.launchActivity(toActivity: Class<out AppCompatActivity>, requestCode: Int? = null, extras: Bundle = Bundle()) {
    val intent = Intent(this, toActivity)
    intent.putExtras(extras)

    if (requestCode == null)
        startActivity(intent)
    else
        startActivityForResult(intent, requestCode)
}

fun launchLoginCodiActivity() {
//    val intentLoginCodi = Intent(this, LoginCodiActivity::class.java)
//    startActivityForResult(intentLoginCodi, LoginCodiActivity.LOGIN_CODI_REQUEST)
//    overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out)
}



/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}