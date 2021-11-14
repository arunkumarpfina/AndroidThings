package com.arun.androidThings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arun.androidThings.R
import com.arun.androidThings.data.Details
import com.arun.androidThings.databinding.CheckboxLayoutBinding

class ThingsAdapter(val catList: List<Details>, val context: Context) :
    RecyclerView.Adapter<ThingsAdapter.ViewHolder>() {
    class ViewHolder(val binding: CheckboxLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.checkbox_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listData = catList[position]
        holder.binding.cb.text = listData.firstName
        holder.binding.cb.isChecked = listData.isChecked
        val col = context.resources.getIntArray(R.array.btn_colours)
        holder.binding.userdata.setBackgroundColor(col[position])
        holder.binding.cb.setOnCheckedChangeListener { compoundButton, b ->
            listData.isChecked = b
        }
    }

    override fun getItemCount(): Int {
        return catList.size
    }


}