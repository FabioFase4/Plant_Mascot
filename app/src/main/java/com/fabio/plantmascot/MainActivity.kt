package com.fabio.plantmascot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent

import com.fabio.plantmascot.ui.GardenScreen
import com.fabio.plantmascot.viewmodel.GardenViewModel

class MainActivity : AppCompatActivity() {
    private var viewModel: GardenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = GardenViewModel(application)

        setContent {
            GardenScreen(viewModel = viewModel!!)
        }

        viewModel?.iniciarMonitoramento()

    }

    override fun onResume() {
        super.onResume()
        viewModel?.iniciarMonitoramento()
    }

    override fun onPause() {
        super.onPause()
        viewModel?.pararMonitoramento()
    }
}