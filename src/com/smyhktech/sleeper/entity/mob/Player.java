package com.smyhktech.sleeper.entity.mob;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;
import com.smyhktech.sleeper.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int animate = 0;  // Animation counter
	private boolean walking = false;
	public static int pixelSize = 32;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.playerBack0;  // Set an initial direction for the player sprite
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.playerBack0;  // Set an initial direction for the player sprite
	}
	
	public void update() {
		int xa = 0, ya = 0;  // Direction of movement
		
		if (animate < 7500) {
			animate++;
		} else {
			animate = 0;
		}
		
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.playerForward6;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerForward7;
				} else {
					sprite = Sprite.playerForward8;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.playerRight3;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerRight4;
				} else {
					sprite = Sprite.playerRight5;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.playerBack0;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerBack1;
				} else {
					sprite = Sprite.playerBack2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.playerLeft9;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.playerLeft10;
				} else {
					sprite = Sprite.playerLeft11;
				}
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite);
	}
}