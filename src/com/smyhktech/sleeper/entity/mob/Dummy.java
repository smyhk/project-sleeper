package com.smyhktech.sleeper.entity.mob;

import com.smyhktech.sleeper.graphics.AnimatedSprite;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;
import com.smyhktech.sleeper.graphics.SpriteSheet;

public class Dummy extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummyDown, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummyUp, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummyLeft, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummyRight, 32, 32, 3);
	
	private AnimatedSprite currentSprites = up;
	
	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	@Override
	public void update() {
		int xa = 0;
		int ya = 0;
		 // ya--;
		if (walking) currentSprites.update();
		else currentSprites.setFrame(0);
		if (ya < 0) {
			currentSprites = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			currentSprites = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			currentSprites = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			currentSprites = right;
			dir = Direction.RIGHT;xa++;
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	@Override
	public void render(Screen screen) {
		sprite = currentSprites.getSprite();
		screen.renderMob(x, y, sprite);
	}
}