package lk.ijse.D24_hostel.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTM {
    private String studentId;
    private String studentName;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
}
