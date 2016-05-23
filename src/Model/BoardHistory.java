package Model;

import Controller.PGController;

public class BoardHistory {
    private char [][] boardContent; // initialize 2d array

    public BoardHistory(PGController controller, char[][] boardContent) {
        this.boardContent = new char[controller.BOARDWIDTH][controller.BOARDHEIGHT];

        // save board content!
        for (int i = 0; i < this.boardContent.length; i++) {
            for (int j = 0; j < this.boardContent[0].length; j++) {
                this.boardContent[i][j] = boardContent[i][j];
            }
        }
    }

    public char[][] getBoardContent() {
        return boardContent;
    }
}
