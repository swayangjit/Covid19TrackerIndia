package com.github.googleassignment.data.network.model

import com.squareup.moshi.Json


/**
 * Created by swayangjit on 2/6/20.
 */
data class DailyStateInfoWrapper(
    @Json(name="states_daily")
    var items: List<DailyStateInfo>?
) {
}