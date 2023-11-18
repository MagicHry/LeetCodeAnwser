from unittest import TestCase
from solution import Solution, ListNode
from typing import *

def lst_to_node(input_lst: Optional[List[int]]) -> Optional[ListNode]:
    if input_lst is None or len(input_lst) == 0:
        return None
    head = ListNode(input_lst[0])
    if len(input_lst) == 1:
        return head
    cur_node = head
    for index in range(1, len(input_lst) - 1):
        next_node = ListNode(input_lst[index])
        cur_node.next = next_node
        cur_node = next_node
    return head


class TestSolution(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_normal_case(self):
        head = ListNode(3)
        ll2 = ListNode(2)
        head.next = ll2
        ll3 = ListNode(0)
        ll2.next = ll3
        ll4 = ListNode(-4)
        ll3.next = ll4
        ll4.next = ll2
        loop = self.solution.hasCycle(head=head)
        self.assertTrue(loop)