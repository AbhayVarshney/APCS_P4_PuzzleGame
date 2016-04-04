package Controller;

public class HighScores {
	private String text;
	private int score;
	
	public HighScores(int score, String text){
		this.score = score;
		this.text = text;
	}
	
	public int getScore(){
		return score;
	}
	
	public String getText(){
		return text;
	}
}
