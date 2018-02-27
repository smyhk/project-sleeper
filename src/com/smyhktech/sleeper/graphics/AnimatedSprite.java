package com.smyhktech.sleeper.graphics;

public class AnimatedSprite extends Sprite {
	
	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int time = 0;
	private int frameLength = -1;  // Number if frames in an animation
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int frameLength) {
		super(sheet, width, height);
		this.frameLength = frameLength;
		sprite = sheet.getSprites()[0];
		if (frameLength > sheet.getSprites().length) System.err.println("Error! Length of animation is too long.");
	}
	
	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= frameLength - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];			
		}
		
		// remove me
		System.out.println(sprite + ", Frame: " + frame);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int frames) {
		this.rate = frames;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("Index out of bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
		
	}
}
