/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.setting;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Syed Minhal
 */
public class SettingController implements Initializable {

    @FXML
    private JFXTextField nDayWithoutFine;
    @FXML
    private JFXTextField finePerDay;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDefaultValues();

    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        int ndays = Integer.parseInt(nDayWithoutFine.getText());
        float fine = Float.parseFloat(finePerDay.getText());
        String uname = username.getText();
        String pass = password.getText();

        Preferences preferences = Preferences.getPreferences();
        preferences.setnDaysWithoutFine(ndays);
        preferences.setFinePerDay(fine);
        preferences.setUsername(uname);
        preferences.setPassword(pass);

        Preferences.writePreferenceToFile(preferences);

    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        ((Stage) nDayWithoutFine.getScene().getWindow()).close();

    }

    private void initDefaultValues() {
        Preferences preferences = Preferences.getPreferences();
        nDayWithoutFine.setText(String.valueOf(preferences.getnDaysWithoutFine()));
        finePerDay.setText(String.valueOf(preferences.getFinePerDay()));
        username.setText(String.valueOf(preferences.getUsername()));
        password.setText(String.valueOf(preferences.getPassword()));
    }

}
