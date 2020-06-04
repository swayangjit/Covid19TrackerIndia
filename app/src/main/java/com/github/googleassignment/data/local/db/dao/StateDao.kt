package com.github.googleassignment.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.googleassignment.data.network.model.State


/**
 * Created by swayangjit on 3/6/20.
 */
@Dao
interface StateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStates(stateList: List<State>)

    @Query("SELECT * FROM state")
    fun getStateList(): MutableList<State>
}