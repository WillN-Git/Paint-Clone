package sections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import constants.AppColors;
import constants.Icons;
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
		gr.fillRect(0, TOP, WIDTH, HEIGHT);
		
		//Mouse position
		gr.setColor(AppColors.TEXT.getColor());
		
		gr.drawImage(new Image(Icons.SMALL_POINTER.toString()), PADDING_H, TOP + 5);
		
		if(
			(40 < mouseX && mouseX < Sizes.SCREEN_DEFAULT_WIDTH.getSize() * 0.7f + 40) && 
			(Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40 < mouseY && mouseY < Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - HEIGHT)
		) {
			gr.drawString(
				mouseX + "," + mouseY,
				PADDING_H + 20,
				TOP + 5
			);
		}
		
		gr.drawImage(new Image(Icons.SMALL_SELECTED.toString()), PADDING_H + 230, TOP + 7);
		
		gr.drawImage(new Image(Icons.SMALL_BOARD.toString()), PADDING_H + 450, TOP + 7);
		gr.drawString(
			(int)(Sizes.SCREEN_DEFAULT_WIDTH.getSize() * 0.7f) + "x" + (int)Sizes.BOARD_HEIGHT.getSize() + "px",
			PADDING_H + 470,
			TOP + 5
		);
	}
}
