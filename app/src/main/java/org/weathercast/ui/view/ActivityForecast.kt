package org.weathercast.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.weathercast.R
import org.weathercast.data.model.ForecastModel
import org.weathercast.data.model.WeatherCurrentModel
import org.weathercast.data.model.WeeksForecastModel
import org.weathercast.data.repo.remote.APIBuilder
import org.weathercast.data.repo.remote.RemoteRepository
import org.weathercast.ui.BaseViewModelFactory
import org.weathercast.ui.adapter.ForecastAdapter
import org.weathercast.ui.viewmodel.ForecastViewModel
import org.weathercast.util.Converter
import org.weathercast.util.Status

class ActivityForecast : AppCompatActivity() {

    private val viewModel: ForecastViewModel by lazy {
        ViewModelProvider(
            this,
            BaseViewModelFactory { ForecastViewModel(RemoteRepository(APIBuilder.apiService)) }).get(
            ForecastViewModel::class.java
        )
    }
    private lateinit var forecastLists: List<WeeksForecastModel.WeeksData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchCurrentData()

    }

    private fun fetchCurrentData() {
        viewModel.resultCurrentWeather.observe(this,{

            when (it.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyclerViewForecast.visibility = View.VISIBLE
                    textViewMainTemp.visibility = View.VISIBLE
                    mainMinTemp.visibility = View.VISIBLE
                    separator.visibility = View.VISIBLE
                    mainMaxTemp.visibility = View.VISIBLE
                    imageViewStatus.visibility = View.VISIBLE
                    textViewStatus.visibility = View.VISIBLE
                    it.data?.let { value ->
                        value.body()?.let { res ->
                            Log.e("RES->", res.toString())
                            displayTemp(res)
                        }
                    }
                    fetchWeeksData()
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun fetchWeeksData() {
        viewModel.resultWeeksData.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    it.data?.let { value ->
                        value.body()?.list?.let { res -> displayAdapter(res) }
                    }
                }
                Status.ERROR -> {
                }
            }
        })
    }

    private fun displayAdapter(list: List<WeeksForecastModel.WeeksData>) {
        recyclerViewForecast.apply {
            layoutManager =
                LinearLayoutManager(this@ActivityForecast, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = ForecastAdapter(list, this@ActivityForecast)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayTemp(forecastModel: WeatherCurrentModel) {
        mainMaxTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.temp_max)}°c"
        mainMinTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.temp_min)}°c"
        textViewMainTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.temp)}°c"
        textViewStatus.text = forecastModel.weather[0].main
    }
}
