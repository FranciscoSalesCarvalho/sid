package com.francisco.sid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.francisco.sid.data.model.Event
import com.francisco.sid.data.repository.EventsRepository
import com.francisco.sid.util.testing.idle.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventsViewModel @Inject constructor(
    private val repository: EventsRepository
) : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _events = MutableLiveData<ViewState<List<Event>>>()
    val events: LiveData<ViewState<List<Event>>>
        get() = _events

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun fetchEvents() {
        EspressoIdlingResource.increment()

        _events.postLoading()
        coroutineScope.launch {
            try {
                val result = repository.fetchEvents()
                _events.postSuccess(result)
            } catch (e: Exception) {
                _events.postThrowable(e)
            }
            if (!EspressoIdlingResource.mCountingIdlingResource.isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }
    }
}