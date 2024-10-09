	package littleZelda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blocks extends Rectangle {

	public Blocks(int x, int y)
	{
		super(x, y, 32, 32); // Size of the blocks;
	}
	
	public void render(Graphics g)
	{
		/* g.setColor(Color.gray);
		g.fillRect(x, y, width, height);
		g.setColor(Color.darkGray); //edge color
		g.drawRect(x, y, width, height); //creates an edge over the blocks */
		
		g.drawImage(Spritesheet.tileWall, x, y, 32, 32, null);
		g.setColor(Color.lightGray);
		g.drawRect(x, y, width, height);
	}
	
}
