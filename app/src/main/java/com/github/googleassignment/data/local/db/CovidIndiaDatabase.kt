package com.github.googleassignment.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.googleassignment.data.local.db.converters.DistrictDataConverter
import com.github.googleassignment.data.local.db.dao.StateDao
import com.github.googleassignment.data.network.model.State


/**
 * Created by swayangjit on 3/6/20.
 */
@Database(entities = [State::class], version = 1, exportSchema = true)
@TypeConverters(value = [DistrictDataConverter::class])
abstract class CovidIndiaDatabase : RoomDatabase() {
    abstract val stateDao: StateDao

    companion object {

        @Volatile
        private var INSTANCE: CovidIndiaDatabase? = null

        fun getInstance(context: Context): CovidIndiaDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CovidIndiaDatabase::class.java,
                        "covid_india"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}