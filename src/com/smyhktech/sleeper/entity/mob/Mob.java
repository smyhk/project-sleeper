package com.smyhktech.sleeper.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.entity.projectile.BoltProjectile;
import com.smyhktech.sleeper.entity.projectile.Projectile;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected boolean moving = false;
	protected List<Projectile> projectiles = new ArrayList<>();
	protected boolean walking = false;
	
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction dir;
	
	public void move(int xa, int ya) {
		// Fixes sliding when in a collision
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = Direction.RIGHT;  // East; Right
		if (xa < 0) dir = Direction.LEFT;  // West; Left
		if (ya > 0) dir = Direction.DOWN;  // South; Down
		if (ya < 0) dir = Direction.UP;  // North; Up
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	public abstract void update();

	public abstract void render(Screen screen);
	
	protected void shoot(int x, int y, double direction) {
		//direction *= 180 / Math.PI;  // Convert to degrees
		Projectile p = new BoltProjectile(x, y, direction);
		projectiles.add(p);
		level.addEntity(p);
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
}
