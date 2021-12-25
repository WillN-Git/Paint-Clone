package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Item;
import constants.*;
import gui.ClickableArea;
import gui.MouseHoverArea;

/*
 * Responsible to handle the settings
 */
public class Settings {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	//Section dimensions
	private float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.SETTING_HEIGHT.getSize();
	
	private float PADDING_H = 10;
	
	private float MIDDLE = 15;
	
	private Item[] viewItems = {
		new Item(null, "100 %", "", PADDING_H + 50, MIDDLE+25),
		new Item(null, "Rulers", "Ctrl+R", 0, 0),
		new Item(null, "Gridlines", "Ctrl+G", 0, 0),
		new Item(null, "Status Bar", "Ctrl+B", 0, 0),
		new Item(null, "FullScreen", "", 0, 0)
	};
	
	private Item[] fileItems = {
		new Item(null, "New", "Ctrl+N", 0, 0),
		new Item(null, "Open", "Ctrl+O", 0, 0),
		new Item(null, "Recent", ">", 0, 0),
		new Item(null, "Save", "Ctrl+S", 0, 0),
		new Item(null, "Save as", "Ctrl+SHIFT+S", 0, 0),
		new Item(null, "Print", ">", 0, 0),
		new Item(null, "From scenner or camera", ">", 0, 0),
		new Item(null, "Send", ">", 0, 0),
		new Item(null, "Set as Desktop Background", ">", 0, 0),
		new Item(null, "Image properties", "Ctrl+E", 0, 0),
		new Item(null, "Settings", "", 0, 0)
	};
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr, float mouseX, float mouseY, float mouseXClick, float mouseYClick) throws SlickException {
		//Background
		gr.setColor(AppColors.PALEGRAY.getColor());
		gr.fillRect(0, 0, WIDTH, HEIGHT);
	
		//Buttons
		(new MouseHoverArea(
				gr,
				new Image(Icons.SAVE.toString()),
				130, MIDDLE + 3, 16, 16
		)).hoverListener(mouseX, mouseY);
		
		(new MouseHoverArea(
				gr,
				new Image(Icons.BACK.toString()),
				170, MIDDLE + 3, 16, 16
		)).hoverListener(mouseX, mouseY);
		
		(new MouseHoverArea(
				gr,
				new Image(Icons.NEXT.toString()),
				200, MIDDLE + 3, 16, 16
		)).hoverListener(mouseX, mouseY);
		
		//MenuItems
		gr.setColor(AppColors.TEXT.getColor());
		
		(new MouseHoverArea(
			gr,
			"File",
			PADDING_H,
			MIDDLE,
			"File".length() * 10,
			20
		)).hoverListener(mouseX, mouseY);
		
		(new ClickableArea(
				gr,
				PADDING_H,
				MIDDLE,
				"File".length() * 10,
				20
		)).clickableListener(mouseXClick, mouseYClick);
		
		(new MouseHoverArea(
				gr,
				"View",
				PADDING_H + 50,
				MIDDLE,
				"View".length() * 10,
				20
		)).hoverListener(mouseX, mouseY);
		
		(new ClickableArea(
				gr,
				PADDING_H + 50,
				MIDDLE,
				"View".length() * 10,
				20
		)).clickableListener(mouseXClick, mouseYClick);
		
		//Feedback
		(new MouseHoverArea(
				gr,
				new Image(Icons.FEEDBACK.toString()),
				WIDTH - PADDING_H - 30, 
				MIDDLE + 3,
				24,
				24
		)).hoverListener(mouseX, mouseY);;
		
		//Line Divider
		gr.setColor(new Color(200, 200, 200));
		
		lineDivider(gr, 115);
		lineDivider(gr, 160);
	}
	
	public void lineDivider(Graphics gr, float x) {
		gr.drawLine(x, MIDDLE - 5, x, MIDDLE + 25);
	}
	
	public void displayMenu() {
		
	}
}
