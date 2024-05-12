package LongestIncreasingSubsequence_300;

import java.util.*;

/*Given an integer array nums, return the length of the longest strictly increasing
subsequence
.



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-10^4 <= nums[i] <= 10^4


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

[5/12/2024]19m out of 30m
79%/32%
O(nlog(n))/O(n)
*/
class Solution {
    static class Card implements Comparable<Card> {
        int val;
        Card prev;

        Card(int val, Card prev) {
            this.val = val;
            this.prev = prev;
        }

        @Override
        public int compareTo(Card card) {
            return val - card.val;
        }
    }

    // Patience-LIS implementation
    // https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
    public int lengthOfLIS(int[] nums) {
        List<Card> cards = Arrays.stream(nums).mapToObj(i -> new Card(i, null)).toList();
        ArrayList<Card> frontCards = new ArrayList<>();
        ArrayList<Stack<Card>> piles = new ArrayList<>();
        for (var curr : cards) {
            int i = Collections.binarySearch(frontCards, curr);
            if (0 <= i) {
                // Exact match: pile up
                piles.get(i).push(curr);
                frontCards.set(i, curr);
                continue;
            }
            // No exact match
            i = ~i;
            if (i == frontCards.size()) {
                // Point prev front
                if (0 < i) {
                    curr.prev = frontCards.get(i - 1);
                }
                // New pile to right
                Stack<Card> stack = new Stack<>();
                stack.add(curr);
                piles.add(stack);
                // New front to right
                frontCards.add(curr);
                continue;
            }
            // Point i-1-th front
            if (0 < i) {
                curr.prev = frontCards.get(i - 1);
            }
            // Pile up to i-th stack
            piles.get(i).push(curr);
            // Replace front
            frontCards.set(i, curr);
        }
        // Just return length
        return frontCards.size();
    }

    public static void main(String[] args) {
        int[] nums = {
            10, 9, 2, 5, 3, 7, 101, 18
        };
        int ans = new Solution().lengthOfLIS(nums);
        System.out.println(ans);
    }
}
