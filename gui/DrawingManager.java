package gui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.gui.TextField;

import components.Graphic;
import components.Store;
import constants.*;
import sections.Canva;


public class DrawingManager {
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	//Shapes
		private Shape[] shapes;
		private Path path;
		private Rectangle rect;
		private RoundedRectangle roundedRect;
		private Ellipse ellipse;
		private Circle circle;
		private Line line;
		private Polygon polygon;
		private Curve curve;
		private TextField textfield;
		
	//Shapes characteristics
		private float width, height, diagonal, segments;
		
	//To retrieve the set of points into the store
		private ArrayList<Vector2f> v;
		
	
	public void displayMyDrawing() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			GameContainer gc = Store.gc;
			Actions currentAction = Store.currentAction;
			int mouseX = Store.mouseX,
				mouseY = Store.mouseY,
				mouseXClick = Store.mouseXClick,
				mouseYClick = Store.mouseYClick,
				absoluteMouseX = Store.absoluteMouseX,
				absoluteMouseY = Store.absoluteMouseY;
				
		
		/**
		 * 
		 * ================ A drawing is formed in 3 steps ================
		 * First we change the cursor
		 * Then we draw (if the cursor is placed on the paper)
		 * Finally we add the drawing to the blind for storage
		 * 
		 */
		
		switch( currentAction ) {
			
		//======================= HANDRAWERS
			case DRAW_WITH_PENCIL : case DRAW_WITH_BRUSH : case ERASE : 
				
				//Init
					v = Store.setOfPoints;
					path = null;
				
				//To change cursor Image
					if(currentAction == Actions.DRAW_WITH_PENCIL) {
						Store.cursorImage = Icons.PENCIL.getIcon();
					} else if(currentAction == Actions.DRAW_WITH_BRUSH) {
						Store.cursorImage =Icons.BRUSH.getIcon();
						gr.setLineWidth(Store.shapeWeight);
					} else {
						Store.cursorImage = Icons.ERASER.getIcon();
						gr.setLineWidth(Store.shapeWeight);
					}
				
				//To draw
					if(v.size() >= 4) {
						path = new Path(v.get(0).getX(), v.get(0).getY());
						
						gr.setColor(Store.primaryColor);
						if(Store.currentAction == Actions.ERASE)
							gr.setColor(Store.secondColor);
						
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
				
				//Saving the drawing
					if(Store.drawFinished) {
						if(currentAction == Actions.ERASE)
							Store.addGraphic(new Graphic(path, Store.secondColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_WITH_BRUSH)
							Store.addGraphic(new Graphic(path, Store.primaryColor, Store.shapeWeight));
						else
							Store.addGraphic(new Graphic(path, Store.primaryColor, 1));
						
						Store.removeAllPoints();//To clear the set of points in the store
						Store.drawFinished = false;
					}
				break;
				
		//======================= BASIC SHAPES
			case DRAW_RECTANGLE : case DRAW_ROUNDED_RECTANGLE : 
			case DRAW_ELLIPSE : case DRAW_LINE : case DRAW_TRIANGLE :
			case DRAW_PENTAGON : case DRAW_HEXAGON :
				
				//Init
					v = Store.setOfPoints;
					rect = null;
					roundedRect = null;
					ellipse = null;
					line = null;
					circle = null;
				
				//To change cursor image
					Store.cursorImage = null;
					
				//To draw	
					gr.setLineWidth(1);
					
					if(v.size() >= 2) {
						width = -( v.get(0).getX() - v.get(v.size() - 1).getX() );
						height = ( v.get(v.size() - 1).getY() - v.get(0).getY() );
						diagonal = (float)Math.sqrt( Math.pow(width, 2) + Math.pow(height, 2) );
						segments = ( Store.currentAction == Actions.DRAW_TRIANGLE ) 
								? 3 : ( Store.currentAction == Actions.DRAW_PENTAGON ) 
								? 5 : 6;
						
						line = new Line(v.get(0).getX(),v.get(0).getY(), v.get(v.size() - 1).getX(), v.get(v.size() - 1).getY());
						roundedRect = new RoundedRectangle(v.get(0).getX(), v.get(0).getY(), width, height, 5);
						circle = new Circle(v.get(0).getX(), v.get(0).getY(), diagonal, (int)segments);
						
						rect = (Store.ctrlButtonPressed) 
							 ? new Rectangle(v.get(0).getX(),v.get(0).getY(), diagonal, diagonal)
							 : new Rectangle(v.get(0).getX(),v.get(0).getY(), width, height);
						
						
						ellipse = (Store.ctrlButtonPressed) 
								? new Ellipse(v.get(0).getX(), v.get(0).getY(), diagonal, diagonal)
								: new Ellipse(v.get(0).getX(), v.get(0).getY(), width, height);
						
						gr.setLineWidth(Store.shapeWeight);
						
						if( Store.shiftButtonPressed )
							gr.rotate(v.get(0).getX(), v.get(0).getY(), mouseY);
						
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
				
				//Saving the drawing
					if( Store.drawFinished ) {
						if(currentAction == Actions.DRAW_LINE)
							Store.addGraphic(new Graphic(line, Store.primaryColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_RECTANGLE)
							Store.addGraphic(new Graphic(rect, Store.primaryColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_ROUNDED_RECTANGLE)
							Store.addGraphic(new Graphic(roundedRect, Store.primaryColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_ELLIPSE)
							Store.addGraphic(new Graphic(ellipse, Store.primaryColor, Store.shapeWeight));
						else
							Store.addGraphic(new Graphic(circle, Store.primaryColor, 2));
						
							
						Store.removeAllPoints();
						Store.drawFinished = false;
					}
				
				gr.setLineWidth(1);//To reset the stroke width of the app
				break;
		
		//======================= TO FILL SHAPES
			case FILL :
				Store.cursorImage = Icons.FILL.getIcon();
				
				//Cmb de formes contiennent se point ?
//				floodFill(mouseXClick, mouseYClick);
				
				if( Canva.containsClick() )
					boundaryFill8(mouseXClick, mouseYClick, AppColors.DARKRED.getColor(), AppColors.BLACK.getColor());
				
				
				//Si c'est une forme, gardes moi cette forme
				
				//Sinon, tu souffres......
				
//				if( Canva.containsClick() ) {
//				
//					floodFill(new Vector2f(mouseXClick, mouseYClick),
//							AppColors.WHITE.getColor(),
//							Store.primaryColor
//							);
//				}
				break;
		
		//======================= TYPEWRITER
			case WRITE :
				
				textfield = Store.textfield;
				
				textfield.setFocus(true);
				
				gr.setColor(Store.primaryColor);
				gr.drawString(textfield.getText(), mouseXClick, mouseYClick);
				
				float minWidth = 100, 
					additionnal = (textfield.getText().length() - minWidth < 0) ? 0 : textfield.getText().length() - minWidth * 1.5f;
				gr.setColor(AppColors.BLACK.getColor());
				gr.drawRect(mouseXClick-2, mouseYClick-2, minWidth + additionnal, 20);
				
				if( Store.enterButtonPressed ) {
					Store.addGraphic(new Graphic(textfield.getText(), (int)mouseXClick, (int)mouseYClick, Store.primaryColor));
					textfield.setText("");
				}
				break;
				
			case ZOOM :
				Store.cursorImage = Icons.ZOOM.getIcon();
				break;
		
		//======================= COLOR PICKER		
			case PICK_COLOR :
				Store.cursorImage = Icons.COLOR_PICKER.getIcon();
				
				
				if( Canva.isHover() ) {
					gr.setColor( gr.getPixel(mouseX, mouseY) );
					
					gr.fillRect(mouseX+24, mouseY-32, 10, 10);
					gr.setColor(AppColors.GRAY.getColor());
					gr.drawRect(mouseX+24, mouseY-32, 10, 10);
				}
				
				if( Store.isClicking && Canva.containsClick() ) {
					Store.setSwatches( gr.getPixel(mouseXClick, mouseYClick) );
				}
				break;
		}
	}
	
	public void floodFill(Vector2f pixel, Color target, Color rep) {
		Graphics gr = Store.gr;
		
		if( 
				(gr.getPixel((int)(pixel.getX()), (int)(pixel.getY())).getRed() == target.getRed()) &&
				(gr.getPixel((int)(pixel.getX()), (int)(pixel.getY())).getGreen() == target.getGreen()) &&
				(gr.getPixel((int)(pixel.getX()), (int)(pixel.getY())).getBlue() == target.getBlue())
		) {
			gr.drawRect(pixel.getX(), pixel.getY(), 2, 2);
			floodFill(new Vector2f(pixel.getX(), pixel.getY()-1), target, rep);
			floodFill(new Vector2f(pixel.getX(), pixel.getY()+1), target, rep);
			floodFill(new Vector2f(pixel.getX()+1, pixel.getY()), target, rep);
			floodFill(new Vector2f(pixel.getX()-1, pixel.getY()-1), target, rep);
		}
	}
	
	public void boundaryFill8(int x, int y, Color fill_color, Color boundary_color) {

	    if( !isEqual(Store.gr.getPixel(x, y), boundary_color) &&
	       !isEqual(Store.gr.getPixel(x, y), fill_color) 
	      ) {
	    	
	    	Store.gr.setColor(fill_color);
	    	Store.gr.fillRect(x, y, 2, 2);
	        boundaryFill8(x + 1, y, fill_color, boundary_color);
	        boundaryFill8(x, y + 1, fill_color, boundary_color);
	        boundaryFill8(x - 1, y, fill_color, boundary_color);
	        boundaryFill8(x, y - 1, fill_color, boundary_color);
	        boundaryFill8(x - 1, y - 1, fill_color, boundary_color);
	        boundaryFill8(x - 1, y + 1, fill_color, boundary_color);
	        boundaryFill8(x + 1, y - 1, fill_color, boundary_color);
	        boundaryFill8(x + 1, y + 1, fill_color, boundary_color);
	    }
	}
	
	private boolean isEqual(Color a, Color b) {
		return ( 
			a.getRed() == b.getRed() && 
			a.getGreen() == b.getGreen() &&
			a.getBlue() == b.getBlue()
		);
	}
}
