package com.demomvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demomvvmapp.adapter.WatchAdapter
import com.demomvvmapp.databinding.ActivityMainBinding
import com.demomvvmapp.model.Watches
import com.demomvvmapp.network.ApiClient
import com.demomvvmapp.network.ApiInterface
import com.demomvvmapp.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var appService: ApiInterface
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }

    private fun initView() {
        appService = ApiClient.createService(this)
        viewModel = HomeViewModel(appService)
        bindWatchData()
    }

    private fun bindWatchData() {
        val adapter = WatchAdapter()
        binding.watchRecycleView.adapter = adapter.apply {
            watchClick = {
                itemClick(it)
            }
        }

        binding.watchRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.arrWatch.observe(this, Observer {
            adapter.updateList(it)
        })

        viewModel.getWatch("", "", 2, "", 1, 0)
    }

    private fun itemClick(watches: Watches) {
        Toast.makeText(this, watches.model_name, Toast.LENGTH_LONG).show()
    }
}