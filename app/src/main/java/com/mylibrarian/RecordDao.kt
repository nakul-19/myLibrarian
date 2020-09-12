package com.mylibrarian

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDao {
    @Insert
    suspend fun addRecord(record : Record )

    @Query("SELECT * FROM record WHERE type = 'L'")
    suspend fun getLentBooks() : List<Record>

    @Query("SELECT * FROM record WHERE type = 'B'")
    suspend fun getBorrowedBooks() : List<Record>

    @Query("SELECT * FROM record")
    suspend fun getBooks() : List<Record>
}