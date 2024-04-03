package CourseSchedule_207;

import java.util.LinkedList;
import java.util.List;

/*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.

[4/2/2024]17m out of 30m
95%/55%
O(#courses+#prerequisites)/O(#courses+#prerequisites)
*/
class Solution {
    private Course[] courses;
    private boolean[] memo;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Draw graph
        courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (var prerequisite : prerequisites) {
            courses[prerequisite[0]].dependencies.add(prerequisite[1]);
        }

        // DFS + memoization
        memo = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!backtrack(courses[i], visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean backtrack(Course curr, boolean[] visited) {
        if (memo[curr.val]) return true;
        if (visited[curr.val]) return false;
        visited[curr.val] = true;
        for (var next : curr.dependencies) {
            if (!backtrack(courses[next], visited)) {
                return false;
            }
        }
        visited[curr.val] = false;
        memo[curr.val] = true;
        return true;
    }
}

class Course {
    int val;
    List<Integer> dependencies = new LinkedList<>();

    public Course(int val) {
        this.val = val;
    }
}
