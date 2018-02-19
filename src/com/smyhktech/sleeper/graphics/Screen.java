package com.smyhktech.sleeper.graphics;

import java.util.Random;

import com.smyhktech.sleeper.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;  // For bitwise operations
	
	public int xOffset, yOffset;
	
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
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.size; y++) {
			int yAbs = y + yp;
			for (int x = 0; x < tile.sprite.size; x++) {
				int xAbs = x + xp;
				
				// Render only what needs to be seen on the screen
				if (xAbs < 0 || xAbs >= width || yAbs < 0 || yAbs >= width) break;
				pixels[xAbs + yAbs * width] = tile.sprite.pixels[x + y * tile.sprite.size];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
