package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Curve;
import org.newdawn.slick.geom.Vector2f;

import constants.Shapes;

public class DrawingManager {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private Graphics gr;
	private Shapes s;
	
	/*
	 * =============================
	 * 			CONSTRUCTOR 
	 * =============================
	*/
	public DrawingManager(Graphics gr) {
		this.gr = gr;
	}
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void drawing(Vector2f start, Vector2f end, Vector2f c1, Vector2f c2) {
		switch(s) {
			case LINE: 
				gr.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
				break;//END OF LINE CASE
				
			case CURVE:
				gr.draw(new Curve(start, c1, c2, end));
				break;//END OF CURVE CASE
				
			case CIRCLE: 
				gr.drawOval(0, 0, 0, 0);
				break;//END OF CIRCLE CASE
			
			case RECTANGLE:
				gr.drawOval(start.getX(), start.getY(), 5, 5);
				break;//END OF RECTANGLE CASE
			
			case ROUND_RECTANGLE: 
				
				break;//END OF ROUND RECTANGLE CASE
				
			case POLYGON: 
				
				break;//END OF POLYGON CASE
				
			case TRIANGLE: 
				
				break;//END OF TRIANGLE CASE
				
			case RIGHT_TRIANGLE: 
				
				break;//END OF RIGHT TRIANGLE CASE
				
			case DIAMOND: 
				
				break;//END OF DIAMOND CASE
				
			case PENTAGON: 
				
				break;//END OF PENTAGON CASE
				
			case HEXAGON: 
				
				break;//END OF HEXAGON CASE
				
			case RIGTH_ARROW: 
				
				break;//END OF RIGHT ARROW CASE
				
			case LEFT_ARROW: 
				
				break;//END OF LEFT ARROW CASE
				
			case UP_ARROW: 
				
				break;//END OF UP ARROW CASE
				
			case DOWN_ARROW: 
				
				break;//END OF DOWN ARROW CASE
				
			case FOUR_POINT_STAR: 
				
				break;//END OF FOUR POINT STAR CASE
				
			case FIVE_POINT_STAR: 
				
				break;//END OF FIVE POINT STAR CASE
				
			case SIX_POINT_STAR: 
				
				break;//END OF SIX POINT STAR CASE
			
			case ROUNDED_RECTANGULAR_CALLOUT: 
				
				break;//END OF ROUNDED RECTANGULAR CALLOUT CASE
			
			case OVAL_CALLOUT: 
				
				break;//END OF OVAL CALLOUT CASE
			
			case CLOUD_CALLOUT: 
				
				break;//END OF CLOUD CALLOUT CASE
			
			case HEART: 
				
				break;//END OF HEART CASE
			
			case LIGHTNING: 
				
				break;//END OF LIGHTNING CASE
		}
	}
}
