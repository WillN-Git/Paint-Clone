package sections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Store;
import constants.AppColors;
import constants.Images;
import constants.Sizes;

public abstract class Board {
	/*
	 * =============================
	 * 			  DATA 
	 * =============================
	*/
	
	private static Graphics gr;
	private static int mouseX, mouseY;
	
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	//Section Dimension
		private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
							HEIGHT = Sizes.BOARD_HEIGHT.getSize();
	
	private static float PADDING_H = 10;
	
	private static float MIDDLE = 20,
						TOP = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize(),
						LEFT = 0;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display(boolean showRuler) throws SlickException {
		//Data retrieval
			gr = Store.getGr();
			mouseX = Store.getMouseX();
			mouseY = Store.getMouseY();
		
		//Background
//			gr.setColor(AppColors.PALEWHITE.getColor());
//			gr.fillRect(LEFT, TOP, WIDTH, HEIGHT);
		
		//Ruler
			if(showRuler) {
				gr.drawImage(new Image(Images.RULER.toString()), LEFT, TOP);
				
				float limitX = 60, 
					  limitY = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 60;
				
				if( mouseY >= Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() ) {
				
					gr.setColor( AppColors.RED.getColor() );
					gr.drawLine( 
								 ((limitX <= mouseX) ? mouseX : limitX), TOP,
								 ((limitX <= mouseX) ? mouseX : limitX), TOP+20
							   );
					
					gr.drawLine(
								0, ((limitY <= mouseY) ? mouseY : limitY),
								18, ((limitY <= mouseY) ? mouseY : limitY)
							   );
				}
			}
	}
}
