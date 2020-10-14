package com.glob.mytrips.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import com.glob.mytrips.MainActivity
import com.glob.mytrips.R
import com.glob.mytrips.app.BaseActivity
import com.glob.mytrips.app.afterTextChanged
import com.glob.mytrips.contracts.LoginContract
import com.glob.mytrips.registers.LoginRegistry
import com.glob.mytrips.view.ui.login.LoggedInUserView
import com.glob.mytrips.view.ui.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    //private lateinit var loginViewModel: LoginViewModel
    private val presenter: LoginContract.Presenter by lazy {
        LoginRegistry().provide(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loading = findViewById<ProgressBar>(R.id.loading)


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

//        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
//            val loginResult = it ?: return@Observer
//
//            loading.visibility = View.GONE
//            if (loginResult.error != null) {
//                showLoginFailed(loginResult.error)
//            }
//            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
//            }
//            setResult(Activity.RESULT_OK)
//            startActivity(Intent(this, MainActivity::class.java))
//
//            //Complete and destroy login activity once successful
//            finish()
//        })


        username.apply {
            afterTextChanged {
                presenter.isUserNameValid(it)
                login.isEnabled = it.isNotEmpty() && password.text.toString().isNotEmpty()
//            loginViewModel.loginDataChanged(
//                username.text.toString(),
//                password.text.toString()
//            )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_NEXT ->
                        presenter.isUserNameValid(username.text.toString())
                    EditorInfo.IME_ACTION_DONE ->
                        login.isEnabled = presenter.login(username.text.toString(), password.text.toString())
//                        loginViewModel.login(
//                            username.text.toString(),
//                            password.text.toString()
//                        )
                }
                false
            }
        }

        password.apply {
            afterTextChanged {
                login.isEnabled = it.isNotEmpty() && username.text.isNotEmpty()
                presenter.isPasswordValid(it)
//                loginViewModel.loginDataChanged(
//                    username.text.toString(),
//                    password.text.toString()
//                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_NEXT ->
                        presenter.isPasswordValid(password.text.toString())
                    EditorInfo.IME_ACTION_DONE ->
                        login.isEnabled = presenter.login(username.text.toString(), password.text.toString())
//                        loginViewModel.login(
//                            username.text.toString(),
//                            password.text.toString()
//                        )
                }
                false
            }
            login.performClick()
        }

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            if(presenter.login(username.text.toString(), password.text.toString())) {
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

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun validUser(loginResult: LoginResult) {
        loading.visibility = View.GONE
        if (loginResult.error != null) {
            showLoginFailed(loginResult.error)
        }
        if (loginResult.success != null) {
            updateUiWithUser(loginResult.success)
        }
        //setResult(Activity.RESULT_OK)
        if (presenter.login(username.text.toString(), password.text.toString())) {
            startActivity(Intent(this, MainActivity::class.java))
            //Complete and destroy login activity once successful
            finish()
        }
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}