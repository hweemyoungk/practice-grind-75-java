package SearchinRotatedSortedArray_33;

/*There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1


Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-10^4 <= target <= 10^4

[4/4/2024]22m out of 30m
100%/64% (Recursive)
O(log(n))/O(log(n))
100%/50% (Iterative)
O(log(n))/O(1)
*/
class Solution {
    private int[] nums;
    private int target;

    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            boolean leftSorted = false;
            boolean rightSorted = false;
            int mid = (lo + hi + 1) / 2;
            if (nums[mid] == target) return mid;
            if (lo <= mid - 1) {
                if (nums[lo] <= nums[mid - 1]) {
                    // Sorted
                    leftSorted = true;
                    if (nums[lo] <= target && target <= nums[mid - 1]) {
                        // In-range
                        return binarySearch(lo, mid - 1);
                    }
                    // out of range: Find in right half
                }
            }
            if (mid + 1 <= hi) {
                if (nums[mid + 1] <= nums[hi]) {
                    rightSorted = true;
                    if (nums[mid + 1] <= target && target <= nums[hi]) {
                        // in-range
                        return binarySearch(mid + 1, hi);
                    }
                }
            }
            if (leftSorted && rightSorted) return -1;
            if (rightSorted) {
                // left has pivot
                hi = mid - 1;
                continue;
            }
            if (leftSorted) {
                lo = mid + 1;
                continue;
            }
            return -1;
        }
        return -1;
    }

//    private int search(int lo, int hi) {
//
//        int mid = (lo + hi + 1) / 2;
//        if (nums[mid] == target) return mid;
//        // Left half
//        if (lo <= mid - 1) {
//            if (nums[lo] < nums[mid - 1]) {
//                // sorted
//                int i = binarySearch(lo, mid - 1);
//                if (i != -1) return i;
//            } else {
//                // has pivot
//                int i = search(lo, mid - 1);
//                if (i != -1) return i;
//            }
//        }
//        // Right half
//        if (mid + 1 <= hi) {
//            if (nums[mid + 1] < nums[hi]) {
//                // sorted
//                int i = binarySearch(mid + 1, hi);
//                if (i != -1) return i;
//            } else {
//                // has pivot
//                int i = search(mid + 1, hi);
//                if (i != -1) return i;
//            }
//        }
//        return -1;
//    }

    // lo~hi is sorted
    private int binarySearch(int lo, int hi) {
        if (target < nums[lo] || nums[hi] < target) return -1;
        while (lo <= hi) {
            int mid = (lo + hi + 1) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
                continue;
            }
            if (target < nums[mid]) {
                hi = mid - 1;
                continue;
            }
            return mid;
        }
        return -1;
    }
}
