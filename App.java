import java.awt.AWTException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;

import components.Store;
import constants.*;
import gui.MenuManager;
import gui.Scroller;
import sections.*;

/**
 * 
 * @author Wilfried Ndefo
 * 
 * A school project using only the JAVA 
 * 	 programming language and the slick2D graphics library
 * 
 */

public class App extends BasicGame {
	public static AppGameContainer app;
	public static ScalableGame paint;
	
	//=================== APP PROPERTIES
		private static String title = "Untitled";
		private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
							HEIGHT = Sizes.SCREEN_DEFAULT_HEIGHT.getSize();
		private TrueTypeFont ttf;
	
	//=================== MANAGER
		private MenuManager menuManager;
		private Scroller scroller;
		
	//=================== GUI
		private float zoomFactor;
		private int mouseX, mouseY, 
					mouseXClick, mouseYClick;
		private boolean showStatusBar, showGridlines , showRuler;
		private TextField textfield;
		
		
	public App(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		//GUI
			menuManager = new MenuManager();
			scroller = new Scroller();
		
		
		zoomFactor = 0.5f;
		
		//Toggle
			showStatusBar = true;
			showGridlines = false;
			showRuler = false;
		
		//Font style
			java.awt.Font font = new java.awt.Font("Century Gothic", java.awt.Font.BOLD, 14);
			ttf = new TrueTypeFont(font, true);
		
		textfield = new TextField(gc, ttf, 150, 150, 0, 0);
		Store.setTextField(textfield);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		//Basics
			gr.setAntiAlias(true);
			gr.setFont(ttf);
			
		//Background
			gr.setColor(AppColors.PALEWHITE.getColor());
			gr.fillRect(
					0, 
					0, 
					Sizes.SCREEN_DEFAULT_WIDTH.getSize(), 
					Sizes.SCREEN_DEFAULT_HEIGHT.getSize()
					);
		
		Store.setGc(gc);
		Store.setGr(gr);
		
		
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());
		
		//Canva
			Canva.display(showGridlines, zoomFactor);
		
		//Board
			Board.display(showRuler);
		
		//Settings
			Settings.display();
		
		//Toolkit	
			Toolkit.display();
		
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize());//1
		
		
		
		//StatusBar
			if(showStatusBar) {
				StatusBar.display();
				lineDivider(gr, Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize());
			}
		
		menuManager.displayMenu();
		
		scroller.display();
		
		textfield.render(gc, gr);
		Store.setTextInput(textfield.getText());
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		Display.setResizable(true);
		paint.recalculateScale();
		
		Store.setMouseX(input.getMouseX());
		Store.setMouseY(input.getMouseY());
		
		//The natural position of the mouse
			mouseX = Store.getMouseX();
			mouseY = Store.getMouseY();
		
		//For doing a selection
			if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ) {
				Store.setMouseXClick(input.getMouseX());
				Store.setMouseYClick(input.getMouseY());
				mouseXClick = Store.getMouseXClick();
				mouseYClick = Store.getMouseYClick();
			}
		
		//Keyboard Shortcuts
			if( input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL) ) {//Control Button pressed
				Store.setCtrlButtonPressed(true);
				
				if( input.isKeyPressed(Input.KEY_G) ) 
					showGridlines = !showGridlines;
				if( input.isKeyPressed(Input.KEY_R) )
					showRuler = !showRuler;
				if( input.isKeyPressed(Input.KEY_B) )
					showStatusBar = !showStatusBar;
				if( input.isKeyPressed(Input.KEY_Z) )
					Store.removeLastGraphic();
				if( input.isKeyPressed(Input.KEY_Y) )
					Store.remitGraphic();
				
			} else {
				Store.setCtrlButtonPressed(false);
			}
			
			if( input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT) ) {
				Store.setShiftButtonPressed(true);
			} else {
				Store.setShiftButtonPressed(false);
			}
			
			
		//Drag action
			if( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ) {
				Store.setIsDragging(true);
				
				switch ( Store.getCurrentAction() ) {//To send drag information to the store
				
					case DRAW_WITH_PENCIL : case DRAW_WITH_BRUSH : case ERASE : case DRAW_LINE :
					case DRAW_RECTANGLE : case DRAW_ROUNDED_RECTANGLE : case DRAW_ELLIPSE :
					case DRAW_TRIANGLE : case DRAW_PENTAGON : case DRAW_HEXAGON :
						Store.setDrawFinished(false);
						
						if( 
							input.getMouseY() > Sizes.SETTING_HEIGHT.getSize() +  Sizes.TOOLKIT_HEIGHT.getSize() + 60 &&
							( input.getMouseX() > 60 && input.getMouseX() < Sizes.CANVA_WIDTH.getSize()+60)
								
						) {
							//If in the store it is said that it is not drawing, it is reported to the store
							if( !Store.getIsDrawing() ) {
								Store.setIsDrawing(true);
							}
							Store.addPoint(new Vector2f(mouseX, mouseY));
						}
					break;
					
					case BACK :
						//TODO : Je suis foutu...
						break;
				}
			} else {
				Store.setIsDragging(false);
				
				if( Store.getIsDrawing() ) {
					Store.setDrawFinished(true);
					Store.setIsDrawing(false);
				}
			}
			
		//Stop writing
			if( input.isKeyPressed(Input.KEY_ENTER) ) {
				Store.setEnterButtonPressed(true);
				textfield.setFocus(false);
			} else {
				Store.setEnterButtonPressed(false);
			}
		
		//To change the cursor when the user chooses a tool
			if( Store.getCursorImage() != null ) {
				if(
				   ( mouseX <= 60 || mouseX > Sizes.CANVA_WIDTH.getSize()+60 ) ||
				   ( mouseY <= Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() )
				  ) {
					app.setDefaultMouseCursor();
				} else {
					app.setMouseCursor( Store.getCursorImage(), 0, 24 );
				}
			}
			
		app.setTitle(title + " - Paint");
	}
	
	public static void main(String[] args) throws SlickException {
		paint = new ScalableGame(
									new App(title + " - Paint"), 
									(int)WIDTH, (int)HEIGHT, 
									false
								);
		
		app = new AppGameContainer( paint );
		
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