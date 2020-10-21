package com.glob.mytrips.presenters

import android.util.Patterns
import com.glob.mytrips.contracts.LoginContract

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(username: String, password: String): Boolean {
        return if (!isUserNameValid(username))
            false
        else isPasswordValid(password)
    }

    override fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}