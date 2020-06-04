package com.github.googleassignment.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.googleassignment.R
import com.github.googleassignment.data.network.model.State
import com.github.googleassignment.databinding.ItemStateBinding
import com.github.googleassignment.ui.detail.DetailActivity


/**
 * Created by swayangjit on 2/6/20.
 */
class StateAdapter(
    private val stateList: List<State>
) : RecyclerView.Adapter<StateAdapter.ViewHolder>() {

    override fun getItemCount() = stateList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_state,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state: State = stateList[position]
        holder.binding.state = state
        holder.binding.position = position
        holder.binding.layoutState.setOnClickListener {
            val intent = Intent(it.context,DetailActivity::class.java)
            intent.putExtra("state", state)
            it.context.startActivity(intent)
        }
    }

    inner class ViewHolder(val binding: ItemStateBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}