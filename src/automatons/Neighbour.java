package automatons;

public class Neighbour {
	String letter;
	String number;
	
	public Neighbour(String letter, String number) {
		super();
		this.letter = letter;
		this.number = number;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void concat(Neighbour a, Neighbour b) {
		
	}
	
	public void reduce() {
		
	}
}
