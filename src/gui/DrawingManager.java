package gui;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.util.FontUtils;

import components.FilledShape;
import components.Graphic;
import components.OutlinedShape;
import components.Store;
import components.Text;
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
			Actions currentAction = Store.currentAction;
			int mouseX = Store.mouseX,
				mouseY = Store.mouseY,
				mouseXClick = Store.mouseXClick,
				mouseYClick = Store.mouseYClick;
		
		//Init
//			Transform t = new Transform();
//			
//			t = t.concatenate( (new Transform()).createRotateTransform( Store.rotateFactor ) );
//			t = t.concatenate( (new Transform()).createTranslateTransform(0, -Store.scrollTranslation) );
			
		
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
						Store.setCursorImage( Icons.PENCIL.getIcon() );
					} else if(currentAction == Actions.DRAW_WITH_BRUSH) {
						Store.setCursorImage( Icons.BRUSH.getIcon() );
					} else {
						Store.setCursorImage( Icons.ERASER.getIcon() );
					}
				
				//Line Width
					 if(currentAction == Actions.DRAW_WITH_BRUSH)
						gr.setLineWidth(Store.shapeWeight);
					 else if( currentAction == Actions.ERASE )
						gr.setLineWidth(Store.shapeWeight);
					
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
						
//						path.transform(t);
						gr.draw(path);
					}
					
				if(currentAction != Actions.DRAW_WITH_PENCIL)
					gr.setLineWidth(1);//Reset line width for the whole app	
					
				
				//Saving the drawing
					if(Store.drawFinished) {
						
//						if( 
//							(path.getPoint(0)[0] >= path.getPoint( path.getPointCount() - 1 )[0] - 10 &&
//							path.getPoint(0)[1] <= path.getPoint( path.getPointCount() - 1 )[1] - 10) 
////								||
////							(path.getPoint(0)[0] <= path.getPoint( path.getPointCount() - 1 )[0] + 10 &&
////							path.getPoint(0)[1] <= path.getPoint( path.getPointCount() - 1 )[1] + 10)
//						) {
//							path.close();
//						}
						
						if(currentAction == Actions.ERASE)
							Store.addGraphic(new OutlinedShape(path, Store.secondColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_WITH_BRUSH)
							Store.addGraphic(new OutlinedShape(path, Store.primaryColor, Store.shapeWeight));
						else
							Store.addGraphic(new OutlinedShape(path, Store.primaryColor, 1));
						
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
					Store.setCursorImage( null );
					
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
						gr.setColor( Store.primaryColor );
						
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
							gr.setLineWidth(2);//Set line width to 1 is too small
							gr.draw(circle);
						}
					}
				
				//Saving the drawing
					if( Store.drawFinished ) {
						if(currentAction == Actions.DRAW_LINE)
							Store.addGraphic(new OutlinedShape(line, Store.primaryColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_RECTANGLE)
							Store.addGraphic(new OutlinedShape(rect, Store.primaryColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_ROUNDED_RECTANGLE)
							Store.addGraphic(new OutlinedShape(roundedRect, Store.primaryColor, Store.shapeWeight));
						else if(currentAction == Actions.DRAW_ELLIPSE)
							Store.addGraphic(new OutlinedShape(ellipse, Store.primaryColor, Store.shapeWeight));
						else
							Store.addGraphic(new OutlinedShape(circle, Store.primaryColor, 2));
						
							
						Store.removeAllPoints();
						Store.drawFinished = false;
					}
				
				gr.setLineWidth(1);//To reset the stroke width of the app
				break;
		
		//======================= TO FILL SHAPES
			case FILL :
				//Change the cursor
					Store.setCursorImage( Icons.FILL.getIcon() );
				
				if( Store.isClicking && Canva.containsClick() )
					pathFill();
				break;
		
		//======================= TYPEWRITER
			case WRITE :
				Store.setCursorImage( null );
				
				//Data retrieval 
					textfield = Store.textfield;
				
				textfield.setFocus(true);
				
				float boxWidth = 100;
				
				if( Canva.containsClick() ) {
					
					
					
					boxWidth = (textfield.getText().length()*10 < 100 ) ? 100 : textfield.getText().length()*7.25f;
					
					gr.setColor(AppColors.BLACK.getColor());
					gr.drawRect(mouseXClick-2, mouseYClick-2, boxWidth, 20);
					
					switch( Store.textAlign ) {
						case 'L' :
							gr.setFont( Store.currentFontFamily );
							gr.setColor(Store.primaryColor);
							gr.drawString( 
									textfield.getText(), 
									mouseXClick, 
									mouseYClick
								);
							
							gr.setFont( Store.appFont );
							break;
						case 'C' :
							FontUtils.drawCenter(
									Store.currentFontFamily, 
									textfield.getText(), 
									mouseXClick, 
									mouseYClick, 
									(int)boxWidth,
									Store.primaryColor
								);
							break;
						case 'R' :
							FontUtils.drawRight(
									Store.currentFontFamily, 
									textfield.getText(), 
									mouseXClick, 
									mouseYClick, 
									(int)boxWidth,
									Store.primaryColor
								);
							break;
					}
				}
				
				//The user has finished to type
					if( Store.enterButtonPressed ) {
						
						Store.addGraphic(new Text(
								Store.currentFontFamily,
								textfield.getText(), 
								(int)mouseXClick, 
								(int)mouseYClick,
								Store.textAlign,
								(int)boxWidth,
								Store.primaryColor
							));
						
						textfield.setText("");
					}
				break;
				
			case ZOOM :
				Store.setCursorImage( Icons.ZOOM.getIcon() );
				break;
		
		//======================= COLOR PICKER		
			case PICK_COLOR :
				Store.setCursorImage( Icons.COLOR_PICKER.getIcon() );
				
				
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
		
		if( Store.longPress && Store.longPressTimer == 60 )
			pathFill();
	}
	
	private void pathFill() {
		int mouseXClick = Store.mouseXClick,
			mouseYClick = Store.mouseYClick;
			
		OutlinedShape g = null;
		Path pathFiller;
		
		for(Graphic g0 : Store.graphics) {
			if(
				g0 instanceof OutlinedShape &&
				((OutlinedShape)g0).getShape().contains(mouseXClick, mouseYClick) && 
				((OutlinedShape)g0).getShape().closed()
			) {
				g = (OutlinedShape)g0;
			}
		}
			
		if( g != null ) {
			
//			if( !(
//					g.getColorOfShape().getRed() == Store.primaryColor.getRed() &&
//					g.getColorOfShape().getGreen() == Store.primaryColor.getGreen() &&
//					g.getColorOfShape().getBlue() == Store.primaryColor.getBlue()
//				) 
//			) { // If the color that the user want to fill is not the same that the current fill color of the shape
				
				Shape s = g.getShape();
				
				pathFiller = new Path( s.getPoint(0)[0], s.getPoint(0)[1] );
					
				for(int i=1; i<s.getPointCount(); i++)
					pathFiller.lineTo(s.getPoint(i)[0], s.getPoint(i)[1]);
					
				pathFiller.close();
				
				s = pathFiller.subtract( ((OutlinedShape)Store.graphics
															 .get(0))
															 .getShape()
										)[0];
				
				
				for( Graphic g1 : Store.graphics ) {
					if( g1 instanceof OutlinedShape && 
						((OutlinedShape)g1) != g &&
						((OutlinedShape)g1).getShape()
											.intersects(s)
					) {
						s = s.subtract( ((OutlinedShape)g1).getShape() )[0];
					}
						
				}

				Store.addGraphic(new FilledShape(s, Store.primaryColor));
//			}
		}
	}
}
