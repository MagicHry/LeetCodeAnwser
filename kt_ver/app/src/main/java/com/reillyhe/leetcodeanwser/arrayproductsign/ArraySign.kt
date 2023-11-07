package com.reillyhe.leetcodeanwser.arrayproductsign

object ArraySign {

    /**
     * 非常简单的题目
     * 遍历一遍就行
     * 针对0做特殊处理
     */
    fun arraySign(nums: IntArray): Int {
        //容错
        if (nums.isEmpty()) return 0
        var curSign = 1
        for (curNum in nums) {
            if (curNum == 0) return 0
            when {
                curNum > 0 -> curSign *= 1
                curNum < 0 -> curSign *= -1
            }
        }
        return curSign
    }
}