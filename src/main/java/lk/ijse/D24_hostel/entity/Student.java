package lk.ijse.D24_hostel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "name")
    private String studentName;

    @Column(name =  "address" )
    private String address;
    @Column(name = "contact_no")
    private String contact;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "gender")
    private String gender;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private List<Reservation> reservations = new ArrayList<>();
}
