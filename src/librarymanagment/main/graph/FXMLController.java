/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.main.graph;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import librarymanagment.DatabaseHandler.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Syed Minhal
 */
public class FXMLController implements Initializable {

    DatabaseHandler databaseHandler;

    @FXML
    private StackPane bookInfoContainer;

    private PieChart bookChart;
     private PieChart memberChart;
    @FXML
    private StackPane memberInfoContainer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = DatabaseHandler.getInstance();
        initGraphs();
    }

    private void initGraphs() {
        bookChart = new PieChart(databaseHandler.getBookGraphStatistics());
        memberChart = new PieChart(databaseHandler.getMemberGraphStatistics());
        bookInfoContainer.getChildren().add(bookChart);
        memberInfoContainer.getChildren().add(memberChart);
        refreshGraphs();
    }
     private void refreshGraphs() {
        bookChart.setData(databaseHandler.getBookGraphStatistics());
        memberChart.setData(databaseHandler.getMemberGraphStatistics());
    }

}
