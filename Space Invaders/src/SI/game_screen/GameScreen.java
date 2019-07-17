package SI.game_screen;

import java.awt.Canvas;
import java.awt.Graphics2D;

import SI.state.SuperStateMachine;

public class GameScreen implements SuperStateMachine{

	private BasicBlocks bb;
	private Player player;	
	
	public GameScreen() {
		player = new Player(640, 620, 60, 60, bb);
		bb = new BasicBlocks();
		}
	
	@Override
	public void update(double delta) {
		player.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		player.draw(g);
		bb.draw(g);
	}

	@Override
	public void initialize(Canvas canvas) {
		canvas.addKeyListener(player);
	}

}
