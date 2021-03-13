package com.francisco.sid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.francisco.sid.MainCoroutineRule
import com.francisco.sid.data.repository.EventsRepository
import com.francisco.sid.factory.EventFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventsViewModelTest {

    private lateinit var viewModel: EventsViewModel

    private val repository: EventsRepository = mockk()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = EventsViewModel(repository)
    }

    @Test
    fun shouldMakeRequest() = runBlocking<Unit> {
        val default = EventFactory.default()
        every { runBlocking { repository.fetchEvents() }  } returns listOf(default)

        val result = runBlocking { repository.fetchEvents() }

        viewModel.fetchEvents()

        verify { runBlocking { repository.fetchEvents() } }
        assertEquals(1, result.size)
        assertEquals(default, result[0])
    }
}