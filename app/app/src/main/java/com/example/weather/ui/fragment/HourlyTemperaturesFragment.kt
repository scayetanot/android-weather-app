package com.example.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.MainApplication
import com.example.weather.R
import com.example.weather.data.entity.HourlyDataEntity
import com.example.weather.databinding.DetailsFragmentBinding
import com.example.weather.utils.viewModelProvider
import javax.inject.Inject

class HourlyTemperaturesFragment: Fragment() {

    private lateinit var temperaturesAdapter: TemperaturesAdapter
    private val appComponents by lazy { MainApplication.appComponents }

    val DFLT_LAT: Double = 33.942791
    val DFLT_LONG: Double = -118.410042

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): HourlyTemperatureViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private lateinit var binding: DetailsFragmentBinding


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        appComponents.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        getViewModel().getHourlyTemperatures(DFLT_LAT, DFLT_LONG)
    }

    private fun initObservers() {
        getViewModel().resultHourlyTemperature.observe(this, Observer { temperaturesList ->
            temperaturesList?.let {
                initRecycler(it)
            }
        }
        )

        getViewModel().errorMessage.observe(this, Observer {
            Toast.makeText(this.context,"Connection Error", Toast.LENGTH_LONG).show();
        })
    }

    private fun initRecycler(list: List<HourlyDataEntity>) {
        if (!list.isNullOrEmpty()) {
            temperaturesAdapter = TemperaturesAdapter(list)//, this)
            binding.detailsRecycler.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = temperaturesAdapter
            }
        } //else {
          //  handleEmptyList()
       // }
    }

    //private fun handleEmptyList() {
    //    with(binding) {
    //        tvErrorMessage.show()
    //        tvErrorMessage.text = getString(R.string.no_result)
    //    }
   // }
}