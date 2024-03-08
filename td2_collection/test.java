package td2_collection;

public class test {

	public static void main(String[] args) {
		    Article A1 = new Article("pantalon", 54.8);
	        Article A2 = new Article("chemise", 67.8);

	        Stock stock = new Stock();
	        stock.addNouvArticle(A1, 6);
	        stock.addNouvArticle(A2, 7);

	        System.out.println("le Stock avant vente est :");
	        stock.sortStock();

	      
	        Facture facture = new Facture(stock);

	    
	        facture.addLigne(4, "pantalon");
	        facture.addLigne(3, "chemise");
	        System.out.println("Montant total de la facture : " + facture.getMontantTotal());

	        System.out.println("le Stock après vente est :");
	        stock.sortStock();
		}
	
		

	}


