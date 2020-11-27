package com.glob.mytrips.view

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.glob.mytrips.R
import com.glob.mytrips.app.DataInfoUser
import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    private val COUNTRY_POS = 0
    private val STATE_POS = 0
    private val PLACE_POS = 0

    private val place = MockPlacesHierarchy.userModel.generalPlaces[COUNTRY_POS].states[STATE_POS].places[PLACE_POS]

    val comeFrom= "${MockPlacesHierarchy.userModel.generalPlaces[COUNTRY_POS].states[STATE_POS].name}," +
            " ${MockPlacesHierarchy.userModel.generalPlaces[COUNTRY_POS].name}"

    @get:Rule
    private var placeDetail: ActivityTestRule<DetailActivity> =
        object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.POSITION, PLACE_POS)
                    putExtra(DetailActivity.COME_FROM, comeFrom)
                }
            }
        }

    @Before
    fun setup() {
        val singleton = DataInfoUser.getInstance()
        singleton.userInfo = MockPlacesHierarchy.userModel
        singleton.countryPosAt = 0
        singleton.statePosAt = 0
        placeDetail.launchActivity(null)
    }

    @Test
    fun validate_value_comefrom() {
        onView(withId(R.id.comeFromDetail)).check(matches(withText(comeFrom)))
    }

    @Test
    fun validate_name_place_detail() {
        onView(withId(R.id.namePlaceDetail)).check(matches(withText(place.name)))
    }
}