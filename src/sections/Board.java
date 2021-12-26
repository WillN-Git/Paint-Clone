package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Store;
import constants.AppColors;
import constants.Icons;
import constants.Images;
import constants.Sizes;

public class Board {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	
	private Graphics gr;
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	//Section Dimension
	private float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.BOARD_HEIGHT.getSize();
	
	private float PADDING_H = 10;
	
	private float MIDDLE = 20,
				TOP = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize(),
				LEFT = 0;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(boolean showRuler) throws SlickException {
		//Data mapping
		gr = Store.getGr();
		
		//Background
		gr.setColor(AppColors.PALEWHITE.getColor());
		gr.fillRect(LEFT, TOP, WIDTH, HEIGHT);
		
		//Ruler
		if(showRuler) {
			gr.drawImage(new Image(Images.RULER.toString()), LEFT, TOP);
		}
	}
}
