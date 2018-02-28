package com.smyhktech.sleeper.level;

import java.util.ArrayList;
import java.util.List;

import com.smyhktech.sleeper.entity.Entity;
import com.smyhktech.sleeper.entity.particle.Particle;
import com.smyhktech.sleeper.entity.projectile.Projectile;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt; // Store tile id's
	protected int[] tiles;  // Store pixel colors used to generate tiles
	
	private List<Entity> entities = new ArrayList<>();  // Store entities in the current level
	private List<Projectile> projectiles = new ArrayList<>();
	private List<Particle> particles = new ArrayList<>();
	
	public static Level spawn = new SpawnLevel("/levels/spawn.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		
	}

	protected void loadLevel(String path) {

	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		// Tests for any corner being a solid tile
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true; // Convert to tile precision (from pixel)
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		// Define corner pins (render region); convert to tile precision
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;  // TODO: future-proof the width and height 16's
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}
	
	public void addEntity(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add( (Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}
	
	public Tile getTile(int x, int y) {
		// Prevent crash when tiles index is < 0 or exceed map dimensions
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		if (tiles[x + y * width] == Tile.colorSpawnFloor) return Tile.spawnFloor;
		if (tiles[x + y * width] == Tile.colorSpawnGrass) return Tile.spawnGrass;
		if (tiles[x + y * width] == Tile.colorSpawnColoredBrick) return Tile.spawnColoredBrick;
		if (tiles[x + y * width] == Tile.colorSpawnGrayBrick) return Tile.spawnGrayBrick;
		if (tiles[x + y * width] == Tile.colorSpawnHedge) return Tile.spawnHedge;
		if (tiles[x + y * width] == Tile.colorSpawnMediumWater) return Tile.spawnMediumWater;
		return Tile.voidTile;
		
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
}

// x < 0 || y < 0 || x >= width || y >= height
