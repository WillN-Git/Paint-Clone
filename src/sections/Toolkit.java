package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Tool;
import constants.AppColors;
import constants.Icons;
import constants.Sizes;
import gui.MouseHoverArea;


public class Toolkit {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.TOOLKIT_HEIGHT.getSize();
	
	private float PADDING_H = 25;
	
	private float TOP = Sizes.SETTING_HEIGHT.getSize(),
				MIDDLE = TOP + 40 - 10,
				BOTTOM = Sizes.SETTING_HEIGHT.getSize() + HEIGHT;
	
	private Color[][] colorset = {
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
	
	private Tool[] tools = {
			//CLIPBOARD
		new Tool(Icons.SMALL_CUT.toString(), PADDING_H + 65, TOP + 17, 16, 16),
		new Tool(Icons.CLIPBOARD.toString(), PADDING_H, MIDDLE + 3, 32, 32),
		new Tool(Icons.SMALL_COPY.toString(), PADDING_H + 65, MIDDLE + 27, 16, 16),
			//IMAGE
		new Tool(Icons.SMALL_CROP.toString(), PADDING_H + 190, TOP + 15, 16, 16),
		new Tool(Icons.SMALL_ROTATE.toString(), PADDING_H + 157, TOP + 15, 16, 16),
		new Tool(Icons.SELECT.toString(), PADDING_H + 115, MIDDLE + 3, 32, 32),
		new Tool(Icons.SMALL_PICTURE.toString(), PADDING_H + 157, MIDDLE + 37, 16, 16),
		new Tool(Icons.SMALL_FLIP.toString(), PADDING_H + 190, MIDDLE + 37, 16, 16),
			//TOOLS
		new Tool(Icons.PENCIL.toString(), PADDING_H + 235, TOP + 8, 24, 24),
		new Tool(Icons.ERASER.toString(), PADDING_H + 235, MIDDLE + 27, 24, 24),
		new Tool(Icons.FILL.toString(), PADDING_H + 273, TOP + 9, 24, 24),
		new Tool(Icons.COLOR_PICKER.toString(), PADDING_H + 275, MIDDLE + 28, 24, 24),
		new Tool(Icons.TEXT.toString(), PADDING_H + 310, TOP + 9.5f, 24, 24),
		new Tool(Icons.ZOOM.toString(), PADDING_H + 310, MIDDLE + 27, 24, 24),
			//BRUSHES
		new Tool(Icons.BRUSH.toString(), PADDING_H + 385, MIDDLE + 8, 24, 24),
			//SHAPES
		new Tool(Icons.SHAPES.toString(), PADDING_H + 470, MIDDLE + 8, 24, 24),
			//SIZE
		new Tool(Icons.METER.toString(), PADDING_H + 540, MIDDLE + 8, 24, 24),
			//COLORS
		new Tool(Icons.COLOR_SCHEME.toString(), PADDING_H + 910, MIDDLE + 8, 24, 24)		
	};
	
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr, float mouseX, float mouseY) throws SlickException {
		//Background
		gr.setColor(AppColors.SMOOTHGRAY.getColor());
		gr.fillRect(0, Sizes.SETTING_HEIGHT.getSize(), WIDTH, HEIGHT);
		
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
		
		gr.setColor(AppColors.BLACK.getColor());
		gr.fillOval(635 + 15 - 14f, TOP + 28 - 14f, 28, 28);
		
		gr.setColor(AppColors.WHITE.getColor());
		gr.fillOval(635 + 15 - 14, MIDDLE + 40 - 14, 28, 28);
		
		
		gr.setColor(AppColors.DARKRED.getColor());
		gr.drawOval(635 + 15 - 17.5f, TOP + 28 - 17.5f, 35, 35);
		gr.drawOval(635 + 15 - 17.5f, MIDDLE + 40 - 17.5f, 35, 35);
		
		//Tools
		for(int i=0; i<tools.length; i++) {
			MouseHoverArea m = new MouseHoverArea(
					gr,
					new Image(tools[i].getImgPath()),
					tools[i].getPosX(),
					tools[i].getPosY(),
					tools[i].getWidth(),
					tools[i].getHeight()
			);
			
			m.hoverListener(mouseX, mouseY);
		}
		
		//Colorset
		for(int i=0; i<colorset.length*20; i+=25) {
			for(int j=0; j<colorset[0].length*25; j+=25) {
				gr.setColor(colorset[i / 25][j / 25]);
				gr.fillOval(690 + j - 7.5f, TOP + 25 + i - 7.5f, 15, 15);
				gr.setColor(AppColors.GRAY.getColor());
				gr.drawOval(690 + j - 7.5f, TOP + 25 + i - 7.5f, 15, 15);
			}
		}
	}
	
	public void lineDivider(Graphics gr, float x) {
		gr.setColor(new Color(200, 200, 200));
		gr.drawLine(x, TOP + 6, x, BOTTOM - 8);
	}
	
	public void categoryLabel(Graphics gr, String label, float x) { 
		gr.setColor(AppColors.GRAY.getColor());
		gr.drawString(label, x, BOTTOM - 20);
	}
}
