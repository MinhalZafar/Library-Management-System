/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.booklist;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import librarymanagment.DatabaseHandler.DatabaseHandler;
import librarymanagment.FXMLDocumentController;
import librarymanagment.addmember.Util.LibraryManagmentUtil;
import librarymanagment.alert.AlertMaker;
import librarymanagment.main.MainController;

/**
 * FXML Controller class
 *
 * @author Syed Minhal
 */
public class BookListController implements Initializable {

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> TitleCol;
    @FXML
    private TableColumn<Book, String> IdCol;
    @FXML
    private TableColumn<Book, String> AuthorCol;
    @FXML
    private TableColumn<Book, String> PublisherCol;
    @FXML
    private TableColumn<Book, Boolean> AvalibilityCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        PublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        AvalibilityCol.setCellValueFactory(new PropertyValueFactory<>("availabilty"));
    }

    private void loadData() {
        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM BOOK";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()) {
                String titlex = rs.getString("title");
                String author = rs.getString("author");
                String id = rs.getString("id");
                String publisher = rs.getString("publisher");
                Boolean avail = rs.getBoolean("isAvail");

                list.add(new Book(titlex, id, author, publisher, avail));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BookListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableView.setItems(list);
    }

    @FXML
    private void handleBookDeleteOp(ActionEvent event) {

        Book selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("No book selected", "Please select a book for deletion.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to delete the book " + selectedForDeletion.getTitle() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = DatabaseHandler.getInstance().deleteBook(selectedForDeletion);
            if (result) {
                AlertMaker.showSimpleAlert("Book deleted", selectedForDeletion.getTitle() + " was deleted successfully.");
                list.remove(selectedForDeletion);
            } else {
                AlertMaker.showSimpleAlert("Failed", selectedForDeletion.getTitle() + " could not be deleted");
            }
        } else {
            AlertMaker.showSimpleAlert("Deletion cancelled", "Deletion process cancelled");
        }

    }

    @FXML
    private void handleBookEditOp(ActionEvent event) {

        //Fetch the selected row
        Book selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No book selected", "Please select a book for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/librarymanagment/FXMLDocument.fxml"));
            Parent parent = loader.load();

            FXMLDocumentController controller = (FXMLDocumentController) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Book");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryManagmentUtil.setStageIcon(stage);

            stage.setOnCloseRequest((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleRefresh(ActionEvent event) {

        loadData();
    }

    public static class Book {

        private final SimpleStringProperty title;
        private final SimpleStringProperty id;
        private final SimpleStringProperty author;
        private final SimpleStringProperty publisher;
        private final SimpleBooleanProperty availabilty;

        public Book(String title, String id, String author, String pub, Boolean avail) {
            this.title = new SimpleStringProperty(title);
            this.id = new SimpleStringProperty(id);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(pub);
            this.availabilty = new SimpleBooleanProperty(avail);
        }

        public String getTitle() {
            return title.get();
        }

        public String getId() {
            return id.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        public Boolean getAvailabilty() {
            return availabilty.get();
        }

    }

}
