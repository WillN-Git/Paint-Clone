import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Curve;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import components.Store;
import constants.*;
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
	private Canva canva;
	private StatusBar statusbar;
	
	//================== GUI
	private boolean showStatusBar = true, showGridlines = false, showRuler = false;
	private float zoomFactor = 0.5f;
	private int mouseX, mouseY, 
				mouseXClick, mouseYClick;
	
	//================== DRAFT
	private int oldTime, currentTime;
	
	ArrayList<Vector2f> v = new ArrayList<>();
	
	
	public Test(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		//=============== Sections
		settings = new Settings();
		toolkit = new Toolkit();
		board = new Board();
		canva = new Canva();
		statusbar = new StatusBar();
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		//gr.scale(Display.getWidth() / WIDTH, Display.getHeight() /  HEIGHT);
		gr.setAntiAlias(true);
		gr.setBackground(AppColors.PALEWHITE.getColor());
		
		Store.setGr(gr);
		
		toolkit.display();
		
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize());//1
		lineDivider(gr, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());//2
		
		//board.display(gr, showRuler);
		if(showRuler) {
			gr.drawImage(new Image(Images.RULER.toString()), 0, Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize());
		}
		
		canva.display(showGridlines, zoomFactor);
		settings.display();
		
		gr.scale(1, 1);
		
		if(showStatusBar) {
			statusbar.display(gr);
			lineDivider(gr, Sizes.SCREEN_DEFAULT_HEIGHT.getSize() - Sizes.STATUSBAR_HEIGHT.getSize());
		}
		
		Vector2f p1 = new Vector2f(150, 437);
		Vector2f c1 = new Vector2f(375, 343);
		Vector2f c2 = new Vector2f(75, 343);
		Vector2f p2 = new Vector2f(300, 437);
		
		Curve c = new Curve(p1, c1, c2, p2);
		
		gr.setColor(new Color(255, 0, 0));
		//gr.translate(mouseX, mouseY);
		gr.draw(c);
		
		
		
		Polygon p = new Polygon();
		
		p.addPoint(150,  437);
		p.addPoint(375, 343);
		p.addPoint(75, 343);
		p.addPoint(300, 437);
		
		gr.draw(p);
		
		Path path = null;
		
		if(Store.getCurrentAction() == Actions.DRAW_WITH_PENCIL) {
			if(v.size() >= 4) {
				path = new Path(v.get(0).getX(), v.get(0).getY());
				
				for(int i=1; i<v.size(); i++)
					path.curveTo(
									v.get(i).getX(), v.get(i).getY(),
									v.get(i).getX(), v.get(i).getY(), 
									v.get(i).getX(), v.get(i).getY()
								);
				gr.setColor(new Color(0,0,0));
				gr.draw(path);
			}
			
			if(Store.getCurrentAction() != Store.getPreviousAction() && path != null) {
				Store.addShape(path);
				System.out.println("\nBoucle !");
				path = null;
			}
		}

		for(Shape sh : Store.getShapes()) {
			gr.draw(sh);
		}
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		Input input = gc.getInput();
		
		Store.setMouseX(input.getMouseX());
		Store.setMouseY(input.getMouseY());
		
		mouseX = Store.getMouseX();
		mouseY = Store.getMouseY();
		
		currentTime++;
		
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
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && Store.getCurrentAction() == Actions.DRAW_WITH_PENCIL) {//Drag action
			if(input.getMouseX() != mouseXClick && input.getMouseY() != mouseYClick)
				v.add(new Vector2f(input.getMouseX(), input.getMouseY()));
			
			title = "Drag !";
		} else {
			title = "No Drag...";
			Store.setCurrentAction(Actions.NONE);
		}
		
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
	
	//DRAFT
	public void drawRectangle(Graphics gr, float mouseXClick, float mouseYClick, float mouseX, float mouseY) {
		gr.setColor(new Color(255, 0, 0));
		gr.drawRect(mouseXClick, mouseYClick, -(mouseXClick - mouseX), (mouseY - mouseYClick));
	}
}