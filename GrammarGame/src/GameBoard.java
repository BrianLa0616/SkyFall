import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bla269.shapes.Rectangle;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;

public class GameBoard extends PApplet {

	private Rectangle setting, back, question;
	private boolean isSettingPage, isPaused;
	private PImage settingButton, backButton, sky, basket;
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

		
		isSettingPage = false;
		isPaused = false;
		
		
	
	}
	
	public void setup() { 
		  settingButton = loadImage("setting.png");
		  backButton = loadImage("back.png");
		  sky = loadImage("sky.jpg");
		  basket = loadImage("basket.jpg");
	}

	public void draw() {
		background(127, 217, 255);
		image(sky,0,0,width,height);

		if (isSettingPage) {
			
			textSize(50);
			text("SETTINGS", 140, 75);
			textSize(20);
			text("Move left : left arrow", 150, 125);
			text("Move right : right arrow", 150, 150);
			text("Pause : p", 150, 175);
			text("Quick drop : down arrow", 150, 200);
			text("Hard drop : spacebar", 150, 225);
			
		} else {
			question.draw(this);
			image(backButton,27,400,width/10,height/10);
			image(basket,225,450,width/5,height/5);

			
		}
		image(settingButton,440,10,width/10,height/10);

		
	}

	public void mousePressed() {
		if (!isSettingPage && setting.isPointInside(mouseX, mouseY)) {
			isSettingPage = true;
		} else if (isSettingPage && setting.isPointInside(mouseX, mouseY)) {
			isSettingPage = false;
		} else if (!isSettingPage && back.isPointInside(mouseX, mouseY)) {
			//popup
			int choice = JOptionPane.showConfirmDialog(window, "Are you sure you want to exit? Your progress will not be saved", "exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
			if (choice == 0) {
				Menu menu = new Menu();
				window.dispose();
			}
		}
		
	}
	
	public void keyPressed() {
		if (key == 'p') {
			isPaused = !isPaused;
		} else if (key == ' ') { 
			
		} else if (key == CODED) {
			if (key == LEFT) {
				
			} else if (key == RIGHT) {
				
			} else if (key == DOWN) {
				
			} 
		}
	}

}
