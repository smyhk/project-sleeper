package com.smyhktech.sleeper.level.tile.spawn;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;
import com.smyhktech.sleeper.level.tile.Tile;

public class SpawnColoredBrickTile extends Tile {

	public SpawnColoredBrickTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void render(int x, int y, Screen screen) {
		// Bitwise shift to convert back to pixel precision
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}
