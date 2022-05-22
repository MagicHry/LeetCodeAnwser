package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.atoi.ATOI
import org.junit.Test
import org.junit.Assert.*
class ATOITest {

    @Test
    fun ez_test() {
        val input = "-+     +_    -+--36217"
        val correct = 0
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun mid_test() {
        val input = "-322.400123"
        val correct = -322
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun exetreme_test1() {
        val input = "-214748364890"
        val correct = Int.MIN_VALUE
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun exetreme_test2() {
        val input = "a8"
        val correct = 0
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun exetreme_test3() {
        val input = "214748364890123"
        val correct = Int.MAX_VALUE
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun exetreme_test4() {
        val input = "     asd331"
        val correct = 0
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun exetreme_test5() {
        val input = "20000000000000000000"
        val correct = 2147483647
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun exetreme_test6() {
        val input = "-000000000000000000000000000000000000000000000000001"
        val correct = -1
        assertEquals(correct, ATOI.myAtoi(input))
    }

    @Test
    fun exetreme_test7() {
        val input = "9223372036854775808"
        val correct = Int.MAX_VALUE
        assertEquals(correct, ATOI.myAtoi(input))
    }

}