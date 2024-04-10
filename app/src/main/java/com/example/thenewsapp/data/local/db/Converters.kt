package com.example.thenewsapp.data.local.db

import androidx.room.TypeConverter
import com.example.thenewsapp.data.network.dto.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}