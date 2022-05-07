package com.reillyhe.leetcodeanwser.sort
import org.junit.Test
import org.junit.Assert.*
import java.lang.StringBuilder

class HeapSortTest {

    @Test
    fun bigroot_organized_test() {
        val intArr = intArrayOf(4,3,5,6,7,1,12,9,7,10)
        val output = intArrayOf(4,3,5,6,7,1,12,9,7,10)
        val corret = intArrayOf(12,10,5,9,7,1,4,6,7,3)
        BigRootHeapSort.organizeBigRoot(output, 10)
        assertEquals(ezPrint(corret), ezPrint(output))
    }

    @Test
    fun littleroot_organized_test() {
        val output = intArrayOf(10,9,4,3,2,7,6,9,5,1)
        val corret = intArrayOf(1,2,4,3,9,7,6,9,5,10)
        LitterRootHeapSort.organizeLittleRoot(output, 10)
        assertEquals(ezPrint(corret), ezPrint(output))
    }

    @Test
    fun bigroot_test() {
        val intArr = intArrayOf(4,3,5,6,7,1,12,9,7,10)
        val corret = intArrayOf(1,3,4,5,6,7,7,9,10,12)
        BigRootHeapSort.doBigRootHeapSort(intArr)
        assertEquals(ezPrint(corret), ezPrint(intArr))
    }

    @Test
    fun littleroot_test() {
        val output = intArrayOf(10,9,4,3,2,7,6,9,5,1)
        val corret = intArrayOf(10,9,9,7,6,5,4,3,2,1)
        LitterRootHeapSort.doBigRootHeapSort(output)
        assertEquals(ezPrint(corret), ezPrint(output))
    }

    private fun ezPrint(intArray: IntArray): String {
        val strBuild = StringBuilder()
        intArray.forEachIndexed { index, i ->
            strBuild.append(i)
            if (index != intArray.size - 1) strBuild.append("-")
        }
        return strBuild.toString()
    }
}