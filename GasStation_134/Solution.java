package GasStation_134;

/*There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique



Example 1:

Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.


Constraints:

n == gas.length == cost.length
1 <= n <= 10^5
0 <= gas[i], cost[i] <= 10^4

[4/25/2024]15m out of 30m
48%/13%
O(n)/O(1)
*/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int min = Integer.MAX_VALUE;
        // List<Integer> iMins = new LinkedList<>();
        int iMin = -1;
        int acc = 0;
        for (int i = 0; i < gas.length; i++) {
            acc += gas[i];
            acc -= cost[i];
            if (acc < min) {
                min = acc;
                iMin = i;
            }
        }

        acc = 0;
        for (int i = 0; i < gas.length; i++) {
            acc += gas[(iMin + 1 + i) % gas.length];
            acc -= cost[(iMin + 1 + i) % gas.length];
            if (acc < 0) {
                return -1;
            }
        }
        // Forward until gas is positive
        while (gas[(iMin + 1) % gas.length] == 0) {
            iMin++;
        }
        return (iMin + 1) % gas.length;
    }
    /*public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] nets = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            nets[i] = gas[i] - cost[i];
        }
        // Merge consecutive positives or negatives. Includes zeros.
        List<int[]> accs = new ArrayList<>();
        // acc is never zero
        int[] max = new int[]{0, -1};
        int[] acc = new int[]{nets[0], 0};
        for (int i = 1; i < nets.length; i++) {
            int curr = nets[i];
            if ((curr ^ acc[0]) < 0) {
                // Different signs
                if (max[0] < acc[0]) {
                    max = acc;
                }
                accs.add(acc);
                acc = new int[]{curr, i};
                continue;
            }
            // Zero or same signs
            acc[0] += curr;
        }
        if ((acc[0] ^ accs.get(0)[0]) < 0) {
            if (max[0] < acc[0]) {
                max = acc;
            }
            accs.add(acc);
        } else {
            int[] e = {accs.get(0)[0] + acc[0], acc[1]};
            if (max[0] < e[0]) {
                max = e;
            }
            accs.set(0, e);
        }

        if (max[1] == -1) return -1;

        int size = accs.size();
        int iStart = accs.indexOf(max);
        int val = 0;
        for (int i = 0; i < size; i++) {
            int[] curr = accs.get((iStart + i) % size);
            val += curr[0];
            if (val < 0) return -1;
        }
        return max[1];
    }*/

    /*public static void main(String[] args) {
        new Solution().canCompleteCircuit(new int[]{5, 8, 2, 8}, new int[]{6, 5, 6, 6});
    }*/
}
