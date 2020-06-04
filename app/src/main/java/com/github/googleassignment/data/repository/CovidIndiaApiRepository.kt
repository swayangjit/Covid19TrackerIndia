package com.github.googleassignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googleassignment.data.local.db.dao.StateDao
import com.github.googleassignment.data.network.CovidIndiaApi
import com.github.googleassignment.data.network.model.State
import com.github.googleassignment.data.network.model.StateWrapper
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Executors


/**
 * Created by swayangjit on 30/5/20.
 */
class CovidIndiaApiRepository(val database: StateDao) {

    private var stateList = mutableListOf<State>()
    private var mutableStateLiveData = MutableLiveData<StateWrapper>()
    val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun getStateInfo(): LiveData<StateWrapper> {
        uiScope.launch {
            val request = CovidIndiaApi.covidIndiaApiService.getAllStateInfo()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    stateList = response as MutableList<State>

                    if (stateList != null) {
                        saveStatesToDB(stateList)
                        mutableStateLiveData.value = prepareStateWrapper()
                    }
                } catch (e: HttpException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    Executors.newSingleThreadExecutor().execute {
                        stateList = database.getStateList()
                        mutableStateLiveData.postValue(prepareStateWrapper())
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        }
        return mutableStateLiveData
    }

    fun prepareStateWrapper(): StateWrapper {
        stateList.sortByDescending { it.totalConfirmed }
        val totalConfirmed: Int = stateList.sumBy { it.totalConfirmed }
        val totalRecovered: Int = stateList.sumBy { it.totalRecovered }
        val totalDecesased: Int = stateList.sumBy { it.totalDeath }
        val totalDeltaConfirmed: Int = stateList.sumBy { it.deltaConfirmed }
        val totalDeltaRecovered: Int = stateList.sumBy { it.deltaRecovered }
        val totalDeltaDecesased: Int = stateList.sumBy { it.deltaDeath }
        return StateWrapper(
            stateList,
            totalConfirmed,
            totalRecovered,
            totalDecesased,
            totalDeltaConfirmed,
            totalDeltaRecovered,
            totalDeltaDecesased
        )
    }

    fun saveStatesToDB(list: MutableList<State>) {
        database.insertStates(list)
    }
}