package com.coppyhop.game.td.engine;

import java.util.ArrayList;
import java.util.List;

import com.coppyhop.game.td.engine.event.MouseEventListener;

public class MouseInputHandler {
	private static float mouseX;
	private static float mouseY;
	private static List<MouseEventListener> eventListeners;
	
	public static void init() {
		eventListeners = new ArrayList<MouseEventListener>();
	}
	
	public static float getMouseX() {
		return mouseX;
	}
	
	public static float getMouseY() {
		return mouseY;
	}
	
	public static void poll(float mx, float my) {
		mouseX = mx;
		mouseY = my;
		for(MouseEventListener e:eventListeners) {
			e.poll(mx, my);
		}
	}
	
	public static void leftClick() {
		
	}
	
	public static void rightClick() {
		
	}
}
