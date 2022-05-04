package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.tree.GoodNodes
import com.reillyhe.leetcodeanwser.tree.TreeNode
import org.junit.Test
import org.junit.Assert.*
class GoodNodesTest {

    @Test
    fun good_nodes_normal_test() {
        val node1 = TreeNode(3)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(2)
        node1.left = node2
        node2.left = node3
        node2.right = node4
        val result = mutableListOf(3,3,4)
        assertEquals(result.size, GoodNodes.goodNodes(node1))
    }

    @Test
    fun good_nodes_hard_test() {
        val node1 = TreeNode(3)
        val node2 = TreeNode(1)
        val node3 = TreeNode(4)
        val node4 = TreeNode(3)
        val node5 = TreeNode(9)
        val node6 = TreeNode(12)
        val node7 = TreeNode(7)
        node1.left = node2
        node1.right = node3
        node2.left = node4
        node3.left = node5
        node3.right = node6
        node5.right = node7
        val result = mutableListOf(3,3,4,9,12)
        assertEquals(result.size, GoodNodes.goodNodes(node1))
    }

    @Test
    fun good_nodes_extreme_test() {
        val node1 = TreeNode(3)
        val actual = GoodNodes.goodNodes(node1)
        assertEquals(1, actual)
        assertEquals(0, GoodNodes.goodNodes(null))
    }

    @Test
    fun good_nodes_allthesame_test() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(1)
        val node3 = TreeNode(1)
        val node4 = TreeNode(1)
        node1.left = node2
        node2.left = node3
        node2.right = node4
        assertEquals(4, GoodNodes.goodNodes(node1))
    }
}