import java.util.ArrayList;

import interfaces.Positionable;

public class MenuItem implements Positionable {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	private String label;
	public ArrayList<Item> items = new ArrayList<>();
	
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

	public String getLabel() {
		return this.label;
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
	
	public void setLabel(String label) {
		this.label = label;
	}
	
}
