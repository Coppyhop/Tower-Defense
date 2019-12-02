package com.coppyhop.game.td.renderer;

import org.lwjgl.opengl.GL11;

import com.coppyhop.game.td.entity.Entity;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

/**
 * RenderEngine
 * 
 * This class handles the most basic form of rendering. Instead of every class
 * getting access to a full OpenGL context, they get access to this renderer 
 * instead, allowing it to manage colors, textures, and other things efficiently
 * This makes the code in other classes easier and makes updating how the engine
 * renders things simpler so that things like shaders can be added without 
 * breaking existing rendering code.
 * 
 * @author kyle
 *
 */
public class RenderEngine {
	
	private int width, height;
	private float UIScale;
	private long deltaTime;
	private long lastFrame;
	
	public RenderEngine(int width, int height, float UIScale){
		this.width = width;
		this.height = height;
		this.UIScale = UIScale;
		initOpenGL(width, height);
	}
	
	public RenderEngine(int width, int height){
		this.width = width;
		this.height = height;
		this.UIScale = 1.0f;
		initOpenGL(width, height);
	}

	private void initOpenGL(int width, int height){
		GL11.glViewport(0, 0, width, height);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-width/2, width/2, height/2, -height/2, 1, -1);
		GL11.glTranslatef(-width/2, -height/2, 0);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		lastFrame = System.nanoTime()/ 1000000;
	}

	public void prepareRender(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void endRender() {
		long time = System.nanoTime()/ 1000000;
		deltaTime = time - lastFrame;
		lastFrame = time;
	}
	public float getWidth(){
		return width/UIScale;
	}

	public float getHeight(){
		return height/UIScale;
	}
	
	public float getUIScale() {
		return UIScale;
	}
	
	public float getDeltaTime() {
		return deltaTime;
	}
	
	public void setTexture(Texture texture){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());
	}

	public void setTexture(int texture){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
	}

	public void setColor(float r, float g, float b, float a){
		GL11.glColor4f(r,g,b,a);
	}

	public void drawRectangle(float x, float y, float width, float height){
		GL11.glBegin(GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x*UIScale, y*UIScale);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f((x+width)*UIScale, y*UIScale);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f((x+width)*UIScale,(y+height)*UIScale);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x*UIScale, (y+height)*UIScale);
		GL11.glEnd();
	}

	public void drawRectangle(float x, float y, float width, float height, 
			float u1, float v1, float u2, float v2){
		GL11.glBegin(GL_QUADS);
		GL11.glTexCoord2f(u1, v1);
		GL11.glVertex2f(x*UIScale, y*UIScale);
		GL11.glTexCoord2f(u2, v1);
		GL11.glVertex2f((x+width)*UIScale, y*UIScale);
		GL11.glTexCoord2f(u2, v2);
		GL11.glVertex2f((x+width)*UIScale,(y+height)*UIScale);
		GL11.glTexCoord2f(u1, v2);
		GL11.glVertex2f(x*UIScale, (y+height)*UIScale);
		GL11.glEnd();
	}
	
	/**
	 * renderEntity
	 * 
	 * renders the given entity to the canvas. Using the values stored in it to
	 * determine its properties. Should not generally be called to render
	 * entities in a game. Instead batch rendering should be used.
	 * TODO: Implement Batch rendering (lol)
	 * 
	 * @param entity
	 */
	public void renderEntity(Entity entity) {
		setTexture(entity.getSprite());
		drawRectangle(entity.getX(), entity.getY(), entity.getWidth(), 
				entity.getHeight());
	}

}
