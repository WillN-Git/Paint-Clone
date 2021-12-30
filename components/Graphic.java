package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public class Graphic {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private Shape shape;
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
}
