package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.FontUtils;

import constants.Actions;
import constants.AppColors;
import constants.Images;

public class ColorWheel {
	/*
	 * =============================
	 * 			   PROPS 
	 * =============================
	*/
	
	private Image chroma;
	private int red = 255, green = 255, blue = 255;
	private String hexRed, hexGreen, hexBlue;
	private float hue, sat, lum;
	Color[] swatches;
	
	/*
	 * =============================
	 * 			  METHODS 
	 * =============================
	*/
	
	public void colorWheelHandler(float TOP, float LEFT) throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			swatches = Store.swatches;
			int mouseX = Store.mouseX,
				mouseY = Store.mouseY,
				mouseXClick = Store.mouseX,
				mouseYClick = Store.mouseYClick;
			
			
		//Chroma			
			chroma = Images.CHROMATIC_RING.getImage();
			gr.drawImage(chroma, 875, TOP + 10);
		
		//RGB
			gr.setColor(AppColors.TEXT.getColor());
			gr.drawString("R", 875, TOP + chroma.getHeight() + 15);
			gr.drawString("G", 874, TOP + chroma.getHeight() + 32);
			gr.drawString("B", 875, TOP + chroma.getHeight() + 48);
			
			//RGB boxes
				gr.setColor(AppColors.LIGHTGRAY.getColor());
				gr.drawRect(890, TOP + chroma.getHeight() + 17, 30, 15);
				gr.drawRect(890, TOP + chroma.getHeight() + 12 + 15 + 8, 30, 15);
				gr.drawRect(890, TOP + chroma.getHeight() + 12 + 30 + 11, 30, 15);
			
		//HSL
			gr.setColor(AppColors.TEXT.getColor());
			gr.drawString("H", 935, TOP + chroma.getHeight() + 15);
			gr.drawString("S", 935, TOP + chroma.getHeight() + 32);
			gr.drawString("L", 935, TOP + chroma.getHeight() + 48);
			
			//HSL boxes
				gr.setColor(AppColors.LIGHTGRAY.getColor());
				gr.drawRect(950, TOP + chroma.getHeight() + 17, 35, 15);
				gr.drawRect(950, TOP + chroma.getHeight() + 12 + 15 + 8, 35, 15);
				gr.drawRect(950, TOP + chroma.getHeight() + 12 + 30 + 11, 35, 15);
		
		//Hex
			gr.setColor(AppColors.TEXT.getColor());
			gr.drawString("Hex", 1010, TOP + chroma.getHeight() + 17);
		
		//Hex box
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRect(995, TOP + chroma.getHeight() + 35, 55, 15);
			
		for(int i=0, x; i<swatches.length; i++) {
			x = ( i > (swatches.length/2)-1 ) ? i - swatches.length/2 : i;
			
			//Color
				gr.setColor(swatches[i]);
				gr.fillRect(
							890 + x*35,
							TOP + chroma.getHeight() + 83 + ((i > (swatches.length/2)-1) ? 20 : 0), 
							15, 15
						);
			
			//Border
				gr.setColor(AppColors.LIGHTGRAY.getColor());
				gr.drawRect(
							890 + x*35, 
							TOP + chroma.getHeight() + 83 + ((i > (swatches.length/2)-1) ? 20 : 0), 
							15, 15
						);
		}
		
			
		if( 
			(0 < mouseX - LEFT && mouseX - LEFT < chroma.getWidth()) && 
			(0 < mouseY - (TOP + 10) && mouseY - (TOP + 10) < chroma.getHeight())
		) {
			//Tooltip
				gr.setColor( chroma.getColor( (int)(mouseX-LEFT), (int)(mouseY - (TOP + 10)) ) );
				gr.fillRect(mouseX + 10, mouseY - 10, 10, 10);
				//Border
					gr.setColor(AppColors.BLACK.getColor());
					gr.drawRect(mouseX+10, mouseY-10, 10, 10);
					
			
			//RGB Values
				red = chroma.getColor( (int)(mouseX-LEFT), (int)(mouseY - (TOP + 10)) ).getRed();
				green = chroma.getColor( (int)(mouseX-LEFT), (int)(mouseY - (TOP + 10)) ).getGreen();
				blue = chroma.getColor( (int)(mouseX-LEFT), (int)(mouseY - (TOP + 10)) ).getBlue();
				
				FontUtils.drawCenter(gr.getFont(), 
							"" + red,
							890, (int)(TOP + chroma.getHeight() + 17), 
							30, AppColors.TEXT.getColor()
						);
				
				FontUtils.drawCenter(
						gr.getFont(), 
						"" + green,
						890, (int)(TOP + chroma.getHeight() + 12 + 15 + 7), 
						30, AppColors.TEXT.getColor()
					);
				
				FontUtils.drawCenter(
						gr.getFont(), 
						"" + blue,
						890, (int)(TOP + chroma.getHeight() + 12 + 30 + 10), 
						30, AppColors.TEXT.getColor()
					);

			//HSL
				float R = red/255f, G = green/255f, B = blue/255f;
				float Cmax = colorMax(R, G, B), Cmin = colorMin(R, G, B);
				float delta = Cmax - Cmin; 
				
				hue = (delta == 0) ? 0 
					: (Cmax == R) ?  (float)Math.floor( 60 * ( ((G - B)/delta)%6 ) )
					: (Cmax == G) ? (float)Math.floor( 60 * ( ((B - R)/delta)+2 ) )
					: (float)Math.floor( 60 * ( ((R - G)/delta)+4 ) );
				
				sat = ( (Cmax + Cmin) == 0) ? 0 
					: (float)Math.floor( (delta / (1 - Math.abs( (Cmax + Cmin) - 1 )))*100 ) ;
				
				lum = (float)Math.floor( ((Cmax + Cmin) / 2 ) * 100 );
				
				FontUtils.drawCenter(
						gr.getFont(),
						"" + (int)((hue<0) ? hue+360 : hue) + "°",
						950, (int)(TOP + chroma.getHeight() + 17), 
						35, AppColors.TEXT.getColor()
					);
				
				FontUtils.drawCenter(
						gr.getFont(),
						"" + (int)sat + "%",
						950, (int)(TOP + chroma.getHeight() + 12 + 15 + 8), 
						35, AppColors.TEXT.getColor()
					);
				
				FontUtils.drawCenter(
						gr.getFont(),
						"" + (int)lum + "%",
						950, (int)(TOP + chroma.getHeight() + 12 + 30 + 11), 
						35, AppColors.TEXT.getColor()
					);
			
			//Hex Value
				hexRed = (red == 0) ? "00" : Integer.toHexString(red);
				hexGreen = (green == 0) ? "00" : Integer.toHexString(green);
				hexBlue = (blue == 0) ? "00" : Integer.toHexString(blue);
				
				FontUtils.drawCenter(
						gr.getFont(), 
						"#"+ hexRed + hexGreen + hexBlue, 
						995, (int)(TOP + chroma.getHeight() + 35), 
						55, AppColors.TEXT.getColor()
					);
				
			if( 
				(0 < mouseXClick-LEFT && mouseXClick-LEFT < chroma.getWidth()) &&	
				(0 < mouseYClick - (TOP + 10) && mouseYClick - (TOP + 10) < chroma.getHeight())
			) {
				Store.setCurrentAction(Actions.CHOOSE_COLOR_IN_CHROMA);
			}
		}
		
		if( Store.currentAction == Actions.CHOOSE_COLOR_IN_CHROMA && Store.isClicking ) {
			Store.setSwatches(new Color(red, green, blue));
			Store.setCurrentAction(Actions.SHOW_CHROMATIC_MENU);
		}
	}
	
	private float colorMax(float r, float g, float b) {
		float CMax = r;
		
		if(CMax <= g)
			CMax = g;
		if(CMax <= b) 
			CMax = b;
		
		return CMax;
	}
	
	private float colorMin(float r, float g, float b) {
		float CMin = r;
		
		if(CMin >= g)
			CMin = g;
		if(CMin >= b)
			CMin = b;
		
		return CMin;
	}
	
}
