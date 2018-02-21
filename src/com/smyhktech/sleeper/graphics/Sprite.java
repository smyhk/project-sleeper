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
	//public static Sprite player = new Sprite(16, 0, 1, SpriteSheet.tiles);
	
	// Note larger sprite size
	public static Sprite playerForward6 = new Sprite(32, 6, 1, SpriteSheet.tiles);
	public static Sprite playerForward7 = new Sprite(32, 7, 1, SpriteSheet.tiles);
	public static Sprite playerForward8 = new Sprite(32, 8, 1, SpriteSheet.tiles);
	
	public static Sprite playerBack0 = new Sprite(32, 0, 1, SpriteSheet.tiles);
	public static Sprite playerBack1 = new Sprite(32, 1, 1, SpriteSheet.tiles);
	public static Sprite playerBack2 = new Sprite(32, 2, 1, SpriteSheet.tiles);
	
	public static Sprite playerLeft9 = new Sprite(32, 9, 1, SpriteSheet.tiles);
	public static Sprite playerLeft10 = new Sprite(32, 10, 1, SpriteSheet.tiles);
	public static Sprite playerLeft11 = new Sprite(32, 11, 1, SpriteSheet.tiles);
	
	public static Sprite playerRight3 = new Sprite(32, 3, 1, SpriteSheet.tiles);
	public static Sprite playerRight4 = new Sprite(32, 4, 1, SpriteSheet.tiles);
	public static Sprite playerRight5 = new Sprite(32, 5, 1, SpriteSheet.tiles);

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
