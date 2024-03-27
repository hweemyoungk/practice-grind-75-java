package ClimbingStairs_70;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 */
/*
* [3/27/2024]10m out of 20m
* 100%/27%
* O(n)/O(1)*/
class Solution {
    public int climbStairs(int n) {
        if (n==1) return 1;
        if (n==2) return 2;
        int prevPrev=1;
        int prev=2;
        int curr=3;
        for (int i = 2; i < n; i++) {
            curr=prev+prevPrev;
            prevPrev=prev;
            prev=curr;
        }
        return prev;
    }
}
