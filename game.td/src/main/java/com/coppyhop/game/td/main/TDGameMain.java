package com.coppyhop.game.td.main;

import com.coppyhop.game.td.renderer.WindowManager;

/**
 * TDGameMain
 * 
 * The main class for the Tower Defense game. This class will contain the bare
 * minimum for setting up the game state. Hopefully I will be able to offload 
 * most of the functionality to other objects to keep most methods and classes
 * clean and short.
 * 
 * @author kyle
 *
 */
public class TDGameMain {

	private static long window;
	public static void main(String[] args) {
		WindowManager.init();
		window = WindowManager.createWindow(640, 480, "TDGame", true);
		while(!WindowManager.shouldWindowClose(window)) {
			loop();
			WindowManager.update(window);
		}
		WindowManager.destroyWindow(window);
	}
	
	private static void loop() {
		
	}

}
