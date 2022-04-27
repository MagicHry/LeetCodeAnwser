package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.lrucache.LRUCache
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

    @Test
    fun lrucache_normal_test() {
        val cache = LRUCache(2)
        cache.put(1,1)
        cache.put(2,2)
        val anwser1 = "{[2:2][1:1]}"
        assertEquals(anwser1, cache.dumpCacheInPriority())
        val value1 = cache.get(1)
        assertEquals(1, value1)
        val anwser2 = "{[1:1][2:2]}"
        assertEquals(anwser2, cache.dumpCacheInPriority())
    }

    @Test
    fun lrucache_resize_test() {
        val cache = LRUCache(3)
        cache.put(1,1)
        cache.put(2,2)
        cache.put(3,3)
        cache.put(4,4)

        cache.get(4)
        cache.get(3)
        cache.get(2)
        cache.get(1)
        //2->3->4
        cache.put(5,5)
        //5->2->3

        cache.get(1)
        //5->2->3
        cache.get(2)
        //2->5->3
        cache.get(3)
        //3->2->5
        val v = cache.get(4)
        cache.get(5)
        //5->3->2
        val anwser1 = "{[5:5][3:3][2:2]}"
        assertEquals(anwser1, cache.dumpCacheInPriority())
        assertEquals(-1, v)
    }
}