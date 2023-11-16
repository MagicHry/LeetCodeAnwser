from typing import *


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        """
        这个题目的核心思路，还是双指针
        指针A：负责记录，当前settle down的最后一个元素的位置，初始位置为0
        指针B：探测指针，初始位置在指针A+1，负责和指针A的值比较，相等，就移动，如果大于，则把当前的值写入指针A+1的位置，指针A右移一步

        结束条件，指针B结束
        特殊情况，由于起始位置，B比A多移一步，因此需要特殊考虑nums为空，和为1的情况
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
            while probe_p < len(nums):
                cur_element = nums[probe_p]
                if cur_element == nums[settle_p]:
                    # 值相等，继续移动
                    probe_p += 1
                else:
                    # 因为是不完全递增的，所以这里只有可能是[cur_element] 大于 nums[settle_p]
                    # 我们需要更新settle_p，并且交换值了
                    settle_p += 1
                    nums[settle_p] = cur_element
                    probe_p += 1
                print(f'nums = {nums} |settle->{settle_p} |probe->{probe_p}')
        return settle_p + 1
