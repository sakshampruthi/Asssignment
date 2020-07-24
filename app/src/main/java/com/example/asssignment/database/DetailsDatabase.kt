package com.example.asssignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Details::class],
    version = 1
)
abstract class DetailsDatabase : RoomDatabase() {

    abstract fun getDetailsDao(): DetailsDao

    companion object{

        @Volatile
        private var instance: DetailsDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DetailsDatabase::class.java,
            "detailsDatabase"
        ).build()
    }
}