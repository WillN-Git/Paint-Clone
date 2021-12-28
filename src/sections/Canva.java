package sections;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import components.Store;
import constants.Actions;
import constants.AppColors;
import constants.Icons;
import constants.Sizes;

public abstract class Canva {
	/*
	 * =============================
	 * 			DATA 
	 * =============================
	*/
	
	private static Graphics gr;
	private static int mouseX, mouseY,
				mouseXClick, mouseYClick;
	private static Actions currentAction;
	
	
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	
	private static float WIDTH = Sizes.SCREEN_DEFAULT_WIDTH.getSize() * 0.7f,
				HEIGHT = Sizes.BOARD_HEIGHT.getSize();
	
	private static float PADDING_H = 10;
	
	private static float MIDDLE = 20,
						TOP = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40,
						LEFT = 40,
						RIGHT = LEFT + WIDTH,
						BOTTOM = Sizes.SETTING_HEIGHT.getSize() + Sizes.TOOLKIT_HEIGHT.getSize() + 40 + HEIGHT;
	
	/*
	 * =============================
	 * 			METHODS 
	 * =============================
	*/
	public static void display(boolean showGridlines, float zoomFactor) throws SlickException {
		//Data mapping
		gr = Store.getGr();
		mouseX = Store.getMouseX();
		mouseY = Store.getMouseY();
		mouseXClick = Store.getMouseXClick();
		mouseYClick = Store.getMouseYClick();
		
		
		//Paper
		gr.setColor(AppColors.WHITE.getColor());
		gr.fillRect(LEFT, TOP, WIDTH, HEIGHT);
		
		gr.setColor(new Color(230, 230, 230));
		gr.drawRect(LEFT, TOP, WIDTH, HEIGHT);
		
		//GridLines
		if(showGridlines) {
			for(int i=(int)TOP; i<BOTTOM; i+=15)//Horizontal Lines
				gr.drawLine(LEFT, i, RIGHT, i);
			
			for(int j=(int)LEFT; j<RIGHT; j+=15)//Vertical lines
				gr.drawLine(j, TOP, j, BOTTOM);
		}
		
		//==================================================
		Path path;
		ArrayList<Vector2f> v;
		
		for(Shape s : Store.getShapes())
			gr.draw(s);
		
		switch( Store.getCurrentAction() ) {
		
		//La Gomme ne fontionne pas tres bien.....
			case DRAW_WITH_PENCIL: case DRAW_WITH_BRUSH: case ERASE: //50% Ok
				if(Store.getCurrentAction() == Actions.DRAW_WITH_PENCIL)
					Store.setCursorImage(Icons.PENCIL.getIcon());
				
				if(Store.getCurrentAction() == Actions.DRAW_WITH_BRUSH) {
					Store.setCursorImage(Icons.BRUSH.getIcon());
					gr.setLineWidth(10);//Set line width for the brush size
				}
				
				if(Store.getCurrentAction() == Actions.ERASE) {
					Store.setCursorImage(Icons.ERASER.getIcon());
					gr.setLineWidth(10);//Set line width for the brush size
				}
					
				v = Store.getSetOfPoints();
				
				path = null;
				
				if(v.size() >= 4) {
					path = new Path(v.get(0).getX(), v.get(0).getY());
					
					for(int i=1; i< v.size() - 2; i++) 
						path.curveTo(
								v.get(i).getX(), v.get(i).getY(),
								v.get(i+1).getX(), v.get(i+1).getY(),
								v.get(i+2).getX(), v.get(i+2).getY()
								);
					
					gr.setColor(AppColors.BLACK.getColor());
					
					if(Store.getCurrentAction() == Actions.ERASE)
						gr.setColor(AppColors.WHITE.getColor());
					
					gr.draw(path);
				}
				
				if(Store.getCurrentAction() != Actions.DRAW_WITH_PENCIL)
					gr.setLineWidth(1);//Reset line width for the whole app	
				
				if(Store.getDrawFinished()) {
					Store.addShape(path);
					Store.removeAllPoints();
					Store.setDrawFinished(false);
				}
				break;
			
			case DRAW_RECTANGLE : 
				
				break;
				
			case FILL:
				Store.setCursorImage(Icons.FILL.getIcon());
				break;
				
			case WRITE:
				Store.setCursorImage(Icons.TEXT.getIcon());
				break;
				
			case ZOOM:
				Store.setCursorImage(Icons.ZOOM.getIcon());
				break;
				
			case PICK_COLOR:
				Store.setCursorImage(Icons.COLOR_PICKER.getIcon());
				break;
		}
	}
}
