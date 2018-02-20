/**
 * Models a sprite
 */
package com.smyhktech.sleeper.graphics;

public class Sprite {

	private SpriteSheet sheet;

	public int size;
	private int x, y;
	public int[] pixels;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0);  // Essentially a blank sprite for blank tiles
	public static Sprite player = new Sprite(16, 0, 1, SpriteSheet.tiles);
	
	// How to define a larger sprite
	// public static Sprite player = new Sprite(32, 0, 5, Spritesheet.sheet)  Count be 32 pixel vice 16

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.size = size;
		pixels = new int[size * size];
		// target coordinates for sprite in sprite sheet
		this.x = x * size;
		this.y = y * size;

		this.sheet = sheet;
		loadSprite();
	}
	
	public Sprite(int size, int color) {
		this.size = size;
		pixels = new int[size * size];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < size * size; i++) {
			pixels[i] = color;
		}
	}

	private void loadSprite() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size];
			}
		}
		
	}

}
