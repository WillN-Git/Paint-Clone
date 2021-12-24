import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;

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
	
	//================== GUI
	private int mouseX, mouseY;
	
	//================== DRAFT
	private MouseOverArea file;
	private boolean draw = false;
	Font font;
	
	
	
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
		//gr.scale(Display.getWidth() / WIDTH, Display.getHeight() /  HEIGHT);
		gr.setAntiAlias(true);
		
		/* ============================ SETTINGS SECTION ============================= */
		settings.display(gr);
		
		/* ============================ TOOLKIT SECTION ============================= */
		toolkit.display(gr, mouseX, mouseY);
		
		/* ============================ BOARD SECTION ============================= */
		board.display(gr);
		
		/* ============================ DRAWSPACE SECTION ============================= */
		drawspace.display(gr);
		
		/* ============================ STATUSBAR SECTION ============================= */
		statusbar.display(gr, mouseX, mouseY);
		
		if(draw) {
			gr.setColor(new Color(255, 0, 0));
			gr.drawOval(mouseX - 7.5f, mouseY - 7.5f, 50, 50);//Segments params allowed you to draw a figure with n segments in an Oval
		}
		
		
		
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
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			draw = true;
			title = "Ok";
		} else {
			draw = false;
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
		
		Display.setResizable(true);
		
		
		//For launching app
		app.start();
	}
	
	public void lineDivider(Graphics gr, float y) {
		gr.setColor(new Color(230, 230, 230));
		gr.drawLine(0, y, WIDTH, y);
	}
}