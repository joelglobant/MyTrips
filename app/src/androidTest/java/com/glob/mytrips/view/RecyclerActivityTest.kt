package com.glob.mytrips.view

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.glob.mytrips.R
import com.glob.mytrips.adapters.BasicAdapterPlace
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class RecyclerActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(RecyclerActivity::class.java)

    @Test
    fun testIsRecyclerVisible() {
        onView(withId(R.id.recyclerView4Test)).check(matches(isDisplayed()))
    }

    @Test
    fun validate_text_at_a_position() {
        onView(withId(R.id.recyclerView4Test))
            .perform(actionOnItem<BasicAdapterPlace.FillItem>(hasDescendant(withText("Jerez")), click()))
    }
    @Test
    fun validateOnData() {
        onData(withId(R.id.recyclerView4Test)).inAdapterView(`is`(instanceOf(BasicAdapterPlace::class.java)))
    }
}