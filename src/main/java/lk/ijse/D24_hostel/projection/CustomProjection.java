package lk.ijse.D24_hostel.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomProjection {
    private String studentId;
    private String studentName;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
    private String resId;
    private String status;

}
