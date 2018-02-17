package com.smyhktech.sleeper;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameMain extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static int width = 300;			   // Width in pixels
	public static int height = width / 16 * 9;  // Aspect ratio
	public static int scale = 3;				   // Scalar for windowing
	
	private Thread gameThread;
	private JFrame frame;
	private boolean running = false;
	
	// Constructor
	public GameMain() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		frame = new JFrame();
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
		while (running) {
			update();
			render();
		}
	}

	private void update() {
		// Coming soon!
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();  // Creates a graphics context for buffer
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.dispose();
		bs.show();  // Displays graphics in the buffer to the screen
	}

	public static void main(String[] args) {
		GameMain game = new GameMain();
		
		// Define game window frame attributes
		game.frame.setResizable(false);
		game.frame.setTitle("Sleeper");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		// Start game thread
		game.startGame();
	}
}
