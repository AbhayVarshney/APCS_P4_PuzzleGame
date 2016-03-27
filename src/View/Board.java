package View;

import javax.swing.*;
import java.awt.*;

/**
 * APCS_P4_PuzzleGame
 * Name: Abhay Varshney   Period 4   3/26/16
 * Time Spent: 2:20 PM to
 * Reflection:
 */
public class Board extends JPanel {
    PGView view;
    int boardSize;

    Board(PGView view) {
        this.view = view;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
    }

    void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
