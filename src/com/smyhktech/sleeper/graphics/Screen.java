package com.smyhktech.sleeper.graphics;

import java.util.Random;

public class Screen {

	private int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;  // For bitwise operations
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];  // Each index is one pixel
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}
	}
	
	/**
	 * Clears the screen
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	/**
	 * Draws on the screen
	 */
	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yp = y + yOffset;
			if (yp < 0 || yp >= height) continue;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width) continue;
				// int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
				// (xx / 16) + (yy / 16 ) * MAP_SIZE;  // Bitwise right shift was lower fps
				pixels[xp + yp * width] = Sprite.grass.pixels[(x&15) + (y&15) * Sprite.grass.size];
			}
		}
	}
}
