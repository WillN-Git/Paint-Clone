package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class DragController {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	private float defaultPosX, defaultPosY;
	
	private float oldPosX, oldPosY;
	
	private float currentPostX, currentPosY;
	
	private boolean isDragged = false;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public DragController(float defaultPosX, float defaultPosY) {
		this.defaultPosX = defaultPosX;
		this.defaultPosY = defaultPosY;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	
}
