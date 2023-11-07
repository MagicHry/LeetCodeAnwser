package com.reillyhe.leetcodeanwser.longestpalindrome

/**
 * 最长回文字段
 * 思路是滑动窗口
 */
object Palindrome {

    fun longestPalindrome(s: String): String {
        //容错
        if (s.isEmpty()) return ""
        val charLst = s.toCharArray()
        //滑动窗口初始化
        var windowSize = charLst.size
        while (windowSize >= 1) {
            //根据当前的滑动窗口大小查看是否形成回文字段
            val maxStartIndex = charLst.size - windowSize
            for (windowStartAt in 0..maxStartIndex) {
                if (isPalindromWindow(
                        windowStartIndex = windowStartAt,
                        windowEndIndex = windowStartAt+windowSize-1,
                        charLst = charLst)) {
                    return s.substring(windowStartAt, windowStartAt+windowSize)
                }
            }
            //每次循环完毕，如果没有退出循环，那么将滑动窗口size-1
            windowSize--
        }
        //如果退出了循环都还没有找到（实际上不可能，因为size=1的情况，isPalindromWindow，必定返回true）
        //但是还是进行一下兜底
        return s.substring(0,1)
    }

    /**
     * 检查当前window是否构成回文字符串
     */
    private fun isPalindromWindow(windowStartIndex:Int, windowEndIndex: Int, charLst: CharArray): Boolean {
        var start = windowStartIndex
        var end = windowEndIndex
        //相等的情况，就是奇数的回文字符
        while (start < end) {
            val charAtStart = charLst[start]
            val charAtEnd = charLst[end]
            //不相等，说明不对称了，那就无法构成回文字段
            if (charAtStart != charAtEnd) return false
            start++
            end--
        }
        //如果能退出循环，说明都是对称的，无论奇偶
        return true
    }
}