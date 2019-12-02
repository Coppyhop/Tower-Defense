package com.coppyhop.game.td.main;

import com.coppyhop.game.td.entity.Entity;
import com.coppyhop.game.td.renderer.Loader;
import com.coppyhop.game.td.renderer.RenderEngine;
import com.coppyhop.game.td.renderer.Texture;
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
	private static RenderEngine renderer;
	public static void main(String[] args) {
		WindowManager.init();
		window = WindowManager.createWindow(640, 480, "TDGame", true);
		renderer = new RenderEngine(640,480);
		Texture test = Loader.loadTexture("/home/kyle/Pictures/mister.anon.png");
		Entity anon = new Entity(0,0,8,8, 0, test);
		while(!WindowManager.shouldWindowClose(window)) {
			renderer.prepareRender();
			anon.setWidth(anon.getWidth()+0.1f*renderer.getDeltaTime());
			anon.setHeight(anon.getHeight() + 0.1f*renderer.getDeltaTime());
			loop();
			renderer.renderEntity(anon);
			renderer.endRender();
			WindowManager.update(window);
		}
		WindowManager.destroyWindow(window);
	}
	
	private static void loop() {

	}

}
