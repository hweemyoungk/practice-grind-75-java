package MinimumHeightTrees_310;

import java.util.*;

/*A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.



Example 1:


Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
Example 2:


Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]


Constraints:

1 <= n <= 2 * 10^4
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.

[4/18/2024]Time up
49%/68%
O(#vertices+#edges ~ #vertices)/O(#vertices+#edges ~ #vertices) where #edges==#vertices-1
*/
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return new LinkedList<>() {{
            add(0);
        }};

        // Topological sort
        // Draw graph
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int[] edge : edges) {
            nodes[edge[0]].neighbors.add(nodes[edge[1]]);
            nodes[edge[1]].neighbors.add(nodes[edge[0]]);
        }
        int remaining = n;
        Queue<TreeNode> currQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nodes[i].neighbors.size() == 1) currQueue.add(nodes[i]);
        }
        // Sort
        List<Integer> ans = new LinkedList<>();
        while (true) {
            if ((remaining == 2 && currQueue.size() == 2) || remaining == 1) {
                return currQueue.stream().map(treeNode -> treeNode.val).toList();
            }
            Queue<TreeNode> nextQueue = new LinkedList<>();
            while (!currQueue.isEmpty()) {
                remaining--;
                TreeNode curr = currQueue.remove();
                // Size of curr.neighbors is 0
                for (var neighbor : curr.neighbors) {
                    neighbor.neighbors.remove(curr);
                    if (neighbor.neighbors.size() == 1) nextQueue.add(neighbor);
                }
            }
            currQueue = nextQueue;
        }
    }

    private static class TreeNode {
        final Set<TreeNode> neighbors = new HashSet<>();
        final int val;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
