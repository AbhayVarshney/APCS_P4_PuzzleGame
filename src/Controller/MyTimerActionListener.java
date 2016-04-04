package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTimerActionListener implements ActionListener {
    PGController controller;

    // constructor
    MyTimerActionListener(PGController controller) {
        this.controller = controller;
    }
    public void actionPerformed(ActionEvent e) {
        controller.timerCounter++;
        if(controller.view != null)
            controller.view.time.setText(String.valueOf(controller.timerCounter +  "  seconds"));
    }
}
