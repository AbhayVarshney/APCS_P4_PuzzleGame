package Controller;

import Model.BoardHistory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {

    PGController controller;

    MyKeyListener(PGController controller) {
        this.controller = controller;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /** ADD BOARD TO ARRAY LIST OF PREVIOUS MOVES**/
        try {
            controller.model.boardHistory.set(controller.moveCounter, new BoardHistory(controller, controller.boardContent));
        } catch(IndexOutOfBoundsException a) {
            controller.model.boardHistory.add(new BoardHistory(controller, controller.boardContent));
        }

        int myXPlayer1 = controller.getXValue(controller.model.SPRITE); /** SAVE INDEX POSITION X of SPRITE. **/
        int myYPlayer1 = controller.getYValue(controller.model.SPRITE); /** SAVE INDEX POSITION Y of SPRITE. **/

        int newMyXPlayer1 = myXPlayer1; // keep original
        int newMyYPlayer1 = myYPlayer1;

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)       { newMyYPlayer1++; controller.moveCounter++;}
        else if (e.getKeyCode() == KeyEvent.VK_LEFT ) { newMyYPlayer1--; controller.moveCounter++;}
        else if (e.getKeyCode() == KeyEvent.VK_UP )   { newMyXPlayer1--; controller.moveCounter++;}
        else if (e.getKeyCode() == KeyEvent.VK_DOWN ) { newMyXPlayer1++; controller.moveCounter++;}

        if(controller.isLocal) { // multiplayer option is true!!!
            createLocal(e);
        }
       
        controller.view.movesCount.setText("" + controller.moveCounter);
        
        if(isEmpty(newMyXPlayer1, newMyYPlayer1) || isVictory(newMyXPlayer1, newMyYPlayer1)) { // safe to move
            controller.setContent(myXPlayer1, myYPlayer1, controller.model.EMPTY);
            controller.setContent(newMyXPlayer1, newMyYPlayer1, controller.model.SPRITE);
        } else if((isCrate(newMyXPlayer1, newMyYPlayer1) || isCompletedCrate(newMyXPlayer1, newMyYPlayer1)) &&
                  (isEmpty(newMyXPlayer1 + (newMyXPlayer1 - myXPlayer1), newMyYPlayer1 + (newMyYPlayer1 - myYPlayer1)) ||
                   isVictory(newMyXPlayer1 + (newMyXPlayer1 - myXPlayer1), newMyYPlayer1 + (newMyYPlayer1 - myYPlayer1)))) {
            controller.setContent(newMyXPlayer1 + (newMyXPlayer1 - myXPlayer1), newMyYPlayer1 + (newMyYPlayer1 - myYPlayer1), controller.model.CRATE);
            controller.setContent(myXPlayer1, myYPlayer1, controller.model.EMPTY);
            controller.setContent(newMyXPlayer1, newMyYPlayer1, controller.model.SPRITE);
        } else {} // user has hit the wall

        controller.model.updateEndZone();
        controller.model.updateVictoryCratesCounter();
        controller.repaintBoard(); // update the board
    }

    private void createLocal(KeyEvent e) {
        int myXPlayer2 = controller.getXValue(controller.model.PLAYER2); /** SAVE INDEX POSITION X of SPRITE. **/
        int myYPlayer2 = controller.getYValue(controller.model.PLAYER2);  /** SAVE INDEX POSITION Y of SPRITE. **/

        int newMyXPlayer2 = myXPlayer2; // keep original
        int newMyYPlayer2 = myYPlayer2;

        if(e.getKeyCode() == KeyEvent.VK_D)       { newMyYPlayer2++; controller.moveCounter++;}
        else if (e.getKeyCode() == KeyEvent.VK_A) { newMyYPlayer2--; controller.moveCounter++;}
        else if (e.getKeyCode() == KeyEvent.VK_W) { newMyXPlayer2--; controller.moveCounter++;}
        else if (e.getKeyCode() == KeyEvent.VK_S) { newMyXPlayer2++; controller.moveCounter++;}

        controller.view.movesCount.setText("" + controller.moveCounter);

        if(isEmpty(newMyXPlayer2, newMyYPlayer2) || isVictory(newMyXPlayer2, newMyYPlayer2)) { // safe to move
            controller.setContent(myXPlayer2, myYPlayer2, controller.model.EMPTY);
            controller.setContent(newMyXPlayer2, newMyYPlayer2, controller.model.PLAYER2);
        } else if((isCrate(newMyXPlayer2, newMyYPlayer2) || isCompletedCrate(newMyXPlayer2, newMyYPlayer2)) &&
                (isEmpty(newMyXPlayer2 + (newMyXPlayer2 - myXPlayer2), newMyYPlayer2 + (newMyYPlayer2 - myYPlayer2)) ||
                        isVictory(newMyXPlayer2 + (newMyXPlayer2 - myXPlayer2), newMyYPlayer2 + (newMyYPlayer2 - myYPlayer2)))) {
            controller.setContent(newMyXPlayer2 + (newMyXPlayer2 - myXPlayer2), newMyYPlayer2 + (newMyYPlayer2 - myYPlayer2), controller.model.CRATE);
            controller.setContent(myXPlayer2, myYPlayer2, controller.model.EMPTY);
            controller.setContent(newMyXPlayer2, newMyYPlayer2, controller.model.PLAYER2);
        }

        // possibly resize jframe and size of array to accomodate bigger map...
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
