package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.AppColors;

public class MouseHoverArea {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private boolean isHover = false;
	//Graphic context
	private Graphics gr;
	//Hover Area Position
	private float posX, posY;
	//Hover Area Dimensions
	private float hoverAreaWidth, hoverAreaHeight;
	//Content
	private String text;
	private Image img;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	public MouseHoverArea(Graphics gr, String text, float posX, float posY, float width, float height) {
		this.text = text;//drawString(text, posX, posY);
		this.gr = gr;
		this.hoverAreaWidth = width + 5;
		this.hoverAreaHeight = height + 5;
	}
	
	public MouseHoverArea(Graphics gr, Image img, float posX, float posY, float width, float height) {
		this.img = img;//gr.drawImage(img, posX, posY);
		this.gr = gr;
		this.posX = posX;
		this.posY = posY;
		this.hoverAreaWidth = width + 8;
		this.hoverAreaHeight = height + 8;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void hoverListener(float mouseX, float mouseY) {
		displayHoverableImage();
		
		if(
			(posX <= mouseX && mouseX <= posX + hoverAreaWidth) &&
			(posY <= mouseY && mouseY <= posY + hoverAreaHeight)
		  ) {
			
			isHover = true;
		} else {
			isHover = false;
		}
		
		hoverAction();	
	}
	
	private void displayHoverableImage() {
		gr.drawImage(img, posX, posY);
	}
	
	private void hoverAction() {	
		if(isHover) {
			gr.setColor(AppColors.TRANSPARENTGRAY.getColor());
			gr.fillRoundRect(posX - 4, posY - 4, hoverAreaWidth, hoverAreaHeight, 2);
		}
	}
	
}
