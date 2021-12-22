package sections;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import constants.AppColors;
import constants.Icons;
import constants.Sizes;


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
				
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public void display(Graphics gr) throws SlickException {
		//Background
		gr.setColor(AppColors.SMOOTHGRAY.getColor());
		gr.fillRect(
			0,
			Sizes.SETTING_HEIGHT.getSize(),
			WIDTH,
			HEIGHT
		);
		
		//Clipboard
		categoryLabel(gr, "Clipboard", PADDING_H);
		
		gr.drawImage(new Image(Icons.CUT.toString()), PADDING_H + 60, TOP + 8);
		gr.drawImage(new Image(Icons.CLIPBOARD.toString()), PADDING_H, MIDDLE + 3);
		gr.drawImage(new Image(Icons.COPY.toString()), PADDING_H + 60, MIDDLE + 27);
		
		
		lineDivider(gr, PADDING_H + 110);
		
		//Image
		categoryLabel(gr, "Image", PADDING_H + 130);
		
		gr.drawImage(new Image(Icons.CROP.toString()), PADDING_H + 180, TOP + 8);
		gr.drawImage(new Image(Icons.SELECT.toString()), PADDING_H + 130, MIDDLE + 3);
		
		gr.drawImage(new Image(Icons.FLIP.toString()), PADDING_H + 180, MIDDLE + 27);
		gr.drawImage(new Image(Icons.ROTATE.toString()), PADDING_H + 130, MIDDLE + 3);
		
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
		categoryLabel(gr, "Colors", PADDING_H + 732.5f);
		
	}
	
	public void lineDivider(Graphics gr, float x) {
		gr.setColor(new Color(200, 200, 200));
		gr.drawLine(x, TOP + 6, x, BOTTOM - 8);
	}
	
	public void categoryLabel(Graphics gr, String label, float x) { 
		gr.setColor(AppColors.TEXT.getColor());
		gr.drawString(label, x, BOTTOM - 20);
	}
}
