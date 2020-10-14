package com.glob.mytrips.view

import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.glob.mytrips.MainActivity
import com.glob.mytrips.R
import com.glob.mytrips.adapters.CountryAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    var mainActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun validate_DrawerLayout_Is_Displayed() {
        onView(withId(R.id.drawerLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun validate_RecyclerView_Is_Visible() {
        onView(withId(R.id.rvMyPlaces)).check(matches(isDisplayed()))
    }

    @Test
    fun validate_User_Info_Is_Displayed_On_DraweLayout() {
        Thread.sleep(3000)
        onView(withId(R.id.drawerLayout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.drawerLayout)).perform(click())
        onView(withId(R.id.tvProfileName)).check(matches(isDisplayed()))
    }

    @Test
    fun validate_RecyclerView_Has_Elements() {
        Thread.sleep(5000)//wait time to get a response from the API
        onView(withId(R.id.rvMyPlaces)).perform(
            actionOnItem<CountryAdapter.CountryViewHolder>(
                hasDescendant(withText("Mexico")),
                click()
            )
        )
    }


}