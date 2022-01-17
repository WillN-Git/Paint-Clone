package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.FontUtils;

import components.FilledShape;
import components.Graphic;
import components.OutlinedShape;
import components.Store;
import components.Text;
import constants.*;
import gui.DrawingManager;

/**
 * 
 * Responsible for rendering drawspace of the application
 *
 */

public abstract class Canva {
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	//Section dimensions
		private static float WIDTH = Sizes.CANVA_WIDTH.getSize(),
							HEIGHT = Sizes.CANVA_HEIGHT.getSize();
	
	//Section positions
		public static float TOP = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 60,
							 LEFT = 60,
							 RIGHT = LEFT + WIDTH,
							 BOTTOM = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 60 + HEIGHT;
	
	//Hitbox
		private static Rectangle hitbox = new Rectangle(LEFT, TOP, WIDTH, HEIGHT);
	
	//The drawing manager
		private static DrawingManager drawingManager = new DrawingManager();
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display(float zoomFactor) throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
	
		//Scroll
			gr.translate(0, -Store.scrollTranslation);
			
		//The Paper
			gr.setColor(AppColors.WHITE.getColor());
			gr.fill(hitbox);
		//Border
			gr.setColor(new Color(230, 230, 230));
			gr.draw(hitbox);
		
		//Transformation
			gr.rotate( LEFT + WIDTH/2, TOP + HEIGHT/2, Store.rotateFactor );
			
			
			if( Store.importedFilePath != null )
				gr.drawImage(new Image(Store.importedFilePath), 350, 350);
			
			
		//For drawing shapes
			for(Graphic g : Store.graphics) {
				
				if( g instanceof Text ) {//To Write
					
					switch( ((Text)g).getAlignement() ) {
						case 'L' :
							gr.setColor(g.getColorOfShape());
							gr.setFont(((Text)g).getFont());
							gr.drawString( 
									((Text)g).getStr(), 
									(int)g.getPosX(),
									(int)g.getPosY()
								);
							
							gr.setFont(Store.appFont);
							break;
						case 'R' :
							FontUtils.drawRight(
									((Text)g).getFont(), 
									((Text)g).getStr(), 
									(int)g.getPosX(),
									(int)g.getPosY(), 
									((Text)g).getWidth(),
									g.getColorOfShape()
								);
							break;
						case 'C' :
							FontUtils.drawCenter(
									((Text)g).getFont(), 
									((Text)g).getStr(), 
									(int)g.getPosX(),
									(int)g.getPosY(), 
									((Text)g).getWidth(),
									g.getColorOfShape()
								);
							break;
					}
				} else if ( g instanceof OutlinedShape ){//To Draw
					gr.setLineWidth(  ((OutlinedShape)g).getWeight() );
					gr.setColor( g.getColorOfShape() );
					
					gr.draw( ((OutlinedShape)g).getShape() );
					
					//To reset the gr
						gr.setLineWidth(1);
				} else if( g instanceof FilledShape ) {//To Fill
					gr.setColor( g.getColorOfShape() );
					gr.fill( ((FilledShape)g).getShape() );
				}
				
			}
		
		gr.resetTransform();
		drawingManager.displayMyDrawing();
		gr.translate(0, -Store.scrollTranslation);
		
		//GridLines
			if( Store.getShowGridlines() ) {
				gr.setColor(AppColors.TRANSPARENTGRAY.getColor());
				for(int i=(int)TOP; i<BOTTOM; i+=15)//Horizontal Lines
					gr.drawLine(LEFT, i, RIGHT, i);
								
				for(int j=(int)LEFT; j<RIGHT; j+=15)//Vertical lines
					gr.drawLine(j, TOP, j, BOTTOM);
			}
			
		gr.resetTransform();
	}
	
	public static boolean containsClick() {
		return hitbox.contains(Store.mouseXClick, Store.mouseYClick);
	}
	
	public static boolean isHover() {
		return hitbox.contains(Store.mouseX, Store.mouseY);
	}
}
