package com.glob.mytrips.view

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.glob.mytrips.R
import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.dtos.PlaceDto
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    private val comeFrom = "Test Zone, MX"
    private val place = PlaceDto(
        1, "Test A", listOf(PhotoDto("none")),
        "Testing for validation", 2.2, false
    )
    private val intent = Intent().apply {
        putExtra("PLACE", place) // TODO: 19/10/2020 PlaceModel y parcelable
        putExtra("FROM", comeFrom)
    }

    @get:Rule
    private var placeDetail: ActivityTestRule<DetailActivity> =
        ActivityTestRule(DetailActivity::class.java, true, false)

    @Before
    fun setup() {

//        context.startActivity()
//        Intent(this, DetailActivity::class.java).apply {
//            putExtra("PLACE", (place as PlaceDto))
//            putExtra("FROM", from)
//        })
    }

    @Test
    fun validate_value_comefrom() {

        placeDetail.launchActivity(intent)
        onView(withId(R.id.comeFromDetail)).check(matches(withText(comeFrom)))
    }

    @Test
    fun validate_name_place_detail() {
        placeDetail.launchActivity(intent)
        onView(withId(R.id.namePlaceDetail)).check(matches(withText(place.name)))
    }

}