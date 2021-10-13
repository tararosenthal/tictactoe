package tictactoe;

import java.util.Objects;

public class InARowUtils<T> {
    private final T[][] array;
    private final T piece;
    private final int numOfRows;
    private final int numOfColumns;

    public InARowUtils(T[][] array, T piece) {
        this.array = array;
        this.piece = piece;
        this.numOfRows = array.length;
        this.numOfColumns = array[0].length;

        for (T[] innerArray: array) {
            if (innerArray.length != numOfColumns) {
                throw new IndexOutOfBoundsException("2D Array must be rectangular to use InARowUtils");
            }
        }
    }

    public boolean checkStraight(int numInARow, boolean horizontal) {
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

    public boolean checkDiagonal(int numInARow) {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (checkBackSlash(numInARow, i, j)
                        || checkForwardSlash(numInARow, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBackSlash(int numInARow, int row,
                                   int column) {
        int plusLimit = Math.min(numOfRows - 1 - row, numOfColumns - 1 - column);
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

    private boolean checkForwardSlash(int numInARow, int row,
                                      int column) {
        int leftLimit = Math.min(numOfRows - 1 - row, column);
        int rightLimit = Math.min(row, numOfColumns - 1 - column);
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
