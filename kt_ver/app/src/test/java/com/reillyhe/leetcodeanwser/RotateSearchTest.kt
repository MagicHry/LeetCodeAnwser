package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.rotatesortedarray.RotateSearchArray
import org.junit.Test
import org.junit.Assert.*
class RotateSearchTest {

    @Test
    fun ez_test1() {
        val input = intArrayOf(
            3,4,5,6,7,8,9,0,1,2
        )
        assertEquals(1, RotateSearchArray.search(input,4))
    }

    @Test
    fun ez_test2() {
        val input = intArrayOf(
            3,4,5,6,7,8,9,0,1,2
        )
        assertEquals(-1, RotateSearchArray.search(input,12))
    }
}