/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.about;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import librarymanagment.addmember.Util.LibraryManagmentUtil;

/**
 * FXML Controller class
 *
 * @author Syed Minhal
 */
public class AboutController implements Initializable {

    private static final String FACEBOOK = "http://www.facebook.com/minhal.zafar.71";
    private static final String FACEBOOK1 = "http://www.facebook.com/irshad";
    private static final String FACEBOOK2 = "http://www.facebook.com/ali";
    private static final String WEBSITE = "http://www.muetszabsoftware.ezyro.com/joomla30/";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void loadWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
            handleWebpageLoadException(url);
        }
    }

    private void handleWebpageLoadException(String url) {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(url);
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane(browser));
        stage.setScene(scene);
        stage.setTitle("Minhal Zafar");
        stage.show();
        LibraryManagmentUtil.setStageIcon(stage);
    }

    @FXML
    private void loadBlog(ActionEvent event) {
        loadWebpage(WEBSITE);

    }

    @FXML
    private void loadFacebook(ActionEvent event) {
        loadWebpage(FACEBOOK);

    }

    @FXML
    private void loadFacebook1(ActionEvent event) {
        loadWebpage(FACEBOOK1);
    }

    @FXML
    private void loadFacebook2(ActionEvent event) {
        loadWebpage(FACEBOOK2);
    }

}
