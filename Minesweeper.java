/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Minesweeper {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        int[][] grid = new int[m][n];
        int countMines = k;
        while (countMines > 0) {
            int randomMine = (int) (Math.random() * (m * n));
            int x = randomMine / n;
            int y = randomMine % n;
            if (grid[x][y] == 0) {
                grid[x][y] = k;
                countMines--;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                int topLeft, left, bottomLeft, top, bottom, topRight, right, bottomRight;
                if (i - 1 >= 0) {
                    top = grid[i - 1][j];
                    if (top == k)
                        count++;
                    if (j - 1 >= 0) {
                        topLeft = grid[i - 1][j - 1];
                        if (topLeft == k)
                            count++;
                    }
                }
                if (j - 1 >= 0) {
                    left = grid[i][j - 1];
                    if (left == k)
                        count++;
                    if (i + 1 < m) {
                        bottomLeft = grid[i + 1][j - 1];
                        if (bottomLeft == k)
                            count++;
                    }
                }
                if (j + 1 < n) {
                    right = grid[i][j + 1];
                    if (right == k)
                        count++;
                    if (i - 1 >= 0) {
                        topRight = grid[i - 1][j + 1];
                        if (topRight == k)
                            count++;
                    }
                    if (i + 1 < m) {
                        bottomRight = grid[i + 1][j + 1];
                        if (bottomRight == k)
                            count++;
                    }
                }
                if (i + 1 < m) {
                    bottom = grid[i + 1][j];
                    if (bottom == k)
                        count++;
                }
                if (k > 0) {
                    if (grid[i][j] == k)
                        System.out.print("*  ");
                    else
                        System.out.print(count + "  ");
                } else {
                    System.out.print("0  ");
                }
            }
            System.out.println();
        }
    }
}
