package com.smyhktech.sleeper.entity.mob;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		if (xa > 0) dir = 1;  // East; Right
		if (xa < 0) dir = 3;  // West; Left
		if (ya > 0) dir = 2;  // South; Down
		if (ya < 0) dir = 0;  // North; Up
		
		if (!collision()) {
			x += xa;
			y += ya;
		}
	}
	
	public void update() {
		
	}
	
	public boolean collision() {
		return false;
	}
	
	public void render() {
		
	}
}
