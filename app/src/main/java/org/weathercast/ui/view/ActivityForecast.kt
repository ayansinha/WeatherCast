package org.weathercast.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.weathercast.R
import org.weathercast.data.model.WeatherCurrentModel
import org.weathercast.data.model.WeeksForecastModel
import org.weathercast.ui.adapter.ForecastAdapter
import org.weathercast.ui.viewmodel.ForecastViewModel
import org.weathercast.util.Constants.Companion.ERROR_MSG
import org.weathercast.util.Converter
import org.weathercast.util.Status
import org.weathercast.util.showSnackBar
import org.weathercast.util.toastShort
import timber.log.Timber
import javax.inject.Inject

/**
 * [ActivityForecast]
 */
class ActivityForecast : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ForecastViewModel
    //private val viewModel: ForecastViewModel by viewModels { viewModelFactory }

    //BaseViewModelFactory { ForecastViewModel(/*RemoteRepository(APIBuilder.apiService), */"Pune") }
    /*private val viewModel: ForecastViewModel by viewModels {
        //BaseViewModelFactory { ForecastViewModel(RemoteRepository(APIBuilder.apiService), "Pune") }
            viewModelFactory
    }*/


    /*private val viewModel: ForecastViewModel by lazy {
        ViewModelProvider(
            this,
            BaseViewModelFactory { ForecastViewModel(RemoteRepository(APIBuilder.apiService) , "Pune") }
        ).get(
            ForecastViewModel::class.java
        )
    }*/

    private lateinit var forecastLists: List<WeeksForecastModel.WeeksData>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ForecastViewModel::class.java)
        fetchCurrentData()

    }

    private fun fetchCurrentData() {
        viewModel.resultCurrentWeather.observe(
            this,
            {
                when (it.status) {
                    Status.LOADING -> apiStatus(::showStatus, Status.LOADING)  //loading
                    //status(Status.LOADING)
                    Status.SUCCESS -> { //success
                        apiStatus(::showStatus, Status.SUCCESS)
                        //status(Status.SUCCESS)
                        it.data?.let { value ->
                            value.body()?.let { res ->
                                Timber.e(res.toString())
                                runOnUiThread {
                                    displayTemp(res)
                                }.also {
                                    fetchWeeksData()
                                }
                            }
                        }

                    }
                    Status.ERROR -> apiStatus(::showStatus, Status.ERROR) //error
                    //status(Status.ERROR)
                }
            }
        )
    }

    private fun fetchWeeksData() {
        viewModel.resultWeeksData.observe(
            this,
            {
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
            }
        )
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
        mainMaxTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.tempMax)}°c"
        mainMinTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.tempMin)}°c"
        textViewMainTemp.text = "${Converter.kelvinToCelsius(forecastModel.main.temp)}°c"
        textViewStatus.text = forecastModel.weather[0].main
    }

    /**
     * higher order function as status passed as a lambda expression
     */
    private fun apiStatus(func: (status: Status) -> Unit, status: Status) {
        func(status)
    }

    /**
     * another way of higher order function calling api status things
     */
    val status : (Status) -> Unit = {
        when (it) {
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
            }
            Status.ERROR -> {
                progressBar.visibility = View.GONE
                toastShort(ERROR_MSG)
                recyclerViewForecast.showSnackBar(ERROR_MSG)
            }
        }
    }

    /**
     * showing api status
     */
    private fun showStatus(status: Status) {
        when (status) {
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
            }
            Status.ERROR -> {
                progressBar.visibility = View.GONE
                toastShort(ERROR_MSG)
                recyclerViewForecast.showSnackBar(ERROR_MSG)
            }
        }
    }
}
