package lk.ijse.D24_hostel.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTM {
    private String studentId;
    private String roomTypeId;
    private String resId;
    private LocalDate Date;
    private String status;
}
