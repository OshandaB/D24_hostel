package dto;

import entity.Room;
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

    public RoomDTO(String roomTypeId, String keyMoney, String roomType, int qty) {
        this.roomTypeId = roomTypeId;
        this.keyMoney = keyMoney;
        this.roomType = roomType;
        this.qty = qty;
    }

    public Room toEntity() {
        Room room = new Room(roomTypeId,roomType,keyMoney,qty,avaliable_room);
        return room;
    }
}
