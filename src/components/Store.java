package components;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import constants.Actions;

public abstract class Store {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	
	private static Graphics gr;
	private static int mouseX, mouseY,
					   mouseXClick, mouseYClick;
	private static ArrayList<Shape> shapes = new ArrayList<>();
	
	private static Actions previousAction, currentAction;
	
	private static Image cursorImage = null;
	
	private static Item[] viewItems = {
		new Item(null, "100 %", ""),
		new Item(null, "Rulers", "Ctrl+R"),
		new Item(null, "Gridlines", "Ctrl+G"),
		new Item(null, "Status Bar", "Ctrl+B"),
		new Item(null, "FullScreen", "")
	};
	
	private static Item[] fileItems = {
			new Item(null, "New", "Ctrl+N"),
			new Item(null, "Open", "Ctrl+O"),
			new Item(null, "Recent", "     >"),
			new Item(null, "Save", "Ctrl+S"),
			new Item(null, "Save as", ""),
			new Item(null, "Print", ">"),
			//new Item(null, "From scenner or camera", ">"),
			new Item(null, "Send", ">"),
			//new Item(null, "Set as Desktop Background", ">"),
			new Item(null, "Image properties", "Ctrl+E"),
			new Item(null, "Settings", "")
		};
	
	
	/*
	 * =============================
	 * 			GETTERS 
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
	
	public static ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public static Actions getPreviousAction() {
		return previousAction;
	}
	
	public static Actions getCurrentAction() {
		return currentAction;
	}
	
	public static Item[] getFileItems() {
		return fileItems;
	}
	
	public static Item[] getViewItems() {
		return viewItems;
	}
	
	public static Image getCursorImage() {
		return cursorImage;
	}
	
	/*
	 * =============================
	 * 			SETTERS
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
	
	public static void addShape(Shape shape) {
		shapes.add(shape);
		System.out.print("\nShape Added !\n");
	}
	
	public static void setCurrentAction(Actions action) {
		previousAction = currentAction;
		currentAction = action;
	}
	
	public static void setCursorImage(Image img) {
		cursorImage = img;
	}
}
