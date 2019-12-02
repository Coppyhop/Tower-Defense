package com.coppyhop.game.td.renderer.shaders;

public class TestShader extends ShaderProgram{

	private static final String VERTEX_FILE = 
			"src/main/java/com/coppyhop/game/td/renderer/shaders/base.vert";
	private static final String FRAGMENT_FILE = 
			"src/main/java/com/coppyhop/game/td/renderer/shaders/base.frag";
	
	public TestShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

}
