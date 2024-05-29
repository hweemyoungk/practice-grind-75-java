package AsteroidCollision_735;

import java.util.*;

/*We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.



Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.


Constraints:

2 <= asteroids.length <= 10^4
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0

[5/29/2024]15m out of 30m
92%/28%
O(n + #collisions ~ 2n)/O(~n)
*/
class Solution {
    // Stack solution
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>() {{
            add(asteroids[0]);
        }};
        for (int i = 1; i < asteroids.length; i++) {
            int curr = asteroids[i];
            if (0 < curr) {
                stack.push(curr);
                continue;
            }
            // Negative curr
            if (stack.empty() || stack.peek() < 0) {
                // Empty or negative prev
                stack.push(curr);
                continue;
            }
            // Not empty and positive prev
            while (true) {
                Integer prev = stack.peek();
                if (prev < 0) {
                    // No collision
                    stack.push(curr);
                    break;
                }
                int sum = prev + curr;
                if (sum < 0) {
                    // Curr wins
                    stack.pop();
                    if (stack.empty()) {
                        stack.push(curr);
                        break;
                    }
                    continue;
                }
                if (sum == 0) {
                    // Both disappear
                    stack.pop();
                    break;
                }
                // 0 < sum
                // Prev wins
                break;
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[ans.length - 1 - i] = stack.pop();
        }
        return ans;
    }

    /*public int[] asteroidCollision(int[] asteroids) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int asteroid : asteroids) {
            list.add(asteroid);
        }
        ListIterator<Integer> iterator = list.listIterator();
        iterator.next();
        Integer curr = iterator.next();
        Integer prev;
        Integer next;
        while (true) {
            // Look back
            iterator.previous();
            prev = iterator.previous();
            if (curr < 0 && 0 < prev) {
                // curr and prev collides
                int sum = curr + prev;
                if (sum < 0) {
                    // curr wins: remove prev
                    iterator.remove();
                    curr = iterator.next();
                    continue;
                }
                if (sum == 0) {
                    // remove both
                    iterator.remove();
                    iterator.next();
                    iterator.remove();
                    curr = iterator.next();
                    continue;
                }
                // 0 < sum
                // prev wins: remove curr
                curr = iterator.next();
                iterator.next();
                iterator.remove();
                continue;
            }
            // Restore to curr
            iterator.next();
            iterator.next();
            // Look forward
            if (!iterator.hasNext()) break;
            next = iterator.next();
            if (0 < curr && next < 0) {
                // curr and next collides
                int sum = curr + next;
                if (sum < 0) {
                    // next wins: remove curr
                    iterator.previous();
                    iterator.previous();
                    iterator.remove();
                    curr = iterator.next();
                    continue;
                }
                if (sum == 0) {
                    // remove both
                    iterator.remove();
                    iterator.previous();
                    iterator.remove();
                    if (!iterator.hasNext()) break;
                    curr = iterator.next();
                    continue;
                }
                // 0 < sum
                // curr wins: remove next
                iterator.remove();
                curr = iterator.previous();
                continue;
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }*/

    public static void main(String[] args) {
        int[] asteroids = new int[]{5, -10, -15};
        int[] ans = new Solution().asteroidCollision(asteroids);
        System.out.println(ans);
    }
}
