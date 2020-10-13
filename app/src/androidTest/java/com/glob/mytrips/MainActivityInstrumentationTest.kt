package com.glob.mytrips

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)


    @Test
    fun validateEditText() {
        onView(withId(R.id.etInput)).perform(typeText("Hello"))
            .check(matches(withText("Hello")))
    }

    @Test
    fun validateTextView() {
        onView(withId(R.id.tvTest)).check(matches(isDisplayed()))
    }

    @Test
    fun validateHideTextView() {
        onView(withId(R.id.tvTest)).check(matches(isEnabled()))
        //onView(withText("wrong answer")).perform(click())
        onView(withId(R.id.tvTest)).perform(click())
        onView(withId(R.id.tvTest)).check(matches(not(isEnabled())))

    }

    @Test
    fun validateOnClickButton() {
        onView(withId(R.id.btnTest)).perform(longClick()).check(matches(isFocusable()))
    }
}