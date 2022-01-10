package components;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	
	//============ GUI
		public static boolean ctrlButtonPressed = false;
		public static boolean shiftButtonPressed = false;
		public static boolean enterButtonPressed = false;
		public static boolean isDragging = false;
		public static boolean isClicking = false;
		public static boolean menuIsShown = false;
		public static float scrollTranslation = 0;
		
		public static String textInput;
		
		public static int mouseX, mouseY,
		   				   mouseXClick, mouseYClick,
		   				   absoluteMouseX, absoluteMouseY;
	
	//============ Shape
		public static boolean isDrawing = false, drawFinished = false;
		public static ArrayList<Graphic> graphics = new ArrayList<>(),
										  removedGraphics = new ArrayList<>();
		public static ArrayList<Vector2f> setOfPoints = new ArrayList<>();
		public static int shapeWeight = 10;
		public static Color primaryColor = AppColors.BLACK.getColor(),
							secondColor = AppColors.WHITE.getColor();
	
	//============ Actions
		public static Actions previousAction = Actions.NONE,
								currentAction = Actions.NONE;
		
		public static Image cursorImage = null;
	
	//============ Tools to help
		public static TextField textfield;
		public static GradientFill colorpicker;
	
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
	 * 			 SETTERS
	 * =============================
	*/
	
	public static void addGraphic(Graphic graphic) {
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
		setOfPoints = new ArrayList<>();
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
}
