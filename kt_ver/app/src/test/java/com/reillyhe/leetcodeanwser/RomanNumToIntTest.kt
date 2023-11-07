package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.romantoint.RomanNumToInt
import org.junit.Test
import org.junit.Assert.*
class RomanNumToIntTest {

    @Test
    fun ez_test1() {
        val input = "III"
        val correct = 3
        assertEquals(correct, RomanNumToInt.romanToInt(input))
    }

    @Test
    fun ez_test2() {
        val input = "MCMXCIV"
        val correct = 1994
        assertEquals(correct, RomanNumToInt.romanToInt(input))
    }
}