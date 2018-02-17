package com.smyhktech.sleeper.graphics;

public class Screen {

	private int width, height;
	public int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];  // Each index is one pixel
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
			for (int x = 0; x < width; x++) {
				pixels[40 + 100 * width] = 0xff00ff;
			}
		}
	}
}
