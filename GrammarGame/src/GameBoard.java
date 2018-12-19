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
	private int qIndex;
	private Question q[] = new Question[50];

	public GameBoard() {
		PApplet.runSketch(new String[] { "" }, this);
		PSurfaceAWT surf = (PSurfaceAWT) this.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();

		window = (JFrame) canvas.getFrame();

		window.setBounds(200, 100, 500, 500);
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

		q[0] = new Question("Jack and Jill ______ up the hill (run)", new String[] { "run", "runs", "runes", "runies" },
				"\"Jack and Jill\"", "\"run\"", "plural",
				"In order to change a verb to match a plural subject, you will leave it in its standard form.");

		q[1] = new Question("You ____ a smart boy (is)", new String[] { "are", "is", "ises", "ares" }, "\"you\"",
				"\"is\"", "singular",
				"Because \"you\" is treated as plural, \"are\" is the correct verb that is used for plural subjects.\n\"Is\" is not a standard verb. For a singlular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[2] = new Question("My mother _________ my dreams (support)",
				new String[] { "supports", "support", "supportes", "supported" }, "\"mother\"", "\"support\"",
				"singular",
				"To change a verb to match a singular subject, you will add an \"es\" or \"s.\"\nIn this case, you will add \"s\" at the end.");

		q[3] = new Question("The girl _____ happy (is)", new String[] { "is", "are", "ises", "iss" }, "\"girl\"",
				"\"is\"", "singular",
				"\"Is\" is not a standard verb. For a singlular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[4] = new Question("The teacher ________ during school (teach)",
				new String[] { "teaches", "teach", "teachs", "teachies" }, "\"teacher\"", "\"teach\"", "singular",
				"To change a verb to match a singular subject, you will add an \"es\" or \"s.\"\nIn this case, you will add \"es\" at the end.");

		q[5] = new Question("The boy _________ the answers on the test (guess)",
				new String[] { "guesses", "guess", "guesss", "gueses" }, "\"boy\"", "\"guess\"", "singular",
				"To change a verb to match a singular subject, you will add an \"es\" or \"s.\"\nIn this case, you will add \"es\" at the end.");

		q[6] = new Question("The cars in the parking lot _________ clean (is)",
				new String[] { "are", "is", "ises", "ares" }, "\"cars\"", "\"is\"", "plural",
				"\"in the parking lot\" provides extra details but does not determine the subject.\n\"Is\" is not a standard verb. For a singlular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[7] = new Question("The monster under my bed _________ scary (is)",
				new String[] { "is", "are", "ises", "ares" }, "\"monster\"", "\"is\"", "singular",
				"\"under my bed\" provides extra details but does not determine the subject.\n\"Is\" is not a standard verb. For a singlular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[8] = new Question("The stores in the mall _________ at night (close)",
				new String[] { "close", "closes", "closs", "closees" }, "\"stores\"", "\"close\"", "plural",
				"\"in the mall\" provides extra details but does not determine the subject.\nIn order to change a verb to match a plural subject, you will leave it in its standard form.");

		q[9] = new Question("The bird in the forest _________ in the air (fly)",
				new String[] { "flies", "fly", "flys", "flyes" }, "\"bird\"", "\"fly\"", "singular",
				"\"in the forest\" provides extra details but does not determine the subject.\nWhen a verb end with \"y\" and the subject is singular, you change the \"-y\" to \"-ie\" and then add the \"s.\"");

		q[10] = new Question("Neither of the twins _________ sick (is)", new String[] { "is", "are", "ises", "ares" },
				"\"neither\"", "\"is\"", "singular",
				"\"of the twins\" provides extra details but does not determine the subject."
						+ "\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[11] = new Question("Everybody _________ to live and survive (eat)",
				new String[] { "eats", "eat", "eates", "eaties" }, "\"everybody\"", "\"eat\"", "singular",
				"\"everybody\" is always singular.\nTo change a verb to match a singular subject, you will add an \"es\" or \"s.\"\nIn this case, you will add \"s\" at the end.");

		q[12] = new Question("The data from my computer____ on my disc (is)",
				new String[] { "are", "is", "ises", "ares" }, "\"data\"", "\"is\"", "plural",
				"\"on my computer\" provides extra details but does not determine the subject.\n\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[13] = new Question("Either of the rooms at the hotel _________ available (is)",
				new String[] { "is", "are", "ises", "ares" }, "\"either\"", "\"is\"", "singular",
				"\"either\" is always singular.\n\"of the rooms at the hotel\" provides extra details but does not determine the subject.\n\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[14] = new Question("Either John or Mary _________ right (is)", new String[] { "is", "are", "ises", "ares" },
				"\"Mary\"", "\"is\"", "singular",
				"When \"either\" is followed by \"or,\" the verb is determined by the second subject.\n\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[15] = new Question("Neither John nor the Parkers _________ wrong (is)",
				new String[] { "are", "is", "ises", "ares" }, "\"Parkers\"", "\"is\"", "plural",
				"When \"neither\" is followed by \"nor,\" the verb is determined by the second subject.\n\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[16] = new Question("Either the Smiths or one of the Johnsons _____ expected to come (is)",
				new String[] { "is", "are", "ises", "ares" }, "\"one\"", "\"is\"", "singular",
				"\"of the Johnsons\" provides extra details but does not determine the subject.\nWhen \"either\" is followed by \"or,\" the verb is determined by the second subject.\n\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[17] = new Question("There _____ a cat in the house (is)", new String[] { "is", "are", "ises", "ares" },
				"\"cat\"", "\"is\"", "singular",
				"When the sentence starts with \"there,\" the subject comes after the verb.\n\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		q[18] = new Question("There _____ many rodents in the city (is)", new String[] { "are", "is", "ises", "ares" },
				"\"rodents\"", "\"is\"", "plural",
				"When the sentence starts with \"there,\" the subject comes after the verb.\n\"Is\" is not a standard verb. For a singular subject, it will remain the same.\nHowever, for a plural subject, it will change to \"are.\"");

		qIndex = 0;
		hardDrop = false;
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
		q[qIndex].shuffle();

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
			textSize(18);
			if (qIndex == 16) {
				textSize(14);
			}
			question.draw(this);
			text(q[qIndex].getSentence(), 250, 35);
			String ans[] = q[qIndex].getAnswers();

			image(backButton, 9, 75, width / 10, height / 10);
			basket.draw(this);
			for (int i = 0; i < 4; i++) {
				cloud[i].draw(this);
			}
			textSize(15);
			for (int i = 0; i < 4; i++) {
				text(ans[i], 100 * (i + 1), cloud[i].getY() + 45);
			}
			if (!isPaused) {
				if (hardDrop) {
					int index = (int) basket.getX() / 100;
					answer(ans[index].equals(q[qIndex].getAnswer()));
					refresh();

				} else {

					for (int i = 0; i < 4; i++) {
						cloud[i].drop();
					}

					if (cloud[0].getY() > 325) {
						int index = (int) basket.getX() / 100;
						answer(ans[index].equals(q[qIndex].getAnswer()));

						refresh();

					}
				}
			} else {
				textSize(50);
				fill(255);
				text("PAUSE", 250, 250);
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

	public void answer(boolean answer) {

		Object[] options = { "OK", "SHOW MORE" };
//		JOptionPane.showMessageDialog(window,
//				"Subject-verb agreement states that the plurality of the subject must match the plurality of the verb.\n"
//						+ q[qIndex].getMessage(),
//				answer ? "CORRECT" : "INCORRECT", answer ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

		int choice = JOptionPane.showOptionDialog(window, q[qIndex].getMessage(), answer ? "CORRECT" : "INCORRECT",
				JOptionPane.YES_NO_OPTION, answer ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE, null,
				options, options[0]);
		
		if (choice == 1) {
			JOptionPane.showMessageDialog(window, q[qIndex].getSpecialMessage(), "EXPLANATION", JOptionPane.INFORMATION_MESSAGE);
		}
		hardDrop = false;
	}

	public void refresh() {
		for (int i = 0; i < 4; i++) {
			cloud[i].setY(100);
		}

		if (qIndex == 18) {
			qIndex = 0;
		} else {
			qIndex++;
		}
		q[qIndex].switchState();
		q[qIndex].shuffle();
	}

}