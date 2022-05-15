package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.addtwonum.AddTwoNum
import com.reillyhe.leetcodeanwser.addtwonum.ListNode
import org.junit.Test
import org.junit.Assert.*
class AddTwoNumsTest {

    @Test
    fun add_two_nums_ez_test() {
        val t1 = ListNode(2)
        val t2 = ListNode(4)
        val t3 = ListNode(3)
        t1.next = t2
        t2.next = t3
        val b1 = ListNode(5)
        val b2 = ListNode(6)
        val b3 = ListNode(4)
        b1.next = b2
        b2.next = b3
        val ret = AddTwoNum.addTwoNumbers(t1, b1)
        assertEquals("708", ret!!.showResult())
    }

    @Test
    fun add_two_nums_mid_test() {
        val t1 = ListNode(6)
        val t2 = ListNode(4)
        val t3 = ListNode(7)
        t1.next = t2
        t2.next = t3
        val b1 = ListNode(7)
        val b2 = ListNode(8)
        val b3 = ListNode(4)
        b1.next = b2
        b2.next = b3
        val ret = AddTwoNum.addTwoNumbers(t1, b1)
        assertEquals("3321", ret!!.showResult())
    }

    @Test
    fun add_two_nums_hard_test() {
        val t1 = ListNode(0)
        val t2 = ListNode(1)
        val t3 = ListNode(0)
        val t4 = ListNode(0)
        val t5 = ListNode(2)
        t1.next = t2
        t2.next = t3
        t3.next = t4
        t4.next = t5
        val b1 = ListNode(0)
        val b2 = ListNode(9)
        b1.next = b2
        val ret = AddTwoNum.addTwoNumbers(t1, b1)
        assertEquals("00102", ret!!.showResult())
    }
}