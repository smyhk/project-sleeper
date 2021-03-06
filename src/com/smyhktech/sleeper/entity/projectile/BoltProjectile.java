package com.smyhktech.sleeper.entity.projectile;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import com.smyhktech.sleeper.entity.spawner.ParticleSpawner;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;


public class BoltProjectile extends Projectile {

	public BoltProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;  // Could be randomized for effect
		speed = 4;
		damage = 20;
		rateOfFire = 10; // Higher the number, slower the render
		sprite = Sprite.boltProjectile;
		
		nx = speed * Math.cos(angle);  // Vector math for animating projectile
		ny = speed * Math.sin(angle);  //
	}
	
	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 8, 4, 4)) {
			level.addEntity(new ParticleSpawner((int) x, (int) y, 44, 50, level));
			remove();
		}
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;

		if (calculateDistance() > range) remove(); // Remove the bolt when range exceeded
	}
	
	private double calculateDistance() {
		double dist = 0.0;
		dist = sqrt(Math.abs(pow(xOrigin - x, 2) + pow(yOrigin - y, 2)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 10, (int) y - 6, this); // Must be cast to int for rendering
	}
}
