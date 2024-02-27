package tp2javaarraylist;

import java.util.Collection;

public class test{

	public static void main(String[] args) {
	
	
	        DossierPharmacie pharmacie = new DossierPharmacie("Ma Pharmacie");

	        pharmacie.nouveauPatient("Hatem", new String[]{"Paracétamol", "Aspirine"});
	        pharmacie.nouveauPatient("roua", new String[]{"Ibuprofène", "Paracétamol"});
	        pharmacie.nouveauPatient("Ali", new String[]{"Aspirine", "Ibuprofène"});
	        pharmacie.nouveauPatient("salah", new String[]{"Aspirine", "Paracétamol", "Ibuprofène"});

	        pharmacie.affiche();

	        pharmacie.ajoutMedicament("Hatem", "Ibuprofène");
	        pharmacie.affichePatient("Hatem");

	        Collection<String> patientsAvecIbuprofene = pharmacie.affichePatientAvecMedicament("Ibuprofène");
	        System.out.println("Patients ayant pris de l'Ibuprofène : " + patientsAvecIbuprofene);
	    }
	}




