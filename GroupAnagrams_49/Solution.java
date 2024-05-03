package GroupAnagrams_49;

import java.util.*;

/*Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]


Constraints:

1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.

[4/30/2024]18m out of 25m
25%/15%
O(#chars + 26*#words)/O(#groups + #words)
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> counterStringsMap = new HashMap<>();
        int[] counter = new int[26];
        for (String str : strs) {
            Arrays.fill(counter, 0);
            for (int i = 0; i < str.length(); i++) {
                counter[str.charAt(i) - 'a']++;
            }
            // counter to string representation
            StringBuilder stringBuilder = new StringBuilder();
            for (int count : counter) {
                stringBuilder.append('#');
                stringBuilder.append((char) count);
            }
            String key = stringBuilder.toString();
            List<String> strings = counterStringsMap.get(key);
            if (strings == null) {
                counterStringsMap.put(key, new LinkedList<>() {{
                    add(str);
                }});
                continue;
            }
            strings.add(str);
        }
        return new ArrayList<>(counterStringsMap.values());
    }

    // ArrayList solution
    /*public List<List<String>> groupAnagrams(String[] strs) {
        Map<ArrayList<Integer>, List<String>> counterStringsMap = new HashMap<>();
        for (String str : strs) {
            Integer[] counter = new Integer[26];
            Arrays.fill(counter, 0);
            for (int i = 0; i < str.length(); i++) {
                counter[str.charAt(i) - 'a']++;
            }
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(counter));
            List<String> strings = counterStringsMap.get(list);
            if (strings == null) {
                List<String> newList = new LinkedList<>() {{
                    add(str);
                }};
                counterStringsMap.put(list, newList);
                continue;
            }
            strings.add(str);
        }
        return counterStringsMap.values().stream().toList();
    }*/
}
