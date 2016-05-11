package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
            multiplayerBoard(true);
            controller.isMultiplayer = true;
        } else if (e.getSource() == controller.view.mntmNetwork) { // user wants to play multiplayer option on network
            multiplayerBoard(false);
            controller.isMultiplayer = true;
        } else if (e.getSource() == controller.view.mntmSinglePlayerMode) { // user wants to play single player mode
            singleplayerBoard();
        }
    }

    public void multiplayerBoard(boolean isLocal) {
        // resize the board elements so that two maps can fit
        int imageSize = 28; // smaller image size
        int WINDOW_WIDTH = controller.view.getWINDOW_WIDTH() + (controller.view.getWINDOW_WIDTH()/2);
        int WINDOW_HEIGHT = controller.view.getWINDOW_HEIGHT();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2 );
        int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2 );
        controller.view.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        controller.view.setLocation(x,y);

        // resize images
        controller.view.board.setCellSize(imageSize);
        controller.view.topLogo.setWidth(WINDOW_WIDTH);

        controller.repaintBoard();
        System.out.println("repainted!");
    }

    public void singleplayerBoard() {
        // resize all board elements to the original size
        int imageSize = 36; // original size!
        int WINDOW_WIDTH = controller.view.getWINDOW_WIDTH();
        int WINDOW_HEIGHT = controller.view.getWINDOW_HEIGHT();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2 );
        int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2 );
        controller.view.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        controller.view.setLocation(x,y);

        // resize images
        controller.view.board.setCellSize(imageSize);
        controller.view.topLogo.setWidth(WINDOW_WIDTH);

        controller.repaintBoard();
    }
}
