package com.reillyhe.leetcodeanwser.intervalmerge

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.Comparator

object IntervalMerge {
    /**
     * 核心思路是优先队列
     * Step1:将区间转换成POJP
     * Step2:区间入优先队列
     * Step3:迭代优先队列，保证永远都是最小区间和最小区间合并
     */
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val retInterval = mutableListOf<Interval>()
        //容错
        if (intervals.isEmpty()) {
            emptyArray<IntArray>()
        }
        val priorityQueue: PriorityQueue<Interval> = PriorityQueue(intervals.size)
        for (interval in intervals) {
            val startIndex = interval[0]
            val endIndex = interval[1]
            //Step1：区间转换为可排序POJO
            val intervalPojo = Interval(startIndex, endIndex)
            //Step2：区间投入优先队列中
            priorityQueue.offer(intervalPojo)
        }
        //Step3，合并
        while (priorityQueue.isNotEmpty()) {
            val curInterval = priorityQueue.poll()!!
            //永远和结果集中的最后一个区间合并（再之前的都是无法合并的）
            val lastIntervalInRet = when {
                retInterval.isEmpty() -> null
                else -> retInterval[retInterval.size-1]
            }
            if (lastIntervalInRet == null) {
                //初始化的case
                retInterval.add(curInterval)
            } else {
                val merged = lastIntervalInRet.mergeInterval(curInterval)
                if (merged) {
                    //成功合并，直接找下一个
                    continue
                } else {
                    //合并失败，则当前这个区间进入结果集
                    retInterval.add(curInterval)
                }
            }
        }
        val ret = mutableListOf<IntArray>()
        for (interval in retInterval) {
            ret.add(interval.toIntArray())
        }
        return ret.toTypedArray()
    }

    class Interval(var startIndex: Int, var endIndex: Int): Comparable<Interval> {

        /**
         * 尝试和另外一个区间进行合并
         */
        fun mergeInterval(other: Interval): Boolean {
            if (endIndex < other.startIndex) return false
            if (startIndex > other.endIndex) return false
            //合并
            startIndex = Math.min(startIndex, other.startIndex)
            endIndex = Math.max(endIndex, other.endIndex)
            return true
        }

        fun toIntArray(): IntArray {
            return intArrayOf(startIndex, endIndex)
        }

        override fun compareTo(other: Interval): Int {
            return when {
                //如果开始位置不相等，对比开始位置
                startIndex != other.startIndex -> {
                    startIndex - other.startIndex
                }
                //如果开始位置相等，则对比终止位置
                else -> {
                    endIndex - other.endIndex
                }
            }
        }
    }
}