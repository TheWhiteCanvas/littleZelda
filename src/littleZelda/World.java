package littleZelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static List<Blocks> blocks = new ArrayList<Blocks>();
	
	public World()
	{
		
		// Create the blocks manually using a loop. (manually = by code)
		//These are for the edges
		for (int xx = 0; xx < 60; xx++)
		{
			blocks.add(new Blocks(xx*32, 0));
		}
		
		for (int xx = 0; xx < 60; xx++)
		{
			blocks.add(new Blocks(xx*32, 1025));
		}
		
		for (int yy = 0; yy < 60; yy++)
		{
			blocks.add(new Blocks(0, yy*32));
		}
		
		for (int yy = 0; yy < 60; yy++)
		{
			blocks.add(new Blocks(1884, yy*32));
		}
		
		//These are walls
		//Vertical
		for (int kk = 0; kk < 20; kk++)
		{
			blocks.add(new Blocks(448, kk*32));
		}
		for (int kk = 0; kk < 20; kk++)
		{
			blocks.add(new Blocks(480, kk*32));
		}
		for (int kk = 0; kk < 28; kk++)
		{
			blocks.add(new Blocks(992, kk*32));
		}
		for (int kk = 0; kk < 28; kk++)
		{
			blocks.add(new Blocks(1024, kk*32));
		}
		
		//Horizontal
		for (int ww = 0; ww < 10; ww++)
		{
			blocks.add(new Blocks(ww*32, 416));
		}
		for (int ww = 0; ww < 10; ww++)
		{
			blocks.add(new Blocks(ww*32, 384));
		}
	}
	
	public static boolean IsFree(int x, int y)
	{
		for (int i = 0; i < blocks.size(); i++)
		{
			Blocks thisBlock = blocks.get(i);
			
			if (thisBlock.intersects(new Rectangle(x, y+10, 64, 64))) //method to check for collision;
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	public void render(Graphics g)
	{
		for (int i = 0; i < blocks.size(); i++)
		{
			blocks.get(i).render(g); //Render as many blocks as necessary;
		}
	}
	
}
