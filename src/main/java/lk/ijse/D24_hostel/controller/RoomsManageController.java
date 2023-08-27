package lk.ijse.D24_hostel.controller;

import lk.ijse.D24_hostel.bo.custom.RoomBO;
import lk.ijse.D24_hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.D24_hostel.dto.RoomDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lk.ijse.D24_hostel.tdm.RoomTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomsManageController implements Initializable {
    public AnchorPane roomsPane;
    RoomBO roomBO = new RoomBOImpl();
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtKeyMonet;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtType;
    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableView<RoomTM> tableRoom;

    public void SaveOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        String roomType = txtType.getText();
        String keyMoney = txtKeyMonet.getText();
        int qty = Integer.parseInt(txtQty.getText());
        RoomDTO roomDTO = new RoomDTO(id, keyMoney, roomType, qty);
        boolean saved = roomBO.saveRooms(roomDTO);
        if (saved) {
            System.out.println("Saved");
            getAll();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String roomType = txtType.getText();
        String keyMoney = txtKeyMonet.getText();
        int qty = Integer.parseInt(txtQty.getText());
        RoomDTO roomDTO = new RoomDTO(id, keyMoney, roomType, qty);
        boolean updated = roomBO.updateRooms(roomDTO);
        if (updated) {
            System.out.println("updated");
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomTypeId(id);
        boolean deleted = roomBO.deleteRooms(roomDTO);
        if (deleted) {
            System.out.println("updated");
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void getAll() throws SQLException {
        tableRoom.getItems().clear();
        ArrayList<RoomDTO> allRooms = roomBO.getAllRooms();
        for (RoomDTO allRoom : allRooms) {
            tableRoom.getItems().addAll(new RoomTM(allRoom.getRoomTypeId(), allRoom.getKeyMoney(), allRoom.getRoomType(), allRoom.getQty()));
        }

    }

    private void setCellValueFactory() {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));

        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void dashboardOnAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = DashboardController.class.getResource("/assests/Dashboard.fxml");
            Parent load = FXMLLoader.load(resource);
            stage.setScene(new Scene(load));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("OB superMarket");

        stage.centerOnScreen();


        stage.show();
        roomsPane.getScene().getWindow().hide();
    }
    }

