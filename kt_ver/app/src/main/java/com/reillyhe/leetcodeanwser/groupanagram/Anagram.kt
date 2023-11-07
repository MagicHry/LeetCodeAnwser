package com.reillyhe.leetcodeanwser.groupanagram

object Anagram {

    /**
     * anagram的定义是，两个string，内部chars必须完全一致，但是顺序可以不一致
     * 所以我们的核心思路是，根据string内部的char生成一个经过排序的新string
     * 根据新string的值，我们就知道哪些是anagram哪些不是了
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val ret = mutableListOf<List<String>>()
        //容错
        if (strs.isEmpty()) return ret
        val anagramMap = HashMap<String, MutableList<String>>()
        for (originalStr in strs) {
            val charLst = originalStr.toCharArray()
            //将字符串按照char ASCII 编码排序，重新生成字符串，作为key
            val sortedLst = charLst.sortedBy {
                it.toInt()
            }.toCharArray()
            val sortedStr = String(sortedLst)
            //尝试加入和当前anagramMap做对比
            if (anagramMap.containsKey(sortedStr)) {
                anagramMap[sortedStr]!!.add(originalStr)
            } else {
                //没有击中就新增一个
                val newLst = mutableListOf<String>()
                newLst.add(originalStr)
                anagramMap[sortedStr] = newLst
            }
        }
        for (entry in anagramMap) {
            ret.add(entry.value)
        }
        return ret
    }
}