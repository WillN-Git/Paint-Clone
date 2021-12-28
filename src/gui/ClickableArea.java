package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import components.Store;
import constants.Actions;
import constants.AppColors;

public class ClickableArea {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private boolean isClicked = false;
	//Graphic context
	private Graphics gr;
	//Hover Area Position
	private float posX, posY;
	//Hover Area Dimensions
	private float clickableAreaWidth, clickableAreaHeight;
	
	private Color color = null;
	
	private Actions action;
	private int mouseX, mouseY;
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public ClickableArea(float posX, float posY, float width, float height, Actions action) {
		this.gr = Store.getGr();
		this.posX = posX;
		this.posY = posY;
		this.clickableAreaWidth = width + 8;
		this.clickableAreaHeight = height + 8;
		this.action = action;
	}
	
	public ClickableArea(float posX, float posY, float width, float height, Actions action, Color color) {
		this(posX, posY, width, height, action);
		this.color = color;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void clickableListener() throws SlickException {
		//Data mapping
		mouseX = Store.getMouseXClick();
		mouseY = Store.getMouseYClick();
		
		
		if(
			(posX <= mouseX && mouseX <= posX + clickableAreaWidth) &&
			(posY <= mouseY && mouseY <= posY + clickableAreaHeight)
		  ) {
			
			isClicked = true;
		}
		clickAction();
	}
	
	private void clickAction() throws SlickException {	
		if(isClicked) {
			if(color == null) {
				//Rectangle
				gr.setColor(AppColors.TRANSPARENTGRAY.getColor());
				gr.fillRoundRect(posX - 4, posY - 4, clickableAreaWidth, clickableAreaHeight, 2);
			}
			
			//Launch the "action"
			Store.setCurrentAction(action);
			
			if(color != null)
				Store.setPrimaryColor(color);
		}
	}
}
