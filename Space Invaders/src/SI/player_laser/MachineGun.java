package SI.player_laser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import SI.display.Display;
import SI.game_screen.BasicBlocks;

public class MachineGun extends PlayerLaserType{
	
	private Rectangle bullet;
	private final double speed =2.5;
	
	public MachineGun(double xPos, double yPos, int width, int height)
	{
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setHeight(height);
		this.setWidth(width);
		
		this.bullet = new Rectangle((int) getxPos(),(int) getyPos(),(int) getHeight(),(int) getWidth());
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(bullet == null)
			return;
		
		g.setColor(Color.WHITE);
		g.fill(bullet);
	}

	@Override
	public void update(double delta, BasicBlocks blocks) {
		if(bullet == null)
			return;
		
		this.setyPos(getyPos() - (delta * speed));
		bullet.y = (int) this.getyPos();
		wallCollide(blocks);
		isOutofBounds();
	}

	@Override
	public boolean collisionRect(Rectangle rect) {
		if(this.bullet == null)
			return false;
		if(bullet.intersects(rect))
		{
			this.bullet = null;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean collisionPoly(Polygon poly) {
		return false;
	}


	@Override
	protected void wallCollide(BasicBlocks blocks) {
		for(int i=0;i<blocks.wall.size();i++)
		{
			if(bullet.intersects(blocks.wall.get(i)))
			{
				blocks.wall.remove(i);
				bullet = null;
				return;
			}
		}
	}


	@Override
	public boolean destroy() {
		if(bullet == null)
			return true;
		
		return false;
	}

	@Override
	protected void isOutofBounds() {
		if(this.bullet == null)
			return;
		
		if(bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x <0 || bullet.x > Display.WIDTH)
		{
			bullet = null;
		}		
	}


}
