package gui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.gui.TextField;

import components.Graphic;
import components.Store;
import constants.Actions;
import constants.AppColors;
import constants.Icons;
import constants.Images;
import constants.Sizes;

public class DrawingManager {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	
	private Graphics gr;
	private GameContainer gc;
	private int mouseX, mouseY,
				mouseXClick, mouseYClick;
	private Actions currentAction;
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	//Shapes
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
		
	//Color Picker
		private GradientFill colorpicker, c1, c2, c3, c4;
		private Image chroma;
	
	public void displayMyDrawing() throws SlickException {
		//Data retrieval
			gr = Store.getGr();
			gc = Store.getGc();
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
		
		//Init
			currentAction = Store.getCurrentAction();
		
		switch( currentAction ) {
			
		//======================= HANDRAWERS
			case DRAW_WITH_PENCIL : case DRAW_WITH_BRUSH : case ERASE : 
				
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
					
					if( Store.getIfShiftButtonIsPressed() )
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
		
			case FILL :
				Store.setCursorImage(Icons.FILL.getIcon());
				break;
		
		//======================= TYPEWRITER
			case WRITE :
				textfield = Store.getTextField();
				
				textfield.setFocus(true);
				
				gr.setColor(Store.getPrimaryColor());
				gr.drawString(textfield.getText(), mouseXClick, mouseYClick);
				
				if( Store.getIfEnterButtonIsPressed() ) {
					Store.addGraphic(new Graphic(textfield.getText(), (int)mouseXClick, (int)mouseYClick, Store.getPrimaryColor()));
					textfield.setText("");
				}
				break;
				
			case ZOOM :
				Store.setCursorImage(Icons.ZOOM.getIcon());
				break;
		
		//======================= TYPEWRITER		
			case PICK_COLOR :
//				Store.setCursorImage(null);
//				
//				red = Store.getRobot().getPixelColor(mouseX, mouseY).getRed();
//				green = Store.getRobot().getPixelColor(mouseX, mouseY).getGreen();
//				blue = Store.getRobot().getPixelColor(mouseX, mouseY).getBlue();
//				c = new Color(red, green, blue);
//				
//				gr.setColor(c);
//				gr.fillRect(mouseX+10, mouseY-10, 10, 10);
//				gr.setColor(AppColors.BLACK.getColor());
//				gr.drawRect(mouseX+10, mouseY-10, 10, 10);
				
//				colorpicker = new GradientFill(80, 350,
//												new Color(0xffff0000), 
//												110, 380, 
//												new Color(0xff0000ff)
//											);
//				
//				c1 = new GradientFill(80, 250, 
//									new Color(255, 0, 0),
//									80, 250 + 250/3,
//									new Color(0, 0, 255)
//								);
//				
//				c2 = new GradientFill(80, 250+250/3, 
//						new Color(0, 0, 255),
//						80, 250 + 250*2/3,
//						new Color(0, 255, 0)
//					);
//				
//				c3 = new GradientFill(80, 250+250*2/3, 
//						new Color(0, 255, 0),
//						80, 250 + 250,
//						new Color(255, 0, 0)
//					);
//				
//				for(int y=250; y<=250+250; y++) {
//					if(y <= 250 + 250/3) {
//						gr.setColor(new Color(c1.colorAt(80, y)));
//					} else if(250 + 250/3 < y  && y <= 250 + 250*2/3) {
//						gr.setColor(new Color(c2.colorAt(80, y)));
//					} else {
//						gr.setColor(new Color(c3.colorAt(80, y)));
//					}
//					gr.fillRect(80, y, 10, 2);
//				}
				//gr.drawRoundRect(80, 250, 10, 250, 10);
				chroma = Images.CHROMA.getImage();
				
				gr.drawImage(chroma, 250, 250);
				
				if(mouseX - 250 > 0 && mouseY - 250 >0) {
				gr.setColor(chroma.getColor(mouseX-250, mouseY-250));
				
				gr.fillRect(mouseX + 10, mouseY - 10, 10, 10);
				gr.setColor(AppColors.BLACK.getColor());
				gr.drawRect(mouseX+10, mouseY-10, 10, 10);
				}
				break;
		}
	}
}
