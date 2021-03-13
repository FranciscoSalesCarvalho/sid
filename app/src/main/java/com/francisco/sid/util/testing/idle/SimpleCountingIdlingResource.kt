package com.francisco.sid.util.testing.idle

import androidx.annotation.NonNull
import androidx.test.espresso.IdlingResource
import java.lang.IllegalArgumentException
import java.util.concurrent.atomic.AtomicInteger

class SimpleCountingIdlingResource(
    @NonNull
    private val mResourceName: String
): IdlingResource {

    private val counter = AtomicInteger(0)

    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return mResourceName
    }

    override fun isIdleNow(): Boolean =
        counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            resourceCallback?.onTransitionToIdle()
        }
        if (counterVal < 0) {
            throw IllegalArgumentException("Counter has been corrupted!")
        }
    }
}