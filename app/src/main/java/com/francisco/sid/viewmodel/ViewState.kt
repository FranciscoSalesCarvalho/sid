package com.francisco.sid.viewmodel

import androidx.lifecycle.MutableLiveData

class ViewState<D>(
    val status: ViewStatus,
    val data: D? = null,
    val throwable: Throwable? = null
) {
    fun handleIt(
        success: (D?) -> Unit = {},
        exception: (Throwable?) -> Unit = {},
        loading: () -> Unit = {},
        stopLoading: () -> Unit = {}
    ): ViewState<D> {
        when (this.status) {
            ViewStatus.LOADING -> {
                loading()
            }
            ViewStatus.SUCCESS -> {
                success(data); stopLoading()
            }
            ViewStatus.EXCEPTION -> {
                exception(throwable); stopLoading()
            }
        }

        return this
    }

    companion object {
        fun <T> success(data: T): ViewState<T> {
            return ViewState(
                status = ViewStatus.SUCCESS,
                data = data
            )
        }

        fun <T> exception(throwable: Throwable): ViewState<T> {
            return ViewState(
                status = ViewStatus.EXCEPTION,
                throwable = throwable
            )
        }
    }

    enum class ViewStatus {
        LOADING, SUCCESS, ERROR, EXCEPTION
    }
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) {
    this.postValue(
        ViewState(
            status = ViewState.ViewStatus.SUCCESS,
            data = data
        )
    )
}

fun <T> MutableLiveData<ViewState<T>>.postThrowable(throwable: Throwable) {
    this.postValue(
        ViewState(
            status = ViewState.ViewStatus.EXCEPTION,
            throwable = throwable
        )
    )
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    this.postValue(ViewState(status = ViewState.ViewStatus.LOADING))
}