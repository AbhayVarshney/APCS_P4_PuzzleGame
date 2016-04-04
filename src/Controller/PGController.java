package Controller;

import Model.PGModel;
import View.PGView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;

public class PGController {
    public PGView view;
    public PGModel model;
    public MyKeyListener keyListener;
    public MyMouseListener mouse;

    /** TIMER **/
    public Timer timer;
    public long timerCounter;

    /** GAME STATUS **/
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

    /** HIGHSCORES **/
    public ArrayList <String> scoreBoard; //storing best scores
    
    public PGController() {
        flagCounter = 0;
        userLevel = 1;

        BOARDWIDTH = 11;
        BOARDHEIGHT = 19;

        scoreBoard = new ArrayList<String>();
        
        /** INITIALIZING LEVELS **/
        LEVEL_1 = "src/Controller/Levels/Level1_Sokoban.txt";
        LEVEL_2 = "src/Controller/Levels/Level2_Sokoban.txt";
        LEVEL_3 = "src/Controller/Levels/Level3_Sokoban.txt";
        LEVEL_4 = "src/Controller/Levels/Level4_Sokoban.txt";
        LEVEL_5 = "src/Controller/Levels/Level5_Sokoban.txt";

        /** INITIALIZING TIMER **/
        timer = new Timer(1000, new MyTimerActionListener(this));
        timer.setInitialDelay(1000);
        timer.start();

        setGameType(LEVEL_1); // default level
        model = new PGModel(this);
        view = new PGView(readFile(), this);
        mouse = new MyMouseListener(this);
        keyListener = new MyKeyListener(this);
        view.addKeyListener(keyListener);
        // initialize objects
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
                    char key = model.BACKDROP;;               // null--> backdrop
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
    public void printBoard() {
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

        // options panel
        view.leftButton.addActionListener(mouse);
        view.rightButton.addActionListener(mouse);
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

    public int getYValue(char element) {
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                if(boardContent[i][j] == element) {
                    return j;
                }
            }
        }
        return -1; // no position found... shouldn't get to here
    }

    public char getContent(int x, int y) {
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

    /** IF THE USER HAS WON THE GAME, THIS METHOD SHOULD RUN **/
    public void updateGameStatus() {
        if(model.gameOver) {
<<<<<<< HEAD
        	//scoreBoard.add()
        	if(userLevel == 1){
        		scoreBoard.add("Level 1: " + view.time.getText());
        	}else if(userLevel == 2){
        		scoreBoard.add("Level 2: " + view.time.getText());
        	}else if(userLevel == 3){
        		scoreBoard.add("Level 3: " + view.time.getText());
        	}else if(userLevel == 4){
        		scoreBoard.add("Level 4: " + view.time.getText());
        	}else if(userLevel == 5){
        		scoreBoard.add("Level 5: " + view.time.getText());
        	}
=======
            System.out.print("Game Status: " + model.gameOver);
>>>>>>> 1434607106e0d4c6290248ebd0c1100eb30f654f
            view.gameWonDialog();
            model.gameOver = false;
            restartGame(true);
        }
    }

    /** RESPONSIBLE FOR MOVING TO THE NEXT LEVEL **/
    public void restartGame(boolean isNextLevel) {
        timer.stop();
        if(isNextLevel) {
            if(userLevel == 1) {
                setGameType(LEVEL_2);
                userLevel++;
            } else if(userLevel == 2) {
                setGameType(LEVEL_3);
                userLevel++;
            } else if(userLevel == 3) {
                setGameType(LEVEL_4);
                userLevel++;
            } else if(userLevel == 4) {
                setGameType(LEVEL_5);
                userLevel++;
            } else if(userLevel == 5) {
                setGameType(LEVEL_1); // restart to level 1
                userLevel = 1;
            }
        }

        // restart time
        timerCounter = 0;
        timer.start();

        view.updateBoardContent(readFile());
        model.locateVictoryTiles(boardContent);
        repaintBoard();
    }
}