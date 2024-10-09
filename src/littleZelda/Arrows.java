package littleZelda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Arrows extends Rectangle {
	//extends Rectangle to import collisions
	
	public int dir = 1; //variable to define direction;
	public int ver = 1; //he taught only how to fire to the sides, here I'm defining the vertical axes for firing up and down;
	public int axis = 0;
	public int speed = 8; //speed of the arrow;
	
	public int frames = 0; //frames to destroy the arrows after a while
	
	//now to the construct method:
	
	public Arrows( int x, int y, int dir, int ver)
	{
		//we need now the position x and y when we call this method
		super(x+16, y+16, 8, 8); //super to call the constructor method inherited from the Rectangle Class: (pos x, pos y, size x, size y)
		this.dir = dir; //horizontal axis for the arrow
		this.ver = ver; //vertical axis for the arrow
	}
	
	//now to the update method for our arrows:
	public void tick()
	{
		if (World.IsFree(x, y)) //instancing arrows if not colliding with the world
		{
			x+=speed*dir; 
			y+=speed*ver;
			frames++;
			if (frames == 60)
			{
				Player.arrows.remove(this);
				return;
			}
		}
		else
		{
			Player.arrows.remove(this); //if colliding, remove the arrows; It's nicer if they stick on the wall
			return;                     // but it's bad for performance, so get rid of them.  
		}
	}
	
	public void render(Graphics g)
	{
		//g.setColor(Color.yellow);
		//g.fillRect(x, y, width, height);
		if (dir == 1)
		{
			axis = 2;
			g.drawImage(Spritesheet.arrow_right, x+10, y+10, 48, 20, null);
		}
		else if (dir == -1)
		{
			axis = 3;
			g.drawImage(Spritesheet.arrow_left, x-10, y+10, 48, 20, null);
		}
		else if (ver == -1)
		{
			axis = 1;
			g.drawImage(Spritesheet.arrow_back, x+25, y, 26, 44, null);
		}
		else
		{
			axis = 0;
			g.drawImage(Spritesheet.arrow_front, x, y+5, 26, 44, null);
		}
	}
	
	public Rectangle bounds()
	{
		if (axis == 1)
		{
			return (new Rectangle(x, y, 48, 20));
		}
		else if (axis == 2)
		{
			return (new Rectangle(x, y, 26, 44));
		}
		else if (axis == 3)
		{
			return (new Rectangle(x, y, 26, 44));
		}
		else 
		{
			return (new Rectangle(x, y, 48, 20));
		}
	}
}
