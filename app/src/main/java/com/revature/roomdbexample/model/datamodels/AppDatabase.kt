package com.revature.roomdbexample.model.datamodels

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.revature.roomdbexample.model.dao.CustomerData
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Customer::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun customerDao():CustomerData

    companion object {

        @Volatile   //Anything that we write will be immediately available to the whole application
        private var INSTANCE: AppDatabase?= null

        @InternalCoroutinesApi
        fun getDatabase(context:Context):AppDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "customer_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}