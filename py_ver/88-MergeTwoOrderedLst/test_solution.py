from unittest import TestCase
from solution import Solution
class TestSolution(TestCase):

    def setUp(self) -> None:
        self.solution = Solution()

    def tearDown(self) -> None:
        self.solution = None

    def test_simple_case(self):
        nums1 = [1, 2, 3, 0, 0, 0]
        nums2 = [2, 5, 6]
        self.solution.merge(nums1=nums1,m=3, nums2=nums2, n=3)
        self.assertEqual([1,2,2,3,5,6], nums1)

    def test_n_zero_case(self):
        nums1 = [1, 2, 3]
        nums2 = []
        self.solution.merge(nums1=nums1,m=3, nums2=nums2, n=0)
        self.assertEqual([1, 2, 3], nums1)

    def test_m_zero_case(self):
        nums1 = [0,0,0]
        nums2 = [1,2,3]
        self.solution.merge(nums1=nums1,m=0, nums2=nums2, n=3)
        self.assertEqual([1, 2, 3], nums1)

    def test_normal_case(self):
        nums1 = [1, 2, 6, 7, 20, 0, 0, 0, 0, 0]
        nums2 = [3, 5, 11, 14, 16]
        self.solution.merge(nums1=nums1,m=5, nums2=nums2, n=5)
        self.assertEqual([1,2,3,5,6,7,11,14,16,20], nums1)