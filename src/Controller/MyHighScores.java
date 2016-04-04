package Controller;

public class MyHighScores {
	private String text;
	private int score;
	
	public MyHighScores(int score, String text){
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
