package SI.levels;

import java.awt.Graphics2D;

import SI.game_screen.BasicBlocks;

public interface SuperLvl {

	void draw(Graphics2D g);
	void update(double delta, BasicBlocks blocks);
	void hasDirectionChange(double delta);
	void changeDirectionAllEnemy(double delta);
	
	boolean isGameOver();
	
	void destroy();
	void reset();
}
