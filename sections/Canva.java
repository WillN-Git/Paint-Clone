package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import components.Graphic;
import components.Store;
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
	
	private static float PADDING_H = 10;
	
	private static float MIDDLE = 20,
						TOP = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 60,
						LEFT = 60,
						RIGHT = LEFT + WIDTH,
						BOTTOM = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 60 + HEIGHT;
	
	//Hitbox
		private static Rectangle hitbox = new Rectangle(LEFT, TOP, WIDTH, HEIGHT);
	
	//The drawing manager
		private static DrawingManager drawingManager;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display(boolean showGridlines, float zoomFactor) throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			int mouseX = Store.mouseX,
				mouseY = Store.mouseY,
				mouseXClick = Store.mouseXClick,
				mouseYClick = Store.mouseYClick;
		
		//Init
			drawingManager = new DrawingManager();
		
		gr.translate(0, -Store.scrollTranslation);
		
		//The Paper
			gr.setColor(AppColors.WHITE.getColor());
			gr.fill(hitbox);
			
			gr.setColor(new Color(230, 230, 230));
			gr.draw(hitbox);
		
		//For drawing shapes
			for(Graphic g : Store.graphics) {
				
				if(g.getStr() != null) { //To Write
					gr.setColor( g.getColorOfShape() );
					gr.drawString(g.getStr(), g.getPosX(), g.getPosY());

				} else { //To draw
				
					gr.setLineWidth(g.getWeight());
					gr.setColor(g.getColorOfShape());
					
					if(g.getWeight() == 0)
						gr.fill(g.getShape());
					else
						gr.draw(g.getShape());
				
					//To reset the gr
						gr.setLineWidth(1);
				}
				
			}
			
		gr.resetTransform();
		drawingManager.displayMyDrawing();
		gr.translate(0, -Store.scrollTranslation);
		
		//GridLines
			if(showGridlines) {
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
