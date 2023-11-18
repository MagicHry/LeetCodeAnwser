from typing import *
from collections import deque
class Solution:
    def isValid(self, s: str) -> bool:
        """
        这道题目非常简单
        是很典型的利用栈进行的字符串处理

        思考这样的case:  ([{)])
        我们使用一个stack：
         - 每次放入元素的时候，看一下里面的元素，如果形成pair，那么直接弹栈，这个当前的元素不加入。
         - 如果没法形成pair，也没关系，我们直接放入栈顶
        字符串遍历完成之后，如果stack不为空，则不合法，为空，则合法
        :param s:
        :return:
        """
        # 特殊情况
        if s is None or len(s.strip())==0:
            return False
        # set中的组合都是成功的pairing
        suc_paring_set: Set[str] = {'()', '{}', '[]'}

        # stack，我们这里用deque
        stack: Deque[str] = deque()

        for cur_char in s:
            if len(stack) == 0:
                # 首个元素的case，直接放进去
                stack.append(cur_char)
            else:
                # 先拿出当前栈顶元素
                stack_top = stack.pop()
                candidate_pair = f'{stack_top}{cur_char}'
                if candidate_pair not in suc_paring_set:
                    # 如果不在，那么stack_top重新入栈，cur_char进入栈顶
                    stack.append(stack_top)
                    stack.append(cur_char)
                # 如果在，那么就pair了，这两个元素都移除

        return len(stack) == 0
