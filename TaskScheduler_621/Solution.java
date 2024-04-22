package TaskScheduler_621;

import java.util.PriorityQueue;
import java.util.Queue;

/*You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.

​Return the minimum number of intervals required to complete all tasks.



Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.

Example 2:

Input: tasks = ["A","C","A","B","D","B"], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.

With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:

Input: tasks = ["A","A","A", "B","B","B"], n = 3

Output: 10

Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.

There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.



Constraints:

1 <= tasks.length <= 10^4
tasks[i] is an uppercase English letter.
0 <= n <= 100

[4/20/2024]20m out of 35m
36%/23%
O(max#ofSingleTask * n)/O(#taskTypes + n)
*/
class Solution {
    // PQ solution
    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        for (var task : tasks) {
            counter[task - 'A']++;
        }
        // PQ
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < 26; i++) {
            if (counter[i] == 0) continue;
            pq.add(counter[i]);
        }
        int t = 0;
        int[] coolingTasks = new int[n + 1];
        int nCoolingTasks = 0;
        while (true) {
            if (pq.isEmpty() && nCoolingTasks == 0) return t;
            if (coolingTasks[t % (n + 1)] != 0) {
                pq.add(coolingTasks[t % (n + 1)]);
                coolingTasks[t % (n + 1)] = 0;
                nCoolingTasks--;
            }
            if (pq.peek() != null) {
                Integer count = pq.remove();
                if (--count != 0) {
                    coolingTasks[t % (n + 1)] = count;
                    nCoolingTasks++;
                }
            }
            t++;
        }

    }
//    Sorted list solution
//    public int leastInterval(char[] tasks, int n) {
//        int[] counter = new int[26];
//        for (var task : tasks) {
//            counter[task - 'A']++;
//        }
//        // Sorted list
//        List<int[]> pq = new ArrayList<>(26);
//        Comparator<int[]> comparator = (a, b) -> b[0] - a[0];
//        for (int i = 0; i < 26; i++) {
//            if (counter[i] == 0) continue;
//            int[] weightedTask = {counter[i], i};
//            int index = Collections.binarySearch(pq, weightedTask, comparator);
//            pq.add(index < 0 ? ~index : index, weightedTask);
//        }
//        int t = 0;
//        int[] availableFrom = new int[26];
//        while (!pq.isEmpty()) {
//            int[] weightedTask = null;
//            int i;
//            for (i = 0; i < pq.size(); i++) {
//                var candidate = pq.get(i);
//                if (t < availableFrom[candidate[1]]) continue;
//                weightedTask = candidate;
//                break;
//            }
//            if (weightedTask == null) {
//                // All tasks must be cooled
//                t++;
//                continue;
//            }
//            // Remove from pq
//            pq.remove(i);
//            // Decrement count
//            if (--weightedTask[0] == 0) {
//                t++;
//                continue;
//            }
//            // Update availability
//            availableFrom[weightedTask[1]] = t + n + 1;
//            // Push to pq
//            int index = Collections.binarySearch(pq, weightedTask, comparator);
//            pq.add(index < 0 ? ~index : index, weightedTask);
//            // Increment t
//            t++;
//        }
//        return t;
//    }
}
