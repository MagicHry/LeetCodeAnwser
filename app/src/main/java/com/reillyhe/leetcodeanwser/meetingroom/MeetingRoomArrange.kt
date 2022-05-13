package com.reillyhe.leetcodeanwser.meetingroom

import java.util.*
import kotlin.Comparator
import kotlin.math.max

object MeetingRoomArrange {

    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        //容错
        if (intervals.isEmpty()) return 0
        //第一步，首先是进行排序，按照会议的开始和终止时间
        intervals.sortWith(object : Comparator<IntArray> {
            override fun compare(o1: IntArray?, o2: IntArray?): Int {
                if (o1 == null || o2 == null) return 0
                return when {
                    o1[0] < o2[0] -> {
                        -1
                    }
                    o1[0] > o2[0] -> {
                        1
                    }
                    else -> {
                        //开始时间相等，判断结束时间
                        when {
                            o1[1] == o2[1] -> {
                                0
                            }
                            o1[1] < o2[1] -> {
                                -1
                            }
                            else -> {
                                1
                            }
                        }
                    }
                }
            }
        })

        //第二步，根据已经排序了的会议室，进行房间的分配
        val meetingRooms = PriorityQueue<MeetingRoom>()
        for (curMeetingReq in intervals) {
            //初始化
            if (meetingRooms.isEmpty()) {
                val firstRoom = MeetingRoom().also {
                    it.bookMeeting(curMeetingReq)
                }
                meetingRooms.offer(firstRoom)
                continue
            }
            //正常情况
            //拿到当前最先结束会议的会议室
            val nearestRoom = meetingRooms.peek()!!
            if (nearestRoom.allMeetingEndAt > curMeetingReq[0]) {
                //最近结束的会议，仍然超过了本次会议的开始时间，那么我们需要一个新的会议室
                val newRoom = MeetingRoom().also { it.bookMeeting(curMeetingReq) }
                meetingRooms.offer(newRoom)
            } else {
                //那么可以在这个会议室开会
                meetingRooms.poll()
                nearestRoom.bookMeeting(curMeetingReq)
                //重新入列，这会造成重排序
                meetingRooms.offer(nearestRoom)
            }
        }
        return meetingRooms.size
    }

    class MeetingRoom(var allMeetingEndAt: Int = -1): Comparable<MeetingRoom> {

        fun bookMeeting(meetingReq: IntArray) {
            allMeetingEndAt = Math.max(allMeetingEndAt, meetingReq[1])
        }

        override fun compareTo(other: MeetingRoom): Int {
            return when {
                allMeetingEndAt == other.allMeetingEndAt -> 0
                allMeetingEndAt < other.allMeetingEndAt -> -1
                else -> 1
            }
        }
    }
}

