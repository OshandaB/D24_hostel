package controller;

import bo.custom.StudentBO;
import bo.custom.impl.StudentBOImpl;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import view.tdm.RoomTM;
import view.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentManageController implements Initializable {
    public AnchorPane studentPane;
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
    StudentBO studentBO = new StudentBOImpl();

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
            tblStudent.getItems().addAll(new StudentTM(allStudent.getStudentId(), allStudent.getStudentName(), allStudent.getAddress(), allStudent.getContact(),allStudent.getDob(),allStudent.getGender()));
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
}
