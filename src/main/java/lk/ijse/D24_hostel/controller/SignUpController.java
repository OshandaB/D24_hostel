package lk.ijse.D24_hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.D24_hostel.dto.UserDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    UserBO userBO = new UserBOImpl();
    @FXML
    private TextField txtPwd;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    public void signUpOnAction(ActionEvent event) {
        String userId = txtUserId.getText();
        String userName = txtUserName.getText();
        String password = txtPwd.getText();

        UserDTO userDTO = new UserDTO(userId,userName,password);

        boolean saved = userBO.saveUsers(userDTO);

        if (saved){
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"SIGN Up COMPLETED");
            alert.show();
        }

    }
    public String generateNextId(){
        String nextUserId = userBO.generateNextUserId();
        if (nextUserId != null) {
            String[] strings = nextUserId.split("user-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "user-" + digit;
        }
        return "user-001";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
  txtUserId.setText(generateNextId());
    }
}
