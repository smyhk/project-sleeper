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
	private int animate = 0;  // Animation counter
	private boolean walking = false;
	private int fireRate;
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.playerDown, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.playerUp, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.playerLeft, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.playerRight, 32, 32, 3);
	
	private AnimatedSprite currentSpites;
	
	public static int pixelSize = 32;
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.playerBack0;  // Set an initial direction for the player sprite
		currentSpites = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.playerBack0;  // Set an initial direction for the player sprite
		currentSpites = down;
		fireRate = BoltProjectile.getRateOfFire();
	}
	
	public void update() {
		if (walking) currentSpites.update();
		else currentSpites.setFrame(0);
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;  // Direction of movement
		
		if (animate < 7500) {
			animate++;
		} else {
			animate = 0;
		}
		
		if (input.up) {
			ya--;
			currentSpites = up;
		} else if (input.down) {
			ya++;
			currentSpites = down;
		}
		if (input.left) {
			xa--;
			currentSpites = left;
		} else if (input.right) {
			xa++;
			currentSpites = right;
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		
		//clear();
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
	
	// Move to updateShooting() method??
//	private void clear() {
//		for (int i = 0; i < projectiles.size(); i++) {
//			Projectile p = projectiles.get(i);
//			if (p.isRemoved()) {
//				if (level.getProjectiles().get(i) == projectiles.get(i)) {
//					projectiles.remove(i);
//					level.getProjectiles().remove(i);
//				}
//			}
//		}
//	}

	public void render(Screen screen) {
//		if (dir == 0) {
//			sprite = Sprite.playerForward6;
//			if (walking) {
//				if (animate % 20 > 10) {
//					sprite = Sprite.playerForward7;
//				} else {
//					sprite = Sprite.playerForward8;
//				}
//			}
//		}
//		if (dir == 1) {
//			sprite = Sprite.playerRight3;
//			if (walking) {
//				if (animate % 20 > 10) {
//					sprite = Sprite.playerRight4;
//				} else {
//					sprite = Sprite.playerRight5;
//				}
//			}
//		}
//		if (dir == 2) {
//			sprite = Sprite.playerBack0;
//			if (walking) {
//				if (animate % 20 > 10) {
//					sprite = Sprite.playerBack1;
//				} else {
//					sprite = Sprite.playerBack2;
//				}
//			}
//		}
//		if (dir == 3) {
//			sprite = Sprite.playerLeft9;
//			if (walking) {
//				if (animate % 20 > 10) {
//					sprite = Sprite.playerLeft10;
//				} else {
//					sprite = Sprite.playerLeft11;
//				}
//			}
//		}
		sprite = currentSpites.getSprite();
		screen.renderPlayer(x - 16, y - 16, sprite);
	}
}