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
	private PImage settingButton, backButton, sky;
	private JFrame window;
	private boolean hardDrop;

	private Basket basket;
	private Cloud[] cloud = new Cloud[4];
	private int frequency;
	private int qIndex;
	private Question q[] = new Question[50];

	public GameBoard() {
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

		// initiating fields
		setting = new Rectangle(10, 125, 45, 45);

		question = new Rectangle(0, 0, 500, 75);
		question.setfill(255, 255, 255);

		back = new Rectangle(10, 75, 45, 45);

		isSettingPage = false;
		isPaused = false;

		basket = new Basket("basket.png", 250, 375);

		for (int i = 0; i < 4; i++) {
			cloud[i] = new Cloud("cloud.png", i * 100 + 50, 100);
		}

		frequency = 0;

		q[0] = new Question("a", "a", "a", new String[] { "a", "b", "c", "d" }, "a");
		q[1] = new Question("a", "a", "a", new String[] { "a", "b", "c", "d" }, "a");

		qIndex = 0;
	}

	public void setup() {
		settingButton = loadImage("setting.png");
		backButton = loadImage("back.png");
		sky = loadImage("sky.jpg");
		basket.setup(this);
		for (int i = 0; i < 4; i++) {
			cloud[i].setup(this);
		}

//		  frameRate(30);

	}

	public void draw() {
		background(127, 217, 255);
		textAlign(CENTER, CENTER);
		image(sky, 0, 0, width, height);

		image(settingButton, 9, 125, width / 10, height / 10);

		if (isSettingPage) {
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
			fill(0);
			question.draw(this);
			text(q[qIndex].getSentence(), 250, 35);
			String ans[] = q[qIndex].getAnswers();

			image(backButton, 9, 75, width / 10, height / 10);
			basket.draw(this);
			for (int i = 0; i < 4; i++) {
				cloud[i].draw(this);
			}
			textSize(25);
			for (int i = 0; i < 4; i++) {
				text(ans[i], 100 * (i + 1), cloud[i].getY() + 45);
			}
			if (!isPaused) {
				if (hardDrop) {
					int index = (int) basket.getX() / 100;
					if (ans[index].equals(q[qIndex].getAnswer())) {
						System.out.println("correct");
					} else {
						System.out.println("wrong");
					}
					refresh();
					hardDrop = false;
				} else {
					if (frequency == 0) {
						frequency = 0;
						for (int i = 0; i < 4; i++) {
							cloud[i].drop();
						}
					}
					if (cloud[0].getY() > 325) {
						int index = (int) basket.getX() / 100;
						refresh();
						if (ans[index].equals(q[qIndex].getAnswer())) {
							System.out.println("correct");
						} else {
							System.out.println("wrong");
						}
					}
				}
			}

		}

	}

	public void mousePressed() {
		if (!isSettingPage && setting.isPointInside(mouseX, mouseY)) {
			isSettingPage = true;
		} else if (isSettingPage && setting.isPointInside(mouseX, mouseY)) {
			isSettingPage = false;
		} else if (!isSettingPage && back.isPointInside(mouseX, mouseY)) {
			// popup
			int choice = JOptionPane.showConfirmDialog(window,
					"Are you sure you want to exit? Your progress will not be saved", "exit", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (choice == 0) {
				new Menu();
				window.dispose();
			}
		}

	}

	public void keyPressed() {
		if (key == 'p') {
			isPaused = !isPaused;

		} else if (key == ' ' && !isPaused) {
			hardDrop = true;
		} else if (key == CODED && !isPaused) {
			if (keyCode == LEFT) {
				basket.move(-100);
			} else if (keyCode == RIGHT) {
				basket.move(100);
			} else if (keyCode == DOWN) {
				for (int i = 0; i < 4; i++) {
					cloud[i].drop();
				}
			}
		}
	}

	public void refresh() {
		for (int i = 0; i < 4; i++) {
			cloud[i].setY(100);
		}

		qIndex = 0;
		q[qIndex].shuffle();
	}

}