package SI.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import SI.timer.Timer;

public class SpriteAnimation {
	
	private ArrayList <BufferedImage> sprites = new ArrayList<BufferedImage>();
	private byte currentSprite;
	
	private boolean loop = false;
	private boolean play = false;
	private boolean  destroyAnimation = false;
	
	private Timer timer;
	private int animationSpeed;
	private double xPos, yPos;

	public SpriteAnimation(double xPos, double yPos,int animationSpeed)
	{
		this.animationSpeed = animationSpeed;
		this.xPos = xPos;
		this.yPos = yPos;
		
		timer = new Timer();
	}
	
	public void draw (Graphics2D g)
	{
		if(isSpriteAnimDestroyed())
			return;
		
		g.drawImage(sprites.get(currentSprite), (int) getxPos(), (int) getyPos(), null);
	}
	
	public void update (double delta)
	{
		if(isSpriteAnimDestroyed())
			return;
		
		if(loop && !play)
			loopAnimation();
		
		if(play && !loop)
			playAnimation();
	}
	
	public void stopAnimation()
	{
		loop = false;
		play = false;
		
	}
	
	public void resetSprite() 
	{
		loop = false;
		play = false;
		currentSprite = 0;
	}
	
	private void playerAnimation()
	{
		
	}
	
	private void loopAnimation()
	{
		
		if(timer.isTimerReady(animationSpeed) && currentSprite == sprites.size())
		{
			currentSprite = 0;
			timer.resetTimer();
		}
		
		else if(timer.timerEvent(animationSpeed) && currentSprite !=sprites.size()-1)
			currentSprite++;
	}
	
	private void playAnimation()
	{
		if(timer.timerEvent(animationSpeed) && currentSprite !=sprites.size()-1 && !isDestroyAnimation())
		{
			play = false;
			currentSprite = 0;
		}

		else if(timer.timerEvent(animationSpeed) && currentSprite == sprites.size()-1 && isDestroyAnimation() )
			sprites = null;
		
		else if(timer.timerEvent(animationSpeed) && currentSprite !=sprites.size()-1)
			currentSprite++;
	}
	
	
	
	public ArrayList<BufferedImage> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<BufferedImage> sprites) {
		this.sprites = sprites;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public boolean isSpriteAnimDestroyed()
	{
		if(sprites == null)
			return true;
		
		return false;
	}
	
	public void addSprite(BufferedImage spriteMap, int xPos, int yPos, int width, int height)
	{
		sprites.add(spriteMap.getSubimage(xPos,yPos,width, height));
	}
	
	
	
	public void PlayAnimation(boolean play, boolean destroyAfterAnimation)
	{
		this.play = play;
		this.setDestroyAnimation(destroyAfterAnimation);
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public boolean isDestroyAnimation() {
		return destroyAnimation;
	}

	public void setDestroyAnimation(boolean destroyAnimation) {
		this.destroyAnimation = destroyAnimation;
	}
	
	
}
