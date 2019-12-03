package com.coppyhop.game.td.main;

import org.joml.Vector3f;

import com.coppyhop.game.td.engine.Loader;
import com.coppyhop.game.td.engine.MouseInputHandler;
import com.coppyhop.game.td.engine.Texture;
import com.coppyhop.game.td.engine.WindowManager;
import com.coppyhop.game.td.entity.Entity;
import com.coppyhop.game.td.renderer.RenderEngine;

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
				1600, 900, "TDGame - OpenGL 3.0", true);
		renderer = new RenderEngine(1600,900);
		Texture test = Loader.loadTexture(
				"src/main/resources/puar.png");
		Entity anon = new Entity(-48,0,48,48, 0, test);
		Entity bob = new Entity(0,0,48,48,0, test);
		Entity fred = new Entity(0,0,1024,768,0,test);
		fred.setColor(new Vector3f(0.25f,0.25f, 0.65f));
		bob.setColor(new Vector3f(1f, 0.3f, 0.3f));
		bob.setSize(48, 96);
		while(!WindowManager.shouldWindowClose(window)) {
			anon.setPosition(MouseInputHandler.getMouseX()-24, 
					MouseInputHandler.getMouseY()-24);
			bob.setSize(1024, 96+renderer.getDeltaTime()*4);
			bob.setPosition(0, 768-(96+renderer.getDeltaTime()*4));
			renderer.processEntity(fred);
			renderer.processEntity(bob);
			renderer.processEntity(anon);
			renderer.render();
			WindowManager.update(window);
		}
		WindowManager.destroyWindow(window);
	}

}
