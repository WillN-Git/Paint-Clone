package constants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum Images {
	//============= IMAGES
		RULER("ruler.png"),
		PX_1("1px.png"),
		PX_3("3px.png"),
		PX_5("5px.png"),
		PX_8("8px.png"),
		CHROMA("chromatic-ring-removebg-preview.png"),
		CHROMATIC_RING("chromatic-ring.png");
	
	//============= PROPS & CONSTRUCTOR
		String path;
			
		Images(String path) {
			this.path = "assets/images/" + path;
		}
	
	//============= GETTERS
		public String toString() {
			return this.path;
		}
		
		public Image getImage() throws SlickException {
			return new Image(path);
		}
}
