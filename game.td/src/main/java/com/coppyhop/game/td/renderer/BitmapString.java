package com.coppyhop.game.td.renderer;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

/**
 * BitmapString
 * 
 * This represents a string of characters that will be rendered to the user's 
 * screen within the bounds of the window. The text displayed within this class
 * cannot be changed, so like a real string this BitampString is immuatable, it
 * can only be rendered to the screen or discarded.
 * 
 * @author kyle
 *
 */
public class BitmapString {
	
	//TODO: This really should be an array, given that it is a fixed size and
	// will never actually change in its lifespan.
	private final ArrayList<BitmapGlyph> string = new ArrayList<>();
	private final BitmapFont font;

	public BitmapString(String string, BitmapFont font){
		char[] chars = string.toCharArray();
		this.font = font;
		for(char c: chars){
			for(BitmapGlyph g: font.getGlyphs() ){
				if(g.getCharRep() == c){
					this.string.add(g);
				}
			}
			
		}
	}
	
	public ArrayList<BitmapGlyph> getString(){
		return string;
	}
	
	//TODO: Re-Do this method with the new rendering engine shader shit
	/*public void render(float x, float y, RenderEngine renderer){
		int curx = 0;
		GL11.glPushMatrix();
		GL11.glTranslatef(x*renderer.getUIScale(), y*renderer.getUIScale(), 0);
		renderer.setTexture(font.getFontTexture());
		for(BitmapGlyph g: string){
			curx += g.getXoffset();
			float u = g.getX() / font.getTextureWidth();
			float v = g.getY() / font.getTextureHeight();
			float u2 = (g.getX() + g.getWidth()) / font.getTextureWidth();
			float v2 = (g.getY() + g.getHeight()) / font.getTextureHeight();
		    renderer.drawRectangle(curx, g.getYoffset(), g.getWidth(), 
		    		g.getHeight(), u, v, u2, v2);
		    curx+= g.getXadvance();
		}
		GL11.glPopMatrix();
	}*/

	public float getWidth(){
		int x = 0;
		for(BitmapGlyph s: string){
			x+=s.getXoffset();
			x+=s.getXadvance();
		}
		return x;
	}


	public float getHeight(){
		return font.getGlyphSize();
	}

}
