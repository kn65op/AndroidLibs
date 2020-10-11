package io.github.kn65op.android.lib.type

import kotlin.math.roundToInt

public class FixedPointNumber(valueIn: Double = 0.0) {
    internal class NoConvert{}
    constructor(valueIn: Int) : this(valueIn.toDouble())
    internal constructor(valueIn: Int, @Suppress("UNUSED_PARAMETER") dummy : NoConvert) : this(valueIn.toDouble() / factor)

    private val value = (valueIn * factor).roundToInt()

    private fun getValue() = value.toDouble() / factor
    internal fun getRawValue() = value

    fun toInt() = getValue().toInt()
    fun toDouble() = getValue()


    override fun equals(other: Any?): Boolean {
        if (other is FixedPointNumber) {
            return other.value == value
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "%.2f".format(getValue())
    }

    operator fun plus(other: FixedPointNumber) =
        FixedPointNumber(getValue() + other.getValue())

    operator fun minus(other: FixedPointNumber) =
        FixedPointNumber(getValue() - other.getValue())

    operator fun times(other: FixedPointNumber) =
        FixedPointNumber(getValue() * other.getValue())

    operator fun div(other: FixedPointNumber) =
        FixedPointNumber(getValue() / other.getValue())

    operator fun compareTo(other: FixedPointNumber) =
        value - other.value

    companion object {
        private const val factor = 100
    }
}


