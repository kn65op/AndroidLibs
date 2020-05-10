package com.example.kn65op.android.lib.type

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.closeTo
import com.natpryce.hamkrest.equalTo
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
    fun Add() {
        assertThat(FixedPointNumber(1.0) + FixedPointNumber(2.0), equalTo(FixedPointNumber(3.0)))
        //assertThat(FixedPointNumber(8.222) + FixedPointNumber(2.0), equalTo(FixedPointNumber(3.0)))
    }
}

