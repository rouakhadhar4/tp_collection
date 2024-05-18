package application;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;


public class ajoutcompany {

    @FXML
    private ImageView imageView1;

    @FXML
    private Button btnChooseImage;

    @FXML
    private TextField nom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField code;

    @FXML
    private TextField telephone;

    @FXML
    private TextField destination;

    @FXML
    private TextField periode;

    @FXML
    private TextField prix;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private Label Labelinfos;

    @FXML
    private ComboBox<String> nomHotelComboBox;

    @FXML
    private ImageView imageView;

    @FXML
    private void onBtnChooseImageAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Débogage : Affiche le chemin du fichier sélectionné
            System.out.println(selectedFile.toURI().toString());
            
            // Mettez à jour l'image de l'imageView avec l'image sélectionnée
            imageView.setImage(new Image(selectedFile.toURI().toString()));
        }
    }
    @FXML
    private void onBtnCancelAction() {
        // Réinitialisez tous les champs texte à vide
        
    }

    @FXML
    void onBtnSavecAction() {
        // Ajoutez le code pour l'action du bouton Enregistrer
    }

    @FXML
    void initialize() {
        // Ajoutez le code d'initialisation ici
    }
}
