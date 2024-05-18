
package application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class List {
	  @FXML
	    private Button affichebt;
	  @FXML
	  private TableColumn<Hotel, Integer> etoiles;
	

   

    @FXML
    private TableColumn<Hotel, Integer> id_hotel;

    @FXML
    private TableColumn<Hotel, String> nom;

    @FXML
    private TableColumn<Hotel, String> adresse;

    @FXML
    private TableColumn<Hotel, String> ville;

    @FXML
    private TableColumn<Hotel, String> pays;
    

    @FXML
    private TableColumn<Hotel, String> description;

    @FXML
    private TableColumn<Hotel, String> image_Url;
    @FXML
    private ImageView image; 
    @FXML
    private TableView<Hotel> TableView;

    @FXML
    private Label Labelinfos;

    @FXML
    private ImageView imageView;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;
    public TableView<Hotel> getTableView() {
        return TableView;
    }

    public TableColumn<Hotel, Integer> getIdColumn() {
        return id_hotel;
    }

    public TableColumn<Hotel, String> getNomColumn() {
        return nom;
    }

  

   
        
     
    @FXML
    private void initialize() {
        id_hotel.setCellValueFactory(new PropertyValueFactory<>("id")); // Assuming "id" is the property name in your Hotel class
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        etoiles.setCellValueFactory(new PropertyValueFactory<>("etoiles"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image_Url.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));

        // Utilisez la cellule personnalisée pour la colonne d'image
        image_Url.setCellFactory(column -> new ImageTableCell<>());
        
    }

    @SuppressWarnings("unused")
    private void showHotelDetails(Hotel hotel) {
        if (hotel != null) {
            // Affichez les détails de l'hôtel sélectionné
            Labelinfos.setText(hotel.toString());

            // Affichez l'image de l'hôtel sélectionné
            String imageUrl = hotel.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Image image = new Image(imageUrl);
                imageView.setImage(image);
            } else {
                // Effacez l'image si l'URL est vide ou nulle
                imageView.setImage(null);
            }
        } else {
            // Effacez les détails si aucun hôtel n'est sélectionné
            Labelinfos.setText("");
            imageView.setImage(null);
        }
    }

    @FXML
    private void deleteButton() {
        // Obtenez l'élément sélectionné dans la table
        Hotel selectedHotel = TableView.getSelectionModel().getSelectedItem();

        if (selectedHotel != null) {
            // Afficher une boîte de dialogue de confirmation de suppression
            Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation de suppression");
            confirmationDialog.setHeaderText("Supprimer l'hôtel sélectionné ?");
            confirmationDialog.setContentText("Êtes-vous sûr de vouloir supprimer l'hôtel sélectionné ?");
            
            // Attendre la réponse de l'utilisateur
            confirmationDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Supprimer l'hôtel sélectionné de la base de données
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agence", "root", "");
                        String query = "DELETE FROM hotel WHERE id_hotel = ?";
                        PreparedStatement statement = conn.prepareStatement(query);
                        statement.setInt(1, selectedHotel.getId());
                        int rowsDeleted = statement.executeUpdate();
                        conn.close();
                        if (rowsDeleted > 0) {
                            // Si la suppression réussit, supprimez également l'élément de la table
                            TableView.getItems().remove(selectedHotel);
                            showAlert("Suppression réussie", "Hôtel supprimé avec succès", "L'hôtel a été supprimé de la base de données et de la liste.");
                        } else {
                            showAlert("Erreur de suppression", "Échec de la suppression", "Impossible de supprimer l'hôtel de la base de données.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        showAlert("Erreur de base de données", "Erreur de suppression", "Une erreur s'est produite lors de la suppression de l'hôtel de la base de données.");
                    }
                }
            });
        } else {
            showAlert("Aucune sélection", "Aucun hôtel sélectionné", "Veuillez sélectionner un hôtel à supprimer.");
        }
    }

    @FXML
    private void insertButton() {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouthotel.fxml"));
            Parent root = loader.load();
            
            // Créer une nouvelle scène
            Scene scene = new Scene(root);
            
            // Créer une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel hôtel");
            stage.setScene(scene);
            
            // Afficher la fenêtre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateHotel(Hotel hotel) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agence", "root", "");

            String query = "UPDATE hotel SET nom = ?, adresse = ?, ville = ?, pays = ?, etoiles = ?, description = ?, image_url = ? WHERE id_hotel = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            
            statement.setString(1, hotel.getNom());
            statement.setString(2, hotel.getAdresse());
            statement.setString(3, hotel.getVille());
            statement.setString(4, hotel.getPays());
            statement.setInt(5, hotel.getEtoiles());
            statement.setString(6, hotel.getDescription());
            statement.setString(7, hotel.getImageUrl());
            statement.setInt(8, hotel.getId());
            
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                showAlert("Update Successful", "Hotel Updated", "The hotel information has been updated successfully.");
                // Afficher l'image mise à jour après la mise à jour réussie
                showUpdatedImage(hotel.getImageUrl());
            } else {
                showAlert("Update Failed", "Hotel Not Updated", "Failed to update hotel information.");
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Error Updating Hotel", "An error occurred while updating hotel information.");
        }
    }

    // Méthode pour afficher l'image mise à jour
    private void showUpdatedImage(String imageUrl) {
        // Ici, vous devez implémenter la logique pour afficher l'image mise à jour.
        // Cela dépend de la façon dont vous affichez normalement les images dans votre interface utilisateur.
        // Si vous utilisez JavaFX, vous pouvez mettre à jour l'ImageView appropriée avec la nouvelle image.
        // Par exemple :
        // Image updatedImage = new Image(imageUrl);
        // imageView.setImage(updatedImage);
        // Assurez-vous de gérer les exceptions lors du chargement de l'image.
    }

        @FXML
        private void updateButton() {
            // Récupérer l'hôtel sélectionné dans le TableView
            Hotel selectedHotel = TableView.getSelectionModel().getSelectedItem();

            // Vérifier si un hôtel est sélectionné
            if (selectedHotel != null) {
                // Afficher un dialogue de formulaire pour saisir les nouvelles valeurs de l'hôtel
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Update Hotel");
                dialog.setHeaderText("Enter new values for the hotel:");

                // Créer les champs de texte pour les attributs de l'hôtel (sauf pour l'URL de l'image)
                TextField nomField = new TextField(selectedHotel.getNom());
                TextField adresseField = new TextField(selectedHotel.getAdresse());
                TextField villeField = new TextField(selectedHotel.getVille());
                TextField paysField = new TextField(selectedHotel.getPays());
                TextField etoilesField = new TextField(String.valueOf(selectedHotel.getEtoiles()));
                TextField descriptionField = new TextField(selectedHotel.getDescription());

                // Créer une image view pour afficher l'image de l'hôtel
                ImageView imageView = new ImageView();
                imageView.setFitWidth(200); // Réglage de la largeur pour l'affichage
                imageView.setPreserveRatio(true);
                // Charger et afficher l'image actuelle de l'hôtel
                Image currentImage = new Image(selectedHotel.getImageUrl());
                imageView.setImage(currentImage);

                // Créer un bouton pour sélectionner une nouvelle image
                Button selectImageButton = new Button("Select Image");
                selectImageButton.setOnAction(e -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Select Image");
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                    File selectedFile = fileChooser.showOpenDialog(dialog.getDialogPane().getScene().getWindow());
                    if (selectedFile != null) {
                        // Charger et afficher la nouvelle image sélectionnée
                        Image newImage = new Image(selectedFile.toURI().toString());
                        imageView.setImage(newImage);
                    }
                });

                // Créer les labels correspondants
                Label nomLabel = new Label("Nom:");
                Label adresseLabel = new Label("Adresse:");
                Label villeLabel = new Label("Ville:");
                Label paysLabel = new Label("Pays:");
                Label etoilesLabel = new Label("Étoiles:");
                Label descriptionLabel = new Label("Description:");

                // Ajouter les champs de texte et les labels au dialogue
                GridPane grid = new GridPane();
                grid.add(nomLabel, 0, 0);
                grid.add(nomField, 1, 0);
                grid.add(adresseLabel, 0, 1);
                grid.add(adresseField, 1, 1);
                grid.add(villeLabel, 0, 2);
                grid.add(villeField, 1, 2);
                grid.add(paysLabel, 0, 3);
                grid.add(paysField, 1, 3);
                grid.add(etoilesLabel, 0, 4);
                grid.add(etoilesField, 1, 4);
                grid.add(descriptionLabel, 0, 5);
                grid.add(descriptionField, 1, 5);
                grid.add(imageView, 0, 6); // Ajouter l'image view à la grille
                grid.add(selectImageButton, 1, 6); // Ajouter le bouton de sélection d'image à la grille
                dialog.getDialogPane().setContent(grid);

                // Ajouter les boutons de confirmation et d'annulation au dialogue
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                // Attendre que l'utilisateur confirme ou annule
                dialog.showAndWait().ifPresent(result -> {
                    if (result == ButtonType.OK) {
                        // Mettre à jour l'hôtel avec les nouvelles valeurs
                        selectedHotel.setNom(nomField.getText());
                        selectedHotel.setAdresse(adresseField.getText());
                        selectedHotel.setVille(villeField.getText());
                        selectedHotel.setPays(paysField.getText());
                        selectedHotel.setEtoiles(Integer.parseInt(etoilesField.getText()));
                        selectedHotel.setDescription(descriptionField.getText());
                        // Mettre à jour l'URL de l'image avec l'URL de la nouvelle image sélectionnée
                        selectedHotel.setImageUrl(imageView.getImage().getUrl());
                        // Mettre à jour l'hôtel dans la base de données
                        updateHotel(selectedHotel);
                    }
                });
            } else {
                showAlert("No Selection", "No Hotel Selected", "Please select a hotel to update.");
            }
        }

	
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void afficheButton() {
        loadHotels();
    }

    private void loadHotels() {
        ObservableList<Hotel> hotelList = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agence", "root", "");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel");

            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id_hotel"));
                hotel.setNom(resultSet.getString("nom"));
                hotel.setAdresse(resultSet.getString("adresse"));
                hotel.setVille(resultSet.getString("ville"));
                hotel.setPays(resultSet.getString("pays"));
                hotel.setEtoiles(resultSet.getInt("etoiles"));
                hotel.setDescription(resultSet.getString("description"));
                hotel.setImageUrl(resultSet.getString("image_url"));

                hotelList.add(hotel);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableView.setItems(hotelList);
    }
}

  

