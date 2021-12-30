package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import components.Store;
import constants.Actions;
import constants.AppColors;
import constants.Sizes;

public class Scroller {
	/*
	 * =============================
	 * 			  DATA 
	 * =============================
	*/
	
	private Graphics gr;
	private int mouseY, mouseYClick;
	
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	//To place the scroller
	private float posX = Sizes.SCREEN_DEFAULT_WIDTH.getSize() - 8,
				  posY = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 3,
				  defaultPosY = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 3;
	
	private float scrollTo, lastScroll;
	
	/*
	 * =============================
	 * 			 METHODS 
	 * =============================
	*/
	
	public void display() throws SlickException {
		//Data retrieval
		gr = Store.getGr();
		mouseY = Store.getMouseY();
		mouseYClick = Store.getMouseYClick();
		
		if(Store.getCurrentAction() == Actions.SCROLL && Store.getIsDragging()) {
			scrollTo = -( mouseYClick - mouseY );
			lastScroll = scrollTo;
			
			Store.setScrollTranslation(scrollTo);
		} else if(Store.getCurrentAction() == Actions.SCROLL) {
			Store.setScrollTranslation(lastScroll);
			
			posY += lastScroll;
			scrollTo = 0;
			
			if(posY < defaultPosY)
				posY = defaultPosY;
			if(posY+130 > Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize())
				posY = Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize() - 130;
			
			
			Store.setCurrentAction(Actions.NONE);
		}
		
		//The scroller
		gr.setColor(AppColors.LIGHTGRAY.getColor());
		gr.drawRoundRect(posX, posY + scrollTo, 5, 130, 10);
		(new ClickableArea(
			posX,
			posY + scrollTo,
			5,
			130,
			Actions.SCROLL
		)).clickableListener();
	}
	
}
