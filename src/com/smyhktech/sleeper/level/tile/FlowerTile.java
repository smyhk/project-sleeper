package com.smyhktech.sleeper.level.tile;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class FlowerTile extends Tile {
	
	public FlowerTile(Sprite sprite) {
		super(sprite);
		
	}

	public void render(int x, int y, Screen screen) {
		// Bitwise shift to convert back to pixel precision
		screen.renderTile(x << 4, y << 4, this);
	}
}
