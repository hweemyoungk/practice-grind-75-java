package BinaryTreeRightSideView_199;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.



Example 1:


Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Example 2:

Input: root = [1,null,3]
Output: [1,3]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

[4/11/2024]7m out of 20m
69%/39%
O(#nodes)/O(~#nodes)
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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};
        while (true) {
            Queue<TreeNode> nextQueue = new LinkedList<>();
            TreeNode curr = queue.remove();
            ans.add(curr.val);
            if (curr.right != null) nextQueue.add(curr.right);
            if (curr.left != null) nextQueue.add(curr.left);
            while (!queue.isEmpty()) {
                curr = queue.remove();
                if (curr.right != null) nextQueue.add(curr.right);
                if (curr.left != null) nextQueue.add(curr.left);
            }
            if (nextQueue.isEmpty()) break;
            queue = nextQueue;
        }
        return ans;
    }
}
