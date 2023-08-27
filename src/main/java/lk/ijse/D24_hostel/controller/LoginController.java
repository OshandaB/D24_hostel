package lk.ijse.D24_hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    public void signInOnAction(ActionEvent event) {
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
}
