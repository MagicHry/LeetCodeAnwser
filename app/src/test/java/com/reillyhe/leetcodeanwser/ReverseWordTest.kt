package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.reverseword.ReverseWord
import org.junit.Test
import org.junit.Assert.*
class ReverseWordTest {

    @Test
    fun ez_test() {
        val input = "the sky is blue"
        val correct = "blue is sky the"
        val ret = ReverseWord.reverseWords(input)
        assertEquals(correct, ret)
    }
}