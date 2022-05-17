package com.reillyhe.leetcodeanwser.wordbreak

object WordBreakBadSolution {

    /**
     * 核心思路是：我们不需要一步一步的进行迭代，我们可以以击中字典的substr为步长进行迭代
     * 同时我们可以改造wordDict，利用索引进行二次加速
     * Step1:改造[wordDict]，由于dict内部元素都是不相同的，我们可以用哈希图来承载
     * Step2:进一步的，我们可以给哈希图生成索引，以word的首字母为索引key，这样可以加速后续迭代
     * Step3:我们可以递归的利用字典中的匹配substr进行拆分，如果拆分到最后把整个字符串拆完了，我们就得到了想要的结果
     */
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        //针对false情况的加速
        val charOccInDict = hashSetOf<Char>()
        for (word in wordDict) {
            for (char in word.toCharArray()) {
                charOccInDict.add(char)
            }
        }
        for (charInOrigin in s.toCharArray()) {
            if (!charOccInDict.contains(charInOrigin)) return false
        }
        //Step1,2，生成hashMapDict，以及他的索引
        val wordDictMap = HashMap<Char, HashSet<String>>(wordDict.size)
        for (word in wordDict) {
            val firstLetter = word[0]
            if (!wordDictMap.containsKey(firstLetter)) {
                wordDictMap[firstLetter] = hashSetOf()
            }
            wordDictMap[firstLetter]!!.add(word)
        }
        //step3，利用wordDictMap进行递归
        return breakWordIntoDict(s,0, wordDictMap)
    }

    private fun breakWordIntoDict(originalWord: String, startAt: Int, dict: HashMap<Char, HashSet<String>>): Boolean {
        val startLetter = originalWord[startAt]
        //如果当前这个开始字母无法匹配索引，直接失败
        if (!dict.containsKey(startLetter)) {
            return false
        }
        //这个索引对应的所有dict word，我们需要看一看是否有match的
        val matchedDictWord = mutableListOf<String>()
        for (dictWord in dict[startLetter]!!) {
            val endAt = startAt+dictWord.length
            if (endAt > originalWord.length) {
                //超出了，这个词不考虑
                continue
            }
            val subStr = originalWord.substring(startAt, endAt)
            //相同，说明击中了字典中的文字
            if (subStr == dictWord) {
                matchedDictWord.add(dictWord)
                //进一步判断，如果这个时候已经到了输入string的结尾了，那我们找到了最后的结果
                if (endAt == originalWord.length) {
                    return true
                }
            }
        }
        //如果为空就不需要继续考虑了
        if (matchedDictWord.isEmpty()) {
            return false
        }
        //针对结果集，递归遍历子字符串
        for (matchedWord in matchedDictWord) {
            if(breakWordIntoDict(originalWord, startAt+matchedWord.length, dict)) {
                //如果返回true了就终止递归
                return true
            }
        }
        //整个子链路遍历完毕都是false，那么只能返回false
        return false
    }
}