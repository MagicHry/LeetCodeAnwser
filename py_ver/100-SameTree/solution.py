# Definition for a binary tree node.
from typing import *
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        """
        判断两棵树是否相同，本质上还是要进行tree traversal的
        我们可以使用前序遍历来做。
        不过，我们前序遍历的时候，多做一点，就是访问到某个节点的时候，顺便比较一下左右孩子的值，等于我们进行了剪枝

        本质上，就是一个多做了两个判断的前序遍历
        :param p:
        :param q:
        :return:
        """
        # 特殊情况，为空的情况
        if p is None and q is None:
            return True
        elif p is None:
            return False
        elif q is None:
            return False

        # 正常情况，开始递归
        # 优先比较一下root
        if p.val != q.val:
            return False
        return self.dfs_two_tree(p_node=p, q_node=q)

    def dfs_two_tree(self, p_node: TreeNode, q_node: TreeNode):
        """
        这里我们使用前序深度优先遍历
        :param p_node:
        :param q_node:
        :return:
        """
        print(f'cur_p_value->{p_node.val}|cur_q_value->{q_node.val}')
        # 我们不需要比较当前节点，因为其已经在前面被比较过了
        # 进一步比较孩子的情况是否相同，如果都有对应孩子，则继续递归
        p_left_v = self.fetch_child_value(node=p_node, is_left=True)
        q_left_v = self.fetch_child_value(node=q_node, is_left=True)
        p_right_v = self.fetch_child_value(node=p_node, is_left=False)
        q_right_v = self.fetch_child_value(node=q_node, is_left=False)

        # 对于不相等的（比如2 v.s.X）,可以直接返回了
        if p_left_v != q_left_v:
            return False
        elif p_right_v != q_right_v:
            return False
        else:
            # 根据情况继续递归
            if p_left_v == 'X' and p_right_v == 'X':
                return True
            elif p_left_v == 'X':
                # 左侧无需继续了 l_result =
                return self.dfs_two_tree(p_node=p_node.right, q_node=q_node.right)
            elif p_right_v == 'X':
                # 右侧无需继续了
                return self.dfs_two_tree(p_node=p_node.left, q_node=q_node.left)
            else:
                # 左右侧都需要继续
                return self.dfs_two_tree(p_node=p_node.right, q_node=q_node.right) and self.dfs_two_tree(p_node=p_node.left, q_node=q_node.left)



    def fetch_child_value(self, node: TreeNode, is_left: bool) -> str:
        if is_left:
            child: Optional[TreeNode] = node.left
        else:
            child: Optional[TreeNode] = node.right

        # 如果为空，则要返回'X'
        if child is None:
            return 'X'
        else:
            return str(child.val)
