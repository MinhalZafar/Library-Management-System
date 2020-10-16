/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.main1;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import librarymanagment.addmember.Util.LibraryManagmentUtil;
import librarymanagment.main.MainController;
import librarymanagment.setting.Preferences;

/**
 * FXML Controller class
 *
 * @author Syed Minhal
 */
public class Mian1Controller implements Initializable {

    @FXML
    private AnchorPane rootPane;

    Preferences preference;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preference = Preferences.getPreferences();

    }

    @FXML
    private void handleMenuClose(ActionEvent event) {

        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void gotomain(ActionEvent event) {
        loadwindow("/librarymanagment/main/main.fxml", "Main Window");
        //closeStage();
    }

//    private void closeStage() {
//        ((Stage) rootPane.getScene().getWindow()).close();
//    }

    void loadwindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryManagmentUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void onlinecheck(ActionEvent event) {
        loadwindow("/librarymanagment/main/graph/FXML.fxml", "All Information");
    }

    @FXML
    private void gotoabout(ActionEvent event) {
        loadwindow("/librarymanagment/about/about.fxml", "About Me");
    }

    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
        Stage stage = ((Stage) rootPane.getScene().getWindow());
        stage.setFullScreen(!stage.isFullScreen());
    }
}
