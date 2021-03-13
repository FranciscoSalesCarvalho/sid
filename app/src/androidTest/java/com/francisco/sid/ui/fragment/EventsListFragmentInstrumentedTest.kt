package com.francisco.sid.ui.fragment

import android.content.Intent
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.rule.ActivityTestRule
import com.francisco.sid.R
import com.francisco.sid.util.testing.idle.EspressoIdlingResource
import com.francisco.sid.ui.activity.MainActivity
import com.francisco.sid.ui.adapter.EventsAdapter
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class EventsListFragmentInstrumentedTest {

    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.mCountingIdlingResource)
        mActivityRule.launchActivity(Intent())
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.mCountingIdlingResource)
    }

    @Test
    fun shouldListBeVisible() {
        onView(withId(R.id.eventsRecyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldStartNextFragmentWhenListItemIsClicked() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.nav_graph_main)
        }

        val scenario = launchFragmentInContainer<EventsListFragment>(themeResId = R.style.Theme_Sid)

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.eventsRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<EventsAdapter.EventViewHolder>(
                0,
                click()
            ))

        assertThat(
            navController.currentDestination?.id,
            `is`(CoreMatchers.equalTo(R.id.eventDetailsFragment))
        )
    }
}