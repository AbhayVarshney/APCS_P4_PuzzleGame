package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {
    PGController controller;
    MyKeyListener(PGController controller) {
        this.controller = controller;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int myX = controller.getXValue(controller.model.SPRITE); /** SAVE INDEX POSITION X of SPRITE. **/
        int myY = controller.getYValue(controller.model.SPRITE); /** SAVE INDEX POSITION Y of SPRITE. **/

        int newMyX = myX;
        int newMyY = myY;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)       { newMyY++; }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT ) { newMyY--; }
        else if (e.getKeyCode() == KeyEvent.VK_UP )   { newMyX--; }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN ) { newMyX++; }

        if(controller.getContent(newMyX, newMyY) == controller.model.EMPTY) { // safe to move
            controller.setContent(myX, myY, controller.model.EMPTY);
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
        }
//        else if(controller.getContent(newMyX, newMyY) == controller.model.CRATE) { // push box
//
//        }
        controller.printBoard();
        controller.repaintBoard(); // update the board

    }
}
