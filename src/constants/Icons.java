package constants;

public enum Icons {
	LOGO("001-paint.png"),
	BACK("back.png"),
	NEXT("next.png"),
	SAVE("floppy-disk.png"),
	FEEDBACK("008-chat.png"),
	CLIPBOARD("006-clipboard.png"),
	COPY("007-copy.png"),
	CUT("008-cut.png"),
	SELECT("005-square-targeting-interface-symbol.png"),
	CROP("009-expand.png"),
	FLIP("003-flip.png"),
	ROTATE("004-horizontal-symmetry.png"),
	PENCIL("010-pencil.png"),
	ERASER("002-eraser.png"),
	COLOR_PICKER("002-color-picker.png"),
	ZOOM("001-zoom.png"),
	TEXT("001-word.png"),
	BRUSH("003-paintbrush.png"),
	SHAPES("005-shapes.png"),
	METER("006-meter.png"),
	COLOR_SCHEME("007-color-scheme.png");
	
	String path;
	
	Icons(String path) {
		this.path = "assets/icons/" + path;
	}
	
	public String toString() {
		return this.path;
	}
}
