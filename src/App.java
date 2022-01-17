import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;

import components.FileChooser;
import components.Store;
import constants.*;
import gui.MenuManager;
import gui.MouseHoverArea;
import gui.Scroller;
import sections.*;

/******************************************************************************************
 ******************************************************************************************
 ******************************************************************************************
 ************** 				@author Wilfried Ndefo						 **************
 **************																 **************
 ************** 			A school project using only the JAVA 			 **************
 ************** 	programming language and the slick2D graphics library 	 **************
 ************** 													 		 **************
 ******************************************************************************************
 ******************************************************************************************
 *******************************************************************************************/

public class App extends BasicGame {
	public static AppGameContainer app;
	
	//=================== APP PROPERTIES
		private static String title = "Untitled";
		private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
							HEIGHT = Sizes.SCREEN_DEFAULT_HEIGHT.getSize();
		private TrueTypeFont ttf;
	
	//=================== MANAGER
		private MenuManager menuManager;
		private Scroller scroller;
		private FileChooser fc;
		
	//=================== GUI
		private float zoomFactor;
		private int mouseX, mouseY;
		private boolean showStatusBar;
		private TextField textfield;
		
		
	public App(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		//GUI
			menuManager = new MenuManager();
			scroller = new Scroller();
			fc = new FileChooser();
		
		zoomFactor = 0.5f;
		
		//Toggle
			showStatusBar = true;
		
		//Font style
			ttf = Store.appFont;
		textfield = new TextField(gc, ttf, 150, 150, 0, 0);
		Store.textfield = textfield;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		//Saving data
			Store.gc = gc;
			Store.gr = gr;
		
		//Basics
			gr.setAntiAlias(true);
			gr.setFont(ttf);
			
		//Background
			gr.setColor(AppColors.PALEWHITE.getColor());
			gr.fillRect(
					0, 0,
					Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
					Sizes.SCREEN_DEFAULT_HEIGHT.getSize()
				);
		
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());
		
		//Canva
			Canva.display(zoomFactor);
			
		//Board
			Board.display();
		
		//Settings
			Settings.display();
		
		//Toolkit	
			Toolkit.display();
		
		//StatusBar
			if(showStatusBar) {
				StatusBar.display();
				lineDivider(gr, Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize());
			}
		
		menuManager.displayMenu();
		
		scroller.display();
		
		textfield.render(gc, gr);
		Store.textfield.setText( textfield.getText() );
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		Store.timer++;
		
		Store.mouseX = input.getMouseX();
		Store.mouseY = input.getMouseY();
		
		
		//The natural position of the mouse in the app
			mouseX = Store.mouseX;
			mouseY = Store.mouseY;
		
		//For doing a selection
			if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ) {
				Store.mouseXClick = input.getMouseX();
				Store.mouseYClick = input.getMouseY();
				Store.isClicking = true;
			} else {
				Store.isClicking = false;
			}
		
		//Keyboard Shortcuts
			if( input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL) ) {//Control Button pressed
				Store.ctrlButtonPressed = true;
				
				if( input.isKeyPressed(Input.KEY_G) ) 
					Store.setShowGridlines();
				if( input.isKeyPressed(Input.KEY_R) )
					Store.setShowRuler();
				if( input.isKeyPressed(Input.KEY_B) )
					showStatusBar = !showStatusBar;
				if( input.isKeyPressed(Input.KEY_Z) )
					Store.removeLastGraphic();
				if( input.isKeyPressed(Input.KEY_Y) )
					Store.remitGraphic();
				
			} else {
				Store.ctrlButtonPressed = false;
			}
			
			if( input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT) ) {
				Store.shiftButtonPressed = true;
			} else {
				Store.shiftButtonPressed = false;
			}
			
			
		//Drag action
			if( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ) {
				Store.longPress = true;
				Store.longPressTimer++;
				
				
				switch ( Store.currentAction ) {//To send drag information to the store
				
					case DRAW_WITH_PENCIL : case DRAW_WITH_BRUSH : case ERASE : case DRAW_LINE :
					case DRAW_RECTANGLE : case DRAW_ROUNDED_RECTANGLE : case DRAW_ELLIPSE :
					case DRAW_TRIANGLE : case DRAW_PENTAGON : case DRAW_HEXAGON :
						
						Store.drawFinished = false;
						
						if( Canva.isHover() ) {
							//If in the store it is said that it is not drawing, it is reported to the store
							if( !Store.isDrawing ) {
								Store.isDrawing = true;
							}
							Store.addPoint(new Vector2f(mouseX, mouseY));
						}
					break;
				}
			} else {
				Store.longPress = false;
				Store.longPressTimer = 0;
				
				if( Store.isDrawing ) {
					Store.drawFinished = true;
					Store.isDrawing = false;
				}
			}
			
		//Stop writing
			if( input.isKeyPressed(Input.KEY_ENTER) ) {
				Store.enterButtonPressed = true;
				textfield.setFocus(false);
			} else {
				Store.enterButtonPressed = false;
			}
		
		//To change the cursor when the user chooses a tool
			if( Store.getCursorImage() != null ) {
				if( !Canva.isHover() || Store.currentAction == Actions.NONE ) {
					app.setDefaultMouseCursor();
					Store.cursorChangedCount = 0;
				} else if( Store.cursorChangedCount == 1 ) {
					app.setMouseCursor( Store.getCursorImage(), 0, 24 );
					Store.cursorChangedCount++;
				}
			}
			
		switch( Store.currentAction ) {
			case SHOW_STATUSBAR :
				showStatusBar = !showStatusBar;
				Store.setCurrentAction(Actions.NONE);
				break;
			case SHOW_RULER :
				Store.setShowRuler();
				Store.setCurrentAction(Actions.NONE);
				break;
			case SHOW_GRIDLINES :
				Store.setShowGridlines();
				Store.setCurrentAction(Actions.NONE);
				break;
			case ROTATE_LEFT_90 :
				Store.rotateFactor -= 90;
				Store.setCurrentAction(Actions.NONE);
				break;
			case ROTATE_RIGHT_90 :
				Store.rotateFactor += 90;
				Store.setCurrentAction(Actions.NONE);
				break;
			case ROTATE_TO_180 :
				Store.rotateFactor += 180;
				Store.setCurrentAction(Actions.NONE);
				break;
			case OPEN_FILE_CHOOSER :
				System.out.println(1);
				Store.importedFilePath = fc.openFile();
				Store.mouseXClick = Store.mouseYClick = -100;
				Store.setCurrentAction(Actions.NONE);
				break;
		}
		
		
		app.setTitle(title + " - Paint");
	}
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer( new App(title) );
		
		app.setDisplayMode( (int)WIDTH, (int)HEIGHT, false );
		app.setShowFPS(false);
		app.setIcon(Icons.LOGO.toString());

		//For launching app
			app.start();
	}
	
	public void lineDivider(Graphics gr, float y) {
		gr.setColor(new Color(230, 230, 230));
		gr.drawLine(0, y, WIDTH, y);
	}

}