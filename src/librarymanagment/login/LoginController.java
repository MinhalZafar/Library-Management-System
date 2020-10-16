/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import librarymanagment.setting.Preferences;
import org.apache.commons.codec.digest.DigestUtils;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import librarymanagment.addmember.Util.LibraryManagmentUtil;
import librarymanagment.main.MainController;

/**
 * FXML Controller class
 *
 * @author Syed Minhal
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    /**
     * Initializes the controller class.
     */
    Preferences preference;
    private Label titlelabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preference = Preferences.getPreferences();

    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = DigestUtils.shaHex(password.getText());

        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
            closeStage();
            loadMain();
        } else {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }

    }

    @FXML
    private void handleCanelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage)username.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/librarymanagment/main1/Mian1.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Managment");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryManagmentUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
