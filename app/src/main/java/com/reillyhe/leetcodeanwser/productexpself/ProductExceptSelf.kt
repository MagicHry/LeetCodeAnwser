package com.reillyhe.leetcodeanwser.productexpself

object ProductExceptSelf {

    /**
     * 核心思路是针对输入数组，求解
     *  - 前缀积
     *  - 后缀积
     * 那么针对题目的要求，实际上输出数组的某个位置i的值就等于
     * PreFix[i] * SuFix[i]
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        //容错
        if (nums.isEmpty()) return IntArray(0)
        //Step1：初始化前缀，后缀，结果数组
        val prefix = IntArray(nums.size)
        val sufix = IntArray(nums.size)
        val ret = IntArray(nums.size)
        //Step2:生成前缀积数组和后缀积数组
        //前缀计算指针，从左-》右
        var preFixCursor = 0
        //后缀计算指针，从右-》左
        var suFixCursor = nums.size-1
        while (preFixCursor < nums.size && suFixCursor >= 0) {
            //前缀积(上一个位置的前缀积 * 上一个位置的值)
            val lastPrefixValue = when {
                preFixCursor-1 < 0 -> 1
                else -> prefix[preFixCursor-1]
            }
            val lastValueForPrefix = when {
                preFixCursor-1 < 0 -> 1
                else -> nums[preFixCursor-1]
            }
            prefix[preFixCursor] = lastPrefixValue * lastValueForPrefix
            preFixCursor++
            //后缀积(上一个位置的后缀积 * 上一个位置的值)
            val lastSufixValue = when {
                suFixCursor+1 >= nums.size -> 1
                else -> sufix[suFixCursor+1]
            }
            val lastValueForSuFix = when {
                suFixCursor+1 >= nums.size -> 1
                else -> nums[suFixCursor+1]
            }
            sufix[suFixCursor] = lastSufixValue * lastValueForSuFix
            suFixCursor--
        }
        //生成结果
        for (index in 0.until(nums.size)) {
            ret[index] = sufix[index] * prefix[index]
        }
        return ret
    }
}