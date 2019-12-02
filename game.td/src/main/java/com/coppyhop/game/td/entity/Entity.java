package com.coppyhop.game.td.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

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
	private Texture sprite;
	private Matrix4f translation;
	private Vector3f position;
	
	public Entity(float x, float y, float width, float height, float rotation,
			Texture sprite) {
		this.sprite = sprite;
		translation = new Matrix4f();
		translation.identity();
		translation.scale(width/320, height/240, 1);
		position = new Vector3f(x/320,y/240,0);
		
	}
	
	public void setSize(float width, float height) {
		translation = new Matrix4f();
		translation.identity();
		translation.scale(width/320, height/240, 1);
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = new Vector3f(position.x/320,position.y/240,0);
	}
	
	public void setPosition(float x, float y) {
		this.position = new Vector3f(x/320,y/240,0);
	}
	
	public void translate(float x, float y) {
		position.x+= x/320;
		position.y+=y/240;
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
