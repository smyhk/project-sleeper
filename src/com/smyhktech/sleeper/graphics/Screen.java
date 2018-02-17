package com.smyhktech.sleeper.graphics;

import java.util.Random;

public class Screen {

	private int width, height;
	public int[] pixels;
	public int[] tiles = new int[64 * 64];
	
	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];  // Each index is one pixel
		
		for (int i = 0; i < 64*64; i++) {
			tiles[i] = random.nextInt(0xffffff);
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
	public void render() {
		
		for (int y = 0; y < height; y++) {
			if (y < 0 || y >= height) break;
			for (int x = 0; x < width; x++) {
				if (x < 0 || x >= width) break;
				int tileIndex = (x / 16) + (y / 16 ) * 64;  // Bitwise right shift was lower fps
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
	}
}
