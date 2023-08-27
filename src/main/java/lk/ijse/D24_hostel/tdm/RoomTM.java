package lk.ijse.D24_hostel.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTM {
    private String roomTypeId;
    private String keyMoney;
    private String roomType;
    private int qty;
}
