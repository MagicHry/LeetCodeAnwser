from typing import *
from enum import Enum

class Solution:

    def find_cases(self, target_level_num: int) -> List[str]:
        if target_level_num <= 0:
            raise Exception(f'Invalid input params -> {target_level_num}')
        # 开始进行DP运算
        all_possible_results = []
        self.frog_jump_dp(all_result_lst=all_possible_results,
                          current_result='',
                          current_step=target_level_num)
        return all_possible_results

    def frog_jump_dp(self, all_result_lst: List[str], current_result: str, current_step: int):
        """
        尝试设置这个蛙跳的DP为：
        Fx(N) = Fx(N-k) + k
        其中，k∈ {1,2}
        N=当前青蛙所在的台阶数目

        为什么这么设置呢，原因是，青蛙当前所在的台阶，和它上一次所在的台阶，必然是N-1;或者是N-2的
        :return:
        """
        if current_step == 1:
            # 结束了
            if current_result == '':
                current_result = f'{current_step}'
            else:
                current_result = f'{current_result}-{current_step}'
            # 入列
            final_result = str(current_result[::-1])
            all_result_lst.append(final_result)
            print(f'Find solution -> {final_result}')
            return
        elif current_step == 0:
            # 直接到达，也结束了
            # 入列
            final_result = str(current_result[::-1])
            all_result_lst.append(final_result)
            print(f'Find solution -> {final_result}')
            return
        elif current_step < 0:
            # 无效答案，退出
            return
        else:
            # 还没结束，继续递归迭代
            # 先更新当前的步骤
            step_options = [1, 2]
            for step in step_options:
                if current_result == '':
                    new_current_result = f'{step}'
                else:
                    new_current_result = f'{current_result}-{step}'
                new_current_step = current_step - step
                self.frog_jump_dp(all_result_lst=all_result_lst, current_result=new_current_result, current_step=new_current_step)




