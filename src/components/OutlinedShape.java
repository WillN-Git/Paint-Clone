package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public class OutlinedShape extends Graphic {
	//Drawing
		private Shape shape = null;
		private int weight;
			
	public OutlinedShape(Shape shape, Color colorOfShape, int weight) {
		super(shape.getX(), shape.getY(), colorOfShape);
		
		this.shape = shape;
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
	
	public void setWeight(int weight) {
		this.weight = weight;
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
		
		if( !(o instanceof OutlinedShape) )
			return false;
		
		OutlinedShape os = (OutlinedShape) o;
		
		return (
				( posX == os.posX && posY == os.posY  ) &&
				( shape.getMaxX() == os.shape.getMaxX() ) &&
				( shape.getMaxY() == os.shape.getMaxY() ) &&
				( shape.getMinX() == os.shape.getMinX() ) &&
				( shape.getMinX() == os.shape.getMinX() ) &&
				( shape.getPointCount() == os.shape.getPointCount() ) &&
				( this.weight == os.weight ) &&
				colorEquals( this.colorOfShape, os.colorOfShape )
		);
	}
}
