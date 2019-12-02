package com.coppyhop.game.td.renderer.shaders;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class BaseShader extends ShaderProgram{

	private static final String VERTEX_FILE = 
			"src/main/java/com/coppyhop/game/td/renderer/shaders/base.vert";
	private static final String FRAGMENT_FILE = 
			"src/main/java/com/coppyhop/game/td/renderer/shaders/base.frag";
	
	private int positionLocation;
	private int colorLocation;
	private int coordLocation;
	
	public BaseShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		positionLocation = getUniformLocation("entityPos");
		colorLocation = getUniformLocation("coloration");
		coordLocation = getUniformLocation("entityXYZ");
	}
	
	public void uploadTransformationMatrix(Matrix4f transformation) {
		super.loadMatrix4f(positionLocation, transformation);
	}
	
	public void color(float r, float g, float b) {
		Vector3f color = new Vector3f(r,g,b);
		super.loadVector3f(colorLocation, color);
	}

	public void position(Vector3f pos) {
		super.loadVector3f(coordLocation, pos);
	}
}
