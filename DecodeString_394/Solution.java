package DecodeString_394;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collector;

/*Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].

[5/18/2024]Time up
38%/94%
O(len of decoded string)/O(len of decoded string)
*/
class Solution {
    // Stack solution
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == ']') {
                // Pop until '['
                StringBuilder tmpStringBuilder = new StringBuilder();
                while (true) {
                    char popped = stack.pop();
                    if (popped == '[') break;
                    // popped is character
                    tmpStringBuilder.append(popped);
                }
                // Pop digits
                StringBuilder digitsBuilder = new StringBuilder();
                while (!stack.isEmpty()) {
                    int popped = stack.pop() - '0';
                    if (popped < 0 || 9 < popped) {
                        // Push back
                        stack.push((char) (popped + '0'));
                        break;
                    }
                    // popped is digit
                    digitsBuilder.append(popped);
                }
                tmpStringBuilder.reverse();
                int times = Integer.parseInt(digitsBuilder.reverse().toString());
                for (int j = 0; j < times; j++) {
                    for (int k = 0; k < tmpStringBuilder.length(); k++) {
                        stack.add(tmpStringBuilder.charAt(k));
                    }
                }
                continue;
            }
            stack.push(curr);
        }
        StringBuilder decodedBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            decodedBuilder.append((char) stack.pop());
        }
        return decodedBuilder.reverse().toString();
    }
}
