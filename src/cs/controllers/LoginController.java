package cs.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import cs.Utilities;
import cs.models.FXParent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller for the login.
 */
public class LoginController extends FXParent implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label emailLabel, passwordLabel, titleLabel;
    @FXML
    private Button loginButton;
    private boolean french;
    private static final String LOGIN_FILE = "login_activity.txt";

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }

    /**
     * Checking the region and updating the language accordingly.
     */
    @Override
    public void updateUI() {

        french = System.getProperty("user.language").equalsIgnoreCase("fr");

        if (french) {
            titleLabel.setText("Calendrier des clients (Zone: "
                    + ZoneId.systemDefault().toString() + ")");
            emailLabel.setText("Mot de passe de l'email");
            passwordLabel.setText("Mot de passe");
            loginButton.setText("Connexion");
        } else {
            titleLabel.setText("Client Schedule (Zone: "
                    + ZoneId.systemDefault().toString() + ")");
        }

    }

    /**
     * When login button is pressed, it will check if user exists or not.
     *
     * @param ae source
     */
    @FXML
    public void login(ActionEvent ae) {

        String email = emailField.getText();
        String pass = passwordField.getText();

        if (email.isEmpty() || pass.isEmpty()) {
            Utilities.error(french ? "Connexion" : "Login",
                    french
                            ? "Les donn�es vides ne sont pas accept�es!"
                            : "Empty data is not accepted!",
                    getStage());
        } else if (getDatabase().loginUser(email, pass)) {
            updateLog(email, pass, "Passed");
            getApplication().update(Utilities.DASHBOARD_FXML);
        } else {
            updateLog(email, pass, "Failed");
            Utilities.error(french ? "Connexion" : "Login",
                    french
                            ? "Les identifiants sont faux!"
                            : "Credentials are wrong!",
                    getStage());
        }

    }

    /**
     * Updating the log file with the login attempt.
     *
     * @param email for login attempt
     * @param pass for login attempt
     * @param status of login attempt
     */
    private void updateLog(String email, String pass, String status) {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(LOGIN_FILE, true));
            writer.append((new Date()) + " => Login " + status + ": Email = " + email + ", Password = " + pass+"\n");
            writer.close();
        } catch (IOException e) {}

    }

}
