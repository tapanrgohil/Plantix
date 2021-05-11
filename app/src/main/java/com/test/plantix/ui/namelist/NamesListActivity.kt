package com.test.plantix.ui.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.plantix.R
import com.test.plantix.data.name.core.Status
import com.test.plantix.databinding.ActivityNameListBinding
import com.test.plantix.ui.namelist.adapter.NamesAdapter
import com.test.plantix.ui.namelist.model.NameUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NamesListActivity : AppCompatActivity() {
    lateinit var binding: ActivityNameListBinding
    var namesAdapter: NamesAdapter = NamesAdapter(arrayListOf())
    private val viewModel by viewModels<NameListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        attachObserver()
        viewModel.getName()
    }

    private fun attachObserver() {
        viewModel.listNameUiModelLiveData.observe(this, {
            it?.let {
                when (it.status) {
                    Status.LOADING -> showLoading()
                    Status.SUCCESS -> setData(it.data)
                    Status.ERROR -> showError(it.message)
                }
            }
        })
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message ?: "Error", Toast.LENGTH_SHORT).show()
        binding.swipeRefresh.isRefreshing = false

    }

    private fun setData(data: List<NameUIModel>?) {
        data?.let {
            namesAdapter.setData(it)
        }
        binding.swipeRefresh.isRefreshing = false
    }

    private fun showLoading() {
        binding.swipeRefresh.isRefreshing = true
    }

    private fun initUi() {
        binding.apply {
            rvNames.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = namesAdapter
            }

            swipeRefresh.setOnRefreshListener {
                viewModel.getName()
            }
        }
    }


}