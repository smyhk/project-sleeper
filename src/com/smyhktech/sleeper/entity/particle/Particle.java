package com.smyhktech.sleeper.entity.particle;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class Particle extends Entity {
	
	private Sprite sprite;
	
	protected int life;
	private int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;  // Amount of pixels to move
	
	public Particle(int x, int y, int life) {
		System.out.println("Particle life: " + life);
		this.x = y;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(25) - 10);
		sprite = Sprite.particleNormal;
		
		this.xa = random.nextGaussian() + 1.8;
		if (this.xa < 0) xa = 0.1;
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		za -= 0.1; // simulate gravity
		
		if (zz < 0) {
			zz = 0;  // Don't let particle go beneath the virtual floor
			za *= -0.55; // Slows bounces on floor
			xa *= 0.4;
			ya *= 0.4;
		}
			
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xx - 12, (int) yy - (int) zz, sprite, true);
	}
}
