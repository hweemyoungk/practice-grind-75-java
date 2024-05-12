package GraphValidTree_261;

import java.util.*;

/*You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.



Example 1:


Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false


Constraints:

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.

[5/12/2024]14m out of 30m
30%/9%
O(#nodes + #edges)/O(#nodes + #edges)
c.f. In a valid tree, #edges == #nodes - 1

*/
class Solution {
    static class Node {
        final Map<Integer, Node> neighbors = new HashMap<>();
        final int val;

        public Node(int val) {
            this.val = val;
        }
    }

    // Cycle detection
    // Topological sorting
    public boolean validTree(int n, int[][] edges) {
        // Corner case
        if (n == 1) return true;

        // Draw graph
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] edge : edges) {
            nodes[edge[0]].neighbors.put(edge[1], nodes[edge[1]]);
            nodes[edge[1]].neighbors.put(edge[0], nodes[edge[0]]);
        }
        // Queue nodes with indegree == 1
        Queue<Node> queue = new LinkedList<>();
        for (Node node : nodes) {
            // What if there's isolated nodes? That's not a valid tree!
            int size = node.neighbors.size();
            if (size == 0) return false;
            if (size == 1) queue.add(node);
        }
        // Sort
        int nRemaining = n;
        while (!queue.isEmpty()) {
            n--;
            Node curr = queue.remove();
            Node counterpart = curr.neighbors.entrySet().iterator().next().getValue(); // (...)
            counterpart.neighbors.remove(curr.val);
            int size = counterpart.neighbors.size();
            if (size == 1) {
                queue.add(counterpart);
                continue;
            }
            if (size == 0) {
                n--;
                break;
            }
        }
        return n == 0;
    }
}
