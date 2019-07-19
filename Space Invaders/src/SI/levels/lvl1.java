package SI.levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import SI.Enemy_types.EnemyType;
import SI.Enemy_types.EnemyTypeBasic;
import SI.game_screen.BasicBlocks;
import SI.game_screen.Player;
import SI.sounds.PlaySound;

public class lvl1 implements SuperLvl{
	
	private Player player;
	private ArrayList<EnemyType> enemies = new ArrayList<EnemyType>();
	
	
	public lvl1(Player player)
	{
		this.player = player;
		
		try {
			PlaySound.playSound("src/SI/sounds_clips/Space Invaders.wav");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for(int y=0;y<5;y++)
			for(int x=0;x<10;x++)
			{
				EnemyType e = new EnemyTypeBasic(284 + (x*80), 50 + (y*60), 1,3 );
						enemies.add(e);
			}
	}

	@Override
	public void draw(Graphics2D g) {
		if(enemies == null)
			return;
		
		for(int i=0;i<enemies.size();i++)
			enemies.get(i).draw(g);
	}

	@Override
	public void update(double delta, BasicBlocks blocks) {
		if(enemies == null)
			return;
		
		for(int i=0;i<enemies.size();i++)
			enemies.get(i).update(delta, player, blocks);
		
		for(int i=0;i<enemies.size();i++)
			enemies.get(i).collide(i, player, blocks, enemies);
		
	hasDirectionChange(delta);
	}

	@Override
	public void hasDirectionChange(double delta) {
		if(enemies == null)
			return;
		
		for(int i=0;i<enemies.size();i++)
			if(enemies.get(i).isOutOfBounds())
				changeDirectionAllEnemy(delta);
	}

	@Override
	public void changeDirectionAllEnemy(double delta) {
		for(int i=0;i<enemies.size();i++)
			enemies.get(i).changeDirection(delta);
	}

	@Override
	public boolean isGameOver() {
		return false;
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void reset() {
		
	}

}
