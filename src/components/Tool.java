package components;

public class Tool {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	private String imgPath;
	private float width, height;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public Tool(String imgPath, float posX, float posY, float width, float height) {
		this.imgPath = imgPath;
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
