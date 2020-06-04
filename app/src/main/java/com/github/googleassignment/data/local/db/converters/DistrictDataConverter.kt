package com.github.googleassignment.data.local.db.converters

import androidx.room.TypeConverter
import com.github.googleassignment.data.network.model.District
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


/**
 * Created by swayangjit on 3/6/20.
 */
class DistrictDataConverter {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun fromString(value: String): List<District>? {
        val listType = Types.newParameterizedType(List::class.java, District::class.java)
        val adapter: JsonAdapter<List<District>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }
    @TypeConverter
    fun toJson(list: List<District>): String {
        val listType = Types.newParameterizedType(List::class.java, District::class.java)
        val adapter: JsonAdapter<List<District>> = moshi.adapter(listType)
        return adapter.toJson(list)
    }
}