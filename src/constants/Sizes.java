package constants;

public enum Sizes {
	//============= SIZES
	SCREEN_DEFAULT_WIDTH(1080),
	SCREEN_DEFAULT_HEIGHT(575),
	SETTING_HEIGHT(575 * 0.085f),
	TOOLKIT_HEIGHT(575 * 0.19f),
	BOARD_HEIGHT(575 - (575 * 0.085f) - (575 * 0.19f)),
	STATUSBAR_HEIGHT(575 * 0.05f);
	
	
	//============= PROPS & CONSTRUCTOR
	float size;
	
	Sizes(float size) {
		this.size = size;
	}
	
	//============= GETTER
	public float getSize() {
		return this.size;
	}
}
