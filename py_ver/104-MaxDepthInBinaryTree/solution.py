from typing import *
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        """
        求解一颗二叉树的最大深度，本质上我们需要做DFS
        我们采用递归的方式进行DFS即可，每次DFS，我们记录递归深度，等结束后，最大的递归深度，就是最大高度
        :param root:
        :return:
        """
        # 容错
        if root is None:
            return 0
        return self.dfs_search(tree_node=root, last_height=0)

    def dfs_search(self, tree_node: TreeNode, last_height: int) -> int:
        # 高度+1
        cur_height = last_height + 1
        print(f'cur_value -> {tree_node.val}, last_height->{last_height}')
        if tree_node.left is None and tree_node.right is None:
            # 遇到叶子节点了
            return cur_height

        # 开始左右递归，我们需要得到递归函数的值，这个值是当前递归部分的最大值，这个值会被一路传递上来
        if tree_node.left is not None:
            l_height = self.dfs_search(tree_node=tree_node.left, last_height=cur_height)
        else:
            l_height = -1

        if tree_node.right is not None:
            r_height = self.dfs_search(tree_node=tree_node.right, last_height=cur_height)
        else:
            r_height = -1

        return max(l_height, r_height)