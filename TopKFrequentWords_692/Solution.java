package TopKFrequentWords_692;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/*Given an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.



Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.


Constraints:

1 <= words.length <= 500
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
k is in the range [1, The number of unique words[i]]


Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?

[5/7/2024]Time up
5%/47% (...)
O(n + klog(k) ~ nlog(k))/O(n)
*/
class Solution {
    private Integer badPivot = null;
    private final ThreadLocalRandom current = ThreadLocalRandom.current();
    private final List<Map.Entry<String, Integer>> ans = new LinkedList<>();

    public List<String> topKFrequent(String[] words, int k) {
        // Quickselect?
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            Integer cnt = wordCount.computeIfAbsent(word, s -> 0);
            wordCount.put(word, cnt + 1);
        }
        List<Map.Entry<String, Integer>> list = wordCount.entrySet().stream().toList();
        if (k < wordCount.size()) {
            while (ans.size() < k) {
                list = quickselect(list, k - ans.size());
            }
        } else if (k == wordCount.size()) {
            ans.addAll(list);
        }
        // Sort ans
        ans.sort((e1, e2) -> e1.getValue().equals(e2.getValue()) ? e1.getKey().compareTo(e2.getKey()) : e2.getValue().compareTo(e1.getValue()));
        return ans.stream().limit(k).map(Map.Entry::getKey).toList();
    }

    private List<Map.Entry<String, Integer>> quickselect(List<Map.Entry<String, Integer>> list, int k) {
        // Pick pivot
        List<Map.Entry<String, Integer>> candidates = badPivot == null ? list : list.stream().filter(entry -> !badPivot.equals(entry.getValue())).toList();
        badPivot = null;
        Integer pivot = candidates.get(current.nextInt(candidates.size())).getValue();
        List<Map.Entry<String, Integer>> more = new LinkedList<>();
        List<Map.Entry<String, Integer>> lessOrEqual = new LinkedList<>();
        boolean hasMultipleValues = false;
        for (var entry : list) {
            Integer value = entry.getValue();
            if (!pivot.equals(value)) hasMultipleValues = true;
            if (pivot < value) {
                more.add(entry);
                continue;
            }
            lessOrEqual.add(entry);
        }
        if (!hasMultipleValues) {
            // Only single values left: Add all
            ans.addAll(lessOrEqual);
            return lessOrEqual;
        }
        if (more.size() == 0) {
            badPivot = pivot;
        }
        if (more.size() <= k) {
            // Append to ans
            ans.addAll(more);
            return lessOrEqual;
        }
        return more;
    }

    public static void main(String[] args) {
        String[] words = {
                "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"
        };
        int k = 4;
        List<String> strings = new Solution().topKFrequent(words, k);
        System.out.println(strings);
    }
}
