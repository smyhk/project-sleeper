package com.smyhktech.sleeper.level.tile;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class GrassTile  extends Tile{

	public GrassTile(Sprite sprite) {
		super(sprite);
		
	}


	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this);
	}

}
