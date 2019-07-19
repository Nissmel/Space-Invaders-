package SI.game_screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import SI.display.Display;
import SI.sounds.PlaySound;


public class Player implements KeyListener{
		
	private BufferedImage pSprite;
	private Rectangle rect;
	private double xPos, yPos;
	private int width, height;
	private final double speed = 6.0d;
	private BasicBlocks blocks;
	
	private boolean left = false, right = false, shoot = false;
	
	
	public PlayerLaser playerLaser;
	
	public Player(double xPos, double yPos, int width, int height, BasicBlocks blocks){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		
		rect = new Rectangle((int) xPos,(int) yPos, width, height);
		
		try{
			URL url = this.getClass().getResource("/SI/images/Player.png");
			pSprite = ImageIO.read(url);
		}catch(IOException e){};
		
		this.blocks = blocks;
		playerLaser = new PlayerLaser();
		
	}
	
	public void draw(Graphics2D g){
		g.drawImage(pSprite,(int) xPos,(int) yPos, width, height, null);
		playerLaser.draw(g);
	}
	
	public void update(double delta){
		if(right && !left && xPos < Display.WIDTH - width +10)
		{
			xPos += speed * delta;
			rect.x = (int) xPos;
		}
		
		if(!right && left && xPos >10)
		{
			xPos -= speed * delta;
			rect.x = (int) xPos;
		}

		playerLaser.update(delta, blocks);
		
		if(shoot)
		{
			playerLaser.shootLaser(xPos, yPos, 2, 4);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
				right = true;
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
				left = true;
		
		if(key == KeyEvent.VK_SPACE)
			{
				try {
					PlaySound.playSound("src/SI/sounds_clips/shoot.wav");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				shoot = true;
			}
		}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
			right = false;
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
			left = false;		
		
		if(key == KeyEvent.VK_SPACE)
			shoot = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	