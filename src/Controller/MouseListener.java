package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MouseListener extends MouseAdapter implements ActionListener {
    PGController controller;
    MouseListener(PGController controller) {
        this.controller = controller;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == controller.view.mntmNewGame) { /** user wants to play new game **/
            // don't change the level. simply restart the game
            controller.restartGame(false);
        } else if(e.getSource() == controller.view.mntmExit) { /** user wants to exit the game **/
            controller.view.dispose();
        } else if(e.getSource() == controller.view.mntmHowToPlay) { /** user clicked help menu button **/
        	System.out.println("How to play");
            try {
                JEditorPane helpContent = new JEditorPane(new URL("file:Html/Sokoban_HowToPlay.html"));
                JScrollPane helpPane = new JScrollPane(helpContent);
                helpPane.setPreferredSize(new Dimension(700,400));
                JOptionPane.showMessageDialog(null, helpPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
            } catch(MalformedURLException a) {
                System.out.println("file not found! " + a.getMessage());
                a.getStackTrace();
            } catch(IOException b) {
                System.out.println("Error: " + b.getMessage());
                b.getStackTrace();
            }
        } else if(e.getSource() == controller.view.mntmAbout) { /** click about page **/
            try {
                JEditorPane helpContent = new JEditorPane(new URL("file:Html/Sokoban_About.html"));
                JScrollPane helpPane = new JScrollPane(helpContent);
                JOptionPane.showMessageDialog(null, helpPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
            } catch(MalformedURLException a) {
                System.out.println("file not found! " + a.getMessage());
                a.getStackTrace();
            } catch(IOException b) {
                System.out.println("Error: " + b.getMessage());
                b.getStackTrace();
            }
        } else if(e.getSource() == controller.view.mntmLevel1) { // user wants to change the level
            controller.userLevel = 5;
            controller.restartGame(true);
        } else if(e.getSource() == controller.view.mntmLevel2) { // user wants to change the level
            controller.userLevel = 1;
            controller.restartGame(true);
        } else if(e.getSource() == controller.view.mntmLevel3) { // user wants to change the level
            controller.userLevel = 2;
            controller.restartGame(true);
        } else if(e.getSource() == controller.view.mntmLevel4) { // user wants to change the level
            controller.userLevel = 3;
            controller.restartGame(true);
        } else if(e.getSource() == controller.view.mntmLevel5) { // user wants to change the level
            controller.userLevel = 4;
            controller.restartGame(true);
        }
    }
}
