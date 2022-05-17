package com.reillyhe.leetcodeanwser.longestnonrepeat

object LongestNonRepeatBad {

    /**
     * 核心思路：
     *  - 我们可以通过一个滑动窗口来迭代可能的substring，滑动窗口由大变小，如果某个滑动窗口满足
     *  不重复的要求，那么我们就找到了结果
     *  - 我们可以设计一个中间数据结构（雷区），来标记输入文本中重复（>=2）的字符
     *  如果当前的滑动窗口覆盖了某一个char的雷区，那么这个滑动窗口是不合规的
     *
     *  问题：这个算法在超长字符串上表现不佳
     */
    fun lengthOfLongestSubstring(s: String): Int {
        //容错
        if (s.isEmpty()) return 0
        //Step1，我们需要考察s中重复出现字符的位置，组成雷区
        val charLst = s.toCharArray()
        val dangerZoom = HashMap<Char, MutableList<Int>>()
        charLst.forEachIndexed { index, currentChar ->
            if (!dangerZoom.containsKey(currentChar)) {
                //不存在则创建数组
                dangerZoom[currentChar] = mutableListOf()
            }
            dangerZoom[currentChar]!!.add(index)
        }
        //把只出现一次的元素剔除（因为某个元素只出现一次的话，永远不会造成窗口失败）
        val it = dangerZoom.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            if (entry.value.size <= 1) {
                it.remove()
            }
        }
        //Step2，利用滑动窗口，发现最大的完全不重复子字符串
        var slidingWindowLength = s.length
        while (slidingWindowLength >= 1) {
            //确定了当前迭代窗口的大小，接下来不断移动窗口
            for (windowStartAt in 0..(s.length-slidingWindowLength)) {
                var windowEndAt = windowStartAt + slidingWindowLength - 1
                //检查这个滑动窗口是否满足要求
                if (isSlidingWindowNonRepeat(dangerZoom, windowStartAt, windowEndAt, s)) {
                    //返回结果
                    return (windowEndAt-windowStartAt+1)
                }
            }
            //减少滑动窗口
            slidingWindowLength--
        }
        //如果始终找不到，那么返回1
        return 1
    }

    private fun isSlidingWindowNonRepeat(dangerZoom: HashMap<Char, MutableList<Int>>,
                                         startAt: Int,
                                         endAt: Int,
                                         input: String): Boolean {
        //查看这个slidingWindow有没有击中某个char的dangerZoom
        for (entry in dangerZoom) {
            val currentChar = entry.key
            val currentCharOCC = entry.value
            //击中1次不要紧，击中两次以上证明此slidingwindow不满足要求
            var hitCnt = 0
            for (occ in currentCharOCC) {
                if (occ in startAt..endAt) {
                    hitCnt++
                }
                if (hitCnt >= 2) return false
            }
        }
        return true
    }
}