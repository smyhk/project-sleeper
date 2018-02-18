/**
 * Manages sprite sheets and caching to memory
 */
package com.smyhktech.sleeper.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public int size = 0;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
	
	// Constructor
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.size = size;
		pixels = new int[size * size];
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
