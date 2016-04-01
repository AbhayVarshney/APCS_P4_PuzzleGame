package Model;

import Controller.PGController;
import java.util.*;

public class PGModel {
    char[][] boardBackground;   /** BEHIND THE SCENE BOARD **/

    public boolean gameOver;
    private int crateCounter;
    //the moves int is used to keep track of moves and is 
    //also used when going back and forth in the movelist
    private int moves = 0;
    
    //TO SAVE THE MOVES HOMEBOY
    List<char [][]> moveList = new ArrayList<>();
    
    private PGController controller;

    public final char BLOCK;
    public final char CRATE;
    public final char COMPLETED_CRATE;
    public final char SPRITE;
    public final char EMPTY;
    public final char VICTORY_TILE;
    public final char BACKDROP;

    /** 2d array w/ positions of victory tile**/
    public CoordinatePoint[] victoryTilesPositions;

    int[] x = {-1, -1, -1, 1, 1,  1, 0,  0};
    int[] y = { 0, -1,  1, 1, 0, -1, 1, -1};

    public PGModel(PGController controller) {
        BLOCK = 'b';         // gray.tile.gif
        CRATE = 'c';         // crate_tile.gif
        COMPLETED_CRATE = 'x'; //completed_crate.gif
        SPRITE = 's';        // Sprite.gif
        EMPTY = ' ';         // draw grey tile
        VICTORY_TILE = '.';  // victor_tile.gif
        BACKDROP = 'o';      // dark grey background

        gameOver = false;
        this.controller = controller;
    }

    private void createBoard() {
        boardBackground = new char[18][10];
    }

    public void updateEndZone() {
        for (int i = 0; i < victoryTilesPositions.length; i++) {
            if(controller.boardContent[victoryTilesPositions[i].getX()]
                    [victoryTilesPositions[i].getY()] == EMPTY) {
                // if it is empty at the position where there should be a victory tile,
                // simply replace it w/ a victory tile
                controller.boardContent[victoryTilesPositions[i].getX()]
                        [victoryTilesPositions[i].getY()] = VICTORY_TILE;
            } else if(controller.boardContent[victoryTilesPositions[i].getX()]
                    [victoryTilesPositions[i].getY()] == CRATE) {
                // if it is empty at the position where there should be a victory tile,
                // simply replace it w/ a victory tile
                controller.boardContent[victoryTilesPositions[i].getX()]
                        [victoryTilesPositions[i].getY()] = COMPLETED_CRATE;
                controller.repaintBoard();
            }
        }
        controller.repaintBoard();
    }

    public void locateVictoryTiles(char[][] boardContent) {
        victoryTilesPositions = new CoordinatePoint[6];
        int counter = 0;
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                if(boardContent[i][j] == controller.model.VICTORY_TILE) {
                    // save all positions of victory tile into coordinate point.
                    victoryTilesPositions[counter++] = new CoordinatePoint(i, j);
                }
            }
        }
    }

    public void updateVictoryCratesCounter() {
        crateCounter = 0;
        for (int i = 0; i < controller.boardContent.length; i++) {
            for (int j = 0; j < controller.boardContent[0].length; j++) {
                if(controller.boardContent[i][j] == COMPLETED_CRATE) {
                    crateCounter++;
                }
            }
        }

        if(crateCounter == 6) {
            // user has beat the level
            // move to the next level
            gameOver = true;
        }

        controller.updateGameStatus();
    }

    public void increaseMove() {
    	moves++;
    }
    
    public int getMoves() {
    	return moves;
    }
    
    public void addBoard() {
    	moveList.add(boardBackground);
    }
}
