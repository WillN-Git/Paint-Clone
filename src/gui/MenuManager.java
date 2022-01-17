package gui;

import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.util.FontUtils;

import components.ColorWheel;
import components.Item;
import components.Store;
import components.Tool;
import constants.*;

public class MenuManager {
	
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	//Helpers
		private ColorWheel colorWheel = new ColorWheel();
		
		private Item[] fileItems = {
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
	
		private Item[] viewItems = {
			new Item(null, "100 %", "", Actions.NONE),
			new Item(null, "Rulers", "Ctrl+R", Actions.SHOW_RULER),
			new Item(null, "Gridlines", "Ctrl+G", Actions.SHOW_GRIDLINES),
			new Item(null, "Status Bar", "Ctrl+B", Actions.SHOW_STATUSBAR),
			new Item(null, "FullScreen", "", Actions.NONE)
		};
	
		private Tool[] shapes = {
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
		
		private Item[] rotateItems = {
			new Item(null, "Rotate right 90°", "", Actions.ROTATE_RIGHT_90),
			new Item(null, "Rotate left 90°", "", Actions.ROTATE_LEFT_90),
			new Item(null, "Rotate 180°", "", Actions.ROTATE_TO_180)
		};
								 		
	
	/*
	 * =============================
	 * 			  METHODS 
	 * =============================
	*/
	
	public void displayMenu() throws SlickException {
		if(Store.previousAction != Actions.CHOOSE_A_COLOR) {
			switch( Store.currentAction ) {
				case SHOW_FILE_MENU : 		showFileMenu();			break;
				case SHOW_VIEW_MENU : 		showViewMenu();			break;
				case SHOW_SHAPE_MENU : 		showShapeMenu();		break;
				case SHOW_SIZE_MENU :		showSizeMenu(); 		break;
				case SHOW_CHROMATIC_MENU : 	showChromaticMenu();	break;
				case WRITE : 				showWriteMenu(); 		break;
				case SHOW_ROTATE_MENU : 	showRotateMenu();		break;
			}
		}
	}
	
	public void showFontSizeMenu() {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle shapeHitbox = new RoundedRectangle(
					485, 
					Sizes.SETTING_HEIGHT.getSize() + 70,
					35 * 4, 
					25 * 3,
					5
				);
			
		//Background
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill(shapeHitbox);
			
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(shapeHitbox);
	}
	
	public void showRotateMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle rotateHitbox = new RoundedRectangle(
					25 + 157, 
					Sizes.SETTING_HEIGHT.getSize() + 35, 
					150, 
					rotateItems.length * 30, 
					5
			);
			 
			
		//Init
			int heightOfItem = 30;
			
		//Background
			gr.setColor( AppColors.DARKWHITE.getColor() );
			gr.fill(rotateHitbox);
			
		//Border
			gr.setColor( AppColors.LIGHTGRAY.getColor() );
			gr.draw(rotateHitbox);
			
		//Items
			int y = 0;
			for(Item r : rotateItems) {
				r.displayItem(
						gr, rotateHitbox.getX(),
						rotateHitbox.getY() + 5 + y, 
						rotateHitbox.getX() +  rotateHitbox.getWidth()
					);
				y += heightOfItem;
			}
	}
	
	public void showWriteMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle writeHitbox = new RoundedRectangle(350,Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize(), 400, 50, 5),
							 fontFamilyBox = new RoundedRectangle(writeHitbox.getX() + 10, writeHitbox.getY() + 10, 150, 30, 4),
							 fontSizeBox = new RoundedRectangle(writeHitbox.getX() + 180, writeHitbox.getY() + 10, 80, 30, 4);
			
		//Background
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill(writeHitbox);
			
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(writeHitbox);
			
		//FontFamilySelector
			gr.setColor( AppColors.WHITE.getColor() );
			gr.fill( fontFamilyBox );
			
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw( fontFamilyBox );
			
			FontUtils.drawCenter(
				new TrueTypeFont(new Font(Store.currentFontFamilyName, Font.BOLD, 14), true),
				Store.currentFontFamilyName,
				(int)fontFamilyBox.getX() - 10,
				(int)fontFamilyBox.getY() + 6,
				(int)fontFamilyBox.getWidth(),
				AppColors.TEXT.getColor()
			);
			
			FontUtils.drawRight(
				gr.getFont(),
				"+",
				(int)fontFamilyBox.getX() - 10,
				(int)fontFamilyBox.getY() + 5,
				(int)fontFamilyBox.getWidth(),
				AppColors.TEXT.getColor()
			);
			
			(new ValueSetter(
					"",
					(int)fontFamilyBox.getX(),
					(int)fontFamilyBox.getY(),
					(int)fontFamilyBox.getWidth(),
					(int)fontFamilyBox.getHeight(),
					Actions.SHOW_FONT_FAMILY_MENU
			))._clickableListener();
			
			if( Store.showFontFamilyMenu )
				handleFontFamilyMenu();
			
		//FontSizeSelector
			gr.setColor(AppColors.WHITE.getColor());
			gr.fill(fontSizeBox);
			
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(fontSizeBox);
			
			FontUtils.drawCenter(
				gr.getFont(),
				"" + Store.currentFontSize,
				(int)fontSizeBox.getX(),
				(int)fontSizeBox.getY() + 5,
				(int)fontSizeBox.getWidth(),
				AppColors.TEXT.getColor()
			);
			
			(new ValueSetter(
					"",
					(int)fontSizeBox.getX(),
					(int)fontSizeBox.getY(),
					(int)fontSizeBox.getWidth(),
					(int)fontSizeBox.getHeight(),
					Actions.SHOW_FONT_SIZE_MENU
			))._clickableListener();
			
		if( Store.showFontSizeMenu )
			handleFontSizeMenu();
			
		//FontPlacement
			(new ValueSetter(
				"",
				Icons.BW_LEFT_ALIGN.getIcon(),
				fontSizeBox.getX() + 100, writeHitbox.getY() + 13,
				24, 24,
				Actions.PUT_TEXT_AT_THE_LEFT
			)).clickableListener();
			
			(new ValueSetter(
				"",
				Icons.BW_CENTER_ALIGN.getIcon(),
				fontSizeBox.getX() + 100 + 42, writeHitbox.getY() + 13,
				24, 24,
				Actions.CENTERING_THE_TEXT
			)).clickableListener();
			
			(new ValueSetter(
				"",
				Icons.BW_RIGHT_ALIGN.getIcon(),
				fontSizeBox.getX() + 100 + 84, writeHitbox.getY() + 13,
				24, 24,
				Actions.PUT_TEXT_AT_THE_RIGHT
			)).clickableListener();
			
			Store.gr.setColor(AppColors.TRANSPARENTGRAY.getColor());
			switch( Store.textAlign ) {
				case 'L' :
					gr.fillRoundRect(
							fontSizeBox.getX() + 100 - 4, 
							writeHitbox.getY() + 13 - 4, 
							32, 
							32, 
							2
					);
					break;
				case 'C' :
					gr.fillRoundRect(
							fontSizeBox.getX() + 100  + 42- 4, 
							writeHitbox.getY() + 13 - 4, 
							32, 
							32, 
							2
					);
					break;
				case 'R' :
					gr.fillRoundRect(
							fontSizeBox.getX() + 100 + 84 - 4, 
							writeHitbox.getY() + 13 - 4, 
							32, 
							32, 
							2
					);
					break;
			}
	}
	
	public void showChromaticMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle chromaticHitbox = new RoundedRectangle(
					860,
					Sizes.SETTING_HEIGHT.getSize() + 70,
					200,
					300,
					8
				);
					 
			
		//Init
			float TOP = Sizes.SETTING_HEIGHT.getSize() + 70,
				  LEFT = 875;
			
		//Background
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill(chromaticHitbox);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(chromaticHitbox);
			
		
		colorWheel.colorWheelHandler(TOP, LEFT);
	}
	
	
	public void showSizeMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle sizeHitbox = new RoundedRectangle(
					565, 
					Sizes.SETTING_HEIGHT.getSize() + 70, 
					35 * 4, 
					90, 
					5
				);
					 
			
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill(sizeHitbox);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(sizeHitbox);
		
		int y = 0;
		for(String[] ts : sizes) {
			(new ValueSetter(
					"",
					new Image(ts[0]),
					585, Sizes.SETTING_HEIGHT.getSize() + 73 + y,
					98, 10,
					Actions.CHOOSE_SIZE,
					Integer.parseInt(ts[1])
			)).clickableListener();
			y += 22;
		}
	}
	
	public void showShapeMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle shapeHitbox = new RoundedRectangle(
					485, 
					Sizes.SETTING_HEIGHT.getSize() + 70,
					35 * 4, 
					25 * 3,
					5
				);
					 
			
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill(shapeHitbox);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(shapeHitbox);
		
		int x=0, y=0, i=0;
		for(Tool t : shapes) {
			(new ActionLauncher(
					t.getLabel(),
					new Image(t.getImgPath()),
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
	
	
	public void showFileMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle fileHitbox = new RoundedRectangle(
					10 - 4, 
					22 + 20 - 3, 
					300, 
					fileItems.length * 30,
					5
			);
		
		int heightOfItem = 30;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill(fileHitbox);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(fileHitbox);
			
		int y=0;
		for(Item i : fileItems) {
			i.displayItem(gr, 10 + 5, 15 + 20 + 10 + y, 10 + 300);
			y+= heightOfItem;
		}
	}
	
	
	public void showViewMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			RoundedRectangle viewHitbox = new RoundedRectangle(
					10 + 50 - 4,
					22 + 20 - 3, 
					300, 
					viewItems.length * 30, 
					5
			);
					 
			
		int heightOfItem = 30;
		
		//Container
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill(viewHitbox);
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw(viewHitbox);
			
		int y=0;
		for(Item i : viewItems) {
			i.displayItem(gr, 10 + 50 + 5, 15 + 20 + 10 + y, 10 + 50 + 300);
			y+= heightOfItem;
		}	
	}
	
	private void handleFontFamilyMenu() throws SlickException {
		//Data retrieval
			Graphics gr = Store.gr;
			String[] fontFamilyNames = Store.fontFamilyNames;
			
			RoundedRectangle writeHitbox = new RoundedRectangle(
					350,
					Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize(),
					400, 
					50, 
					5
				),
					 
			fontHitbox = new RoundedRectangle(
					writeHitbox.getX() + 10, 
					writeHitbox.getY() + 40, 
					150, fontFamilyNames.length * 30, 
					4
				);
			
		
		gr.drawImage(
			Images.FONTFAMILYMENU.getImage(), 
			writeHitbox.getX() + 10, 
			writeHitbox.getY() + 40
		);
			
		int i = 0;
		for(String ff : fontFamilyNames) {
			(new ValueSetter(
				"",
				(int)fontHitbox.getX() + 10, 
				(int)fontHitbox.getY() + i*30 + 5, 
				(int)fontHitbox.getWidth() - 20, 
				15,
				Actions.CHOOSE_FONT_FAMILY,
				ff
			)).clickableListener();
			
			i++;
		}
	}
	

	private void handleFontSizeMenu() throws SlickException {
		//Data retrieval
		Graphics gr = Store.gr;
		String[] fontFamilyNames = Store.fontFamilyNames;
		int mouseX = Store.mouseX,
			mouseY = Store.mouseY;
		
		int[] sizes = {5, 10, 12, 14, 16, 18, 20, 26, 28, 32, 40};
		
		RoundedRectangle writeHitbox = new RoundedRectangle(
				350,
				Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize(),
				400, 
				50, 
				5
			),
				
		fontSizeBox = new RoundedRectangle(
				writeHitbox.getX() + 180,
				writeHitbox.getY() + 10,
				80,
				30,
				4
			),
				 
		fontSizeHitbox = new RoundedRectangle(
				fontSizeBox.getX(), 
				fontSizeBox.getY() + 35, 
				80, 
				sizes.length * 30,
				4
		);
		
		//Background
			gr.setColor(AppColors.DARKWHITE.getColor());
			gr.fill( fontSizeHitbox );
		
		//Border
			gr.setColor(AppColors.LIGHTGRAY.getColor());
			gr.draw( fontSizeHitbox );
		
		int i=0;
		for(int s : sizes) {
			FontUtils.drawCenter(
					gr.getFont(), 
					"" + s,
					(int)fontSizeHitbox.getX() + 8, 
					(int)fontSizeHitbox.getY() + 5 + i * 30, 
					(int)fontSizeBox.getWidth() - 16, 
					AppColors.TEXT.getColor()
				);
			
			(new ValueSetter(
					"",
					(int)fontSizeHitbox.getX() + 8, 
					(int)fontSizeHitbox.getY() + 5 + i * 30, 
					(int)fontSizeBox.getWidth() - 16, 
					15,
					Actions.CHOOSE_FONT_SIZE,
					s
			)).clickableListener();
			
			i++;
		}
	}
}
