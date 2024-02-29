package tp1_ex2;

import java.util.Scanner;

public class test {
	public static void main(String[] args) {
	    Annuaire annuaire = new Annuaire();
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        String commande = scanner.nextLine();

	        
	         if (commande.startsWith("+")) {
	            String nom = commande.substring(1);
	            System.out.println("Entrez le num :");
	            String numero = scanner.nextLine();
	            System.out.println("Entrez l'adresse :");
	            String adresse = scanner.nextLine();
	            Fiche fiche = new Fiche(nom, numero, adresse);
	            annuaire.ajouterFiche(nom, fiche);
	            System.out.println("ajout : " + nom);
	            annuaire.afficherAnnuaire();
	          
	            
	        } else if (commande.startsWith("?")) {
	            String nom = commande.substring(1);
	            Fiche fiche = annuaire.rechercherFiche(nom);
	            if (fiche != null) {
	                System.out.println(fiche);
	            } else {
	                System.out.println("Aucune fiche trouvée pour le nom : " + nom);
	            }
	        } else if (commande.equals("!")) {
	            annuaire.afficherAnnuaire();
	        }
	    

	   // scanner.close();
	}
	}

}