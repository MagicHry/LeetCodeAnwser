package com.reillyhe.leetcodeanwser.rotatesortedarray

object RotateSearchArray {

    /**
     * 核心思路：
     *  - 依然是二分法，只不过我们二分出来，有一个array是有可能包括旋转点K的，这就导致有一个
     *  子数组不是排序的
     *  - 但是我们可以通过另外一个排序的数组（二分出两个，最多只有一个数组是没有sorted）
     *  确定target到底在哪
     *  - 按照上面那点的逻辑，选取到正确的数组
     */
    fun search(nums: IntArray, target: Int): Int {
        //容错
        if (nums.isEmpty()) return -1
        //二分搜索
        return biSearch(0, nums.size-1, nums, target)
    }

    private fun biSearch(startAt: Int, endAt: Int, nums: IntArray, target: Int): Int {
        //只有一个的情况，直接对比结果
        if (startAt == endAt) {
            return when (target) {
                nums[startAt] -> startAt
                else -> -1
            }
        }
        //进行二分
        val piviot = (endAt - startAt + 1) / 2 + startAt
        if (nums[piviot] == target) {
//            println("hit piviot at -> $piviot")
            return piviot
        }
        val leftStart = startAt
        val leftEnd = piviot-1
        val rightStart = Math.min(piviot+1, endAt)
        val rightEnd = endAt
        //这里实际上，两个区间都有可能是sorted（如果piviot = k）
        //找到sorted的区间（k不在的区间）
        if (nums[leftEnd] >= nums[leftStart]) {
            //左侧的是一个sorted区间，那么判断值，是否在左侧区间中
            if (target <= nums[leftEnd] && target >= nums[leftStart]) {
                //这个时候继续二分左侧区间
//                println("($startAt-$endAt) and try to bisearch at sub range ($leftStart-$leftEnd)")
                return biSearch(leftStart, leftEnd, nums, target)
            } else {
                //不在，则二分右侧区间
//                println("($startAt-$endAt) and try to bisearch at sub range ($rightStart-$rightEnd)")
                return biSearch(rightStart, rightEnd, nums, target)
            }
        } else {
            //右侧是一个sorted区间，同样判断target是否在区间内
            if (target <= nums[rightEnd] && target >= nums[rightStart]) {
//                println("($startAt-$endAt) and try to bisearch at sub range ($rightStart-$rightEnd)")
                return biSearch(rightStart, rightEnd, nums, target)
            } else {
//                println("($startAt-$endAt) and try to bisearch at sub range ($leftStart-$leftEnd)")
                return biSearch(leftStart, leftEnd, nums, target)
            }
        }
    }
}