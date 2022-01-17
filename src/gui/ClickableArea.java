package gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Store;
import constants.Actions;
import constants.AppColors;

public abstract class ClickableArea extends MouseHoverArea {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	protected boolean isClicked = false;
	protected Actions action;
	
	
	/*
	 * ================================
	 * 		    CONSTRUCTOR 
	 * ================================
	*/
	
	public ClickableArea(String text, float posX, float posY, float width, float height, Actions action) {
		super(text, posX, posY, width, height);
		this.action = action;
	}
	
	public ClickableArea(String text, Image img, float posX, float posY, float width, float height, Actions action) {
		super(text, img, posX, posY, width, height);
		this.action = action;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void _clickableListener() throws SlickException {
		//Data retrieval
			int mouseX = Store.mouseXClick,
				mouseY = Store.mouseYClick;
		
		if(
			(posX <= mouseX && mouseX <= posX + hoverAreaWidth) &&
			(posY <= mouseY && mouseY <= posY + hoverAreaHeight)
		  ) {
			
			isClicked = true;
		}
		clickAction();
	}
	
	public void clickableListener() throws SlickException {
		_clickableListener();
		
		hoverListener();	
	}
	
	protected abstract void clickAction();
	
	protected void clickIndicator() {	
		if(isClicked) {
			//Rectangle
				Store.gr
					 .setColor(AppColors.TRANSPARENTGRAY.getColor());
				
				Store.gr
					 .fillRoundRect(posX - 4, posY - 4, hoverAreaWidth, hoverAreaHeight, 2);
		}
	}
}
