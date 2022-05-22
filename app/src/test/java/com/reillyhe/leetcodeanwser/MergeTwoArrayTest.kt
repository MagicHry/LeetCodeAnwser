package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.mergetwolist.MergeTwoArray
import org.junit.Test
import org.junit.Assert.*
class MergeTwoArrayTest {

    @Test
    fun ez_test() {
        val left = intArrayOf(
            1,2,3,0,0,0
        )
        val right = intArrayOf(
            2,5,6
        )
        val correct = intArrayOf(
            1,2,2,3,5,6
        )
        MergeTwoArray.merge(left,3,right,3)
        assertArrayEquals(correct, left)
    }
}