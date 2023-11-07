package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.tree.BFSTraversal
import com.reillyhe.leetcodeanwser.tree.DFSTraversal
import com.reillyhe.leetcodeanwser.tree.TreeNode
import org.junit.Test
import org.junit.Assert.*

class TreeTraversalTest {
    @Test
    fun dfs_test() {
        val node1 = TreeNode(3)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(2)
        node1.left = node2
        node2.left = node3
        node2.right = node4
        val result = mutableListOf<Int>(3,3,4,2)
        assertEquals(result.toString(), DFSTraversal.doDFS(node1).toString())
    }

    @Test
    fun dfs_hard_test() {
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
        val result = mutableListOf<Int>(3,1,3,4,9,7,12)
        assertEquals(result.toString(), DFSTraversal.doDFS(node1).toString())
    }

    @Test
    fun bfs_test() {
        val node1 = TreeNode(3)
        val node2 = TreeNode(3)
        val node3 = TreeNode(4)
        val node4 = TreeNode(2)
        node1.left = node2
        node2.left = node3
        node2.right = node4
        val result = mutableListOf<Int>(3,3,4,2)
        assertEquals(result.toString(), BFSTraversal.doBFS(node1).toString())
    }

    @Test
    fun bfs_hard_test() {
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
        val result = mutableListOf<Int>(3,1,4,3,9,12,7)
        assertEquals(result.toString(), BFSTraversal.doBFS(node1).toString())
    }
}