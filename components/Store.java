package components;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import constants.Actions;
import constants.AppColors;
import constants.Sizes;

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
		private static Graphics gr;
		private static int mouseX, mouseY,
						   mouseXClick, mouseYClick;
		private static boolean ctrlButtonPressed = false;
	
	//============ Shape
		private static boolean isDrawing = false, drawFinished = false;
		private static ArrayList<Graphic> graphics = new ArrayList<>(),
										  removedGraphics = new ArrayList<>();
		private static ArrayList<Vector2f> setOfPoints = new ArrayList<>();
		private static int shapeWeight = 10;
		private static Color primaryColor = AppColors.BLACK.getColor(),
							secondColor = AppColors.WHITE.getColor();
	
	//============ Actions
		private static Actions previousAction = Actions.NONE,
								currentAction = Actions.NONE;
		private static boolean isDragging = false;
		private static float scrollTranslation = 0;
		
		private static Image cursorImage = null;
	
	
	/*
	 * =============================
	 * 			  GETTERS 
	 * =============================
	*/
	
	public static Graphics getGr() {
		return gr;
	}
	
	public static int getMouseX() {
		return mouseX;
	}
	
	public static int getMouseY() {
		return mouseY;
	}
	
	public static int getMouseXClick() {
		return mouseXClick;
	}
	
	public static int getMouseYClick() {
		return mouseYClick;
	}
	
	public static boolean getIfCtrlButtonIsPressed() {
		return ctrlButtonPressed;
	}
	
	public static ArrayList<Graphic> getGraphics() {
		return graphics;
	}

	public static ArrayList<Vector2f> getSetOfPoints() {
		return setOfPoints;
	}
	
	public static Actions getPreviousAction() {
		return previousAction;
	}
	
	public static Actions getCurrentAction() {
		return currentAction;
	}
	
	public static Image getCursorImage() {
		return cursorImage;
	}
	
	public static boolean getIsDrawing() {
		return isDrawing;
	}
	
	public static boolean getDrawFinished() {
		return drawFinished;
	}
	
	public static Color getPrimaryColor() {
		return primaryColor;
	}
	
	public static Color getSecondColor() {
		return secondColor;
	}
	
	public static int getShapeWeight() {
		return shapeWeight;
	}
	
	public static boolean getIsDragging() {
		return isDragging;
	}
	
	public static float getScrollTranslation() {
		return scrollTranslation;
	}
	
	/*
	 * =============================
	 * 			  SETTERS
	 * =============================
	*/
	
	public static void setGr(Graphics gr) {
		Store.gr = gr; 
	}
	
	public static void setMouseX(int mouseX) {
		Store.mouseX = mouseX;
	}
	
	public static void setMouseY(int mouseY) {
		Store.mouseY = mouseY;
	}
	
	public static void setMouseXClick(int mouseXClick) {
		Store.mouseXClick = mouseXClick;
	}
	
	public static void setMouseYClick(int mouseYClick) {
		Store.mouseYClick = mouseYClick;
	}
	
	public static void setCtrlButtonPressed(boolean isPressed) {
		Store.ctrlButtonPressed = isPressed;
	}
	
	public static void addGraphic(Graphic graphic) {
		graphics.add(graphic);
		System.out.println("\n Combien ? " + graphics.size());
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
	
	public static void addPoint(Vector2f point) {
		setOfPoints.add(point);
	}
	
	public static void removeAllPoints() {
		setOfPoints = new ArrayList<>();
	}
	
	public static void setCurrentAction(Actions action) {
		previousAction = currentAction;
		currentAction = action;
	}
	
	public static void setCursorImage(Image img) {
		cursorImage = img;
	}
	
	public static void setIsDrawing(boolean isDrawing) {
		Store.isDrawing = isDrawing;
	}
	
	public static void setDrawFinished(boolean drawFinished) {
		Store.drawFinished = drawFinished;
	}
	
	public static void setPrimaryColor(Color newPrimaryColor) {
		Store.primaryColor = newPrimaryColor;
	}
	
	public static void setSecondColor(Color newSecondColor) {
		Store.secondColor = newSecondColor;
	}
	
	public static void setShapeWeight(int shapeWeight) {
		Store.shapeWeight = shapeWeight;
	}
	
	public static void setIsDragging(boolean isDragging) {
		Store.isDragging = isDragging;
	}
	
	public static void setScrollTranslation(float scrollTranslation) {
		Store.scrollTranslation = scrollTranslation;
	}
}
