from unittest import TestCase
from solution import Solution

class TestSolution(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_normal_case(self):
        lst = [0,1,2,2,3,0,4,2]
        val = 2
        size = self.solution.removeElement(nums=lst, val=val)
        self.assertEqual(5, size)
        print(lst)

    def test_more_complicate_case(self):
        lst = [1,0,2,0,3,0,0,0,0,0,0,0,5,5,5,0,0,0,1]
        size = self.solution.removeElement(nums=lst, val=0)
        print(lst)
        self.assertEqual(7, size)

    def test_extreme_case(self):
        lst = [1,1,1,1]
        size = self.solution.removeElement(nums=lst, val=1)
        print(lst)
        self.assertEqual(0, size)

    def test_empty_case(self):
        lst = []
        size = self.solution.removeElement(nums=lst, val=0)
        print(lst)
        self.assertEqual(0, size)

    def test_single_non_val_case(self):
        lst = [1]
        size = self.solution.removeElement(nums=lst, val=0)
        print(lst)
        self.assertEqual(1, size)

    def test_single_val_case(self):
        lst = [1]
        size = self.solution.removeElement(nums=lst, val=1)
        print(lst)
        self.assertEqual(0, size)

    def test_two_lst_case(self):
        lst = [1,2]
        size = self.solution.removeElement(nums=lst, val=1)
        print(lst)
        self.assertEqual(1, size)

    def test_two_lst_reverse_case(self):
        lst = [1,2]
        size = self.solution.removeElement(nums=lst, val=2)
        print(lst)
        self.assertEqual(1, size)