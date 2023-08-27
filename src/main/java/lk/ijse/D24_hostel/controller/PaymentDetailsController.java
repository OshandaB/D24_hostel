package lk.ijse.D24_hostel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24_hostel.bo.custom.PaymentDetailBO;
import lk.ijse.D24_hostel.bo.custom.impl.PaymentDetailBOImpl;
import lk.ijse.D24_hostel.dto.CustomDTO;
import lk.ijse.D24_hostel.dto.RoomDTO;
import lk.ijse.D24_hostel.tdm.PaymentDetailTM;
import lk.ijse.D24_hostel.tdm.RoomTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentDetailsController implements Initializable {
    PaymentDetailBO paymentDetailBO = new PaymentDetailBOImpl();
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colSId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private AnchorPane paymentPane;

    @FXML
    private TableView<PaymentDetailTM> tblPymentDetails;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     getAll();
     setCellValueFactory();
    }

    private void setCellValueFactory() {
        colSId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));

        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colResId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));



    }

    private void getAll() {
        tblPymentDetails.getItems().clear();
        List<CustomDTO> paymentDetails = paymentDetailBO.getPaymentDetails();
        System.out.println(paymentDetails);
        for (CustomDTO room : paymentDetails) {
            tblPymentDetails.getItems().addAll(new PaymentDetailTM(
                    room.getStudentId(),
                    room.getStudentName(),
                    room.getAddress(),
                    room.getContact(),
                    room.getDob(),
                    room.getGender(),
                    room.getResId(),
                    room.getStatus()));
        }
    }
}
