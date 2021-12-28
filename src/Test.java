import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import components.MenuManager;
import components.Store;
import constants.*;
import sections.*;


public class Test extends BasicGame {
	public static AppGameContainer app;
	
	//================== APP PROPERTIES
	private static String title = "Untitled";
	private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
						HEIGHT = Sizes.SCREEN_DEFAULT_HEIGHT.getSize();
	
	//=================== MANAGER
	private MenuManager menuManager;
	
	//================== GUI
	private boolean showStatusBar, showGridlines , showRuler;
	private float zoomFactor;
	private int mouseX, mouseY, 
				mouseXClick, mouseYClick;
	
	//================== DRAFT
	private int oldTime, currentTime;
	
	ArrayList<Vector2f> v = new ArrayList<>(), v1 = new ArrayList<>();
	
	
	public Test(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {		
		menuManager = new MenuManager();
		
		zoomFactor = 0.5f;
		showStatusBar = true;
		showGridlines = false;
		showRuler = false;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		//gr.scale(Display.getWidth() / WIDTH, Display.getHeight() /  HEIGHT);
		gr.setAntiAlias(true);
		gr.setBackground(AppColors.PALEWHITE.getColor());
		
		Store.setGr(gr);
		
		Settings.display();
		Toolkit.display();
		
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize());//1
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());//2
		
		//board.display(gr, showRuler);
		if(showRuler) {
			gr.drawImage(new Image(Images.RULER.toString()), 0, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());
		}
		
		Canva.display(showGridlines, zoomFactor);
		
		if(showStatusBar) {
			StatusBar.display(gr);
			lineDivider(gr, Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize());
		}
		
		menuManager.displayMenu();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		Store.setMouseX(input.getMouseX());
		Store.setMouseY(input.getMouseY());
		
		//The natural position of the mouse
		mouseX = Store.getMouseX();
		mouseY = Store.getMouseY();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			Store.setMouseXClick(input.getMouseX());
			Store.setMouseYClick(input.getMouseY());
			mouseXClick = Store.getMouseXClick();
			mouseYClick = Store.getMouseYClick();
		}
		
		//Keyboard Shortcuts
		if(input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL)) {//Control Button pressed
			if(input.isKeyPressed(Input.KEY_G)) 
				showGridlines = !showGridlines;
			if(input.isKeyPressed(Input.KEY_R))
				showRuler = !showRuler;
			if(input.isKeyPressed(Input.KEY_B))
				showStatusBar = !showStatusBar;
		}
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {//Drag action
			
			
			switch ( Store.getCurrentAction() ) {//To send drag information to the store
			
				case DRAW_WITH_PENCIL : case DRAW_WITH_BRUSH : case ERASE : case DRAW_RECTANGLE :
					Store.setDrawFinished(false);
					
					if(input.getMouseY() > Sizes.SETTING_HEIGHT.getSize() +  Sizes.TOOLKIT_HEIGHT.getSize()) {
						//If in the store it is said that it is not drawing, it is reported to the store
						if(!Store.getIsDrawing()) {
							Store.setIsDrawing(true);
							Store.setStart_shape_from_x(input.getMouseX());
							Store.setStart_shape_from_y(input.getMouseY());
						}
						Store.addPoint(new Vector2f(input.getMouseX(), input.getMouseY()));
					}
				break;
			}
			
			title = "Drag !";//Test
		} else {
			title = "No Drag...";//Test
			
			if(Store.getIsDrawing()) {
				Store.setDrawFinished(true);
				Store.setIsDrawing(false);
			}
		}
		
		//To change the cursor when the user chooses a tool
		if(Store.getCursorImage() != null) {
			if(mouseY <= Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize()) {
				app.setDefaultMouseCursor();
			} else {
				app.setMouseCursor(Store.getCursorImage(), 0, 24);
			}
		}
		
		app.setTitle(title + " - Paint");
	}
	
	@Override
	public boolean closeRequested() {
		System.exit(0);
		return false;
	}
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Test(title + " - Paint"), (int)WIDTH, (int)HEIGHT, false);
		
		app.setShowFPS(false);
		app.setIcon(Icons.LOGO.toString());
		
//		Display.setResizable(true);
		
		//For launching app
		app.start();
	}
	
	public void lineDivider(Graphics gr, float y) {
		gr.setColor(new Color(230, 230, 230));
		gr.drawLine(0, y, WIDTH, y);
	}

}