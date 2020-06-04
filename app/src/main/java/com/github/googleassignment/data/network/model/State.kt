package com.github.googleassignment.data.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


/**
 * Created by swayangjit on 1/6/20.
 */
@Parcelize
@Entity
@JsonClass(generateAdapter = true)
data class State(
    @PrimaryKey
    val state: String,
    val statecode: String,
    val districtData: List<District>
) : Parcelable{
    val totalConfirmed: Int
        get() = districtData.sumBy { it.confirmed }

    val deltaConfirmed: Int
        get() = districtData.sumBy { it.delta.confirmed }

    val totalActive: Int
        get() = districtData.sumBy { it.active }

    val totalRecovered: Int
        get() = districtData.sumBy { it.recovered }

    val deltaRecovered: Int
        get() = districtData.sumBy { it.delta.recovered }

    val totalDeath: Int
        get() = districtData.sumBy { it.deceased }

    val deltaDeath: Int
        get() = districtData.sumBy { it.delta.deceased }
}