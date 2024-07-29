package com.example.movieapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.models.popular.Result

@Dao
interface BookmarkDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(quote: Result): Long

    @Query("SELECT * FROM BookmarkDatabase")
    suspend fun getAllBookMarks(): List<Result>

    @Query("DELETE FROM BookmarkDatabase WHERE id = :id")
    suspend fun deleteItem(id: String)

}