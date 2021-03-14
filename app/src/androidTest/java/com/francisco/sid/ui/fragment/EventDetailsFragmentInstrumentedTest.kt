package com.francisco.sid.ui.fragment

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.francisco.sid.R
import com.francisco.sid.data.model.Event
import com.francisco.sid.ui.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class EventDetailsFragmentInstrumentedTest {

    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)


    @Before
    fun setup() {
        val arguments = bundleOf("event" to default())
        val launchFragmentIntent: Intent = NavDeepLinkBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext
        ).setGraph(R.navigation.nav_graph_main)
            .setComponentName(MainActivity::class.java)
            .setDestination(R.id.eventDetailsFragment)
            .setArguments(arguments)
            .createTaskStackBuilder().intents[0]

        mActivityRule.launchActivity(launchFragmentIntent)
    }

    @Test
    fun shouldGoToCheckinForm() {
        onView(withId(R.id.btnGoCheckin))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.btnCheckin))
            .check(matches(isDisplayed()))
    }

    companion object {
        fun default(): Event = Event(
            "1",
            "Event Default",
            "This is a event default used for tests",
            "",
            1L,
            1.0,
            1.0,
            1.1
        )
    }
}