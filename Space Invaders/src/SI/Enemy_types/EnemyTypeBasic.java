package SI.Enemy_types;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import SI.display.Display;
import SI.game_screen.BasicBlocks;
import SI.game_screen.GameScreen;
import SI.game_screen.Player;
import SI.sprite.SpriteAnimation;

public class EnemyTypeBasic implements EnemyType {
	
	private double speed = 1.0;
	private Rectangle rect;
	private SpriteAnimation enemySprite;
	

	public EnemyTypeBasic(double xPos, double yPos, int rows, int collumns)
	{
		enemySprite = new SpriteAnimation(xPos, yPos, rows, collumns, 300,  "/SI/images/Invaders.png");
		enemySprite.setWidth(40);
		enemySprite.setHeight(40);
		enemySprite.setLimit(3);

		
		this.setRect(new Rectangle((int) enemySprite.getxPos(), (int) enemySprite.getyPos(),enemySprite.getWidth(), enemySprite.getHeight()));
		enemySprite.setLoop(true);
	}
	
	@Override
	public void draw(Graphics2D g) {
		enemySprite.draw(g);
	}

	@Override
	public void update(double delta, Player player, BasicBlocks blocks) {
		enemySprite.update(delta);
		
		enemySprite.setxPos(enemySprite.getxPos() - (delta*speed));
		this.getRect().x = (int) enemySprite.getxPos();
	}

	@Override
	public void changeDirection(double delta) {
		speed *=-1.05;
		enemySprite.setxPos(enemySprite.getxPos()-(delta * speed));
		this.getRect().x = (int) enemySprite.getxPos();
		
		enemySprite.setyPos(enemySprite.getyPos() + (delta*15));
		this.getRect().y = (int) enemySprite.getyPos();
	}

	@Override
	public boolean deathScene() {
		if(!enemySprite.isPlay())
			return false;
		if(enemySprite.isSpriteAnimDestroyed())
			return true;
		return false;
	}

	@Override
	public boolean collide(int i, Player player, BasicBlocks blocks, ArrayList<EnemyType> enemys) {
		
		if(enemySprite.isPlay())
		{
		if(enemys.get(i).deathScene())
			enemys.remove(i);
		}
		
		for(int w=0;w<player.playerLaser.laser.size();w++)
		{
			if(enemys != null && player.playerLaser.laser.get(w).collisionRect(((EnemyTypeBasic) enemys.get(i)).getRect())) 
			{
				enemySprite.resetLimit();
				enemySprite.setAnimationSpeed(120);
				enemySprite.setPlay(true, true);
				GameScreen.SCORE +=80;
				return true;
			}
		}	
		
		return false;
	}

	@Override
	public boolean isOutOfBounds() {
		if(rect.x == 0 && rect.x< Display.WIDTH - rect.width)
			return true;
		if(rect.x > 1230 && rect.x< Display.WIDTH - rect.width)
			return true;
		
		return false;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

}
