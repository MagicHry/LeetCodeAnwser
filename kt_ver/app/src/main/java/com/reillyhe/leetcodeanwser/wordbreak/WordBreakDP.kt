package com.reillyhe.leetcodeanwser.wordbreak

object WordBreakDP {

    /**
     * 核心思路是通过DP解决
     * 我们设X = 从index=0开始的，长度为X的子字符串
     * F(x) = 这个字符串X是否可以完全由[wordDict]中的词组成
     * 那么有
     * F(x) = F(p) && wordDict.contains(substring(x-p))
     * 即F(x)是否为true
     * 需要考虑的是 x 之前的由字典词组成的子字符串  &&  剩余位置是否能被字典词击中
     */
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        //我们进行worddict的hashset化，这样可以加速查询效率
        val wordDictSet = HashSet<String>()
        for (word in wordDict) {
            wordDictSet.add(word)
        }
        //当前的输入字符如果直接击中某个词，可以直接返回
        if (wordDictSet.contains(s)) return true
        //DP初始化
        val dpLookupTable = Array<Boolean>(s.length+1) {
            false
        }
        //F(0)固定为true
        dpLookupTable[0] = true
        //开始计算DP
        for (x in 1..s.length) {
            //针对当前的x，找到p
            var resultForX = false
            for (p in 0.until(x)) {
                //f(p)必须要为true，即P这个子字符串需要是可以由dict单词组成的
                if (dpLookupTable[p]) {
                    //进一步查看 x-p 的情况
                    val substring = s.substring(p, x)
                    if (wordDict.contains(substring)) {
                        //找到了就可以退出p的循环
                        resultForX = true
                        break
                    }
                }
            }
            dpLookupTable[x] = resultForX
        }
        //最后返回F(s.length)的结果
        return dpLookupTable[s.length]
    }
}