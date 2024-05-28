package SubarraySumEqualsK_560;

import java.util.*;

/*Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2


Constraints:

1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7

[5/28/2024]35m out of 35m
27%/5% (suboptimal)

*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, List<Integer>> sumToIndexes = new HashMap<>();
        sumToIndexes.put(0, new LinkedList<>() {{
            add(-1);
        }});
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int finalI = i;
            sumToIndexes.compute(sum, (integer, indexes) -> {
                if (indexes == null) {
                    indexes = new LinkedList<>();
                }
                indexes.add(finalI);
                return indexes;
            });
        }
        int ans = 0;
        if (k == 0) {
            for (Map.Entry<Integer, List<Integer>> entry : sumToIndexes.entrySet()) {
                int size = entry.getValue().size();
                ans += size * (size - 1) / 2; // nC2
            }
            return ans;
        }
        for (Integer low : sumToIndexes.keySet()) {
            List<Integer> highIndexes = sumToIndexes.get(low + k);
            if (highIndexes == null) continue;
            // Both low and high indexes exist
            List<Integer> lowIndexes = sumToIndexes.get(low);
            for (Integer lowIndex : lowIndexes) {
                int i = Collections.binarySearch(highIndexes, lowIndex);
                ans += highIndexes.size() - ~i;
            }
        }
        return ans;
    }
}
