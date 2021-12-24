package constants;

public enum Icons {
	//============= ICONS
	
	//LOGO
	LOGO("001-paint.png"),
	
	//SMALL
	SMALL_SELECT("003-selection.png"),
	SMALL_POINTER("001-pointer.png"),
	SMALL_CROP("003-crop.png"),
	SMALL_FLIP("005-flip-image.png"),
	SMALL_ROTATE("004-rotation.png"),
	SMALL_SELECTED("001-select.png"),
	SMALL_BOARD("004-board.png"),
	SMALL_PICTURE("001-picture.png"),
	SMALL_COPY("001-copy.png"),
	SMALL_CUT("002-scissors.png"),
	
	//NORMAL
	BACK("back.png"),
	NEXT("next.png"),
	SAVE("floppy-disk.png"),
	FEEDBACK("008-chat.png"),
	CLIPBOARD("001-clipboard.png"),
	SELECT("001-selection.png"),
	PENCIL("010-pencil.png"),
	ERASER("002-eraser.png"),
	FILL("001-fill.png"),
	COLOR_PICKER("002-color-picker.png"),
	ZOOM("001-zoom.png"),
	TEXT("001-word.png"),
	BRUSH("003-paintbrush.png"),
	SHAPES("005-shapes.png"),
	METER("006-meter.png"),
	COLOR_SCHEME("007-color-scheme.png");
	
	
	
	//============= PROPS & CONSTRUCTOR
	String path;
	
	Icons(String path) {
		this.path = "assets/icons/" + path;
	}
	
	//============= GETTER
	public String toString() {
		return this.path;
	}
}
