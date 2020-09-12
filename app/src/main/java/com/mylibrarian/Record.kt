package com.mylibrarian

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    val b_name : String,
    val p_name : String,
    val type : Char
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}