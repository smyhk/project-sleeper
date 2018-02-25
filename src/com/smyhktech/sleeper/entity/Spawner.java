package com.smyhktech.sleeper.entity;

import com.smyhktech.sleeper.entity.particle.Particle;
import com.smyhktech.sleeper.level.Level;

public class Spawner extends Entity {
	
	public enum Type {
		MOB, PARTICLE;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		this.x = x;
		this.y = y;
		this.type = type;
		for (int i = 0; i < amount; i++) {
			if (type == type.PARTICLE) {
				level.addEntity(new Particle(x, y, 50));
			}
		}
	}
}
