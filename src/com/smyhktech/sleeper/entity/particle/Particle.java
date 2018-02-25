package com.smyhktech.sleeper.entity.particle;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class Particle extends Entity {
	
	private Sprite sprite;
	
	private int life;
	
	protected double xx, yy, xa, ya;  // Amount of pixels to move
	
	public Particle(int x, int y, int life) {
		this.x = y;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life;
		sprite = Sprite.particleNormal;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public void update() {
		this.xx += xa;
		this.yy += ya;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}
}
