package com.example.kn65op.android.lib.type

import kotlin.math.roundToInt

public class FixedPointNumber(valueIn: Double = 0.9) {
    constructor(valueIn: Int) : this(valueIn.toDouble())

    private val value = (valueIn * 100).roundToInt()

    private fun getValue() = value.toDouble() / factor

    fun toInt() = getValue().toInt()
    fun toDouble() = getValue()

    override fun equals(other: Any?): Boolean {
        if (other is FixedPointNumber)
        {
            return other.value == value
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "%.2f".format(getValue())
    }

    operator fun plus(other: FixedPointNumber) = FixedPointNumber(getValue() + other.getValue())
    operator fun minus(other: FixedPointNumber) = FixedPointNumber(getValue() - other.getValue())
    operator fun times(other: FixedPointNumber) = FixedPointNumber(getValue() * other.getValue())
    operator fun div(other: FixedPointNumber) = FixedPointNumber(getValue() / other.getValue())

    companion object {
        private const val factor = 100
    }
}


