package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {
    PGController controller;
    boolean isVictory;
    MyKeyListener(PGController controller) {
        this.controller = controller;
        isVictory = false;
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
            if(!isVictory) {
                controller.setContent(myX, myY, controller.model.EMPTY);
            } else {
                controller.setContent(myX, myY, controller.model.VICTORY_TILE);
            }
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
            isVictory = false;
        } else if(controller.getContent(newMyX, newMyY) == controller.model.VICTORY_TILE) {
            if(isVictory) {
                controller.setContent(myX, myY, controller.model.VICTORY_TILE);
            } else {
                isVictory = true;
                controller.setContent(myX, myY, controller.model.EMPTY);
            }
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
        } else if(controller.getContent(newMyX, newMyY) == controller.model.CRATE &&
                 (controller.getContent(newMyX + (newMyX - myX), newMyY + (newMyY - myY)) == controller.model.EMPTY ||
                  controller.getContent(newMyX + (newMyX - myX), newMyY + (newMyY - myY)) == controller.model.VICTORY_TILE)) {

            if()


            if(isVictory) {
                controller.setContent(myX, myY, controller.model.VICTORY_TILE);
            } else {
                isVictory = true;
                controller.setContent(myX, myY, controller.model.EMPTY);
            }
            // push box
            controller.setContent(myX, myY, controller.model.EMPTY);
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
            controller.setContent(newMyX + (newMyX - myX), newMyY + (newMyY - myY), controller.model.CRATE);
        }
        //controller.printBoard();
        controller.repaintBoard(); // update the board

    }
}
