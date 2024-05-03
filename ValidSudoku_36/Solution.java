package ValidSudoku_36;

/*Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.

[4/30/2024]12m out of 35m
100%/60%
O(side^2)/O(side)
*/
class Solution {
    private boolean[] visited = new boolean[9];

    public boolean isValidSudoku(char[][] board) {
        // Row
        for (int i = 0; i < 9; i++) {
            clearVisited();
            char[] row = board[i];
            for (int j = 0; j < 9; j++) {
                if (row[j] == '.') continue;
                if (visited[row[j] - '1']) return false;
                visited[row[j] - '1'] = true;
            }
        }
        // Column
        for (int i = 0; i < 9; i++) {
            clearVisited();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') continue;
                if (visited[board[j][i] - '1']) return false;
                visited[board[j][i] - '1'] = true;
            }
        }
        // Sub-box
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clearVisited();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (board[3 * i + k][3 * j + l] == '.') continue;
                        if (visited[board[3 * i + k][3 * j + l] - '1']) return false;
                        visited[board[3 * i + k][3 * j + l] - '1'] = true;
                    }
                }
            }
        }
        return true;
    }

    private void clearVisited() {
        for (int i = 0; i < 9; i++) {
            visited[i] = false;
        }
    }
}
