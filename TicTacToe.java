package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {
    public TicTacToe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(465, 505);
        setLayout(new BorderLayout());
        setVisible(true);
        setTitle("Tic Tac Toe");

        Board board = new Board();
        add(board, BorderLayout.CENTER);
    }
}