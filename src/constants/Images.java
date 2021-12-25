package constants;

public enum Images {
	//============= IMAGES
	RULER("ruler.png");
	
	//============= PROPS & CONSTRUCTOR
	String path;
		
	Images(String path) {
		this.path = "assets/images/" + path;
	}
	
	//============= GETTER
	public String toString() {
		return this.path;
	}
}
