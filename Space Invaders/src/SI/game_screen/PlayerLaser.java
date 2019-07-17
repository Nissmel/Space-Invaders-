package SI.game_screen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import SI.player_laser.MachineGun;
import SI.player_laser.PlayerLaserType;
import SI.timer.Timer;

public class PlayerLaser {
	private Timer timer;

	public ArrayList<PlayerLaserType> laser = new ArrayList<PlayerLaserType>();
	
	public PlayerLaser()
	{
		timer = new Timer();
	}
	
	public void draw(Graphics2D g)
	{
		for(int i=0;i<laser.size();i++)
			laser.get(i).draw(g);
	}
	
	public void update(double delta, BasicBlocks blocks)
	{
		for(int i=0;i<laser.size();i++) 
		{
			laser.get(i).update(delta, blocks);
			if(laser.get(i).destroy())
				laser.remove(i);
		}
	}
	
	public void shootLaser(double xPos, double yPos, int width, int height)
	{
		laser.add(new MachineGun(xPos + 22,yPos + 15, width, height));
	}
}
