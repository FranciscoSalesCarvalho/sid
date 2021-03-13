package com.francisco.sid.data.network

import com.francisco.sid.data.model.Checkin
import com.francisco.sid.data.model.Event
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {

    @GET("events")
    fun fetchEvents(): Deferred<List<Event>>

    @POST("checkin")
    fun checkin(@Body info: Checkin): Deferred<Any>
}
