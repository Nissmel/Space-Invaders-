package SI.state;

import java.awt.Canvas;
import java.awt.Graphics2D;

public interface SuperStateMachine {

	public void update(double delta);
	public void draw(Graphics2D g);
	public void initialize(Canvas canvas);
	
}
