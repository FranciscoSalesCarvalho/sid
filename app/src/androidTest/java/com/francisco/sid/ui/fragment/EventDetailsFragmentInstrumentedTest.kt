package com.francisco.sid.ui.fragment

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.francisco.sid.R
import com.francisco.sid.data.model.Event
import com.francisco.sid.ui.activity.MainActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.After
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

    @Test
    fun shouldShare() {
        Intents.init()

        onView(withId(R.id.share))
            .perform(click())

        val expectedIntent = allOf(
            hasAction(Intent.ACTION_SEND),
            hasType("text/plain")
        )
        intended(chooser(expectedIntent))

        Intents.release()
    }

    private fun chooser(matcher: Matcher<Intent>): Matcher<Intent> {
        return allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(`is`(Intent.EXTRA_INTENT), matcher))
    }

    companion object {
        private fun default(): Event = Event(
            "1",
            "Event Default",
            "This is a event default used for tests",
            "http",
            1L,
            1.0,
            1.0,
            1.1
        )
    }
}