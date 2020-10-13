package com.glob.mytrips.presenters

import android.util.Patterns
import android.view.View
import com.glob.mytrips.R
import com.glob.mytrips.contracts.LoginContract
import com.glob.mytrips.view.data.Result
import com.glob.mytrips.view.ui.login.LoggedInUserView
import com.glob.mytrips.view.ui.login.LoginResult

class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {

    override fun login(username: String, password: String): Boolean {
        //val result = loginRepository.login(username, password)
        // TODO: 09/10/2020 a mock response ok
        val result = Result.Success("OK")
        //

//        if (result is Result.Success) {
//            //_loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
//            view.validUser(LoginResult(success = LoggedInUserView(displayName = result.data)))
//        } else {
//            //_loginResult.value = LoginResult(error = R.string.login_failed)
//            view
//        }

        return if (!isUserNameValid(username)) {
            //view.validUser(LoginResult(success = LoggedInUserView(displayName = result.data)))
            false
        } else if (!isPasswordValid(password)) {
            //view.validUser(LoginResult(error = R.string.invalid_password))
            false
        } else {
            true
        }
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

    //        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
//            val loginState = it ?: return@Observer
//
//            // disable login button unless both username / password is valid
//            login.isEnabled = loginState.isDataValid
//
//            if (loginState.usernameError != null) {
//                username.error = getString(loginState.usernameError)
//            }
//            if (loginState.passwordError != null) {
//                password.error = getString(loginState.passwordError)
//            }
//        })

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            //view.validUser(LoginFormState(usernameError = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            //view.validUser(LoginFormState(passwordError = R.string.invalid_password))
        } else {
            //view.validUser(LoginFormState(isDataValid = true))
        }
    }

}