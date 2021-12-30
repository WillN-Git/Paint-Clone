package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import components.Store;
import constants.AppColors;

public class MouseHoverArea {
	/*
	 * =============================
	 * 			  DATA 
	 * =============================
	*/
	
	private Graphics gr;
	private int mouseX, mouseY;
	
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private boolean isHover = false;
	
	//Hover Area Position
		private float posX, posY;
	
	//Hover Area Dimensions
		private float hoverAreaWidth, hoverAreaHeight;
	
	//Content
		private String text = null;
		private Image img = null;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public MouseHoverArea(String text, float posX, float posY, float width, float height) {
		this.text = text;//drawString(text, posX, posY);
		this.gr = Store.getGr();
		this.posX = posX;
		this.posY = posY;
		this.hoverAreaWidth = width + 8;
		this.hoverAreaHeight = height + 8;
	}
	
	public MouseHoverArea(String text, Image img, float posX, float posY, float width, float height) {
		this.text = text;
		this.img = img;//gr.drawImage(img, posX, posY);
		this.gr = Store.getGr();
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
	
	public void hoverListener() {
		//Data retrieval
			mouseX = Store.getMouseX();
			mouseY = Store.getMouseY();
		
		displayHoverableArea();
		
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
	
	private void displayHoverableArea() {
		if(img != null) {
			gr.drawImage(img, posX, posY);
		} else {
			gr.setColor(AppColors.TEXT.getColor());
			gr.drawString(text, posX, posY);
		}
	}
	
	private void hoverAction() {	
		if(isHover) {
			gr.setColor(AppColors.TRANSPARENTGRAY.getColor());
			gr.fillRoundRect(posX - 4, posY - 4, hoverAreaWidth, hoverAreaHeight, 2);
			
			//(new Tooltip(text, posX, posY)).showTooltip(gr);
		}
	}
	
}
