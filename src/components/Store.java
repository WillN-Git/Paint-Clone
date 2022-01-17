package components;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;

import constants.Actions;
import constants.AppColors;

/**
 * 
 * ========================= THE APPLICATION'S DATABASE ============================= 
 * The store is responsible for keeping all the data
 * Not all the data. But the ones that are shared by related components.
 * 
 * Communication will therefore be easier, because no matter what the hierarchical level 
 * 		of the component in the data flow, it will have quick and easy access to the 
 * 		desired information. 
 */

public abstract class Store {
	/*
	 * =============================
	 * 			  DATA 
	 * =============================
	*/
	
	//============ Basics
		public static Graphics gr;
		public static GameContainer gc;
		public static final TrueTypeFont appFont = new TrueTypeFont(
														new Font("Century Gothic", Font.BOLD, 14),
														true
													);
		
	//============ GUI
		public static boolean ctrlButtonPressed = false,
							  shiftButtonPressed = false,
							  enterButtonPressed = false,
							  longPress = false,
							  isClicking = false,
							  menuIsShown = false;
		
		public static float scrollTranslation = 0,
							lastScrollTranslation = 0;
		
		public static String textInput;
		
		public static int mouseX, mouseY,
		   				   mouseXClick, mouseYClick;
		
	//============ Toggle
		private static boolean showRuler = false,
							   showGridlines = false;
		
		public static int rotateFactor = 0;
		public static boolean showFontFamilyMenu = false, 
							  showFontSizeMenu = false;
	
	//============ Shape
		public static boolean isDrawing = false, drawFinished = false;
		
		public static ArrayList<Graphic> graphics = new ArrayList<>(),
										 removedGraphics = new ArrayList<>();
		
		public static ArrayList<Vector2f> setOfPoints = new ArrayList<>();
		public static int shapeWeight = 10;
		
		public static Color primaryColor = AppColors.BLACK.getColor(),
							secondColor = AppColors.WHITE.getColor();
		
		
	//============ Font
		public static String currentFontFamilyName = "Century Gothic";
		public static TrueTypeFont currentFontFamily = appFont;
		public static int currentFontSize = 14;
		
		public static String[] fontFamilyNames = {
			"Bradley Hand ITC",
			"Calibri",
			"Candara",
			"CASTELLAR",
			"Century Gothic",
			"Comic Sans MS",
			"Harrington",
			"Impact",
			"Curl MT",
			"Georgia",
			"Sergoe UI"
		};
	
	//============ Actions
		public static Actions previousAction = Actions.NONE,
							  currentAction = Actions.NONE;
		
		private static Image cursorImage = null;
		public static int cursorChangedCount = 0;
	
	//============ Tools to help
		public static TextField textfield;
		public static int longPressTimer = 0;
		public static char textAlign = 'L';
		public static String importedFilePath= null;
		public static int timer = 0;
	
	//============ Color watches
		public static Color[] swatches = {
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor(),
			AppColors.TRANSPARENT.getColor()
		};
		
		public static int lastSwatcheIndex;
	
	/*
	 * =============================
	 * 			 GETTERS
	 * =============================
	*/
		
	public static Image getCursorImage() {
		return cursorImage;
	}
	
	public static boolean getShowRuler() {
		return showRuler;
	}

	public static boolean getShowGridlines() {
		return showGridlines;
	}
	
	/*
	 * =============================
	 * 			 SETTERS
	 * =============================
	*/
	
	public static void addGraphic(Graphic graphic) {
//		if( graphic instanceof Text ) {
//			for(Graphic grph : graphics) 
//				if( grph instanceof Text  && (Text)grph == (Text)graphic )
//					return;
//		}
//		
//		if( graphic instanceof FilledShape ) {
//			System.out.println("Hey !");
//			for(Graphic grph : graphics) 
//				if( grph instanceof FilledShape && (FilledShape)grph == (FilledShape)graphic )
//					return;
//		}
//		
//		if( graphic instanceof OutlinedShape ) {
//			for(Graphic grph : graphics) 
//				if( grph instanceof OutlinedShape  && (OutlinedShape)grph == (OutlinedShape)graphic )
//					return;
//		}
		
		graphics.add(graphic);
	}

	public static void removeLastGraphic() {
		if( graphics.size() > 0 ) {
			removedGraphics.add( graphics.get( graphics.size()-1 ) );
			graphics.remove( graphics.size() - 1 );
		}
	}
	
	public static void remitGraphic() {
		if( removedGraphics.size() > 0 ) {
			graphics.add( removedGraphics.get( removedGraphics.size()-1 ) );
			removedGraphics.remove( removedGraphics.size()-1 );
		}
	}
	
	public static void addPoint(Vector2f p) {
		setOfPoints.add(p);
	}
	
	public static void removeAllPoints() {
		for(int i=setOfPoints.size()-1; i>=0; i--)
			setOfPoints.remove(i);
	}
	
	public static void deleteRemovedGraphics() {
		for(int i=removedGraphics.size()-1; i>=0; i--)
			removedGraphics.remove(i);
	}
	
	public static void setCurrentAction(Actions action) {
		previousAction = currentAction;
		currentAction = action;
	}
	
	public static void setSwatches(Color newColor) {
		if( lastSwatcheIndex >= swatches.length )
			lastSwatcheIndex = 0;
		
		swatches[ lastSwatcheIndex ] = newColor;
		
		lastSwatcheIndex++;
	}
	
	public static void setCursorImage(Image image) {
		if( previousAction != currentAction )
			cursorChangedCount = 0;
		
		if( cursorChangedCount == 0  )  {
			cursorImage = image;
			cursorChangedCount++;
		}
	}
	
	public static void setShowRuler() {
		showRuler = !showRuler;
	}
	
	public static void setShowGridlines() {
		showGridlines = !showGridlines;
	}
}
