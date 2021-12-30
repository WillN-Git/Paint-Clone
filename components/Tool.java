package components;

import constants.Actions;

public class Tool {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	private String imgPath;
	private float width, height;
	private String label;
	private Actions action;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public Tool(String label, String imgPath, float posX, float posY, float width, float height, Actions action) {
		this.label = label;
		this.imgPath = imgPath;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.action = action;
	}
	
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	
	public String getImgPath() {
		return this.imgPath;
	}
	
	public float getPosX() {
		return this.posX;
	}
	
	public float getPosY() {
		return this.posY;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Actions getAction() {
		return this.action;
	}
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public void setPosX(float posX) {
		this.posX = posX;
	}
	
	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public void setPosition(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setDimension(float width, float height) {
		this.width = width;
	}
	
}
