package lk.ijse.D24_hostel.controller;

import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import lk.ijse.D24_hostel.bo.BOFactory;
import lk.ijse.D24_hostel.bo.custom.RoomBO;
import lk.ijse.D24_hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.D24_hostel.controller.util.ValidationController;
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
    public TextField txtMaxStudent;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    RoomBO roomBO = BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RoomBO);
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
        int max = Integer.parseInt(txtMaxStudent.getText());
        RoomDTO roomDTO = new RoomDTO(id, keyMoney, roomType, qty, max);
        roomDTO.setAvaliable_room(qty);
        boolean saved = roomBO.saveRooms(roomDTO);
        if (saved) {
            System.out.println("Saved");
            getAll();
            clearAll();
            removeStyle();
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        String roomType = txtType.getText();
        String keyMoney = txtKeyMonet.getText();
        int qty = Integer.parseInt(txtQty.getText());
        int max = Integer.parseInt(txtMaxStudent.getText());
        RoomDTO roomDTO = new RoomDTO(id, keyMoney, roomType, qty, max);
        roomDTO.setAvaliable_room(qty);
        boolean updated = roomBO.updateRooms(roomDTO);
        if (updated) {
            System.out.println("updated");
            getAll();
            clearAll();
            removeStyle();
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomTypeId(id);
        boolean deleted = roomBO.deleteRooms(roomDTO);
        if (deleted) {
            System.out.println("updated");
            getAll();
            clearAll();
            removeStyle();
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
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

    public void tblOnAction(MouseEvent mouseEvent) {
        removeStyle();
        RoomTM tm = (RoomTM) tableRoom.getSelectionModel().getSelectedItem();
        txtId.setText(tm.getRoomTypeId());
        txtQty.setText(String.valueOf(tm.getQty()));
        txtKeyMonet.setText(tm.getKeyMoney());
        txtType.setText(tm.getRoomType());


    }

    public void txtValiId(KeyEvent keyEvent) {
        String name = txtId.getText();
        String keyMoeny = txtKeyMonet.getText();
        String qty = txtQty.getText();
        String maxQty = txtMaxStudent.getText();

        try {
            boolean isValidate = ValidationController.roomTypeId(name);
            boolean isValidate1 = ValidationController.keyMoney(keyMoeny);
            boolean isValidate2 = ValidationController.qty(qty);
            boolean isValidate3 = ValidationController.qty(maxQty);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate) {
                txtId.setStyle("-fx-border-color : green");
                txtId.setOnAction((e) -> {
                    txtKeyMonet.requestFocus();
                });
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            } else {
                txtId.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            }
        } catch (Exception e) {
        }

    }

    public void txtValiKeyMoney(KeyEvent keyEvent) {
        String name = txtId.getText();
        String keyMoeny = txtKeyMonet.getText();
        String qty = txtQty.getText();
        String maxQty = txtMaxStudent.getText();


        try {
            boolean isValidate = ValidationController.roomTypeId(name);
            boolean isValidate1 = ValidationController.keyMoney(keyMoeny);
            boolean isValidate2 = ValidationController.qty(qty);
            boolean isValidate3 = ValidationController.qty(maxQty);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate1) {
                txtKeyMonet.setStyle("-fx-border-color : green");
                txtKeyMonet.setOnAction((e) -> {
                    txtKeyMonet.requestFocus();
                });
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            } else {
                txtKeyMonet.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            }
        } catch (Exception e) {
        }

    }

    public void txtQty(KeyEvent keyEvent) {
        String name = txtId.getText();
        String keyMoeny = txtKeyMonet.getText();
        String qty = txtQty.getText();
        String maxQty = txtMaxStudent.getText();


        try {
            boolean isValidate = ValidationController.roomTypeId(name);
            boolean isValidate1 = ValidationController.keyMoney(keyMoeny);
            boolean isValidate2 = ValidationController.qty(qty);
            boolean isValidate3 = ValidationController.qty(maxQty);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate2) {
                txtQty.setStyle("-fx-border-color : green");
                txtQty.setOnAction((e) -> {
                    txtKeyMonet.requestFocus();
                });
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            } else {
                txtQty.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtMaxQty(KeyEvent keyEvent) {
        String name = txtId.getText();
        String keyMoeny = txtKeyMonet.getText();
        String qty = txtQty.getText();
        String maxQty = txtMaxStudent.getText();


        try {
            boolean isValidate = ValidationController.roomTypeId(name);
            boolean isValidate1 = ValidationController.keyMoney(keyMoeny);
            boolean isValidate2 = ValidationController.qty(qty);
            boolean isValidate3 = ValidationController.qty(maxQty);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate3) {
                txtMaxStudent.setStyle("-fx-border-color : green");
                txtMaxStudent.setOnAction((e) -> {
                    txtKeyMonet.requestFocus();
                });
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            } else {
                txtMaxStudent.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | !isValidate3 | txtId.getText().isEmpty() | txtKeyMonet.getText().isEmpty() | txtQty.getText().isEmpty() | txtMaxStudent.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void removeStyle() {
        txtQty.setStyle(null);
        txtId.setStyle(null);
        txtKeyMonet.setStyle(null);
        txtMaxStudent.setStyle(null);
    }

    public void clearAll() {
        txtQty.setText(null);
        txtId.setText(null);
        txtKeyMonet.setText(null);
        txtMaxStudent.setText(null);
        txtType.setText(null);
    }
}

