package com.francisco.sid.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.francisco.sid.core.MainCoroutineRule
import com.francisco.sid.data.repository.EventsRepository
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    protected val repository: EventsRepository = mockk()
}