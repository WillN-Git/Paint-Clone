package gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import components.Store;
import constants.Actions;
import constants.AppColors;
import constants.Sizes;

public class ValueSetter extends ClickableArea {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private Color color = null;
	private float _f;
	private String s;
	
	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	
	public ValueSetter(String text, float posX, float posY, float width, float height, Actions action) {
		super(text, posX, posY, width, height, action);
	}
	
	public ValueSetter(String text, float posX, float posY, float width, float height, Actions action, Color color) {
		this(text, posX, posY, width, height, action);
		this.color = color;
	}
	
	public ValueSetter(String text, float posX, float posY, float width, float height, Actions action, float _f) {
		this(text, posX, posY, width, height, action);
		this._f = _f;
	}
	
	public ValueSetter(String text, float posX, float posY, float width, float height, Actions action, String s) {
		this(text, posX, posY, width, height, action);
		this.s = s;
	}
	
	public ValueSetter(String text, Image img, float posX, float posY, float width, float height, Actions action) {
		super(text, img, posX, posY, width, height, action);
	}
	
	public ValueSetter(String text, Image img, float posX, float posY, float width, float height, Actions action, Color color) {
		this(text, img, posX, posY, width, height, action);
		this.color = color;
	}
	
	public ValueSetter(String text, Image img, float posX, float posY, float width, float height, Actions action, float _f) {
		this(text, img, posX, posY, width, height, action);
		this._f = _f;
	}
	
	public ValueSetter(String text, Image img, float posX, float posY, float width, float height, Actions action, String s) {
		this(text, img, posX, posY, width, height, action);
		this.s = s;
	}
	
	/*
	 * ================================
	 * 			  METHODS 
	 * ================================
	*/
	
	@Override 
	protected void clickAction() {
		if(isClicked) {
			switch( action ) {
				case CHOOSE_A_COLOR : 
					Store.primaryColor = color;
					break;
					
				case CHOOSE_SIZE : 
					Store.shapeWeight = (int)_f;
					break;
					
				case SCROLL :
					Store.scrollTranslation = _f;
					break;
					
				case CENTERING_THE_TEXT :
					Store.textAlign = 'C';
					break;
					
				case PUT_TEXT_AT_THE_LEFT :
					Store.textAlign = 'L';
					break;
					
				case PUT_TEXT_AT_THE_RIGHT :
					Store.textAlign = 'R';
					break;
					
				case SHOW_FONT_FAMILY_MENU :
					Store.showFontFamilyMenu = true;
					Store.showFontSizeMenu = false;
					break;
				
				case CHOOSE_FONT_FAMILY : 
					Store.currentFontFamily = new TrueTypeFont(
												new Font(s, Font.BOLD, Store.currentFontSize),
												true
											);
					
					Store.currentFontFamilyName = s;
					
					Store.showFontFamilyMenu = false;
					break;
					
				case SHOW_FONT_SIZE_MENU :
					Store.showFontSizeMenu = true;
					Store.showFontFamilyMenu = false;
					break;
				
				case CHOOSE_FONT_SIZE : 
					Store.currentFontSize = (int)_f;
					
					Store.currentFontFamily = new TrueTypeFont(
							new Font(Store.currentFontFamilyName, Font.BOLD, Store.currentFontSize),
							true
						);
					
					Store.showFontSizeMenu = false;
					break;
			}
			
			clickIndicator();
		}
	}
	
	@Override
	protected void clickIndicator() {
		if(isClicked) {
			if(color == null) {
				//Rectangle
					Store.gr.setColor(AppColors.TRANSPARENTGRAY.getColor());
					Store.gr.fillRoundRect(posX - 4, posY - 4, hoverAreaWidth, hoverAreaHeight, 2);
			}
		}
	}
	
}
