package com.example.asssignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Details (

    val email: String,
    val name: String,
    val password: String

){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
