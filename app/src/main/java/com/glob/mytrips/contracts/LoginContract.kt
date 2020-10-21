package com.glob.mytrips.contracts

interface LoginContract {

    interface View {
        fun validUser()
    }

    interface Presenter {
        fun login(username: String, password: String): Boolean
        fun isUserNameValid(username: String): Boolean
        fun isPasswordValid(password: String): Boolean
    }

}