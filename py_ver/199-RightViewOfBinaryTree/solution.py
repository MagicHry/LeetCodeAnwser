# Definition for a binary tree node.
from typing import *
from collections import deque
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        """
        这题的本质，还是BFS，但是需要做一些额外的操作：
         - 首先，我们BFS的时候，优先看右侧，因为同层嘛，被选中的，总是右侧的
         - 其次，我们不能一个Node一个Node的放入BFSQueue里面，这样的话，我们丢失了层数信息
         我们需要一层一层的放入BFSQueue里面，对于每一层，我们都选择最右侧的即可
        :param root:
        :return:
        """
        # 容错
        if root is None:
            return []
        # layer queue中存放，当前这一层的所有TreeNode
        layer_queue: Deque[List[TreeNode]] = deque()

        # 初始化
        result_lst: List[int] = [root.val]
        init_layer = []
        if root.right is not None:
            init_layer.append(root.right)
        if root.left is not None:
            init_layer.append(root.left)
        layer_queue.append(init_layer)

        while len(layer_queue) > 0:
            cur_layer_nodes = layer_queue.popleft()
            next_layer_nodes = []
            for node_index in range(0, len(cur_layer_nodes)):
                cur_node = cur_layer_nodes[node_index]
                if node_index == 0:
                    # 同层最右侧node，进入最终结果lst
                    result_lst.append(cur_node.val)
                # 按照先右后左的顺序，查看子Node，放入下一层list中
                if cur_node.right is not None:
                    next_layer_nodes.append(cur_node.right)
                if cur_node.left is not None:
                    next_layer_nodes.append(cur_node.left)
            if len(next_layer_nodes) == 0:
                # 到底了，没有下一层了
                break
            else:
                layer_queue.append(next_layer_nodes)

        return result_lst

