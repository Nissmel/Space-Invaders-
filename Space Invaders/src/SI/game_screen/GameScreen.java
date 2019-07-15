package SI.game_screen;

import java.awt.Canvas;
import java.awt.Graphics2D;

import SI.state.SuperStateMachine;

public class GameScreen implements SuperStateMachine{

	private Player player;
	
	public GameScreen() {
		player = new Player(150, 150, 50, 50);
	}
	
	@Override
	public void update(double delta) {
		player.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		player.draw(g);
	}

	@Override
	public void initialize(Canvas canvas) {
		canvas.addKeyListener(player);
	}

}
