package SI.game_screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import SI.levels.lvl1;
import SI.state.SuperStateMachine;

public class GameScreen implements SuperStateMachine{

	private BasicBlocks blocks;
	private Player player;	
	private lvl1 level;
	
	public static int SCORE = 0;
	
	public GameScreen() {
		blocks = new BasicBlocks();
		player = new Player(640, 620, 50, 50, blocks);
		level = new lvl1(player);
	}
	
	@Override
	public void update(double delta) {
		player.update(delta);
		level.update(delta, blocks);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.BOLD, 20));
		g.drawString("Score: " + SCORE + "", 15, 30);
		player.draw(g);
		blocks.draw(g);
		level.draw(g);
	}

	@Override
	public void initialize(Canvas canvas) {
		canvas.addKeyListener(player);
	}

}
