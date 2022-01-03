package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public class Graphic {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private String str = null;
	private int posX, posY;
	private Shape shape = null;
	private Color colorOfShape;
	private int weight;
	
	/*
	 * =============================
	 * 		   CONSTRUCTOR 
	 * =============================
	*/
	
	public Graphic(Shape shape, Color colorOfShape, int weight) {
		this.shape = shape;
		this.colorOfShape = colorOfShape;
		this.weight = weight;
	}
	
	public Graphic(String str, int posX, int posY, Color colorOfShape) {
		this.str = str;
		this.posX = posX;
		this.posY = posY;
		this.colorOfShape = colorOfShape;
	}
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	
	public Shape getShape() {
		return shape;
	}
	
	public Color getColorOfShape() {
		return colorOfShape;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String getStr() {
		return str;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void addColorOfShape(Color colorOfShape) {
		this.colorOfShape = colorOfShape;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setStr(String str) {
		this.str = str;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
}
