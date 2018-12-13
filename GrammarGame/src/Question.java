
public class Question {

	private String sentence, subject, verb;
	private String[] answers;
	public Question(String sent, String sub, String ver, String[] ans) {
		sentence = sent;
		subject = sub;
		verb = ver;
		answers = ans;
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
}
