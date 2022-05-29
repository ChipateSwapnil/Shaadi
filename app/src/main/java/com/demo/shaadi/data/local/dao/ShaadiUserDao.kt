package com.demo.shaadi.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.shaadi.data.local.entity.ShaadiUser
import com.demo.shaadi.utils.Constants

@Dao
interface ShaadiUserDao {
    @Query("SELECT * FROM "+ Constants.TABLE_NAME)
    fun getShaadiUsers(): LiveData<List<ShaadiUser>>

    @Insert(onConflict = IGNORE)
    suspend fun insertAll(users: List<ShaadiUser>)

    @Insert(onConflict = REPLACE)
    suspend fun insert(user :ShaadiUser)

    @Update
    suspend fun updateShaadiUser(user :ShaadiUser)

//    @Query("UPDATE ${Constants.TABLE_NAME} SET status=:status WHERE id = :id")
//    suspend fun  updateShaadiUser(status : Int, id : Int);

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    suspend fun clear()
}