package MajorityElement_169;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
/*
* [3/28/2024]5m out of 20m
* 100%/26%
* O(n)/O(1)
* Boyer-Moore Voting Algorithm
* */
class Solution {
    public int majorityElement(int[] nums) {
        int currTarget = nums[0];
        int currCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == currTarget) {
                currCount++;
                continue;
            }
            if (currCount == 0) {
                currTarget = nums[i];
                currCount = 1;
                continue;
            }
            currCount--;
        }
        return currTarget;
    }
}
