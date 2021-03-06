package Controller;

import Model.PGModel;
import View.PGView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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
    public int moveCounter;

    /** Board Size **/
    public int BOARDWIDTH;
    public int BOARDHEIGHT;

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
    public final String MULTIPLAYER_1;

    /** HIGH SCORES **/
    public ArrayList <MyHighScores> scoreBoard; //storing best scores ascending order

    /** MULTIPLAYER **/
    public boolean isLocal;
    
    public PGController() {
        flagCounter = 0;
        moveCounter = 0;
        
        userLevel = 1; // user's current level

        BOARDWIDTH = 11;  // size of board that code will read
        BOARDHEIGHT = 19; // size of board that code will read

        scoreBoard = new ArrayList<>();
        
        /** INITIALIZING LEVELS **/
        LEVEL_1 = "src/Controller/Levels/Level1_Sokoban.txt";
        LEVEL_2 = "src/Controller/Levels/Level2_Sokoban.txt";
        LEVEL_3 = "src/Controller/Levels/Level3_Sokoban.txt";
        LEVEL_4 = "src/Controller/Levels/Level4_Sokoban.txt";
        LEVEL_5 = "src/Controller/Levels/Level5_Sokoban.txt";
        MULTIPLAYER_1 = "src/Controller/Levels/Multiplayer1_Sokoban.txt";

        /** INITIALIZING TIMER **/
        timer = new Timer(1000, new MyTimerActionListener(this));
        timer.setInitialDelay(1000);
        timer.start();

        setGameType(LEVEL_1); // default level

        /** INITIALIZING OBJECTS **/
        model = new PGModel(this);
        view = new PGView(readFile(), this);
        mouse = new MyMouseListener(this);
        keyListener = new MyKeyListener(this);
        view.addKeyListener(keyListener);

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
                    } else if(input.equals("second.png")) {   // second player
                        key = model.PLAYER2;
                    }
                    boardContent[i][j] = key;
                }
            }
        } catch(IOException e) {
            System.out.println("File not found: " + e.getMessage());
            e.getStackTrace();
        } catch(NoSuchElementException e) {
            System.out.println("No element found: " + e.getMessage());
        }

        return boardContent;
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
        view.mntmLocal.addActionListener(mouse);
        view.mntmSinglePlayerMode.addActionListener(mouse);
        view.leftButton.addActionListener(mouse);
        view.rightButton.addActionListener(mouse);
    }

    // setter method
    void setGameType(String gameType) {
        this.gameType = gameType;
    }
    void setContent(int posX, int posY, char element) {
        boardContent[posX][posY] = element;
    }

    // getter method
    int getXValue(char element) { // get x position of boardContent
        for (int i = 0; i < boardContent.length; i++) {
            for (int j = 0; j < boardContent[0].length; j++) {
                if(boardContent[i][j] == element) {
                    return i;
                }
            }
        }
        return -1; // no position found... shouldn't get to here
    }

    public int getYValue(char element) { // get y position of boardContent
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
        view.topLogo.repaint();
    }

    /** IF THE USER HAS WON THE GAME, THIS METHOD SHOULD RUN **/
    public void updateGameStatus() {
        if(model.gameOver) {
        	String output = "";
        	if(userLevel == 1){
        		//scoreBoard.add("Level 1: " + view.time.getText());
        		output = "Level 1: " + view.time.getText();
        		scoreBoard.add(new MyHighScores((int)timerCounter, output));
        	} else if(userLevel == 2){
        		//scoreBoard.add("Level 2: " + view.time.getText());
        		output = "Level 2: " + view.time.getText();
        		scoreBoard.add(new MyHighScores((int)timerCounter, output));
        	} else if(userLevel == 3){
        		//scoreBoard.add("Level 3: " + view.time.getText());
        		output = "Level 3: " + view.time.getText();
        		scoreBoard.add(new MyHighScores((int)timerCounter, output));
        	} else if(userLevel == 4){
        	 	//scoreBoard.add("Level 4: " + view.time.getText());
        		output = "Level 4: " + view.time.getText();
        		scoreBoard.add(new MyHighScores((int)timerCounter, output));
        	} else if(userLevel == 5){
        		//scoreBoard.add("Level 5: " + view.time.getText());
        		output = "Level 5: " + view.time.getText();
        		scoreBoard.add(new MyHighScores((int)timerCounter, output));
        	} else if(userLevel == 6) { // multiplayer option
                output = "Multiplayer: " + view.time.getText();
                scoreBoard.add(new MyHighScores((int) timerCounter, output));
            }
            view.gameWonDialog();
            model.gameOver = false;
            restartGame(true);
        }
    }

    /** RESPONSIBLE FOR MOVING USER TO THE NEXT LEVEL **/
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
            } else if(userLevel == 5 || userLevel == 6) { // 6 --> multiplayer
                setGameType(LEVEL_1); // restart to level 1
                userLevel = 1;
            }
        }

        // restart time & moves counter
        timerCounter = 0;
        moveCounter = 0;
        view.movesCount.setText("" + moveCounter);
        timer.start();

        view.updateBoardContent(readFile());
        model.locateVictoryTiles(boardContent);

        repaintBoard();
    }
}