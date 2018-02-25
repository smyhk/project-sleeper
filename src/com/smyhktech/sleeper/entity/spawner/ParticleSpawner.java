package com.smyhktech.sleeper.entity.spawner;

import com.smyhktech.sleeper.entity.particle.Particle;
import com.smyhktech.sleeper.level.Level;

public class ParticleSpawner extends Spawner {

	//private int life;

	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		//life = life;
		for (int i = 0; i < amount; i++) {
			level.addEntity(new Particle(x, y, life));
		}
	}

}
