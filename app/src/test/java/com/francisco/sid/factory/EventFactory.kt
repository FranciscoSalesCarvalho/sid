package com.francisco.sid.factory

import com.francisco.sid.data.model.Event

object EventFactory {

    fun default(): Event = Event(
        "1",
        "Event Default",
        "This is a event default used for tests",
        "",
        1L,
        1.0,
        1.0,
        1.1)
}