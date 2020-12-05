package org.weathercast.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.weathercast.R
import org.weathercast.data.model.ForecastModel
import org.weathercast.data.model.WeatherCurrentModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "INIT_BLOCK: --> onCreate")
        fetchCurrentData()

        val forecastLists = mutableListOf(
            ForecastModel("Monday", "31\u00B0c", "34°", "28°", R.drawable.ic_cloud),
            ForecastModel("Tuesday", "29\u00B0c", "33°", "27°", R.drawable.ic_sunny),
            ForecastModel("Wednesday", "32\u00B0c", "35°", "26°", R.drawable.ic_rainy),
            ForecastModel("Thursday", "33\u00B0c", "36°", "28°", R.drawable.ic_night),
            ForecastModel("Friday", "31\u00B0c", "35°", "29°", R.drawable.ic_thunder)
        )


        recyclerViewForecast.apply {
            layoutManager =
                LinearLayoutManager(this@ActivityForecast, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = ForecastAdapter(forecastLists, this@ActivityForecast)
        }
    }

    private fun fetchCurrentData() {
        viewModel.resultCurrentWeather.observe(this, {
            Log.d("TAG", "INIT_BLOCK: --> observer")
            Log.e("RESPONSE->", it.data.toString())
            it.data?.let { value ->
                value.body()?.let { res ->
                    Log.e("RES->", res.toString())
                    displayTemp(res)
                }
            }
            when (it.status) {
                Status.LOADING -> {
                    Log.e("STATUS", "LOADING" )
                }
                Status.SUCCESS -> {
                    Log.e("STATUS", "SUCCESS" )
                }
                Status.ERROR -> {
                    Log.e("STATUS", "ERROR" )
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun displayTemp(forecastModel: WeatherCurrentModel) {
        mainMaxTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.temp_max)}°c"
        mainMinTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.temp_min)}°c"
        textViewMainTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.temp)}°c"
        textViewStatus.text = forecastModel.weather[0].main
    }
}
