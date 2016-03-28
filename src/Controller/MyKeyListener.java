package Controller;

import View.PGView;

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
            if(controller.getContent(newMyX + (newMyX - myX), newMyY + (newMyY - myY)) == controller.model.VICTORY_TILE) {
                // user has successfully dragged crate to the desired position
                controller.setContent(newMyX + (newMyX - myX), newMyY + (newMyY - myY), controller.model.COMPLETED_CRATE);
            } else {
                controller.setContent(newMyX + (newMyX - myX), newMyY + (newMyY - myY), controller.model.CRATE);
            }
            // push box
            controller.setContent(myX, myY, controller.model.EMPTY);
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
        }

        updateVictoryCrates();

        /** NEED TO FIX IF CRATE IS IN THE VICTORY TILE**/
        //controller.printBoard();
        controller.repaintBoard(); // update the board

    }

    void updateVictoryCrates() {
        int counter = 0;
        for (int i = 0; i < controller.boardContent.length; i++) {
            for (int j = 0; j < controller.boardContent[0].length; j++) {
                if(controller.boardContent[i][j] == controller.model.COMPLETED_CRATE) {
                    counter++;
                }
            }
        }
        if(counter == 6) {
            // user has beat the level
            // move to the next level
            new PGView().gameWonDialog();
        }

    }
}
