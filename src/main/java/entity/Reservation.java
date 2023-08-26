package entity;

import embedded.ReservationDetailsPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @Column(name = "res_id")
    private String resId;
    @EmbeddedId
    private ReservationDetailsPK reservationDetailsPK;


    @Column(name = "date")
    private LocalDate date;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "student_id",
            referencedColumnName = "student_id",
            insertable = false,
            updatable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "room_type_id",
            referencedColumnName = "room_type_id",
            insertable = false,
            updatable = false)
    private Room room;

    public Reservation(String resId, ReservationDetailsPK reservationDetailsPK, LocalDate date, String status) {
        this.resId = resId;
        this.reservationDetailsPK = reservationDetailsPK;
        this.date = date;
        this.status = status;
    }
}
