package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.meetingroom.MeetingRoomArrange
import org.junit.Test
import org.junit.Assert.*
class MeetingRoomTest {

    @Test
    fun room_arrange_ez_test1() {
        val input = mutableListOf<IntArray>(
            intArrayOf(1,2),
            intArrayOf(1,2),
            intArrayOf(3,4),
            intArrayOf(2,5),
            intArrayOf(5,9),
            intArrayOf(5,8),
        )
        val ret = MeetingRoomArrange.minMeetingRooms(intervals = input.toTypedArray())
        assertEquals(2, ret)
    }

    @Test
    fun room_arrange_ez_test2() {
        val input = mutableListOf<IntArray>(
            intArrayOf(0,30),
            intArrayOf(5,10),
            intArrayOf(15,20)
        )
        val ret = MeetingRoomArrange.minMeetingRooms(intervals = input.toTypedArray())
        assertEquals(2, ret)
    }

    @Test
    fun room_arrange_ez_test3() {
        val input = mutableListOf<IntArray>(
            intArrayOf(7,10),
            intArrayOf(2,4)
        )
        val ret = MeetingRoomArrange.minMeetingRooms(intervals = input.toTypedArray())
        assertEquals(1, ret)
    }


    @Test
    fun room_arrange_mid_test1() {
        val input = mutableListOf<IntArray>(
            intArrayOf(1,2),
            intArrayOf(1,2),
            intArrayOf(3,4),
            intArrayOf(2,5),
            intArrayOf(5,9),
            intArrayOf(5,8),
            intArrayOf(200,300),
            intArrayOf(250,310),
        )
        val ret = MeetingRoomArrange.minMeetingRooms(intervals = input.toTypedArray())
        assertEquals(2, ret)
    }
}