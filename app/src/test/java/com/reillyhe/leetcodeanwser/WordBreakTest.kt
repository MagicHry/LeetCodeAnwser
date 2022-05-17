package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.wordbreak.WordBreakBadSolution
import com.reillyhe.leetcodeanwser.wordbreak.WordBreakDP
import org.junit.Test
import org.junit.Assert.*
class WordBreakTest {

    @Test
    fun ez_test() {
        val word = mutableListOf<String>(
            "leet","code"
        )
        val ret = WordBreakDP.wordBreak("leetcode", word)
        assertEquals(true, ret)
    }

    @Test
    fun mid_test() {
        val word = mutableListOf<String>(
            "cat","sand","dogee","cats","an"
        )
        val ret = WordBreakDP.wordBreak("catsandogee", word)
        assertEquals(true, ret)
    }

    @Test
    fun mid_test2() {
        //"acccbccb"
        //["cc","bc","ac","ca"]
        val word = mutableListOf<String>(
            "cc","bc","ac","ca"
        )
        val ret = WordBreakDP.wordBreak("acccbccb", word)
        assertEquals(false, ret)
    }

    @Test
    fun hard_test() {
        val word = mutableListOf<String>(
            "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"
        )
        val ret = WordBreakDP.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", word)
        assertEquals(false, ret)
    }
}