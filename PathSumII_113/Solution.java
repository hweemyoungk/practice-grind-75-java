package PathSumII_113;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.



Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: []
Example 3:

Input: root = [1,2], targetSum = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000

[5/14/2024]19m out of 25m
100%/78%
O(n for preorder traversal + nlog(n) ~ n^2 for copy = n^2)/O(log(n)~n)
*/
class Solution {
    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private final LinkedList<List<Integer>> ans = new LinkedList<>();
    private final ArrayList<Integer> currPath = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ans;
    }

    private void dfs(TreeNode root, int remaining) {
        // root can be null
        if (root == null) return;
        currPath.add(root.val);
        remaining -= root.val;
        if (root.left == null && root.right == null && remaining == 0) {
            // Copy and add to ans
            ans.add(new ArrayList<>(currPath));
        } else {
            // Traverse
            dfs(root.left, remaining);
            dfs(root.right, remaining);
        }
        currPath.remove(currPath.size() - 1);
    }
}
