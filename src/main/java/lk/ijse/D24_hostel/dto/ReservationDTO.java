package lk.ijse.D24_hostel.dto;

import lk.ijse.D24_hostel.embedded.ReservationDetailsPK;
import lk.ijse.D24_hostel.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String roomTypeId;
    private String studentId;
    private String resId;
    private LocalDate date;
    private String status;

    public Reservation toEntity() {
        ReservationDetailsPK reservationDetailsPK = new ReservationDetailsPK(studentId,roomTypeId);
        Reservation reservation = new Reservation(resId,reservationDetailsPK,date,status);
        return reservation;
    }
}
