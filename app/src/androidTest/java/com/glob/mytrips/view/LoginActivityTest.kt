package com.glob.mytrips.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.glob.mytrips.R
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<LoginActivity> =
        ActivityTestRule(LoginActivity::class.java)

    @Test
    fun validate_Login_Button_Is_Disabled_When_Not_Password_Is_Present() {
        onView(withId(R.id.username)).perform(typeText("admin"))
        onView(withId(R.id.login)).check(matches(not(isEnabled())))
    }

    @Test
    fun validate_Error_Message_With_A_Short_Password() {
        onView(withId(R.id.username)).perform(typeText("admin"))
        onView(withId(R.id.password)).perform(typeText("Ad"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        //val expectedNoStatisticsText: String = InstrumentationRegistry.getInstrumentation().context.resources.getString(R.string.invalid_password)
        onView(withId(R.id.password)).check(matches(hasErrorText("Password must be >5 characters")))
    }

    @Test
    fun validate_Errors_With_Wrong_Credentials() {
        onView(withId(R.id.username)).perform(typeText("admin@"))
        onView(withId(R.id.password)).perform(typeText("adm"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        onView(withId(R.id.username)).check(matches(hasErrorText("Not a valid username")))
        onView(withId(R.id.password)).check(matches(hasErrorText("Password must be >5 characters")))
    }

    @Test
    fun validate_Launch_MainActivity() {
        onView(withId(R.id.username)).perform(typeText("JOEL"))
        onView(withId(R.id.password)).perform(typeText("Adminx"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
    }
}