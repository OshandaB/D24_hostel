package lk.ijse.D24_hostel.dto;

import lk.ijse.D24_hostel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private String roomTypeId;
    private String keyMoney;
    private String roomType;
    private int qty;
    private int avaliable_room;
    private int maxStudent;

    public RoomDTO(String roomTypeId, String keyMoney, String roomType, int qty,int maxStudent) {
        this.roomTypeId = roomTypeId;
        this.keyMoney = keyMoney;
        this.roomType = roomType;
        this.qty = qty;
        this.maxStudent=maxStudent;
    }

    public RoomDTO(String roomId, String keyMoney, String roomType, int qty) {
        this.roomTypeId = roomTypeId;
        this.keyMoney = keyMoney;
        this.roomType = roomType;
        this.qty = qty;
    }

    public Room toEntity() {
        Room room = new Room(roomTypeId,roomType,keyMoney,qty,avaliable_room,maxStudent);
        return room;
    }
}
