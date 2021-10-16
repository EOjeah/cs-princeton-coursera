/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] linearGridStatus;
    private final int sizeOfGrid;
    private int openSpaces = 0;
    private final int virtualTopIndex;
    private final int virtualBottomIndex;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n too small");
        }
        // node = new int[n][n];
        // grid = new boolean[n][n];
        sizeOfGrid = n;
        // openSpaces = 0;
        // parent = new int[n * n];
        // size = new int[n * n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         node[i][j] = (i * n) + j;
        //         grid[i][j] = false;
        //     }
        // }
        // for (int[] row : node) {
        //     System.out.println(Arrays.toString(row));
        // }
        virtualTopIndex = n*n;
        virtualBottomIndex = n*n + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF((n*n) + 2);
        linearGridStatus = new boolean[n*n];
        for (int i = 0; i < n*n; i++) {
            // if (i < n) {
            //     weightedQuickUnionUF.union(i, n*n);
            // }
            // else if (i >= (n*n) - n) {
            //     weightedQuickUnionUF.union(i, (n*n) + 1);
            // }
            linearGridStatus[i] = false;
        }
    }

    public void open(int row, int col) {
        // System.out.println("===================================");
        validateRowAndCol(row, col);
        int index = translatePositionToIndex(row, col);
        if (row == 1) {
            weightedQuickUnionUF.union(index, virtualTopIndex);
        }
        if (row == sizeOfGrid) {
            weightedQuickUnionUF.union(index, virtualBottomIndex);
        }

        if (!linearGridStatus[index]) {
            linearGridStatus[index] = true;
            openSpaces++;
        }

        int[] x = getAdjacentCells(row, col);

        for (int i : x) {
            if (i < 0) continue;
            int iCol = (i % sizeOfGrid) + 1;
            int iRow = (i / sizeOfGrid) + 1;
            // System.out.printf("when i=%d then index is (%d, %d)\n", i, iRow, iCol);
            if (isOpen(iRow, iCol)) {
                // System.out.println(Arrays.toString(linearGridStatus));
                // System.out.printf("%d->%d", index, i);
                // System.out.println();
                weightedQuickUnionUF.union(index, i);
            }
        }
        // System.out.println(linearGridStatus);
        // System.out.printf("Adjacent cells of %d=", index);
        // System.out.println(Arrays.toString(x));
        // int count = weightedQuickUnionUF.count();
        // System.out.println("Open spaces ");
        // System.out.println(openSpaces);
    }

    public boolean isOpen(int row, int col) {
        validateRowAndCol(row, col);
        int index = translatePositionToIndex(row, col);
        // System.out.printf("row: %d col: %d index: %d\n", row, col, index);
        // System.out.println(Arrays.toString(linearGridStatus));
        return linearGridStatus[index];
    }

    public boolean isFull(int row, int col) {
        validateRowAndCol(row, col);
        int index = translatePositionToIndex(row, col);
        int pID = weightedQuickUnionUF.find(index);
        int qID = weightedQuickUnionUF.find(sizeOfGrid*sizeOfGrid);
        if (pID == qID) {
            // System.out.println("is Full!");
            return true;
        }
        // System.out.println("not full :(");
        return false;
    }

    public int numberOfOpenSites() {
        return openSpaces;
    }

    public boolean percolates() {
        int pID = weightedQuickUnionUF.find(virtualTopIndex);
        int qID = weightedQuickUnionUF.find(virtualBottomIndex);
        // if (pID == qID) {
        //     System.out.println("System percolates!");
        // } else {
        //     System.out.println("System does not percolate");
        // }
        return (qID == pID);
    }

    private void validateRowAndCol(int row, int col) {
        if (row < 1 || row > sizeOfGrid || col < 1 || col > sizeOfGrid) {
            throw new IllegalArgumentException("out of bounds");
        }
    }

    private int translatePositionToIndex(int i, int j) {
        int transposeI = i - 1;
        int transposeJ = j - 1;
        return transposeI * this.sizeOfGrid + transposeJ;
    }

    private int[] getAdjacentCells(int i, int j) {
        int left = -1, right = -1, top = -1, down = -1;
        if (i == 1) {
            down = translatePositionToIndex(i + 1, j);
        } else if (i == this.sizeOfGrid) {
            top = translatePositionToIndex(i-1, j);
        } else {
            top = translatePositionToIndex(i-1, j);
            down = translatePositionToIndex(i + 1, j);
        }

        if (j == 1) {
            right = translatePositionToIndex(i, j + 1);
        } else if (j == this.sizeOfGrid) {
            left = translatePositionToIndex(i, j - 1);
        } else {
            right = translatePositionToIndex(i, j + 1);
            left = translatePositionToIndex(i, j - 1);
        }
        int[] adjacentCells = {top, left, right, down};

        // adjacentCells = Arrays.stream(adjacentCells).filter(value -> value > -1).toArray();
        return adjacentCells;
    }

    public static void main(String[] args) {
        Percolation id = new Percolation(10);
        id.open(5, 0);
        // id.isFull(3,3);
        // System.out.println(id.percolates());
        // WeightedQuickUnionUF
    }
}