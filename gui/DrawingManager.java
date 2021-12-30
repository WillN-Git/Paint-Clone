package gui;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import components.Graphic;
import components.Store;
import constants.Actions;
import constants.Icons;

public class DrawingManager {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	
	private static Graphics gr;
	private static int mouseX, mouseY,
					   mouseXClick, mouseYClick;
	private static Actions currentAction;
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	//Shapes
		private static Path path;
		private static Rectangle rect;
		private static RoundedRectangle roundedRect;
		private static Ellipse ellipse;
		private static Circle circle;
		private static Line line;
		private static Polygon polygon;
		private static Curve curve;
		
	//Shapes characteristics
		private static float width, height, diagonal, segments;
		
	//To retrieve the set of points into the store
		private static ArrayList<Vector2f> v;
	
	public void displayMyDrawing() throws SlickException {
		//Data retrieval
			gr = Store.getGr();
			mouseX = Store.getMouseX();
			mouseY = Store.getMouseY();
			mouseXClick = Store.getMouseXClick();
			mouseYClick = Store.getMouseYClick();
				
		
		/**
		 * 
		 * ================ A drawing is formed in 3 steps ================
		 * First we change the cursor
		 * Then we draw (if the cursor is placed on the paper)
		 * Finally we add the drawing to the blind for storage
		 * 
		 */
		
		currentAction = Store.getCurrentAction();
		
		switch( currentAction ) {
			
		//======================= HANDRAWERS
			case DRAW_WITH_PENCIL: case DRAW_WITH_BRUSH: case ERASE: 
				
				if(currentAction == Actions.DRAW_WITH_PENCIL) {
					Store.setCursorImage(Icons.PENCIL.getIcon());
				} else if(currentAction == Actions.DRAW_WITH_BRUSH) {
					Store.setCursorImage(Icons.BRUSH.getIcon());
					gr.setLineWidth(Store.getShapeWeight());
				} else {
					Store.setCursorImage(Icons.ERASER.getIcon());
					gr.setLineWidth(Store.getShapeWeight());
				}
				
				v = Store.getSetOfPoints();
				
				path = null;
				
				if(v.size() >= 4) {
					path = new Path(v.get(0).getX(), v.get(0).getY());
					
					gr.setColor(Store.getPrimaryColor());
					if(Store.getCurrentAction() == Actions.ERASE)
						gr.setColor(Store.getSecondColor());
					
					for(int i=1; i< v.size() - 2; i++) 
						path.curveTo(
							v.get(i).getX(), v.get(i).getY(),
							v.get(i+1).getX(), v.get(i+1).getY(),
							v.get(i+2).getX(), v.get(i+2).getY()
						);
					
					gr.draw(path);
				}
				
				if(currentAction != Actions.DRAW_WITH_PENCIL)
					gr.setLineWidth(1);//Reset line width for the whole app	
				
				if(Store.getDrawFinished()) {
					if(currentAction == Actions.ERASE)
						Store.addGraphic(new Graphic(path, Store.getSecondColor(), Store.getShapeWeight()));
					else if(currentAction == Actions.DRAW_WITH_BRUSH)
						Store.addGraphic(new Graphic(path, Store.getPrimaryColor(), Store.getShapeWeight()));
					else
						Store.addGraphic(new Graphic(path, Store.getPrimaryColor(), 1));
					
					Store.removeAllPoints();//To clear the set of points in the store
					Store.setDrawFinished(false);
				}
				break;
				
		//======================= BASIC SHAPES
			case DRAW_RECTANGLE : case DRAW_ROUNDED_RECTANGLE : 
			case DRAW_ELLIPSE : case DRAW_LINE : case DRAW_TRIANGLE :
			case DRAW_PENTAGON : case DRAW_HEXAGON :
				v = Store.getSetOfPoints();
				
				Store.setCursorImage(null);
				
				rect = null;
				roundedRect = null;
				ellipse = null;
				line = null;
				circle = null;
				
				gr.setLineWidth(1);
				
				if(v.size() >= 2) {
					width = -( v.get(0).getX() - v.get(v.size() - 1).getX() );
					height = ( v.get(v.size() - 1).getY() - v.get(0).getY() );
					diagonal = (float)Math.sqrt( Math.pow(width, 2) + Math.pow(height, 2) );
					segments = ( Store.getCurrentAction() == Actions.DRAW_TRIANGLE ) 
							? 3 : ( Store.getCurrentAction() == Actions.DRAW_PENTAGON ) 
							? 5 : 6;
					
					line = new Line(v.get(0).getX(),v.get(0).getY(), v.get(v.size() - 1).getX(), v.get(v.size() - 1).getY());
					roundedRect = new RoundedRectangle(v.get(0).getX(), v.get(0).getY(), width, height, 5);
					circle = new Circle(v.get(0).getX(), v.get(0).getY(), diagonal, (int)segments);
					
					rect = (Store.getIfCtrlButtonIsPressed()) 
						 ? new Rectangle(v.get(0).getX(),v.get(0).getY(), diagonal, diagonal)
						 : new Rectangle(v.get(0).getX(),v.get(0).getY(), width, height);
					
					
					ellipse = (Store.getIfCtrlButtonIsPressed()) 
							? new Ellipse(v.get(0).getX(), v.get(0).getY(), diagonal, diagonal)
							: new Ellipse(v.get(0).getX(), v.get(0).getY(), width, height);
					
					gr.setLineWidth(Store.getShapeWeight());
					
					if(currentAction == Actions.DRAW_LINE) {
						gr.draw(line);
					} else if(currentAction == Actions.DRAW_RECTANGLE) {
						gr.draw(rect);
					} else if(currentAction == Actions.DRAW_ELLIPSE) {
						gr.draw(ellipse);
					} else if(currentAction == Actions.DRAW_ROUNDED_RECTANGLE) {
						gr.draw(roundedRect);
					} else {
						gr.setLineWidth(2);
						gr.draw(circle);
					}
					
				}
				
				if( Store.getDrawFinished() ) {
					if(currentAction == Actions.DRAW_LINE)
						Store.addGraphic(new Graphic(line, Store.getPrimaryColor(), Store.getShapeWeight()));
					else if(currentAction == Actions.DRAW_RECTANGLE)
						Store.addGraphic(new Graphic(rect, Store.getPrimaryColor(), Store.getShapeWeight()));
					else if(currentAction == Actions.DRAW_ROUNDED_RECTANGLE)
						Store.addGraphic(new Graphic(roundedRect, Store.getPrimaryColor(), Store.getShapeWeight()));
					else if(currentAction == Actions.DRAW_ELLIPSE)
						Store.addGraphic(new Graphic(ellipse, Store.getPrimaryColor(), Store.getShapeWeight()));
					else
						Store.addGraphic(new Graphic(circle, Store.getPrimaryColor(), 2));
					
						
					Store.removeAllPoints();
					Store.setDrawFinished(false);
				}
				
				gr.setLineWidth(1);//To reset the stroke width of the app
				break;
		
			case FILL:
				Store.setCursorImage(Icons.FILL.getIcon());
				break;
				
			case WRITE:
				Store.setCursorImage(Icons.TEXT.getIcon());
				break;
				
			case ZOOM:
				Store.setCursorImage(Icons.ZOOM.getIcon());
				break;
				
			case PICK_COLOR:
				Store.setCursorImage(Icons.COLOR_PICKER.getIcon());
				break;
		}
	}
}
