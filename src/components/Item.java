package components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import constants.AppColors;

public class Item {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private Image icon;
	private String label;
	private String shortcut;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public Item(Image icon, String label, String shortcut) {
		this.icon = icon;
		this.label = label;
		this.shortcut = shortcut;
	}
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	
	public String getLabel() {
		return this.label;
	}
	
	public Image getIcon() {
		return this.icon;
	}
	
	public String getShortcut() {
		return this.shortcut;
	}
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public void displayItem(Graphics gr, float posX, float posY, float end) {
		gr.setColor(AppColors.WHITE.getColor());
		
		if(icon != null)
			gr.drawImage(icon, posX, posY);
		
		gr.drawString(label, posX + 20, posY);
		gr.drawString(shortcut, end - (end*0.22f), posY);
	}
	
}
