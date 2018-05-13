package com.example.sravanreddy.realestateproject.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = [(PropertyData::class), (PropertyTable::class)], version = 1, exportSchema = false)
abstract class PropertyDataBase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
    companion object {
        private var INSTANCE: PropertyDataBase? = null

        fun getInstance(context: Context): PropertyDataBase {
            if(INSTANCE == null){
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        PropertyDataBase::class.java, "Sample.db")
                        .allowMainThreadQueries()
                        .build()

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

