/**
 * Manages sprite sheets and caching to memory
 */
package com.smyhktech.sleeper.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE;
	//public int width, height;
	public int[] pixels;
	public final int WIDTH, HEIGHT;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 384);
	public static SpriteSheet spawnLevel = new SpriteSheet("/textures/sheets/spawnlevel.png", 48);
	public static SpriteSheet boltProjectile = new SpriteSheet("/textures/projectiles/boltProjectile.png", 48);
	
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/playerSheet.png", 128, 96);
	public static SpriteSheet playerDown = new SpriteSheet(player, 0, 0, 1, 3, 32);
	
	// Constructors
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;  // Offset
		int yy = y * spriteSize;  // Offset
		int w = width * spriteSize;  // Pixel precision
		int h = height * spriteSize; // Pixel precision
		
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;

		pixels = new int[w * h];
		
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
	}
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		loadSheet();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		loadSheet();
	}
	
	private void loadSheet() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0,  0, w, h, pixels, 0, w);  // Loads the sprite sheet into the pixels array
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
