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
	 * 			  PROPS 
	 * =============================
	*/
	
	//To place the scroller
		private float posX = Sizes.SCREEN_DEFAULT_WIDTH.getSize() - 8,
					  posY = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 3,
					  defaultPosY = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 3;
	
	private float scrollTo;
	
	/*
	 * =============================
	 * 			 METHODS 
	 * =============================
	*/
	
	public void display() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			int mouseY = Store.mouseY,
				mouseYClick = Store.mouseYClick;
		
		if( Store.currentAction == Actions.SCROLL && Store.longPress ) {// If the user is allowed to scroll and he's dragging his mouse
			scrollTo = -( mouseYClick - mouseY );
			
			Store.scrollTranslation = scrollTo;
		} else if(Store.currentAction == Actions.SCROLL) {
			//Store.scrollTranslation = scrollTo;
			
			posY += scrollTo;
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
			(new ActionLauncher(
				"",
				posX,
				posY + scrollTo,
				5, 130,
				Actions.SCROLL
			)).clickableListener();
	}
	
}
