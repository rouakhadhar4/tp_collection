package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.util.List;

public class ControllerEtudiant {

    @FXML
    private Button ajouterButton;

    @FXML
    private Button supprimerButton;

    @FXML
    private Button modifierButton;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private ToggleGroup sexeToggleGroup;

    @FXML
    private ChoiceBox<String> filiereChoiceBox;

    @FXML
    private TreeTableView<Etudiant> etudiantTable;

    private EtudiantM etudiantModel;

    @FXML
    public void initialize() {
        filiereChoiceBox.getItems().addAll("classe informatique", "classe electrique", "classe mecanique");
        setupTableView();
        etudiantModel = new EtudiantM();
        loadEtudiants();
    }

    private void setupTableView() {
        TreeTableColumn<Etudiant, Integer> idColumn = new TreeTableColumn<>("ID");
        TreeTableColumn<Etudiant, String> nomColumn = new TreeTableColumn<>("Nom");
        TreeTableColumn<Etudiant, String> prenomColumn = new TreeTableColumn<>("Prenom");
        TreeTableColumn<Etudiant, String> sexeColumn = new TreeTableColumn<>("Sexe");
        TreeTableColumn<Etudiant, String> filiereColumn = new TreeTableColumn<>("Filiere");

        idColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("prenom"));
        sexeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("sexe"));
        filiereColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("filiere"));

        etudiantTable.getColumns().addAll(idColumn, nomColumn, prenomColumn, sexeColumn, filiereColumn);
    }

    private void loadEtudiants() {
        List<Etudiant> etudiants = etudiantModel.findAll();
        TreeItem<Etudiant> root = new TreeItem<>();

        for (Etudiant etudiant : etudiants) {
            root.getChildren().add(new TreeItem<>(etudiant));
        }

        etudiantTable.setRoot(root);
        etudiantTable.setShowRoot(false);
    }

    @FXML
    private void ajouterButtonClicked() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        RadioButton selectedRadioButton = (RadioButton) sexeToggleGroup.getSelectedToggle();
        String sexe = selectedRadioButton.getText();
        String filiere = filiereChoiceBox.getValue();

        Etudiant newEtudiant = new Etudiant(nom, prenom, sexe, filiere);
        boolean success = etudiantModel.create(newEtudiant);

        if (success) {
            loadEtudiants();
        } else {
            showErrorAlert("Erreur lors de l'ajout !");
        }
    }

    @FXML
    private void supprimerButtonClicked() {
        TreeItem<Etudiant> selectedEtudiantItem = etudiantTable.getSelectionModel().getSelectedItem();

        if (selectedEtudiantItem != null) {
            Etudiant selectedEtudiant = selectedEtudiantItem.getValue();
            boolean success = etudiantModel.delete(selectedEtudiant);

            if (success) {
                loadEtudiants();
            } else {
                showErrorAlert("Erreur lors de la suppression de l'étudiant.");
            }
        } else {
            showErrorAlert("Aucun étudiant sélectionné.");
        }
    }

    @FXML
    private void modifierButtonClicked() {
        TreeItem<Etudiant> selectedEtudiantItem = etudiantTable.getSelectionModel().getSelectedItem();

        if (selectedEtudiantItem != null) {
            Etudiant selectedEtudiant = selectedEtudiantItem.getValue();
            selectedEtudiant.setNom(nomTextField.getText());
            selectedEtudiant.setPrenom(prenomTextField.getText());
            RadioButton selectedRadioButton = (RadioButton) sexeToggleGroup.getSelectedToggle();
            selectedEtudiant.setSexe(selectedRadioButton.getText());
            selectedEtudiant.setFiliere(filiereChoiceBox.getValue());

            boolean success = etudiantModel.update(selectedEtudiant);

            if (success) {
                loadEtudiants();
            } else {
                showErrorAlert("Erreur lors de la modification de l'étudiant.");
            }
        } else {
            showErrorAlert("Aucun étudiant sélectionné.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
   