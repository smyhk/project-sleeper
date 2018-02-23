package com.smyhktech.sleeper.entity.projectile;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;  // Overrides Entity x, y; used for precision math
	protected double nx, ny;  // New x,y
	protected double distance;  // From origin
	protected double speed, range, damage;
	protected static int rateOfFire;
	
	public Projectile(int x, int y, double dir) {
		this.x = x;  // From Entity superclass
		this.y = y;  // From Entity superclass
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.size;
	}
	
	protected void move() {
		
	}
	
	public static int getRateOfFire() {
		return rateOfFire;
	}
}
