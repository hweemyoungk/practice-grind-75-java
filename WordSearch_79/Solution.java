package WordSearch_79;

import java.util.*;

/*Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.


Follow up: Could you use search pruning to make your solution faster with a larger board?

[4/17/2024]25m out of 30m
5%/16% (...)
O(m*n*3^l)/O(l) where l == len(word)
*/
class Solution {
    private char[][] board;
    private String word;
    private int m;
    private int n;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        m = board.length;
        n = board[0].length;
        // Find seed
        Queue<ArrayList<Integer>> seeds = new LinkedList<>();
        char initial = word.charAt(0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == initial) {
                    ArrayList<Integer> e = new ArrayList<>();
                    e.add(i);
                    e.add(j);
                    seeds.add(e);
                }
            }
        }
        // DFS
        Set<ArrayList<Integer>> trace = new HashSet<>();
        for (var coord : seeds) {
            if (dfs(coord, 1, trace)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(ArrayList<Integer> coord, int iNextChar, Set<ArrayList<Integer>> trace) {
        if (iNextChar == word.length()) return true;
        trace.add(coord);
        char nextChar = word.charAt(iNextChar);
        ArrayList<Integer> nextCoord = new ArrayList<>();
        if (0 < coord.get(0)) {
            nextCoord.add(coord.get(0) - 1);
            nextCoord.add(coord.get(1));
            if (!trace.contains(nextCoord) && board[nextCoord.get(0)][nextCoord.get(1)] == nextChar && dfs(nextCoord, iNextChar + 1, trace)) {
                return true;
            }
            nextCoord.clear();
        }
        if (coord.get(0) < m - 1) {
            nextCoord.add(coord.get(0) + 1);
            nextCoord.add(coord.get(1));
            if (!trace.contains(nextCoord) && board[nextCoord.get(0)][nextCoord.get(1)] == nextChar && dfs(nextCoord, iNextChar + 1, trace)) {
                return true;
            }
            nextCoord.clear();
        }
        if (0 < coord.get(1)) {
            nextCoord.add(coord.get(0));
            nextCoord.add(coord.get(1) - 1);
            if (!trace.contains(nextCoord) && board[nextCoord.get(0)][nextCoord.get(1)] == nextChar && dfs(nextCoord, iNextChar + 1, trace)) {
                return true;
            }
            nextCoord.clear();
        }
        if (coord.get(1) < n - 1) {
            nextCoord.add(coord.get(0));
            nextCoord.add(coord.get(1) + 1);
            if (!trace.contains(nextCoord) && board[nextCoord.get(0)][nextCoord.get(1)] == nextChar && dfs(nextCoord, iNextChar + 1, trace)) {
                return true;
            }
            nextCoord.clear();
        }
        trace.remove(coord);
        return false;
    }
}
