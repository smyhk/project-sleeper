package com.smyhktech.sleeper.graphics;

import java.util.Random;

import com.smyhktech.sleeper.entity.mob.Player;
import com.smyhktech.sleeper.entity.projectile.Projectile;
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
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int yAbs = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xAbs = x + xp;
				
				// Render only what needs to be seen on the screen
				if (xAbs < -tile.sprite.SIZE || xAbs >= width || yAbs < 0 || yAbs >= height) break;
				if (xAbs < 0) xAbs = 0; // Prevent crash for partial tiles
				pixels[xAbs + yAbs * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int yAbs = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xAbs = x + xp;
				
				// Render only what needs to be seen on the screen
				if (xAbs < -p.getSpriteSize() || xAbs >= width || yAbs < 0 || yAbs >= height) break;
				if (xAbs < 0) xAbs = 0; // Prevent crash for partial tiles
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != 0xffff00ff) pixels[xAbs + yAbs * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < Player.pixelSize; y++) {
			int yAbs = y + yp;
			for (int x = 0; x < Player.pixelSize; x++) {
				int xAbs = x + xp;
				
				// Render only what needs to be seen on the screen
				if (xAbs < -Player.pixelSize || xAbs >= width || yAbs < 0 || yAbs >= height) break;
				if (xAbs < 0) xAbs = 0; // Prevent crash for partial tiles
				int col = sprite.pixels[x + y * Player.pixelSize];
				if (col != 0xffff00ff) pixels[xAbs + yAbs * width] = col;  // Do not render sprite background color; note the ff after 0x
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
