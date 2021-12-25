package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.AppColors;

public class ClickableArea {

	public ClickableArea(Graphics gr, Image img, float posX, float posY, float width, float height) {
		
	}
	
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
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	public ClickableArea(Graphics gr, float posX, float posY, float width, float height) {
		this.gr = gr;
		this.posX = posX;
		this.posY = posY;
		this.clickableAreaWidth = width + 8;
		this.clickableAreaHeight = height + 8;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void clickableListener(float mouseX, float mouseY) {
		
		if(
			(posX <= mouseX && mouseX <= posX + clickableAreaWidth) &&
			(posY <= mouseY && mouseY <= posY + clickableAreaHeight)
		  ) {
			
			isClicked = true;
		}
		clickAction();	
		
	}
	
	private void clickAction() {	
		if(isClicked) {
			gr.setColor(AppColors.TRANSPARENTGRAY.getColor());
			gr.fillRoundRect(posX - 4, posY - 4, clickableAreaWidth, clickableAreaHeight, 2);
		}
	}
	

}
