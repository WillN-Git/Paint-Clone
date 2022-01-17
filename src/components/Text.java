package components;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;

public class Text extends Graphic {
	/*
	 * =============================
	 * 			  PROPS 
	 * =============================
	*/
	
	private TrueTypeFont font;
	private String str = null;
	private char align = 'L';
	private int width;
	

	/*
	 * =============================
	 * 		   CONSTRUCTOR 
	 * =============================
	*/
		
	public Text(TrueTypeFont font, String str, int posX, int posY, char align, int width, Color colorOfShape) {
		super(posX, posY, colorOfShape);
		
		this.font = font;
		this.str = str;
		this.align = align;
		this.width = width;
	}
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	
	public String getStr() {
		return str;
	}
	
	public char getAlignement() {
		return align;
	}
	
	public int getWidth() {
		return width;
	}
	
	public TrueTypeFont getFont() {
		return font;
	}

	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	
	public void setStr(String str) {
		this.str = str;
	}
	
	@Override
	public boolean equals(Object o) {
		if( o == this )
			return true;
		if( !(o instanceof Text) )
			return false;
		
		Text t = (Text) o;
		
		return (
				( posX == t.posX && posY == t.posY ) &&
				( this.str == t.str ) &&
				colorEquals( this.colorOfShape, t.colorOfShape)
		);
	}
}
