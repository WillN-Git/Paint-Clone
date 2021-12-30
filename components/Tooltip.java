package components;

import org.newdawn.slick.Graphics;

import constants.AppColors;

public class Tooltip {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	
	private String label;
	
	/*
	 * =============================
	 * 		  CONSTRUCTOR 
	 * =============================
	*/
	
	public Tooltip(String label, float posX, float posY) {
		this.label = label;
		this.posX = posX;
		this.posY = posY;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void showTooltip(Graphics gr) {
		gr.setColor(AppColors.LIGHTGOLD.getColor());
		gr.fillRect(posX - 4, posY - 20, label.length() * 10.5f, 20);
		
		gr.setColor(AppColors.TEXT.getColor());
		gr.drawString(label, posX, posY - 20);
	}
}
