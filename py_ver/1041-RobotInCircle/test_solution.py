from unittest import TestCase
from solution import *

class SolutionTester(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_GL(self):
        result = self.solution.isRobotBounded('GL')
        self.assertTrue(result)

    def test_GGLLGG(self):
        result = self.solution.isRobotBounded('GGLLGG')
        self.assertTrue(result)

    def test_RGLGLG(self):
        result = self.solution.isRobotBounded('RGLGLG')
        self.assertTrue(result)

    def test_LRRG(self):
        result = self.solution.isRobotBounded('LRRG')
        self.assertTrue(result)

    def test_LGRG(self):
        result = self.solution.isRobotBounded('LGRG')
        self.assertFalse(result)
