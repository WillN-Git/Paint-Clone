package components;

public class MenuItem {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	private String text;
	private float width, height;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public MenuItem(String text, float posX, float posY, float width, float height) {
		this.text = text;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	
	public String getText() {
		return this.text;
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
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void setText(String text) {
		this.text = text;
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
