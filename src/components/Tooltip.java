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
		gr.setColor(AppColors.TEXT.getColor());
		gr.drawString(label, posX, posY);
	}
}
