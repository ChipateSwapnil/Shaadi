package com.demo.shaadi.ui.view.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.shaadi.data.local.entity.ShaadiUser
import com.demo.shaadi.data.remote.response.NetworkResponse
import com.demo.shaadi.databinding.ActivityHomeBinding
import com.demo.shaadi.ui.adapter.ShaadiUsersAdapter
import com.demo.shaadi.ui.viewmodels.HomeViewModel
import com.demo.shaadi.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), ShaadiUsersAdapter.OnAcceptDeclineListener {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private val adapter = ShaadiUsersAdapter(emptyList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setRecycler()
        initObserver()
    }

    private fun initView(){
        homeViewModel.getShaadiUsers()

        binding.tvRetry.setOnClickListener {
            homeViewModel.getShaadiUsers()
        }
    }

    private fun setRecycler(){
        val layoutManager = LinearLayoutManager(this@HomeActivity)
        binding.rvShaadi.layoutManager = layoutManager
        binding.rvShaadi.adapter = adapter
    }

    private fun initObserver(){
        homeViewModel.usersResult.observe(this, Observer {
            when (it) {
                is NetworkResponse.Loading -> displayLoading()
                is NetworkResponse.Success<*> -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    binding.tvRetry.visibility = View.GONE
                }
                is NetworkResponse.ConnectionError -> displayError(it.errorMessage)
                is NetworkResponse.Error -> displayError(it.errorMessage)
            }
        })

        homeViewModel.localUserResult.observe(this) {
            if(it.isEmpty()){
                displayError(Constants.NO_DATA_FOUND)
            }else {
                adapter.setAdapterList(it)
                binding.rvShaadi.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.tvError.visibility = View.GONE
                binding.tvRetry.visibility = View.GONE
            }
        }
    }

    private fun displayLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvShaadi.visibility = View.GONE
    }

    @SuppressLint("LogNotTimber")
    private fun displayError(errorMessage: String) {
        binding.rvShaadi.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = errorMessage
        Log.d("responseValue", errorMessage)
        binding.tvRetry.visibility = View.VISIBLE
    }

    override fun onClick(shaadiUser: ShaadiUser) {
        homeViewModel.updateShaadiUser(shaadiUser)
    }

}