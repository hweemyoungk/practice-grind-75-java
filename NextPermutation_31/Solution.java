package NextPermutation_31;

/*A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.



Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100

[4/25/2024]Time up
100%/79%
O(n)/O(1)
*/
class Solution {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i - 1] >= nums[i]) {
                // Descending
                continue;
            }
            // Found ascending
            // 1. Swap i-1 and j that is smallest larger than i-1
            int j = i;
            while (j < nums.length) {
                if (nums[i - 1] >= nums[j]) break;
                j++;
            }
            j--;
            swap(nums, i - 1, j);
            // 2. Reverse nums[i:]
            for (j = 0; j < (nums.length - i) / 2; j++) {
                swap(nums, i + j, nums.length - 1 - j);
            }
            /*// 3. Move nums[i]
            while (i < nums.length - 1 && !(nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
                i++;
            }*/
            return;
        }
        // Completely descending: Reverse whole
        for (int i = 0; i < nums.length / 2; i++) {
            swap(nums, i, nums.length - 1 - i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int a = nums[i];
        int b = nums[j];
        nums[i] = b;
        nums[j] = a;
    }
}
