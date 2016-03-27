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

    /** TIMER **/
    Timer time;

    /** GAME STATUS **/
    boolean gameOver;
    String gameType;

    /** Board Size **/
    final int BOARDWIDTH;
    final int BOARDHEIGHT;

    /** BOARD STATUS **/
    public int userLevel;

    public PGController() {
        int userLevel = 1;

        gameOver = false;
        BOARDWIDTH = 11;
        BOARDHEIGHT = 19;

        setGameType("src/Controller/Levels/Level1_Sokoban.txt");
        model = new PGModel();
        view = new PGView(readFile(), this);
    }

    char[][] readFile() {
        char[][] boardType = new char[BOARDWIDTH][BOARDHEIGHT];
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
                    } else if(input.equals("ground.png")) {   // EMPTY
                        key = model.EMPTY;
                    } else if(input.equals("tree_big.png")) { // sprite
                        key = model.SPRITE;
                    }
                    boardType[i][j] = key;
                }
            }
        } catch(IOException e) {
            System.out.println("File not found: " + e.getMessage());
            e.getStackTrace();
        }

         /** READING 2D ARRAY PURPOSES **/
         for (int i = 0; i < BOARDWIDTH; i++) {
             for (int j = 0; j < BOARDHEIGHT; j++) {
                 System.out.print(boardType[i][j] + " ");
             }
             System.out.println();
         }
        return boardType;
    }


    // setter method
    void setGameType(String gameType) {
        this.gameType = gameType;
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
