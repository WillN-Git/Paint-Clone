import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import components.Store;
import constants.*;
import gui.MenuManager;
import gui.Scroller;
import sections.*;


public class Test extends BasicGame {
	public static AppGameContainer app;
	
	//=================== APP PROPERTIES
	private static String title = "Untitled";
	private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
						HEIGHT = Sizes.SCREEN_DEFAULT_HEIGHT.getSize();
	
	//=================== MANAGER
	private MenuManager menuManager;
	private Scroller scroller;
	
	//=================== GUI
	private float zoomFactor;
	private int mouseX, mouseY, 
				mouseXClick, mouseYClick;
	private boolean showStatusBar, showGridlines , showRuler;
	
	//=================== DRAFT
	private int oldTime, currentTime;
	
	
	public Test(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		menuManager = new MenuManager();
		scroller = new Scroller();
		
		zoomFactor = 0.5f;
		showStatusBar = true;
		showGridlines = false;
		showRuler = false;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		//Basics
		gr.setAntiAlias(true);
		gr.setBackground(AppColors.PALEWHITE.getColor());
		
		Store.setGr(gr);
		
		
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());
		Canva.display(showGridlines, zoomFactor);
		Board.display(showRuler);
		
		Settings.display();
		Toolkit.display();
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize());//1
		
		
		if(showStatusBar) {
			StatusBar.display();
			lineDivider(gr, Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize());
		}
		
		menuManager.displayMenu();
		
		scroller.display();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
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
		
		if( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ) {//Drag action
			Store.setIsDragging(true);
			
			switch ( Store.getCurrentAction() ) {//To send drag information to the store
			
				case DRAW_WITH_PENCIL : case DRAW_WITH_BRUSH : case ERASE : case DRAW_LINE :
				case DRAW_RECTANGLE : case DRAW_ROUNDED_RECTANGLE : case DRAW_ELLIPSE :
				case DRAW_TRIANGLE : case DRAW_PENTAGON : case DRAW_HEXAGON :
					Store.setDrawFinished(false);
					
					if( input.getMouseY() > Sizes.SETTING_HEIGHT.getSize() +  Sizes.TOOLKIT_HEIGHT.getSize() ) {
						//If in the store it is said that it is not drawing, it is reported to the store
						if( !Store.getIsDrawing() ) {
							Store.setIsDrawing(true);
							Store.setStart_shape_from_x(mouseX);
							Store.setStart_shape_from_y(mouseY);
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
		app = new AppGameContainer( new Test(title + " - Paint"), (int)WIDTH, (int)HEIGHT, false );
		
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