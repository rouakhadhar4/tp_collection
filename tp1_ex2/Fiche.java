package tp1_ex2;

import java.util.HashMap;
import java.util.Map;

class Fiche {
    private String nom;
    private String numero;
    private String adresse;

    public Fiche(String nom, String numero, String adresse) {
        this.nom = nom;
        this.numero = numero;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String toString() {
        return nom + " : " + numero + ", " + adresse;
    }
}


