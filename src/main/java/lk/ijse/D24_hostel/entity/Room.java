package lk.ijse.D24_hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Room")
public class Room {
@Id
    @Column(name = "room_type_id")
    private String roomId;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "key_money")
    private String keyMoney;
    @Column(name = "qty")
    private int qty;
    private int avaliable_room;
    private int maximum_student;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();

    public Room(String roomId, String roomType, String keyMoney, int qty, int avaliable_room,int maximum_student) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.qty = qty;
        this.avaliable_room = avaliable_room;
        this.maximum_student=maximum_student;
    }
}
