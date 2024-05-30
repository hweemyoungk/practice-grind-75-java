package KthLargestElementinanArray_215;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/*Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?



Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4


Constraints:

1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

[5/30/2024]22m out of 30m
56%/6%
O(n)/O(n)
56%/47% (Using java.util.Arrays... Why?)
O(n)/O(n)
*/
class Solution {
    // Quickselect solution

    private Integer badPivot;
    private int remaining;
    private int ans = Integer.MAX_VALUE;

    public int findKthLargest(int[] nums, int k) {
        remaining = k;
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        while (0 < remaining) {
            list = quickselect(list);
        }
        return ans;
    }

    private List<Integer> quickselect(List<Integer> nums) {
        List<Integer> candidates = badPivot == null ? nums : nums.stream().filter(integer -> !Objects.equals(integer, badPivot)).toList();
        badPivot = null;
        Integer pivot = candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
        // Larger
        List<Integer> larger = new LinkedList<>();
        // EqualOrLess
        List<Integer> equalOrLess = new LinkedList<>();
        boolean hasMultipleUniques = false;
        for (Integer num : nums) {
            if (!Objects.equals(pivot, num)) {
                hasMultipleUniques = true;
            }
            if (pivot < num) {
                larger.add(num);
                continue;
            }
            equalOrLess.add(num);
        }
        if (!hasMultipleUniques) {
            // Only has pivot
            remaining -= equalOrLess.size();
            for (var num : equalOrLess) {
                ans = Integer.min(ans, num);
            }
            return equalOrLess;
        }
        // Has multiple unique values
        if (larger.isEmpty()) {
            badPivot = pivot;
        }
        if (larger.size() <= remaining) {
            remaining -= larger.size();
            for (var num : larger) {
                ans = Integer.min(ans, num);
            }
            return equalOrLess;
        }
        // larger.size() > k
        return larger;
    }
}
