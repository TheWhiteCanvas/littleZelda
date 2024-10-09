package littleZelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle { //extends Rectangle because it has all the collision system and movement vectors
	
	public boolean right, left, up, down; // boolean variables to control the input movement 
	
	public int speed = 4;
	public int lastSprite;
	
	public int dir = 0; //horizontal axis for the arrow;
	public int ver = 1; //vertical axis for the arrow;
	public int arrowsquantity = 1; 
	
	public Player(int x, int y) 
	{
		super(x, y, 32, 64); // x, y are reference values to the position of the player plus width and height.
	}
	
	public int curAnim = 0; //variable to control the Animation - (index for the array)
	public int curFrames = 0, targetFrames = 10; //control the frames of the animation. The higher the targetFrames, slower the Animation

	public static List<Arrows> arrows = new ArrayList<Arrows>(); //Adding the ammo/bullets for ranged combat; CTRL+SHIFT+O to import on ArrayList; Instancing the class.
	public List<Enemy> enemies = new ArrayList<Enemy>();
	
	public boolean shoot = false;
	public boolean iscolliding = false;
	public int frameswait = 10;
	
	public void tick() //creating logic
	{
		
		boolean moved = false; 
		frameswait++; //waiting for arrows to be relaunched;
		
		if((iscolliding == false) && right && World.IsFree(x+speed, y))
		{
			moved = true;
			ver = 0;
			dir = 1;
			x+=speed;
		}
		else if((iscolliding == false) && left && World.IsFree(x-speed, y))
		{
			moved = true;
			ver = 0;
			dir = -1;
			x-=speed;
		}
		
		if((iscolliding == false) && up && World.IsFree(x, y-speed))
		{
			moved = true;
			ver = -1;
			dir = 0;
			y-=speed;
		}
		if((iscolliding == false) && down && World.IsFree(x, y+speed))
		{
			moved = true;
			ver = 1;
			dir = 0;
			y+=speed;
		}
		
		if (moved)
		{
			curFrames++;
			if (curFrames == targetFrames)
			{
				curFrames = 0;
				curAnim++;
				if (curAnim == Spritesheet.player_front.length)
				{
					curAnim = 0;
				}
			}
		}
		else
		{
			curAnim = 0;
		}
		
		//now, check the arrow direction and axis
		
		if (lastSprite == 1)
		{
			ver = -1;
			dir = 0;
		}
		else if (lastSprite == 2)
		{
			ver = 0;
			dir = -1;
		}
		else if (lastSprite == 3)
		{
			ver = 0;
			dir = 1;
		}
		else 
		{
			ver = 1;
			dir = 0;
		}
		
		//next the method to fire the arrows:
		
		if (shoot == true)
		{
			if (frameswait >= 20) //only allow arrows to be released after at least 10 frames since the last one
			{
				arrows.add(new Arrows(x, y, dir, ver));
				frameswait = 0; //reset frames counting till next arrow
				shoot = false;
			}
		}
		
		for (int i=0; i < arrows.size(); i++)
		{
			arrows.get(i).tick();
		}			
		
	}
	
	public void render(Graphics g)
	{
		//g.setColor(Color.lightGray);
		//g.fillRect(x, y, width, height);
		
		if(down)
		{
			g.drawImage(Spritesheet.player_front[curAnim], x, y, 66, 78, null);
			lastSprite = 0;
		}
		else if(up)
		{
			g.drawImage(Spritesheet.player_back[curAnim], x, y, 78, 90, null);
			lastSprite = 1;
		}
		else if(left)
		{
			g.drawImage(Spritesheet.player_left[curAnim], x, y, 78, 78, null);
			lastSprite = 2;
		}
		else if(right)
		{
			g.drawImage(Spritesheet.player_right[curAnim], x, y, 78, 81, null);
			lastSprite = 3;
		}
		else
		{
			if(lastSprite == 1)
			{
				g.drawImage(Spritesheet.player_back[curAnim], x, y, 78, 90, null);
			}
			else if(lastSprite == 2)
			{
				g.drawImage(Spritesheet.player_left[curAnim], x, y, 78, 78, null);
			}
			else if(lastSprite == 3)
			{
				g.drawImage(Spritesheet.player_right[curAnim], x, y, 78, 81, null);
			}
			else
			{
				g.drawImage(Spritesheet.player_front[curAnim], x, y, 66, 78, null);
			}
		}
		
		for (int i=0; i < arrows.size(); i++) //method to render the arrows
		{
			arrows.get(i).render(g);
		}	
	}
	
	public Rectangle bounds()
	{
		if (lastSprite == 1)
		{
			return (new Rectangle(x, y, 78, 90));
		}
		else if (lastSprite == 2)
		{
			return (new Rectangle(x, y, 78, 78));
		}
		else if (lastSprite == 3)
		{
			return (new Rectangle(x, y, 78, 81));
		}
		else
		{
			return (new Rectangle(x, y, 66, 78));
		}
	}
	
}
