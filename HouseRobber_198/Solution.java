package HouseRobber_198;

/*You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400

[4/22/2024]9m out of 25m
100%/95%
TC: O(2n): backtrack is called twice per i
SC: O(n + n + n/2): answer + dp + max recursive call stack
5m out of 25m [Editorial]
100%/70%
TC: O(n)
SC: O(1)
*/
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        // One pass, in-place
        if (nums.length >= 3) {
            nums[nums.length - 3] += nums[nums.length - 1];
        }
        for (int i = nums.length - 4; i >= 0; i--) {
            nums[i] += Integer.max(nums[i + 2], nums[i + 3]);
        }
        return Integer.max(nums[0], nums[1]);
    }
    /*private int[] nums;
    private int[] dp;

    public int rob(int[] nums) {
        // Backtracking
        this.nums = nums;
        this.dp = new int[nums.length];
        Arrays.fill(dp, -1);
        backtrack(0);
        backtrack(1);
        return Integer.max(backtrack(0), backtrack(1));
    }

    // Returns max sum from index i, inclusive.
    private int backtrack(int i) {
        if (nums.length - 1 < i) return 0;
        if (dp[i] != -1) return dp[i];
        // Consider i+2 and i+3
        dp[i] = nums[i] + Integer.max(backtrack(i + 2), backtrack(i + 3));
        return dp[i];
    }*/
}
