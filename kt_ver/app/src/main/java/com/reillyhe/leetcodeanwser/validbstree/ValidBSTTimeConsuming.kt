package com.reillyhe.leetcodeanwser.validbstree

import java.util.*
import kotlin.collections.HashMap

object ValidBSTTimeConsuming {

    /**
     * 核心思路：
     * - 一个特殊的BFS
     * - 我们在进行BFS的时候，需要确保我们的child保持bst的特性
     * - 同时，针对每一个child，我们需要找到其Zig-Zag Parent进行验证
     */
    fun isValidBST(root: TreeNode?): Boolean {
        //容错
        if (root == null || (root.left == null && root.right == null)) {
            return true
        }
        //初始化
        val searchParent = HashMap<Int, Pair<TreeNode, ChildPos>>()
        val bfsQueue = LinkedList<TreeNode>()
        bfsQueue.offer(root)
        //开始进行BFS
        while (bfsQueue.isNotEmpty()) {
            val curParent = bfsQueue.poll()!!
            val leftChild = curParent.left
            val rightChild = curParent.right
            //规则1：leftChild < parent < rightChild
            val leftCheck = leftChild?.run {
                `val` < curParent.`val`
            } ?: true
            val rightCheck = rightChild?.run {
                `val` > curParent.`val`
            } ?: true
            //如果规则1不通过，那么可以直接判定为false
            if (!(leftCheck && rightCheck)) {
                return false
            }
            //规则2：ZigZag检测
            val leftZigZagCheck = leftChild?.run {
                zigzagCheck(this, curParent, ChildPos.LEFT, searchParent)
            } ?: true
            val rightZigZagCheck = rightChild?.run {
                zigzagCheck(this, curParent, ChildPos.RIGHT, searchParent)
            } ?: true
            //规则2检测不通过，判定为false
            if (!(leftZigZagCheck && rightZigZagCheck)) {
                return false
            }
            //补充parentinfo，入列，继续迭代
            leftChild?.apply {
                searchParent[hashCode()] = Pair(curParent, ChildPos.LEFT)
                bfsQueue.offer(this)
            }
            rightChild?.apply {
                searchParent[hashCode()] = Pair(curParent, ChildPos.RIGHT)
                bfsQueue.offer(this)
            }
        }
        //bfs结束了都没有跳出，那么返回true
        return true
    }

    private fun zigzagCheck(child: TreeNode,
                            parent: TreeNode,
                            childPos: ChildPos,
                            searcher: HashMap<Int, Pair<TreeNode, ChildPos>>): Boolean {
        //同方向一直寻找，直到预见拐点
        var curPos = childPos
        var targetParent: TreeNode = parent
        while (curPos == childPos) {
            //搜索父亲
            if (!searcher.containsKey(targetParent.hashCode())) {
                //找不到了，那么说明没有zigzag，直接通过测试
                return true
            }
            val info = searcher[targetParent.hashCode()]!!
            targetParent = info.first
            curPos = info.second
        }
        return when (childPos) {
            ChildPos.LEFT -> {
                //右拐
                child.`val` > targetParent.`val`
            }
            ChildPos.RIGHT -> {
                //左拐
                child.`val` < targetParent.`val`
            }
        }
    }

    enum class ChildPos {
        LEFT, RIGHT
    }
}