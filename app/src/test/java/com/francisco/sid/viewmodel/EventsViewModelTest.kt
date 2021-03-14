package com.francisco.sid.viewmodel

import com.francisco.sid.base.BaseViewModelTest
import com.francisco.sid.factory.EventFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EventsViewModelTest: BaseViewModelTest() {

    private lateinit var viewModel: EventsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = EventsViewModel(repository)
    }

    @Test
    fun shouldMakeRequest() = runBlocking {
        val default = EventFactory.default()
        every { runBlocking { repository.fetchEvents() }  } returns listOf(default)

        val result = runBlocking { repository.fetchEvents() }

        viewModel.fetchEvents()

        verify { runBlocking { repository.fetchEvents() } }
        assertEquals(1, result.size)
        assertEquals(default, result[0])
    }
}