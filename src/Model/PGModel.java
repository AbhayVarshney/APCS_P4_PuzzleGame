package Model;

import Controller.PGController;

public class PGModel {
    char[][] boardBackground;   /** BEHIND THE SCENE BOARD **/

    public boolean gameOver;
    private int crateCounter;
    private int flagCounter;

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
        flagCounter = 0;
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                if(boardContent[i][j] == controller.model.VICTORY_TILE) {
                    flagCounter++;
                }
            }
        }
        victoryTilesPositions = new CoordinatePoint[flagCounter];
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

        if(crateCounter == flagCounter) {
            // user has beat the level
            // move to the next level
            gameOver = true;
        }

        controller.updateGameStatus();
    }
}
