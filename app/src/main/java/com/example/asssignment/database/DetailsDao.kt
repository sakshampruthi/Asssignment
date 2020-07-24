package com.example.asssignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DetailsDao {

    @Insert
    fun addDetails(details: Details)

    @Query("SELECT * FROM Details")
    fun getAllDetails(): List<Details>


}