package com.reillyhe.leetcodeanwser.tree

import java.util.*

object BFSTraversal {

    fun doBFS(root: TreeNode?): MutableList<Int> {
        val result = mutableListOf<Int>()
        root ?: return result
        val bfsQueue = LinkedList<TreeNode>()
        bfsQueue.offer(root)
        while (bfsQueue.isNotEmpty()) {
            //先探查队列第一个元素
            val curElement = bfsQueue.poll() ?: continue
            result.add(curElement.`val`)
            //再从左至右放入元素至队列
            curElement.left?.apply { bfsQueue.offer(this) }
            curElement.right?.apply { bfsQueue.offer(this) }
        }
        return result
    }
}