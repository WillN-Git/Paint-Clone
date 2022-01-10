package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.FontUtils;

import components.Item;
import components.Store;
import components.Tool;
import constants.Actions;
import constants.AppColors;
import constants.Icons;
import constants.Images;
import constants.Sizes;

public class MenuManager {
	
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	//Helpers
		private static Item[] fileItems = {
			new Item(null, "New", "Ctrl+N", Actions.NONE),
			new Item(null, "Open", "Ctrl+O", Actions.NONE),
			new Item(null, "Recent", "     >", Actions.NONE),
			new Item(null, "Save", "Ctrl+S", Actions.NONE),
			new Item(null, "Save as", "", Actions.NONE),
			new Item(null, "Print", "     >", Actions.NONE),
			//new Item(null, "From scanner or camera", ">"),
			new Item(null, "Send", "     >", Actions.NONE),
			//new Item(null, "Set as Desktop Background", ">"),
			new Item(null, "Image properties", "Ctrl+E", Actions.NONE),
			new Item(null, "Settings", "", Actions.NONE)
		};
	
		private static Item[] viewItems = {
			new Item(null, "100 %", "", Actions.NONE),
			new Item(null, "Rulers", "Ctrl+R", Actions.SHOW_RULER),
			new Item(null, "Gridlines", "Ctrl+G", Actions.SHOW_GRIDLINES),
			new Item(null, "Status Bar", "Ctrl+B", Actions.SHOW_STATUSBAR),
			new Item(null, "FullScreen", "", Actions.NONE)
		};
	
		private static Tool[] shapes = {
			new Tool("Line", Icons.BW_LINE.toString(), 0, 0, 16, 16, Actions.DRAW_LINE),
			new Tool("Curve", Icons.BW_CURVE.toString(), 0, 0, 16, 16, Actions.DRAW_CURVE),
			new Tool("Circle", Icons.BW_OVAL.toString(), 0, 0, 16, 16, Actions.DRAW_ELLIPSE),
			new Tool("Rectangle", Icons.BW_RECTANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_RECTANGLE),
			new Tool("Round Rectangle", Icons.BW_ROUND_RECTANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_ROUNDED_RECTANGLE),
			new Tool("Polygon", Icons.BW_POLYGON.toString(), 0, 0, 16, 16, Actions.DRAW_POLYGON),
			new Tool("Triangle", Icons.BW_TRIANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_TRIANGLE),
			new Tool("Right triangle", Icons.BW_RIGHT_TRIANGLE.toString(), 0, 0, 16, 16, Actions.DRAW_RIGHT_TRIANGLE),
			new Tool("Pentagon", Icons.BW_PENTAGON.toString(), 0, 0, 16, 16, Actions.DRAW_PENTAGON),
			new Tool("Hexagon", Icons.BW_HEXAGON.toString(), 0, 0, 16, 16, Actions.DRAW_HEXAGON)	
		};
		
		private String[][] sizes = {
			{Images.PX_1.toString(), "1"},
			{Images.PX_3.toString(), "3"},
			{Images.PX_5.toString(), "5"},
			{Images.PX_8.toString(), "10"}
		};
		
	
	/*
	 * =============================
	 * 			  METHODS 
	 * =============================
	*/
	
	public void displayMenu() throws SlickException {
		switch( Store.currentAction ) {
			case SHOW_FILE_MENU :
				showFileMenu();
				break;
			case SHOW_VIEW_MENU :
				showViewMenu();
				break;
			case SHOW_SHAPE_MENU :
				if(Store.previousAction != Actions.CHOOSE_A_COLOR)
					showShapeMenu();
				break;
			case SHOW_SIZE_MENU : 
				showSizeMenu();
				break;
			case SHOW_CHROMATIC_MENU :
				showChromaticMenu();
				break;
		}
	}
	
	public void showChromaticMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			Color[] swatches = Store.swatches;
			int mouseX = Store.mouseX,
				mouseY = Store.mouseY,
				mouseXClick = Store.mouseXClick,
				mouseYClick = Store.mouseYClick;
			
		//Init
			float TOP = Sizes.SETTING_HEIGHT.getSize() + 70,
				  LEFT = 875;
			int red = 255, green = 255, blue = 255;
			
		//Background
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(860, TOP, 200, 300, 8);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(860, TOP, 200, 300, 8);
			
		//Chroma			
			Image chroma = Images.CHROMATIC_RING.getImage();
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
				gr.drawRect(955, TOP + chroma.getHeight() + 17, 30, 15);
				gr.drawRect(955, TOP + chroma.getHeight() + 12 + 15 + 8, 30, 15);
				gr.drawRect(955, TOP + chroma.getHeight() + 12 + 30 + 11, 30, 15);
		
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

				
				//Hex Value
					String hexRed = (red == 0) ? "00" : Integer.toHexString(red),
						hexGreen = (green == 0) ? "00" : Integer.toHexString(green),
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
	
	public void showSizeMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(565, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * 4, 90, 5);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(565, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * 4, 90, 5);
		
		int y = 0;
		for(String[] ts : sizes) {
			(new MouseHoverArea(
					"",
					new Image(ts[0]),
					585,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					98,
					10
			)).hoverListener();
			
			(new ClickableArea(
					585,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					98,
					10,
					Actions.CHOOSE_SIZE,
					Integer.parseInt(ts[1])
			)).clickableListener();
			y += 22;
		}
	}
	
	public void showShapeMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
		
		int numberPerColumn = 4;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(485, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * numberPerColumn, 25 * 3, 5);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(485, Sizes.SETTING_HEIGHT.getSize() + 70, 35 * numberPerColumn, 25 * 3, 5);
		
		int x=0, y=0, i=0;
		for(Tool t : shapes) {
			(new MouseHoverArea(
					t.getLabel(),
					new Image(t.getImgPath()),
					495 + x,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					t.getWidth(),
					t.getHeight()
			)).hoverListener();
			
			(new ClickableArea(
					495 + x,
					Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					t.getWidth(),
					t.getHeight(),
					t.getAction()
			)).clickableListener();
			
			i++;
			x = (i % 4 == 0) ? 0 : x + 35;
			y += (i % 4 == 0) ? 25 : 0;
		}
	}
	
	public void showFileMenu() {
		//Data retrieval
			Graphics gr = Store.gr;
		
		int heightOfItem = 30;
		int numberOfItems = fileItems.length;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(
				10 - 4, 
				22 + 20 - 3, 
				300,//width 
				numberOfItems * heightOfItem, 
				5
			);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(
				10 - 4, 
				22 + 20 - 3, 
				300,//width 
				numberOfItems * heightOfItem, 
				5
			);
			
		int y=0;
		for(Item i : fileItems) {
			i.displayItem(gr, 10 + 5, 15 + 20 + 10 + y, 10 + 300);
			y+= heightOfItem;
		}
	}
	
	public void showViewMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
		
		int heightOfItem = 30;
		int numberOfItems = viewItems.length;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fillRoundRect(
				10 + 50 - 4, 
				22 + 20 - 3, 
				300,//width 
				numberOfItems * heightOfItem, 
				5
			);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.drawRoundRect(
					10 + 50 - 4, 
					22 + 20 - 3, 
					300,//width 
					numberOfItems * heightOfItem, 
					5
				);
			
		int y=0;
		for(Item i : viewItems) {
			i.displayItem(gr, 10 + 50 + 5, 15 + 20 + 10 + y, 10 + 50 + 300);
			
			(new ClickableArea(
				10 + 50 + 5,
				15 + 20 + 10 + y,
				(10 + 50 + 300) - (10 + 50 + 5),
				heightOfItem,
				i.getAction()
			)).clickableListener();
			
			y+= heightOfItem;
		}
	}
}
