package com.francisco.sid.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checkin(
    val eventId: String,
    val name: String,
    val email: String
): Parcelable