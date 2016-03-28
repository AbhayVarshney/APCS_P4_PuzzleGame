package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MouseListener extends MouseAdapter implements ActionListener {
    PGController controller;
    MouseListener(PGController controller) {
        this.controller = controller;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == controller.view.mntmNewGame) { // user wants to play new game

        } else if(e.getSource() == controller.view.mnHelp) { // help

        }
    }
}
