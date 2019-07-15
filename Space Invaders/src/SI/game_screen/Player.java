package SI.game_screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class Player implements KeyListener{
		
	private BufferedImage pSprite;
	private Rectangle rect;
	private double xPos, yPos, startXPos, startYPos;
	private int width, height;
	
	private boolean left = false, right = false, shoot = false;
	
	
	public Player(double xPos, double yPos, int width, int height){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		
		rect = new Rectangle((int) xPos,(int) yPos, width, height);
		
		try{
			URL url = this.getClass().getResource("/SI/images/Player.png");
			pSprite = ImageIO.read(url);
		}catch(IOException e){};
		
	}
	
	public void draw(Graphics2D g){
		g.drawImage(pSprite,(int) xPos,(int) yPos, width, height, null);

	}
	
	public void update(double delta){

	}
	
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	