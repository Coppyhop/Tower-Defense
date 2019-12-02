package com.coppyhop.game.td.main;

import com.coppyhop.game.td.engine.Loader;
import com.coppyhop.game.td.entity.Entity;
import com.coppyhop.game.td.renderer.RenderEngine;
import com.coppyhop.game.td.renderer.Texture;
import com.coppyhop.game.td.renderer.WindowManager;
import com.coppyhop.game.td.renderer.shaders.TestShader;

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
		TestShader shader = new TestShader();
		Texture test = Loader.loadTexture(
				"C:\\Users\\kjbre\\OneDrive\\Pictures\\goku.png");
		Entity anon = new Entity(0,0,8,8, 0, test);
		anon.setShader(shader);
		while(!WindowManager.shouldWindowClose(window)) {
			renderer.prepareRender();
			anon.setWidth(anon.getWidth()+0.1f*renderer.getDeltaTime());
			anon.setHeight(anon.getHeight() + 0.1f*renderer.getDeltaTime());
			loop();
			renderer.renderEntity(anon);
			renderer.endRender();
			WindowManager.update(window);
		}
		shader.cleanUp();
		WindowManager.destroyWindow(window);
	}
	
	private static void loop() {

	}

}
