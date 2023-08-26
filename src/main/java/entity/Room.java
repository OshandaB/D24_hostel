package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();

    public Room(String roomId, String roomType, String keyMoney, int qty, int avaliable_room) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.qty = qty;
        this.avaliable_room = avaliable_room;
    }
}
