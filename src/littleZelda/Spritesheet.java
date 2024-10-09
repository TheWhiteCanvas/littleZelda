package littleZelda;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	public static BufferedImage spritesheet;
	
	public static BufferedImage[] player_front;
	public static BufferedImage[] player_back;
	public static BufferedImage[] player_right;
	public static BufferedImage[] player_left;
	
	public static BufferedImage[] enemy_front;
	public static BufferedImage[] enemy_back;
	public static BufferedImage[] enemy_right;
	public static BufferedImage[] enemy_left;
	
	public static BufferedImage[] eliteguardian_front;
	public static BufferedImage[] eliteguardian_back;
	public static BufferedImage[] eliteguardian_right;
	public static BufferedImage[] eliteguardian_left;
	
	public static BufferedImage[] boss_front;
	public static BufferedImage[] boss_back;
	public static BufferedImage[] boss_right;
	public static BufferedImage[] boss_left;
	
	public static BufferedImage arrow_front;
	public static BufferedImage arrow_back;
	public static BufferedImage arrow_left;
	public static BufferedImage arrow_right;
	
	public static BufferedImage tileWall;
	public static BufferedImage tileFloor;
	
	public Spritesheet()
	{
		try {
			spritesheet = ImageIO.read(getClass().getResource("/sprites001.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tileWall = Spritesheet.getSprite(240, 304, 16, 16);
		tileFloor = Spritesheet.getSprite(528, 145, 16, 16);
		
		player_front = new BufferedImage[2];
		player_back = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		enemy_front = new BufferedImage[3];
		enemy_back = new BufferedImage[3];
		enemy_left = new BufferedImage[3];
		enemy_right = new BufferedImage[3];
		
		eliteguardian_front = new BufferedImage[3];
		eliteguardian_back = new BufferedImage[3];
		eliteguardian_left = new BufferedImage[3];
		eliteguardian_right = new BufferedImage[3];
		
		boss_front = new BufferedImage[3];
		boss_back = new BufferedImage[3];
		boss_left = new BufferedImage[3];
		boss_right = new BufferedImage[3];
		
		player_front[0] = Spritesheet.getSprite(445, 359, 22, 26);
		player_front[1] = Spritesheet.getSprite(472, 359, 22, 26);
		
		player_back[0] = Spritesheet.getSprite(566, 355, 25, 29);
		player_back[1] = Spritesheet.getSprite(597, 355, 25, 29);
		
		player_left[0] = Spritesheet.getSprite(587, 507, 26, 26);
		player_left[1] = Spritesheet.getSprite(619, 507, 26, 26);
		
		player_right[0] = Spritesheet.getSprite(502, 358, 26, 27);
		player_right[1] = Spritesheet.getSprite(534, 358, 26, 27);
		
		arrow_front = Spritesheet.getSprite(441, 453, 9, 15);
		arrow_back = Spritesheet.getSprite(490, 452, 9, 16);
		arrow_right = Spritesheet.getSprite(458, 456, 17, 7);
		arrow_left = Spritesheet.getSprite(590, 546, 17, 7);
		
		enemy_front[0] = Spritesheet.getSprite(432, 802, 22, 42);
		enemy_front[1] = Spritesheet.getSprite(462, 802, 22, 44);
		enemy_front[2] = Spritesheet.getSprite(489, 802, 22, 42);
		
		enemy_back[0] = Spritesheet.getSprite(431, 886, 24, 30);
		enemy_back[1] = Spritesheet.getSprite(459, 886, 24, 30);
		enemy_back[2] = Spritesheet.getSprite(487, 886, 25, 29);
		
		enemy_left[0] = Spritesheet.getSprite(425, 920, 32, 30);
		enemy_left[1] = Spritesheet.getSprite(461, 920, 32, 30);
		enemy_left[2] = Spritesheet.getSprite(495, 920, 32, 30);
		
		enemy_right[0] = Spritesheet.getSprite(431, 846, 30, 30);
		enemy_right[1] = Spritesheet.getSprite(466, 846, 32, 30);
		enemy_right[2] = Spritesheet.getSprite(501, 846, 32, 30);
		
		eliteguardian_front[0] = Spritesheet.getSprite(10, 683, 25, 35);
		eliteguardian_front[1] = Spritesheet.getSprite(39, 683, 25, 39);
		eliteguardian_front[2] = Spritesheet.getSprite(68, 683, 25, 35);
		
		eliteguardian_back[0] = Spritesheet.getSprite(10, 887, 24, 27);
		eliteguardian_back[1] = Spritesheet.getSprite(37, 887, 24, 27);
		eliteguardian_back[2] = Spritesheet.getSprite(64, 887, 24, 27);
		
		eliteguardian_left[0] = Spritesheet.getSprite(278, 921, 32, 27);
		eliteguardian_left[1] = Spritesheet.getSprite(313, 921, 32, 28);
		eliteguardian_left[2] = Spritesheet.getSprite(348, 921, 32, 27);
		
		eliteguardian_right[0] = Spritesheet.getSprite(14, 791, 31, 27);
		eliteguardian_right[1] = Spritesheet.getSprite(47, 792, 31, 25);
		eliteguardian_right[2] = Spritesheet.getSprite(82, 792, 33, 27);
		
		boss_front[0] = Spritesheet.getSprite(10, 1168, 26, 26);
		boss_front[1] = Spritesheet.getSprite(72, 1168, 26, 26);
		boss_front[2] = Spritesheet.getSprite(133, 1168, 26, 26);
		
		boss_back[0] = Spritesheet.getSprite(8, 1277, 27, 25);
		boss_back[1] = Spritesheet.getSprite(78, 1277, 27, 25);
		boss_back[2] = Spritesheet.getSprite(153, 1277, 27, 25);
		
		boss_left[0] = Spritesheet.getSprite(240, 1166, 16, 27);
		boss_left[1] = Spritesheet.getSprite(263, 1166, 16, 27);
		boss_left[2] = Spritesheet.getSprite(285, 1166, 16, 27);
		
		boss_right[0] = Spritesheet.getSprite(241, 1202, 16, 27);
		boss_right[1] = Spritesheet.getSprite(263, 1202, 16, 27);
		boss_right[2] = Spritesheet.getSprite(287, 1202, 16, 27);
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height)
	{
		return spritesheet.getSubimage(x, y, width, height);
	}
	
}