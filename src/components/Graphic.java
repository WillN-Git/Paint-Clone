package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class Graphic {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	protected Color colorOfShape;
	protected float posX, posY;
	
	//Image
		private Image img;
	
	/*
	 * =============================
	 * 		   CONSTRUCTOR 
	 * =============================
	*/
	
	public Graphic(float posX, float posY, Color colorOfShape) {
		this.posX = posX;
		this.posY = posY;
		this.colorOfShape = colorOfShape;
	}
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	
	public Color getColorOfShape() {
		return colorOfShape;
	}
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void addColorOfShape(Color colorOfShape) {
		this.colorOfShape = colorOfShape;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	protected boolean colorEquals(Color c1, Color c2) {
		return (
				c1.getRed() == c2.getRed() &&
				c1.getGreen() == c2.getGreen() &&
				c1.getBlue() == c2.getBlue()
		);
	}
}
