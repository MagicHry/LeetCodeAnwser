package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.kclosedp.KClosetPoints
import org.junit.Test
import org.junit.Assert.*
import java.lang.StringBuilder

class KClosetPointTest {

    @Test
    fun kcloset_p_ez_test() {
        val points = mutableListOf<IntArray>(
            intArrayOf(3,3),
            intArrayOf(5,-1),
            intArrayOf(-2,4)
        )
        val result = mutableListOf<IntArray>(
            intArrayOf(3,3),
            intArrayOf(-2,4)
        )
        val ret = KClosetPoints.kClosest(points = points.toTypedArray(), k = 2)
        assertEquals(ezPrint(result.toTypedArray()), ezPrint(ret))
    }

    @Test
    fun kcloset_p_mid_test() {
        val points = mutableListOf<IntArray>(
            intArrayOf(3,3),
            intArrayOf(5,-1),
            intArrayOf(-2,4),
            intArrayOf(7,3),
            intArrayOf(-3,-3),
            intArrayOf(100,0),
            intArrayOf(2,2)
        )
        val result = mutableListOf<IntArray>(
            intArrayOf(2,2),
            intArrayOf(-3,-3),
            intArrayOf(3,3),
            intArrayOf(-2,4),
            intArrayOf(5,-1)
        )
        val ret = KClosetPoints.kClosest(points = points.toTypedArray(), k = 5)
        assertEquals(ezPrint(result.toTypedArray()), ezPrint(ret))
    }

    @Test
    fun kcloset_p_hard_test() {
        val points = mutableListOf<IntArray>(
            intArrayOf(3,3),
            intArrayOf(5,-1),
            intArrayOf(-2,4),
            intArrayOf(7,3),
            intArrayOf(-10,-3),
            intArrayOf(100,0),
            intArrayOf(2,2),
            intArrayOf(100,1),
            intArrayOf(100,2),
            intArrayOf(100,3),
            intArrayOf(100,4),
            intArrayOf(100,5),
            intArrayOf(100,6),
            intArrayOf(100,9),
            intArrayOf(100,2),
            intArrayOf(100,0),
            intArrayOf(100,0),
        )
        val result = mutableListOf<IntArray>(
            intArrayOf(2,2),
            intArrayOf(3,3),
            intArrayOf(-2,4),
            intArrayOf(5,-1),
            intArrayOf(7,3),
            intArrayOf(-10,-3),
            intArrayOf(100,0),
            intArrayOf(100,0),
        )
        val ret = KClosetPoints.kClosest(points = points.toTypedArray(), k = 8)
        assertEquals(ezPrint(result.toTypedArray()), ezPrint(ret))
    }

    private fun ezPrint(result: Array<IntArray>): String {
        val strBuilder = StringBuilder()
        strBuilder.append("[")
        for (p in result) {
            strBuilder.append("(").append(p[0]).append(",").append(p[1]).append(")").append(",")
        }
        strBuilder.append("]")
        return strBuilder.toString()
    }
}