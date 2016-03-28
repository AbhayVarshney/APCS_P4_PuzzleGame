package View;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    PGView view;

    /** BOARD **/
    int cellSize;

    Board(PGView view, int cellSize) {
        this.view = view;
        this.cellSize = cellSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        setBackground(new Color(59,59,59));

        for (int i = 0; i < view.boardContent.length; i++) {
            for (int j = 0; j < view.boardContent[0].length; j++) {
                if(view.boardContent[i][j] == view.controller.model.BLOCK) {
                    g.drawImage(view.wall, cellSize*j, cellSize*i, cellSize, cellSize, null);
                } else if(view.boardContent[i][j] == view.controller.model.CRATE) {
                    g.drawImage(view.crate, cellSize*j, cellSize*i, cellSize, cellSize, null);
                } else if(view.boardContent[i][j] == view.controller.model.SPRITE) {
                    g.drawImage(view.userSprite, cellSize*j, cellSize*i, cellSize, cellSize, null);
                } else if(view.boardContent[i][j] == view.controller.model.EMPTY) {
                    g.setColor(new Color(42,42,42));
                    g.fillRect(cellSize*j, cellSize*i, cellSize, cellSize);
                } else if(view.boardContent[i][j] == view.controller.model.VICTORY_TILE) {
                    g.drawImage(view.victory_tile, cellSize*j, cellSize*i, cellSize, cellSize, null);
                } else if(view.boardContent[i][j] == view.controller.model.COMPLETED_CRATE) {
                    g.drawImage(view.completedCrate, cellSize*j, cellSize*i, cellSize, cellSize, null);
                } else { // backdrop
                    g.setColor(new Color(59,59,59));
                    g.fillRect(cellSize*j, cellSize*i, cellSize, cellSize);
                }
                g2.setColor(Color.BLACK);
            }
        }
    }
}
