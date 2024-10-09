package littleZelda;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {

	public static BufferedImage sprites;
	public static BufferedImage tileLevel;
	
	public Sprites()
	{
		try {
			sprites = ImageIO.read(getClass().getResource("/sprites002.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tileLevel = Sprites.getSprite(0, 0, 1882, 1058); //level floor sprite
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height)
	{
		return sprites.getSubimage(x, y, width, height);
	}
}
