package com.smyhktech.sleeper;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.smyhktech.sleeper.entity.mob.Player;
import com.smyhktech.sleeper.graphics.Screen;
import com.smyhktech.sleeper.input.Keyboard;
import com.smyhktech.sleeper.level.Level;

public class GameMain extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static int width = 300;			   // Width in pixels
	public static int height = width / 16 * 9;  // Aspect ratio
	public static int scale = 3;				   // Scalar for windowing
	public static String title = "Sleeper";
	
	private Thread gameThread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Placeholder for drawing images
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();		  // Draws on the placeholder
	
	// Constructor
	public GameMain() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		player = new Player(23 * 16, 59 * 16, key);
		
		addKeyListener(key);  // Binds keyboard class
	}
	
	public synchronized void startGame() {
		running = true;
		gameThread = new Thread(this, "Game Display");
		gameThread.start();
	}
	
	public synchronized void stopGame() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		
		// Values used for timing
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;  // Store change in time between lastTime and now
		
		int frames = 0;		// fps accumulator
		int updates = 0;		// update count accumulator; should be 60 per minute
		
		requestFocus();  // Give focus to game canvas on load
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + " | [fps: " + frames + " ], [ups: " + updates + " ]");
				
				// Reset the counters
				updates = 0;
				frames = 0;
			}
		}
		stopGame();
	}
	
	private void update() {
		key.update();
		player.update();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();  // Clears the screen before rendering the next image
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		// Copies pixel array from Screen class to this
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();  // Creates a graphics context for buffer
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		// player movement debugging stuff
//		g.setColor(Color.CYAN);
//		g.setFont(new Font("Veranda", 0, 50));
//		g.drawString("X: " + player.x + ", y: " + player.y, 350, 300);
		// ================================
		
		g.dispose();
		bs.show();  // Displays graphics in the buffer to the screen
	}

	public static void main(String[] args) {
		GameMain game = new GameMain();
		
		// Define game window frame attributes
		game.frame.setResizable(false);
		game.frame.setTitle(GameMain.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		// Start game thread
		game.startGame();
	}
}
