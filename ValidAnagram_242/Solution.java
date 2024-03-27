package ValidAnagram_242;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
/*
* [3/26/2024]6m out of 15m
* 44%/95%
* O(n)/O(1)
* */
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] charDiff = new int[26];
        for (int i = 0; i < s.length(); i++) {
            var c1 = s.charAt(i)-97;
            var c2 = t.charAt(i)-97;
            charDiff[c1]+=1;
            charDiff[c2]-=1;
        }
        for (int i = 0; i < 26; i++) {
            if (charDiff[i]!=0) return false;
        }
        return true;
    }
}