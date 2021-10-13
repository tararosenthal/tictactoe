package tictactoe;

import java.util.Objects;

public class InARowUtils<T> {
    private final T[][] array;
    private final T piece;

    public InARowUtils(T[][] array, T piece) {
        this.array = array;
        this.piece = piece;
    }

    public boolean checkStraight(int numOfRows, int numOfColumns,
                                 int numInARow, boolean horizontal) {
        for (int i = 0; i < numOfRows; i++) {
            int pieceCount = 0;
            for (int j = 0; j < numOfColumns; j++) {
                int row;
                int column;
                if (horizontal) {
                    row = i;
                    column = j;
                } else {
                    row = j;
                    column = i;
                }
                if (Objects.equals(array[row][column], piece)) {
                    pieceCount++;
                    if (pieceCount >= numInARow) {
                        return true;
                    }
                } else {
                    pieceCount = 0;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonal(int numOfRows, int numOfColumns,
                                 int numInARow) {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (checkBackSlash(numOfRows, numOfColumns, numInARow, i, j)
                        || checkForwardSlash(numOfRows, numOfColumns, numInARow, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBackSlash(int numOfRows, int numOfColumns,
                                   int numInARow, int row,
                                   int column) {
        int plusLimit = Math.min(--numOfRows - row, --numOfColumns - column);
        int minusLimit = Math.min(row, column);
        int maxShift = plusLimit + minusLimit + 1;
        row -= minusLimit;
        column -= minusLimit;
        int pieceCount = 0;

        for (int i = 0; i < maxShift; i++) {
            if (Objects.equals(array[row + i][column + i], piece)) {
                pieceCount++;
                if (pieceCount >= numInARow) {
                    return true;
                }
            } else {
                pieceCount = 0;
            }
        }
        return false;
    }

    private boolean checkForwardSlash(int numOfRows, int numOfColumns,
                                      int numInARow, int row,
                                      int column) {
        int leftLimit = Math.min(--numOfRows - row, column);
        int rightLimit = Math.min(row, --numOfColumns - column);
        int maxShift = leftLimit + rightLimit + 1;
        row += leftLimit;
        column -= leftLimit;
        int pieceCount = 0;

        for (int i = 0; i < maxShift; i++) {
            if (Objects.equals(array[row - i][column + i], piece)) {
                pieceCount++;
                if (pieceCount >= numInARow) {
                    return true;
                }
            } else {
                pieceCount = 0;
            }
        }
        return false;
    }
}
