from typing import *
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        """
        这题的核心思路是，双指针。
        思考这样的数组 [0,1,2,2,3,0,4,2];VAL=2
        我们其实要把它变成[0,1,3,0,4,2,2,2]
        那么我们盯住VAL=2，设置指针A和指针B，分别在收尾
        尾部指针B的移动逻辑是：
            - 如果当前元素为Val，向左移动，直到遇到不是VAL的元素，停留下来等指针A
        首部指针A的移动逻辑是：
            - 如果当前元素不是Val，向右移动，直到遇到VAL，遇到VAL的时候，和指针B进行交换

        交换后，A左移一次，B右移一次
        停止条件：A和B交汇

        对于新列表长度，边界长度太多了，直接选择摆烂，重新数一遍
        :param nums:
        :param val:
        :return:
        """
        if len(nums) == 0:
            return 0
        left_p = 0
        right_p = len(nums) - 1
        while left_p < right_p:
            if nums[left_p] != val:
                # 先看A
                left_p += 1
                continue
            elif nums[right_p] == val:
                # 再看B
                right_p -= 1
                continue
            else:
                # 能走到这，说明此时A指向了VAL，B指向了非VAL，交换位置
                nums[left_p] = nums[right_p]
                nums[right_p] = val
                # 各进一步
                left_p += 1
                right_p -= 1

        counter = 0
        for element in nums:
            if element != val:
                counter += 1
        return counter



