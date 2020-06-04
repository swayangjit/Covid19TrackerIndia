package com.github.googleassignment.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.googleassignment.R
import com.github.googleassignment.data.network.model.District
import com.github.googleassignment.databinding.ItemDistrictBinding


/**
 * Created by swayangjit on 4/6/20.
 */
class DistrictAadapter(private val districtList: List<District>) : RecyclerView.Adapter<DistrictAadapter.ViewHolder>() {

    override fun getItemCount() = districtList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_district,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.district = districtList[position]
        holder.binding.position = position
    }

    inner class ViewHolder(val binding: ItemDistrictBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}