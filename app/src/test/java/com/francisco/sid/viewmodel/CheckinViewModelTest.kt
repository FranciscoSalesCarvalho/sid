package com.francisco.sid.viewmodel

import com.francisco.sid.base.BaseViewModelTest
import com.francisco.sid.data.model.Checkin
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CheckinViewModelTest: BaseViewModelTest() {

    private lateinit var viewModel: CheckinViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CheckinViewModel(repository)
    }

    @Test
    fun shouldMakeRequest() = runBlocking {
        val id = "1"
        val info = Checkin(id, "Francisco Sales", "s@s.com")
        viewModel.nameLiveEvent.postValue(info.name)
        viewModel.emailLiveEvent.postValue(info.email)

        every { runBlocking { repository.checkin(info) }  } returns Any()

        viewModel.checkin(id)

        verify { runBlocking { repository.checkin(info) } }
    }
}