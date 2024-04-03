package CoinChange_322;

import java.util.HashMap;
import java.util.Map;

/*You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4

[4/2/2024]19m out of 25m
7%/8% (HashMap)
37%/99% (array)
O(amount*len(coins))/O(amount)
*/
class Solution {
    //private Map<Integer, Integer> amountToMinCoin = new HashMap<>();
    private int[] amountToMinCoin;
    private int[] coins;

    public int coinChange(int[] coins, int amount) {
        // Backtrack + memoization
        this.coins = coins;
        amountToMinCoin = new int[amount];
        return backtrack(amount);
    }

    private int backtrack(int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        // amount>1
        //if (amountToMinCoin.containsKey(amount)) {
        //    return amountToMinCoin.get(amount);
        //}
        if (amountToMinCoin[amount-1]!=0) {
            return amountToMinCoin[amount-1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int n = backtrack(amount - coins[i]);
            if (n == -1) continue;
            min = Math.min(min, n);
        }
        if (min == Integer.MAX_VALUE) {
            // amountToMinCoin.put(amount, -1);
            amountToMinCoin[amount-1] = -1;
            return -1;
        }
        // amountToMinCoin.put(amount, min + 1);
        amountToMinCoin[amount-1] = min + 1;
        return min + 1;
    }
}
