from typing import *
from enum import Enum

class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        if len(grid) == 0:
            return 0
        if len(grid[0]) == 0:
            return 0
        row_cnt = len(grid)
        col_cnt = len(grid[0])
        return self.dp_min_path_sum(grid=grid, row_index=(row_cnt-1), col_index=(col_cnt-1))

    def dp_min_path_sum(self, grid: List[List[int]], row_index: int, col_index: int) -> int:
        # 容错
        if row_index >= len(grid) or col_index >= len(grid[0]):
            raise Exception('Invalid index!')
        if row_index == 0 and col_index == 0:
            # End Condition;到达grid[0][0] 左上角了
            # 直接返回Value
            return grid[row_index][col_index]
        else:
            """
            继续尝试递归：
            但是这里要考虑负数的情况
            即row_index为负数
            或col_index为负数
            哪个是负数，就取另外一个
            
            P.S.不可能出现row_index和col_index都是负数的情况，
            因为(0,0)这个点是Ending Condition，直接返回了
            """
            new_row_index = row_index - 1
            new_col_index = col_index - 1
            dp_new_row: Optional[int] = None
            dp_new_col: Optional[int] = None
            if new_row_index >= 0:
                dp_new_row = self.dp_min_path_sum(grid=grid, row_index=new_row_index, col_index=col_index)
            if new_col_index >= 0:
                dp_new_col = self.dp_min_path_sum(grid=grid, row_index=row_index, col_index=new_col_index)
            # 选择最小的那个返回
            if dp_new_col is None and dp_new_row is None:
                raise Exception('This condition may never happened, please recheck the code')
            if dp_new_row is None:
                return dp_new_col + grid[row_index][col_index]
            if dp_new_col is None:
                return dp_new_row + grid[row_index][col_index]

            # 如果两者都存在，那么要进行比较
            return min((dp_new_col + grid[row_index][col_index]), (dp_new_row + grid[row_index][col_index]))
