package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.orrangerot.RotOrrange
import org.junit.Test
import org.junit.Assert.*
class OrangeRottingTest {

    @Test
    fun orange_rotting_ez_test1() {
        val input = arrayOf(
            intArrayOf(2,1,1),
            intArrayOf(1,1,0),
            intArrayOf(0,1,1),
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(4, result)
    }

    @Test
    fun orange_rotting_ez_test2() {
        val input = arrayOf(
            intArrayOf(2,1,1),
            intArrayOf(0,1,1),
            intArrayOf(1,0,1),
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(-1, result)
    }

    @Test
    fun orange_rotting_ez_test3() {
        val input = arrayOf(
            intArrayOf(0,0,1),
            intArrayOf(0,0,1),
            intArrayOf(2,0,2),
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(2, result)
    }

    @Test
    fun orange_rotting_ez_test4() {
        val input = arrayOf(
            intArrayOf(0,2,0),
            intArrayOf(0,0,0),
            intArrayOf(2,0,2),
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(0, result)
    }

    @Test
    fun orange_rotting_mid_test1() {
        val input = arrayOf(
            intArrayOf(0,2,0,2),
            intArrayOf(0,1,1,1),
            intArrayOf(0,1,2,0),
            intArrayOf(0,0,0,0)
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(1, result)
    }

    @Test
    fun orange_rotting_mid_test2() {
        val input = arrayOf(
            intArrayOf(2,0,0,0),
            intArrayOf(1,0,0,0),
            intArrayOf(1,0,0,0),
            intArrayOf(1,1,2,1)
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(2, result)
    }

    @Test
    fun orange_rotting_extreme_test1() {
        val input = arrayOf(
            intArrayOf(0,0,0,0),
            intArrayOf(1,0,0,0),
            intArrayOf(1,0,0,0),
            intArrayOf(1,1,1,1)
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(-1, result)
    }

    @Test
    fun orange_rotting_extreme_test2() {
        val input = arrayOf(
            intArrayOf(0,0,0,0),
            intArrayOf(0,0,0,0)
        )
        val result = RotOrrange.orangesRotting(input)
        assertEquals(0, result)
    }
}