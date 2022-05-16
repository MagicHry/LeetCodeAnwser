package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.logreorder.LogReorder
import org.junit.Test
import org.junit.Assert.*
class LogReorderTest {
    @Test
    fun ez_test() {
        val input = mutableListOf<String>()
        input.add("dig1 8 1 5 1")
        input.add("let1 art can")
        input.add("dig2 3 6")
        input.add("let2 own kit dig")
        input.add("let3 art zero")
        val result = mutableListOf<String>(
            "let1 art can",
            "let3 art zero",
            "let2 own kit dig",
            "dig1 8 1 5 1",
            "dig2 3 6"
        )
        assertArrayEquals(result.toTypedArray(), LogReorder.reorderLogFiles(input.toTypedArray()))
    }

    @Test
    fun mid_test() {
        val input = mutableListOf<String>(
            "1 n u", "r 527", "j 893", "6 14", "6 82"
        )
        val result = mutableListOf<String>(
            "1 n u", "r 527", "j 893", "6 14", "6 82"
        )
        assertArrayEquals(result.toTypedArray(), LogReorder.reorderLogFiles(input.toTypedArray()))
    }

    @Test
    fun hard_test() {
        val input = mutableListOf<String>(
            "zoey i love you","lucas i love you","rong i love you"
        )
        val result = mutableListOf<String>(
            "lucas i love you","rong i love you","zoey i love you"
        )
        assertArrayEquals(result.toTypedArray(), LogReorder.reorderLogFiles(input.toTypedArray()))
    }
}