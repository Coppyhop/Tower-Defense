package com.coppyhop.game.td.renderer;

/**
 * LEGACY CODE - CURRENTLY INACCESSIBLE BY ANY NORMAL MEANS
 * I need to refactor all of text stuff using the new rendering system, so
 * I disabled it. 
 * 
 * BitmapGlyph
 * 
 * Represents a single glyph (character) for a given font. Contains where the 
 * glyph exists on the texture, its size, and offset values to make it line up
 * naturally with other glyphs in the same font when strung together. It has
 * an advance as well to move the "cursor" when rendering the text so that it 
 * knows where to start drawing the next letter.
 * 
 * @author kyle
 *
 */
public class BitmapGlyph {
	private final int charId;
	private final char charRep;
	private final float x;
    private final float y;
	private final float width;
    private final float height;
	private final int xoffset;
    private final int yoffset;
	private final int xadvance;

	public BitmapGlyph(int charId, int x, int y, int width, int height, 
			int xoffset, int yoffset, int xadvance) {
		this.charId = charId;
		this.charRep =(char) charId;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.xadvance = xadvance;
	}

	public int getCharId() {
		return charId;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public int getXoffset() {
		return xoffset;
	}

	public int getYoffset() {
		return yoffset;
	}

	public char getCharRep() {
		return charRep;
	}

	public int getXadvance() {
		return xadvance;
	}

	public String toString(){
		return charRep + ": " +x+", " + y + " W: " + width + " H:" + height;
	}

}
