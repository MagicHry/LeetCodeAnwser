package com.reillyhe.leetcodeanwser.randomcp

class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null

    fun info(): String {
        val strBuilder = StringBuilder()
        val head = this
        strBuilder.append("[")
        var cursor: Node? = this
        while (cursor != null) {
            strBuilder.append("(")
            strBuilder.append("Value=${cursor.`val`},")
            if (cursor.random == null) {
                strBuilder.append("RandomIndex=null")
            } else {
                strBuilder.append("RandomIndex=${indexOf(head, cursor.random!!)}")
            }
            strBuilder.append("),")
            cursor = cursor.next
        }
        strBuilder.append("]")
        return strBuilder.toString()
    }

    fun indexOf(head:Node, node: Node): Int {
        var cursor: Node? = head
        var index = 0
        while (cursor != null) {
            if (cursor === node) {
                return index
            }
            cursor = cursor.next
            index++
        }
        return -1
    }
}