package com.example.sravanreddy.realestateproject.data.local

import android.app.Activity
import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.sravanreddy.realestateproject.common.Constants


@Database(entities = arrayOf(PropertyTable::class), version = 1, exportSchema = false )
public abstract class  PropertyDataBase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
    companion object {
        public fun getPropertyDataBase( context: Context) : PropertyDataBase{
            var INSTANCE :PropertyDataBase = Room.databaseBuilder(context.applicationContext,
                                PropertyDataBase::class.java!!, Constants.DATABASENAME)
                                .build()

            return INSTANCE
        }
    }

}

