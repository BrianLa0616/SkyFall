
public class Question {

	private String sentence, subject, verb;
	private String[] answers;
	private String answer, plurality;
	private boolean[] used;

	public Question(String sent, String sub, String ver, String[] ans,String plurality) {
		sentence = sent;
		subject = sub;
		verb = ver;

		answers = ans;
		answer = answers[0];

		used = new boolean[4];

		for (int i = 0; i < 4; i++) {
			used[i] = false;
		}
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
		}
	}
	
	public String getPlurality() {
		return plurality;
	}
}
