from unittest import TestCase
from solution import Solution
class TestSolution(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_normal_case(self):
        lst = [0,0,1,1,1,2,2,3,3,4]
        size = self.solution.removeDuplicates(nums=lst)
        self.assertEqual(5, size)

    def test_single_case(self):
        lst = [1]
        size = self.solution.removeDuplicates(nums=lst)
        self.assertEqual(1, size)

    def test_allthesame_case(self):
        lst = [1,1,1,1]
        size = self.solution.removeDuplicates(nums=lst)
        self.assertEqual(1, size)

    def test_non_repeat_case(self):
        lst = [1,2,3,4]
        size = self.solution.removeDuplicates(nums=lst)
        self.assertEqual(4, size)