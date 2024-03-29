package AddBinary_67;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= a.length, b.length <= 10^4
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
/*
* [3/28/2024]Time up
* 100%/67%
* O(max(m,n))/O(max(m,n)))*/
class Solution {
    public String addBinary(String a, String b) {
        char[] result = new char[Math.max(a.length(), b.length())];
        int k = result.length - 1;
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (!(i < 0 && j < 0)) {
            int va = i >= 0 ? a.charAt(i) - 48 : 0;
            int vb = j >= 0 ? b.charAt(j) - 48 : 0;
            int add = va+vb+carry;
            carry = add>>1;
            result[k] = (char)((add&1)+48);
            i--;
            j--;
            k--;
        }
        return carry==0 ? String.valueOf(result) : '1' + String.valueOf(result);
    }
}
