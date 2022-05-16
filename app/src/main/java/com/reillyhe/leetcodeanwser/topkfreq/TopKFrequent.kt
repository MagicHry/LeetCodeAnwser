package com.reillyhe.leetcodeanwser.topkfreq

import java.util.*
import kotlin.collections.HashMap

/**
 * 要求：
 * 1.出现次数
 * 2.如果相同，则根据字母排序
 */
object TopKFrequent {
    /**
     * 核心思路是堆排序（优先队列实现）
     * Step1：一次循环生成String的POJO，因为要统计出现次数，同时要客制化Comparable
     * Step2：循环完毕后进入优先队列进行堆排序
     * Step3：优先队列向外喷出K个POJO即可
     */
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val freqMap = HashMap<String, Word>()
        val ret = mutableListOf<String>()
        //容错
        if (words.isEmpty()) return ret
        //Step1，统计freq，生成POJO
        for (plainWord in words) {
            if (freqMap.containsKey(plainWord)) {
                //已经有一样的word了，增加freq计数
                freqMap[plainWord]!!.freq++
            } else {
                //没有的话需要构造
                val word = Word(plainWord).also {
                    it.freq = 1
                }
                freqMap[plainWord] = word
            }
        }
        //Step2:进入优先队列
        val wordPriorityQueue = PriorityQueue<Word>()
        for (entry in freqMap) {
            wordPriorityQueue.offer(entry.value)
        }
        //Step3:prioryQueue只需要输出前K个Word即可
        var iterCnt = k
        while (iterCnt >= 1 && wordPriorityQueue.isNotEmpty()) {
            ret.add(wordPriorityQueue.poll()!!.wordInStr)
            iterCnt--
        }
        return ret
    }

    class Word(val wordInStr: String): Comparable<Word> {
        var freq: Int = 0
        override fun compareTo(other: Word): Int {
            return when {
                //出现次数不等，则比较出现次数
                freq != other.freq -> {
                    //因为使用的是优先队列，越频繁的越小
                    -1 * (freq - other.freq)
                }
                //出现次数相同，则比较字母顺序，字母顺序就是越小越前面
                else -> {
                    (wordInStr.compareTo(other.wordInStr))
                }
            }
        }
    }
}