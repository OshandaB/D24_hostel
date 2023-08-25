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

    public Room toEntity() {
        Room room = new Room(roomTypeId,roomType,keyMoney,qty);
        return room;
    }
}
