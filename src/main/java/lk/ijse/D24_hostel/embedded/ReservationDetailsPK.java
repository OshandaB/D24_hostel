package lk.ijse.D24_hostel.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReservationDetailsPK implements Serializable {

    @Column(name = "student_id",unique = true)
    private String studentId;
    @Column(name = "room_type_id")
    private String roomId;
}
