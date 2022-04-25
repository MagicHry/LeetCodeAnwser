package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.twosum.TwoSum
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun two_sum_test() {

        val input1 = intArrayOf(1,3,5,7,18)
        val target1 = 10
        val ret1 = TwoSum.twoSum(input1, target1)
        assertEquals(target1, input1[ret1!![0]]+input1[ret1!![1]])

        val input2 = intArrayOf(3,3)
        val target2 = 6
        val ret2 = TwoSum.twoSum(input2, target2)
        assertEquals(target2, input2[ret2!![0]]+input2[ret2!![1]])
    }
}