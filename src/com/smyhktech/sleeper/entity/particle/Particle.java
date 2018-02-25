package com.smyhktech.sleeper.entity.particle;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class Particle extends Entity {
	
	private Sprite sprite;
	
	protected int life;
	private int time = 0;
	
	protected double xx, yy, xa, ya;  // Amount of pixels to move
	
	public Particle(int x, int y, int life) {
		System.out.println("Particle life: " + life);
		this.x = y;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(25) - 10);
		sprite = Sprite.particleNormal;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		this.xx += xa;
		this.yy += ya;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}
}
