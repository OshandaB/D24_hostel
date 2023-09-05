package lk.ijse.D24_hostel.controller;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import lk.ijse.D24_hostel.bo.BOFactory;
import lk.ijse.D24_hostel.bo.custom.StudentBO;
import lk.ijse.D24_hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.D24_hostel.controller.util.ValidationController;
import lk.ijse.D24_hostel.dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lk.ijse.D24_hostel.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentManageController implements Initializable {
    public AnchorPane studentPane;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    @FXML
    private TableView<StudentTM> tblStudent;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDob;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;
    StudentBO studentBO = BOFactory.getBoFactory().getBo(BOFactory.BOTypes.StudentBO);

    public void SaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        LocalDate dob = txtDob.getValue();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        StudentDTO studentDTO = new StudentDTO(id, name, address, contact, dob, gender);
        boolean saved = studentBO.saveStudent(studentDTO);
        if (saved) {
            System.out.println("Saved Succesfully");
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        LocalDate dob = txtDob.getValue();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        StudentDTO studentDTO = new StudentDTO(id, name, address, contact, dob, gender);
        boolean updated = studentBO.updateStudent(studentDTO);
        if (updated) {
            System.out.println("update Succesfully");
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(id);
        boolean deleted = studentBO.deleteStudent(studentDTO);
        if (deleted) {
            System.out.println("delete Succesfully");
        }
    }

    public void dashboardOnAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(true);
        try {
            URL resource = StudentManageController.class.getResource("/assests/Dashboard.fxml");
            Parent load = FXMLLoader.load(resource);
            stage.setScene(new Scene(load));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("OB superMarket");

        stage.centerOnScreen();


        stage.show();
        studentPane.getScene().getWindow().hide();
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void getAll() throws SQLException {
        tblStudent.getItems().clear();
        ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
        for (StudentDTO allStudent : allStudents) {
            tblStudent.getItems().addAll(new StudentTM(allStudent.getStudentId(), allStudent.getStudentName(), allStudent.getAddress(), allStudent.getContact(), allStudent.getDob(), allStudent.getGender()));
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));

        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

    }

    public void txtValiId(KeyEvent keyEvent) {
    }

    public void txtValiName(KeyEvent keyEvent) {
        String name = txtName.getText();
        String name1 = txtContact.getText();

        String name2 = txtAddress.getText();

        try {
            boolean isValidate1 = ValidationController.name(name);
            boolean isValidate = ValidationController.PhoneNumber(name1);
            boolean isValidate2 = ValidationController.address(name2);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate1) {
                txtName.setStyle("-fx-border-color : green");
                txtName.setOnAction((e) -> {
                    txtAddress.requestFocus();
                });
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtName.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        } catch (Exception e) {
        }

    }

    public void txtValiCnt(KeyEvent keyEvent) {
        String name = txtContact.getText();
        String name1 = txtName.getText();
        String name2 = txtAddress.getText();

        try {

            boolean isValidate1 = ValidationController.name(name1);
            boolean isValidate = ValidationController.PhoneNumber(name);
            boolean isValidate2 = ValidationController.address(name2);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate) {
                txtContact.setStyle("-fx-border-color : green");
                txtContact.setOnAction((e) -> {
                    txtAddress.requestFocus();
                });
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtContact.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtValiAddress(KeyEvent keyEvent) {
        String name = txtAddress.getText();
        String name2 = txtContact.getText();
        String name1 = txtName.getText();


        try {
            boolean isValidate = ValidationController.address(name);
            boolean isValidate1 = ValidationController.name(name1);
            boolean isValidate2 = ValidationController.PhoneNumber(name2);
//          btnSave.setDisable(!isValidate | txtCustId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | txtEmail.getText().isEmpty() );
            if (isValidate) {
                txtAddress.setStyle("-fx-border-color : green");
                txtAddress.setOnAction((e) -> {
                    txtAddress.requestFocus();
                });
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtAddress.setStyle("-fx-border-color: red");
                //btnSave.setDisable(true);
                btnSave.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | !isValidate1 | !isValidate2 | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtValiGender(KeyEvent keyEvent) {
    }
}
