package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.FontUtils;

import components.Store;
import components.Tool;
import constants.*;
import gui.*;

/**
 * 
 * Responsible for rendering all the tools in the application
 * 
 */

public abstract class Toolkit {
	
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
				Store.swatches
			};
	
	private static Tool[] tools = {
			//CLIPBOARD
		new Tool("Cut", Icons.SMALL_CUT.toString(), PADDING_H + 65, TOP + 17, 16, 16, Actions.CUT),
		new Tool("Paste", Icons.CLIPBOARD.toString(), PADDING_H, MIDDLE + 3, 32, 32, Actions.PASTE),
		new Tool("Copy", Icons.SMALL_COPY.toString(), PADDING_H + 65, MIDDLE + 27, 16, 16, Actions.COPY),
			//IMAGE
		new Tool("Crop", Icons.SMALL_CROP.toString(), PADDING_H + 190, TOP + 15, 16, 16, Actions.CROP),
		new Tool("Rotate", Icons.SMALL_ROTATE.toString(), PADDING_H + 157, TOP + 15, 16, 16, Actions.SHOW_ROTATE_MENU),
		new Tool("Select", Icons.SELECT.toString(), PADDING_H + 115, MIDDLE + 3, 32, 32, Actions.SELECT),
		new Tool("Picture", Icons.SMALL_PICTURE.toString(), PADDING_H + 157, MIDDLE + 37, 16, 16, Actions.OPEN_FILE_CHOOSER),
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
		new Tool("Color scheme", Icons.COLOR_SCHEME.toString(), PADDING_H + 910, MIDDLE + 8, 24, 24, Actions.SHOW_CHROMATIC_MENU)		
	};
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	
	public static void display() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			
		//Background
			gr.setColor(AppColors.SMOOTHGRAY.getColor());
			gr.fillRect(0, TOP, WIDTH, HEIGHT);
			
		//Separator
			sectionSeparator(gr, Sizes.SETTING_HEIGHT.getSize());
		
		//Clipboard
			FontUtils.drawCenter(
				gr.getFont(),
				"Clipboard", 
				0, (int)BOTTOM - 20, 
				(int)(PADDING_H+105 - 0), 
				AppColors.GRAY.getColor()
			);
			
			lineDivider(gr, PADDING_H + 105);
			
		
		//Image
			FontUtils.drawCenter(
				gr.getFont(), 
				"Image", 
				(int)(PADDING_H + 105), (int)BOTTOM - 20, 
				(int)(220 - 105), AppColors.GRAY.getColor()
			);
			
			lineDivider(gr, PADDING_H + 220);
		
		//Tools
			FontUtils.drawCenter(
				gr.getFont(),
				"Tools", 
				(int)(PADDING_H +220), (int)BOTTOM - 20, 
				(int)(350 - 220), AppColors.GRAY.getColor()
			);
			
			lineDivider(gr, PADDING_H + 350);
		
		//Brushes
			FontUtils.drawCenter(
				gr.getFont(), 
				"Brushes", 
				(int)(PADDING_H + 350), (int)BOTTOM - 20, 
				(int)(440 - 350), AppColors.GRAY.getColor()
			);
			
			lineDivider(gr, PADDING_H + 440);
		
		//Shapes
			FontUtils.drawCenter(
				gr.getFont(), 
				"Shapes", 
				(int)(PADDING_H + 440), (int)BOTTOM - 20, 
				(int)(520 - 440), AppColors.GRAY.getColor()
			);
			
			lineDivider(gr, PADDING_H + 520);
		
		//Size
			FontUtils.drawCenter(
				gr.getFont(), 
				"Sizes", 
				(int)(PADDING_H + 520), (int)BOTTOM - 20, 
				(int)(580 - 520), AppColors.GRAY.getColor()
			);
			
			lineDivider(gr, PADDING_H + 580);
		
		//Colors
			FontUtils.drawCenter(
				gr.getFont(), 
				"Colors", 
				(int)(PADDING_H + 580), (int)BOTTOM - 20, 
				(int)(910 - 580), AppColors.GRAY.getColor()
			);
		
		//Primary and secondary
			displayMainColor(gr, Store.primaryColor, TOP + 28);
			displayMainColor(gr, Store.secondColor, MIDDLE + 40);
		
		//Tools
			for(Tool t : tools) {
				(new ActionLauncher(
					t.getLabel(),
					new Image( t.getImgPath() ),
					t.getPosX(), t.getPosY(),
					t.getWidth(), t.getHeight(),
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
					
					(new ValueSetter(
						"",
						690 + j - 7.5f, TOP + margin + i - 7.5f,
						15, 15,
						Actions.CHOOSE_A_COLOR,
						colorset[i / margin][j / margin]
					))._clickableListener();
				}
			}
	}
	
	private static void lineDivider(Graphics gr, float x) {
		gr.setColor(new Color(200, 200, 200));
		gr.drawLine(x, TOP + 6, x, BOTTOM - 8);
	}
	
	private static void sectionSeparator(Graphics gr, float y) {
		gr.setColor(new Color(230, 230, 230));
		gr.drawLine(0, y, Sizes.SCREEN_DEFAULT_WIDTH.getSize(), y);
	}
	
	private static void displayMainColor(Graphics gr, Color color, float y) {
		gr.setColor(color);
		gr.fillOval(635 + 15 - 14, y - 14, 28, 28);
		
		gr.setColor(AppColors.DARKRED.getColor());
		gr.drawOval(635 + 15 - 17.5f, y - 17.5f, 35, 35);
	}
}
