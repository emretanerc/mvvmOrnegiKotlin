package com.etcmobileapps.mvvmornegikotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.etcmobileapps.mvvmornegikotlin.R
import com.etcmobileapps.mvvmornegikotlin.adapter.RecyclerViewAdapter
import com.etcmobileapps.mvvmornegikotlin.databinding.ActivityMainBinding


private lateinit var bindingMain: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private val recyclerViewAdapter= RecyclerViewAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
         viewModel.refreshData()
        bindingMain.recyclerView.layoutManager= LinearLayoutManager( applicationContext)
        bindingMain.recyclerView.adapter = recyclerViewAdapter


    }

    override fun onStart() {
        super.onStart()

        bindingMain.swipeRefreshLayout.setOnRefreshListener {
            bindingMain.recyclerView.visibility=View.GONE
            bindingMain.dataError.visibility=View.GONE
            bindingMain.dataLoading.visibility=View.VISIBLE
            viewModel.refreshData()
            bindingMain.swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.data.observe(this, Observer { data->
            data?.let {
                bindingMain.recyclerView.visibility= View.VISIBLE
                recyclerViewAdapter.updateDataList(data)
            }
        })
        viewModel.dataError.observe(this, Observer { error ->
            error?.let {
                if(it) {
                    bindingMain.dataError.visibility = View.VISIBLE
                } else {
                    bindingMain.dataError.visibility = View.GONE
                }
            }
        })
        viewModel.dataLoading.observe(this, Observer { loading->
            loading?.let {
                if (it) {
                    bindingMain.dataLoading.visibility = View.VISIBLE
                    bindingMain.recyclerView.visibility = View.GONE
                    bindingMain.dataError.visibility = View.GONE
                } else {
                    bindingMain.dataLoading.visibility = View.GONE
                }
            }
        })
    }
}