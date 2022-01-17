package components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import constants.Actions;
import constants.AppColors;
import gui.ActionLauncher;
import gui.ClickableArea;
import gui.MouseHoverArea;

public class Item {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	private Image icon;
	private String label;
	private String shortcut;
	private Actions action;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public Item(Image icon, String label, String shortcut, Actions action) {
		this.icon = icon;
		this.label = label;
		this.shortcut = shortcut;
		this.action = action;
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
	
	public Actions getAction() {
		return action;
	}
	
	/*
	 * =============================
	 * 			 METHODS 
	 * =============================
	*/
	
	public void displayItem(Graphics gr, float posX, float posY, float end) throws SlickException {
		gr.setColor(AppColors.TEXT.getColor());
		
		if(icon != null)
			gr.drawImage(icon, posX, posY);
		
		gr.drawString(label, posX + 20, posY);
		gr.drawString(shortcut, end - (end*0.22f), posY);
		
		(new ActionLauncher(
				"",
				null,
				posX + 20, posY, //Position
				end - (posX + 35), 20, //Dimensions
				action
		)).clickableListener();;
	}
	
}
