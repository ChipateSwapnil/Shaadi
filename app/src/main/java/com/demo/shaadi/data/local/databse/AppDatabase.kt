package com.demo.shaadi.data.local.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.shaadi.data.local.dao.ShaadiUserDao
import com.demo.shaadi.data.local.entity.ShaadiUser

@Database(entities = [ShaadiUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): ShaadiUserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "shaadi.db").build()
                }
            }
            return instance!!
        }
    }
}