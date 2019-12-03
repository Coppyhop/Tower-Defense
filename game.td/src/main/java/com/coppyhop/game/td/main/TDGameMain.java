package com.coppyhop.game.td.main;

import org.joml.Vector3f;

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
		Entity anon = new Entity(-48,0,48,48, 0, test);
		Entity bob = new Entity(0,0,48,48,0, test);
		Entity fred = new Entity(0,0,640,480,0,test);
		fred.setColor(new Vector3f(0.25f,0.25f, 0.65f));
		bob.setColor(new Vector3f(1f, 0.3f, 0.3f));
		bob.setSize(48, 96);
		while(!WindowManager.shouldWindowClose(window)) {
			anon.translate(1f, 0);
			anon.scale(1.005f);
			if(anon.getRelativeX() > 640) {
				anon.setPosition(-48, 0);
				anon.setSize(48, 48);
			}
			bob.setSize(640, 96+renderer.getDeltaTime()*4);
			bob.setPosition(0, 480-(96+renderer.getDeltaTime()*4));
			renderer.prepareRender();
			renderer.renderEntity(fred);
			renderer.renderEntity(anon);
			renderer.renderEntity(bob);
			renderer.endRender();
			renderer.drawRender();
			WindowManager.update(window);
		}
		WindowManager.destroyWindow(window);
	}

}
