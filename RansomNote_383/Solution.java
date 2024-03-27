package RansomNote_383;

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote and magazine consist of lowercase English letters.
 */
/*
* [3/27/2024]13m out of 20m
* 99%/54%
* O(len(magazine)),O(1)*/
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length()<ransomNote.length()) return false;
        int[] counter = new int[26];
        int i;
        for (i = 0; i < ransomNote.length(); i++) {
            char c1 = ransomNote.charAt(i);
            counter[c1-97]-=1;
            char c2 = magazine.charAt(i);
            counter[c2-97]+=1;
        }
        int nDone=0;
        for (int j = 0; j < 26; j++) {
            if (counter[j]>=0) nDone++;
        }
        while (nDone!=26 && i<magazine.length()) {
            char c2 = magazine.charAt(i);
            if (counter[c2-97]<0) {
                counter[c2-97]+=1;
                if (counter[c2-97]==0) nDone+=1;
            }
            i++;
        }
        return nDone==26;
    }
}
