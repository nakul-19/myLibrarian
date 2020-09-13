package com.mylibrarian

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Record(
    @ColumnInfo(name = "b_name") var b_name : String,
    @ColumnInfo(name = "p_name") var p_name : String,
    @ColumnInfo(name = "type") var type : String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}