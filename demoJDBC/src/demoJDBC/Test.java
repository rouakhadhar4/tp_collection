package demoJDBC;

import java.util.List;

import ma.projet.beans.client;
import ma.projet.connexion.connexion;
import ma.projet.service.ClientService;

public class Test {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        
        clientService.create(new client("SAFI", "Ali"));
        clientService.create(new client("ALAOUI", "Widane"));
        clientService.create(new client("RAMI", "Amine"));
        clientService.create(new client("ALAMI", "Kamal"));
        clientService.create(new client("SELAMI", "Mohamed"));

       
        client clientWithId3 = clientService.findById(3);
        if (clientWithId3 != null) {
            System.out.println("Client avec ID 3 : " + clientWithId3.getNom() + " " + clientWithId3.getPrenom());
        } else {
            System.out.println("Aucun client trouvé avec l'ID 3");
        }

       
        client clientToDelete = clientService.findById(3);
        if (clientToDelete != null) {
            clientService.delete(clientToDelete);
            System.out.println("Client avec ID 3 supprimé avec succès");
        } else {
            System.out.println("Aucun client trouvé avec l'ID 3, suppression impossible");
        }

       
        client clientToUpdate = clientService.findById(2);
        if (clientToUpdate != null) {
            clientToUpdate.setNom("roua");
            clientToUpdate.setPrenom("khadhar");
            clientService.update(clientToUpdate);
            System.out.println("Client avec ID 2 mis à jour avec succès");
        } else {
            System.out.println("Aucun client trouvé avec l'ID 2, mise à jour impossible");
        }

      
        List<client> allClients = clientService.findAll();
        System.out.println("Liste de tous les clients :");
        for (client client : allClients) {
            System.out.println(client.getNom() + " " + client.getPrenom());
        }
    }
}
