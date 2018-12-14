import processing.core.PApplet;
import processing.core.PImage;

public class Basket extends PApplet {

	private PImage basket;
	private String file;
	private float x, y;

	public Basket(String file, float x, float y) {
		this.file = file;
		this.x = x;
		this.y = y;
	}

	public void setup(PApplet d) {
		basket = d.loadImage(file);
		
	}

	public void draw(PApplet d) {
		d.image(basket, x, y, width, height);

	}

	public void move(int x) {
		if (this.x + x > 0 && this.x + x < 400) {
			this.x += x;
		}
	}

}
