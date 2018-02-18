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

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.size = size;
		pixels = new int[size * size];
		// target coordinates for sprite in sprite sheet
		this.x = x * size;
		this.y = y * size;

		this.sheet = sheet;
		loadSprite();
	}

	private void loadSprite() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size];
			}
		}
		
	}

}
