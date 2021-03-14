package com.francisco.sid.ui.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.francisco.sid.core.GSTestRunner
import com.francisco.sid.R
import com.francisco.sid.base.TestSidApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(GSTestRunner::class)
@Config(application = TestSidApplication::class)
class EventsListFragmentTest {

    private lateinit var scenario: FragmentScenario<EventsListFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun shouldNotBeNull() {
        scenario.onFragment { fragment ->
            Assert.assertNotNull(fragment)
        }
    }

    @Test
    fun shouldFindListView() {
        scenario.recreate()
        scenario.onFragment { fragment ->
            val listView = fragment.requireView().findViewById<RecyclerView>(R.id.eventsRecyclerView)
            listView.findViewHolderForAdapterPosition(0)
                ?.itemView
                ?.performClick()
        }
    }
}