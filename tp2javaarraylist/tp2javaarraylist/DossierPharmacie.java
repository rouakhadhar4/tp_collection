package tp2javaarraylist;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class DossierPharmacie {
	    private String nom;
	    private HashMap<String, Patient> patients;

	    public DossierPharmacie(String n) {
	        nom = n;
	        patients = new HashMap<String, Patient>();
	    }

	   
	    void nouveauPatient(String nom, String[] ord) {
	        Patient patient = new Patient(nom);
	        for (String medicament : ord) {
	            patient.ajoutMedicament(medicament);
	        }
	        patients.put(nom.toLowerCase(), patient);
	    }

	   
	    public boolean ajoutMedicament(String nom, String m) {
	        Patient patient = patients.get(nom.toLowerCase());
	        if (patient != null) {
	            patient.ajoutMedicament(m);
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public void affichePatient(String nom) {
	        Patient patient = patients.get(nom.toLowerCase());
	        if (patient != null) {
	            patient.affiche();
	        } else {
	            System.out.println("Le patient n'existe pas.");
	        }
	    }

	 
	    public void affiche() {
	        System.out.println(" le Nom de la pharmacie : " + nom);
	        System.out.println(" la Liste des patients : ");
	        for (Patient patient : patients.values()) {
	            System.out.println("Nom du patient : " + patient.getNom());
	        }
	    }

	 
	    public Collection<String> affichePatientAvecMedicament(String m) {
	        ArrayList<String> patientsAvecMedicament = new ArrayList<>();
	        for (Patient patient : patients.values()) {
	            if (patient.contientMedicament(m)) {
	                patientsAvecMedicament.add(patient.getNom());
	            }
	        }
	        return patientsAvecMedicament;
	    }
	}
