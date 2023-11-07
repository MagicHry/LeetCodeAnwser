package com.reillyhe.leetcodeanwser.longestnonrepeat

object LongestNonRepeat {

    /**
     * 核心思路，动态规划
     * - 有X=长度为X的subtring
     * - 那么F(X)=长度为X的不重复subtring的start/end index
     * - 那么F(X)=F(P)+Adjacent(P)，其中P+1 = X
     *   F(P)为比X小1的不重复subtring，
     *   Adjacent(P)为p这个subtring的前一个char或者后一个char
     * 我们迭代到某个F(X)没有办法生成数据
     * 那么结果就是X-1
     */
    fun lengthOfLongestSubstring(s: String): Int {
        //容错
        if (s.isEmpty()) return 0
        if (s.isBlank()) return 1
        if (s.length==1) return 1
        val charLst = s.toCharArray()
        //DP初始化（X=1我们将其初始化完毕再进行迭代）
        val dpLookUpTable = HashMap<Int, HashSet<SubString>>()
        val initTable = HashSet<SubString>(s.length)
        charLst.forEachIndexed { index, char ->
            val sub = SubString()
            sub.apply {
                startAt = index
                endAt = index
                subString = char.toString()
            }
            initTable.add(sub)
        }
        var validX = 1
        dpLookUpTable[1] = initTable
        for (x in 2..s.length) {
            val lastStepResult = dpLookUpTable[x-1]
            if (lastStepResult.isNullOrEmpty()) {
                //已经没有p来配对x了，我们到达了终点
                return validX
            }
            //遍历上一步的结果
            val currentStepResult = HashSet<SubString>()
            for (p in lastStepResult) {
                //由于左侧其他的p来保证，我们保证右侧即可
                val rightChar = when {
                    p.endAt+1 >= charLst.size -> null
                    else -> charLst[p.endAt+1]
                }
                val addRightSuc = rightChar?.run {
                    p.add(this)
                } ?: false
                //如果无法添加，说明要么击中边界，要么重复了，这个p无法组成当前的x
                if (addRightSuc) {
                    currentStepResult.add(p)
                }
            }
            //当前步骤完成
            dpLookUpTable[x] = currentStepResult
            if (currentStepResult.isNotEmpty()) {
                validX++
            }
        }
        //如果走到循环结束都没有退出，那么说明整个字符串都是符合的
        return validX
    }

    class SubString {
        //相对于输入string而言
        var startAt = -1
        var endAt = -1
        var subString: String = ""

        override fun hashCode(): Int {
            return "$startAt-$endAt".hashCode()
        }

        fun copy(): SubString {
            val copy = SubString()
            copy.let {
                it.startAt = startAt
                it.endAt = endAt
            }
            return copy
        }

        /**
         * 添加相邻的char
         * 只需要考虑右侧即可
         */
        fun add(addChar: Char): Boolean {
            if (subString.contains(addChar)) return false
            //更新subtring位置
            endAt++
            subString += addChar
            return true
        }
    }
}