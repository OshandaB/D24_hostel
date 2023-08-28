package lk.ijse.D24_hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.D24_hostel.dto.UserDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePwdController implements Initializable  {
UserBO userBO = new UserBOImpl();
    @FXML
    private Label lblUsrId;

    @FXML
    private TextField txtConfirmPwd;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private  String userId;

    public String getUserId() {
        return userId;
    }
//


    public void setUserId(String userId) {
        this.userId = userId;
        System.out.println(userId);

    }

    public void ChangeOnAction(ActionEvent event) {

        String useName = txtUserName.getText();
        String pwd = txtPassword.getText();
        String confPwd = txtConfirmPwd.getText();
        if (pwd.equals(confPwd)){
            System.out.println(LoginController.id);
            UserDTO dto = new UserDTO(LoginController.id,useName,pwd);
            boolean updated = userBO.updateUsers(dto);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"PASSWORD Change SuccessFully!!");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"PASSWORD doesnot match !!!");
            alert.show();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
