package com.demo.shaadi.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.shaadi.data.local.entity.ShaadiUser
import com.demo.shaadi.data.remote.response.NetworkResponse
import com.demo.shaadi.data.repositories.ShaadiUsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: ShaadiUsersRepository) : ViewModel() {

    val usersResult = MutableLiveData<NetworkResponse>()
    val localUserResult = repository.getStoredShaadiUsersData()

    fun getShaadiUsers() {
        viewModelScope.launch {
            usersResult.postValue(NetworkResponse.Loading)
            withContext(Dispatchers.IO) {
                try {
                    repository.getShaadiUsers(usersResult)
                } catch (throwable: Throwable) {
                    usersResult.postValue(NetworkResponse.Error(throwable.message.toString()))
                }
            }
        }
    }

    fun updateShaadiUser(shaadiUser: ShaadiUser){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateShaadiUser(shaadiUser)
            }
        }
    }
}