package com.coppyhop.game.td.renderer;

import java.util.ArrayList;

/**
 * LEGACY CODE - CURRENTLY INACCESSIBLE BY ANY NORMAL MEANS
 * I need to refactor all of text stuff using the new rendering system, so
 * I disabled it. 
 * 
 * BitmapFont
 * 
 * Represents a font that can be used by the rendering engine to draw text on 
 * the canvas. Contains a reference to the texture, its dimensions, and the size
 * of the text. It also contains a list of valid letters (called glyphs) with
 * their positions and dimensions on the canvas, as well as a few other 
 * properties to make the text render more naturally on the screen. 
 * 
 * @author kyle
 *
 */
public class BitmapFont {

	private final float textureWidth;
    private final float textureHeight;
    private final float glyphSize;
	private final Texture fontTexture;
	//TODO: Make glyphs a map instead of a list, a list makes no sense here
	// but as this is legacy code from my old minesweeper project, it's passable
	// for now
	private final ArrayList<BitmapGlyph> glyphs;
	
	public BitmapFont(float textureWidth, float textureHeight, float glyphSize, 
			Texture fontTexture, ArrayList<BitmapGlyph> glyphs) {
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		this.fontTexture = fontTexture;
		this.glyphSize = glyphSize;
		this.glyphs = glyphs;
	}

	public float getTextureWidth() {
		return textureWidth;
	}

	public float getTextureHeight() {
		return textureHeight;
	}

	public Texture getFontTexture() {
		return fontTexture;
	}

	public ArrayList<BitmapGlyph> getGlyphs() {
		return glyphs;
	}
	
	public float getGlyphSize(){
		return glyphSize;
	}
	
}
