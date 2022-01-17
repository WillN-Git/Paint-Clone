package gui;

import org.newdawn.slick.Image;

import components.Store;
import constants.Actions;

public class ActionLauncher extends ClickableArea {
	
	/*
	 * ================================
	 * 			  CONSTRUCTOR 
	 * ================================
	*/
	
	public ActionLauncher(String text, float posX, float posY, float width, float height, Actions action) {
		super(text, posX, posY, width, height, action);
	}
	
	public ActionLauncher(String text, Image img, float posX, float posY, float width, float height, Actions action) {
		super(text, img, posX, posY, width, height, action);
	}
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	
	@Override
	protected void clickAction() {
		if(isClicked) {
			Store.setCurrentAction(action);
			
			clickIndicator();
		}
	}
	
}
