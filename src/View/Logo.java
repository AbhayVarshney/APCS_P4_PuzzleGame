package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Logo extends JPanel{
	private BufferedImage img;
	private int WIDTH;
	private int HEIGHT;
	
	//constructor
	Logo(BufferedImage img, int width, int height){
		this.img = img;
		WIDTH = width;
		HEIGHT = height;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
	}

	public void setWidth(int width) { this.WIDTH = width; }
}
