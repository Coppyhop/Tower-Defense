package com.coppyhop.game.td.entity;

import com.coppyhop.game.td.renderer.Texture;

/**
 * Entity
 * 
 * This is the base representation of an entity. An entity is something that can
 * be moved, rotated, and modified around the screen. Entities are how the
 * engine handles objects. A bullet or an enemy would be counted as an entity
 * and would extend this class to be rendered and handled as such. 
 * 
 * @author kyle
 *
 */
public class Entity {
	private float x;
	private float y;
	private float width;
	private float height;
	private float rotation;
	private Texture sprite;
	
	public Entity(float x, float y, float width, float height, float rotation,
			Texture sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.sprite = sprite;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Texture getSprite() {
		return sprite;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}
	
	
}
