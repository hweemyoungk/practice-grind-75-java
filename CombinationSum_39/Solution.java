package CombinationSum_39;

import java.util.LinkedList;
import java.util.List;

/*Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
frequency
 of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []


Constraints:

1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40

[4/4/2024]26m out of 30m
84%/24%
O(#candidates^(1+(target/min(candidates))))/O(target/min(candidates))
(r^0+r^1+...+r^n = r^(1+n)-1)
*/
class Solution {
    private int[] candidates;
    private LinkedList<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // backtrack
        this.candidates = candidates;
        backtrack(new LinkedList<>(), -1, target);
        return ans;
    }

    private void backtrack(LinkedList<Integer> curr, int iPrev, int target) {
        if (target == 0) {
            ans.add(new LinkedList<>(curr));
            return;
        }
        if (target < 0) return;
        for (int i = 0; i < candidates.length; i++) {
            if (i < iPrev) continue;
            int candidate = candidates[i];
            curr.add(candidate);
            backtrack(curr, i, target - candidate);
            curr.removeLast();
        }
    }
}
