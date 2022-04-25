package com.reillyhe.leetcodeanwser.twosum

object TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val matcherSet = mutableSetOf<Int>()
        val anwser = IntArray(2)
        for (candidate: Int in nums) {
            val matcher = target - candidate
            if (candidate in matcherSet) {
                //自己就是matcher，说明匹配成功了
                val index1 = nums.indexOf(candidate)
                //对于相同数目的做特殊处理
                val index2 = if (candidate != matcher) {
                    nums.indexOf(matcher)
                } else {
                    nums.lastIndexOf(matcher)
                }
                //不能是同一个元素
                if (index1 != index2) {
                    anwser[0] = index1
                    anwser[1] = index2
                    return anwser
                }
            }
            //没找到就加入matcher集合
            //自己当然也要加入进去
            matcherSet.add(matcher)
        }
        return anwser
    }
}