from typing import *
from enum import Enum

class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        # 先跑一遍指令，看看情况
        robot = Point()
        degree_offset = robot.workout_offset(instructions)
        if degree_offset == 0:
            # 没有偏移角度，那么点位也不能变
            return robot.current_position == (0, 0)
        elif degree_offset == 90:
            # 执行四次，看效果
            # 前面已经执行了一次了，那么就执行三次即可
            robot.iterate_over_instructions(iteration_cnt=3, instructions=instructions)
        else:
            # 180°，执行两次，前面已经执行过一次了，那么再执行一次即可
            robot.iterate_over_instructions(iteration_cnt=1, instructions=instructions)
        return robot.current_position == (0, 0)

class SingleInstruction(Enum):
    LEFT = 'L'
    RIGHT = 'R'
    GO = 'G'

class Direction(Enum):
    NORTH = 'N'
    SOUTH = 'S'
    EAST = 'E'
    WEST = 'W'

class Point:
    def __init__(self):
        self.current_direction: Direction = Direction.NORTH
        self.current_position: Tuple[int, int] = (0, 0)

        self.direction_lst: List[Direction] = [
            Direction.NORTH,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST
        ]

        self.current_direction_index = 0

    def iterate_over_instructions(self, iteration_cnt: int, instructions: str):
        """
        执行额外次数的指令
        :param instructions:
        :param iteration_cnt:
        :return:
        """
        for iter in range(0, iteration_cnt):
            self.workout_offset(instructions)

    def workout_offset(self, instructions: str) -> int:
        """
        运行一次instructions，并且解析处角度上的偏移
        :param instructions:
        :return:
        """
        if instructions == '':
            raise Exception('empty instructions in [workout_offset]')
        for cur_instruction in instructions:
            if cur_instruction == SingleInstruction.LEFT.value:
                # 方向变动
                self.turn(instruction=SingleInstruction.LEFT)
            elif cur_instruction == SingleInstruction.RIGHT.value:
                self.turn(instruction=SingleInstruction.RIGHT)
            elif cur_instruction == SingleInstruction.GO.value:
                self.go()
            else:
                raise Exception(f'Unrecognized instruct in [workout_offset] -> {cur_instruction}')
        # 求解完毕后，返回当前朝向和起始朝向的偏移角度
        # 注意：270°，被认为是90°
        if self.current_direction == Direction.NORTH:
            return 0
        elif self.current_direction == Direction.SOUTH:
            return 180
        else:
            return 90

    def turn(self, instruction: SingleInstruction):
        """
        单纯转向
        为了优雅点，我们使用list进行操作
        按照【N,E,S,W】排列的话
        右转，就是index+1，到尾部的话，重新变为0
        左转，就是index-1，到头部的话，重新变为3
        :param instruction:
        :return:
        """
        if instruction == SingleInstruction.GO:
            raise Exception('can not GO in [turn]')
        elif instruction == SingleInstruction.LEFT:
            if self.current_direction_index - 1 < 0:
                self.current_direction_index = 3
            else:
                self.current_direction_index -= 1
        else:
            if self.current_direction_index + 1 >= len(self.direction_lst):
                self.current_direction_index = 0
            else:
                self.current_direction_index += 1
        self.current_direction = self.direction_lst[self.current_direction_index]

    def go(self):
        """
        单纯的GO
        根据当前的方位，N,S,E,W
        对于所处的点(X,Y),进行不同的计算
        :return:
        """
        if self.current_direction == Direction.NORTH:
            x, y = self.current_position
            self.current_position = (x, (y+1))
        elif self.current_direction == Direction.SOUTH:
            x, y = self.current_position
            self.current_position = (x, (y-1))
        elif self.current_direction == Direction.WEST:
            x, y = self.current_position
            self.current_position = ((x-1), y)
        else:
            x, y = self.current_position
            self.current_position = ((x+1), y)




