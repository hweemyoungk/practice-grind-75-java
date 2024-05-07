package FindtheDuplicateNumber_287;

/*Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.



Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
Example 3:

Input: nums = [3,3,3,3,3]
Output: 3


Constraints:

1 <= n <= 10^5
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.


Follow up:

How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?

[5/7/2024]19m out of 20m
61%/49%
O(~(m+l-1))/O(1) where m: distance between loop and head, l: length of loop
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[nums[0]];
        int fast = nums[nums[nums[0]]];
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // Found cycle
        slow = nums[0];
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}
