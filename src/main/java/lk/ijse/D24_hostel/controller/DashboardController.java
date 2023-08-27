package lk.ijse.D24_hostel.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public AnchorPane pane1;
    public Label lblTime;
    public Label lblTime1;

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
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = DashboardController.class.getResource("/assests/Payment_details.fxml");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
TimeNow();
    }

    private void TimeNow() {
        Thread thread =new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm ");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM,  dd, yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat(" a");
            while (true){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());
                final String timenow2 = sdf2.format(new Date());
                Platform.runLater(() ->{
                    lblTime.setText(timenow);
                    lblTime1.setText(timenow2);
//                    labelTime.setStyle("-fx-font-size: 25px; -fx-text-fill: white");
//                    labelTime1.setText(timenow1);
//                    labelTime1.setStyle("-fx-font-size: 15px; -fx-text-fill: white");
                });
            }
        });
        thread.start();
    }
}
