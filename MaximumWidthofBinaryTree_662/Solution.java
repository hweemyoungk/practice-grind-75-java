package MaximumWidthofBinaryTree_662;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.



Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).


Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100

[5/19/2024]15m out of 20m
36%/8%
O(N)/O(max(#nodes in a level) ~N/2)
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

    static class IndexedTreeNode {
        int index;
        TreeNode treeNode;

        public IndexedTreeNode(int index, TreeNode treeNode) {
            this.index = index;
            this.treeNode = treeNode;
        }
    }

    // BFS
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 1;
        List<IndexedTreeNode> currLevel = new ArrayList<>() {{
            add(new IndexedTreeNode(0, root));
        }};
        List<IndexedTreeNode> nextLevel = new ArrayList<>();
        while (!currLevel.isEmpty()) {
            // Width
            ans = Integer.max(ans, currLevel.get(currLevel.size() - 1).index - currLevel.get(0).index + 1);
            // Append child nodes to next level
            for (IndexedTreeNode curr : currLevel) {
                if (curr.treeNode.left != null) {
                    nextLevel.add(new IndexedTreeNode(2 * curr.index, curr.treeNode.left));
                }
                if (curr.treeNode.right != null) {
                    nextLevel.add(new IndexedTreeNode(2 * curr.index + 1, curr.treeNode.right));
                }
            }
            // Replace currLevel with nextLevel
            currLevel.clear();
            currLevel.addAll(nextLevel);
            nextLevel.clear();
        }
        return ans;
    }

    // BFS
    // OOM
//    public int widthOfBinaryTree(TreeNode root) {
//        int ans = 1;
//        List<TreeNode> currLevel = new ArrayList<>() {{
//            add(root);
//        }};
//        List<TreeNode> nextLevel = new ArrayList<>();
//        while (!currLevel.isEmpty()) {
//            Integer iFirst = null;
//            Integer iLast = null;
//            boolean nextHasNonNull = false;
//            // Append child nodes to next level
//            for (int i = 0; i < currLevel.size(); i++) {
//                TreeNode curr = currLevel.get(i);
//                if (curr == null) {
//                    nextLevel.add(null);
//                    nextLevel.add(null);
//                    continue;
//                }
//                if (iFirst == null) {
//                    iFirst = i;
//                }
//                iLast = i;
//                if (curr.left != null || curr.right != null) nextHasNonNull = true;
//                nextLevel.add(curr.left);
//                nextLevel.add(curr.right);
//            }
//            // Width
//            ans = Integer.max(ans, iLast - iFirst + 1);
//
//            if (!nextHasNonNull) break;
//            // Replace currLevel with nextLevel
//            currLevel.clear();
//            currLevel.addAll(nextLevel);
//            nextLevel.clear();
//        }
//        return ans;
//    }
}
