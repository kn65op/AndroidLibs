package io.github.kn65op.android.lib.type

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.closeTo
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.matches
import org.junit.Test

class FixedPointNumberTest {
    private val error = 0.001
    private val smaller = FixedPointNumber(101.1)
    private val bigger = FixedPointNumber(101.11)

    @Test
    fun `Not initialized should be zero`() {
        val number = FixedPointNumber()
        assertThat(number.toDouble(), equalTo(0.0))
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
    fun `No convert initialization should not convert`() {
        val number = FixedPointNumber(212, FixedPointNumber.NoConvert())

        assertThat(number.toDouble(), closeTo(2.12, error))
    }

    @Test
    fun `getRaw should return raw value`() {
        val number = FixedPointNumber(2.34)

        assertThat(number.getRawValue(), equalTo(234))
    }

    @Test
    fun add() {
        assertThat(
            FixedPointNumber(1.0) + FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(3.0))
        )
        assertThat(
            FixedPointNumber(8.22) + FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(10.21))
        )
    }

    @Test
    fun subtract() {
        assertThat(
            FixedPointNumber(1.0) - FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(-1.0))
        )
        assertThat(
            FixedPointNumber(8.22) - FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(6.23))
        )
    }

    @Test
    fun multiply() {
        assertThat(
            FixedPointNumber(1.0) * FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(2.0))
        )
        assertThat(
            FixedPointNumber(8.22) * FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(16.36))
        )
    }

    @Test
    fun divide() {
        assertThat(
            FixedPointNumber(1.0) / FixedPointNumber(
                2.0
            ), equalTo(FixedPointNumber(0.5))
        )
        assertThat(
            FixedPointNumber(8.22) / FixedPointNumber(
                1.99
            ), equalTo(FixedPointNumber(4.13))
        )
    }

    @Test
    fun `convert toString`() {
        assertThat(FixedPointNumber(1).toString(), matches(Regex("1.00")))
        assertThat(FixedPointNumber(1.258).toString(), matches(Regex("1.26")))
        assertThat(FixedPointNumber(0.11).toString(), matches(Regex("0.11")))
    }

    @Test
    fun biggerOrEqualThen() {
        assertThat(bigger >= smaller, equalTo(true))
        assertThat(smaller >= bigger, equalTo(false))
        assertThat(bigger >= bigger, equalTo(true))
    }

    @Test
    fun biggerThen() {
        assertThat(bigger > smaller, equalTo(true))
        assertThat(smaller > bigger, equalTo(false))
        assertThat(bigger > bigger, equalTo(false))
    }

    @Test
    fun smallerOrEqualThen() {
        assertThat(bigger <= smaller, equalTo(false))
        assertThat(smaller <= bigger, equalTo(true))
        assertThat(bigger <= bigger, equalTo(true))
    }

    @Test
    fun smallerThen() {
        assertThat(bigger < smaller, equalTo(false))
        assertThat(smaller < bigger, equalTo(true))
        assertThat(bigger < bigger, equalTo(false))
    }
}

