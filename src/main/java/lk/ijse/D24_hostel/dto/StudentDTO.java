package lk.ijse.D24_hostel.dto;

import lk.ijse.D24_hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String studentId;
    private String studentName;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
    public Student toEntity() {
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setStudentName(this.studentName);
        student.setAddress(this.address);

        student.setContact(this.contact);
        student.setDob(this.dob);
        student.setGender(this.gender);
        return student;
    }
}
