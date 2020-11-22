package com.utn.appsure.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utn.appsure.model.Policy

@Database(entities = [Policy::class], version = 1)
abstract class AppSureDatabase : RoomDatabase() {
    abstract fun policyDao(): PolicyDao

    companion object {
        var INSTANCE: AppSureDatabase? = null

        fun getAppDataBase(context: Context): AppSureDatabase? {
            if (INSTANCE == null) {
                synchronized(AppSureDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppSureDatabase::class.java,
                        "AppSureDatabase"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}