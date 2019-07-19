package SI.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import SI.state.StateMachine;

public class Display extends Canvas implements Runnable{
	
	public static final int WIDTH = 1280, HEIGHT = 720;


	private static final long serialVersionUID = 1L;

	public static void main(String [] args) {
		
		Display display = new Display();
		JFrame window = new JFrame();
		
		window.add(display);
		window.pack();
		window.setTitle("Space Invaders");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		display.start();
		
		
	}
	
	private boolean running = false;
	private Thread thread;
	
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int FPS;
	
	public static StateMachine state;
	
	
	public Display()
	{
		this.setSize(WIDTH, HEIGHT);
		this.setFocusable(true);
		
		state = new StateMachine(this);
		state.setState((byte) 0);
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastLoopTime = System.nanoTime();
		final int Targert_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / Targert_FPS;
		int frames = 0;
		
		
		this.createBufferStrategy(3); //sprobowac 2
		BufferStrategy bs = this.getBufferStrategy();
		
		while(running)
		{
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer +=1000;
				FPS = frames;
				frames = 0;
			}
			
			draw(bs);
			update(delta);
			
			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000000);
			}catch(Exception e) {};
		}
}


	public void draw(BufferStrategy bs)
	{
		do {
			do {
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				
				state.draw(g);
				
				g.dispose(); 
				
			}while(bs.contentsRestored());
			bs.show();
		}while(bs.contentsLost());
	}
	
	public void update(double delta)
	{
		state.update(delta);
	}

}




