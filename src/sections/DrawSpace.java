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
	
	private float MIDDLE = 20;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr) throws SlickException {
		//Paper
		gr.setColor(AppColors.WHITE.getColor());
		gr.fillRect(
			40,
			Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40,
			WIDTH,
			HEIGHT
		);
		
		gr.setColor(new Color(230, 230, 230));
		gr.drawRect(
			40,
			Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40,
			WIDTH,
			HEIGHT
		);
	}
}
