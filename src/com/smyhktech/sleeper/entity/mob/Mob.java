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
	
	protected void shoot(int x, int y, double direction) {
		// direction *= 180 / Math.PI; conver to degrees
		System.out.println("Angle: " + direction);
	}
	
	public boolean collision(int xa, int ya) {
		boolean solid = false;
		// Tests for any corner being a solid tile
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) >> 4;
			int yt = ((y + ya) + c / 2 * 12 + 3) >> 4;
			if (level.getTile(xt, yt).solid()) solid = true; // Convert to tile precision (from pixel)
		}
		return solid;
	}
	
	public void render() {
		
	}
	
}
