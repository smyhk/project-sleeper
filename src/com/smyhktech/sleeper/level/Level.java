package com.smyhktech.sleeper.level;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt; // Store tile id's
	protected int[] tiles;  // Store pixel colors used to generate tiles
	
	public static Level spawn = new SpawnLevel("/levels/spawn.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		
	}

	protected void loadLevel(String path) {

	}
	
	public void update() {
		
	}
	
	private void time() {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		// Define corner pins (render region); convert to tile precision
		int x0 = xScroll >> 4;
		// TODO: future-proof the width and height 16's
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		// Prevent crash when tiles index is < 0 or exceed map dimensions
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		if (tiles[x + y * width] == Tile.colorSpawnFloor) return Tile.spawnFloor;
		if (tiles[x + y * width] == Tile.colorSpawnGrass) return Tile.spawnGrass;
		if (tiles[x + y * width] == Tile.colorSpawnColoredBrick) return Tile.spawnColoredBrick;
		if (tiles[x + y * width] == Tile.colorSpawnGrayBrick) return Tile.spawnGrayBrick;
		if (tiles[x + y * width] == Tile.colorSpawnHedge) return Tile.spawnHedge;
		if (tiles[x + y * width] == Tile.colorSpawnMediumWater) return Tile.spawnMediumWater;
		return Tile.voidTile;
		
	}
}

// x < 0 || y < 0 || x >= width || y >= height
