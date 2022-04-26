package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.randomcp.Node
import com.reillyhe.leetcodeanwser.randomcp.RandomCopy
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

    @Test
    fun random_copy_normal_test() {
        val node1 = Node(3)
        val node2 = Node(6)
        val node3 = Node(17)
        val node4 = Node(9)
        val node5 = Node(6)
        node1.apply {
            next = node2
            random = null
        }
        node2.apply {
            next = node3
            random = node4
        }
        node3.apply {
            next = node4
            random = node1
        }
        node4.apply {
            next = node5
            random = node4
        }
        node5.apply {
            next = null
            random = node3
        }
        val node = node1
        val info = node.info()
        System.out.println(info)
        val copiedNode = RandomCopy.copyRandomList(node)
        val copiedInfo = copiedNode!!.info()
        assertEquals(info, copiedInfo)
    }

    @Test
    fun random_copy_equalv_test() {
        val node1 = Node(3)
        val node2 = Node(3)
        val node3 = Node(3)
        node1.apply {
            next = node2
            random = null
        }
        node2.apply {
            next = node3
            random = node3
        }
        node3.apply {
            next = null
            random = node1
        }
        val node = node1
        val info = node.info()
        System.out.println(info)
        val copiedNode = RandomCopy.copyRandomList(node)
        val copiedInfo = copiedNode!!.info()
        assertEquals(info, copiedInfo)
    }
}