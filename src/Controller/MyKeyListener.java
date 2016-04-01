package Controller;

import View.PGView;

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
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)       { newMyY++; controller.model.increaseMove();}
        else if (e.getKeyCode() == KeyEvent.VK_LEFT ) { newMyY--; controller.model.increaseMove();}
        else if (e.getKeyCode() == KeyEvent.VK_UP )   { newMyX--; controller.model.increaseMove();}
        else if (e.getKeyCode() == KeyEvent.VK_DOWN ) { newMyX++; controller.model.increaseMove();}
        else if (e.getKeyCode() == KeyEvent.VK_1) { // left
        	controller.model.isValidChange(controller.model.getMoves(), -1);
        	System.out.println("LEFT ARROW CLICKED");
        } else if(e.getKeyCode() == KeyEvent.VK_2) { //right
        	controller.model.isValidChange(controller.model.getMoves(), 1);
        	System.out.println("Right ARROW CLICKED");
        }
 
        if(isEmpty(newMyX, newMyY)) { // safe to move
            controller.setContent(myX, myY, controller.model.EMPTY);
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
        } else if(isVictory(newMyX, newMyY)) {
            controller.setContent(myX, myY, controller.model.EMPTY);
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
        } else if((isCrate(newMyX, newMyY) || isCompletedCrate(newMyX, newMyY)) &&
                  (isEmpty(newMyX + (newMyX - myX), newMyY + (newMyY - myY)) ||
                   isVictory(newMyX + (newMyX - myX), newMyY + (newMyY - myY)))) {
            controller.setContent(newMyX + (newMyX - myX), newMyY + (newMyY - myY), controller.model.CRATE);
            controller.setContent(myX, myY, controller.model.EMPTY);
            controller.setContent(newMyX, newMyY, controller.model.SPRITE);
        }
        
        
        controller.model.addBoard(controller.boardContent);
        System.out.println(controller.model.getMoves());
       
        
        controller.model.updateEndZone();
        controller.model.updateVictoryCratesCounter();
        controller.repaintBoard(); // update the board
    }

    private boolean isVictory(int posX, int posY) {
        return controller.getContent(posX, posY) == controller.model.VICTORY_TILE;
    }

    private boolean isEmpty(int posX, int posY) {
        return controller.getContent(posX, posY) == controller.model.EMPTY;
    }

    private boolean isCrate(int posX, int posY) {
        return controller.getContent(posX, posY) == controller.model.CRATE;
    }

    private boolean isCompletedCrate(int posX, int posY) {
        return controller.getContent(posX, posY) == controller.model.COMPLETED_CRATE;
    }
}
