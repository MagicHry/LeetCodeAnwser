package com.reillyhe.leetcodeanwser.validbstree

object ValidBST {

    /**
     * 核心思路：
     *  - 按照BST的定义 左子树 < root < 右子数
     *  - 因此我们只需要进行一次中序遍历即可
     */
    fun isValidBST(root: TreeNode?): Boolean {
        //容错
        if (root == null || (root.left == null && root.right == null)) {
            return true
        }
        //初始化
        val touchedValue = ArrayList<Int>()
        //中序遍历
        return traversalInMidOrder(root, touchedValue)
    }

    private fun addByCheck(value: Int, traversalLst: ArrayList<Int>): Boolean {
        //加入前检查上一个元素的值
        val lastValue = when {
            traversalLst.isEmpty() -> null
            else -> traversalLst[traversalLst.size-1]
        }
        val ret = when {
            lastValue == null -> true
            value > lastValue -> true
            else -> false
        }
        traversalLst.add(value)
        return ret
    }

    private fun traversalInMidOrder(node: TreeNode,
                                    traversalLst: ArrayList<Int>): Boolean {
        //如果是叶子节点，无需递归
        if (node.left == null && node.right == null) {
            return addByCheck(node.`val`, traversalLst)
        }
        val leftChild = node.left
        val rightChild = node.right
        //中序遍历
        val left = leftChild?.run {
            traversalInMidOrder(this, traversalLst)
        } ?: true
        val root = addByCheck(node.`val`, traversalLst)
        val right = rightChild?.run {
            traversalInMidOrder(this, traversalLst)
        } ?: true
        return left && root && right
    }
}