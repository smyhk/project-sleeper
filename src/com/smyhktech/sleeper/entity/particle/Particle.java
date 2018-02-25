package com.smyhktech.sleeper.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.graphics.Sprite;

public class Particle extends Entity {
	
	private List<Particle> particles = new ArrayList<>();
	private Sprite sprite;
	
	private int life;
	
	public Particle(int x, int y, int life) {
		this.x = y;
		this.y = y;
		this.life = life;
		sprite = Sprite.particleNormal;
		particles.add(this);
	}
	
	public Particle(int x, int y, int life, int amount) {
		this(x, y, life);
		for (int i = 0; i < amount - 1; i++) {
			particles.add(new Particle(x, y, life));
		}
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
}
