package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
	
	//Section dimensions
		private static float WIDTH = Sizes.CANVA_WIDTH.getSize(),
							HEIGHT = Sizes.CANVA_HEIGHT.getSize();
	
	private static float PADDING_H = 10;
	
	private static float MIDDLE = 20,
						TOP = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 60,
						LEFT = 60,
						RIGHT = LEFT + WIDTH,
						BOTTOM = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 60 + HEIGHT;
	
	//The drawing manager
		private static DrawingManager drawingManager;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display(boolean showGridlines, float zoomFactor) throws SlickException {
		//Data retrieval
			gr = Store.getGr();
			mouseX = Store.getMouseX();
			mouseY = Store.getMouseY();
			mouseXClick = Store.getMouseXClick();
			mouseYClick = Store.getMouseYClick();
			drawingManager = new DrawingManager();
		
		gr.translate(0, -Store.getScrollTranslation());
		
		//The Paper
			gr.setColor(AppColors.WHITE.getColor());
			gr.fillRect(LEFT, TOP, WIDTH, HEIGHT);
			
			gr.setColor(new Color(230, 230, 230));
			gr.drawRect(LEFT, TOP, WIDTH, HEIGHT);
		
		//For drawing shapes
			for(Graphic g : Store.getGraphics()) {
				gr.setLineWidth(g.getWeight());
				gr.setColor(g.getColorOfShape());
				gr.draw(g.getShape());
				gr.setLineWidth(1);
			}
		
		drawingManager.displayMyDrawing();
		
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
}
