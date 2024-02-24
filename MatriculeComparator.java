package tp_collection;

import java.util.Comparator;

public class MatriculeComparator implements Comparator<Etudiant>   {
    public int compare(Etudiant o1, Etudiant o2) {
        return o1.getMatricule().compareTo(o2.getMatricule());
    }

	
}
