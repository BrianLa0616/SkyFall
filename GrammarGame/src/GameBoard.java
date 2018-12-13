import java.awt.Dimension;

import javax.swing.JFrame;

import bla269.shapes.Rectangle;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;

public class GameBoard extends PApplet {

	private Rectangle setting, back, question;
	private boolean isRulePage, isPaused;
	private PImage settingButton, backButton;
	private JFrame window;

	public GameBoard() {
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
		
		
		setting = new Rectangle(440,10,50,50);
		
		question = new Rectangle(0,0,500,75);
		question.setfill(255, 255, 255);
		
		back = new Rectangle(25, 400, 50, 50);

		
		isRulePage = false;
		isPaused = false;
		
	
	}
	
	public void setup() { 
		  settingButton = loadImage("setting.png");
		  backButton = loadImage("back.png");

	}

	public void draw() {
		background(127, 217, 255);
		question.draw(this);
		image(settingButton,440,10,width/10,height/10);
		image(backButton,27,400,width/10,height/10);

		if (isRulePage) {
			
			fill(0);
			textSize(50);
			text("SETTINGS", 80, 75);
			textSize(20);
			text("1. Read the sentence at the top of the screen", 40, 125);
			text("2. View answer choices in each cloud", 40, 175);
			text("3. Use arrow keys to move the basket", 40, 225);
			text("4. Catch the correct term ", 40, 275);
			text("5. Use left/right arrow to left/right", 40, 325);
			text("6. Press p to pause", 40, 375);

		} else {
			
		}
		
	}

	public void mousePressed() {
		if (!isRulePage && setting.isPointInside(mouseX, mouseY)) {
			isRulePage = true;
		} else if (isRulePage && back.isPointInside(mouseX, mouseY)) {
			isRulePage = false;
		} else if (!isRulePage && back.isPointInside(mouseX, mouseY)) {
			//popup
			Menu menu = new Menu();
		}
		
	}

}
