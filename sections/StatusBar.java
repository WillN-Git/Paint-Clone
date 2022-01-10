package sections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import components.Store;
import constants.AppColors;
import constants.Icons;
import constants.Sizes;

/**
 * 
 * Responsible for rendering all the component's status of the application
 * 
 */

public abstract class StatusBar {
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	//Section dimensions
		private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
							HEIGHT = Sizes.STATUSBAR_HEIGHT.getSize();
	
	private static float PADDING_H = 10;
	
	private static float TOP = Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - HEIGHT;
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			int mouseX = Store.mouseX,
				mouseY = Store.mouseY;
		
		//Background
			gr.setColor(AppColors.PALEGRAY.getColor());
			gr.fillRect(0, TOP, WIDTH, HEIGHT);
		
		//Mouse position
			gr.setColor(AppColors.TEXT.getColor());
		
		gr.drawImage(Icons.SMALL_POINTER.getIcon(), PADDING_H, TOP + 5);
		
		
		if( Canva.isHover() ) {
			gr.drawString(
				(mouseX - 40) + "," + (int)(mouseY - (Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40)) + "px",
				PADDING_H + 20,
				TOP + 5
			);
		}
		
		gr.drawImage(Icons.SMALL_SELECTED.getIcon(), PADDING_H + 230, TOP + 7);
		
		gr.drawImage(Icons.SMALL_BOARD.getIcon(), PADDING_H + 450, TOP + 7);
		gr.drawString(
			(int)(Sizes.SCREEN_DEFAULT_WIDTH.getSize() * 0.7f) + "x" + (int)Sizes.BOARD_HEIGHT.getSize() + "px",
			PADDING_H + 470,
			TOP + 5
		);
	}
}
