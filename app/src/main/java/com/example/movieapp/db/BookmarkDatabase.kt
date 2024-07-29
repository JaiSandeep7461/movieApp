package com.example.movieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.models.popular.Result


@Database(entities = [Result::class], version = 1)
@TypeConverters(Converters::class)
abstract class BookmarkDatabase:RoomDatabase() {
abstract fun bookMarkedMovieDao():BookmarkDataDao
}