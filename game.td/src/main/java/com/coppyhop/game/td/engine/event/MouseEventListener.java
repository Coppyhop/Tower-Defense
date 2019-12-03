package com.coppyhop.game.td.engine.event;

public abstract class MouseEventListener {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public abstract void hoverEvent();
	public abstract void clickEvent();
	public abstract void rightClickEvent();
	
	public void poll(float mx, float my) {
		
	}
}
