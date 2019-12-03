package com.coppyhop.game.td.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.coppyhop.game.td.engine.Texture;
import com.coppyhop.game.td.renderer.RenderEngine;
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
	private Texture sprite;
	private Matrix4f translation;
	private Vector3f position;
	private Vector3f color;
	private static float widthFactor = RenderEngine.getWidth()/2;
	private static float heightFactor = RenderEngine.getHeight()/2;
	
	public Entity(float x, float y, float width, float height, float rotation,
			Texture sprite) {
		widthFactor = RenderEngine.getWidth()/2;
		heightFactor = RenderEngine.getHeight()/2;
		this.sprite = sprite;
		translation = new Matrix4f();
		translation.identity();
		translation.scale(width/widthFactor, height/heightFactor, 1);
		position = new Vector3f(x/widthFactor,y/heightFactor,0);
		color = new Vector3f(1,1,1);
		
	}
	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public void setSize(float width, float height) {
		translation = new Matrix4f();
		translation.identity();
		translation.scale(width/widthFactor, height/heightFactor, 1);
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public float getRelativeX() {
		return position.x * widthFactor;
	}
	
	public float getRelativeY() {
		return position.y * widthFactor;
	}

	public void setPosition(Vector3f position) {
		this.position = new Vector3f(position.x/widthFactor,position.y/heightFactor,0);
	}
	
	public void setPosition(float x, float y) {
		this.position = new Vector3f(x/widthFactor,y/heightFactor,0);
	}
	
	public void translate(float x, float y) {
		position.x+= x/widthFactor;
		position.y+=y/heightFactor;
	}

	public void scale(float factor) {
		translation.scale(factor);
	}

	public Matrix4f getTranslation() {
		return translation;
	}

	public Texture getSprite() {
		return sprite;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}
	
	
}
