package com.smyhktech.sleeper.level;

public class TileCoordinate {
	
	private int x, y;
	private final int TILE_SIZE = 16;
	
	public TileCoordinate(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int[] getCoordinates() {
		int[] coord = {getX(), getY()};
		return coord;
	}
}
