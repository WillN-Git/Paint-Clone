import java.util.ArrayList;

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
import org.newdawn.slick.geom.Curve;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.MouseOverArea;

import constants.AppColors;
import constants.Icons;
import constants.Sizes;
import sections.*;


public class Test extends BasicGame {
	public static AppGameContainer app;
	
	//================== APP PROPERTIES
	private static String title = "Untitled";
	private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize(),
				HEIGHT = Sizes.SCREEN_DEFAULT_HEIGHT.getSize();
	
	//================== SECTIONS
	private Settings settings;
	private Toolkit toolkit;
	private Board board;
	private DrawSpace drawspace;
	private StatusBar statusbar;
	
	//================== GUI
	private int mouseX, mouseY;
	private int mouseXClick, mouseYClick;
	private int mouseXDrag, mouseYDrag;
	private boolean showStatusBar = true, showGridlines = false, showRuler = false;
	private float zoomFactor = 0.5f;
	
	//================== DRAFT
	private MouseOverArea file;
	private boolean draw = false;
	Font font;
	
	ArrayList<Vector2f> v = new ArrayList<>();
	
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
		
		/* ============================ TOOLKIT SECTION ============================= */
		toolkit.display(gr, mouseX, mouseY, mouseXClick, mouseYClick);
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize());
		
		/* ============================ SETTINGS SECTION ============================= */
		settings.display(gr, mouseX, mouseY, mouseXClick, mouseYClick);
		
		/* ============================ BOARD SECTION ============================= */
		board.display(gr, showRuler);
		
		/* ============================ DRAWSPACE SECTION ============================= */
		drawspace.display(gr, showGridlines, zoomFactor);
		
		gr.scale(1, 1);
		/* ============================ STATUSBAR SECTION ============================= */
		if(showStatusBar) {
			statusbar.display(gr, mouseX, mouseY);
			lineDivider(gr, Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize());
		}
		
		if(draw) {
			gr.setColor(new Color(255, 0, 0));
			gr.drawOval(mouseX - 7.5f, mouseY - 7.5f, 50, 50);//Segments params allowed you to draw a figure with n segments in an Oval
		}
		
		Vector2f p1 = new Vector2f(150, 437);
		Vector2f c1 = new Vector2f(375, 343);
		Vector2f c2 = new Vector2f(75, 343);
		Vector2f p2 = new Vector2f(300, 437);
		
		Curve c = new Curve(p1, c1, c2, p2);
		
		gr.setColor(new Color(255, 0, 0));
		//gr.translate(mouseX, mouseY);
		gr.draw(c);
		
		//LineDividers
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());
		
		//drawRectangle(gr, mouseXClick, mouseYClick, mouseX, mouseY);
		
//		gr.setColor(AppColors.LIGHTGRAY.getColor());
//		gr.fillRoundRect(200, 300, 150, 200, 5);
//		gr.setColor(AppColors.BLACK.getColor());
//		gr.drawString("100 %", 205, 305);
		
		Polygon p = new Polygon();
		
		p.addPoint(150,  437);
		p.addPoint(375, 343);
		p.addPoint(75, 343);
		p.addPoint(300, 437);
		//p.addPoint(58, 560);
		
		gr.draw(p);
		
		Path path; 
		
		if(v.size() >= 4) {
			path= new Path(v.get(0).getX(), v.get(0).getY());
			for(int i=1; i<v.size() - 2; i++)
				path.curveTo(v.get(i).getX(), v.get(i).getY(), v.get(i+1).getX(), v.get(i+1).getY(),v.get(i+2).getX(), v.get(i+2).getY());
			
			gr.setColor(new Color(0, 0, 0));
			gr.draw(path);
		}
		
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		Input input = gc.getInput();
		
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			mouseXClick = input.getMouseX();
			mouseYClick = input.getMouseY();
		}
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			v.add(new Vector2f(input.getMouseX(), input.getMouseY()));
		}
		
		//Keyboard Shortcuts
		
		if((input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL)) && input.isKeyPressed(Input.KEY_G)) {
			showGridlines = !showGridlines;
		}
		
		if((input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL)) && input.isKeyPressed(Input.KEY_R)) {
			showRuler = !showRuler;
		}
		
		if((input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL)) && input.isKeyPressed(Input.KEY_B)) {
			showStatusBar = !showStatusBar;
		}
		
		
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
		
		//For launching app
		app.start();
	}
	
	public void lineDivider(Graphics gr, float y) {
		gr.setColor(new Color(230, 230, 230));
		gr.drawLine(0, y, WIDTH, y);
	}
	
	//DRAFT
	public void drawRectangle(Graphics gr, float mouseXClick, float mouseYClick, float mouseX, float mouseY) {
		gr.setColor(new Color(255, 0, 0));
		gr.drawRect(mouseXClick, mouseYClick, -(mouseXClick - mouseX), (mouseY - mouseYClick));
	}
}