package com.example.weather.ui.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.example.weather.R
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.databinding.HourlyTempItemLayoutBinding
import com.example.weather.utils.convertToReadableHour
import com.example.weather.utils.findDrawable
import com.example.weather.utils.formatTemperature

class TemperaturesAdapter(
        var listOfTemperatures: List<HourlyDataEntity>) : RecyclerView.Adapter<TemperaturesAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return listOfTemperatures.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperaturesAdapter.ViewHolder {
        return ViewHolder(
            parent.context,
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.hourly_temp_item_layout,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(listOfTemperatures[position])
    }

    inner class ViewHolder(private val context: Context, private val viewDataBinding: HourlyTempItemLayoutBinding) :
            RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bindViewHolder(hourlyTemperature: HourlyDataEntity) {
            viewDataBinding.hour.text = convertToReadableHour(hourlyTemperature.time)
            viewDataBinding.icon.setImageDrawable(findDrawable(context, hourlyTemperature.icon))
            viewDataBinding.temp.text = formatTemperature(hourlyTemperature.temperature)
            //viewDataBinding.itemClickListener =
        }
    }

}