package components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.AppColors;

public class Item {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	private Image icon;
	private String label;
	private String shortcut;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public Item(Image icon, String label, String shortcut, float posX, float posY) {
		this.icon = icon;
		this.label = label;
		this.shortcut = shortcut;
		this.posX = posX;
		this.posY = posY;
	}
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void displayItem(Graphics gr) {
		gr.setColor(AppColors.TEXT.getColor());
		if(icon != null)
			gr.drawImage(icon, posX, posY);
		gr.drawString(label, posX + 10, posY);
		gr.drawString(shortcut, posX + 40, posY);
	}
	
}
