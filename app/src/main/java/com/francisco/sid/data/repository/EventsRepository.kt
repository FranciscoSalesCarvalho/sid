package com.francisco.sid.data.repository

import com.francisco.sid.data.model.Checkin
import com.francisco.sid.data.model.Event
import com.francisco.sid.data.network.EventApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val api: EventApi
) {

    suspend fun fetchEvents(): List<Event> =
        withContext(Dispatchers.Main) {
            api.fetchEvents()
        }.await()

    suspend fun checkin(info: Checkin): Any =
        withContext(Dispatchers.Main) {
            api.checkin(info)
        }.await()
}