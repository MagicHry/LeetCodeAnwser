# Definition for singly-linked list.
from typing import *
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        """
        这道题目，核心在于，在O(1)的空间复杂度的情况下，判断链表有没有环。
        解决方案也比较简单，就是直接开始遍历，但是每次遇到一个Node，我们要修改Node的值，变成一个极其特殊的值。
        - 在python上，我们可以直接把他变成字符串'x'
        - 在其他语言上，我们可以把val变成一个不可能出现的数字，例如本题，val的范围为 -10^5 <= Node.val <= 10^5,那就设置一个这个区间之外的
        而我们一旦遇到这个value 'X',说明，出现环了
        :param head:
        :return:
        """

        # 特殊情况，没有Node
        if head is None:
            return False
        elif head.next is None:
            return False

        cur_node = head
        while cur_node.next is not None:
            # 检查元素的值
            if cur_node.val == 'X':
                return True
            else:
                # 未曾见过的元素，更新值
                cur_node.val = 'X'
            cur_node = cur_node.next
        return False