from typing import *
from collections import deque

class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        这个题目，很贱，可以做到空间占用O(1)的同时，时间复杂度O(M+N)
        关键在于：
        num1=[1,2,3,0,0,0]
        num2=[2,5,6]
        这样的数据结构，我们比较的时候，不要正向比较，而是需要反向比较！！
        这样那些为0的空间就可以被很简单的直接占用了！！
        :param nums1:
        :param m:
        :param nums2:
        :param n:
        :return:
        """
        m_index = (m-1)
        n_index = (n-1)
        global_pointer = m+n-1
        while not (m_index < 0 and n_index < 0):
            if m_index >= 0:
                cur_m_value = nums1[m_index]
            else:
                cur_m_value = None
            if n_index >= 0:
                cur_n_value = nums2[n_index]
            else:
                cur_n_value = None
            # M为空情况，选N
            if cur_m_value is None:
                nums1[global_pointer] = cur_n_value
                n_index -= 1
            elif cur_n_value is None:
                # N为空情况，选M
                nums1[global_pointer] = cur_m_value
                m_index -= 1
            elif cur_n_value >= cur_m_value:
                # N>=M情况，选N
                nums1[global_pointer] = cur_n_value
                n_index -= 1
            else:
                # N < M情况，选M
                nums1[global_pointer] = cur_m_value
                m_index -= 1
            global_pointer -= 1



















































