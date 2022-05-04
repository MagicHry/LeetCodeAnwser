package com.reillyhe.leetcodeanwser.tree

import java.util.*
import kotlin.collections.HashMap

object GoodNodes {
    // 返回的是数目
    fun goodNodes(root: TreeNode?): Int {
        return goodNodesImp(root).size
    }

    private fun goodNodesImp(root: TreeNode?): MutableList<Int> {
        val result = mutableListOf<Int>()
        root ?: return result
        val parentMaxMap: HashMap<Int, Int> = HashMap()
        //数据初始化，先填入root的数据
        val dfsStack = LinkedList<TreeNode>()
        dfsStack.push(root)
        parentMaxMap[root.hashCode()] = root.`val`
        result.add(root.`val`)
        while (dfsStack.isNotEmpty()) {
            val curNode = dfsStack.pop()
            //当前的元素不需要探测了，是不是good nodes在入栈的时候已经决定好了
            val parentMax = parentMaxMap[curNode.hashCode()]!!
            //dfs - 顺序先右后左
            curNode.right?.apply {
                //本条链路上的parentMax
                var newParentMax = parentMax
                if (`val` >= parentMax) {
                    //判断其是否是good nodes
                    result.add(`val`)
                    newParentMax = `val`
                }
                //不论是否是good,都需要记录
                parentMaxMap[hashCode()] = newParentMax
                dfsStack.push(this)
            }

            curNode.left?.apply {
                //本条链路上的parentMax
                var newParentMax = parentMax
                if (`val` >= parentMax) {
                    //判断其是否是good nodes
                    result.add(`val`)
                    newParentMax = `val`
                }
                //不论是否是good,都需要记录
                parentMaxMap[hashCode()] = newParentMax
                dfsStack.push(this)
            }
        }
        return result
    }
}