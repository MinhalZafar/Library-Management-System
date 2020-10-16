/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagment.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import librarymanagment.DatabaseHandler.DatabaseHandler;
import librarymanagment.alert.AlertMaker;
import librarymanagment.memberlist.ListMemberController;
import librarymanagment.memberlist.ListMemberController.Member;

/**
 * FXML Controller class
 *
 * @author Syed Minhal
 */
public class MemberAddController implements Initializable {

    DatabaseHandler handler;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXButton savebutton;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton close;
    
        private Boolean isInEditMode = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    private void addmember(ActionEvent event) {

        String mName = name.getText();
        String mID = id.getText();
        String mMobile = mobile.getText();
        String mEmail = Email.getText();

        Boolean flag = mName.isEmpty() || mID.isEmpty() || mMobile.isEmpty() || mEmail.isEmpty();
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }

        if(isInEditMode){
            handleUpdateMember();
        return;
        }
        
        String st = "INSERT INTO MEMBER VALUES ("
                + "'" + mID + "',"
                + "'" + mName + "',"
                + "'" + mMobile + "',"
                + "'" + mEmail + "'"
                + ")";
        System.out.println(st);
        if (handler.execAction(st)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Saved");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error Occured");
            alert.showAndWait();
        }

    }

    public void infalteUI(ListMemberController.Member member)
    {
        name.setText(member.getName());
        id.setText(member.getId());
        id.setEditable(false);
        mobile.setText(member.getMobile());
        Email.setText(member.getEmail());
        
        isInEditMode = Boolean.TRUE;
    }

    private void handleUpdateMember() 
    {
        Member member = new ListMemberController.Member(name.getText(), id.getText(), mobile.getText(), Email.getText());
        if (DatabaseHandler.getInstance().updateMember(member)) {
            AlertMaker.showSimpleAlert("Success", "Member Updated");
        } else {
            AlertMaker.showErrorMessage("Failed", "Cant update member");
        }
    }

    
    @FXML
    private void cencel(ActionEvent event) {
           Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
