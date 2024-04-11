package Subsets_78;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*Given an integer array nums of unique elements, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.

[4/11/2024]10m out of 30m
60%/30%
O(n*(2^n))/O(n)
*/
class Solution {
    private final List<List<Integer>> ans = new LinkedList<>();
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        subsets(0, new Stack<>());
        return ans;
    }

    private void subsets(int iCurr, Stack<Integer> prevSubset) {
        if (iCurr == nums.length) {
            // Copy
            ans.add(new LinkedList<>(prevSubset));
            return;
        }
        // Case1: subset not include iCurr
        subsets(iCurr + 1, prevSubset);
        // Case2: subset includes iCurr
        prevSubset.push(nums[iCurr]);
        subsets(iCurr + 1, prevSubset);
        prevSubset.pop();
    }
}
