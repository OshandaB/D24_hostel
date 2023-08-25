package controller;

import bo.custom.ReservationBO;
import bo.custom.impl.ReservationBOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private ComboBox<String> cmbRoomIds;

    @FXML
    private ComboBox<?> cmbStudentIds;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtStatus;

    ReservationBO reservationBO = new ReservationBOImpl();

    public void SaveOnAction(ActionEvent actionEvent) {

    }

    public void updateOnAction(ActionEvent actionEvent) {

    }

    public void deleteOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    private void loadStIds() {

    }
}
