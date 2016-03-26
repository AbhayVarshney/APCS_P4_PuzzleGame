package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.glass.ui.Timer;

import Model.PGModel;
import View.PGView;

public class PGController {
    PGView view;
    PGModel model;
    Timer time;
    boolean first;
    int sec = 0;
    BufferedImage crate, wall, victory_tile, sprite;
    public PGController() { // default constructor
        view = new PGView(this);
        model = new PGModel();
        try{
        	crate = ImageIO.read(new File("crate_tile.gif"));
        	wall = ImageIO.read(new File("gray_tile.gif"));
        	victory_tile = ImageIO.read(new File("victory_tile.gif"));
        	sprite = ImageIO.read(new File("Sprite.gif"));
        	
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
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
