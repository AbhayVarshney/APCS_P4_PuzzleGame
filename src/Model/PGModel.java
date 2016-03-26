package Model;

public class PGModel {
    char[][] boardBackground;   /** BEHIND THE SCENE BOARD **/
    char[][] boardShow;         /** WHAT THE USER WILL SEE **/

    boolean gameOver;
    int boardSize, cellSize;

    final char BLOCK;
    final char CRATE;
    final char SPRITE;
    final char EMPTY;
    final char VICTORY_TILE;
    final char BACKDROP;

    int[] x = {-1, -1, -1, 1, 1,  1, 0,  0};
    int[] y = { 0, -1,  1, 1, 0, -1, 1, -1};

    public PGModel() {
        BLOCK = 'b';         // gray.tile.gif
        CRATE = 'c';         // crate_tile.gif
        SPRITE = 's';        // Sprite.gif
        EMPTY = ' ';         // draw grey
        VICTORY_TILE = '.';  // victor_tile.gif
        BACKDROP = '-';      // GREY
    }

    void createBoard() {

    }
}
