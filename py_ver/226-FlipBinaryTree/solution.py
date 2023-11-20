# Definition for a binary tree node.
from typing import *
from collections import deque
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        """
        核心是翻转二叉树，而且，连所有的子节点也都要翻转。
        即左孩子变成右孩子
         [4,2,7,1,3,6,9] -》 [4,7,2,9,6,3,1]

        核心是对树进行BFS即可，我们每次BFS的时候，都会得到L/R CHild,这个时候和parent做一个翻转即可
        :param root:
        :return:
        """

        # 特殊情况，为空
        if root is None:
            return None

        bfs_queue:Deque[TreeNode] = deque()
        bfs_queue.append(root)

        while len(bfs_queue) > 0:
            cur_parent = bfs_queue.popleft()
            l_child: Optional[TreeNode] = cur_parent.left
            r_child: Optional[TreeNode] = cur_parent.right
            # 交换位置
            cur_parent.left = r_child
            cur_parent.right = l_child
            # 放入队列准备继续
            if l_child is not None:
                bfs_queue.append(l_child)
            if r_child is not None:
                bfs_queue.append(r_child)

        return root