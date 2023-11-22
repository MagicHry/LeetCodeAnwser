# Definition for a binary tree node.
from typing import *
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def __init__(self):
        self.last_val: Optional[int] = None
        self.min_diff: Optional[int] = None

    def getMinimumDifference(self, root: Optional[TreeNode]) -> int:
        """
        这个题目首先需要了解二叉搜索树的特性，就是，leftChild<Parent<rightChild
        对于每一颗子树，都成立的

        那么，如果要找到最小值，比如，是要从数值上，相邻的数值中寻找，才可能出现：
        [1,3,6,7,10]->我们能找到6,7，这两数的DIFF最小

        因此，对于二叉搜索树，我们只需要做到，能以一种方式，将其内容变成如上的内容即可。
        那么答案很简单，直接使用中序遍历即可，而且，在中序遍历中，我们实际上可以加速。

        用一个VAR存储上一个遍历的元素，当前的和VAR作差，记录MIN即可。
        :param root:
        :return:
        """
        # 容错
        if root is None:
            return 0

        self.mid_order_dfs(cur_node=root)

        return self.min_diff


    def mid_order_dfs(self, cur_node: TreeNode):

        if cur_node.left is not None:
            self.mid_order_dfs(cur_node=cur_node.left)

        # 初始化情况
        if self.last_val is None:
            self.last_val = cur_node.val
        else:
            # 如果last_val存在，开始进行最小差求解
            if self.min_diff is None:
                self.min_diff = cur_node.val - self.last_val
            else:
                self.min_diff = min(self.min_diff, (cur_node.val - self.last_val))
            # 更新last_val
            self.last_val = cur_node.val

        if cur_node.right is not None:
            self.mid_order_dfs(cur_node=cur_node.right)
