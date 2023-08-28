package lk.ijse.D24_hostel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.D24_hostel.dto.UserDTO;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ComboBox cmbUserId;
    public AnchorPane paneLogin;
    UserBO userBO = new UserBOImpl();
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
      static String id ;
    public void signInOnAction(ActionEvent event) {
id= (String) cmbUserId.getValue();


        UserDTO userDTO = userBO.searchUsers((String) cmbUserId.getValue());
        String useName = userDTO.getUserName();
        String pwd = userDTO.getPassword();

        if(useName.equals(txtUserName.getText()) && pwd.equals(txtPassword.getText())){
//            new ChangePwdController(userDTO.getUserId());
            Stage stage = new Stage();
            stage.resizableProperty().setValue(true);
            try {
                URL resource = DashboardController.class.getResource("/assests/Dashboard.fxml");
                Parent load = FXMLLoader.load(resource);
                stage.setScene(new Scene(load));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("D24 Hostel");

            stage.centerOnScreen();


            stage.show();
            paneLogin.getScene().getWindow().hide();
            DashboardController changePwdController = new DashboardController();
            changePwdController.setUserId((String) cmbUserId.getValue());

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Login Unsuccessfully!");
            alert.show();
        }

    }

    public void signUpOnAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = DashboardController.class.getResource("/assests/Sign_up.fxml");
            Parent load = FXMLLoader.load(resource);
            stage.setScene(new Scene(load));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("OB superMarket");

        stage.centerOnScreen();


        stage.show();
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      loadIds();
    }

    private void loadIds() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<String> userIds = userBO.loadUserIds();
        for (String studentId : userIds) {
            obList.add(studentId);
        }
        cmbUserId.setItems(obList);
    }

}
