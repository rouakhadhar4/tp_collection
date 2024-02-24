package tp_collection;
import java.util.ArrayList;

public class Etudiant implements Statisticable , Comparable<Etudiant> {
	private String matricule;
	private String nom;
	private ArrayList<Note> notes;
	
	public Etudiant(String matricule,String nom) {
		this.matricule=matricule;
		this.nom=nom;
		notes = new ArrayList<>(); 
	}

	@Override
	public String toString() {
		return "Etudiant [matricule=" + matricule + ", nom=" + nom + ", notes=" + notes + "]";
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}

	@Override
	public float getValue() {
		float somme =0;
		for(Note i : notes) {
			somme+=i.getNote();
		}
		return somme/notes.size();
	}
	
	public void addNote(String cours,float note) {
		this.notes.add(new Note(cours,note));
		
	}
	
	@Override 
	public int compareTo(Etudiant e) {
		if(this.getValue()== e.getValue()) {
			return 0;
		}
		else if(this.getValue()> e.getValue()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	

}
