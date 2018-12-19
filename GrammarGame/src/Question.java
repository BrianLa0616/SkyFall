
public class Question {

	private String sentence, subject, verb, plurality;
	private String[] answers;
	private String answer, specialMessage;
	private boolean[] used;
	private boolean completed;

	public Question(String sent, String[] ans, String subject, String verb, String plurality, String specialMessage) {
		sentence = sent;

		answers = ans;
		answer = answers[0];

		used = new boolean[4];

		for (int i = 0; i < 4; i++) {
			used[i] = false;
		}
		this.specialMessage = specialMessage;
		completed = false;
		this.subject = subject;
		this.verb = verb;
		this.plurality = plurality;
	}

	public String getSentence() {
		return sentence;
	}

	public String getSubject() {
		return subject;
	}

	public String getVerb() {
		return verb;
	}

	public String[] getAnswers() {
		return answers;
	}

	public String getAnswer() {
		return answer;
	}

	public void shuffle() {

		String[] temp = new String[4];
		for (int i = 0; i < 4; i++) {
			temp[i] = answers[i];
		}

		int index = (int) (Math.random() * 4);
		answers[0] = temp[index];
		used[index] = true;
		for (int i = 1; i < 3; i++) {
			index = (int) (Math.random() * 4);
			while (used[index]) {
				index = (int) (Math.random() * 4);
			}
			answers[i] = temp[index];
			used[index] = true;
		}

		for (int i = 0; i < 4; i++) {
			if (!used[i]) {
				answers[3] = temp[i];
			}
			used[i] = false;
		}
	}

	public String getSpecialMessage() {
		return specialMessage;
	}

	public boolean getState() {
		return completed;
	}

	public void switchState() {
		completed = true;
	}

	public String getMessage() {
		return "In this sentence, the subject, " + subject + ", is " + plurality + ", making the answer \"" + answer
				+ "\", the " + plurality + " form of " + verb;
	}
}
