import java.awt.Dimension;

import javax.swing.JFrame;

import bla269.shapes.Rectangle;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;

public class Menu extends PApplet {

	private Rectangle rules, play, setting, back;
	private boolean isRulePage, isSettingPage, isHomePage;
	private PImage backButton, sky;
	private JFrame window;

	public Menu() {
		PApplet.runSketch(new String[] { "" }, this);
		PSurfaceAWT surf = (PSurfaceAWT) this.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		window = (JFrame) canvas.getFrame();

		window.setBounds(100, 100, 500, 500);
		window.setMinimumSize(new Dimension(200, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();

		isRulePage = false;
		isSettingPage = false;
		isHomePage = true;

		play = new Rectangle(150, 150, 200, 75);
		play.setfill(0, 180, 255);
		play.setStroke(0, 180, 255);

		rules = new Rectangle(150, 250, 200, 75);
		rules.setfill(0, 180, 255);
		rules.setStroke(0, 180, 255);

		setting = new Rectangle(150, 350, 200, 75);
		setting.setfill(0, 180, 255);
		setting.setStroke(0, 180, 255);

		back = new Rectangle(25, 400, 50, 50);

	}

	public void setup() {
		backButton = loadImage("back.png");
		sky = loadImage("sky.jpg");
	}

	public void draw() {
		background(127, 217, 255);
		textAlign(CENTER,CENTER);
		image(sky, 0, 0, width, height);

		if (isRulePage) {
			image(backButton, 27, 400, width / 10, height / 10);

			textSize(50);
			text("HOW TO PLAY", 250, 75);
			textSize(20);
			textAlign(LEFT);
			text("1. Read the sentence at the top of the screen", 40, 125);
			text("2. View answer choices in each cloud", 40, 165);
			text("3. Use arrow keys to move the basket", 40, 205);
			text("to the answer choice that completes ",40,230);
			text("the sentence correctly", 40,255);
			text("4. Catch the correct choice", 40, 295);

		} else if (isSettingPage) {
			image(backButton, 27, 400, width / 10, height / 10);
			textSize(50);
			text("SETTINGS", 250, 75);
			textSize(20);
			textAlign(RIGHT);
			text("Move left :", 250, 125);
			text("Move right :", 250, 150);
			text("Pause :", 250, 175);
			text("Quick drop :", 250, 200);
			text("Hard drop :", 250, 225);
			textAlign(LEFT);
			
			text(" left arrow", 250, 125);
			text(" right arrow", 250, 150);
			text(" p", 250, 175);
			text(" down arrow", 250, 200);
			text(" spacebar", 250, 225);
			
		} else {
			
			if (play.isPointInside(mouseX, mouseY)) {
				play.setfill(0, 162, 229);
			} else {
				play.setfill(0, 180, 255);
			}
			
			if (rules.isPointInside(mouseX, mouseY)) {
				rules.setfill(0,162,229);
			} else {
				rules.setfill(0, 180, 255);
			}
			
			if (setting.isPointInside(mouseX, mouseY)) {
				setting.setfill(0,162,229);
			} else {
				setting.setfill(0, 180, 255);
			}
			play.draw(this);
			rules.draw(this);
			setting.draw(this);

			textSize(70);
			text("SKYFALL", 250, 60);
			textSize(40);
			text("PLAY", 250, 182.5f);
			text("RULES", 250, 282.5f);
			text("SETTINGS", 250, 382.5f);
		}
	}

	public void mousePressed() {
		if (isHomePage && rules.isPointInside(mouseX, mouseY)) {
			isHomePage = false;
			isRulePage = true;
		} else if (isRulePage && back.isPointInside(mouseX, mouseY)) {
			isRulePage = false;
			isHomePage = true;
		} else if (isHomePage && setting.isPointInside(mouseX, mouseY)) {
			isSettingPage = true;
			isHomePage = false;
		} else if (isSettingPage && back.isPointInside(mouseX, mouseY)) {
			isSettingPage = false;
			isHomePage = true;
		} else if (isHomePage && play.isPointInside(mouseX, mouseY)) {

			window.dispose();

			new GameBoard();
		}
	}

}
