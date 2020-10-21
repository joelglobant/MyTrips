package com.glob.mytrips.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import com.glob.mytrips.MainActivity
import com.glob.mytrips.R
import com.glob.mytrips.app.BaseActivity
import com.glob.mytrips.app.afterTextChanged
import com.glob.mytrips.contracts.LoginContract
import com.glob.mytrips.registers.LoginRegistry
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter by lazy {
        LoginRegistry().provide(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        username.apply {
            afterTextChanged {
                presenter.isUserNameValid(it)
                login.isEnabled = it.isNotEmpty() && password.text.toString().isNotEmpty()
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_NEXT ->
                        presenter.isUserNameValid(username.text.toString())
                    EditorInfo.IME_ACTION_DONE ->
                        login.isEnabled =
                            presenter.login(username.text.toString(), password.text.toString())
                }
                false
            }
        }

        password.apply {
            afterTextChanged {
                login.isEnabled = it.isNotEmpty() && username.text.isNotEmpty()
                presenter.isPasswordValid(it)
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_NEXT ->
                        presenter.isPasswordValid(password.text.toString())
                    EditorInfo.IME_ACTION_DONE ->
                        login.isEnabled =
                            presenter.login(username.text.toString(), password.text.toString())
                }
                false
            }
            login.performClick()
        }

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            if (presenter.login(username.text.toString(), password.text.toString())) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                loading.visibility = View.GONE
                if (!presenter.isUserNameValid(username.text.toString()))
                    username.error = resources.getString(R.string.invalid_username)
                if (!presenter.isPasswordValid(password.text.toString()))
                    password.error = resources.getString(R.string.invalid_password)
            }
        }
    }

    override fun validUser() {
        loading.visibility = View.GONE
        if (presenter.login(username.text.toString(), password.text.toString())) {
            startActivity(Intent(this, MainActivity::class.java))
            //Complete and destroy login activity once successful
            finish()
        }
    }
}