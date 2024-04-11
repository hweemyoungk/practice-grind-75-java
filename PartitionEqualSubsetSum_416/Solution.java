package PartitionEqualSubsetSum_416;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100*/
class Solution {
    private int[] nums;
    private final Set<int[]> unpartitionables = new HashSet<>();

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum % 2) == 1) return false;
        int half = sum >> 1;
        this.nums = nums;
        return canPartition(0, half, sum);
    }

    private boolean canPartition(int iCurr, int targetSum, int maxSum) {
        if (targetSum == 0) return true;
        int[] pair = {iCurr, targetSum};
        if (targetSum < 0 || maxSum < targetSum || iCurr == nums.length || unpartitionables.contains(pair)) {
            unpartitionables.add(pair);
            return false;
        }
        // Case1: partition includes iCurr
        if (canPartition(iCurr + 1, targetSum - nums[iCurr], maxSum - nums[iCurr])) return true;
        // Case2: partition doesn't include iCurr
        if (canPartition(iCurr + 1, targetSum, maxSum - nums[iCurr])) return true;
        unpartitionables.add(pair);
        return false;
    }
}
