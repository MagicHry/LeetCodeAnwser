from unittest import TestCase
from solution import Solution
class TestSolution(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_normal_case(self):
        result = self.solution.simplifyPath(path='/home/')
        self.assertEqual('/home', result)

    def test_double_spliiter(self):
        result = self.solution.simplifyPath(path='/home//desk/////aka')
        self.assertEqual('/home/desk/aka', result)

    def test_self_case(self):
        result = self.solution.simplifyPath(path='/home/./a/b/./c/./d')
        self.assertEqual('/home/a/b/c/d', result)


    def test_parent_case(self):
        result = self.solution.simplifyPath(path='/home/./a/.././c/./d')
        self.assertEqual('/home/c/d', result)

    def test_single_case(self):
        result = self.solution.simplifyPath(path='/../')
        self.assertEqual('/', result)