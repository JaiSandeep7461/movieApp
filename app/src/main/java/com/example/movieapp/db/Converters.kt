package com.example.movieapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromGenreList(genreList:List<Int>?):String?{
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListString: String?):List<Int>{
        return if(genreListString==null){
            return emptyList()
        }else{
            val listType = object : TypeToken<List<Int>>(){}.type
            Gson().fromJson(genreListString,listType)
        }

    }

}