package com.smyhktech.sleeper.entity.projectile;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;


public class BoltProjectile extends Projectile {

	public BoltProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 75;  // Could be randomized for effect
		speed = 2;
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
		
		if (calculateDistance() > range) remove();  // Remove the bolt when range exceeded
	}
	
	private double calculateDistance() {
		double dist = 0.0;
		dist = sqrt(Math.abs(pow(xOrigin - x, 2) + pow(yOrigin - y, 2)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 20, (int) y - 12, this); // Must be cast to int for rendering
	}
}
