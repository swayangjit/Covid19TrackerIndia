package com.github.googleassignment.data.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by swayangjit on 1/6/20.
 */
@Parcelize
data class Delta(
    val confirmed: Int,
    val deceased: Int,
    val recovered: Int
): Parcelable {
}