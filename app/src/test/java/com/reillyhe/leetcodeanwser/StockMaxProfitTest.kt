package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.maxprofit.StockMaxProfit
import org.junit.Test
import org.junit.Assert.*
class StockMaxProfitTest {

    @Test
    fun ez_test1() {
        val input = intArrayOf(
            9,3,2,8,1,7
        )
        val correct = 6
        assertEquals(correct, StockMaxProfit.maxProfit(input))
    }

    @Test
    fun ez_test2() {
        val input = intArrayOf(
            9,3,2,25,1,20
        )
        val correct = 23
        assertEquals(correct, StockMaxProfit.maxProfit(input))
    }

    @Test
    fun ez_test3() {
        val input = intArrayOf(
            4,7,1,2
        )
        val correct = 3
        assertEquals(correct, StockMaxProfit.maxProfit(input))
    }

}