package com.reillyhe.leetcodeanwser.addtwonum

/**
 * 核心思路是双指针同步移动
 */
object AddTwoNum {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        //容错
        if (l1 == null || l2 == null) {
            return when {
                l1 == null -> l2
                l2 == null -> l1
                else -> null
            }
        }
        var ret: ListNode? = null
        var retCursor: ListNode? = null
        //是否发生了进位
        var digitIncreasing = false
        //指针
        var l1Cursor = l1
        var l2Cursor = l2
        //终止循环的条件，两个列表都到头了，且没有进位需要处理了
        while (!(l1Cursor == null && l2Cursor == null && !digitIncreasing)) {
            var l1V = l1Cursor?.`val`
            var l2V = l2Cursor?.`val`
            var rawAdding = when {
                l1V == null -> l2V
                l2V == null -> l1V
                else -> { l1V+l2V }
            }
            if (rawAdding == null) {
                //如果此时仍然为空，那么说明是两个list都没有了，但是在处理最后一次的进位
                rawAdding = 1
            } else if (digitIncreasing) {
                //上一次计算有进位，这里需要+1
                rawAdding += 1
            }
            val addingResult = rawAdding%10
            //判断本次相加是否要进位
            digitIncreasing = (addingResult != rawAdding)
            //添加结果
            if (ret == null) {
                ret = ListNode(addingResult)
                retCursor = ret
            } else {
                retCursor?.next = ListNode(addingResult)
                retCursor = retCursor?.next
            }
            //移动指针
            l1Cursor = l1Cursor?.next
            l2Cursor = l2Cursor?.next
        }
        return ret
    }
}