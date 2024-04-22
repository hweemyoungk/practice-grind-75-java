package DailyTemperatures_739;

/*Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100

[4/22/2024]9m out of 30m
45%/87%
O(n)/O(n)
9m out of 30m [Editorial]
96%/48%
O(n)/O(1)
*/
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        for (int i = temperatures.length - 2; i >= 0; i--) {
            // Look forward
            int curr = temperatures[i];
            if (curr < temperatures[i + 1]) {
                ans[i] = 1;
                continue;
            }
            // Next is not warmer than curr
            int j = i + 1;
            while (true) {
                if (ans[j] == 0) {
                    ans[i] = 0;
                    break;
                }
                j = j + ans[j];
                if (curr < temperatures[j]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        // ...
        return ans;
    }
    /*public int[] dailyTemperatures(int[] temperatures) {
        // Stack solution
        int[] ans = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int[] curr = {temperatures[i], i};
            // Look back stack
            while (!stack.isEmpty()) {
                int[] prev = stack.pop();
                if (prev[0] >= curr[0]) {
                    // Back to stack and break
                    stack.push(prev);
                    break;
                }
                // Curr is warmer than prev
                ans[prev[1]] = i - prev[1];
            }
            // Push curr to stack
            stack.push(curr);
        }
        return ans;
    }*/
}
