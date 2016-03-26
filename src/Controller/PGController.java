package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.PGModel;
import View.PGView;

public class PGController {
    PGView view;
    PGModel model;
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
}
