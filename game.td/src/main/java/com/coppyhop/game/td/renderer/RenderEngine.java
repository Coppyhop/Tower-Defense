package com.coppyhop.game.td.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.coppyhop.game.td.engine.Texture;
import com.coppyhop.game.td.entity.Entity;
import com.coppyhop.game.td.renderer.shaders.BaseShader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.BufferUtils;

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
	
	private static int WIDTH, HEIGHT;
	private static float UI_SCALE;
	private long deltaTime;
	private long lastFrame;
	private BaseShader shader;
	private int fboTexID;
	private float time = 0;
	private List<Entity> toRender;
	public static Texture blankTexture = new Texture(0);
	
	//Vertex and index data for a 2D rectangle (only shape needed for 2D games)
	private float[] vertices = {
		0f, 0f, 0f,
		0f, -1f, 0f,
		1f, -1f, 0f,
		1f, 0f, 0f,
	};
	private float[] textureCoords = {0,0,0,1,1,1,1,0};
	private int[] indicies = {0,1,3,3,1,2};
	
	//Buffer ids
	private int rectVBOId;
	private int iboID;
	private int fboID;
	
	public RenderEngine(int width, int height, float UIScale){
		RenderEngine.WIDTH = width;
		RenderEngine.HEIGHT = height;
		RenderEngine.UI_SCALE = UIScale;
		initOpenGL();
	}
	
	public RenderEngine(int width, int height){
		RenderEngine.WIDTH = width;
		RenderEngine.HEIGHT = height;
		RenderEngine.UI_SCALE = 1.0f;
		initOpenGL();
	}

	/**
	 * initOpenGL
	 * 
	 * initializes the canvas and prepares it for our game's rendering loop.
	 * also sets up the delta time so it isn't insane on the first frame.
	 */
	private void initOpenGL(){
		toRender = new LinkedList<Entity>();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		loadVertices();
		genFBO();
		lastFrame = System.nanoTime()/ 1000000;
		shader = new BaseShader();
	}
	
	/**
	 * loadVertices
	 * 
	 * Loads up the built-in vertex and index data to GL Buffers, allowing us to
	 * use shaders more effectively and draw things upon the screen better.
	 */
	public void loadVertices() {
		rectVBOId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, rectVBOId);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices);
		buffer.flip();
		FloatBuffer buffer3 = BufferUtils.createFloatBuffer(textureCoords.length);
		buffer3.put(textureCoords);
		buffer3.flip();
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, (long)vertices.length*27, GL15.GL_STATIC_DRAW);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, vertices.length*4, buffer3);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		iboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, iboID);
		IntBuffer buffer2 = BufferUtils.createIntBuffer(indicies.length);
		buffer2.put(indicies);
		buffer2.flip();
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer2, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void genFBO() {
		fboID = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fboID);
		fboTexID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fboTexID);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 
				WIDTH, HEIGHT, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, 0);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, 
				GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, 
				GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL30.glFramebufferTexture2D
		(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, 
				fboTexID, 0);
		GL30.glDrawBuffers(GL30.GL_COLOR_ATTACHMENT0);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}

	/**
	 * prepareRender
	 * 
	 * Prepares the given frame for rendering. Clears the canvas and binds the
	 * buffers needed to render our quad to the screen. As our only vertices
	 * are quads it is fine to just bind our buffers at the beginning.
	 */
	public void prepareRender(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, rectVBOId);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, iboID);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, vertices.length*4);
	}

	/**
	 * endRender
	 * 
	 * Finishes rendering the current frame. Disables and cleans up the remnants
	 * it also calculates the delta time for this frame to maintain constant
	 * speed despite fluctuating framerate.
	 */
	public void endRender() {
		GL20.glEnableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void drawRender() {
		time+=0.5f;
		long time = System.nanoTime()/ 1000000;
		deltaTime = time - lastFrame;
		lastFrame = time;
	}
	
	public static float getWidth(){
		return WIDTH/UI_SCALE;
	}

	public static float getHeight(){
		return HEIGHT/UI_SCALE;
	}
	
	public static float getUIScale() {
		return UI_SCALE;
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

	public void processEntity(Entity entity) {
		toRender.add(entity);
	}
	
	public void render() {
		prepareRender();
		for(Entity e: toRender) {
			renderEntity(e);
		}
		endRender();
		drawRender();
		toRender.clear();
	}
	//Might not even be needed anymore with shaders
	/*public void setColor(float r, float g, float b, float a){
		GL11.glColor4f(r,g,b,a);
	}*/

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
		shader.start();
		shader.color(entity.getColor());
		shader.position(entity.getPosition());
		shader.uploadTransformationMatrix(entity.getTranslation());
		setTexture(entity.getSprite());
		GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
		shader.stop();
	}

}
