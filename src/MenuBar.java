import java.util.ArrayList;

import interfaces.Positionable;

public class MenuBar implements Positionable {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	private float width, height;
	public ArrayList<MenuItem> menuItems = new ArrayList<>();
	
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	@Override
	public float getPosX() {
		return this.posX;
	}
	@Override
	public float getPosY() {
		return this.posY;
	}
	
	/*
	 * =============================
	 * 			SETTERS 
	 * =============================
	*/
	@Override
	public void setPosX(float posX) {
		this.posX = posX;
	}
	@Override
	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	
}
