import interfaces.Positionable;

public class Item implements Positionable  {
	/*
	 * =============================
	 * 			PROPS 
	 * =============================
	*/
	private float posX, posY;
	private String label;
	private boolean isClicked = false;
	private boolean isHover = false;

	
	/*
	 * ================================
	 * 			CONSTRUCTOR 
	 * ================================
	*/
	public Item() {
		super();
	}
	
	public Item(String label) {
		this.label = label;
	}
	
	public Item(String label, float posX, float posY) {
		this.label = label;
		this.posX = posX;
		this.posY = posY;
	}
	
	/*
	 * =============================
	 * 			GETTERS 
	 * =============================
	*/
	@Override
	public float getPosX() {
		return posX;
	}

	@Override
	public float getPosY() {
		return posY;
	}
	
	public String getLabel() {
		return label;
	}

	public boolean getIsClicked() {
		return isClicked;
	}
	
	public boolean getIsHover() {
		return isHover;
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
	
	public void setIsHover(boolean isHover) {
		this.isHover = isHover;
	}
	
	public void setIsClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
	/*
	 * =============================
	 * 			OTHERS 
	 * =============================
	*/
	
	public String toString() {
		return this.label;
	}
}
