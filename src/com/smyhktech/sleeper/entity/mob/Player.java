package com.smyhktech.sleeper.entity.mob;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;
import com.smyhktech.sleeper.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	public static int pixelSize = 32;

	public Player(Keyboard input) {
		this.input = input;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void update() {
		int xa = 0, ya = 0;  // Direction of movement
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if (xa != 0 || ya != 0) move(xa, ya);
	}
	
	public void render(Screen screen) {
		screen.renderPlayer(x - 16, y - 16, Sprite.player);
	}
}