package utils;

import interfaces.Positionable;

public class LineDivider implements Positionable {
	private float posX, posY;
	private float width, height;
	
	/* ================= GETTERS ================= */
	@Override
	public float getPosX() {
		return this.posX;
	}

	@Override
	public float getPosY() {
		return this.posY;
	}

	/* ================= SETTERS ================= */
	@Override
	public void setPosX(float posX) {
		this.posX = posX;
	}

	@Override
	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
}
