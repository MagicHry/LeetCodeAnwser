package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.longestpalindrome.Palindrome
import org.junit.Test
import org.junit.Assert.*
class LongestPalindromTest {

    @Test
    fun substr_test() {
        val str = "abc"
        assertEquals("ab", str.substring(0,2))
    }

    @Test
    fun longest_palindrom_ez_test() {
        val input = "babad"
        val ret = Palindrome.longestPalindrome(input)
        assertEquals("bab", ret)
    }

    @Test
    fun longest_palindrom_mid_test() {
        val input = "ac1aefeaghj62"
        val ret = Palindrome.longestPalindrome(input)
        assertEquals("aefea", ret)
    }

    @Test
    fun longest_palindrom_hard_test() {
        val input = "ae2aeffeaghj62"
        val ret = Palindrome.longestPalindrome(input)
        assertEquals("aeffea", ret)
    }

    @Test
    fun longest_palindrom_extreme_test() {
        val input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        val ret = Palindrome.longestPalindrome(input)
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaa", ret)
    }

    @Test
    fun longest_palindrom_extreme_test2() {
        val input = "abcdefghijklmno123798"
        val ret = Palindrome.longestPalindrome(input)
        assertEquals("a", ret)
    }

}