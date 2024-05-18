package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class signin {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorMessageLabel;

    @FXML
    private void signInButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // Vérifiez les informations de connexion et affichez un message d'erreur si nécessaire
        if (username.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("Veuillez saisir un nom d'utilisateur et un mot de passe.");
        } else {
            // Logique de connexion
        }
    }
}