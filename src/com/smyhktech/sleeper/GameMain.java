package com.smyhktech.sleeper;

public class GameMain implements Runnable {
	
	public static int width = 300;			   // Width in pixels
	public static int height = width / 16 * 9;  // Aspect ratio
	public static int scale = 3;				   // Scalar for windowing
	
	private Thread gameThread;
	private boolean running = false;
	
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
		}
	}
}
