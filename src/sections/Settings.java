package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import components.Store;
import constants.*;
import gui.ClickableArea;
import gui.MouseHoverArea;


public abstract class Settings {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	
	private static Graphics gr;
	private static int mouseX, mouseY;
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	//Section dimensions
	private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.SETTING_HEIGHT.getSize();
	
	private static float PADDING_H = 10;
	
	private static float MIDDLE = 15;	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display() throws SlickException {
		//Data mapping
		gr = Store.getGr();
		mouseX = Store.getMouseX();
		mouseY = Store.getMouseY();
		
		//Background
		gr.setColor(AppColors.PALEGRAY.getColor());
		gr.fillRect(0, 0, WIDTH, HEIGHT);
	
		//Line Divider
		lineDivider(gr, 115);
		lineDivider(gr, 160);
		
		//Buttons
		(new MouseHoverArea(
				"Save",
				Icons.SAVE.getIcon(),
				130, MIDDLE + 3, 16, 16
		)).hoverListener();
		
		(new MouseHoverArea(
				"Back",
				Icons.BACK.getIcon(),
				170, MIDDLE + 3, 16, 16
		)).hoverListener();
		
		(new MouseHoverArea(
				"Next",
				Icons.NEXT.getIcon(),
				200, MIDDLE + 3, 16, 16
		)).hoverListener();
		
		//MenuItems
		gr.setColor(AppColors.TEXT.getColor());
		
		(new MouseHoverArea(
			"File",
			PADDING_H,
			MIDDLE,
			"File".length() * 10,
			20
		)).hoverListener();
		
		(new ClickableArea(
				PADDING_H,
				MIDDLE,
				"File".length() * 10,
				20,
				Actions.SHOW_FILE_MENU
		)).clickableListener();
		
		(new MouseHoverArea(
				"View",
				PADDING_H + 50,
				MIDDLE,
				"View".length() * 10,
				20
		)).hoverListener();
		
		(new ClickableArea(
				PADDING_H + 50,
				MIDDLE,
				"View".length() * 10,
				20,
				Actions.SHOW_VIEW_MENU
		)).clickableListener();
		
		//Feedback
		(new MouseHoverArea(
				"Feedback",
				Icons.FEEDBACK.getIcon(),
				WIDTH - PADDING_H - 30, 
				MIDDLE + 3,
				24,
				24
		)).hoverListener();		
	}
	
	public static void lineDivider(Graphics gr, float x) {
		gr.setColor(new Color(200, 200, 200));
		gr.drawLine(x, MIDDLE - 5, x, MIDDLE + 25);
	}
}
