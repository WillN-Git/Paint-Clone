package constants;

import org.newdawn.slick.Color;

public enum AppColors {
	//============= COLORS
	
	//LIGHT
	WHITE(0xffffffff),
	LIGHTGRAY(0xffc3c3c3),
	LIGHTYELLOW(0xffefe4b0),
	LIGHTTURQUOISE(0xff99d9ea),
	LIGHTGOLD(0x40ffc90e),
	
	//DARK
	BLACK(0xff333333),
	DARKRED(0xff880015),
	DARKWHITE(0xfffafafa),
	
	//PALE
	PALEGRAY(0xfff9f1ee),
	PALEWHITE(0xfff6f6f6),
	
	//SMOOTH
	SMOOTHGRAY(0xfff8f8f8),
	
	//FONT
	TEXT(0xff222222),
	
	//TRANSPARENT
	TRANSPARENTGRAY(0x40cccccc),
	
	//NORMAL
	GRAY(0xff7f7f7f),
	RED(0xffed1c24),
	ORANGE(0xffff7f27),
	YELLOW(0xfffff200),
	GREEN(0xff22b14c),
	TURQUOISE(0xff00a2e8),
	INDIGO(0xff3f48cc),
	PURPLE(0xffa349a4),
	PINK(0xffffaec9),
	GOLD(0xffffc90e),
	LIME(0xffb5e61d),
	BLUEGRAY(0xff7092be),
	LAVENDER(0xffc8bfe7);
	
	//============= PROPS & CONSTRUCTOR
	Color c;
	
	AppColors(int hColor) {//To set the color #aarrggbb
		this.c = new Color(hColor);
	}
	
	//============= GETTER
	public Color getColor() {
		return this.c;
	}
}
