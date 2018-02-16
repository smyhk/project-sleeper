package com.smyhktech.sleeper;

public class GameMain implements Runnable {
	
	public static int width = 300;			   // Width in pixels
	public static int height = width / 16 * 9;  // Aspect ratio
	public static int scale = 3;				   // Scalar for windowing
	
	private Thread gameThread;
	
	public synchronized void startGame() {
		gameThread = new Thread(this, "Game");
		gameThread.start();
	}
	
	public synchronized void stopGame() {
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
