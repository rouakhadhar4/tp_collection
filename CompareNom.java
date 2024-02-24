package tp_collection;

import java.util.Comparator;

public class CompareNom implements Comparator<Etudiant> {
	public int compare(Etudiant o1, Etudiant o2) {
		return o1.getNom().compareTo(o2.getNom());
		
	}

	

	

}
