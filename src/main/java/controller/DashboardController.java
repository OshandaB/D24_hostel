package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardController {
    public AnchorPane pane1;

    public void studentOnAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = DashboardController.class.getResource("/assests/Student_manage.fxml");
            Parent load = FXMLLoader.load(resource);
            stage.setScene(new Scene(load));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("OB superMarket");

        stage.centerOnScreen();


        stage.show();
        pane1.getScene().getWindow().hide();
    }

    public void roomsOnAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = DashboardController.class.getResource("/assests/Rooms_manage.fxml");
            Parent load = FXMLLoader.load(resource);
            stage.setScene(new Scene(load));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("OB superMarket");

        stage.centerOnScreen();


        stage.show();
        pane1.getScene().getWindow().hide();
    }

    public void reservationOnAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = DashboardController.class.getResource("/assests/Reservation.fxml");
            Parent load = FXMLLoader.load(resource);
            stage.setScene(new Scene(load));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("OB superMarket");

        stage.centerOnScreen();


        stage.show();
        pane1.getScene().getWindow().hide();
    }

    public void paymentOnAction(ActionEvent event) {

    }
}
