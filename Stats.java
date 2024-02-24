package tp_collection;
import java.util.ArrayList;
import java.util.Collections;

public class Stats {
	public static Note meilleureNote(Etudiant e)
	{
		return Collections.max(e.getNotes());
	}
	public static Note minNote(Etudiant e)
	{
		return Collections.min(e.getNotes());
	}
	public float moyNote(Etudiant e) {
	    ArrayList<Note> notes = e.getNotes();
	    float sum = 0;
	    for (Note note : notes) {
	        sum += note.getValue();
	    }
	    return sum / notes.size();
	}
	public float moyGroupe(ArrayList<Etudiant> etudiants) {	  
		float sum = 0;
			for (Etudiant e : etudiants) {
			       sum += moyNote(e);
			 }
			     return sum / etudiants.size();
		}
	
	
	
	public Etudiant meilleurEtudiant(ArrayList<Etudiant> etudiants) {  
		    return (Collections.max(etudiants));
		}

		public Etudiant moinsbonEtudiant(ArrayList<Etudiant> etudiants) {  
		    return (Collections.min(etudiants));
		}
	

}
