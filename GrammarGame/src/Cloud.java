import processing.core.PApplet;
import processing.core.PImage;

public class Cloud extends PApplet{

	private String file;
	private float x, y;
	private PImage cloud;

	public Cloud(String file, float x, float y) {
		this.file = file;
		this.x = x;
		this.y = y;
	}
	
	public void setup(PApplet d) {
		cloud = d.loadImage(file);
	}
	
	public void draw(PApplet d) {
		d.image(cloud, x, y,width,height);
	}
	
	public void drop() {
		if (y < 475) {
			y+=2;
		}
	}
	
	public void hardDrop() {
		y = 475;
	}
	
	public float getY() {
		return y;
	}
}
