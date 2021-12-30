package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Store;
import components.Tool;
import constants.*;
import gui.*;


public abstract class Toolkit {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	
	private static Graphics gr;
	private static int mouseX, mouseY;
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	//Section dimension
	private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
						HEIGHT = Sizes.TOOLKIT_HEIGHT.getSize();
	
	private static float PADDING_H = 25;
	
	private static float TOP = Sizes.SETTING_HEIGHT.getSize(),
						MIDDLE = TOP + 40 - 10,
						BOTTOM = Sizes.SETTING_HEIGHT.getSize() + HEIGHT;
	
	private static Color[][] colorset = {
				{
					AppColors.BLACK.getColor(),
					AppColors.GRAY.getColor(),
					AppColors.DARKRED.getColor(),
					AppColors.RED.getColor(),
					AppColors.ORANGE.getColor(),
					AppColors.YELLOW.getColor(),
					AppColors.GREEN.getColor(),
					AppColors.TURQUOISE.getColor(),
					AppColors.INDIGO.getColor(),
					AppColors.PURPLE.getColor()
				}, 
				{
					AppColors.WHITE.getColor(),
					AppColors.LIGHTGRAY.getColor(),
					AppColors.LIGHTGRAY.getColor(),
					AppColors.PINK.getColor(),
					AppColors.GOLD.getColor(),
					AppColors.LIGHTYELLOW.getColor(),
					AppColors.LIME.getColor(),
					AppColors.LIGHTTURQUOISE.getColor(),
					AppColors.BLUEGRAY.getColor(),
					AppColors.LAVENDER.getColor()
				}, 
				{
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor(),
					AppColors.WHITE.getColor()
				}
			};
	
	private static Tool[] tools = {
			//CLIPBOARD
		new Tool("Cut", Icons.SMALL_CUT.toString(), PADDING_H + 65, TOP + 17, 16, 16, Actions.CUT),
		new Tool("Paste", Icons.CLIPBOARD.toString(), PADDING_H, MIDDLE + 3, 32, 32, Actions.PASTE),
		new Tool("Copy", Icons.SMALL_COPY.toString(), PADDING_H + 65, MIDDLE + 27, 16, 16, Actions.COPY),
			//IMAGE
		new Tool("Crop", Icons.SMALL_CROP.toString(), PADDING_H + 190, TOP + 15, 16, 16, Actions.CROP),
		new Tool("Rotate", Icons.SMALL_ROTATE.toString(), PADDING_H + 157, TOP + 15, 16, 16, Actions.ROTATE),
		new Tool("Select", Icons.SELECT.toString(), PADDING_H + 115, MIDDLE + 3, 32, 32, Actions.SELECT),
		new Tool("Picture", Icons.SMALL_PICTURE.toString(), PADDING_H + 157, MIDDLE + 37, 16, 16, Actions.RESIZE),
		new Tool("Flip", Icons.SMALL_FLIP.toString(), PADDING_H + 190, MIDDLE + 37, 16, 16, Actions.FLIP),
			//TOOLS
		new Tool("Pencil", Icons.PENCIL.toString(), PADDING_H + 235, TOP + 8, 24, 24, Actions.DRAW_WITH_PENCIL),
		new Tool("Eraser", Icons.ERASER.toString(), PADDING_H + 235, MIDDLE + 27, 24, 24, Actions.ERASE),
		new Tool("Fill", Icons.FILL.toString(), PADDING_H + 273, TOP + 9, 24, 24, Actions.FILL),
		new Tool("Color picker", Icons.COLOR_PICKER.toString(), PADDING_H + 275, MIDDLE + 28, 24, 24, Actions.PICK_COLOR),
		new Tool("Text", Icons.TEXT.toString(), PADDING_H + 310, TOP + 9.5f, 24, 24, Actions.WRITE),
		new Tool("Zoom", Icons.ZOOM.toString(), PADDING_H + 310, MIDDLE + 27, 24, 24, Actions.ZOOM),
			//BRUSHES
		new Tool("Brush", Icons.BRUSH.toString(), PADDING_H + 385, MIDDLE + 8, 24, 24, Actions.DRAW_WITH_BRUSH),
			//SHAPES
		new Tool("Shapes", Icons.SHAPES.toString(), PADDING_H + 470, MIDDLE + 8, 24, 24, Actions.SHOW_SHAPE_MENU),
			//SIZE
		new Tool("Sizes", Icons.METER.toString(), PADDING_H + 540, MIDDLE + 8, 24, 24, Actions.SHOW_SIZE_MENU),
			//COLORS
		new Tool("Color scheme", Icons.COLOR_SCHEME.toString(), PADDING_H + 910, MIDDLE + 8, 24, 24, Actions.CHOOSE_COLOR_IN_CHROMA)		
	};
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display() throws SlickException {
		//Data retrieval
		gr = Store.getGr();
		mouseX = Store.getMouseX();
		mouseY = Store.getMouseY();
		
		
		//Background
		gr.setColor(AppColors.SMOOTHGRAY.getColor());
		gr.fillRect(0, TOP, WIDTH, HEIGHT);
		
		//Clipboard
		categoryLabel(gr, "Clipboard", PADDING_H);
		lineDivider(gr, PADDING_H + 105);
		
		//Image
		categoryLabel(gr, "Image", PADDING_H + 130);
		lineDivider(gr, PADDING_H + 220);
		
		//Tools
		categoryLabel(gr, "Tools", PADDING_H + 260);
		lineDivider(gr, PADDING_H + 350);
		
		//Brushes
		categoryLabel(gr, "Brushes", PADDING_H + 362.5f);
		lineDivider(gr, PADDING_H + 440);
		
		//Shapes
		categoryLabel(gr, "Shapes", PADDING_H + 452.5f);
		lineDivider(gr, PADDING_H + 520);
		
		//Size
		categoryLabel(gr, "Size", PADDING_H + 532.5f);
		lineDivider(gr, PADDING_H + 580);
		
		//Colors
		categoryLabel(gr, "Colors", PADDING_H + 715.5f);
		
		//Primary and secondary
		displayMainColor(gr, Store.getPrimaryColor(), TOP + 28);
		displayMainColor(gr, Store.getSecondColor(), MIDDLE + 40);
		
		//Tools
		for(Tool t : tools) {
			(new MouseHoverArea(
				t.getLabel(),
				new Image(t.getImgPath()),
				t.getPosX(),
				t.getPosY(),
				t.getWidth(),
				t.getHeight()
			)).hoverListener();
			
			(new ClickableArea(
				t.getPosX(),
				t.getPosY(),
				t.getWidth(),
				t.getHeight(),
				t.getAction()
			)).clickableListener();
		}
		
		//Colorset
		int margin = 25;
		for(int i=0; i<colorset.length*20; i+=margin) {//Is not really good to look with the 25 value of the margin
			
			for(int j=0; j<colorset[0].length*margin; j+=margin) {
				gr.setColor(colorset[i / margin][j / margin]);
				gr.fillOval(690 + j - 7.5f, TOP + margin + i - 7.5f, 15, 15);
				gr.setColor(AppColors.GRAY.getColor());
				gr.drawOval(690 + j - 7.5f, TOP + margin + i - 7.5f, 15, 15);
				
				(new ClickableArea(
					690 + j - 7.5f,
					TOP + margin + i - 7.5f,
					15,
					15,
					Store.getPreviousAction(),
					colorset[i / margin][j / margin]
				)).clickableListener();
			}
		}
	}
	
	public static void lineDivider(Graphics gr, float x) {
		gr.setColor(new Color(200, 200, 200));
		gr.drawLine(x, TOP + 6, x, BOTTOM - 8);
	}
	
	public static void categoryLabel(Graphics gr, String label, float x) { 
		gr.setColor(AppColors.GRAY.getColor());
		gr.drawString(label, x, BOTTOM - 20);
	}
	
	public static void displayMainColor(Graphics gr, Color color, float y) {
		gr.setColor(color);
		gr.fillOval(635 + 15 - 14, y - 14, 28, 28);
		
		gr.setColor(AppColors.DARKRED.getColor());
		gr.drawOval(635 + 15 - 17.5f, y - 17.5f, 35, 35);
	}
}
