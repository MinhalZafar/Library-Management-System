/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import librarymanagment.DatabaseHandler.DatabaseHandler;
import librarymanagment.addmember.Util.LibraryManagmentUtil;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/librarymanagment/login/login.fxml"));        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Library Managment Login");
        
         LibraryManagmentUtil.setStageIcon(stage);
        
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
