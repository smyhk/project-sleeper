package com.smyhktech.sleeper.entity.projectile;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class BoltProjectile extends Projectile {

	public BoltProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.boltProjectile;
		
		nx = speed * Math.cos(angle);  // Vector math for animating projectile
		ny = speed * Math.sin(angle);  //
	}
	
	public void update() {
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int) x - 20, (int) y - 12, this); // Must be cast to int for rendering
	}
}
