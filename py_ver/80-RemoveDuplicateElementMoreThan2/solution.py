from typing import *
from enum import Enum


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        """
        这道题的思路，和移除有序顺组中的重复元素是一样的，还是双指针。
        只不过，多了一个条件：即重复出现的元素，可以额外保留一个。

        那么我们还是这样设置指针：
        指针A：settle指针，初始位置为0
         - 移动逻辑：
            - 出现一次指针A的值等于指针B的值的时候，需要右移，然后把指针B的值设置过来 （这样做的目的是，一次相等，那么这个元素就保留了，settle了）
            - 如果指针A和指针B不等，由于有序性，必然是指针B的值 > 指针A，那么老样子，移动A，交换值

        指针B：探测指针，初始位置为1
         - 移动逻辑：如果满足指针A的两种移动逻辑，交换，并且右移一位
         - 否则就是超过一次判定相等的情况了，那么直接右移

        终止条件：指针B到达末尾
        特殊情况：考虑nums为空，或者为长度为1的情况

        :param nums:
        :return:
        """
        if len(nums) == 0:
            return 0
        elif len(nums) == 1:
            return 1
        else:
            settle_p = 0
            probe_p = 1
            matched_counter = 0
            while probe_p < len(nums):
                cur_element = nums[probe_p]
                if cur_element == nums[settle_p]:
                    # 这里讨论两种情况
                    if matched_counter < 1:
                        # 相等，但是之前没遇到过，那么移动probe指针，交换value
                        matched_counter = 1
                        # 注意，这里一定要交换，我们不能保证当前两个相等的元素是相邻的（因为之前那个元素可能是交换过来的）
                        settle_p += 1
                        nums[settle_p] = cur_element
                        probe_p += 1
                    else:
                        # 相等，但是已经match过一次了，那么直接移动probe指针
                        probe_p += 1
                        matched_counter += 1
                else:
                    # 当前元素更大，直接交换，且注意，这里需要重置counter，因为settle_p指向的元素，值已经变了，要重新计数
                    matched_counter = 0
                    settle_p += 1
                    probe_p += 1
                    nums[settle_p] = cur_element
                # print(f'nums->{nums}|settle->{settle_p}|probe->{probe_p}')

            return settle_p + 1
