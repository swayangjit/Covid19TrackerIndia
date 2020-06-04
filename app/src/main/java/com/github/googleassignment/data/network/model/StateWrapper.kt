package com.github.googleassignment.data.network.model


/**
 * Created by swayangjit on 2/6/20.
 */
data class StateWrapper(
    val stateInfo: List<State>,
    val dailyConfirmed: Int,
    val dailyRecovered: Int,
    val dailyDeceased: Int,
    val deltaConfirmed: Int,
    val deltaRecovered: Int,
    val deltaDeceased: Int
) {
}