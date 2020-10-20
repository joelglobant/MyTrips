package com.glob.mytrips.app

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.glob.mytrips.R

abstract class BaseActivity: AppCompatActivity() {

    override fun onBackPressed() {
        val manager: FragmentManager = supportFragmentManager
        val fragments= manager.fragments
        if (fragments.isNotEmpty() && fragments.size > 1 ) {
            //manager.beginTransaction().show(frag).commit()
            manager.beginTransaction().remove(manager.fragments.last()).commit()
            manager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    protected fun addFragmentView(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            if (!supportFragmentManager.fragments.contains(fragment))
                add(R.id.fragmentContainer, fragment, fragment::class.java.simpleName)
            else
                show(fragment)
        }
        transaction.commitNow()
    }

    protected fun sendMessage(msg: String) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}