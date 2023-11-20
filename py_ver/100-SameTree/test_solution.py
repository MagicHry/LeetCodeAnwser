from unittest import TestCase
from solution import Solution, TreeNode
class TestSolution(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    # def test_normal_case(self):
    #     root1 = TreeNode(val=1)
    #     root1 = TreeNode(val=1)
    #     size = self.solution.removeDuplicates(nums=lst)
    #     self.assertEqual(7, size)
