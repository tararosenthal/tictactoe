package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    public Cell(Board board, String label) {
        this.setName("Button" + label);
        this.setText(" ");
        this.setFont(new Font("Arial", Font.BOLD, 40));
        this.setFocusPainted(false);
        this.addActionListener(actionEvent ->
            {if(!board.isGameOver()) {
                this.setText(this.getText().isBlank() ?
                    board.getPlayerTurn() : this.getText());
            board.checkStatus(this);}});
    }

    public void resetCell() {
        this.setText(" ");
    }
}
