package com.glob.mytrips.contracts

import com.glob.mytrips.view.ui.login.LoginResult

interface LoginContract {

    interface View {
        fun validUser(loginResult: LoginResult)
    }

    interface Presenter {
        fun login(username: String, password: String): Boolean
        fun isUserNameValid(username: String): Boolean
        fun isPasswordValid(password: String): Boolean

    }


//        fun loginDataChanged(username: String, password: String) {
//            if (!isUserNameValid(username)) {
//                _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
//            } else if (!isPasswordValid(password)) {
//                _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
//            } else {
//                _loginForm.value = LoginFormState(isDataValid = true)
//            }
//        }

        // A placeholder username validation check
//        private fun isUserNameValid(username: String): Boolean {
//            return if (username.contains('@')) {
//                Patterns.EMAIL_ADDRESS.matcher(username).matches()
//            } else {
//                username.isNotBlank()
//            }
//        }
//
//        // A placeholder password validation check
//        private fun isPasswordValid(password: String): Boolean {
//            return password.length > 5
//    }

}