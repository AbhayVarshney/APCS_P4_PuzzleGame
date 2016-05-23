package Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MyMouseListener extends MouseAdapter implements ActionListener {
    PGController controller;

    MyMouseListener(PGController controller) {
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == controller.view.mntmNewGame) { /** user wants to play new game **/
            // don't change the level. simply restart the game.
            controller.restartGame(false);
            controller.isLocal = false;
        } else if (e.getSource() == controller.view.mntmHighScore) { /** user wants to open high scores **/
            controller.view.openHighScores();
            controller.view.requestFocus();
        } else if (e.getSource() == controller.view.mntmExit) { /** user wants to exit the game **/
            controller.timer.stop();
            controller.view.dispose();
        } else if (e.getSource() == controller.view.mntmHowToPlay) { /** user clicked help menu button **/
            System.out.println("How to play");
            try {
                JEditorPane helpContent = new JEditorPane(new URL("file:Html/Sokoban_HowToPlay.html"));
                JScrollPane helpPane = new JScrollPane(helpContent);
                helpPane.setPreferredSize(new Dimension(700, 400));
                JOptionPane.showMessageDialog(null, helpPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
            } catch (MalformedURLException a) {
                System.out.println("file not found! " + a.getMessage());
                a.getStackTrace();
            } catch (IOException b) {
                System.out.println("Error: " + b.getMessage());
                b.getStackTrace();
            }
        } else if (e.getSource() == controller.view.mntmAbout) { /** click about page **/
            try {
                JEditorPane helpContent = new JEditorPane(new URL("file:Html/Sokoban_About.html"));
                JScrollPane helpPane = new JScrollPane(helpContent);
                JOptionPane.showMessageDialog(null, helpPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
            } catch (MalformedURLException a) {
                System.out.println("file not found! " + a.getMessage());
                a.getStackTrace();
            } catch (IOException b) {
                System.out.println("Error: " + b.getMessage());
                b.getStackTrace();
            }
        } else if (e.getSource() == controller.view.mntmLevel1) { // user wants to change the level
            controller.userLevel = 5;
            controller.restartGame(true);
        } else if (e.getSource() == controller.view.mntmLevel2) { // user wants to change the level
            controller.userLevel = 1;
            controller.restartGame(true);
        } else if (e.getSource() == controller.view.mntmLevel3) { // user wants to change the level
            controller.userLevel = 2;
            controller.restartGame(true);
        } else if (e.getSource() == controller.view.mntmLevel4) { // user wants to change the level
            controller.userLevel = 3;
            controller.restartGame(true);
        } else if (e.getSource() == controller.view.mntmLevel5) { // user wants to change the level
            controller.userLevel = 4;
            controller.restartGame(true);
        } else if (e.getSource() == controller.view.mntmLocal) {  // user wants to play multiplayer option locally
            controller.isLocal = true;
            multiplayer();
        } else if (e.getSource() == controller.view.mntmSinglePlayerMode) { // user wants to play single player mode
            controller.isLocal = false;
            singlePlayer();
        } else if (e.getSource() == controller.view.rightButton) { // user chooses to move right in move history
            controller.view.requestFocus();

            if(controller.moveCounter < controller.model.boardHistory.size()-1) {
                controller.moveCounter++;
                controller.view.movesCount.setText("" + controller.moveCounter);

                // update the board
                for (int i = 0; i < controller.model.boardHistory.get(controller.moveCounter).getBoardContent().length; i++) {
                    for (int j = 0; j < controller.model.boardHistory.get(controller.moveCounter).getBoardContent()[0].length; j++) {
                        controller.boardContent[i][j] = controller.model.boardHistory.get(controller.moveCounter).getBoardContent()[i][j];
                    }
                }
                controller.repaintBoard();
            }
        } else if (e.getSource() == controller.view.leftButton) { // user chooses to move left in move history
            controller.view.requestFocus();

            if (controller.moveCounter > 0) { // precondition
                controller.moveCounter--;
                controller.view.movesCount.setText("" + controller.moveCounter);

                // update the board
                for (int i = 0; i < controller.model.boardHistory.get(controller.moveCounter).getBoardContent().length; i++) {
                    for (int j = 0; j < controller.model.boardHistory.get(controller.moveCounter).getBoardContent()[0].length; j++) {
                        controller.boardContent[i][j] = controller.model.boardHistory.get(controller.moveCounter).getBoardContent()[i][j];
                    }
                }
                controller.repaintBoard();
            }
        }
    }

    public void multiplayer() {
        try {
            controller.view.player2 = ImageIO.read(new File("images/Player_2.png"));

            /** CREATING A BIGGER WINDOW FOR MULTIPLAYER **/
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int WINDOW_WIDTH = 850;
            int WINDOW_HEIGHT = 850;
            int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2 );
            int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2 );
            controller.view.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
            controller.view.board.setPreferredSize(new Dimension(700, 500));

            controller.BOARDHEIGHT = 20;
            controller.BOARDWIDTH = 20;
            controller.view.board.cellSize = 25;
            controller.userLevel = 6;
            controller.setGameType(controller.MULTIPLAYER_1);
            controller.restartGame(false);
        } catch(IOException a) {
            System.out.println("Images can't be found. " + a.getMessage());
            a.printStackTrace();
        }

        controller.repaintBoard();
    }

    public void singlePlayer() {
        /** CREATING A SMALLER WINDOW FOR MULTIPLAYER **/
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int WINDOW_WIDTH = 850;
        int WINDOW_HEIGHT = 750;
        int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2 );
        int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2 );
        controller.view.board.setPreferredSize(new Dimension(700, 400));

        controller.view.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        controller.userLevel = 1;
        controller.BOARDHEIGHT = 19;
        controller.BOARDWIDTH = 11;
        controller.view.board.cellSize = 36;
        controller.setGameType(controller.LEVEL_1);
        controller.restartGame(false);

        // go through 2d array and convert PLAYER2 or BOTH to EMPTY

        controller.repaintBoard();
    }
}
