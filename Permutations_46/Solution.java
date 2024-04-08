package Permutations_46;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.

[4/8/2024]10m out of 30m
49%/76%
49%/43% (Editorial)
*/
class Solution {
    private final List<List<Integer>> ans = new LinkedList<>();
    private int[] nums;
    private boolean[] visited;
    public List<List<Integer>> permute(int[] nums) {
        // Backtrack
        this.nums=nums;
        visited = new boolean[nums.length];
        backtrack(new LinkedList<>());
        return ans;
    }

    private void backtrack(LinkedList<Integer> permutation) {
        if (permutation.size()== nums.length) {
            ans.add(new LinkedList<>(permutation));
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) continue;
            visited[i]=true;
            permutation.add(nums[i]);
            backtrack(permutation);
            permutation.removeLast();
            visited[i]=false;
        }
    }
}
