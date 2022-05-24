package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.validbstree.TreeNode
import com.reillyhe.leetcodeanwser.validbstree.ValidBST
import com.reillyhe.leetcodeanwser.validbstree.ValidBSTTimeConsuming
import org.junit.Test
import org.junit.Assert.*
class ValidBSTTest {

    @Test
    fun ez_test1() {
        //[120,70,140,50,100,130,160,20,55,75,110,119,135,150,200]
        val root = TreeNode(8)
        val r1 = TreeNode(4)
        val r2 = TreeNode(16)
        val r3 = TreeNode(1)
        val r4 = TreeNode(5)
        val r5 = TreeNode(10)
        root.left = r1
        root.right = r2
        r1.left = r3
        r1.right = r4
        r2.left = r5
        val correct = true
        assertEquals(correct, ValidBST.isValidBST(root))
    }

    @Test
    fun ez_test2() {
        //[120,70,140,50,100,130,160,20,55,75,110,119,135,150,200]
        val root = TreeNode(8)
        val r1 = TreeNode(4)
        val r2 = TreeNode(16)
        val r3 = TreeNode(2)
        val r4 = TreeNode(5)
        val r5 = TreeNode(10)
        val r6 = TreeNode(20)
        val r7 = TreeNode(1)
        val r8 = TreeNode(6)
        root.left = r1
        root.right = r2
        r1.left = r3
        r1.right = r4
        r2.left = r5
        r2.right = r6
        r3.left = r7
        r3.right = r8
        val correct = false
        assertEquals(correct, ValidBST.isValidBST(root))
    }

    @Test
    fun ez_test3() {
        val root = TreeNode(-2147483648)
        val r1 = TreeNode(2147483647)
        root.left = null
        root.right = r1
        val correct = true
        assertEquals(correct, ValidBST.isValidBST(root))
    }

    //
    //[-2147483648,null,2147483647]
}