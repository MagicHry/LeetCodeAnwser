# Definition for singly-linked list.
from typing import *


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        """
        这道题目比较简单，简单的原因是，输入链表已经是反过来的了。思考如下两个数：
        376 +
        45

        L1直接变成-> 6->7->3
        L2变成   -> 5->4

        那么在加的时候，我们注意几点：
        1.直接按位加起来，如果有一个链表没有元素了，那么就只加另外一个
        2.加起来的数值，大于10的，保留余数，下一位计算的时候，要加1
        3.特别注意，当循环要结束的时候，如果还需要进1位，也得加上
        :param l1:
        :param l2:
        :return:
        """
        # 极限情况处理
        if l1 is None and l2 is None:
            return None
        elif l1 is None:
            return l1
        elif l2 is None:
            return l1
        else:
            l1_p = l1
            l2_p = l2
            additional_one = False
            result_header: Optional[ListNode] = None
            last_result: Optional[ListNode] = None
            while True:
                # 退出循环条件
                if l1_p is None and l2_p is None:
                    # 循环要结束了，如果上一次相加，要进位，这里也需要额外加上
                    if additional_one:
                        last_result.next = ListNode(val=1)
                    break

                # 获取当前l1,l2的值
                if l1_p is None or l1_p.val is None:
                    l1_v = 0
                else:
                    l1_v = l1_p.val
                if l2_p is None or l2_p.val is None:
                    l2_v = 0
                else:
                    l2_v = l2_p.val

                # 尝试相加
                if additional_one:
                    sum = l1_v + l2_v + 1
                else:
                    sum = l1_v + l2_v

                print(f'sum is -> {sum}')

                # 得到当前这一位的结果，并且添加上去
                result_value = sum % 10
                cur_node = ListNode(val=result_value)
                if last_result is None:
                    # 表示我们处于头部
                    last_result = cur_node
                    result_header = cur_node
                else:
                    last_result.next = cur_node
                    last_result = cur_node

                # 判断下次是否需要进位
                if sum >= 10:
                    additional_one = True
                else:
                    additional_one = False

                # 更新指针
                if l1_p is not None and l1_p.next is not None:
                    l1_p = l1_p.next
                else:
                    l1_p = None

                if l2_p is not None and l2_p.next is not None:
                    l2_p = l2_p.next
                else:
                    l2_p = None
            return result_header
