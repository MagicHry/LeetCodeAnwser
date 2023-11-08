from unittest import TestCase
from solution import *
class TestFrogJumping(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_step_1(self):
        results = self.solution.find_cases(target_level_num=1)
        self.assertEqual(1, len(results))
        print(results)

    def test_step_2(self):
        results = self.solution.find_cases(target_level_num=2)
        self.assertEqual(2, len(results))
        print(results)

    def test_step_4(self):
        results = self.solution.find_cases(target_level_num=4)
        self.assertEqual(5, len(results))
        print(results)
