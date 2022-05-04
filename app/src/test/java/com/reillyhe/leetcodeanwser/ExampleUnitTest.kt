package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.lrucache.LRUCache
import com.reillyhe.leetcodeanwser.randomcp.Node
import com.reillyhe.leetcodeanwser.randomcp.RandomCopy
import com.reillyhe.leetcodeanwser.sprial.SprialOrder
import com.reillyhe.leetcodeanwser.twosum.TwoSum
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
//    @Test
//    fun two_sum_test() {
//
//        val input1 = intArrayOf(1,3,5,7,18)
//        val target1 = 10
//        val ret1 = TwoSum.twoSum(input1, target1)
//        assertEquals(target1, input1[ret1!![0]]+input1[ret1!![1]])
//
//        val input2 = intArrayOf(3,3)
//        val target2 = 6
//        val ret2 = TwoSum.twoSum(input2, target2)
//        assertEquals(target2, input2[ret2!![0]]+input2[ret2!![1]])
//    }
//
//    @Test
//    fun random_copy_normal_test() {
//        val node1 = Node(3)
//        val node2 = Node(6)
//        val node3 = Node(17)
//        val node4 = Node(9)
//        val node5 = Node(6)
//        node1.apply {
//            next = node2
//            random = null
//        }
//        node2.apply {
//            next = node3
//            random = node4
//        }
//        node3.apply {
//            next = node4
//            random = node1
//        }
//        node4.apply {
//            next = node5
//            random = node4
//        }
//        node5.apply {
//            next = null
//            random = node3
//        }
//        val node = node1
//        val info = node.info()
//        System.out.println(info)
//        val copiedNode = RandomCopy.copyRandomList(node)
//        val copiedInfo = copiedNode!!.info()
//        assertEquals(info, copiedInfo)
//    }
//
//    @Test
//    fun random_copy_equalv_test() {
//        val node1 = Node(3)
//        val node2 = Node(3)
//        val node3 = Node(3)
//        node1.apply {
//            next = node2
//            random = null
//        }
//        node2.apply {
//            next = node3
//            random = node3
//        }
//        node3.apply {
//            next = null
//            random = node1
//        }
//        val node = node1
//        val info = node.info()
//        System.out.println(info)
//        val copiedNode = RandomCopy.copyRandomList(node)
//        val copiedInfo = copiedNode!!.info()
//        assertEquals(info, copiedInfo)
//    }
//
//    @Test
//    fun lrucache_normal_test() {
//        val cache = LRUCache(2)
//        cache.put(1,1)
//        cache.put(2,2)
//        val anwser1 = "{[2:2][1:1]}"
//        assertEquals(anwser1, cache.dumpCacheInPriority())
//        val value1 = cache.get(1)
//        assertEquals(1, value1)
//        val anwser2 = "{[1:1][2:2]}"
//        assertEquals(anwser2, cache.dumpCacheInPriority())
//    }
//
//    @Test
//    fun lrucache_resize_test() {
//        val cache = LRUCache(3)
//        cache.put(1,1)
//        cache.put(2,2)
//        cache.put(3,3)
//        cache.put(4,4)
//
//        cache.get(4)
//        cache.get(3)
//        cache.get(2)
//        cache.get(1)
//        //2->3->4
//        cache.put(5,5)
//        //5->2->3
//
//        cache.get(1)
//        //5->2->3
//        cache.get(2)
//        //2->5->3
//        cache.get(3)
//        //3->2->5
//        val v = cache.get(4)
//        cache.get(5)
//        //5->3->2
//        val anwser1 = "{[5:5][3:3][2:2]}"
//        assertEquals(anwser1, cache.dumpCacheInPriority())
//        assertEquals(-1, v)
//    }

    @Test
    fun sprial_order_3x3_test() {
        val row1 = intArrayOf(1,2,3)
        val row2 = intArrayOf(4,5,6)
        val row3 = intArrayOf(7,8,9)
        val matrix = arrayOf(row1, row2, row3)
        val result = listOf(1,2,3,6,9,8,7,4,5)
        val ret = SprialOrder.spiralOrder(matrix = matrix)
        assertEquals(result.toString(), ret.toString())
    }

    @Test
    fun sprial_order_4x4_test() {
        val row1 = intArrayOf(1,2,3,4)
        val row2 = intArrayOf(5,6,7,8)
        val row3 = intArrayOf(9,10,11,12)
        val row4 = intArrayOf(13,14,15,16)
        val matrix = arrayOf(row1, row2, row3, row4)
        val result = listOf(1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10)
        val ret = SprialOrder.spiralOrder(matrix = matrix)
        assertEquals(result.toString(), ret.toString())
    }

    @Test
    fun sprial_order_4x4_bigger_width_test() {
        val row1 = intArrayOf(1,2,3,4,5,6,7)
        val row2 = intArrayOf(8,9,10,11,12,13,14)
        val row3 = intArrayOf(15,16,17,18,19,20,21)
        val matrix = arrayOf(row1, row2, row3)
        val result = listOf(1,2,3,4,5,6,7,14,21,20,19,18,17,16,15,8,9,10,11,12,13)
        val ret = SprialOrder.spiralOrder(matrix = matrix)
        assertEquals(result.toString(), ret.toString())
    }

    @Test
    fun sprial_order_4x4_bigger_height_test() {
        //[[23,18,20,26,25],[24,22,3,4,4],[15,22,2,24,29],[18,15,23,28,28]]
        val row1 = intArrayOf(23,18,20,26,25)
        val row2 = intArrayOf(24,22,3,4,4)
        val row3 = intArrayOf(15,22,2,24,29)
        val row4 = intArrayOf(18,15,23,28,28)
        val matrix = arrayOf(row1, row2, row3, row4)
        //[23,18,20,26,25,4,29,28,28,23,15,18,15,24,22,3,4,24,2,22]
        val result = listOf(23,18,20,26,25,4,29,28,28,23,15,18,15,24,22,3,4,24,2,22)
        val ret = SprialOrder.spiralOrder(matrix = matrix)
        assertEquals(result.toString(), ret.toString())
    }

    @Test
    fun sprial_order_special_row_test() {
        val row1 = intArrayOf(1,2,3)
        val matrix = arrayOf(row1)
        val result = listOf(1,2,3)
        val ret = SprialOrder.spiralOrder(matrix = matrix)
        assertEquals(result.toString(), ret.toString())
    }

    @Test
    fun sprial_order_special_colomn_test() {
        val row1 = intArrayOf(1)
        val row2 = intArrayOf(2)
        val row3 = intArrayOf(3)
        val matrix = arrayOf(row1, row2, row3)
        val result = listOf(1,2,3)
        val ret = SprialOrder.spiralOrder(matrix = matrix)
        assertEquals(result.toString(), ret.toString())
    }
}