package littleZelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Rectangle { //import Rectangle (Because it has the collision, vectors (to move), etc
	
	public boolean right, up, down, left;
	public int lastSprite = 0;
	public int spd = 2;
	//public int dir = 0; //0 to 3 down, up, left, right
	public int moving = 0;
	public int framemove = 0;
	
	public Enemy (int x, int y) 
	{
		super(x, y, 32, 64); //X, Y location parameters, plus Width and Height - to define accordingly
	}
	
	public int curAnim = 0;
	public int curFrames = 0, targetFrames = 10; //the higher the frames, slower the animation
	
	public void tick()
	{
		boolean moved = false;
		framemove++;
		
		if (framemove >= 40)
		{
			int randomNum = ThreadLocalRandom.current().nextInt(0,5);
			moving = randomNum;
			framemove = 0;
		}
		
		if (moving == 0)
		{
			moved = true;
			right = false;
			left = false;
			up = false;
			down = true;
		}
		else if (moving == 1)
		{
			moved = true;
			down = false;
			right = false;
			left = false;
			up = true;
		}
		else if (moving == 2)
		{
			moved = true;
			down = false;
			up = false;
			right = false;
			left = true;
		}
		else if (moving == 3)
		{
			moved = true;
			down = false;
			up = false;
			left = false;
			right = true;
		}
		else 
		{
			moved = false;
			down = false;
			up = false;
			left = false;
			right = false;
		}
		
		if(right && World.IsFree(x+spd, y))
		{
			moved = true;
			lastSprite = 3;
			//dir = 3;
			x+=spd;
		}
		else if(left && World.IsFree(x-spd, y))
		{
			moved = true;
			lastSprite = 2;
			//dir = 2;
			x-=spd;
		}
		else if(up && World.IsFree(x, y-spd))
		{
			moved = true;
			lastSprite = 1;
			//dir = 1;
			y-=spd;
		}
		else if(down && World.IsFree(x, y+spd))
		{
			moved = true;
			lastSprite = 0;
			//dir = 0;
			y+=spd;
		}
		
		if(moved)
		{
			curFrames++;
			if(curFrames == targetFrames)
			{
				curFrames = 0;
				curAnim++;
				if(curAnim == Spritesheet.enemy_front.length)
				{
					curAnim = 0;
				}
			}
		}
		else
		{
			curAnim = 0;
		}
		
		/*if (lastSprite == 1)
		{
			dir = 1;
		}
		else if (lastSprite == 2)
		{
			dir = 2;
		}
		else if (lastSprite == 3)
		{
			dir = 3;
		}
		else 
		{
			dir = 0;
		}*/
	} 

	public void render(Graphics g)
	{
		if(down) {g.drawImage(Spritesheet.enemy_front[curAnim], x, y, 66, 126, null);
		lastSprite = 0;
		}
		else if(left) {g.drawImage(Spritesheet.enemy_left[curAnim], x, y, 96, 90, null);
		lastSprite = 2;
		}
		else if(right) {g.drawImage(Spritesheet.enemy_right[curAnim], x, y, 96, 90, null);
		lastSprite = 3;
		}
		else if(up) {g.drawImage(Spritesheet.enemy_back[curAnim], x, y, 72, 90, null);
		lastSprite = 1;
		}
		else { //if statement to fix mistake of returning sprite to down | front position and staying 
			//in the last pose, it checks lastSprite
			{
				if (lastSprite == 1)
				{
					g.drawImage(Spritesheet.enemy_back[curAnim], x, y, 72, 90, null);
				}
				else if (lastSprite == 2)
				{
					g.drawImage(Spritesheet.enemy_left[curAnim], x, y, 96, 90, null);
				}
				else if (lastSprite == 3)
				{
					g.drawImage(Spritesheet.enemy_right[curAnim], x, y, 96, 90, null);
				}
				else
				{
					g.drawImage(Spritesheet.enemy_front[curAnim], x, y, 66, 126, null);
				}
			}
		}
	}
	
	public Rectangle bounds()
	{
		if (lastSprite == 1)
		{
			return (new Rectangle(x, y, 72, 90));
		}
		else if (lastSprite == 2)
		{
			return (new Rectangle(x, y, 96, 90));
		}
		else if (lastSprite == 3)
		{
			return (new Rectangle(x, y, 96, 90));
		}
		else
		{
			return (new Rectangle(x, y, 66, 126));
		}
	}
}
