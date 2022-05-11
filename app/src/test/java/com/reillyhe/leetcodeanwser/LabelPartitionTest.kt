package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.labelpartition.LabelPartition
import org.junit.Test
import org.junit.Assert.*
class LabelPartitionTest {

    @Test
    fun label_partition_ez_test() {
        val input = "abacccdcf"
        val ret = LabelPartition.partitionLabels(input)
        val correct = mutableListOf(3,5,1)
        assertArrayEquals(correct.toTypedArray(), ret.toTypedArray())
    }

    @Test
    fun label_partition_mid_test() {
        val input = "ababcbacideejdf"
        val ret = LabelPartition.partitionLabels(input)
        val correct = mutableListOf(8,1,5,1)
        assertArrayEquals(correct.toTypedArray(), ret.toTypedArray())
    }

    @Test
    fun label_partition_extreme_test1() {
        val input = "abcdefg"
        val ret = LabelPartition.partitionLabels(input)
        val correct = mutableListOf(1,1,1,1,1,1,1)
        assertArrayEquals(correct.toTypedArray(), ret.toTypedArray())
    }

    @Test
    fun label_partition_hard_test() {
        val input = "abacdcodslkfqjkahfo"
        val ret = LabelPartition.partitionLabels(input)
        val correct = mutableListOf(19)
        assertArrayEquals(correct.toTypedArray(), ret.toTypedArray())
    }
}