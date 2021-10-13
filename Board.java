package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Board extends JPanel {
    private final List<Cell> cells = new ArrayList<>();
    private final String[][] cellValuesArray = new String[3][3];
    private boolean isPlayerXTurn = true;
    private final StatusBar statusBar;
    private boolean gameOver = false;

    public Board() {
        this.setSize(450, 475);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        JPanel jCells = new JPanel();
        jCells.setSize(450, 450);
        jCells.setVisible(true);
        jCells.setLayout(new GridLayout(3, 3));
        createCells();
        for (Cell cell: cells) {
            jCells.add(cell);
        }
        this.add(jCells, BorderLayout.CENTER);

        statusBar = new StatusBar(this);
        this.add(statusBar, BorderLayout.SOUTH);

        for (String[] array: cellValuesArray) {
            Arrays.fill(array, " ");
        }
    }

    private void createCells() {
        for (int i = 3; i > 0; i--) {
            for (char j = 'A'; j < 'D'; j++) {
                cells.add(new Cell(this, j + "" + i));
            }
        }
    }

    void resetBoard() {
        for (Cell cell: cells) {
            cell.resetCell();
        }
        isPlayerXTurn = true;
        gameOver = false;
        for (String[] array: cellValuesArray) {
            Arrays.fill(array, " ");
        }
    }

    String getPlayerTurn() {
        String temp = isPlayerXTurn ? "X" : "O";
        isPlayerXTurn = !isPlayerXTurn;
        return temp;
    }

    void checkStatus(Cell cell) {
        int index = cells.indexOf(cell);
        boolean isBoardFull = true;
        cellValuesArray[index / 3][index % 3] = cell.getText();

        for (String[] array: cellValuesArray) {
            for (String s: array) {
                if (s.isBlank()) {
                    isBoardFull = false;
                }
            }
        }

        setStatus(isBoardFull);
    }

    private void setStatus(boolean isBoardFull) {
        statusBar.setStatus(
                checkIfWin("X") ?
                        BoardStatus.X_WINS :
                        checkIfWin("O") ?
                                BoardStatus.O_WINS :
                                isBoardFull ?
                                        BoardStatus.DRAW :
                                        BoardStatus.GAME_IN_PROGRESS);
    }

    private boolean checkIfWin(String piece) {
        InARowUtils<String> inARowUtils = new InARowUtils<>(cellValuesArray, piece);

        gameOver = inARowUtils.checkStraight(3, 3, 3, true)
                || inARowUtils.checkStraight(3, 3, 3, false)
                || inARowUtils.checkDiagonal(3, 3, 3);

        return gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
