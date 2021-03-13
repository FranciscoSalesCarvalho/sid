package com.francisco.sid.util.testing.idle

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }
}