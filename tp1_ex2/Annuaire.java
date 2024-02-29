package tp1_ex2;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Annuaire {
    private Map<String, Fiche> annuaire;

    public Annuaire() {
        annuaire = new HashMap<>();
    }

    public void ajouterFiche(String nom, Fiche fiche) {
        annuaire.put(nom, fiche);
    }

    public void afficherAnnuaire() {
        for (Fiche fiche : annuaire.values()) {
            System.out.println(fiche);
        }
    }

    public Fiche rechercherFiche(String nom) {
        return annuaire.get(nom);
    }
}
	