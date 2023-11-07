package com.reillyhe.leetcodeanwser.addtwonum

import java.lang.StringBuilder

class ListNode(var `val`: Int) {
    var next: ListNode? = null
    fun showResult(): String {
        val strBuilder = StringBuilder()
        strBuilder.append(`val`)
        var cursor = next
        while (cursor != null) {
            strBuilder.append(cursor.`val`)
            cursor = cursor.next
        }
        return strBuilder.toString()
    }
}