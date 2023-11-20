# Definition for a binary tree node.
from typing import *
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        """
        此种解法不一定是最好的。
        我们有一个二叉树（数组从左到右排布）：
        [1,2,2,3,4,4,3]

        核心是，使用中序遍历，中序遍历的时候，遇到子节点为空，写入一个N或者X啥的，那么这样完成后，中序遍历的结果为：
        [3,2,4,1,4,2,3]
        不难看出，数组就是对称的

        因为二叉树的特性，满的二叉树其节点总数为 2^H-1，因此一定是奇数

        所以我们得到对应的数组，或者字符串，直接头尾判断即可
        :param root:
        :return:
        """

        # 特殊情况，为空
        if root is None:
            return False
        # 加速判断，如果颗二叉树是对称的，那么其第二层要么没有，要么两个哦度要有且相等
        if root.left is not None:
            if root.right is None:
                return False
            if root.left.val != root.right.val:
                return False
        elif root.left is None:
            return root.right is None

        # 数字要转str，因为为空的情况，我们要用字符特殊标记
        traversal_list: List[str] = []
        self.mid_order_dfs(cur_node=root, traversal_list=traversal_list)
        print(f'traversal_list -> {traversal_list}')

        # 这种情况下，traversal list一定是奇数的，我们直接开始收尾检查
        start_p = 0
        end_p = len(traversal_list) - 1
        while start_p < end_p:
            print(f'start -> {traversal_list[start_p]} | end -> {traversal_list[end_p]}')
            if traversal_list[start_p] != traversal_list[end_p]:
                return False
            start_p += 1
            end_p -=1
        return True

    def mid_order_dfs(self, cur_node: TreeNode, traversal_list: List[str]):
        # 停止条件
        if cur_node.left is None and cur_node.right is None:
            traversal_list.append(str(cur_node.val))
            return
        else:
            if cur_node.left is None:
                # 添加特殊标记
                traversal_list.append('Empty')
            else:
                self.mid_order_dfs(cur_node=cur_node.left, traversal_list=traversal_list)

            traversal_list.append(str(cur_node.val))

            if cur_node.right is None:
                # 添加特殊标记
                traversal_list.append('Empty')
            else:
                self.mid_order_dfs(cur_node=cur_node.right, traversal_list=traversal_list)