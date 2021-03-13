package com.francisco.sid.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.francisco.sid.data.model.Checkin
import com.francisco.sid.data.model.Event
import com.francisco.sid.data.repository.EventsRepository
import com.francisco.sid.util.Constants
import com.francisco.sid.util.Validators
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckinViewModel @Inject constructor(
    private val repository: EventsRepository
) : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val nameLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val nameErrorLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()

    val emailLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val emailErrorLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()

    private val _shouldSubmitLiveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val shouldSubmitLiveEvent: LiveData<Boolean> get() = _shouldSubmitLiveEvent

    private val _checkin = SingleLiveEvent<ViewState<Any>>()
    val checkin: LiveData<ViewState<Any>>
        get() = _checkin

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun validateFields() {
        var isValid = true
        if (!isValidName()) {
            isValid = false
        }
        if (!isValidEmail()) {
            isValid = false
        }
        _shouldSubmitLiveEvent.value = isValid
    }

    private fun isValidName(): Boolean {
        var valid = true
        if (nameLiveEvent.value.isNullOrBlank()) {
            nameErrorLiveEvent.value = Constants.ErrorMessages.FIELD_REQUIRED
            valid = false
        } else {
            nameErrorLiveEvent.value = ""
        }
        return valid
    }

    private fun isValidEmail(): Boolean {
        var valid = true
        if (TextUtils.isEmpty(emailLiveEvent.value)) {
            emailErrorLiveEvent.value = Constants.ErrorMessages.FIELD_REQUIRED
            valid = false
        } else if (!Validators.isValidEmail(emailLiveEvent.value)) {
            emailErrorLiveEvent.value = Constants.ErrorMessages.INVALID_EMAIL
            valid = false
        } else {
            emailErrorLiveEvent.value = ""
        }
        return valid
    }

    fun checkin(eventId: String) {
        _checkin.postLoading()

        require(nameLiveEvent.value != null) { "Name not nullable" }
        require(emailLiveEvent.value != null) { "Email not nullable" }

        val info = Checkin(eventId, nameLiveEvent.value!!, emailLiveEvent.value!!)

        coroutineScope.launch {
            try {
                val result = repository.checkin(info)
                _checkin.postSuccess(result)
            } catch (e: Exception) {
                _checkin.postThrowable(e)
                e.printStackTrace().toString()
            }
        }
    }
}