package com.reillyhe.leetcodeanwser.mergetwolist

object MergeTwoArray {

    /**
     * 核心思路是双指针
     * 比较简单
     */
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        //容错
        if (n == 0) return
        //拷贝nums1
        val nums1Cp = nums1.copyOfRange(0, nums1.size)
        var leftCursor = 0
        var rightCursor = 0
        //双指针循环
        var retIndex  = 0
        while (leftCursor < m || rightCursor < n) {
            val curLeft = when {
                leftCursor < m -> nums1Cp[leftCursor]
                else -> Int.MAX_VALUE
            }
            val curRight = when {
                rightCursor < n -> nums2[rightCursor]
                else -> Int.MAX_VALUE
            }
            if (curLeft <= curRight) {
                //放置左侧
                nums1[retIndex] = curLeft
                leftCursor++
            } else {
                //放置右侧
                nums1[retIndex] = curRight
                rightCursor++
            }
            retIndex++
        }
    }
}