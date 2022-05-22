package com.reillyhe.leetcodeanwser.atoi

import java.util.*

object ATOI {

    /**
     * 核心思路
     *  - 利用white psace，和其他非digit 字符作为分割点
     *  - 利用queue保存digit字符数据
     *  - 满足分割条件后退出，然后弹队列生成数据
     */
    fun myAtoi(s: String): Int {
        //容错
        if (s.isEmpty() || s.isBlank()) return 0
        //初始化
        //移除先导空格
        val trimed = s.trimStart()
        val charLst = trimed.toCharArray()
        //是否定义好了符号
        var signed: Int? = null
        //是否已经开始准备寻找数字
        var isLookingForDigit = false
        val digitQueue = LinkedList<Int>()
        // Step1，构造数字队列，以及符号
        for (curCharIndex in 0.until(charLst.size)) {
            val curChar = charLst[curCharIndex]
            //判断是否是数字
            if (curChar.isDigit()) {
                isLookingForDigit = true
                digitQueue.offer(Integer.parseInt(curChar.toString()))
            } else if (isSignedSymbol(curChar) != null) {
                //遇到了正负号，这里要分情况讨论
                //具体情况被两种规则决定
                //1.我们是否signed过
                //2.我们是否已经遇到了digit
                //只有在没有遇到digit，且为signed的情况下，我们需要记录
                if (!isLookingForDigit && signed == null) {
                    signed = isSignedSymbol(curChar)!!
                    //找到符号，也需要开始寻找digit
                    isLookingForDigit = true
                } else {
                    //其余情况都需要break了（因为一旦找到有效正负号，下一个char必须要开始寻找digit）
                    break
                }
            } else {
                //其他字符，直接弹队列
//                //case1，没有开始寻找digit，那么我们开始寻找
//                if (!isLookingForDigit) {
//                    isLookingForDigit = true
//                    continue
//                }
//                //case2，遇到了digit，那么准备弹队列
                break
            }
        }
        // step2，组装数字
        var result = 0L
        val signedSymbol = signed ?: 1
        while (digitQueue.isNotEmpty()) {
            var curDigit = digitQueue.poll()
            //进位
            val add = Math.pow(10.toDouble(), digitQueue.size.toDouble()).toLong()
            //Long也有可能超出
            if (add >= Long.MAX_VALUE && curDigit != 0 || ((curDigit * add) + result) < result) {
                if (signedSymbol > 0) {
                    return Int.MAX_VALUE
                }else {
                    return Int.MIN_VALUE
                }
            }
            result += curDigit * add
        }
        //添加上符号
        result = signed?.run {
            result * this
        } ?: result
        //step3 判断范围
        result = Math.max(result, Int.MIN_VALUE.toLong())
        result = Math.min(result, Int.MAX_VALUE.toLong())
        return result.toInt()
    }

    private fun isSignedSymbol(char: Char): Int? {
        return when (char) {
            '-' -> -1
            '+' -> 1
            else -> null
        }
    }
}