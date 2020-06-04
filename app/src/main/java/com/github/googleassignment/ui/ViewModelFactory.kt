package com.github.googleassignment.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googleassignment.data.local.db.CovidIndiaDatabase
import com.github.googleassignment.data.local.db.dao.StateDao
import com.github.googleassignment.ui.main.MainViewModel


/**
 * Created by swayangjit on 3/6/20.
 */
class ViewModelFactory(
    private val dataSource: StateDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}