package Controller;

import Model.PGModel;
import View.PGView;
import java.util.Timer;
public class PGController {
    PGView view;
    PGModel model;
    Timer time;
    boolean first;
    int sec = 0;
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
