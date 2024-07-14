package com.example.movieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.models.BookmarkedMovie

@Database(entities = [BookmarkedMovie::class], version = 1)
abstract class BookmarkDatabase:RoomDatabase() {
abstract fun bookMarkedMovieDao():BookmarkDataDao
}