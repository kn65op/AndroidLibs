package com.example.kn65op.android.lib.type;

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Test

class FixedPointNumberTest {
    @Test
    fun NotInitializedDirectlyShouldBeEqualToZero() {
        val number = FixedPointNumber()
        val zero = 0
        MatcherAssert.assertThat(number.toInt(), equalTo(zero))
    }

}