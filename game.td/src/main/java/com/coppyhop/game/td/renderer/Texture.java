package com.coppyhop.game.td.renderer;

/**
 * Texture
 * 
 * Represents a texture that can be used by the engine to render colurful
 * sprites and stuff. Contains only the ID Number of the texture in OpenGL's 
 * buffer. This class is immutable.
 * @author kyle
 *
 */
public class Texture {

	private final int texID;
	
	public Texture(int texID){
		this.texID = texID;
	}
	
	public int getID(){
		return texID;
	}

}
