package com.github.googleassignment.data.network.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


/**
 * Created by swayangjit on 1/6/20.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class District(
    val district: String,
    val notes: String,
    val active: Int,
    val confirmed: Int,
    val deceased: Int,
    val recovered: Int,
    val delta: Delta
): Parcelable {
}