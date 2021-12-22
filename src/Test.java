import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

import constants.AppColors;
import constants.Icons;
import constants.Sizes;
import sections.*;


public class Test extends BasicGame {
	public static AppGameContainer app;
	
	//================== APP PROPERTIES
	public static String title = "Untitled";
	public static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
						HEIGHT = Sizes.SCREEN_DEFAULT_HEIGHT.getSize();
	
	//================== SECTIONS
	Settings settings;
	Toolkit toolkit;
	Board board;
	DrawSpace drawspace;
	StatusBar statusbar;
	
	//================== DRAFT
	private MouseOverArea file;
	private int mouseX, mouseY;
	
	
	
	public Test(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		settings = new Settings();
		toolkit = new Toolkit();
		board = new Board();
		drawspace = new DrawSpace();
		statusbar = new StatusBar();
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		/* ============================ SETTINGS SECTION ============================= */
		settings.display(gr);
		
		
		/* ============================ TOOLKIT SECTION ============================= */
		toolkit.display(gr);
		
		
		
		/* ============================ BOARD SECTION ============================= */
		board.display(gr);
		
		/* ============================ DRAWSPACE SECTION ============================= */
		drawspace.display(gr);
		
		/* ============================ STATUSBAR SECTION ============================= */
		statusbar.display(gr, mouseX, mouseY);
		
		
		//LineDividers
		lineDivider(gr, Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize());
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize());
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		Input input = gc.getInput();
		
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		app.setTitle(title + " - Paint");
	}
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Test(title + " - Paint"), (int)WIDTH, (int)HEIGHT, false);
		
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