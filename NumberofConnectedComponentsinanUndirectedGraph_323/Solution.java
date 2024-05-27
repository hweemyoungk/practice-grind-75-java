package NumberofConnectedComponentsinanUndirectedGraph_323;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.



Example 1:


Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1


Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.

[5/27/2024]21m out of 30m
33%/24%
O(#nodes+#edges)/O(#nodes+#edges)
*/
class Solution {
    static class Vertex {
        int val;
        final List<Integer> neighbors = new LinkedList<>();

        public Vertex(int val) {
            this.val = val;
        }
    }

    // BFS
    public int countComponents(int n, int[][] edges) {
        // Draw graph
        Vertex[] vertices = new Vertex[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int[] edge : edges) {
            vertices[edge[0]].neighbors.add(edge[1]);
            vertices[edge[1]].neighbors.add(edge[0]);
        }
        int ans = 0;
        Queue<Vertex> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            // New component
            ans++;
            visited[i] = true;
            for (int iNext : vertices[i].neighbors) {
                queue.add(vertices[iNext]);
            }
            vertices[i].neighbors.clear();
            // BFS
            while (!queue.isEmpty()) {
                Vertex curr = queue.poll();
                if (visited[curr.val]) continue;
                visited[curr.val] = true;
                for (int iNext : curr.neighbors) {
                    if (visited[iNext]) continue;
                    queue.add(vertices[iNext]);
                }
                curr.neighbors.clear();
            }
        }
        return ans;
    }

    /*// Topological sort
    public int countComponents(int n, int[][] edges) {
        // Draw graph
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int[] edge : edges) {
            vertices[edge[0]].neighbors.add(edge[1]);
            vertices[edge[1]].neighbors.add(edge[0]);
        }
        int ans = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (Vertex vertex : vertices) {
            int nNeighbors = vertex.neighbors.size();
            if (nNeighbors == 0) {
                ans++;
                n--;
                continue;
            }
            if (nNeighbors == 1) {
                queue.add(vertex.val);
            }
        }
        while (!queue.isEmpty()) {
            n--;
            Vertex curr = vertices[queue.poll()];
            Vertex next = vertices[curr.neighbors.get(0)];
            next.neighbors.remove(curr.val);
            int nNeighbors = next.neighbors.size();
            if (nNeighbors == 0) {
                // Centric of component: process vertex w/o enqueuing.
                n--;
                ans++;
                continue;
            }
            if (nNeighbors == 1) {
                queue.add(next.val);
            }
        }
    }*/
}
