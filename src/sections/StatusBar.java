package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import constants.AppColors;
import constants.Sizes;

public class StatusBar {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.STATUSBAR_HEIGHT.getSize();
	
	private float PADDING_H = 10;
	
	private float TOP = Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - HEIGHT;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr, int mouseX, int mouseY) throws SlickException {
		//Background
		gr.setColor(AppColors.PALEGRAY.getColor());
		gr.fillRect(
			0,
			Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - HEIGHT,
			WIDTH,
			HEIGHT
		);
		
		//Mouse position
		gr.setColor(AppColors.TEXT.getColor());
		
		gr.drawString(mouseX + "x" + mouseY, PADDING_H, TOP + 5f);
	}
}
