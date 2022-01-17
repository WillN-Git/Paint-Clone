package components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.FontUtils;

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
	
	public void showTooltip() {
		Graphics gr = Store.gr;
		
		gr.setColor(AppColors.LIGHTGOLD.getColor());
		gr.fillRect(posX, posY - 25, label.length() * 10.5f, 20);
			
		gr.setColor(AppColors.TEXT.getColor());
		FontUtils.drawCenter(
				gr.getFont(), 
				label, 
				(int)posX, 
				(int)posY - 25,
				(int)(label.length() * 10.5f),
				AppColors.TEXT.getColor()
			);
	}
}
