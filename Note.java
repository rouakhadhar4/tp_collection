package tp_collection;

public class Note implements Statisticable , Comparable<Note>{
	@Override
	public String toString() {
		return "Note [cours=" + cours + ", note=" + note + "]";
	}

	private String cours;
	private float note;
	
	public Note(String cours,float note ) {
		this.cours=cours;
		this.note=note;
	}

	public String getCours() {
		return cours;
	}

	public void setCours(String cours) {
		this.cours = cours;
	}

	public double getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}
	
	public float getValue() {
		return this.note;
	}
	
	@Override 
	public int compareTo(Note n) {
		if(this.getValue()== n.getValue()) {
			return 0;
		}
		else if(this.getValue()> n.getValue()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	
	

}