package gui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.geom.Vector2f;

import components.Item;
import components.Store;
import constants.Actions;
import constants.AppColors;
import constants.Icons;
import constants.Sizes;

public class ActionManager {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	private Graphics gr;
	private int mouseX, mouseY,
				mouseXClick, mouseYClick;
	private Item[] fileItems = Store.getFileItems(),
				viewItems = Store.getViewItems();
	
	private float posX, posY;
	private float clickableAreaWidth, clickableAreaHeight;
	
	/*
	 * =============================
	 * 		  CONSTRUCTOR 
	 * =============================
	*/
	public ActionManager(float posX, float posY, float clickableAreaWidth, float clickableAreaHeight) {
		this.posX = posX;
		this.posY = posY;
		this.clickableAreaWidth = clickableAreaWidth;
		this.clickableAreaHeight = clickableAreaHeight;
	}
	
	
	/*
	 * =============================
	 * 			METHODS
	 * =============================
	*/
	public void doThis(Actions action) throws SlickException {
		//Data
		gr = Store.getGr();
		mouseXClick = Store.getMouseXClick();
		mouseYClick = Store.getMouseYClick();
		
		Store.setCurrentAction(action);
		
		switch(action) {
			case SHOW_MENU: 
				gr.setColor(AppColors.LIGHTTURQUOISE.getColor());
				
				if(mouseYClick < Sizes.SETTING_HEIGHT.getSize()) { //If you click on a menu item
					int heightOfItem = 30;
					int numberOfItems = (mouseXClick <= 60) ? fileItems.length : viewItems.length;
					Item[] items = (mouseXClick <= 60) ? fileItems : viewItems;
					
					gr.fillRoundRect(
							posX - 4, 
							posY + clickableAreaHeight - 3, 
							300,//width 
							numberOfItems * heightOfItem + 10, 5);
					
					gr.setColor(AppColors.WHITE.getColor());
					
					int y=0;
					for(Item i : items) {
						i.displayItem(gr, posX + 5, posY + clickableAreaHeight + 5 + y, posX + 300);
						y+= heightOfItem;
					}
				}
				break;
				
			case DRAW_WITH_PENCIL:
				Store.setCursorImage(Icons.PENCIL.getIcon());
				break;
				
			case ERASE:
				Store.setCursorImage(Icons.ERASER.getIcon());
				break;
				
			case FILL:
				Store.setCursorImage(Icons.FILL.getIcon());
				break;
				
			case WRITE:
				Store.setCursorImage(Icons.TEXT.getIcon());
				break;
				
			case ZOOM:
				Store.setCursorImage(Icons.ZOOM.getIcon());
				break;
				
			case DRAW_WITH_BRUSH:
				Store.setCursorImage(Icons.BRUSH.getIcon());
				break;
				
			case PICK_COLOR:
				Store.setCursorImage(Icons.COLOR_PICKER.getIcon());
				break;
			
			case SELECT_OPTION: 
//				Path path; 
//				
//				if(v.size() >= 4) {
//					path= new Path(v.get(0).getX(), v.get(0).getY());
//					for(int i=1; i<v.size() - 2; i++)
//						path.curveTo(v.get(i).getX(), v.get(i).getY(), v.get(i+1).getX(), v.get(i+1).getY(),v.get(i+2).getX(), v.get(i+2).getY());
//					
//					gr.setColor(new Color(0, 0, 0));
//					gr.draw(path);
//				}
				break;
		}
	}
}
