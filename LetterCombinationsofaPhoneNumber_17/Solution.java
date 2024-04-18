package LetterCombinationsofaPhoneNumber_17;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.




Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]


Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].

[4/17/2024]13m out of 30m
100%/27%
O(l*(avg(#candidates)^l))/O(l)
where l == len(digits)
*/
class Solution {
    private final Map<Character, char[]> digitCharsMap = Map.of(
            '2', new char[]{'a', 'b', 'c'},
            '3', new char[]{'d', 'e', 'f'},
            '4', new char[]{'g', 'h', 'i'},
            '5', new char[]{'j', 'k', 'l'},
            '6', new char[]{'m', 'n', 'o'},
            '7', new char[]{'p', 'q', 'r', 's'},
            '8', new char[]{'t', 'u', 'v',},
            '9', new char[]{'w', 'x', 'y', 'z'});
    private String digits;
    private char[] currChars;
    private final List<String> ans = new LinkedList<>();

    public List<String> letterCombinations(String digits) {
        // Corner case
        if (digits.length() == 0) return ans;

        this.digits = digits;
        this.currChars = new char[digits.length()];
        backtrack(0);
        return ans;
    }

    private void backtrack(int i) {
        if (i == digits.length()) {
            ans.add(String.valueOf(currChars));
            return;
        }
        for (char c : digitCharsMap.get(digits.charAt(i))) {
            currChars[i] = c;
            backtrack(i + 1);
        }
    }
}
