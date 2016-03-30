package Controller;

import Model.PGModel;
import View.PGView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

public class PGController {
    public PGView view;
    public PGModel model;
    public MyKeyListener keyListener;
    public MouseListener mouse;

    /** TIMER **/
    Timer time;

    /** GAME STATUS **/
    boolean gameOver;
    String gameType;

    /** Board Size **/
    final int BOARDWIDTH;
    final int BOARDHEIGHT;

    /** BOARD IMPORTANT **/
    public int userLevel;
    public char[][] boardContent;
    public int flagCounter;

    /** LEVELS **/
    public final String LEVEL_1;
    public final String LEVEL_2;
    public final String LEVEL_3;
    public final String LEVEL_4;
    public final String LEVEL_5;

    public PGController() {
        flagCounter = 0;
        userLevel = 1;

        gameOver = false;
        BOARDWIDTH = 11;
        BOARDHEIGHT = 19;

        /** INITIALIZING LEVELS **/
        LEVEL_1 = "src/Controller/Levels/Level1_Sokoban.txt";
        LEVEL_2 = "src/Controller/Levels/Level2_Sokoban.txt";
        LEVEL_3 = "src/Controller/Levels/Level3_Sokoban.txt";
        LEVEL_4 = "src/Controller/Levels/Level4_Sokoban.txt";
        LEVEL_5 = "src/Controller/Levels/Level5_Sokoban.txt";

        setGameType(LEVEL_1);
        model = new PGModel(this);
        view = new PGView(readFile(), this);
        keyListener = new MyKeyListener(this);
        view.addKeyListener(keyListener);
        mouse = new MouseListener(this);
        addListeners();
    }

    char[][] readFile() {
        boardContent = new char[BOARDWIDTH][BOARDHEIGHT];
        try {
            File file = new File(gameType);
            Scanner scan = new Scanner(file);
            for (int i = 0; i < BOARDWIDTH; i++) {
                for (int j = 0; j < BOARDHEIGHT; j++) {
                    String input =  scan.next();
                    char key = model.BACKDROP;;                  // null--> backdrop
                    if(input.equals("rock.png")) {            // BLOCK
                        key = model.BLOCK;
                    } else if(input.equals("tree.png")) {     // CRATE
                        key = model.CRATE;
                    } else if(input.equals("stairs.png")) {   // VICTORY TILE
                        key = model.VICTORY_TILE;
                        flagCounter++;
                    } else if(input.equals("ground.png")) {   // EMPTY
                        key = model.EMPTY;
                    } else if(input.equals("tree_big.png")) { // sprite
                        key = model.SPRITE;
                    }
                    boardContent[i][j] = key;
                }
            }
        } catch(IOException e) {
            System.out.println("File not found: " + e.getMessage());
            e.getStackTrace();
        }

        printBoard();

        return boardContent;
    }

    // temporary. testing purposes
    void printBoard() {
        /** READING 2D ARRAY PURPOSES **/
        for (int i = 0; i < BOARDWIDTH; i++) {
            for (int j = 0; j < BOARDHEIGHT; j++) {
                System.out.print(boardContent[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    void addListeners() {
        view.mntmAbout.addActionListener(mouse);
        view.mntmHowToPlay.addActionListener(mouse);
        view.mntmNewGame.addActionListener(mouse);
        view.mntmHighScore.addActionListener(mouse);
        view.mntmExit.addActionListener(mouse);
        view.mntmLevel1.addActionListener(mouse);
        view.mntmLevel2.addActionListener(mouse);
        view.mntmLevel3.addActionListener(mouse);
        view.mntmLevel4.addActionListener(mouse);
        view.mntmLevel5.addActionListener(mouse);
    }

    // setter method
    void setGameType(String gameType) {
        this.gameType = gameType;
    }
    void setContent(int posX, int posY, char element) {
        boardContent[posX][posY] = element;
        //model.updateBoard();
    }

    // getter method
    int getXValue(char element) {
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                if(boardContent[i][j] == element) {
                    return i;
                }
            }
        }
        return -1; // no position found... shouldn't get to here
    }

    int getYValue(char element) {
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                if(boardContent[i][j] == element) {
                    return j;
                }
            }
        }
        return -1; // no position found... shouldn't get to here
    }

    char getContent(int x, int y) {
        return boardContent[x][y];
    }

    public void repaintBoard() {
        view.updateBoardContent(boardContent);
        view.board.repaint();
    }

    public void updateBoard(char[][] board) {
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                boardContent[i][j] = board[i][j];
            }
        }
    }

//    void start() {
//        switch (gameType) {
//            case "easy":
//                boardSize = 10;
//                cellSize = 40;
//                break;
//            case "medium":
//                boardSize = 15;
//                cellSize = 26;
//                break;
//            case "hard":
//                boardSize = 20;
//                cellSize = 20;
//                break;
//            default:
//                boardSize = 10;
//                cellSize = 40;
//                break;
//        }
//    }


    //mouse stuff
    /*
    public class Mouse extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (first) {
                first = false;
                time = new Timer(1000, d -> {
                    sec++;
                   
                });
                
            }
            time.stop();
        }
    }*/
}
