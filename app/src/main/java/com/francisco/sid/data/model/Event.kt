package com.francisco.sid.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.math.BigDecimal

@Parcelize
data class Event(
   val id: String,
   val title: String,
   val description: String,
   val image: String,
   val date: Long,
   val longitude: Double,
   val latitude: Double,
   val price: Double
): Parcelable