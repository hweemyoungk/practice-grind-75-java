package FindAllAnagramsinaString_438;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".


Constraints:

1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.

[4/18/2024]20m out of 30m
55%/33%
O(len(p)+26+len(s)-len(p) ~ len(s))/O(1)
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new LinkedList<>();
        int ls = s.length();
        int lp = p.length();
        if (ls < lp) return ans;
        // Sliding window
        int[] counter = new int[26];
        for (int i = 0; i < lp; i++) {
            counter[p.charAt(i) - 'a']--;
            counter[s.charAt(i) - 'a']++;
        }
        Set<Integer> iNonZeros = new HashSet<>(26);
        for (int i = 0; i < 26; i++) {
            if (counter[i] != 0) iNonZeros.add(i);
        }
        if (iNonZeros.size() == 0) ans.add(0);
        for (int iCurr = 1; iCurr <= ls - lp; iCurr++) {
            int iRemove = s.charAt(iCurr - 1) - 'a';
            if (--counter[iRemove] == 0) {
                iNonZeros.remove(iRemove);
            } else {
                iNonZeros.add(iRemove);
            }
            int iAdd = s.charAt(iCurr + lp - 1) - 'a';
            if (++counter[iAdd] == 0) {
                iNonZeros.remove(iAdd);
            } else {
                iNonZeros.add(iAdd);
            }
            if (iNonZeros.size() == 0) ans.add(iCurr);
        }
        return ans;
    }
}
