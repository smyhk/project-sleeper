package com.smyhktech.sleeper.entity.particle;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class Particle extends Entity {
	
	private Sprite sprite;
	
	private int life;
	private int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;  // Amount of pixels to move
	
	public Particle(int x, int y, int life) {
		this.x = y;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particleNormal;
		
		this.xa = random.nextGaussian();
		//if (this.xa < 0) xa = 0.1;
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
		
		move(xx + xa, (yy + ya) + (zz + za));
	}
	
	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		// Tests for any corner being a solid tile
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true; // Convert to tile precision (from pixel)
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx - 1, (int) yy - (int) zz - 1, sprite, true);
	}
}
