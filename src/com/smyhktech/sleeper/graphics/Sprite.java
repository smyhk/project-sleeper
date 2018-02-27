/**
 * Models a sprite
 */
package com.smyhktech.sleeper.graphics;

public class Sprite {

	private SpriteSheet sheet;

	public int size;
	private int x, y;
	private int width, height;
	public int[] pixels;
	
	// Map tile sprites
	public static Sprite grass = new Sprite(16, 0, 6, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0);  // Essentially a blank sprite for blank tiles
	
	// Spawn level sprites here:
	public static Sprite spawnGrass = new Sprite(16, 0, 0, SpriteSheet.spawnLevel);
	public static Sprite spawnHedge = new Sprite(16, 1, 0, SpriteSheet.spawnLevel);
	public static Sprite spawnMediumWater = new Sprite(16, 2, 0, SpriteSheet.spawnLevel);
	public static Sprite spawnGrayBrick = new Sprite(16, 0, 1, SpriteSheet.spawnLevel);
	public static Sprite spawnColoredBrick = new Sprite(16, 0, 2, SpriteSheet.spawnLevel);
	public static Sprite spawnFloor = new Sprite(16, 1, 1, SpriteSheet.spawnLevel);
	
	// Player sprites; note larger sprite size
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
	
	// Projectile sprites
	public static Sprite boltProjectile = new Sprite(16, 0, 0, SpriteSheet.boltProjectile);
	
	// Particles
	public static Sprite particleNormal = new Sprite(3, 0xaaaaaa);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.size = size;
		this.width = size;
		this.height = size;
		pixels = new int[size * size];
		// target coordinates for sprite in sprite sheet
		this.x = x * size;
		this.y = y * size;

		this.sheet = sheet;
		loadSprite();
	}
	
	public Sprite(int width, int height, int color) {
		size = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	// Constructor for void sprite
	public Sprite(int size, int color) {
		this.size = size;
		this.width = size;
		this.height = size;
		pixels = new int[size * size];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	private void loadSprite() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
		
	}

}
