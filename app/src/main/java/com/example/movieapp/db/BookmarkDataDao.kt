package com.example.movieapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.models.BookmarkedMovie
import com.example.movieapp.data.models.Item

@Dao
interface BookmarkDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(quote:Item):Long

    @Query("SELECT * FROM BookmarkDatabase")
    suspend fun getAllBookMarks():List<Item>

    @Query("DELETE FROM BookmarkDatabase WHERE id = :id")
    suspend fun deleteItem(id:String)

}