package com.mylibrarian

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Record::class],
    version = 1
)

abstract class RecordDatabase : RoomDatabase(){

    abstract fun getRecordDao() : RecordDao

    companion object{
        @Volatile private var instance : RecordDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            RecordDatabase::class.java,
            "recordDatabase"
        ).build()
    }

}