package lk.ijse.D24_hostel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24_hostel.bo.BOFactory;
import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.D24_hostel.controller.util.ValidationController;
import lk.ijse.D24_hostel.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private AnchorPane signUPPane;
    public JFXButton btnSignUp;
    UserBO userBO = BOFactory.getBoFactory().getBo(BOFactory.BOTypes.UserBO);
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

        UserDTO userDTO = new UserDTO(userId, userName, password);

        boolean saved = userBO.saveUsers(userDTO);

        if (saved) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "SIGN Up COMPLETED");
            alert.show();
            Stage stage = new Stage();
            stage.resizableProperty().setValue(true);
            try {
                URL resource = ReservationController.class.getResource("/assests/login.fxml");
                Parent load = FXMLLoader.load(resource);
                stage.setScene(new Scene(load));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("OB superMarket");

            stage.centerOnScreen();


            stage.show();
            signUPPane.getScene().getWindow().hide();
        }

    }

    public String generateNextId() {
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

    public void txtValiUserId(KeyEvent keyEvent) {
        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String pwd = txtPwd.getText();

        try {
            boolean isValidate = ValidationController.userId(id);
            boolean isValidate1 = ValidationController.userName(name);
            boolean isValidate2 = ValidationController.password(pwd);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate) {
                txtUserId.setStyle("-fx-border-color : green");
                txtUserId.setOnAction((e) -> {
                    txtUserName.requestFocus();
                });
                btnSignUp.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtUserId.getText().isEmpty() | txtUserName.getText().isEmpty() | txtPwd.getText().isEmpty());

            } else {
                txtUserId.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSignUp.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtUserId.getText().isEmpty() | txtUserName.getText().isEmpty() | txtPwd.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtValiName(KeyEvent keyEvent) {
        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String pwd = txtPwd.getText();

        try {
            boolean isValidate = ValidationController.userId(id);
            boolean isValidate1 = ValidationController.userName(name);
            boolean isValidate2 = ValidationController.password(pwd);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate1) {
                txtUserName.setStyle("-fx-border-color : green");
                txtUserName.setOnAction((e) -> {
                    txtPwd.requestFocus();
                });
                btnSignUp.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtUserId.getText().isEmpty() | txtUserName.getText().isEmpty() | txtPwd.getText().isEmpty());

            } else {
                txtUserName.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSignUp.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtUserId.getText().isEmpty() | txtUserName.getText().isEmpty() | txtPwd.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtvaliPassword(KeyEvent keyEvent) {
        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String pwd = txtPwd.getText();

        try {
            boolean isValidate = ValidationController.userId(id);
            boolean isValidate1 = ValidationController.userName(name);
            boolean isValidate2 = ValidationController.password(pwd);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate2) {
                txtPwd.setStyle("-fx-border-color : green");

                btnSignUp.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtUserId.getText().isEmpty() | txtUserName.getText().isEmpty() | txtPwd.getText().isEmpty());

            } else {
                txtPwd.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSignUp.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtUserId.getText().isEmpty() | txtUserName.getText().isEmpty() | txtPwd.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }
}
