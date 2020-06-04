package com.github.googleassignment.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googleassignment.data.local.db.dao.StateDao
import com.github.googleassignment.data.network.model.DailyStateInfo
import com.github.googleassignment.data.network.model.StateWrapper
import com.github.googleassignment.data.repository.CovidIndiaApiRepository


/**
 * Created by swayangjit on 30/5/20.
 */
class MainViewModel(
    val database: StateDao,
    application: Application) : AndroidViewModel(application) {

    private val covidIndiaApiRepository = CovidIndiaApiRepository(database)
    var  stateListInfo: LiveData<StateWrapper> = MutableLiveData()
    var dailyStateListInfo: LiveData<List<DailyStateInfo>> = MutableLiveData()

    fun getStateList(): LiveData<StateWrapper> {
        stateListInfo = covidIndiaApiRepository.getStateInfo()
        return stateListInfo
    }

    override fun onCleared() {
        super.onCleared()
        covidIndiaApiRepository.viewModelJob.cancel()
    }
}