package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class Picture extends Graphic {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private Image img;
	private int width, height;
	
	/*
	 * =============================
	 * 			CONSTRUCTOR 
	 * =============================
	*/
	
	public Picture(Image img, float posX, float posY, int width, int height, Color colorOfShape) {
		super(posX, posY, colorOfShape);
		
		this.width = width;
		this.height = height;
	}
	
	/*
	 * =============================
	 * 			 GETTERS 
	 * =============================
	*/
	
	public Image getImg() {
		return img;
	}
	
	public int getWidth() {
		return width; 
	}
	
	public int getHeight() {
		return height;
	}
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void setImg(Image img) {
		this.img = img;	
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

}
