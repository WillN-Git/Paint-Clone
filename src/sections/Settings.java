package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import constants.*;

/*
 * Responsible to handle the settings
 */
public class Settings {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.SETTING_HEIGHT.getSize();
	
	private float PADDING_H = 10;
	
	private float MIDDLE = 15;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr) throws SlickException {
		//Background
		gr.setColor(AppColors.PALEGRAY.getColor());
		gr.fillRect(0, 0, WIDTH, HEIGHT);
	
		//Buttons
		gr.drawImage(new Image(Icons.SAVE.toString()), 130, MIDDLE + 3);
		gr.drawImage(new Image(Icons.BACK.toString()), 170, MIDDLE + 3);
		gr.drawImage(new Image(Icons.NEXT.toString()), 200, MIDDLE + 3);
		
		//MenuItems
		gr.setColor(AppColors.TEXT.getColor());
		gr.drawString("File", PADDING_H, MIDDLE);
		gr.drawString("View", PADDING_H + 50, MIDDLE);
		
		//Feedback
		gr.drawImage(new Image(Icons.FEEDBACK.toString()), WIDTH - PADDING_H - 30, MIDDLE + 3);
		
		//Line Divider
		gr.setColor(new Color(200, 200, 200));
		
		lineDivider(gr, 115);
		lineDivider(gr, 160);
	}
	
	public void lineDivider(Graphics gr, float x) {
		gr.drawLine(x, MIDDLE - 5, x, MIDDLE + 25);
	}
}
