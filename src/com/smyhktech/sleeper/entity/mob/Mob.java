package com.smyhktech.sleeper.entity.mob;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = -1;
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		
		// Fixes sliding when in a collision
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = 1;  // East; Right
		if (xa < 0) dir = 3;  // West; Left
		if (ya > 0) dir = 2;  // South; Down
		if (ya < 0) dir = 0;  // North; Up
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	public void update() {
		
	}
	
	public boolean collision(int xa, int ya) {
		boolean solid = false;
		if (level.getTile((x + xa) >> 4, (y + ya) >> 4).solid()) solid = true; // Convert to tile precision (from pixel)
		return solid;
	}
	
	public void render() {
		
	}
}
