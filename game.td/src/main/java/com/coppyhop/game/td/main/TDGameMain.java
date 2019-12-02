package com.coppyhop.game.td.main;

import com.coppyhop.game.td.engine.Loader;
import com.coppyhop.game.td.entity.Entity;
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
		window = WindowManager.createWindow(
				640, 480, "TDGame - OpenGL 2.0", true);
		renderer = new RenderEngine(640,480);
		Texture test = Loader.loadTexture(
				"C:\\Users\\kjbre\\OneDrive\\Pictures\\goku.png");
		Entity anon = new Entity(0,0,48,48, 0, test);
		Entity bob = new Entity(0,0,48,48,0, test);
		bob.setSize(48, 96);
		while(!WindowManager.shouldWindowClose(window)) {
			renderer.prepareRender();
			anon.translate(0.1f*renderer.getDeltaTime(), 0);
			bob.scale(1.01f);
			renderer.renderEntity(bob);
			renderer.renderEntity(anon);
			renderer.endRender();
			WindowManager.update(window);
		}
		WindowManager.destroyWindow(window);
	}

}
