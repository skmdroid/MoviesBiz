package com.skmdroid.moviesbiz.data.local

import androidx.room.TypeConverter

class IntListConverter {
    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() }
    }
}