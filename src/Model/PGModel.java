package Model;

public class PGModel {
    char[][] boardBackground;   /** BEHIND THE SCENE BOARD **/

    boolean gameOver;
    int boardSize, cellSize;

    public final char BLOCK;
    public final char CRATE;
    public final char COMPLETED_CRATE;
    public final char SPRITE;
    public final char EMPTY;
    public final char VICTORY_TILE;
    public final char BACKDROP;

    int[] x = {-1, -1, -1, 1, 1,  1, 0,  0};
    int[] y = { 0, -1,  1, 1, 0, -1, 1, -1};

    public PGModel() {
        BLOCK = 'b';         // gray.tile.gif
        CRATE = 'c';         // crate_tile.gif
        COMPLETED_CRATE = 'x'; //completed_crate.gif
        SPRITE = 's';        // Sprite.gif
        EMPTY = ' ';         // draw grey tile
        VICTORY_TILE = '.';  // victor_tile.gif
        BACKDROP = 'o';      // dark grey background
    }

    void createBoard() {
        boardBackground = new char[18][10];
    }
}
