package com.reillyhe.leetcodeanwser.tree

import java.util.*

object DFSTraversal {

    fun doDFS(root: TreeNode?): MutableList<Int>{
        val result = mutableListOf<Int>()
        root ?: return result
        val dfsStack: LinkedList<TreeNode> = LinkedList()
        dfsStack.push(root)
        while (dfsStack.isNotEmpty()) {
            //先取出元素，进行探查
            val curElement = dfsStack.pop()
            result.add(curElement.`val`)
            //先右后左
            curElement.right?.apply { dfsStack.push(this) }
            curElement.left?.apply { dfsStack.push(this) }
        }
        return result
    }
}