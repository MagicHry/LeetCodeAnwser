from typing import *
from collections import deque
class Solution:
    def simplifyPath(self, path: str) -> str:
        """
        这个题目的核心思路还是通过栈来解决
        其实核心就是，返回字符串，没有'.',没有'..'，去除多个'/'
        对于一个复杂的unix路径,比如:
        /a/./b//c/../d/../f
        我们需要使用str.split完成
        完成后，我们利用栈做下面几件事情：
        1.遇到'/'，什么也不做
        2.遇到常规str，向栈顶添加即可
        3.遇到'.'，什么也不做
        4.遇到'..' 则直接弹栈当前栈顶元素
        5.遇到‘’，这是由于split导致的，也什么都不做

        然后，我们开始出栈
        出栈元素，需要在前面添加一个'/'
        :param path:
        :return:
        """
        # 极端情况
        if len(path.strip()) == 0:
            return ''

        split_str = path.split('/')
        stack: Deque[str] = deque()
        for cur_sub_path in split_str:
            if cur_sub_path == '/':
                continue
            elif cur_sub_path == '':
                continue
            elif cur_sub_path == '.':
                continue
            elif cur_sub_path == '..':
                # 这个需要尝试弹栈
                if len(stack) >= 1:
                    stack.pop()
            else:
                # 添加进栈
                stack.append(cur_sub_path)

        # 开始出栈，出栈的顺序是，path从右往左
        result_path = ''
        if len(stack) == 0:
            # 一些特殊的case，导致实际上就是一个空的路径，那么要特殊处理
            result_path = '/'
        else:
            while len(stack) != 0:
                cur_element = stack.pop()
                result_path = f'/{cur_element}{result_path}'


        return result_path