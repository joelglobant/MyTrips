package com.glob.mytrips.presenters

import android.util.Patterns
import com.glob.mytrips.contracts.LoginContract
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun login(username: String, password: String): Boolean {
        showLoader()
        Thread.sleep(400) // mock delay
        return if (!isUserNameValid(username)) {
            hideLoader()
            false
        } else {
            hideLoader()
            isPasswordValid(password)
        }
    }

    private fun showLoader() {
        view.showLoader(true)
    }

    private fun hideLoader() {
        view.showLoader(false)
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

    override fun onDestroy() {
        disposable.dispose()
    }
}