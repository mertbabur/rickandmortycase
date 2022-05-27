package com.example.rickandmortycase.data.model.response


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val name: String?,
    val url: String?
): Parcelable