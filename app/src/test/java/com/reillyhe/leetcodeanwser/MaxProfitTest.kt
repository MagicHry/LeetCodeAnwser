package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.maxprofit.MaxProfit
import org.junit.Test
import org.junit.Assert.*
class MaxProfitTest {

    @Test
    fun ez_test1() {
        //[3,2,6,5,0,3]
        val input = intArrayOf(
            3,2,6,5,0,3
        )
        val correct = 4
        assertEquals(correct, MaxProfit.maxProfit(input))
    }

    @Test
    fun ez_test2() {
        //[3,2,6,5,0,3]
        val input = intArrayOf(
            4,7,1,2
        )
        val correct = 3
        assertEquals(correct, MaxProfit.maxProfit(input))
    }
}