package SortColors_75;

/*Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.


Follow up: Could you come up with a one-pass algorithm using only constant extra space?

[4/10/2024]10m out of 25m
100%/12%
O(n)/O(1)
*/
class Solution {
    public void sortColors(int[] nums) {
        int i = -1;
        int j = nums.length;
        int k = 0;
        while (k < j) {
            // Try forward i
            if (i < nums.length - 1 && nums[i + 1] == 0) {
                i++;
                k = Math.max(i + 1, k);
                continue;
            }
            // Try backward j
            if (j > 0 && nums[j - 1] == 2) {
                j--;
                continue;
            }
            if (nums[k] == 1) {
                k++;
                continue;
            }
            if (nums[k] == 0) {
                i++;
                nums[k] = nums[i];
                nums[i] = 0;
                continue;
            }
            if (nums[k] == 2) {
                j--;
                nums[k] = nums[j];
                nums[j] = 2;
                continue;
            }
        }
    }
}
