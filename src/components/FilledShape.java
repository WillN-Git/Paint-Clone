package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class FilledShape extends Graphic {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	//Drawing
		private Shape shape = null;
		
	public FilledShape(Shape shape, Color colorOfShape) {
		super(shape.getX(), shape.getY(), colorOfShape);
		
		this.shape = shape;	
	}
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	
	public Shape getShape() {
		return shape;
	}
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	@Override
	public boolean equals(Object o) {
		if( o == this )
			return true;
		
		if( !(o instanceof FilledShape) )
			return false;
		
		FilledShape fs = (FilledShape) o;
		
		return (
				( posX == fs.posX && posY == fs.posY  ) &&
				( shape.getWidth() == fs.shape.getWidth() ) &&
				( shape.getHeight() == fs.shape.getHeight() ) &&
				colorEquals( this.colorOfShape, fs.colorOfShape )
		);
	}
	
}
