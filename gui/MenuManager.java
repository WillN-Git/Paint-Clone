package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Item;
import components.Store;
import components.Tool;
import constants.Actions;
import constants.AppColors;
import constants.Icons;
import constants.Images;
import constants.Sizes;

public class MenuManager {
	/*
	 * =============================
	 * 			  DATA 
	 * =============================
	*/
	
	private Graphics gr;
	private int mouseXClick, mouseYClick;
	
	
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private static Item[] fileItems = {
		new Item(null, "New", "Ctrl+N"),
		new Item(null, "Open", "Ctrl+O"),
		new Item(null, "Recent", "     >"),
		new Item(null, "Save", "Ctrl+S"),
		new Item(null, "Save as", ""),
		new Item(null, "Print", "     >"),
		//new Item(null, "From scenner or camera", ">"),
		new Item(null, "Send", "     >"),
		//new Item(null, "Set as Desktop Background", ">"),
		new Item(null, "Image properties", "Ctrl+E"),
		new Item(null, "Settings", "")
	};
	
	private static Item[] viewItems = {
		new Item(null, "100 %", ""),
		new Item(null, "Rulers", "Ctrl+R"),
		new Item(null, "Gridlines", "Ctrl+G"),
		new Item(null, "Status Bar", "Ctrl+B"),
		new Item(null, "FullScreen", "")
	};
	
	private static Tool[] shapes = {
		new Tool("Line", Icons.BW_LINE.toString(), 0, 0, 16, 16, Actions.DRAW_LINE),
		new Tool("Curve", Icons.BW_CURVE.toString(), 0, 0, 16, 16, Actions.DRAW_CURVE),
		new Tool("Circle", Icons.BW_OVAL.toString(), 0, 0, 16, 16, Actions.DRAW_ELLIPSE),
		new Tool("Rectangle", Icons.BW_RECTANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_RECTANGLE),
		new Tool("Round Rectangle", Icons.BW_ROUND_RECTANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_ROUNDED_RECTANGLE),
		new Tool("Polygon", Icons.BW_POLYGON.toString(), 0, 0, 16, 16, Actions.DRAW_POLYGON),
		new Tool("Triangle", Icons.BW_TRIANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_TRIANGLE),
		new Tool("Right triangle", Icons.BW_RIGHT_TRIANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_RIGHT_TRIANGLE),
		new Tool("Pentagon", Icons.BW_PENTAGON.toString(), 0, 0, 16, 16, Actions.DRAW_PENTAGON),
		new Tool("Hexagon", Icons.BW_HEXAGON.toString(), 0, 0, 16, 16, Actions.DRAW_HEXAGON)	
	};
	
	private String[][] sizes = {
		{Images.PX_1.toString(), "1"},
		{Images.PX_3.toString(), "3"},
		{Images.PX_5.toString(), "5"},
		{Images.PX_8.toString(), "10"}
	};
	
	/*
	 * =============================
	 * 			  METHODS 
	 * =============================
	*/
	
	public void displayMenu() throws SlickException { 
		switch( Store.getCurrentAction() ) {
			case SHOW_FILE_MENU :
				showFileMenu();
				break;
			case SHOW_VIEW_MENU :
				showViewMenu();
				break;
			case SHOW_SHAPE_MENU :
				if(Store.getPreviousAction() != Actions.CHOOSE_A_COLOR)
					showShapeMenu();
				break;
			case SHOW_SIZE_MENU : 
				showSizeMenu();
				break;
			case CHOOSE_COLOR_IN_CHROMA :
				showChromaticMenu();
				break;
		}
	}
	
	public void showChromaticMenu() {
		//Data retrieval
			gr = Store.getGr();
			
			
	}
	
	public void showSizeMenu() throws SlickException {
		//Data retrieval
			gr = Store.getGr();
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(565, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * 4, 90, 5);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(565, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * 4, 90, 5);
		
		int y = 0;
		for(String[] ts : sizes) {
			(new MouseHoverArea(
					"",
					new Image(ts[0]),
					585,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					98,
					10
			)).hoverListener();
			
			(new ClickableArea(
					585,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					98,
					10,
					Actions.CHOOSE_SIZE,
					Integer.parseInt(ts[1])
			)).clickableListener();
			y += 22;
		}
	}
	
	public void showShapeMenu() throws SlickException {
		//Data retrieval
			gr = Store.getGr();
		
		int numberPerColumn = 4;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(485, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * numberPerColumn, 25 * 3, 5);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(485, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * numberPerColumn, 25 * 3, 5);
		
		int x=0, y=0, i=0;
		for(Tool t : shapes) {
			(new MouseHoverArea(
					t.getLabel(),
					new Image(t.getImgPath()),
					495 + x,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					t.getWidth(),
					t.getHeight()
			)).hoverListener();
			
			(new ClickableArea(
					495 + x,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					t.getWidth(),
					t.getHeight(),
					t.getAction()
			)).clickableListener();
			
			i++;
			x = (i % 4 == 0) ? 0 : x + 35;
			y += (i % 4 == 0) ? 25 : 0;
		}
	}
	
	public void showFileMenu() {
		//Data retrieval
			gr = Store.getGr();
		
		int heightOfItem = 30;
		int numberOfItems = fileItems.length;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(
				10 - 4, 
				22 + 20 - 3, 
				300,//width 
				numberOfItems * heightOfItem, 
				5
			);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(
				10 - 4, 
				22 + 20 - 3, 
				300,//width 
				numberOfItems * heightOfItem, 
				5
			);
			
		int y=0;
		for(Item i : fileItems) {
			i.displayItem(gr, 10 + 5, 15 + 20 + 10 + y, 10 + 300);
			y+= heightOfItem;
		}
	}
	
	public void showViewMenu() {
		//Data retrieval
			gr = Store.getGr();
		
		int heightOfItem = 30;
		int numberOfItems = viewItems.length;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(
				10 + 50 - 4, 
				22 + 20 - 3, 
				300,//width 
				numberOfItems * heightOfItem, 
				5
			);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(
					10 + 50 - 4, 
					22 + 20 - 3, 
					300,//width 
					numberOfItems * heightOfItem, 
					5
				);
			
		int y=0;
		for(Item i : viewItems) {
			i.displayItem(gr, 10 + 50 + 5, 15 + 20 + 10 + y, 10 + 50 + 300);
			y+= heightOfItem;
		}
	}
}
