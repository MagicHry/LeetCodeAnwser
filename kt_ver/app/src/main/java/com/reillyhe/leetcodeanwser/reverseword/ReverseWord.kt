package com.reillyhe.leetcodeanwser.reverseword

import java.lang.StringBuilder
import java.util.*

object ReverseWord {

    /**
     * 核心思路是：
     * - s -> charArray
     * - 利用strack来维护char的进栈顺序
     * - 识别white space进行弹栈操作
     */
    fun reverseWords(s: String): String {
        if (s.isBlank() || s.isEmpty()) return ""
        val strBuilder =  StringBuilder()
        val charLst = s.toCharArray()
        val wordStack = LinkedList<Char>()
        val whiteSpace = ' '
        for (charIndex in (s.length-1).downTo(0)) {
            val curChar = charLst[charIndex]
            if (curChar == whiteSpace) {
                //遇到white space需要尝试弹栈
                popStack(wordStack, strBuilder)
            } else {
                //遇到有效char需要入栈
                wordStack.push(curChar)
            }
        }
        //如果最后wordStack不为空，说明最后一个单词还没有弹出，那么我们要把它弹出
        if (wordStack.isNotEmpty()) {
            popStack(wordStack, strBuilder)
        }
        //最后由于首位不能有white space，我们需要移除尾部的白空格
        strBuilder.deleteCharAt(strBuilder.lastIndex)
        return strBuilder.toString()
    }

    private fun popStack(stack: LinkedList<Char>, strBuilder: StringBuilder) {
        if (stack.isEmpty()) return
        while (stack.isNotEmpty()) {
            strBuilder.append(stack.pop())
        }
        //最后加上一个white space
        strBuilder.append(' ')
    }
}