from unittest import TestCase
from solution import Solution
class TestSolution(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_normal_case(self):
        valid = self.solution.isValid('([{}])')
        self.assertTrue(valid)

    def test_not_suc_case(self):
        valid = self.solution.isValid('([]}])')
        self.assertFalse(valid)

    def test_one_by_one_case(self):
        valid = self.solution.isValid('()()[]{}')
        self.assertTrue(valid)

    def test_empty_case(self):
        valid = self.solution.isValid('           ')
        self.assertFalse(valid)