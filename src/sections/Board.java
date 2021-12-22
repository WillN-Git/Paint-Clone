package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import constants.AppColors;
import constants.Icons;
import constants.Sizes;

public class Board {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.BOARD_HEIGHT.getSize();
	
	private float PADDING_H = 10;
	
	private float MIDDLE = 20;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr) throws SlickException {
		//Background
		gr.setColor(AppColors.PALEWHITE.getColor());
		gr.fillRect(
			0,
			Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize(),
			WIDTH,
			HEIGHT
		);
	}
}
