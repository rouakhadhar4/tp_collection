package tp2javaarraylist;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Patient {
	    private String nom;
	    private Set<String> ordonnance;

	    public Patient(String n) {
	        nom = n;
	        ordonnance = new HashSet<>();
	    }

	    public String getNom() {
	        return nom;
	    }

	 
	    public boolean ordonnanceVide() {
	        return ordonnance.isEmpty();
	    }

	  
	    public void ajoutMedicament(String m) {
	        ordonnance.add(m);
	    }

	  
	    public void affiche() {
	        System.out.println(" le Nom du patient : " + nom);
	        System.out.println("l'Ordonnance est  : " + ordonnance);
	    }

	   
	    public boolean contientMedicament(String m) {
	        return ordonnance.contains(m);
	    }


	    public void trieOrdonnance() {
	        Set<String> sortedOrdonnance = new TreeSet<>(ordonnance);
	        ordonnance.clear();
	        ordonnance.addAll(sortedOrdonnance);
	    }
	}
