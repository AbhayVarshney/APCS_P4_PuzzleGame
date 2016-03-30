package View;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    PGView view;

    /** BOARD **/
    int cellSize;

    /** 2d array w/ positions of victory tile**/
    CoordinatePoint[] victoryTilesPositions;

    Board(PGView view, int cellSize) {
        this.view = view;
        this.cellSize = cellSize;
        victoryTilesPositions = new CoordinatePoint[6];

        int counter = 0;
        for (int i = 0; i < view.boardContent.length; i++) {
            for (int j = 0; j < view.boardContent[0].length; j++) {
                if(view.boardContent[i][j] == view.controller.model.VICTORY_TILE) {
                    // save all positions of victory tile into coordinate point.
                    victoryTilesPositions[counter++] = new CoordinatePoint(i, j);
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        setBackground(new Color(59,59,59));
//<<<<<<< HEAD
        //g2.d
//=======
        placeVictoryTiles();
        updateCompletedCrates();
        view.controller.updateBoard(view.boardContent);

//>>>>>>> e71e77689d83ae531cd3c710b71ed311cd48d571
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

    void updateCompletedCrates() {
        for (int i = 0; i < victoryTilesPositions.length; i++) {
            if(view.boardContent[victoryTilesPositions[i].getX()]
                    [victoryTilesPositions[i].getY()] == view.controller.model.CRATE) {
                // if it is empty at the position where there should be a victory tile,
                // simply replace it w/ a victory tile
                view.boardContent[victoryTilesPositions[i].getX()]
                        [victoryTilesPositions[i].getY()] = view.controller.model.COMPLETED_CRATE;
            }
        }
    }

    void placeVictoryTiles() {
        for (int i = 0; i < victoryTilesPositions.length; i++) {
            if(view.boardContent[victoryTilesPositions[i].getX()]
                                [victoryTilesPositions[i].getY()] == view.controller.model.EMPTY) {
                // if it is empty at the position where there should be a victory tile,
                // simply replace it w/ a victory tile
                view.boardContent[victoryTilesPositions[i].getX()]
                                 [victoryTilesPositions[i].getY()] = view.controller.model.VICTORY_TILE;
            }
        }
    }
}