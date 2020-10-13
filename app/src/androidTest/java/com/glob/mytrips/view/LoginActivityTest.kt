package com.glob.mytrips.view

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.glob.mytrips.MainActivity
import com.glob.mytrips.R
import com.glob.mytrips.contracts.LoginContract
import com.glob.mytrips.presenters.LoginPresenter
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit.rule


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<LoginActivity> =
        ActivityTestRule(LoginActivity::class.java)

    @get:Rule
    var secondTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    private val presenter: LoginContract.Presenter by lazy {
        LoginPresenter(activityTestRule.activity)
    }

    @Test
    fun validateCredentials() {

        onView(withId(R.id.username)).perform()
        onView(withId(R.id.password))
        onView(withId(R.id.login))
    }

    @Test
    fun validateNeedPassword() {
        onView(withId(R.id.username)).perform(typeText("admin"))
        onView(withId(R.id.login)).check(matches(not(isEnabled())))
    }

    @Test
    fun launchMainActivity() {
        onView(withId(R.id.username)).perform(typeText("JOEL"))
        onView(withId(R.id.password)).perform(typeText("Admin"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        val intent = Intent()
        secondTestRule.launchActivity(intent)
        onView(withId(R.id.activityMainUser)).check(matches(isDisplayed()))
    }

    @Test
    fun demonstrateIntentPrep() {
        val intent = Intent()
        intent.putExtra("EXTRA", "Test")
        secondTestRule.launchActivity(intent)
        onView(withId(R.id.activityMainUser)).check(matches(isDisplayed()))
    }
}