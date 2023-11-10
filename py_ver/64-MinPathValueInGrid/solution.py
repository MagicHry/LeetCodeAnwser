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
        look_up_table = self.build_look_up_table(grid=grid,
                                                 row_cnt=row_cnt,
                                                 col_cnt=col_cnt)
        return self.dp_min_path_sum(grid=grid,
                                    lookup_table=look_up_table,
                                    row_index=(row_cnt-1),
                                    col_index=(col_cnt-1))

    def build_look_up_table(self, grid: List[List[int]], row_cnt: int, col_cnt: int) -> List[List[Optional[int]]]:
        """
        按照 {dp_min_path_sum} 中所说，构建一个第一行和第一列的速查表
        但是我们的速查表要和grid本身是一样大小的，原因是后面我们还有机会拓展它
        :param grid:
        :return:
        """
        look_up_table: List[List[Optional[int]]] = []
        for index in range(0, row_cnt):
            cur_row = [None] * col_cnt
            look_up_table.append(cur_row)
        # 计算第一行的所有DP值
        for first_row_col_index in range(0, col_cnt):
            # 根据CASE2 DP(0,N) = DP(0,N-1) + GRID(0,N)
            if first_row_col_index == 0:
                # (0,0)为特殊情况
                look_up_table[0][first_row_col_index] = grid[0][first_row_col_index]
            else:
                look_up_table[0][first_row_col_index] = grid[0][first_row_col_index] + look_up_table[0][first_row_col_index-1]
        # 计算第一列的所有DP值
        for first_col_row_index in range(0, row_cnt):
            # 根据CASE3 DP(M,0) = DP(M-1,0) + GRID(M,0)
            if first_col_row_index == 0:
                # (0,0)为特殊情况
                look_up_table[first_col_row_index][0] = grid[first_col_row_index][0]
            else:
                look_up_table[first_col_row_index][0] = grid[first_col_row_index][0] + look_up_table[first_col_row_index-1][0]

        return look_up_table




    def dp_min_path_sum(self,
                        grid: List[List[int]],
                        lookup_table: List[List[Optional[int]]],
                        row_index: int,
                        col_index: int) -> int:
        """
        核心思路是通过DP解决
        因为我们只能向下走，或者向右走。
        不难看出，当前某个点(M,N)的最短路径和，只能通过点(M-1,N)或是(M,N-1)来到
        那么我们不难看出，每一步之间都存在关系，这样我们就可以不断递归推算了，因此可以使用DP

        但是，如果直接使用DP，可能会导致搜索空间过大，成本过高。因此，我们的状态迁移函数要考虑多种特例。

        注意，计算当前点的最短路径和的时候，也要将当前这个点的值纳入考量

        CASE1：极端特例，就是坐上角的点，DP(0,0) = GRID(0,0)
        CASE2：第一行，因为第一行的元素，只能通过同行元素向右得到，因此他们的值是固定的，即DP(0,N) = DP(0,N-1) + GRID(0,N)
        CASE3：第一列，因为第一列的元素，只能通过同列元素向下得到，因此他们的值也是固定的，即DP(M,0) = DP(M-1,0) + GRID(M,0)
        CASE4：除上述情况的通解，需要考虑从上方下来的，和从左边过来 DP(M,N) = Min{DP(M-1,N);DP(M,N-1)} + GRID(M,N)

        另外，对于CASE2，CASE3，我们可以形成速查表，不需要进行迭代了
        同理，有一些点可能会被遇到多次，思考一下，如果是一个3X3的GRID，中间那个点，至少会被检查两次，那如果第一次查出来了，第二次属实没有任何必要
        因此我们可以做一个3X3的GRID 速查表

        :param grid:
        :param row_index:
        :param col_index:
        :return:
        """
        # 容错
        if row_index >= len(grid) or col_index >= len(grid[0]):
            raise Exception('Invalid index!')
        # 如果在LookUptable中有值，那么直接返回
        value_in_lookup_table = lookup_table[row_index][col_index]
        if value_in_lookup_table is not None:
            # End Condition; 击中lookup table了
            # 直接返回Value
            print(f'[dp_min_path_sum] HIT lookup table for point ({row_index},{col_index}), with value={value_in_lookup_table}')
            return value_in_lookup_table
        else:
            """
            继续尝试递归：
            但是这里要考虑负数的情况
            即row_index为负数
            或col_index为负数
            哪个是负数，就取另外一个
            """
            new_row_index = row_index - 1
            new_col_index = col_index - 1
            dp_new_row: Optional[int] = None
            dp_new_col: Optional[int] = None

            # 走到头（负数）了就没必要走了
            if new_row_index >= 0:
                dp_new_row = self.dp_min_path_sum(grid=grid,
                                                  lookup_table=lookup_table,
                                                  row_index=new_row_index,
                                                  col_index=col_index)
            if new_col_index >= 0:
                dp_new_col = self.dp_min_path_sum(grid=grid,
                                                  lookup_table=lookup_table,
                                                  row_index=row_index,
                                                  col_index=new_col_index)
            # 选择最小的那个返回
            if dp_new_col is None and dp_new_row is None:
                raise Exception('This condition may never happened, please recheck the code')
            elif dp_new_row is None:
                chosen_value = dp_new_col + grid[row_index][col_index]
            elif dp_new_col is None:
                chosen_value = dp_new_row + grid[row_index][col_index]
            else:
                # 如果两者都存在，那么要进行比较
                chosen_value = min(dp_new_col, dp_new_row) + grid[row_index][col_index]
            # 更新lookup table
            lookup_table[row_index][col_index] = chosen_value
            print(f'The DP value for ({row_index},{col_index}) is {chosen_value}')
            return chosen_value
