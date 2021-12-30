package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import constants.AppColors;
import constants.Sizes;

public class DrawSpace {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize() * 0.7f,
				HEIGHT = Sizes.BOARD_HEIGHT.getSize();
	
	private float PADDING_H = 10;
	
	private float MIDDLE = 20,
				TOP = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40,
				LEFT = 40,
				RIGHT = LEFT + WIDTH,
				BOTTOM = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40 + HEIGHT;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr, boolean showGridlines, float zoomFactor) throws SlickException {
		gr.scale(zoomFactor, zoomFactor);
		
		//Paper
		gr.setColor(AppColors.WHITE.getColor());
		gr.fillRect(LEFT, TOP, WIDTH, HEIGHT);
		
		gr.setColor(new Color(230, 230, 230));
		gr.drawRect(LEFT, TOP, WIDTH, HEIGHT);
		
		//GridLines
		if(showGridlines) {
			for(int i=(int)TOP; i<BOTTOM; i+=15)//Horizontal Lines
				gr.drawLine(LEFT, i, RIGHT, i);
			
			for(int j=(int)LEFT; j<RIGHT; j+=15)//Vertical lines
				gr.drawLine(j, TOP, j, BOTTOM);
		}
	}
}
