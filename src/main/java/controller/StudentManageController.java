package controller;

import bo.custom.StudentBO;
import bo.custom.impl.StudentBOImpl;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;

public class StudentManageController {
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
        StudentDTO studentDTO = new StudentDTO(id,name,address,contact,dob,gender);
        boolean saved = studentBO.saveStudent(studentDTO);
        if (saved){
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
        StudentDTO studentDTO = new StudentDTO(id,name,address,contact,dob,gender);
        boolean updated = studentBO.updateStudent(studentDTO);
        if (updated){
            System.out.println("update Succesfully");
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(id);
        boolean deleted = studentBO.deleteStudent(studentDTO);
        if (deleted){
            System.out.println("delete Succesfully");
        }
    }
}
