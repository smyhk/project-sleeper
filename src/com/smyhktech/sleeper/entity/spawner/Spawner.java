package com.smyhktech.sleeper.entity.spawner;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.level.Level;

public class Spawner extends Entity {
	
	protected enum Type {
		MOB, PARTICLE;
	}
	
	//private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		this.x = x;
		this.y = y;
		//this.type = type;
	}
}
