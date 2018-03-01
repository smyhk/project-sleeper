package com.smyhktech.sleeper.entity.mob;

import com.smyhktech.sleeper.GameMain;
import com.smyhktech.sleeper.entity.projectile.BoltProjectile;
import com.smyhktech.sleeper.graphics.AnimatedSprite;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;
import com.smyhktech.sleeper.graphics.SpriteSheet;
import com.smyhktech.sleeper.input.Keyboard;
import com.smyhktech.sleeper.input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	//private int animate = 0;  // Animation counter
	private int fireRate;
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.playerDown, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.playerUp, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.playerLeft, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.playerRight, 32, 32, 3);
	
	private AnimatedSprite currentSprites;
	
	public static int pixelSize = 32;
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.playerBack0;  // Set an initial direction for the player sprite
		currentSprites = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.playerBack0;  // Set an initial direction for the player sprite
		currentSprites = down;
		fireRate = BoltProjectile.getRateOfFire();
	}
	
	public void update() {
		if (walking) currentSprites.update();
		else currentSprites.setFrame(0);
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;  // Direction of movement
		
//		if (animate < 7500) {
//			animate++;
//		} else {
//			animate = 0;
//		}
		
		if (input.up) {
			ya--;
			currentSprites = up;
		} else if (input.down) {
			ya++;
			currentSprites = down;
		}
		if (input.left) {
			xa--;
			currentSprites = left;
		} else if (input.right) {
			xa++;
			currentSprites = right;
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		
		updateShooting();
	}
	
	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0){
			double dx = Mouse.getX() - GameMain.getWindowWidth() / 2;
			double dy = Mouse.getY() - GameMain.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx); // calculate direction using tangent
			shoot(x, y, dir);
			fireRate = BoltProjectile.getRateOfFire();
		}
	}

	public void render(Screen screen) {
		sprite = currentSprites.getSprite();
		screen.renderMob(x - 16, y - 16, sprite);
	}
}