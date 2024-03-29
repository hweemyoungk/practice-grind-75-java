package SingleNumber_136;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * Each element in the array appears twice except for one element which appears only once.
 */
/*
* [3/29/2024]5m out of 15m
* Editorial
* 100%/34%
* O(n)/O(1)
* */
class Solution {
    public int singleNumber(int[] nums) {
        // XOR
        int ans=nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans^=nums[i];
        }
        return ans;
    }
}
