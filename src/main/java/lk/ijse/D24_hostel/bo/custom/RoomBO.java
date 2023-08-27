package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO {
    boolean saveRooms(RoomDTO roomDTO);



    boolean updateRooms(RoomDTO roomDTO);

    boolean deleteRooms(RoomDTO roomDTO);
    public ArrayList<RoomDTO> getAllRooms() throws SQLException;
}
