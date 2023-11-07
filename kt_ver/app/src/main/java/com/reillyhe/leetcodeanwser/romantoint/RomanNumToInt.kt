package com.reillyhe.leetcodeanwser.romantoint

import java.lang.StringBuilder

object RomanNumToInt {

    /**
     * 核心思路：
     * - 罗马数字一般都是小的在右侧，本质上应该是递减数列
     * - 但是有6中情况是特殊的
     * - 思路就是把这6中情况当初双个数的POJO，和单个数的POJO一起进来计算
     * - 通过这样的操作，这个数列又会是递减的
     */
    fun romanToInt(s: String): Int {
        val strBuilder = StringBuilder()
        val charLst = s.toCharArray()
        val numsLst = mutableListOf<Int>()
        //转换成降序的数字数组
        var cursor = 0
        while (cursor < charLst.size) {
            //首先尝试找6种特殊的case
            strBuilder.clear()
            val next = cursor+1
            if (next < charLst.size) {
                strBuilder.append(charLst[cursor].toString())
                strBuilder.append(charLst[next].toString())
                val phrase = strBuilder.toString()
                if (romanMap.containsKey(phrase)) {
                    //击中了
                    numsLst.add(romanMap[phrase]!!)
                    //那么一次性步过两个char
                    cursor = next+1
                    continue
                }
            }
            //如果没有被contine，那么加入单个
            numsLst.add(romanMap[charLst[cursor].toString()]!!)
            cursor++
        }
        //最后相加即可
        val ret = numsLst.fold(0) { total, current ->
             total+current
        }
        return ret
    }

    val I = 1
    val V = 5
    val X = 10
    val L = 50
    val C = 100
    val D = 500
    val M = 1000
    val IV = 4
    val IX = 9
    val XL = 40
    val XC = 90
    val CD = 400
    val CM = 900
    val romanMap = mapOf(
        "I" to I,
        "V" to V,
        "X" to X,
        "L" to L,
        "C" to C,
        "D" to D,
        "M" to M,
        "IV" to IV,
        "IX" to IX,
        "XL" to XL,
        "XC" to XC,
        "CD" to CD,
        "CM" to CM
    )
}