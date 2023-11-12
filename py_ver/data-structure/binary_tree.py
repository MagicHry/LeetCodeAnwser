import math
from typing import *
from queue import Queue
from queue import LifoQueue
from unittest import TestCase


class BinaryTreeNode:
    """
    标准二叉树
    即。除了最后一个LAYER
    所有Node都必须是满的（当然，这样不一定会平衡就是了）

    二叉树具有如下公式。
    设
    h = 层数；
    i = 如果将二叉树按照左->右顺序平铺，在列表中的index；
    N = 二叉树所有节点的个数；

    那么有如下的等式：
    1. 如果有某一个ParentNode，下标为i，则有：
        - 其LeftNode下标为：2*i + 1
        - 其RightNode下标为: 2*i + 2
    2. 如果二叉树是满二叉树，那么则有：
        - N = 2^h - 1
    3. 根据第二点的结论，不难得出，当我们的值N时，不管二叉树是不是满的，其高度/深度h:
        - h = CEIL(log(N+1))，这里我们取完对数后，需要向上取整
    4. 如果我们得知了二叉树某一行的行号，则有，改行的元素最大总数为：
        - N(该行) = 2^(h-1)
    """

    def __init(self):
        self.is_root: bool = False
        self.left: Optional[BinaryTreeNode] = None
        self.right: Optional[BinaryTreeNode] = None
        self.value: Optional[Any] = None


@staticmethod
def gen_tree_from_list(input_list: List[Any]) -> Tuple[int, Optional[BinaryTreeNode]]:
    """
    这个函数将会根据平铺的二叉树列表初始化一个二叉树数据结构
    并且，将会计算出二叉树的深度/高度

    我们可以利用，上面的规则1来简化计算
    我们实际上可以算出，最后一个ParentNode的下标是多少
    然后，我们从0迭代到下标位置即可
    :param input_list:
    :return:
    """
    # 容错
    if input_list is None or len(input_list) <= 0:
        root = BinaryTreeNode()
        root.is_root = True
        return 0, root

    # 找到最后一个ParentNode的下标
    # 这里我们要转化一下公式1
    # last_parent_index = (N-3)/2 或者 (N-2)/2
    last_parent_index: Optional[int] = None
    if ((len(input_list)) - 3) % 2 == 0:
        last_parent_index = int(((len(input_list)) - 3) / 2)
    else:
        last_parent_index = int(((len(input_list)) - 2) / 2)
    print(f'input list length -> {len(input_list)}')
    # 直接开始迭代
    root: Optional[BinaryTreeNode] = None
    temp_node_lst: List[Optional[BinaryTreeNode]] = [None] * len(input_list)
    for index in range(0, last_parent_index + 1):
        print(f'start process Node index:{index}')
        if temp_node_lst[index] is None:
            cur_node = BinaryTreeNode()
            root = cur_node
            cur_node.left = None
            cur_node.right = None
            temp_node_lst[index] = cur_node
        else:
            cur_node = temp_node_lst[index]
        if index == 0:
            # 根节点
            cur_node.is_root = True
        cur_node.value = input_list[index]
        # 左节点和右节点
        left_child_index = 2 * index + 1
        right_child_index = 2 * index + 2
        print(f'Parent Node index:{index}, left.right child will be ({left_child_index},{right_child_index})')
        _connect_node(parent=cur_node,
                      child_index=left_child_index,
                      left=True,
                      input_list=input_list,
                      cache=temp_node_lst)
        _connect_node(parent=cur_node,
                      child_index=right_child_index,
                      left=False,
                      input_list=input_list,
                      cache=temp_node_lst)

    # 最后，求解树的高度
    # 这里我们利用公式3，即高度 h = CEIL(log(N+1))
    N = len(input_list)
    height = math.ceil(math.log2(N+1))

    return height, root


@staticmethod
def _connect_node(parent: BinaryTreeNode,
                  child_index: int,
                  left: bool,
                  input_list: List[Any],
                  cache: List[Optional[BinaryTreeNode]]):
    if child_index < len(input_list):
        # 连接+存放(子节点一定不在缓存里面)
        child_node = BinaryTreeNode()
        child_node.left = None
        child_node.right = None
        child_node.value = input_list[child_index]
        if left:
            parent.left = child_node
            print(f'Connected with LEFT child node at index:{child_index}')
        else:
            parent.right = child_node
            print(f'Connected with RIGHT child node at index:{child_index}')
        # 储存
        cache[child_index] = child_node
    else:
        print(f'Reaching end!')

class tree_test(TestCase):

    def test_full_binary_tree(self):
        input = [2,3,5,7,9,1,4]
        height, root = gen_tree_from_list(input_list=input)
        self.assertEqual(3,height)
        zigzag = root.right.left.value
        self.assertEqual(1, zigzag)

    def test_not_full_binary_tree(self):
        input = [2, 3, 5, 7, 9, 1, 4, 100, 3, 6, 17, 25]
        height, root = gen_tree_from_list(input_list=input)
        self.assertEqual(4,height)
        zigzag_r = root.right.left.right
        zigzag_l = root.right.left.left.value
        self.assertIsNone(zigzag_r)
        self.assertEqual(25, zigzag_l)


