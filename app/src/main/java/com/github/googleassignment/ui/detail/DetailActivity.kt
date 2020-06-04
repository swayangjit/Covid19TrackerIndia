package com.github.googleassignment.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.googleassignment.R
import com.github.googleassignment.data.network.model.State
import com.github.googleassignment.databinding.ActivityDetailBinding


/**
 * Created by swayangjit on 4/6/20.
 */
class DetailActivity: AppCompatActivity(){

    private lateinit var binding: ActivityDetailBinding
    private lateinit var districtAadapter: DistrictAadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val state: State = intent.getParcelableExtra("state")
        supportActionBar?.title = state.state
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpRecyclerView(state)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUpRecyclerView(state: State) {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvDistrict.layoutManager = linearLayoutManager
        districtAadapter = DistrictAadapter(state.districtData.sortedByDescending { it.confirmed })
        binding.rvDistrict.adapter = districtAadapter
    }

}