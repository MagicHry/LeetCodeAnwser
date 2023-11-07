package com.reillyhe.leetcodeanwser.labelpartition

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object LabelPartition {

    fun partitionLabels(s: String): List<Int> {
        val ret = ArrayList<Int>()
        //容错
        if (s.isEmpty()) return ret
        //第一步，形成针对26个字母的区间
        val inputChars = s.toCharArray()
        var charRangeContainer = HashMap<Char, CharOccRange>()
        inputChars.forEachIndexed { index, curChar ->
            if (!charRangeContainer.containsKey(curChar)) {
                val range = CharOccRange(curChar.toString())
                charRangeContainer[curChar] = range
            }
            val curCharRange = charRangeContainer[curChar]!!
            //更新出现次数和区间
            curCharRange.apply {
                occurence++
                markIndex(index)
            }
        }
        //第二步，迭代所有区间，尝试合并
        //首先要进行sort，这样方便加速后面的合并
        var sortedCharRange = ArrayList<CharOccRange>()
        charRangeContainer.forEach {
            sortedCharRange.add(it.value)
        }
        sortedCharRange.sortBy {
            it.startIndex
        }
        var curIndex = 0
        val strSize = s.length
        val tmpMergedLst = mutableListOf<CharOccRange>()
        while (curIndex < strSize) {
            //找到当前待处理range
            val curRange = sortedCharRange.firstOrNull { it.startIndex == curIndex } ?: return ret
            //向前递进
            val rangeIndex = sortedCharRange.indexOf(curRange)
            if (rangeIndex+1 < sortedCharRange.size) {
                //后面还有区间，尝试合并
                tmpMergedLst.clear()
                for (iterIndex in (rangeIndex+1).until(sortedCharRange.size)) {
                    val nextRange = sortedCharRange.get(iterIndex)
                    val sucMerge = curRange.tryMergeRange(nextRange)
                    if (!sucMerge) {
                        //无法合并，说明到头了，准备进行迭代一级循环
                        break
                    } else {
                        tmpMergedLst.add(nextRange)
                    }
                }
                //删除被合并的range
                for (range in tmpMergedLst) {
                    sortedCharRange.remove(range)
                }
                curIndex = curRange.endIndex+1
            } else {
                break
            }
        }
        for (range in sortedCharRange) {
            ret.add(range.endIndex - range.startIndex + 1)
        }
        return ret
    }

    class CharOccRange(var str: String): Comparable<CharOccRange> {
        var occurence: Int = 0
        var startIndex: Int = -1
        var endIndex: Int = -1
        //更新区间
        fun markIndex(index: Int) {
            if (startIndex < 0 || startIndex > index) startIndex = index
            if (endIndex < 0 || endIndex < index) endIndex = index
        }

        override fun compareTo(other: CharOccRange): Int {
            return when {
                startIndex == other.startIndex -> 0
                startIndex > other.startIndex -> 1
                else -> -1
            }
        }

        fun tryMergeRange(other: CharOccRange): Boolean {
            //由于char不同，因此不可能出现index相等的情况
            if (startIndex > other.endIndex) return false
            if (endIndex < other.startIndex) return false
            //其余情况我们需要合并
            startIndex = Math.min(startIndex, other.startIndex)
            endIndex = Math.max(endIndex, other.endIndex)
            str = str + "-" + other.str
            return true
        }
    }
}