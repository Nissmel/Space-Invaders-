package SI.game_screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class BasicBlocks {
	
	public ArrayList<Rectangle> wall = new ArrayList<Rectangle>();
	
	public BasicBlocks()
	{
		basicBlocks(75, 530);
		basicBlocks(437, 530);
		basicBlocks(799, 530);
		basicBlocks(1150, 530);

	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.GREEN);
		for(int i=0;i<wall.size();i++)
		{
			g.fill(wall.get(i));
		}
	}
	
	public void basicBlocks(int xPos, int yPos)
	{
		int wallWidth = 10;
		int x =0;
		int y =0;
		
		for(int i=0;i<18;i++)
		{
			if((20+(i*2) + wallWidth < 32+wallWidth))
			{
				row(20+(i*2) + wallWidth, xPos - (i*3), yPos + (i*3));
				x=(i*3) +3;
			}
			else
			{
				row(32+wallWidth, xPos - x, yPos + (i*3));
				y=(i*3);
			}
		}
		
		for(int i=0;i<5;i++)
		{
			row(wallWidth - i, xPos -x, (yPos+y) + (i*3));
		}
		
		for(int i=0;i<5;i++)
		{
			row(wallWidth - i, (xPos -x) + (32*3) + (i*3), (yPos+y) + (i*3));
		}
	}
	
	public void row(int rows, int xPos, int yPos)
	{
		for(int i=0;i<rows;i++)
		{
			Rectangle brick = new Rectangle(xPos +(i*3), yPos, 3,3);
			this.wall.add(brick);			
		}
	}
	
	public void reset()
	{
		wall.clear();
		
		basicBlocks(75, 530);
		basicBlocks(437, 530);
		basicBlocks(799, 530);
		basicBlocks(1150, 530);
	}
}
