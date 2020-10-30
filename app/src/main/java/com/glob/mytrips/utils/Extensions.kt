package com.glob.mytrips.utils

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

fun AppCompatActivity.launchActivity(toActivity: Class<out AppCompatActivity>, requestCode: Int? = null, extras: Bundle = Bundle()) {
    val intent = Intent(this, toActivity)
    intent.putExtras(extras)

    if (requestCode == null)
        startActivity(intent)
    else
        startActivityForResult(intent, requestCode)
}

fun ImageView.loadUrl(url: String) {
    //.build().load(photos[position].url).into(holder.photo)
    Picasso.Builder(context).build().load(url)
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