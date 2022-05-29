package com.demo.shaadi.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.shaadi.data.local.dao.ShaadiUserDao
import com.demo.shaadi.data.local.entity.ShaadiUser
import com.demo.shaadi.data.model.ShaadiUsers
import com.demo.shaadi.data.remote.api.ShaadiUsersService
import com.demo.shaadi.data.remote.response.NetworkResponse
import com.demo.shaadi.utils.ConnectionHandler
import com.demo.shaadi.utils.Constants

class ShaadiUsersRepository(private val service: ShaadiUsersService, private val connectionHandler: ConnectionHandler, private val shaadiUserDao: ShaadiUserDao) {
    suspend fun getShaadiUsers(usersResult: MutableLiveData<NetworkResponse>) {
        if (connectionHandler.isOnline()) {
            val userData = getRemoteData()
            usersResult.postValue(NetworkResponse.Success<ShaadiUsers>(userData))
            storeShaadiUser(getRemoteData())
        }else{
            usersResult.postValue(NetworkResponse.ConnectionError(Constants.NO_CONNECTION_FOUND))
        }
    }

    private suspend fun storeShaadiUser(shaadiUsers: ShaadiUsers) {
        val tempShaadiList = mutableListOf<ShaadiUser>()
        for( user in  shaadiUsers.results){
            tempShaadiList.add(
                ShaadiUser(
                    id = user.login?.uuid ?: "",
                    name = "${user.name.title} ${user.name.first} ${user.name.last}",
                    age = user.dob.age,
                    profileLarge = user.picture.large,
                    profileMedium = user.picture.medium,
                    location = "${user.location.city}, ${user.location.country}",
                    status = Constants.USER_REQUEST_PENDING
                )
            )
        }
        shaadiUserDao.insertAll(tempShaadiList)
    }

    suspend fun updateShaadiUser(shaadiUser: ShaadiUser){
        shaadiUserDao.updateShaadiUser(shaadiUser)
    }

    fun getStoredShaadiUsersData(): LiveData<List<ShaadiUser>> = shaadiUserDao.getShaadiUsers()

    private suspend fun getRemoteData() =  service.getShaadiUsers()
}