package tp_collection;

import java.util.ArrayList;
import java.util.Collections;

public class test {

	public static void main(String[] args) {
	

	       ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
	       ArrayList<Note> listeNotes = new ArrayList<>();
	       Stats stats = new Stats();
		      Etudiant etudiant1 = new Etudiant("12345", "roua");
		        etudiant1.addNote("Mathématique ", 80f);
		        etudiant1.addNote("informatique", 75f);
		       

		        Etudiant etudiant2 = new Etudiant("24678", "ahlem");
		        etudiant2.addNote("Science", 85f);
		        etudiant2.addNote("informatique", 90f);

		        Etudiant etudiant3 = new Etudiant("4689", "hatem");
		        etudiant3.addNote("informatique", 70);
		        etudiant3.addNote("Science", 65);

		         listeEtudiants.add(etudiant1);
		        listeEtudiants.add(etudiant2);
		        listeEtudiants.add(etudiant3);
		        System.out.println( listeEtudiants.toString());
		        
		        Collections.sort(listeEtudiants,new CompareNom());
		        System.out.println("Tri par nom : ");
			        for (Etudiant etudiant : listeEtudiants) {
			            System.out.println(etudiant.getMatricule() + " - " + etudiant.getNom());
			        }
			   
			    Collections.sort(listeEtudiants,new MatriculeComparator());
			    System.out.println("Tri par matricule : ");
			    for (Etudiant etudiant : listeEtudiants) {
				     System.out.println(etudiant.getMatricule() + " - " + etudiant.getNom());
				}
		
		        System.out.println("les moyennes étudiant : ");
		        for (Etudiant etudiant : listeEtudiants) {
		            System.out.println("Etudiant: " + etudiant.getNom());
		            System.out.println("Moyenne: " + etudiant.getValue());
		            System.out.println("Meilleure note : " + Stats.meilleureNote(etudiant));
		            System.out.println("Moins bonne note : " + Stats.minNote(etudiant));
		            System.out.println();
		            
		           

		           
		}
		        System.out.println("les groupes : ");
		        System.out.println("Moyenne du groupe : " + stats.moyGroupe(listeEtudiants));
		    
                Etudiant meilleurEtudiant = stats.meilleurEtudiant(listeEtudiants);
		        Etudiant moinsBonEtudiant = stats.moinsbonEtudiant(listeEtudiants);

		        System.out.println("Meilleur étudiant du groupe : ");
		        System.out.println("Matricule : " + meilleurEtudiant.getMatricule());
		        System.out.println("Nom : " + meilleurEtudiant.getNom());
		        System.out.println("Moyenne : " + stats.moyNote(meilleurEtudiant));
		       

		        System.out.println("Moins bon étudiant du groupe : ");
		        System.out.println("Matricule : " + moinsBonEtudiant.getMatricule());
		        System.out.println("Nom : " + moinsBonEtudiant.getNom());
		        System.out.println("Moyenne : " + stats.moyNote(moinsBonEtudiant));
		    
		      
		        
		       
		}


	}


