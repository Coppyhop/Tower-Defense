package com.coppyhop.game.td.renderer.shaders;

public class BasePostProcesingShader extends ShaderProgram {
	private static final String VERTEX_FILE = 
			"src/main/java/com/coppyhop/game/td/renderer/shaders/basepp.vert";
	private static final String FRAGMENT_FILE = 
			"src/main/java/com/coppyhop/game/td/renderer/shaders/basepp.frag";
	
	public BasePostProcesingShader() {
		super(VERTEX_FILE,FRAGMENT_FILE);
	}
	
	private int timeLocation;

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		timeLocation = getUniformLocation("time");
	}

	public void loadTime(float time) {
		super.loadFloat(timeLocation, time);
	}
}
