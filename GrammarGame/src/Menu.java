import java.awt.Dimension;

import javax.swing.JFrame;

import bla269.shapes.Rectangle;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;

public class Menu extends PApplet {

	private Rectangle rules, play, back;
	private boolean isRulePage;
	private PImage img, sky;
	private JFrame window;

	public Menu() {
		PApplet.runSketch(new String[]{""}, this);
		PSurfaceAWT surf = (PSurfaceAWT) this.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		window = (JFrame)canvas.getFrame();

		window.setBounds(100, 100, 500, 500);
		window.setMinimumSize(new Dimension(200,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
		isRulePage = false;
		play = new Rectangle(150, 200, 200, 75);
		play.setfill(0,180,255);
		play.setStroke(0, 180, 255);

		
		rules = new Rectangle(150, 325, 200, 75);
		rules.setfill(0, 180, 255);
		rules.setStroke(0, 180, 255);
		
		back = new Rectangle(25, 400, 50, 50);

	}
	
	public void setup() { 
		  img = loadImage("back.png");
		  sky = loadImage("sky.jpg");
	}

	public void draw() {
		background(127, 217, 255);

		image(sky,0,0,width,height);
		if (isRulePage) {
			image(img,27,400,width/10,height/10);
			
			textSize(50);
			text("HOW TO PLAY", 80, 75);
			textSize(20);
			text("1. Read the sentence at the top of the screen", 40, 125);
			text("2. View answer choices in each cloud", 40, 175);
			text("3. Use arrow keys to move the basket", 40, 225);
			text("4. Catch the correct term ", 40, 275);
			text("5. Use left/right arrow to left/right", 40, 325);
			text("6. Press p to pause", 40, 375);

		} else {
			rules.draw(this);
			play.draw(this);
			textSize(70);
			text("SKYFALL", 105,125);
			textSize(40);
			text("PLAY", 200, 255);
			text("RULES", 190, 380);
		}
	}

	public void mousePressed() {
		if (!isRulePage && rules.isPointInside(mouseX, mouseY)) {
			isRulePage = true;
		} else if (isRulePage && back.isPointInside(mouseX, mouseY)) {
			isRulePage = false;
		}
		else if (!isRulePage && play.isPointInside(mouseX, mouseY)) {
			
			window.dispose();
			
			GameBoard gameboard = new GameBoard();
			
			

		}
	}

}
