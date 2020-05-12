package io.github.kn65op.android.lib.type

import androidx.room.TypeConverter

class FixedPointNumberConverter {
    @TypeConverter
    fun fromFixedPointNumber(value : FixedPointNumber?)  = value?.getRawValue()

    @TypeConverter
    fun toFixedPointNumber(value : Int?) = value?.let { FixedPointNumber(it, FixedPointNumber.NoConvert()) }
}
