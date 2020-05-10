package io.github.kn65op.android.lib.type

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.closeTo
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.matches
import io.github.kn65op.android.lib.type.FixedPointNumber
import org.junit.Test

class FixedPointNumberTest {
    private val error = 0.001

    @Test
    fun `Not initialized should be zero`() {
        val number = FixedPointNumber()
        assertThat(number.toInt(), equalTo(0))
    }

    @Test
    fun `Initialized should have value`() {
        val value = 2
        val number = FixedPointNumber(value)

        assertThat(number.toInt(), equalTo(value))
    }

    @Test
    fun `Initialized with double should trim to limit round`() {
        val number = FixedPointNumber(2.348)

        assertThat(number.toDouble(), closeTo(2.35, error))
    }

    @Test
    fun `Initialized with double should trim to limit`() {
        val number = FixedPointNumber(2.344)

        assertThat(number.toDouble(), closeTo(2.34, error))
    }

    @Test
    fun add() {
        assertThat(
            FixedPointNumber(1.0) + FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(3.0)))
        assertThat(
            FixedPointNumber(8.22) + FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(10.21)))
    }

    @Test
    fun subtract() {
        assertThat(
            FixedPointNumber(1.0) - FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(-1.0)))
        assertThat(
            FixedPointNumber(8.22) - FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(6.23)))
    }

    @Test
    fun multiply() {
        assertThat(
            FixedPointNumber(1.0) * FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(2.0)))
        assertThat(
            FixedPointNumber(8.22) * FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(16.36)))
    }

    @Test
    fun divide() {
        assertThat(
            FixedPointNumber(1.0) / FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(0.5)))
        assertThat(
            FixedPointNumber(8.22) / FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(4.13)))
    }

    @Test
    fun `convert toString`() {
        assertThat(FixedPointNumber(1).toString(), matches(Regex("1.00")))
        assertThat(FixedPointNumber(1.258).toString(), matches(Regex("1.26")))
        assertThat(FixedPointNumber(0.11).toString(), matches(Regex("0.11")))
    }
}

