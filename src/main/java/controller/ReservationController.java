package controller;

import bo.custom.ReservationBO;
import bo.custom.impl.ReservationBOImpl;
import dto.ReservationDTO;
import embedded.ReservationDetailsPK;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    public Label lblDate;
    public AnchorPane reservationPane;
    @FXML
    private ComboBox<String> cmbRoomIds;

    @FXML
    private ComboBox<String> cmbStudentIds;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtStatus;

    ReservationBO reservationBO = new ReservationBOImpl();

    public void SaveOnAction(ActionEvent actionEvent) {
        String stId = cmbStudentIds.getValue();
        String roomTypeId = cmbRoomIds.getValue();
        String resId = txtId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());
        String status = txtStatus.getText();
        ReservationDetailsPK reservationDetailsPK = new ReservationDetailsPK(stId,roomTypeId);
        ReservationDTO reservationDTO = new ReservationDTO(reservationDetailsPK.getRoomId(),reservationDetailsPK.getStudentId(),resId,date,status);
        boolean saved = reservationBO.saveReservation(reservationDTO);
        if (saved){
            System.out.println("saved");
        }



    }

    public void updateOnAction(ActionEvent actionEvent) {
        String stId = cmbStudentIds.getValue();
        String roomTypeId = cmbRoomIds.getValue();
        String resId = txtId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());
        String status = txtStatus.getText();
        ReservationDetailsPK reservationDetailsPK = new ReservationDetailsPK(stId,roomTypeId);
        ReservationDTO reservationDTO = new ReservationDTO(reservationDetailsPK.getRoomId(),reservationDetailsPK.getStudentId(),resId,date,status);
        boolean updated = reservationBO.updateReservation(reservationDTO);
        if (updated){
            System.out.println("updated");
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String stId = cmbStudentIds.getValue();
        String roomTypeId = cmbRoomIds.getValue();
        String resId = txtId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());
        String status = txtStatus.getText();
        ReservationDetailsPK reservationDetailsPK = new ReservationDetailsPK(stId,roomTypeId);
        ReservationDTO reservationDTO = new ReservationDTO(reservationDetailsPK.getRoomId(),reservationDetailsPK.getStudentId(),resId,date,status);
        boolean updated = reservationBO.deleteReservation(reservationDTO);
        if (updated){
            System.out.println("deleted");
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(String.valueOf(LocalDate.now()));
        loadStIds();
        try {
            loadRoomsIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadRoomsIds() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<String> roomIds = reservationBO.loadRoomIds();
        for (String roomId : roomIds) {
            obList.add(roomId);
        }
        cmbRoomIds.setItems(obList);

    }

    private void loadStIds() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<String> studentIds = reservationBO.loadStudentIds();
        for (String studentId : studentIds) {
            obList.add(studentId);
        }
        cmbStudentIds.setItems(obList);
    }

    public void roomOnAction(ActionEvent event) {
     String id = cmbRoomIds.getValue();
//        int count = reservationBO.countReservation(id);
//
//        System.out.println(count);
    }

    public void dashboardOnAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = ReservationController.class.getResource("/assests/Dashboard.fxml");
            Parent load = FXMLLoader.load(resource);
            stage.setScene(new Scene(load));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("OB superMarket");

        stage.centerOnScreen();


        stage.show();
        reservationPane.getScene().getWindow().hide();
    }

}
