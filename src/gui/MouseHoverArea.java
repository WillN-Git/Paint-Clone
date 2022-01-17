package gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.util.FontUtils;

import components.Store;
import components.Tooltip;
import constants.AppColors;

public class MouseHoverArea {
	
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private boolean isHover = false;
	//Hover Area Position
		protected float posX, posY;
	
	//Hover Area Dimensions
		protected float hoverAreaWidth, hoverAreaHeight;
	
	//Content
		protected String text = null;
		protected Image img = null;
		
	private int mouseX, mouseY;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public MouseHoverArea(String text, float posX, float posY, float width, float height) {
		this.text = text;//drawString(text, posX, posY);
		this.posX = posX;
		this.posY = posY;
		this.hoverAreaWidth = width + 8;
		this.hoverAreaHeight = height + 8;
	}
	
	public MouseHoverArea(String text, Image img, float posX, float posY, float width, float height) {
		this(text, posX, posY, width, height);
		
		this.img = img;//gr.drawImage(img, posX, posY);
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void hoverListener() {
		//Data retrieval
			mouseX = Store.mouseX;
			mouseY = Store.mouseY;
		
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
			Store.gr
				 .drawImage(img, posX, posY);
		} else {
			Store.gr
				 .setColor(AppColors.TEXT.getColor());
			
			FontUtils.drawCenter(
					Store.gr.getFont(), 
					text, 
					(int)posX - 4, 
					(int)posY, 
					(int)hoverAreaWidth, 
					AppColors.TEXT.getColor()
				);
		}
	}
	
	private void hoverAction() {	
		if(isHover) {
			Store.gr
				 .setColor(AppColors.TRANSPARENTGRAY.getColor());
		
			Store.gr
				 .fillRoundRect(posX - 4, posY - 4, hoverAreaWidth, hoverAreaHeight, 2);
			
			if(text != "File" && text != "View")
				(new Tooltip(text, posX, posY)).showTooltip();
		}
	}
	
}
