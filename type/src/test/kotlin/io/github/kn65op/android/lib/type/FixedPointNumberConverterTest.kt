package io.github.kn65op.android.lib.type

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.closeTo
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class FixedPointNumberConverterTest {
    val converter = FixedPointNumberConverter()
    private val error = 0.001

    @Test
    fun `convert to fixed point number`() {
        val number = converter.toFixedPointNumber(818)
        val value = number?.toDouble() ?: 0.0

        assertThat(value, closeTo(8.18, error))
    }

    @Test
    fun `convert from fixed point number`() {
        val value = converter.fromFixedPointNumber(FixedPointNumber(8.18))

        assertThat(value, equalTo(818))
    }

}
