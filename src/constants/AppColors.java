package constants;

import org.newdawn.slick.Color;

public enum AppColors {
	//============= CONSTANTS
	
	PALEGRAY(0xFFF9F1EE),
	PALEWHITE(0xFFF6F6F6),
	WHITE(0xFFFFFFFF),
	SMOOTHGRAY(0xFFF8F8F8),
	TEXT(0xFF222222);
	
	//============= PROPS & CONSTRUCTOR
	Color c;
	
	AppColors(int hColor) {
		this.c = new Color(hColor);
	}
	
	public Color getColor() {
		return this.c;
	}
}
