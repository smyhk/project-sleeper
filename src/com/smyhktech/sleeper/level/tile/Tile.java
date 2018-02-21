package com.smyhktech.sleeper.level.tile;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;
import com.smyhktech.sleeper.level.tile.spawn.SpawnColoredBrickTile;
import com.smyhktech.sleeper.level.tile.spawn.SpawnFloorTile;
import com.smyhktech.sleeper.level.tile.spawn.SpawnGrassTile;
import com.smyhktech.sleeper.level.tile.spawn.SpawnGrayBrickTile;
import com.smyhktech.sleeper.level.tile.spawn.SpawnHedgeTile;
import com.smyhktech.sleeper.level.tile.spawn.SpawnMediumWaterTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	// Map tiles
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	// Spawn tiles
	public static Tile spawnGrass = new SpawnGrassTile(Sprite.spawnGrass);
	public static Tile spawnHedge = new SpawnHedgeTile(Sprite.spawnHedge);
	public static Tile spawnMediumWater = new SpawnMediumWaterTile(Sprite.spawnMediumWater);
	public static Tile spawnGrayBrick = new SpawnGrayBrickTile(Sprite.spawnGrayBrick);
	public static Tile spawnColoredBrick = new SpawnColoredBrickTile(Sprite.spawnColoredBrick);
	public static Tile spawnFloor = new SpawnFloorTile(Sprite.spawnFloor);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean breakable() {
		return false;
	}
}
