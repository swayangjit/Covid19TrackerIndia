package com.github.googleassignment.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.googleassignment.R
import com.github.googleassignment.data.local.db.CovidIndiaDatabase
import com.github.googleassignment.databinding.ActivityMainBinding
import com.github.googleassignment.ui.ViewModelFactory
import com.github.googleassignment.ui.main.adapter.StateAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var stateAdapter: StateAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.title = getString(R.string.label_toolbar)
        val dataSource = CovidIndiaDatabase.getInstance(application).stateDao
        val viewModelFactory = ViewModelFactory(dataSource, application)
        mMainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = mMainViewModel
        binding.lifecycleOwner = this
        setUpRecyclerView()
        setUpSwipeRereshLayout()
        getStateInfo()
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvState.layoutManager = linearLayoutManager
        binding.rvState.showShimmerAdapter()
    }

    private fun setUpSwipeRereshLayout() {
        binding.layoutSwipeRefresh.setColorSchemeResources(R.color.red, R.color.blue, R.color.green)
        binding.layoutSwipeRefresh.setOnRefreshListener {
            binding.rvState.showShimmerAdapter()
            getStateInfo()
        }
    }

    private fun getStateInfo() {
        mMainViewModel.getStateList().observe(this, Observer { stateInfo ->
            stateAdapter = StateAdapter(stateInfo.stateInfo)
            binding.rvState.adapter = stateAdapter
            binding.rvState.hideShimmerAdapter()
            if (binding.layoutSwipeRefresh.isRefreshing()) {
                binding.layoutSwipeRefresh.setRefreshing(false);
            }
        })
    }
}
